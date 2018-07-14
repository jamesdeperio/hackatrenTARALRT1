/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.reward

import android.support.design.widget.AppBarLayout
import kotlinx.coroutines.experimental.Job

interface HasRewardContract {
    interface Event {
        fun onEarnPointsEvent()
        fun onRedeemEvent()
    }

    interface ViewMethod {
        fun gotoARFragment(appbar: AppBarLayout):Job
        fun showClaimRewardDialog():Job
    }

    interface Presenter {
        fun loadDefaultItemList()
    }

    interface Adapter {

        fun notifyLastInserted(): Job
        fun refreshAll(): Job
    }

}