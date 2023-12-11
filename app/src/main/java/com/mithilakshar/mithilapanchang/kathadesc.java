package com.mithilakshar.mithilapanchang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class kathadesc extends AppCompatActivity {

     TextView kathaDesc,kathaTitle;
    ImageView kathaImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kathadesc);

        kathaTitle=findViewById(R.id.kathatitle);
        kathaDesc=findViewById(R.id.kathaDesc);
        kathaImg=findViewById(R.id.kathaImg);
        Intent intent = getIntent();

        String kathaT = intent.getStringExtra("kathaName");
        String kathaD = intent.getStringExtra("kathaStory");
        String kathaI = intent.getStringExtra("kathaUrl");

        kathaTitle.setText(kathaT);
        kathaDesc.setText(kathaD);
        Picasso.get().load(kathaI).into(kathaImg);





}}