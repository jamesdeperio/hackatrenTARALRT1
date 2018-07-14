/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.monitoring

import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.Job

interface HasMonitoringContract {
    interface Event

    interface ViewMethod

    interface Presenter {
        fun loadStations(): Disposable
    }

    interface Adapter {

        fun notifyLastInserted(): Job
        fun refreshAll(): Job
        fun notifyItemIndexAt(index: Int):Job
    }

}