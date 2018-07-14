/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.loader

import android.support.v4.app.Fragment
import jdp.pocketlib.util.Navigate
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.home.HomeController
import nyxdev.hackatren.taralrt1.appmodule.menu.MenuController

class LoaderViewImpl(
        private val fragment: Fragment,
        private val view: LoaderView
) : HasLoaderContract.ViewMethod {
    override fun gotoHomeFragment(): Job = launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.main_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = HomeController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }

    override fun gotoMenuFragment(): Job = launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.main_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = MenuController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }
}