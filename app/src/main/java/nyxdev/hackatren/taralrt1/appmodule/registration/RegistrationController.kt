/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.registration

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import nyxdev.hackatren.taralrt1.appmodule.menu.MenuView
import nyxdev.hackatren.taralrt1.global.base.DIBaseSwipeFragment
import nyxdev.hackatren.taralrt1.global.model.event.Credential
import nyxdev.hackatren.taralrt1.global.util.Constant
import nyxdev.hackatren.taralrt1.global.util.RegistrationPage
import nyxdev.hackatren.taralrt1.integration.bus.CredentialBus
import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity
import javax.inject.Inject

class RegistrationController : DIBaseSwipeFragment(), HasRegistrationContract.Event {
    @field:[Inject] internal lateinit var presenter: HasRegistrationContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasRegistrationContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable
    @field:[Inject] internal lateinit var credentialBus: CredentialBus
    private var accountEntity:AccountEntity = AccountEntity()


    override fun initialization(savedInstanceState: Bundle?) {
        credentialBus.subscribeReceiver(onCredentialReceivedEvent())

    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {
        viewMethod.buildViewPager()
    }

    override fun onCredentialReceivedEvent(): Consumer<in Any> = Consumer {
        Log.e("credential","$it")
        if (it is Credential && it.type== RegistrationPage.Credential) {
            accountEntity.email=it.value1!!
            accountEntity.password=it.value2!!
            accountEntity.name=it.value3!!
        } else if (it is Credential && it.type== RegistrationPage.QRCode)
            accountEntity.rfid=it.value1!!
        else if (it is Credential && it.type== RegistrationPage.NFC)
            accountEntity.nfc=it.value1!!
    }

    override fun onConfirmSuccessCreatingEvent(dialog: SweetAlertDialog) {
        dialog.dismiss()
        viewMethod.gotoHomeFragment()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        subscription.dispose()
        credentialBus.unSubscribeReceiver()
        MenuView.isOpenRegistration=false
    }

    override fun onBackPressedEvent() {
        activity!!.onBackPressed()
    }

    override fun onPrevEvent() {
        viewMethod.moveViewPagerBackward()
    }

    override fun onNextEvent() {
        if (viewMethod.getNextButtonText() == Constant.NEXT){
            viewMethod.moveViewPagerForward()
        }else{
            viewMethod.showLoadingDialog()
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
            subscription.add(presenter.createAccount(accountEntity))
        }
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            RegistrationPage.Credential -> {
                viewMethod.hidePrevButton()
                viewMethod.changeNextToCreate(shouldChange = false)
            }
            RegistrationPage.QRCode -> {
                viewMethod.showPrevButton()
                viewMethod.changeNextToCreate(shouldChange = false)
            }
            RegistrationPage.NFC -> {
                viewMethod.showPrevButton()
                viewMethod.changeNextToCreate(shouldChange = true)
            }
        }
    }
}