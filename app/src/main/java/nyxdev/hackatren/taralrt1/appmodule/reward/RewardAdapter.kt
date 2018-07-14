package nyxdev.hackatren.taralrt1.appmodule.reward

import jdp.pocketlib.base.PocketAdapter
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.global.model.obj.reward.Reward

class RewardAdapter : PocketAdapter() ,HasRewardContract.Adapter {
    var rewardList : MutableList<Reward> = ArrayList()
    override fun getItemCount(): Int = rewardList.size

    override fun viewTypeCondition(position: Int): Int = rewardList[position].type

    override fun notifyLastInserted(): Job = launch(UI) {
        notifyItemInserted(itemCount)
    }
    override fun refreshAll():Job = launch(UI) {
        notifyDataSetChanged()
    }
}
