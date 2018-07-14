/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.qrpage

import com.google.android.gms.vision.barcode.Barcode

class QRPageImpl(
        private val viewMethod: HasQRPageContract.ViewMethod
) : HasQRPageContract.Presenter {
    override fun isBarcodeCentered(barcode: Barcode): Boolean
            = barcode.boundingBox.left>=viewMethod.getCameraRec().left-50 &&
            barcode.boundingBox.right<=viewMethod.getCameraRec().right+50 &&
            barcode.boundingBox.top>=viewMethod.getCameraRec().top-50&&
            barcode.boundingBox.bottom<=viewMethod.getCameraRec().bottom+50
}