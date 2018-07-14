/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.main

import android.content.Context
import android.support.v4.app.FragmentManager
import jdp.pocketlib.util.Navigate
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.loader.LoaderController

class MainViewImpl(
        private val context: Context,
        private val view: MainView,
        private val fragmentManager: FragmentManager
) : HasMainContract.ViewMethod {
    override fun gotoLoaderFragment(): Job = launch(UI) {
        Navigate.using(fragmentManager = fragmentManager)
                .change(layoutID = R.id.main_container)
                .to(fragmentToChange = LoaderController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }
}