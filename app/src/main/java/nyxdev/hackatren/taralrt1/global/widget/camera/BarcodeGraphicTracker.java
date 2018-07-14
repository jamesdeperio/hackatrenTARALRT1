//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nyxdev.hackatren.taralrt1.global.widget.camera;

import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

class BarcodeGraphicTracker extends Tracker<Barcode> {
    private GraphicOverlay<BarcodeGraphic> mOverlay;
    private BarcodeGraphic mGraphic;

    BarcodeGraphicTracker(GraphicOverlay<BarcodeGraphic> overlay, BarcodeGraphic graphic) {
        this.mOverlay = overlay;
        this.mGraphic = graphic;
    }

    public void onNewItem(int id, Barcode item) {
        this.mGraphic.setId(id);
    }

    public void onUpdate(Detections<Barcode> detectionResults, Barcode item) {
        this.mOverlay.add(this.mGraphic);
        this.mGraphic.updateItem(item);
    }

    public void onMissing(Detections<Barcode> detectionResults) {
        this.mOverlay.remove(this.mGraphic);
    }

    public void onDone() {
        this.mOverlay.remove(this.mGraphic);
    }
}
