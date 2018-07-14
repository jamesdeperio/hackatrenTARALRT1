package nyxdev.hackatren.taralrt1.appmodule.reward

import android.content.Context
import android.view.View
import android.widget.TextView
import com.rey.material.widget.Button
import jdp.pocketlib.base.PocketViewHolder
import nyxdev.hackatren.taralrt1.R

class RewardViewholderOption(
        private val context: Context,
        private val adapter: RewardAdapter,
        private val event: HasRewardContract.Event) : PocketViewHolder() {
    override fun onCreateViewHolder(view: View, position: Int) {
        val btnEarnPoints= view.findViewById<Button>(R.id.btnEarnPoints)!!
        btnEarnPoints.setOnClickListener { event.onEarnPointsEvent() }
        val btnRedeem= view.findViewById<Button>(R.id.btnRedeem)!!
        btnRedeem.setOnClickListener { event.onRedeemEvent() }

        val tvAccountID=view.findViewById<TextView>(R.id.tvAccountID)!!
        tvAccountID.text=adapter.rewardList[position].accountID

        val tvPoints=view.findViewById<TextView      >(R.id.tvPoints)!!
        tvPoints.text=adapter.rewardList[position].points
    }

}

