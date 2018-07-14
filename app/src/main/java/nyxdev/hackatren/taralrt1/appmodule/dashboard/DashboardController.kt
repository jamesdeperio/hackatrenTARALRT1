/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.dashboard

import android.os.Bundle
import android.os.StrictMode
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.appmodule.home.HomeView
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import javax.inject.Inject

class DashboardController : DIBaseFragment(), HasDashboardContract.Event {
    @field:[Inject] internal lateinit var presenter: HasDashboardContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasDashboardContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable

    override fun initialization(savedInstanceState: Bundle?) {
        HomeView.isOpenDashboard=true
    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {
        try {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
            subscription.add(presenter.loadPoints())
            subscription.add(presenter.loadAnnouncement())
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription.dispose()
        HomeView.isOpenDashboard=false
    }
}