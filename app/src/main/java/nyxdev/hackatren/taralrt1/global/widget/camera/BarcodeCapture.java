package nyxdev.hackatren.taralrt1.global.widget.camera;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.barcode.BarcodeDetector.Builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import nyxdev.hackatren.taralrt1.R;


public final class BarcodeCapture extends BarcodeFragment {
    private static final String TAG = "Barcode-reader";
    private static final int RC_HANDLE_GMS = 9001;
    private static final int RC_HANDLE_CAMERA_PERM = 2;
    public static final String BarcodeObject = "Barcode";
    private CameraSource mCameraSource;
    private CameraSourcePreview mPreview;
    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;
    private BarcodeDetector barcodeDetector;

    public BarcodeCapture() {
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.barcode_capture, container, false);
        this.mPreview = rootView.findViewById(R.id.preview);
        this.mGraphicOverlay = rootView.findViewById(R.id.graphicOverlay);
        this.mGraphicOverlay.setShowText(this.isShouldShowText());
        this.mGraphicOverlay.setRectColors(this.getRectColors());
        this.mGraphicOverlay.setDrawRect(this.isShowDrawRect());
        this.gestureDetector = new GestureDetector(this.getContext(), new BarcodeCapture.CaptureGestureListener());
        this.scaleGestureDetector = new ScaleGestureDetector(this.getContext(), new BarcodeCapture.ScaleListener());
        rootView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent e) {
                boolean b = BarcodeCapture.this.scaleGestureDetector.onTouchEvent(e);
                boolean c = BarcodeCapture.this.gestureDetector.onTouchEvent(e);
                return b || c || view.onTouchEvent(e);
            }
        });
        createCameraSource(this.isAutoFocus(), this.isShowFlash());
        return rootView;
    }



    @SuppressLint({"InlinedApi", "WrongConstant"})
    private void createCameraSource(boolean autoFocus, boolean useFlash) {
        this.barcodeDetector = (new Builder(this.getContext())).setBarcodeFormats(this.getBarcodeFormat()).build();
        BarcodeTrackerFactory barcodeFactory = new BarcodeTrackerFactory(this.mGraphicOverlay) {
            void onCodeDetected(Barcode barcode) {
                if (!BarcodeCapture.this.isTouchAsCallback() && !BarcodeCapture.this.supportMultipleScan()) {
                    BarcodeCapture.this.barcodeRetriever.onRetrieved(barcode);
                }

            }
        };
        this.barcodeDetector.setProcessor((new com.google.android.gms.vision.MultiProcessor.Builder(barcodeFactory)).build());
        if (!this.barcodeDetector.isOperational()) {
            Log.w("Barcode-reader", "Detector dependencies are not yet available.");
            IntentFilter lowstorageFilter = new IntentFilter("android.intent.action.DEVICE_STORAGE_LOW");
            boolean hasLowStorage = this.getActivity().registerReceiver(null, lowstorageFilter) != null;
            if (hasLowStorage) {
                Toast.makeText(this.getContext(), R.string.low_storage_error, Toast.LENGTH_LONG).show();
                Log.w("Barcode-reader", this.getString(R.string.low_storage_error));
            }
        }

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)this.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        CameraSource.Builder builder= new CameraSource.Builder(this.getContext(), this.barcodeDetector).setFacing(this.getCameraFacing()).setRequestedPreviewSize(height, width).setRequestedFps(15.0F);
        if (VERSION.SDK_INT >= 14) {
            builder = builder.setFocusMode(autoFocus ? "continuous-picture" : null);
        }

        this.mCameraSource = builder.setFlashMode(useFlash ? "torch" : null).build();
    }

    public void onResume() {
        super.onResume();
        this.startCameraSource();
    }

    @SuppressLint("WrongConstant")
    public void refresh() {
        this.mGraphicOverlay.setDrawRect(this.isShowDrawRect());
        this.mGraphicOverlay.setRectColors(this.getRectColors());
        this.mGraphicOverlay.setShowText(this.isShouldShowText());
        this.mCameraSource.setFocusMode(this.isAutoFocus() ? "continuous-picture" : null);
        this.mCameraSource.setFlashMode(this.isShowFlash() ? "torch" : "off");
        if (this.getCameraFacing() != this.mCameraSource.getCameraFacing() || this.isBarcodeFormatUpdate()) {
            this.setBarcodeFormatUpdate(false);
            this.mCameraSource.setCameraFacing(this.getCameraFacing());
            this.mCameraSource.stop();
            this.mCameraSource.release();
            this.createCameraSource(this.isAutoFocus(), this.isShowFlash());
            this.startCameraSource();
        }

    }

    public void onPause() {
        super.onPause();
        if (this.mPreview != null) {
            this.mPreview.stop();
        }

    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mPreview != null) {
            this.mPreview.release();
        }

    }



    public void pCameraSource(){
    }

    public void startCameraSource() throws SecurityException {
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.getContext());
        if (code != 0) {
            Dialog dlg = GoogleApiAvailability.getInstance().getErrorDialog(this.getActivity(), code, 9001);
            dlg.show();
        }

        if (this.mCameraSource != null) {
            try {
                this.mPreview.start(this.mCameraSource, this.mGraphicOverlay);
            } catch (IOException var3) {
                Log.e("Barcode-reader", "Unable to start camera source.", var3);
                this.mCameraSource.release();
                this.mCameraSource = null;
            }
        }

    }

    private boolean onTap(float rawX, float rawY) {
        int[] location = new int[2];
        this.mGraphicOverlay.getLocationOnScreen(location);
        float x = (rawX - (float)location[0]) / this.mGraphicOverlay.getWidthScaleFactor();
        float y = (rawY - (float)location[1]) / this.mGraphicOverlay.getHeightScaleFactor();
        Barcode best = null;
        float bestDistance = 3.4028235E38F;
        ArrayList<Barcode> allRetrieved = new ArrayList();
        Iterator var9 = this.mGraphicOverlay.getGraphics().iterator();

        while(var9.hasNext()) {
            BarcodeGraphic graphic = (BarcodeGraphic)var9.next();
            Barcode barcode = graphic.getBarcode();
            allRetrieved.add(barcode);
            if (barcode.getBoundingBox().contains((int)x, (int)y)) {
                best = barcode;
                break;
            }

            float dx = x - (float)barcode.getBoundingBox().centerX();
            float dy = y - (float)barcode.getBoundingBox().centerY();
            float distance = dx * dx + dy * dy;
            if (distance < bestDistance) {
                best = barcode;
                bestDistance = distance;
            }
        }

        if (best != null) {
            if (this.barcodeRetriever != null) {
                if (this.supportMultipleScan()) {
                    this.barcodeRetriever.onRetrieved(best);
                } else {
                    this.barcodeRetriever.onRetrieved(best);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void stopScanning() {
        super.stopScanning();
        this.barcodeDetector.release();
    }

    public Camera retrieveCamera() {
        return this.mCameraSource.getCamera();
    }

    private class ScaleListener implements OnScaleGestureListener {
        private ScaleListener() {
        }

        public boolean onScale(ScaleGestureDetector detector) {
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector detector) {
            BarcodeCapture.this.mCameraSource.doZoom(detector.getScaleFactor());
        }
    }

    private class CaptureGestureListener extends SimpleOnGestureListener {
        private CaptureGestureListener() {
        }

        public boolean onSingleTapConfirmed(MotionEvent e) {
            return BarcodeCapture.this.onTap(e.getRawX(), e.getRawY()) || super.onSingleTapConfirmed(e);
        }
    }
}
