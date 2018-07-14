package nyxdev.hackatren.taralrt1.appmodule.dashboard

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import jdp.pocketlib.base.PocketViewHolder
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.app.GlideApp

class DashboardViewholder(private val context:Context, private val adapter: DashboardAdapter) : PocketViewHolder() {
    override fun onCreateViewHolder(view: View, position: Int) {
        val tvTitle=view.findViewById<TextView>(R.id.tvTitle)!!
        val tvPubDate=view.findViewById<TextView>(R.id.tvPubDate)!!
        val tvDesc=view.findViewById<TextView>(R.id.tvDesc)!!
        val imgPhoto=view.findViewById<ImageView>(R.id.imgPhoto)!!
        if (adapter.announcementList[position].image!=null){
            GlideApp.with(context)
                    .load(adapter.announcementList[position].image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .centerCrop()
                    .thumbnail(0.8f)
                    .into(imgPhoto)
            imgPhoto.visibility=View.VISIBLE
        }
        tvTitle.text=adapter.announcementList[position].title
        tvPubDate.text=adapter.announcementList[position].pubDate
        tvDesc.text=adapter.announcementList[position].content

    }
}

