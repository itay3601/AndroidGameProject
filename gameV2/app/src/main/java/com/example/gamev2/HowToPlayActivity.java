package com.example.gamev2;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HowToPlayActivity extends AppCompatActivity {
    private Point point =new Point();
    private HowToPlayThred howToPlayThred;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent=new Intent(this,this.getClass());

        getWindowManager().getDefaultDisplay().getSize(point);
        howToPlayThred=new HowToPlayThred(this,point.x,point.y);
        setContentView(howToPlayThred);
        howToPlayThred.newGame();




    }

    public HowToPlayActivity() {
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(HowToPlayActivity.this);
        View dialogView =getLayoutInflater().inflate(R.layout.dialog_how_to_play,null);
        Button againBtn=dialogView.findViewById(R.id.againBtn);
        Button beackHowBtn=dialogView.findViewById(R.id.beackHowBtn);
        beackHowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HowToPlayActivity.super.onBackPressed();

            }
        });
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howToPlayThred.setPlaying(false);
                HowToPlayActivity.super.onBackPressed();
                startActivity(intent);

            }
        });


        builder.setView(dialogView).show();
    }


}
