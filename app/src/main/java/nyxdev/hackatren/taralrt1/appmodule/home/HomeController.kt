/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.home

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import nyxdev.hackatren.taralrt1.global.util.BottomNavigation
import nyxdev.hackatren.taralrt1.global.widget.AppBarStateChangeListener
import javax.inject.Inject

class HomeController : DIBaseFragment(), HasHomeContract.Event {
    @field:[Inject] internal lateinit var presenter: HasHomeContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasHomeContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable

    override fun initialization(savedInstanceState: Bundle?) {
        viewMethod.enabledDashBoard()
    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {
        viewMethod.gotoDashboardFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        subscription.dispose()
    }

    override fun onAppbarStateChanged(state: AppBarStateChangeListener.State) {
        if (state == AppBarStateChangeListener.State.COLLAPSED) viewMethod.stopTrainAnimation()
        else if (state == AppBarStateChangeListener.State.EXPANDED) viewMethod.startTrainAnimation()
    }

    override fun onBottomNavigationChangedEvent(type: Int) {
        when(type) {
            BottomNavigation.DASHBOARD -> {
                if (!HomeView.isOpenDashboard){
                    viewMethod.enabledDashBoard()
                    viewMethod.gotoDashboardFragment()
                }
            }
            BottomNavigation.CROWD -> {
                if (!HomeView.isOpenVolume){
                    viewMethod.enabledCrowd()
                    viewMethod.gotoCrowdVolumeFragment()
                }
            }
            BottomNavigation.REWARD -> {
                if (!HomeView.isOpenReward){
                    viewMethod.enabledReward()
                    viewMethod.gotoRewardFragment()
                }
            }
        }
    }
}