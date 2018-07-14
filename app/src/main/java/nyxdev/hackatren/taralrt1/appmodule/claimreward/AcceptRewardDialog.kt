package nyxdev.hackatren.taralrt1.appmodule.claimreward

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.rey.material.app.Dialog
import com.rey.material.widget.Button
import io.reactivex.schedulers.Schedulers
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.integration.dao.query.Query
import nyxdev.hackatren.taralrt1.integration.network.RestRepository

class AcceptRewardDialog(context: Context?, private val restRespository: RestRepository, private val selectQuery: Query.Select) : Dialog(context),HasClaimRewardContract.Event {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_earn_points)
        val btnAccept= findViewById<Button>(R.id.btnAccept)!!
        btnAccept.setOnClickListener {
            restRespository.savePointsRequest(data = selectQuery.getProfile().accountID+"`scan`1")
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.newThread())
                    .doOnNext {
                        Log.e("response","${it.string()}")
                    }
                    .subscribe()
            dismiss()
        }
        }


}