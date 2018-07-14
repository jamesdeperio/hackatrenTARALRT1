
package nyxdev.hackatren.taralrt1.global.widget.camera;

import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;

import java.util.ArrayList;

import nyxdev.hackatren.taralrt1.R;


public abstract class BarcodeFragment extends Fragment {
    private boolean shouldShowText;
    private boolean multipleScan;
    private boolean showDrawRect;
    private boolean touchAsCallback;
    private boolean shouldFocus;
    private boolean showFlash;
    private Integer[] rectColors;
    private int barcodeFormat;
    private int cameraFacing;
    private boolean barcodeFormatUpdate = false;
    protected BarcodeRetriever barcodeRetriever;

    public BarcodeFragment() {
    }

    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        TypedArray a = this.getContext().obtainStyledAttributes(attrs, R.styleable.gvb);
        this.showFlash = a.getBoolean(R.styleable.gvb_gvb_flash, false);
        this.showDrawRect = a.getBoolean(R.styleable.gvb_gvb_draw, false);
        this.shouldShowText = a.getBoolean(R.styleable.gvb_gvb_show_text, false);
        this.shouldFocus = a.getBoolean(R.styleable.gvb_gvb_auto_focus, false);
        this.touchAsCallback = a.getBoolean(R.styleable.gvb_gvb_touch, false);
        this.multipleScan = a.getBoolean(R.styleable.gvb_gvb_multiple, false);
        this.barcodeFormat = a.getInt(R.styleable.gvb_gvb_code_format, 0);
        this.cameraFacing = a.getInt(R.styleable.gvb_gvb_camera_facing, 0);
        int colors = a.getResourceId(R.styleable.gvb_gvb_rect_colors, R.array.rect_color);
        if (colors != 0) {
            TypedArray resourceArray = this.getResources().obtainTypedArray(colors);
            ArrayList<Integer> rectColorsList = new ArrayList();

            for(int i = 0; i < resourceArray.length(); ++i) {
                int resourceId = resourceArray.getResourceId(i, 0);
                rectColorsList.add(resourceId);
            }

            this.rectColors = (Integer[])rectColorsList.toArray(new Integer[rectColorsList.size()]);
            resourceArray.recycle();
        }

        a.recycle();
    }

    public int getBarcodeFormat() {
        return this.barcodeFormat;
    }

    public BarcodeFragment setBarcodeFormat(int barcodeFormat) {
        this.barcodeFormatUpdate = this.getBarcodeFormat() != barcodeFormat;
        this.barcodeFormat = barcodeFormat;
        return this;
    }

    public boolean isShouldShowText() {
        return this.shouldShowText;
    }

    public BarcodeFragment setShouldShowText(boolean shouldShowText) {
        this.shouldShowText = shouldShowText;
        return this;
    }

    public boolean isShowDrawRect() {
        return this.showDrawRect;
    }

    public BarcodeFragment setShowDrawRect(boolean showDrawRect) {
        this.showDrawRect = showDrawRect;
        return this;
    }

    public boolean isTouchAsCallback() {
        return this.touchAsCallback;
    }

    public BarcodeFragment setTouchAsCallback(boolean touchAsCallback) {
        this.touchAsCallback = touchAsCallback;
        return this;
    }

    public int getCameraFacing() {
        return this.cameraFacing;
    }

    public BarcodeFragment setCameraFacing(int cameraFacing) {
        this.cameraFacing = cameraFacing;
        return this;
    }

    public boolean isAutoFocus() {
        return this.shouldFocus;
    }

    public BarcodeFragment shouldAutoFocus(boolean shouldFocus) {
        this.shouldFocus = shouldFocus;
        return this;
    }

    public boolean isShowFlash() {
        return this.showFlash;
    }

    public BarcodeFragment setShowFlash(boolean showFlash) {
        this.showFlash = showFlash;
        return this;
    }

    public boolean supportMultipleScan() {
        return this.multipleScan;
    }

    public BarcodeFragment setSupportMultipleScan(boolean multipleScan) {
        this.multipleScan = multipleScan;
        return this;
    }

    public Integer[] getRectColors() {
        return this.rectColors;
    }

    public BarcodeFragment setRectColors(Integer[] rectColors) {
        this.rectColors = rectColors;
        return this;
    }

    public void setRetrieval(BarcodeRetriever retrieval) {
        this.barcodeRetriever = retrieval;
    }

    protected BarcodeFragment setBarcodeFormatUpdate(boolean barcodeFormatUpdate) {
        this.barcodeFormatUpdate = barcodeFormatUpdate;
        return this;
    }

    public boolean isBarcodeFormatUpdate() {
        return this.barcodeFormatUpdate;
    }

    public void stopScanning() {
    }

    public abstract Camera retrieveCamera();
}
