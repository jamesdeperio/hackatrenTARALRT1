/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.crowdvolume

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import nyxdev.hackatren.taralrt1.R

class CrowdVolumeView(
        view: View,
        event: HasCrowdVolumeContract.Event) {
    val viewPager = view.findViewById<ViewPager>(R.id.viewPager)!!.apply {
        this.addOnPageChangeListener(event)
    }
    val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)!!
}