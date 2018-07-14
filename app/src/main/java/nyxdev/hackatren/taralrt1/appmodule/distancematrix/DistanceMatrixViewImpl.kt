/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.distancematrix

import android.annotation.SuppressLint
import android.support.v4.app.Fragment
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.global.model.obj.eta.Element

class DistanceMatrixViewImpl(
        private val fragment: Fragment,
        private val view: DistanceMatrixView
) : HasDistanceMatrixContract.ViewMethod {
    override fun dismissLoadingDialog(): Job  = launch(UI){
        view.loadingDialog.dismiss()
    }

    override fun showLoadingDialog(): Job  = launch(UI){
        view.loadingDialog.show()
    }

    override fun showErrorDialog(error: String): Job = launch(UI){
        view.errorDialog.contentText=error
        view.errorDialog.show()
    }

    @SuppressLint("SetTextI18n")
    override fun loadETA(elements: Element): Job = launch(UI){
        view.tvDistance.text="DISTANCE: ${elements.distance!!.text}"
        view.tvFare.text="FARE: ${elements.fare!!.text}"
        view.tvTime.text="ESTIMATED TIME: ${elements.duration!!.text}"
    }
}