/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.monitoring

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import jdp.pocketlib.layoutmanager.PocketLinearLayout
import nyxdev.hackatren.taralrt1.R

class MonitoringView(
        view: View,
        event: HasMonitoringContract.Event,
        adapter: MonitoringAdapter) {
    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)!!.apply {
        this.adapter = adapter
        this.itemAnimator =null
        this.layoutManager = PocketLinearLayout(context = context,horizontal = LinearLayout.VERTICAL,b = false)
    }
}