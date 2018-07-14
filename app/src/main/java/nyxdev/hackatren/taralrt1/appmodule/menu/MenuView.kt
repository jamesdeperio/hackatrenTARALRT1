/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.menu

import android.content.Context
import android.view.View
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.android.gms.common.SignInButton
import com.rey.material.widget.Button
import nyxdev.hackatren.taralrt1.R

class MenuView(
        view: View,
        event: HasMenuContract.Event,
        context: Context) {
    companion object Properties {
        var isOpenSignIn=false
        var isOpenRegistration=false
    }
    val btnSignInWithGoogle = view.findViewById<SignInButton>(R.id.btnSignInWithGoogle)!!.apply {
        this.setOnClickListener {
       //     if (!MenuView.isOpenSignIn && !MenuView.isOpenSignInWithFB && !MenuView.isOpenSignInWithGoogle && !MenuView.isOpenRegistration)
            event.signInWithGoogleEvent() }
        this.setSize(SignInButton.SIZE_WIDE)
    }
    val btnSignIn = view.findViewById<Button>(R.id.btnSignIn)!!.apply {
        this.setOnClickListener {
            if (!MenuView.isOpenSignIn &&  !MenuView.isOpenRegistration)
                event.signInEvent()
        }
    }
    val btnRegister = view.findViewById<Button>(R.id.btnRegister)!!.apply {
        this.setOnClickListener {
            if (!MenuView.isOpenSignIn && !MenuView.isOpenRegistration)
                event.registerEvent() }
    }

    val errorDialog = SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).apply {
        this.titleText = context.getString(R.string.OOPS)
        this.setConfirmClickListener { dismiss() }
        this.setCanceledOnTouchOutside(false)
    }
}