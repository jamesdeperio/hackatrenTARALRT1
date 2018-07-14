/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.registration

import android.content.Context
import android.graphics.Color
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.rey.material.widget.Button
import com.rey.material.widget.ImageButton
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.util.Constant

class RegistrationView(
        view: View,
        event: HasRegistrationContract.Event,
        context: Context) {
    val viewPager = view.findViewById<ViewPager>(R.id.viewPager)!!.apply {
        this.addOnPageChangeListener(event)
    }
    val btnBack = view.findViewById<ImageButton>(R.id.btnBack)!!.apply {
        this.setOnClickListener { event.onBackPressedEvent() }
    }
    val btnPrev = view.findViewById<Button>(R.id.btnPrev)!!.apply {
        this.visibility=View.GONE
        this.setOnClickListener { event.onPrevEvent() }
    }
    val btnNext = view.findViewById<Button>(R.id.btnNext)!!.apply {
        this.setOnClickListener { event.onNextEvent() }
    }
    val tvTitle= view.findViewById<TextView>(R.id.tvTitle)!!.apply {
        this.text= context.getString(R.string.registration)
    }

    val loadingDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE).apply {
        this.progressHelper.barColor = Color.parseColor(Constant.LOADER_COLOR)
        this.titleText = context.getString(R.string.creating_account)
        this.setCancelable(false)
        this.setCanceledOnTouchOutside(false)
    }
    val errorDialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).apply {
        this.titleText = context.getString(R.string.OOPS)
        this.setConfirmClickListener { dismiss() }
        this.setCanceledOnTouchOutside(false)
    }

    val successDialog= SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).apply {
        this.setConfirmClickListener { event.onConfirmSuccessCreatingEvent(dialog=this) }
        this.titleText = context.getString(R.string.success)
        this.setCanceledOnTouchOutside(false)
        this.setCancelClickListener { dismiss() }
    }
}