package nyxdev.hackatren.taralrt1.appmodule.claimreward

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import jdp.pocketlib.layoutmanager.PocketLinearLayout
import nyxdev.hackatren.taralrt1.R

class ClaimRewardView(
        view: View,
        adapter: ClaimRewardAdapter,
        context: Context) {
    val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView)!!.apply {
        this.adapter=adapter
        this.layoutManager= PocketLinearLayout(context = context,horizontal = LinearLayout.VERTICAL,b = false)
    }
}
