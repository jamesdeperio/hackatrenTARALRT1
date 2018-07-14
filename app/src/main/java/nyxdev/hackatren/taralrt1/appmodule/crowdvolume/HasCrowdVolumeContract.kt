/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.crowdvolume

import android.support.design.widget.AppBarLayout
import android.support.v4.view.ViewPager
import kotlinx.coroutines.experimental.Job

interface HasCrowdVolumeContract {
    interface Event : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    }

    interface ViewMethod {
        fun buildViewPager():Job
        fun collapseAppbar(appbar: AppBarLayout):Job
    }

    interface Presenter

}