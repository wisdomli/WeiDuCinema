package com.bw.movie.WeiDuCinema.di.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.WeiDuCinema.R;

/**
 * 三秒延迟闪屏页面
 */
public class SplashActivity extends AppCompatActivity {

    private TextView time_text;
    private int time = 3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (time > 0) {
                    time--;
                    time_text.setText(time + "s");
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else {
                    handler.removeCallbacksAndMessages(null);
                    startActivity(new Intent(SplashActivity.this, ExperienceActivity.class));
                    finish();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        time_text = findViewById(R.id.time_text);
        handler.sendEmptyMessageDelayed(0, 1000);
        findViewById(R.id.time_tiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacksAndMessages(null);
                startActivity(new Intent(SplashActivity.this, ExperienceActivity.class));
                finish();
            }
        });
    }
}
