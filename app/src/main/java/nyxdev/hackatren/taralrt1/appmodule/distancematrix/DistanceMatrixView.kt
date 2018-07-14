/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.distancematrix

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.rey.material.widget.Button
import com.roughike.swipeselector.SwipeItem
import com.roughike.swipeselector.SwipeSelector
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.util.Constant

class DistanceMatrixView(
        view: View,
        event: HasDistanceMatrixContract.Event,
        context: Context) {
    var currentStation="1039 Epifanio de los Santos Ave, Bago Bantay, Quezon City, 1105 Metro Manila"
    var destinationStation="Grace Park West, Caloocan, Metro Manila"
    val currentStationSelector = view.findViewById<SwipeSelector>(R.id.currentStationSelector)!!.apply {
        this.setItems(
                SwipeItem(0, "Roosevelt LRT", "1039 Epifanio de los Santos Ave, Bago Bantay, Quezon City, 1105 Metro Manila"),
                SwipeItem(1, "Balintawak LRT", "Balintawak,Quezon City, Metro Manila"),
                SwipeItem(2, "Monumento  LRT", "Grace Park West, Caloocan, Metro Manila")
        )
        this.setOnItemSelectedListener {
            currentStation=it.description
        }
    }
    val destinationStationSelector = view.findViewById<SwipeSelector>(R.id.destinationStationSelector)!!.apply {
        this.setItems(
                SwipeItem(0, "Roosevelt LRT", "1039 Epifanio de los Santos Ave, Bago Bantay, Quezon City, 1105 Metro Manila"),
                SwipeItem(1, "Balintawak LRT", "Balintawak,Quezon City, Metro Manila"),
                SwipeItem(2, "Monumento  LRT", "Grace Park West, Caloocan, Metro Manila")
        )
        this.selectItemAt(2)
        this.setOnItemSelectedListener {
            destinationStation=it.description
        }
    }

    val btnCheck = view.findViewById<Button>(R.id.btnCheck)!!.apply {
        this.setOnClickListener { event.onCheckETAEvent(currentStation,destinationStation) }
    }
    val tvFare = view.findViewById<TextView>(R.id.tvFare)!!
    val tvDistance = view.findViewById<TextView>(R.id.tvDistance)!!
    val tvTime = view.findViewById<TextView>(R.id.tvTime)!!
    val loadingDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE).apply {
        this.progressHelper.barColor = Color.parseColor(Constant.LOADER_COLOR)
        this.titleText = context.getString(R.string.fetching)
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
    }
    val errorDialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).apply {
        this.titleText = context.getString(R.string.OOPS)
        this.setConfirmClickListener { dismiss() }
        this.setCanceledOnTouchOutside(false)
    }

}