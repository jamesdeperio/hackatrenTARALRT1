/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.menu

import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import nyxdev.hackatren.taralrt1.global.model.event.GoogleAuth
import nyxdev.hackatren.taralrt1.global.util.AuthType
import nyxdev.hackatren.taralrt1.integration.bus.MenuBus
import javax.inject.Inject

class MenuController : DIBaseFragment(), HasMenuContract.Event {
    @field:[Inject] internal lateinit var presenter: HasMenuContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasMenuContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable
    @field:[Inject] internal lateinit var googleSignInClient: GoogleSignInClient
    @field:[Inject] internal lateinit var menuBus: MenuBus

    override fun initialization(savedInstanceState: Bundle?) {
        menuBus.subscribeReceiver(onMenuSignInReceiverEvent())
    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        menuBus.unSubscribeReceiver()
        subscription.dispose()
    }
    override fun signInWithGoogleEvent() {
        startActivityForResult(googleSignInClient.signInIntent, AuthType.GOOGLE)
    }

    override fun onMenuSignInReceiverEvent(): Consumer<in Any> = Consumer {
        if (it is GoogleAuth) presenter.loginWithGoogle(taskGoogleSignInAccount=it.task!!)
    }
    override fun signInWithFacebookEvent() {
    }

    override fun signInEvent() {
       MenuView.isOpenSignIn=true
        viewMethod.gotoLoginFragment()
    }

    override fun registerEvent() {
        MenuView.isOpenRegistration=true
        viewMethod.gotoRegistrationFragment()
    }
}