/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.menu

import android.support.v4.app.Fragment
import jdp.pocketlib.util.Navigate
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.login.LoginController
import nyxdev.hackatren.taralrt1.appmodule.registration.RegistrationController

class MenuViewImpl(
        private val fragment: Fragment,
        private val view: MenuView
) : HasMenuContract.ViewMethod {
    override fun showErrorDialog(error: String): Job = launch(UI) {
        view.errorDialog.contentText=error
        view.errorDialog.show()
    }

    override fun gotoRegistrationFragment(): Job = launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.main_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = RegistrationController())
                .withBackStack(isBackstackEnabled = true)
                .commitAllowingStateLoss()
    }


    override fun gotoLoginFragment(): Job= launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.main_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = LoginController())
                .withBackStack(isBackstackEnabled = true)
                .commitAllowingStateLoss()
    }
}