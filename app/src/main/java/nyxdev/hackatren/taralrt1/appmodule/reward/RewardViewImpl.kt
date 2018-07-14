/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.reward

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import jdp.pocketlib.util.Navigate
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.ar.ARController
import nyxdev.hackatren.taralrt1.appmodule.claimreward.ClaimRewardDialog


class RewardViewImpl(
        private val fragment: Fragment,
        private val view: RewardView
) : HasRewardContract.ViewMethod {
    override fun gotoARFragment(appbar: AppBarLayout): Job = launch(UI) {
        appbar.setExpanded(false,true)
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.content_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = ARController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }

    override fun showClaimRewardDialog(): Job = launch(UI){
        val cdd = ClaimRewardDialog(fragment.context!!)
        cdd.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        cdd.show()
    }
}