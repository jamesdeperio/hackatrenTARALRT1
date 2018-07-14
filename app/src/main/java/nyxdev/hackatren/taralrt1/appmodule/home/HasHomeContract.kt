/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.home

import kotlinx.coroutines.experimental.Job
import nyxdev.hackatren.taralrt1.global.widget.AppBarStateChangeListener

interface HasHomeContract {
    interface Event {
        fun onAppbarStateChanged(state: AppBarStateChangeListener.State)
        fun onBottomNavigationChangedEvent(type: Int)
    }

    interface ViewMethod {
        fun stopTrainAnimation():Job
        fun startTrainAnimation():Job
        fun gotoDashboardFragment():Job
        fun enabledDashBoard():Job
        fun enabledCrowd():Job
        fun enabledReward():Job
        fun gotoCrowdVolumeFragment():Job
        fun gotoRewardFragment():Job
    }

    interface Presenter

}