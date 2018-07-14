/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.qrpage

import android.annotation.SuppressLint
import android.graphics.Rect
import android.support.v4.app.Fragment
import android.view.View
import jdp.pocketlib.util.Navigate
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.R

class QRPageViewImpl(
        private val fragment: Fragment,
        private val view: QRPageView
) : HasQRPageContract.ViewMethod {
    override fun loadCamera(): Job = launch(UI) {
        view.cameraLayout.visibility=View.VISIBLE
        Navigate.using(fragmentManager = fragment.childFragmentManager)
                .change(layoutID = R.id.camera)
                .to(fragmentToChange = view.barcode)
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }

    @SuppressLint("SetTextI18n")
    override fun updateStatus(status: String): Job  = launch(UI) {
        view.tvStatus.text="STATUS: $status"
    }
    override fun closeCamera(): Job = launch(UI) {
        view.cameraLayout.visibility=View.GONE
        Navigate.using(fragmentManager = fragment.childFragmentManager)
                .change(layoutID = R.id.camera)
                .to(fragmentToChange = Fragment())
                .withBackStack(isBackstackEnabled = false)
                .commitAllowingStateLoss()
    }
    override fun getCameraRec(): Rect = Rect(view.scannerBoundLayout.left,view.scannerBoundLayout.top,view.scannerBoundLayout.right,view.scannerBoundLayout.bottom)

    override fun showAnimation(): Job = launch(UI){
        view.lottieCamera.visibility= View.VISIBLE
    }
    override fun hideAnimation(): Job = launch(UI){
        view.lottieCamera.visibility= View.GONE
    }
    override fun animateSuccess(event: HasQRPageContract.Event):Job = launch(UI) {
        view.lottieCamera.addAnimatorListener(event)
        view.lottieCamera.playAnimation()
    }

}