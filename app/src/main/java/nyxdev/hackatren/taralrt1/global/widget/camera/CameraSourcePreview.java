//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package nyxdev.hackatren.taralrt1.global.widget.camera;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.ViewGroup;

import com.google.android.gms.common.images.Size;

import java.io.IOException;

public class CameraSourcePreview extends ViewGroup {
    private static final String TAG = "CameraSourcePreview";
    private Context mContext;
    private SurfaceView mSurfaceView;
    private boolean mStartRequested;
    private boolean mSurfaceAvailable;
    private CameraSource mCameraSource;
    private GraphicOverlay mOverlay;

    public CameraSourcePreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mStartRequested = false;
        this.mSurfaceAvailable = false;
        this.mSurfaceView = new SurfaceView(context);
        this.mSurfaceView.getHolder().addCallback(new CameraSourcePreview.SurfaceCallback());
        this.addView(this.mSurfaceView);
    }

    @RequiresPermission("android.permission.CAMERA")
    public void start(CameraSource cameraSource) throws IOException, SecurityException {
        if (cameraSource == null) {
            this.stop();
        }

        this.mCameraSource = cameraSource;
        if (this.mCameraSource != null) {
            this.mStartRequested = true;
            this.startIfReady();
        }

    }

    @RequiresPermission("android.permission.CAMERA")
    public void start(CameraSource cameraSource, GraphicOverlay overlay) throws IOException, SecurityException {
        this.mOverlay = overlay;
        this.start(cameraSource);
    }

    public void stop() {
        if (this.mCameraSource != null) {
            this.mCameraSource.stop();
        }

    }

    public void release() {
        if (this.mCameraSource != null) {
            this.mCameraSource.release();
            this.mCameraSource = null;
        }

    }

    @RequiresPermission("android.permission.CAMERA")
    private void startIfReady() throws IOException, SecurityException {
        if (this.mStartRequested && this.mSurfaceAvailable) {
            this.mCameraSource.start(this.mSurfaceView.getHolder());
            if (this.mOverlay != null) {
                Size size = this.mCameraSource.getPreviewSize();
                int min = Math.min(size.getWidth(), size.getHeight());
                int max = Math.max(size.getWidth(), size.getHeight());
                if (this.isPortraitMode()) {
                    this.mOverlay.setCameraInfo(min, max, this.mCameraSource.getCameraFacing());
                } else {
                    this.mOverlay.setCameraInfo(max, min, this.mCameraSource.getCameraFacing());
                }

                this.mOverlay.clear();
            }

            this.mStartRequested = false;
        }

    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = 320;
        int height = 240;
        if (mCameraSource != null)
        {
            Size size = mCameraSource.getPreviewSize();
            if (size != null)
            {
                width = size.getWidth();
                height = size.getHeight();
            }
        }

        // Swap width and height sizes when in portrait, since it will be rotated 90 degrees
        if (isPortraitMode())
        {
            int tmp = width;

            //noinspection SuspiciousNameCombination
            width = height;
            height = tmp;
        }

        final int layoutWidth = right - left;
        final int layoutHeight = bottom - top;

        // Computes height and width for potentially doing fit width.
        int childWidth = layoutWidth;
        int childHeight = (int) (((float) layoutWidth / (float) width) * height);

        for (int i = 0; i < getChildCount(); ++i)
        {
            getChildAt(i).layout(0, 0, childWidth, layoutHeight);
        }

        try
        {
            startIfReady();
        }
        catch (SecurityException se)
        {
            Log.e(TAG, "Do not have permission to start the camera", se);
        }
        catch (IOException e)
        {
            Log.e(TAG, "Could not start camera source.", e);
        }

    }

    private boolean isPortraitMode() {
        int orientation = this.mContext.getResources().getConfiguration().orientation;
        if (orientation == 2) {
            return false;
        } else if (orientation == 1) {
            return true;
        } else {
            Log.d("CameraSourcePreview", "isPortraitMode returning false by default");
            return false;
        }
    }

    private class SurfaceCallback implements Callback {
        private SurfaceCallback() {
        }

        public void surfaceCreated(SurfaceHolder surface) {
            CameraSourcePreview.this.mSurfaceAvailable = true;

            try {
                CameraSourcePreview.this.startIfReady();
            } catch (SecurityException var3) {
                Log.e("CameraSourcePreview", "Do not have permission to start the camera", var3);
            } catch (IOException var4) {
                Log.e("CameraSourcePreview", "Could not start camera source.", var4);
            }

        }

        public void surfaceDestroyed(SurfaceHolder surface) {
            CameraSourcePreview.this.mSurfaceAvailable = false;
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }
    }
}
