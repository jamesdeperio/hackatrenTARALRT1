//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nyxdev.hackatren.taralrt1.global.widget.camera;

import com.google.android.gms.vision.MultiProcessor.Factory;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

abstract class BarcodeTrackerFactory implements Factory<Barcode> {
    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;

    BarcodeTrackerFactory(GraphicOverlay<BarcodeGraphic> barcodeGraphicOverlay) {
        this.mGraphicOverlay = barcodeGraphicOverlay;
    }

    public Tracker<Barcode> create(Barcode barcode) {
        this.onCodeDetected(barcode);
        BarcodeGraphic graphic = new BarcodeGraphic(this.mGraphicOverlay);
        return new BarcodeGraphicTracker(this.mGraphicOverlay, graphic);
    }

    abstract void onCodeDetected(Barcode var1);
}
