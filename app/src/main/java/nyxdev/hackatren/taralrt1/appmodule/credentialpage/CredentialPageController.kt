/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.credentialpage

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.appmodule.menu.MenuView
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import nyxdev.hackatren.taralrt1.global.model.event.Credential
import nyxdev.hackatren.taralrt1.global.util.LoginPage
import nyxdev.hackatren.taralrt1.global.util.RegistrationPage
import nyxdev.hackatren.taralrt1.integration.bus.CredentialBus
import javax.inject.Inject

class CredentialPageController : DIBaseFragment(), HasCredentialPageContract.Event {
    @field:[Inject] internal lateinit var presenter: HasCredentialPageContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasCredentialPageContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable
    @field:[Inject] internal lateinit var credentialBus: CredentialBus

    override fun initialization(savedInstanceState: Bundle?) {

    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription.dispose()
    }
    override fun onTypingEmailDoneEvent() {
      credentialBus.sendEvent(event = Credential(type = if (MenuView.isOpenSignIn) LoginPage.Pasword else RegistrationPage.Credential,
              value1 = viewMethod.getEmail(), value2 = viewMethod.getPassword(),value3=viewMethod.getName()))
    }

    override fun onTypingPasswordDoneEvent() {
        credentialBus.sendEvent(event = Credential(type =  if (MenuView.isOpenSignIn) LoginPage.Pasword else RegistrationPage.Credential
                , value1 = viewMethod.getEmail(), value2 = viewMethod.getPassword(), value3 = viewMethod.getName()))
    }
}