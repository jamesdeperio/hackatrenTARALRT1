//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nyxdev.hackatren.taralrt1.global.widget.camera;

import com.google.android.gms.vision.barcode.Barcode;

public interface BarcodeRetriever {
    void onRetrieved(Barcode var1);
    void onRetrievedFailed(String var1);
}
