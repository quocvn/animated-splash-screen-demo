package com.quocnguyen.splashscreendemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by quocnguyen on 08/07/2017.
 */
public class SplashScreenActivity extends Activity{

    ImageView imageViewSplash;
    TextView txtAppName;
    RelativeLayout relativeLayout;
    Thread SplashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageViewSplash = (ImageView) findViewById(R.id.imageViewSplash);
        txtAppName = (TextView) findViewById(R.id.txtAppName);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);

        startAnimations();
    }

    private void startAnimations() {

        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);

        rotate.reset();
        translate.reset();
        relativeLayout.clearAnimation();

        imageViewSplash.startAnimation(rotate);
        txtAppName.startAnimation(translate);
        SplashThread = new Thread(){
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while (waited < 3500) {
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waited += 100;
                }
                SplashScreenActivity.this.finish();
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        };
        SplashThread.start();
    }
}
