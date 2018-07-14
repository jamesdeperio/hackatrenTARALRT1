/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.crowdvolume

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.appmodule.home.HomeView
import nyxdev.hackatren.taralrt1.global.base.DIBaseFragment
import nyxdev.hackatren.taralrt1.global.util.CrowdVolumeTab
import javax.inject.Inject

class CrowdVolumeController : DIBaseFragment(), HasCrowdVolumeContract.Event {
    @field:[Inject] internal lateinit var presenter: HasCrowdVolumeContract.Presenter
    @field:[Inject] internal lateinit var viewMethod: HasCrowdVolumeContract.ViewMethod
    @field:[Inject] internal lateinit var subscription: CompositeDisposable

    override fun initialization(savedInstanceState: Bundle?) {
        HomeView.isOpenVolume=true
    }

    override fun onLoadEvent(savedInstanceState: Bundle?) {
        viewMethod.buildViewPager()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        HomeView.isOpenVolume=false
        subscription.dispose()
    }

    override fun onPageSelected(position: Int) {
        if (position==CrowdVolumeTab.MAP)
            viewMethod.collapseAppbar(activity!!.findViewById(R.id.appBarLayout)!!)
    }
}