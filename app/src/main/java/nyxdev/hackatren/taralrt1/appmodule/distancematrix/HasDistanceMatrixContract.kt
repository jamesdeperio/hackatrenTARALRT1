/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.distancematrix

import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.Job
import nyxdev.hackatren.taralrt1.global.model.obj.eta.Element

interface HasDistanceMatrixContract {
    interface Event {
        fun onCheckETAEvent(currentStation: String, destinationStation: String)
    }

    interface ViewMethod {
        fun loadETA(elements: Element):Job
        fun showErrorDialog(error: String):Job
        fun dismissLoadingDialog():Job
        fun showLoadingDialog():Job

    }

    interface Presenter {
        fun loadETA(currentStation: String, destinationStation: String): Disposable
    }

}