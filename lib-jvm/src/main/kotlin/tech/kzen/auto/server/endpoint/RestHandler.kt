package tech.kzen.auto.server.endpoint

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import java.io.IOException
import java.net.URI
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors


@Component
class RestHandler {
    //-----------------------------------------------------------------------------------------------------------------
    companion object {
        val classPathRoots = listOf(
            URI("classpath:/public/")
        )

        val resourceDirectories = discoverResourceDirectories()

        private const val cssExtension = "css"

        val allowedExtensions = listOf(
            "html",
            "js",
            cssExtension,
            "svg",
            "png",
            "ico")

        private val cssMediaType = MediaType.valueOf("text/css")


        private const val jvmSuffix = "-jvm"

        private fun discoverResourceDirectories(): List<Path> {
            val builder = mutableListOf<Path>()

            val projectRoot =
                if (Files.exists(Paths.get("src"))) {
                    ".."
                }
                else {
                    "."
                }

            val projectName: String? = Files.list(Paths.get(projectRoot)).use { files ->
                val list = files.collect(Collectors.toList())

                val jvmModule = list.firstOrNull { it.fileName.toString().endsWith(jvmSuffix)}
                if (jvmModule == null) {
                    null
                }
                else {
                    val filename = jvmModule.fileName.toString()

                    filename.substring(0 until filename.length - jvmSuffix.length)
                }
            }

            if (projectName != null) {
                // IntelliJ and typical commandline working dir is project root
                builder.add(Paths.get("$projectName-jvm/src/main/resources/public/"))
                builder.add(Paths.get("$projectName-js/build/distributions/"))

                // Eclipse and Gradle default active working directory is the module
                builder.add(Paths.get("src/main/resources/public/"))
                builder.add(Paths.get("../$projectName-js/build/distributions/"))
            }
            else {
                builder.add(Paths.get("static/"))
            }

            return builder
        }
    }


    //-----------------------------------------------------------------------------------------------------------------
    // TODO: is this secure?
    fun staticResource(serverRequest: ServerRequest): Mono<ServerResponse> {
        val excludingInitialSlash = serverRequest.path().substring(1)

        val resolvedPath =
            if (excludingInitialSlash == "") {
                "index.html"
            }
            else {
                excludingInitialSlash
            }

        val path = Paths.get(resolvedPath).normalize()
        val extension = getFileExtension(path)

        if (! isResourceAllowed(path)) {
            return ServerResponse
                .badRequest()
                .build()
        }

        val bytes: ByteArray = readResource(path)
            ?: return ServerResponse
                .notFound()
                .build()

        val responseBuilder = ServerResponse.ok()

        val responseType: MediaType? = responseType(extension)
        if (responseType !== null) {
            responseBuilder.contentType(responseType)
        }

        return if (bytes.isEmpty()) {
            responseBuilder.build()
        }
        else {
            responseBuilder.body(Mono.just(bytes))
        }
    }


    private fun responseType(extension: String): MediaType? {
        return when (extension) {
            cssExtension -> cssMediaType

            else -> null
        }
    }


    private fun isResourceAllowed(path: Path): Boolean {
        if (path.isAbsolute) {
            return false
        }

        val extension = getFileExtension(path)
        return allowedExtensions.contains(extension)
    }


    private fun readResource(relativePath: Path): ByteArray? {
        // NB: moving up to help with auto-reload of js, TODO: this used to work below classPathRoots
        for (root in resourceDirectories) {
            val candidate = root.resolve(relativePath)
            if (! Files.exists(candidate)) {
                continue
            }

            return Files.readAllBytes(candidate)
        }

        for (root in classPathRoots) {
            try {
                val resourceLocation: URI = root.resolve(relativePath.toString())
                val relativeResource = resourceLocation.path.substring(1)
                val resourceUrl = getResource(relativeResource)
                return resourceUrl.readBytes()
            }
            catch (ignored: Exception) {}
        }

//        println("%%%%% not read: $relativePath")
        return null
    }


    fun getFileExtension(path: Path): String {
        val name = path.fileName ?: return ""

        // null for empty paths and root-only paths
        val fileName = name.toString()
        val dotIndex = fileName.lastIndexOf('.')
        return if (dotIndex == -1) "" else fileName.substring(dotIndex + 1)
    }


    fun getResource(resourceName: String?): URL {
        val loader: ClassLoader =
            Thread.currentThread().contextClassLoader
                ?: RestHandler::class.java.classLoader

        val url = loader.getResource(resourceName)
        checkNotNull(url) { "resource $resourceName not found" }
        return url
    }
}