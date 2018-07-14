/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.login

import android.os.Bundle
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import nyxdev.hackatren.taralrt1.appmodule.menu.MenuView
import nyxdev.hackatren.taralrt1.global.base.DIBaseSwipeFragment
import nyxdev.hackatren.taralrt1.global.model.event.Credential
import nyxdev.hackatren.taralrt1.global.util.Constant
import nyxdev.hackatren.taralrt1.global.util.LoginPage
import nyxdev.hackatren.taralrt1.integration.bus.CredentialBus
import nyxdev.hackatren.taralrt1.integration.dao.table.AccountEntity
import javax.inject.Inject

class LoginController : DIBaseSwipeFragment(), HasLoginContract.Event {
    @field:[Inject] internal lateinit var presenter: HasLoginContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasLoginContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable
    @field:[Inject] internal lateinit var credentialBus: CredentialBus
    private var accountEntity: AccountEntity = AccountEntity()

    override fun initialization(savedInstanceState: Bundle?) {
        credentialBus.subscribeReceiver(onCredentialReceivedEvent())

    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {
        viewMethod.buildViewPager()
    }

    override fun onCredentialReceivedEvent(): Consumer<in Any> = Consumer {
        Log.e("credential","$it")
        if (it is Credential && it.type== LoginPage.Pasword) {
            accountEntity.password=it.value2!!
        }  else if (it is Credential && it.type== LoginPage.NFC)
            accountEntity.nfc=it.value1!!
    }


    override fun onDestroyView() {
        super.onDestroyView()
        MenuView.isOpenSignIn=false
        subscription.dispose()
        credentialBus.unSubscribeReceiver()
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
        }else subscription.addAll(presenter.loginAccount(accountEntity))
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            LoginPage.NFC -> {
                viewMethod.hidePrevButton()
                viewMethod.changeNextToCreate(shouldChange = false)
            }
            LoginPage.Pasword -> {
                viewMethod.showPrevButton()
                viewMethod.changeNextToCreate(shouldChange = true)
            }
        }
    }
}