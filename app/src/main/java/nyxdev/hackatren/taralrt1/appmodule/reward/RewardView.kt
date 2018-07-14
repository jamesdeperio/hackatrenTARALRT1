/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.reward

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.LinearLayout
import jdp.pocketlib.layoutmanager.PocketLinearLayout
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import nyxdev.hackatren.taralrt1.R

class RewardView(
        view: View,
        adapter: RewardAdapter) {
    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)!!.apply {
        val alphaAdapter = ScaleInAnimationAdapter(adapter)
        alphaAdapter.setInterpolator(OvershootInterpolator())
        alphaAdapter.setFirstOnly(false)
        alphaAdapter.setDuration(800)
        this.adapter = alphaAdapter
        this.layoutManager = PocketLinearLayout(context = context,horizontal = LinearLayout.VERTICAL,b = false)
    }
}