/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.ar

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.appmodule.home.HomeView
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import javax.inject.Inject

class ARController : DIBaseFragment(), HasARContract.Event {
    @field:[Inject] internal lateinit var presenter: HasARContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasARContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable
    override fun initialization(savedInstanceState: Bundle?) {
        HomeView.isOpenReward=true
    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription.dispose()
        HomeView.isOpenReward=false
    }

    override fun onResume() {
        super.onResume()
        viewMethod.onViewResume()
    }

    override fun onPause() {
        super.onPause()
        viewMethod.onViewPause()
    }

    override fun onCloseCameraEvent() {
        viewMethod.gotoRewardFragment()
    }
}