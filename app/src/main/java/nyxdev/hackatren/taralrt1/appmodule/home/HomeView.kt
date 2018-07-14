/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.home

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rey.material.widget.Button
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.app.GlideApp
import nyxdev.hackatren.taralrt1.global.util.BottomNavigation
import nyxdev.hackatren.taralrt1.global.widget.AppBarStateChangeListener
import nyxdev.hackatren.taralrt1.global.widget.ScrollingImageView

class HomeView(
        view: View,
        event: HasHomeContract.Event,
        context: Context) {
    companion object Properties{
        var isOpenDashboard=false
        var isOpenVolume=false
        var isOpenReward=false
    }
    val appBarLayout = view.findViewById<AppBarLayout>(R.id.appBarLayout)!!.apply {
        this.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                event.onAppbarStateChanged(state!!)
            }
        })
    }
    val scrollingBg = view.findViewById<ScrollingImageView>(R.id.scrollingBg)!!
    val adsBanner = view.findViewById<ImageView>(R.id.adsBanner)!!.apply {
        this.scaleType = ImageView.ScaleType.FIT_XY
        GlideApp.with(context)
                .asGif()
                .load(R.drawable.ads)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(this)
    }
    val btnDashboard = view.findViewById<Button>(R.id.btnDashboard)!!.apply {
        this.setOnClickListener { event.onBottomNavigationChangedEvent(BottomNavigation.DASHBOARD) }
    }
    val btnCrowdVolume = view.findViewById<Button>(R.id.btnCrowdVolume)!!.apply {
        this.setOnClickListener { event.onBottomNavigationChangedEvent(BottomNavigation.CROWD) }
    }
    val btnReward = view.findViewById<Button>(R.id.btnReward)!!.apply {
        this.setOnClickListener { event.onBottomNavigationChangedEvent(BottomNavigation.REWARD) }
    }
}
