package gkyrits.test.tv_demo_try1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.VideoView;

public class PlayerActivity extends Activity {

    public static final String ELEM_KEY = "elem_play_key";

    public VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle extra = getIntent().getExtras();
        Model.Element elem = (Model.Element) extra.getSerializable(ELEM_KEY);

        video = findViewById(R.id.videoview_id);
        if(elem!=null) {
            video.setVideoPath(elem.videoPath);
            //video.requestFocus();
            /*video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    video.start();
                }
            });*/
            //video.start();
        }

    }
}