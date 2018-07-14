/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.ar

import android.support.v4.app.Fragment
import jdp.pocketlib.util.Navigate
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.reward.RewardController

class ARViewImpl(
        private val fragment: Fragment,
        private val view: ARView
) : HasARContract.ViewMethod {
    override fun gotoRewardFragment(): Job = launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.content_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = RewardController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }

    override fun onViewResume(): Job = launch(UI) {
        view.glView.onResume()
    }

    override fun onViewPause(): Job = launch(UI) {
        view.glView.onPause()
    }
}