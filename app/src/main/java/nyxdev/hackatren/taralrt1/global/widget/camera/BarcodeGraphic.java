//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nyxdev.hackatren.taralrt1.global.widget.camera;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeGraphic extends GraphicOverlay.Graphic {
    private int mId;
    private static int mCurrentColorIndex = 0;
    private Paint mRectPaint;
    private Paint mTextPaint;
    private volatile Barcode mBarcode;
    private GraphicOverlay graphicOverlay;

    BarcodeGraphic(GraphicOverlay overlay) {
        super(overlay);
        this.graphicOverlay = overlay;
        mCurrentColorIndex = (mCurrentColorIndex + 1) % overlay.getRectColors().length;
        int selectedColor = ContextCompat.getColor(overlay.getContext(), overlay.getRectColors()[mCurrentColorIndex]);
        this.mRectPaint = new Paint();
        this.mRectPaint.setColor(selectedColor);
        this.mRectPaint.setStyle(Style.STROKE);
        this.mRectPaint.setStrokeWidth(4.0F);
        this.mTextPaint = new Paint();
        this.mTextPaint.setColor(selectedColor);
        this.mTextPaint.setTextSize(36.0F);
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Barcode getBarcode() {
        return this.mBarcode;
    }

    void updateItem(Barcode barcode) {
        this.mBarcode = barcode;
        this.postInvalidate();
    }

    public void draw(Canvas canvas) {
        Barcode barcode = this.mBarcode;
        if (barcode != null) {
            RectF rect = new RectF(barcode.getBoundingBox());
            rect.left = this.translateX(rect.left);
            rect.top = this.translateY(rect.top);
            rect.right = this.translateX(rect.right);
            rect.bottom = this.translateY(rect.bottom);
            if (this.graphicOverlay.isDrawRect()) {
                canvas.drawRect(rect, this.mRectPaint);
            }

            if (this.graphicOverlay.isShowText()) {
                canvas.drawText(barcode.rawValue, rect.left, rect.bottom, this.mTextPaint);
            }

        }
    }
}
