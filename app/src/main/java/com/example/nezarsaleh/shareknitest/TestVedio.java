package com.example.nezarsaleh.shareknitest;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;

public class TestVedio extends AppCompatActivity implements View.OnClickListener, SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {



    private SurfaceView surfaceViewFrame;
    private static final String TAG = "VideoPlayer";
    private SurfaceHolder holder;
    private ProgressBar progressBarWait;
    private ImageView pause;
    private MediaPlayer player;
    private Timer updateTimer;
    String video_uri = "http://www.sdg.ae/v.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vediotest);




//        pause = (ImageView) findViewById(R.id.imageViewPauseIndicator);
//        pause.setVisibility(View.GONE);
//        if (player != null) {
//            if (!player.isPlaying()) {
//                pause.setVisibility(View.VISIBLE);
//            }
//        }


        surfaceViewFrame = (SurfaceView) findViewById(R.id.surfaceViewFrame);
        surfaceViewFrame.setOnClickListener(this);
        surfaceViewFrame.setClickable(false);

     //   progressBarWait = (ProgressBar) findViewById(R.id.progressBarWait);

        holder = surfaceViewFrame.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        player = new MediaPlayer();
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setScreenOnWhilePlaying(true);
        player.setDisplay(holder);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.surfaceViewFrame) {
            if (player != null) {
                if (player.isPlaying()) {
                    player.pause();
                    pause.setVisibility(View.VISIBLE);
                } else {
                    player.start();
                    pause.setVisibility(View.GONE);
                }
            }
        }

    }





    private void playVideo() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    player.setDataSource(video_uri);
                    player.prepare();
                } catch (Exception e) { // I can split the exceptions to get which error i need.
                    showToast("Error while playing video");
                    Log.i(TAG, "Error");
                    e.printStackTrace();
                }
            }
        }).start();
    }





    private void showToast(final String string) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(TestVedio.this, string, Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }





    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub

    }

    public void surfaceCreated(SurfaceHolder holder) {
        playVideo();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }
    //prepare the video
    public void onPrepared(MediaPlayer mp) {
      //  progressBarWait.setVisibility(View.GONE);

        // Adjust the size of the video
        // so it fits on the screen
        int videoWidth = player.getVideoWidth();
        int videoHeight = player.getVideoHeight();
        float videoProportion = (float) videoWidth / (float) videoHeight;
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        float screenProportion = (float) screenWidth / (float) screenHeight;
        android.view.ViewGroup.LayoutParams lp = surfaceViewFrame.getLayoutParams();

        if (videoProportion > screenProportion) {
            lp.width = screenWidth;
            lp.height = (int) ((float) screenWidth / videoProportion);
        } else {
            lp.width = (int) (videoProportion * (float) screenHeight);
            lp.height = screenHeight;
        }
        surfaceViewFrame.setLayoutParams(lp);

        if (!player.isPlaying()) {
            player.start();
        }
        surfaceViewFrame.setClickable(true);
    }

    // callback when the video is over
    public void onCompletion(MediaPlayer mp) {
        mp.stop();
        if (updateTimer != null) {
            updateTimer.cancel();
        }
        finish();
    }




}
