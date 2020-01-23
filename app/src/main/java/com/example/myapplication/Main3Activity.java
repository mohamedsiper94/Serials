package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;

import cn.jzvd.Jzvd;

public class Main3Activity extends AppCompatActivity {
    private PlayerView playerView;
   public static String url="";
     SimpleExoPlayer simpleExoPlayer;
     ProgressBar progressBar;
    PlayerControlView playerControlView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
          url=getIntent().getStringExtra("url");
        // intiExoplayer();

           Log.d("json","url is :" + url);

    cn.jzvd.JzvdStd jzvdStd=findViewById(R.id.jz_video);
           jzvdStd.setUp(url,"el");
           jzvdStd.thumbImageView.setImageResource(R.drawable.emoji_1f30d);





     }  @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    /*private void intiExoplayer() {
     //   playerView=findViewById(R.id.exo);
        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(this);

        playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory data=new DefaultDataSourceFactory(this, Util.getUserAgent(this,"appname"));
        MediaSource vid=new ExtractorMediaSource.Factory(data).createMediaSource(Uri.parse(url));
        simpleExoPlayer.prepare(vid);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getBufferedPosition();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }*/

}
