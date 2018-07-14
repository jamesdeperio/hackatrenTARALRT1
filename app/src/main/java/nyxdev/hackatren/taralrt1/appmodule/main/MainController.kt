/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.main

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.global.app.MainExceptionHandler
import nyxdev.hackatren.taralrt1.global.base.DIBaseActivity
import nyxdev.hackatren.taralrt1.global.model.event.GoogleAuth
import nyxdev.hackatren.taralrt1.global.module.nfc.NfcManager
import nyxdev.hackatren.taralrt1.global.util.AuthType
import nyxdev.hackatren.taralrt1.integration.bus.MenuBus
import javax.inject.Inject


class MainController : DIBaseActivity(), HasMainContract.Event {
    @field:[Inject] internal lateinit var presenter: HasMainContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasMainContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable
    @field:[Inject] internal lateinit var menuBus: MenuBus
    @field:[Inject] internal lateinit var nfcManager: NfcManager.Cycle

    override fun initialization(savedInstanceState: Bundle?) {
        ActivityCompat.requestPermissions(this, arrayOf(
                Manifest.permission.NFC,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CAMERA)
                , 1234)
        Thread.setDefaultUncaughtExceptionHandler(MainExceptionHandler())
    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {
        viewMethod.gotoLoaderFragment()
    }

    override fun onNewIntent(intent: Intent?) {
        nfcManager.onNewIntent(intent!!)
        super.onNewIntent(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription.dispose()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            AuthType.GOOGLE -> menuBus.sendEvent(event = GoogleAuth(task =GoogleSignIn.getSignedInAccountFromIntent(data)))
            AuthType.FACEBOOK -> { }
        }
    }


}