package com.hbgz.zhjy.starter;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoActivity extends Activity {
    private SurfaceHolder holder;
    private IjkMediaPlayer ijkMediaPlayer;
    private SurfaceView mVideoView2;
    private ViewGroup.LayoutParams layoutParams;
    private int h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mVideoView2 = (SurfaceView) findViewById(R.id.videoView);
        layoutParams = mVideoView2.getLayoutParams();
        h = layoutParams.height;
        final Button btn_change = (Button) findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (VideoActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    btn_change.setText("窗口");
                }else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    layoutParams.height = h;
                    btn_change.setText("全屏");
                }
            }
        });
        holder = mVideoView2.getHolder();
        ijkMediaPlayer = new IjkMediaPlayer();
        play();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                play();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                ijkMediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
    private void play() {
        try {
            ijkMediaPlayer.reset();
            ijkMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            ijkMediaPlayer.setDataSource(getApplicationContext(), Uri.parse("rtmp://119.97.130.227:45306/live/96"));
//            ijkMediaPlayer.setDataSource(getApplicationContext(), Uri.parse("rtmp://live.hkstv.hk.lxdns.com/live/hks"));
//            ijkMediaPlayer.setDataSource("rtmp://119.97.130.227:45306/live/96");
            ijkMediaPlayer.setDisplay(holder);
            ijkMediaPlayer.prepareAsync();
            ijkMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onRestart() {
        ijkMediaPlayer.reset();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        ijkMediaPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        ijkMediaPlayer.stop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        ijkMediaPlayer.release();
        ijkMediaPlayer = null;
        super.onDestroy();
    }


}
