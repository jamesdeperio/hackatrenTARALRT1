/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.ar

import kotlinx.coroutines.experimental.Job

interface HasARContract {
    interface Event {
        fun onCloseCameraEvent()
    }

    interface ViewMethod {
        fun onViewResume():Job
        fun onViewPause():Job
        fun gotoRewardFragment():Job
    }

    interface Presenter

}