/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.crowdvolume

import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import jdp.pocketlib.builder.PageBuilder
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.appmodule.distancematrix.DistanceMatrixController
import nyxdev.hackatren.taralrt1.appmodule.mappage.MapPageController
import nyxdev.hackatren.taralrt1.appmodule.monitoring.MonitoringController

class CrowdVolumeViewImpl(
        private val fragment: Fragment,
        private val view: CrowdVolumeView
) : HasCrowdVolumeContract.ViewMethod {
    override fun collapseAppbar(appbar: AppBarLayout): Job = launch(UI){
        appbar.setExpanded(false,true)
    }

    override fun buildViewPager(): Job = launch(UI){
        PageBuilder.build {
            this.setViewPager(viewPager = view.viewPager)
            this.setTabLayout(tabLayout = view.tabLayout)
            this.setFragmentManager(fragmentManager= fragment.childFragmentManager)
            this.setPageTransformer(com.eftimoff.viewpagertransformers.TabletTransformer())
            this.addPage("Train Map", MapPageController())
            this.addPage("Station Monitoring", MonitoringController())
            this.addPage("Distance Matrix", DistanceMatrixController())
        }
    }
}