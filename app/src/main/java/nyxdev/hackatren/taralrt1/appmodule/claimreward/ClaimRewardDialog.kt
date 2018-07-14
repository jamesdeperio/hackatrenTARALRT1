package nyxdev.hackatren.taralrt1.appmodule.claimreward

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.rey.material.app.Dialog
import io.reactivex.disposables.CompositeDisposable
import jdp.pocketlib.layoutmanager.PocketLinearLayout
import nyxdev.hackatren.taralrt1.R

class ClaimRewardDialog(context: Context) : Dialog(context),HasClaimRewardContract.Event {
    private val subscription= CompositeDisposable()

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = layoutInflater.inflate(R.layout.dialog_claim_reward, null)
        val adapter = ClaimRewardAdapter()
        val viewholder = ClaimRewardViewholder(adapter = adapter,context = context!!)
        viewholder.setContentView(R.layout.item_list_rewards)
        adapter.addViewHolder(viewholder)

        val recyclerView=layout.findViewById<RecyclerView>(R.id.recyclerView)!!.apply {
            this.adapter=adapter
            this.layoutManager= PocketLinearLayout(context = context,horizontal = LinearLayout.VERTICAL,b = false)
        }
        setContentView(layout)
        val presenter:HasClaimRewardContract.Presenter = ClaimRewardImpl(adapter)
        subscription.addAll(presenter.loadLoadRewards())
    }

    override fun onStop() {
        super.onStop()
        subscription.dispose()
    }

}