package view

import kotlinx.coroutines.CoroutineScope
import kotlinx.css.*
import react.*
import react.dom.div
import styled.StyleSheet
import styled.css
import styled.styledA
import styled.styledDiv

private object ApplicationStyles : StyleSheet("ApplicationStyles", isStatic = true) {
    val wrapper by css {
        padding(32.px, 16.px)
    }

    val post by css {
        marginBottom = 32.px
    }
}

interface ApplicationProps : RProps {
    var coroutineScope: CoroutineScope
}

class ApplicationState : RState {
//    var postWithComments: List<PostWithComments> = emptyList()
//    var users: List<User> = emptyList()
}

class ApplicationComponent : RComponent<ApplicationProps, ApplicationState>() {
    init {
        state = ApplicationState()
    }

    private val coroutineContext
        get() = props.coroutineScope.coroutineContext


    override fun RBuilder.render() {
        div {
            styledA("/") {
                css {
                    specific {
                        paddingLeft = 48.px
                    }
                }
                +"hello world!!!"
            }
        }

        styledDiv {
            css {
                +ApplicationStyles.wrapper
            }
            +"test 42"
        }
    }
}
