package com.digital4africa.alvin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class Splashscreen extends AppCompatActivity {


    private ImageView mainlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appsplashscreen);

        mainlogo=(ImageView)findViewById(R.id.logo);


        Thread thread=new Thread(){

            @Override
            public void run() {
                super.run();

                try {
                    sleep(4000);


                    Intent intent =new Intent(getApplicationContext(),MainPage.class);


                    startActivity(intent);

                    finish();



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}
