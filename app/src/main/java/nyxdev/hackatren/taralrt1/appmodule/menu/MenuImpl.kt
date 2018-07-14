/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.menu

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MenuImpl(
        private val viewMethod: HasMenuContract.ViewMethod
) : HasMenuContract.Presenter {
    override fun loginWithGoogle(taskGoogleSignInAccount: Task<GoogleSignInAccount>) {
        try {
            val account = taskGoogleSignInAccount.getResult(ApiException::class.java)
            //TODO::
            // CHECK IF EMAIL IS EXISTING IN DB.
            // IF TRUE viewMethod.gotoHomeFragment()
            // ELSE
             viewMethod.gotoRegistrationFragment()
            // event(RegistrationRequirement(email))
        } catch (e: Exception) {
            e.printStackTrace()
            viewMethod.showErrorDialog("Failed to connect with Google. Try Again!")
        }
    }

}