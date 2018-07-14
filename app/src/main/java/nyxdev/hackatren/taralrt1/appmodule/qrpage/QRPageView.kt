/**
 * @version codepocket template builder v1.0
 * @author github.com/jamesdeperio
 **/
package nyxdev.hackatren.taralrt1.appmodule.qrpage

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.vision.barcode.Barcode
import com.rey.material.widget.Button
import com.rey.material.widget.FloatingActionButton
import nyxdev.hackatren.taralrt1.R
import nyxdev.hackatren.taralrt1.global.widget.camera.BarcodeCapture

class QRPageView(
        view: View,
        event: HasQRPageContract.Event) {
    val barcode = BarcodeCapture().apply {
        this.setRetrieval(event)
        this.isShowDrawRect=true
        this.barcodeFormat= Barcode.QR_CODE
        this.isShouldShowText=false
        this.setSupportMultipleScan(false)
        this.shouldAutoFocus(true)
        this.isTouchAsCallback=false
        this.rectColors= arrayOf(R.color.color_blue)
    }
    val cameraLayout= view.findViewById<FrameLayout>(R.id.cameraLayout)!!.apply {
        this.visibility=View.GONE
    }
    val lottieCamera= view.findViewById<LottieAnimationView>(R.id.lottieCamera)!!
    val scannerBoundLayout= view.findViewById<FrameLayout>(R.id.scanner)!!
    val btnScan= view.findViewById<Button>(R.id.btnScan)!!.apply {
        this.setOnClickListener { event.onQRCameraEvent() }
    }
    val btnClose= view.findViewById<FloatingActionButton>(R.id.btnClose)!!.apply {
        this.setOnClickListener { event.onQRCameraCloseEvent() }
    }
    val tvStatus= view.findViewById<TextView>(R.id.tvStatus)!!
}