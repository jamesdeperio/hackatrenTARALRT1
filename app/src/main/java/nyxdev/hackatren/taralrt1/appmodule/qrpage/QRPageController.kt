/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.qrpage

import android.animation.Animator
import android.os.Bundle
import com.google.android.gms.vision.barcode.Barcode
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import nyxdev.hackatren.taralrt1.global.model.event.Credential
import nyxdev.hackatren.taralrt1.global.util.RegistrationPage
import nyxdev.hackatren.taralrt1.integration.bus.CredentialBus
import javax.inject.Inject

class QRPageController : DIBaseFragment(), HasQRPageContract.Event {
    @field:[Inject] internal lateinit var presenter: HasQRPageContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasQRPageContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable
    @field:[Inject] internal lateinit var credentialBus: CredentialBus
    private var barcode: Barcode? =null
    private var isAnimating=false

    override fun initialization(savedInstanceState: Bundle?) {

    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {

    }

    override fun onQRCameraEvent() {
        viewMethod.loadCamera()
    }

    override fun onQRCameraCloseEvent() {
        viewMethod.closeCamera()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription.dispose()
    }

    override fun onRetrieved(barcode: Barcode?) {
        this.barcode=barcode
        if (!isAnimating && barcode!=null && presenter.isBarcodeCentered(barcode))
            viewMethod.animateSuccess(this as HasQRPageContract.Event)

    }

    override fun onAnimationEnd(p0: Animator?) {
        viewMethod.hideAnimation()
        //remove camera
        viewMethod.updateStatus("OK")
        viewMethod.closeCamera()

        credentialBus.sendEvent(event = Credential(type = RegistrationPage.QRCode, value1 = barcode!!.displayValue))
        isAnimating=false
    }

    override fun onAnimationCancel(p0: Animator?) {
        viewMethod.hideAnimation()
        //remove vamera
        viewMethod.updateStatus("OK")
        viewMethod.closeCamera()
        credentialBus.sendEvent(event = Credential(type = RegistrationPage.QRCode, value1 = barcode!!.displayValue))
        isAnimating=false
    }

    override fun onAnimationStart(p0: Animator?) {
        isAnimating=true
        viewMethod.showAnimation()
    }
}