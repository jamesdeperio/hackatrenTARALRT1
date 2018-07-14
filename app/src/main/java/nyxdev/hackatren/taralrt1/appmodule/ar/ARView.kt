/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.ar

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.rey.material.widget.FloatingActionButton
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.module.ar.GLView
import nyxdev.hackatren.taralrt1.integration.dao.query.Query
import nyxdev.hackatren.taralrt1.integration.network.RestRepository

class ARView(
        view: View,
        event: HasARContract.Event,
        context: Context,
        restRespository: RestRepository,
        selectQuery: Query.Select) {
     val  glView= GLView(context,restRespository,selectQuery)
    val  preview = view.findViewById<FrameLayout>(R.id.preview).apply {
        this.addView(glView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }
    val btnClose= view.findViewById<FloatingActionButton>(R.id.btnClose)!!.apply {
        this.setOnClickListener { event.onCloseCameraEvent() }
    }
}