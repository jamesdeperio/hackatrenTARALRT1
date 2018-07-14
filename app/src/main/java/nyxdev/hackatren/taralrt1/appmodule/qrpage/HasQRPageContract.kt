/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.qrpage

import android.animation.Animator
import android.graphics.Rect
import com.google.android.gms.vision.barcode.Barcode
import kotlinx.coroutines.experimental.Job
import nyxdev.hackatren.taralrt1.global.widget.camera.BarcodeRetriever

interface HasQRPageContract {
    interface Event : BarcodeRetriever, Animator.AnimatorListener {
        override fun onRetrievedFailed(error: String?) {}
        override fun onAnimationRepeat(p0: Animator?) {}
        fun onQRCameraEvent()
        fun onQRCameraCloseEvent()

    }

    interface ViewMethod {
        fun loadCamera(): Job
        fun getCameraRec(): Rect
        fun showAnimation(): Job
        fun hideAnimation(): Job
        fun animateSuccess(event: Event): Job
        fun closeCamera():Job
        fun updateStatus(status: String):Job

    }

    interface Presenter {
        fun isBarcodeCentered(barcode: Barcode): Boolean
    }

}