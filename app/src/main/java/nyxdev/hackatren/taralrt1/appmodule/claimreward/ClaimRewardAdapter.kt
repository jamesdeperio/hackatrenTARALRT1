package nyxdev.hackatren.taralrt1.appmodule.claimreward

import jdp.pocketlib.base.PocketAdapter
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ClaimRewardAdapter : PocketAdapter() ,HasClaimRewardContract.Adapter{
    var rewardList : MutableList<String> = ArrayList()
    override fun getItemCount(): Int = rewardList.size
    override fun notifyLastInserted(): Job = launch(UI) {
        notifyItemInserted(itemCount)
    }
}
