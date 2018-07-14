package nyxdev.hackatren.taralrt1.appmodule.monitoring

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import jdp.pocketlib.base.PocketViewHolder
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.app.GlideApp

class MonitorViewholder(private val context:Context, private val adapter: MonitoringAdapter) : PocketViewHolder() {
    @SuppressLint("SetTextI18n")
    override fun onCreateViewHolder(view: View, position: Int) {
        val tvStation= view.findViewById<TextView>(R.id.tvStation)!!
        tvStation.text=adapter.stationList[position].fldStationName
        val imgTraffic= view.findViewById<ImageView>(R.id.imgTraffic)!!

        val tvVolume= view.findViewById<TextView>(R.id.tvVolume)!!
        if (adapter.stationList[position].totalPassenger==null) {
            tvVolume.text="Estimated Crowd: No passenger"
            GlideApp.with(context)
                    .load(R.drawable.light)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(imgTraffic)
        }else {
            tvVolume.text="Estimated Crowd: ${adapter.stationList[position].totalPassenger.toString().toFloat().toInt()} people"
            if (adapter.stationList[position].lite!!.toLowerCase()=="yes")
                GlideApp.with(context)
                        .load(R.drawable.light)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(imgTraffic)
            else if (adapter.stationList[position].moderate!!.toLowerCase()=="yes")
                GlideApp.with(context)
                        .load(R.drawable.moderate)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(imgTraffic)
            else if (adapter.stationList[position].heavy!!.toLowerCase()=="yes")
                GlideApp.with(context)
                        .load(R.drawable.heavy)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(imgTraffic)

        }
    }
}

