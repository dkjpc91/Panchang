package com.mithilakshar.mithilapanchang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    ImageView splashTxt,logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splashTxt=findViewById(R.id.splashTxt);
        logo=findViewById(R.id.logo);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.fade);
        animation.setDuration(3000);
        logo.startAnimation(animation);
        splashTxt.startAnimation(animation);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i= new Intent(getApplicationContext(),home.class);
                startActivity(i);
                finish();
            }
        },3000);


    }
}