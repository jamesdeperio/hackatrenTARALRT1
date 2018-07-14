/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.nfcpage

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.appmodule.menu.MenuView
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import nyxdev.hackatren.taralrt1.global.model.event.Credential
import nyxdev.hackatren.taralrt1.global.module.nfc.NfcManager
import nyxdev.hackatren.taralrt1.global.util.LoginPage
import nyxdev.hackatren.taralrt1.global.util.RegistrationPage
import nyxdev.hackatren.taralrt1.integration.bus.CredentialBus
import javax.inject.Inject

class NFCPageController : DIBaseFragment(), HasNFCPageContract.Event {
    @field:[Inject] internal lateinit var presenter: HasNFCPageContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasNFCPageContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable
    @field:[Inject] internal lateinit var credentialBus: CredentialBus
    @field:[Inject] internal lateinit var nfcManager: NfcManager.Cycle

    override fun initialization(savedInstanceState: Bundle?) {
        nfcManager.onCreate(context!!)
        nfcManager.setOnTagReadListener(onTagReadListener = this as HasNFCPageContract.Event)
    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {

    }

    override fun onTagRead(tagRead: String) {
        credentialBus.sendEvent(event = Credential(
                type =if (MenuView.isOpenSignIn)  LoginPage.NFC else RegistrationPage.NFC,
                value1 = tagRead))
        viewMethod.updateStatus("OK")
    }
    override fun onPause() {
        super.onPause()
        nfcManager.onPause(context!!)
    }

    override fun onResume() {
        super.onResume()
        nfcManager.onResume(context!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nfcManager.setOnTagReadListener(onTagReadListener = null)
    }
}