/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.claimreward

import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.Job

interface HasClaimRewardContract {
    interface Event {

    }


    interface Presenter {
        fun loadLoadRewards(): Disposable
    }

    interface Adapter {

        fun notifyLastInserted(): Job
    }

}