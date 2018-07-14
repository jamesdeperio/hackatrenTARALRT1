package nyxdev.hackatren.taralrt1.appmodule.dashboard

import jdp.pocketlib.base.PocketAdapter
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import nyxdev.hackatren.taralrt1.global.model.obj.rss.Item

class DashboardAdapter : PocketAdapter() ,HasDashboardContract.Adapter {
    var announcementList : MutableList<Item> = ArrayList()
    override fun getItemCount(): Int = announcementList.size

    override fun notifyLastInserted(): Job = launch(UI) {
        notifyItemInserted(itemCount)
    }
    override fun refreshAll():Job = launch(UI) {
        notifyDataSetChanged()
    }
}
