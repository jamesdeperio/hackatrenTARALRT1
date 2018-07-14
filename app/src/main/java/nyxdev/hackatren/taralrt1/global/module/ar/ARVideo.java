//================================================================================================================================
//
//  Copyright (c) 2015-2018 VisionStar Information Technology (Shanghai) Co., Ltd. All Rights Reserved.
//  EasyAR is the registered trademark or trademark of VisionStar Information Technology (Shanghai) Co., Ltd in China
//  and other countries for the augmented reality technology developed by VisionStar Information Technology (Shanghai) Co., Ltd.
//
//================================================================================================================================

package nyxdev.hackatren.taralrt1.global.module.ar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import cn.easyar.FunctorOfVoidFromVideoStatus;
import cn.easyar.StorageType;
import cn.easyar.VideoPlayer;
import cn.easyar.VideoStatus;
import cn.easyar.VideoType;
import nyxdev.hackatren.taralrt1.appmodule.claimreward.AcceptRewardDialog;
import nyxdev.hackatren.taralrt1.integration.dao.query.Query;
import nyxdev.hackatren.taralrt1.integration.network.RestRepository;

public class ARVideo
{
    private VideoPlayer player;
    private boolean prepared;
    private boolean found;
    private String path;
    private Context context;
    private  RestRepository restRespository;
    private  Query.Select selectQuery;
    public ARVideo(Context context, RestRepository restRespository, Query.Select selectQuery)
    {
        this.restRespository=restRespository;
        this.selectQuery=selectQuery;
        this.context=context;
        player = new VideoPlayer();
        prepared = false;
        found = false;
    }
    public void dispose()
    {
        player.close();
    }

    public void openVideoFile(String path, int texid)
    {
        this.path = path;
        player.setRenderTexture(texid);
        player.setVideoType(VideoType.Normal);
        player.open(path, StorageType.Assets, new FunctorOfVoidFromVideoStatus() {
            @Override
            public void invoke(int status) {
                setVideoStatus(status);
            }
        });
    }
    public void openTransparentVideoFile(String path, int texid)
    {
        this.path = path;
        player.setRenderTexture(texid);
        player.setVideoType(VideoType.TransparentSideBySide);
        player.open(path, StorageType.Assets, new FunctorOfVoidFromVideoStatus() {
            @Override
            public void invoke(int status) {
                setVideoStatus(status);
            }
        });
    }
    public void openStreamingVideo(String url, int texid)
    {
        this.path = url;
        player.setRenderTexture(texid);
        player.setVideoType(VideoType.Normal);
        player.open(url, StorageType.Absolute, new FunctorOfVoidFromVideoStatus() {
            @Override
            public void invoke(int status) {
                setVideoStatus(status);
            }
        });
    }

    public void setVideoStatus(int status)
    {
        Log.i("HelloAR", "video: " + path + " (" + Integer.toString(status) + ")");
        if (status == VideoStatus.Ready) {
            prepared = true;
            if (found) {
                player.play();
            }
        } else if (status == VideoStatus.Completed) {
            if (found) {
         //       player.play();
                new Handler(Looper.myLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        AcceptRewardDialog cdd = new AcceptRewardDialog(context,restRespository,selectQuery);
                        cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        cdd.show();
                    }
                });

            }

        }
    }

    public void onFound()
    {
        found = true;
        if (prepared) {
            player.play();
        }
    }
    public void onLost()
    {
        found = false;
        if (prepared) {
            player.pause();
        }
    }
    public boolean isRenderTextureAvailable()
    {
        return player.isRenderTextureAvailable();
    }
    public void update()
    {
        player.updateFrame();
    }
}
