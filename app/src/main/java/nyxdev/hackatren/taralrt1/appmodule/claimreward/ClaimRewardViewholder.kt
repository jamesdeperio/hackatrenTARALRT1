package nyxdev.hackatren.taralrt1.appmodule.claimreward

import android.content.Context
import android.view.View
import android.widget.TextView
import jdp.pocketlib.base.PocketViewHolder
import nyxdev.hackatren.taralrt1.R

class ClaimRewardViewholder(private val context:Context, private val adapter: ClaimRewardAdapter) : PocketViewHolder() {
    override fun onCreateViewHolder(view: View, position: Int) {
        val  tvReward=view.findViewById<TextView>(R.id.tvReward)!!
        tvReward.text=adapter.rewardList[position]
    }
}

