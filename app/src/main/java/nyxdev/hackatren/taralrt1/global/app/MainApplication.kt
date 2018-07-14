/**
 * @author github.com/jamesdeperio
 * @version codepocket template builder v1.0
 */

package nyxdev.hackatren.taralrt1.global.app

import android.content.Context
import android.support.multidex.MultiDex
import android.util.Log
import com.onesignal.OneSignal
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerAppComponent.builder().application(this).build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    override fun onCreate() {
        super.onCreate()
       OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init()
        val a=OneSignal.getPermissionSubscriptionState()
        Log.e("responses", a.subscriptionStatus.userId)
     }
}
