/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.menu

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import io.reactivex.functions.Consumer
import kotlinx.coroutines.experimental.Job

interface HasMenuContract {
    interface Event {
        fun signInWithGoogleEvent()
        fun signInWithFacebookEvent()
        fun signInEvent()
        fun registerEvent()
        fun onMenuSignInReceiverEvent(): Consumer<in Any>
    }

    interface ViewMethod {
         fun gotoLoginFragment(): Job
        fun gotoRegistrationFragment():Job
        fun showErrorDialog(error: String):Job
    }

    interface Presenter {
        fun loginWithGoogle(taskGoogleSignInAccount: Task<GoogleSignInAccount>)

    }

}