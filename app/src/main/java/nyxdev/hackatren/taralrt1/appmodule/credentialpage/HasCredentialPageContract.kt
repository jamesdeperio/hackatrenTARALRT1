/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.credentialpage

interface HasCredentialPageContract {
    interface Event {
        fun onTypingEmailDoneEvent()
        fun onTypingPasswordDoneEvent()
    }

    interface ViewMethod {
        fun getEmail(): String
        fun getPassword(): String
        fun getName(): String
    }

    interface Presenter

}