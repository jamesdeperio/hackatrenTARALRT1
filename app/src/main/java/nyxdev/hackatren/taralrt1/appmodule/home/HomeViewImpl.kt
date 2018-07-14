/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.home

import android.graphics.Color
import android.os.Build
import android.support.v4.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import jdp.pocketlib.util.Navigate
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.crowdvolume.CrowdVolumeController
import nyxdev.hackatren.taralrt1.appmodule.dashboard.DashboardController
import nyxdev.hackatren.taralrt1.appmodule.reward.RewardController

class HomeViewImpl(
        private val fragment: Fragment,
        private val view: HomeView
) : HasHomeContract.ViewMethod {
    override fun enabledDashBoard(): Job= launch(UI) {
        YoYo.with(Techniques.Pulse)
                .duration(500)
                .playOn(view.btnDashboard)
        view.appBarLayout.setExpanded(true,true)
        view.btnDashboard.setTextColor(Color.parseColor("#ff9900"))
        view.btnCrowdVolume.setTextColor(Color.parseColor("#c4afd6"))
        view.btnReward.setTextColor(Color.parseColor("#c4afd6"))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.btnDashboard.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.bulletin_board_selected,null), null, null)
            view.btnCrowdVolume.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.traffic_light,null), null, null)
            view.btnReward.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.wallet_giftcard,null), null, null)
        }else {
            view.btnDashboard.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.bulletin_board_selected), null, null)
            view.btnCrowdVolume.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.traffic_light), null, null)
            view.btnReward.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.wallet_giftcard), null, null)

        }
    }
    override fun enabledCrowd(): Job= launch(UI) {
        YoYo.with(Techniques.Pulse)
                .duration(500)
                .playOn(view.btnCrowdVolume)
        view.appBarLayout.setExpanded(false,true)
        view.btnCrowdVolume.setTextColor(Color.parseColor("#ff9900"))
        view.btnDashboard.setTextColor(Color.parseColor("#c4afd6"))
        view.btnReward.setTextColor(Color.parseColor("#c4afd6"))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.btnDashboard.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.bulletin_board,null), null, null)
            view.btnCrowdVolume.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.traffic_light_selected,null), null, null)
            view.btnReward.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.wallet_giftcard,null), null, null)
        }else {
            view.btnDashboard.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.bulletin_board), null, null)
            view.btnCrowdVolume.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.traffic_light_selected), null, null)
            view.btnReward.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.wallet_giftcard), null, null)
        }
    }

    override fun enabledReward(): Job = launch(UI) {
        YoYo.with(Techniques.Pulse)
                .duration(500)
                .playOn(view.btnReward)
        view.appBarLayout.setExpanded(true,true)
        view.btnReward.setTextColor(Color.parseColor("#ff9900"))
        view.btnCrowdVolume.setTextColor(Color.parseColor("#c4afd6"))
        view.btnDashboard.setTextColor(Color.parseColor("#c4afd6"))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.btnDashboard.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.bulletin_board,null), null, null)
            view.btnCrowdVolume.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.traffic_light,null), null, null)
            view.btnReward.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.wallet_giftcard_selected,null), null, null)
        }else {
            view.btnDashboard.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.bulletin_board), null, null)
            view.btnCrowdVolume.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.traffic_light), null, null)
            view.btnReward.setCompoundDrawablesWithIntrinsicBounds(null, fragment.resources.getDrawable(R.drawable.wallet_giftcard_selected), null, null)
        }
    }

    override fun gotoDashboardFragment(): Job  = launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.content_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = DashboardController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }

    override fun gotoCrowdVolumeFragment(): Job  = launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.content_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = CrowdVolumeController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }

    override fun gotoRewardFragment(): Job  = launch(UI) {
        Navigate.using(fragmentManager = fragment.fragmentManager!!)
                .change(layoutID = R.id.content_container)
                .from(currentFragment = fragment)
                .to(fragmentToChange = RewardController())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }

    override fun startTrainAnimation(): Job = launch(UI){
        view.scrollingBg.start()
      //  view.titleLogo1.visibility=View.VISIBLE
        //view.titleLogo2.visibility=View.GONE
    }

    override fun stopTrainAnimation(): Job = launch(UI){
        view.scrollingBg.stop()
       // view.titleLogo2.visibility=View.VISIBLE
       // view.titleLogo1.visibility=View.GONE
    }
}