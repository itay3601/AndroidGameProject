package com.example.gamev2;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isPlaying=true;
    private MediaPlayer songActivity;


    private AudioManager mAudioManager;
    private final static int MAX_VOLUME = 100;
    private boolean keepPlaying=false;
    private RadioGroup radioJumpingBoy;
    private RadioButton cheakJumpingBoy1;
    private RadioButton  jumpingBoy1;
    private int radioid;
    private JumpingBoy rootJumpingBoy;
    private View dialogView;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private float volume=100;
    private boolean MotionControll;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playbtn = findViewById(R.id.playbtn);
        Button Exit = findViewById(R.id.ExitBtn);
        final Button highScore = findViewById(R.id.hight_score);
        songActivity = MediaPlayer.create(this, R.raw.skysound);
        ImageButton setting = findViewById(R.id.setting);
        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        Button howToPlay = findViewById(R.id.how_to_play);
        //dialogView = getLayoutInflater().inflate(R.layout.dialog_main_activity, null);
        //toggleButton = dialogView.findViewById(R.id.motionControl_btn);
        rootJumpingBoy = new JumpingBoy();
        MotionControll = true;
        sp = getSharedPreferences("HeightScore", MODE_PRIVATE);
        editor = sp.edit();
        editor.putBoolean("checked",true);

        if (!sp.contains("firstrun")) {
            //editor = sp.edit();
            editor.putBoolean("checked",true);
            editor.putInt("score", 0);
            editor.putInt("score2", 0);
            editor.putInt("score3", 0);
            editor.putInt("score4", 0);
            editor.putString("editText", "");
            editor.putString("editText1", "");
            editor.putString("editText2", "");
            editor.putString("editText3", "");

            editor.commit();
        }



//////Setting Button/////////
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               dialogView = getLayoutInflater().inflate(R.layout.dialog_main_activity, null);
                radioJumpingBoy = dialogView.findViewById(R.id.radioJampingBoy);
                toggleButton = dialogView.findViewById(R.id.motionControl_btn);
                toggleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MotionControll = toggleButton.isChecked();
                        editor=sp.edit();
                        System.out.println("this is motion controll" + MotionControll);
                        editor.putBoolean("checked",MotionControll);
                        editor.commit();
                    }
                });

                jumpingBoy1 = dialogView.findViewById(R.id.jumpingboy1);

                SeekBar seekBar = dialogView.findViewById(R.id.seekbarVolume);
                seekBar.setMax(MAX_VOLUME);
                seekBar.setProgress(MAX_VOLUME * mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));


                ///Seekbar///////////////////
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        volume = (float) (1 - (Math.log(MAX_VOLUME - progress) / Math.log(MAX_VOLUME)));
                        songActivity.setVolume(volume, volume);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });



                builder.setView(dialogView).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chackButton(dialogView);
                        if (radioid == jumpingBoy1.getId()) {
                            rootJumpingBoy.setTypeJumpinBoy1(false);

                        } else {
                            rootJumpingBoy.setTypeJumpinBoy1(true);


                        }

                    }
                }).show();




            }
        });



        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HeightScore.class);

                keepPlaying=true;


                startActivity(intent);
            }
        });



        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GameActivity.class);

                isPlaying=false;
                songActivity.pause();
                intent.putExtra("volume",volume);
                if(toggleButton!=null) {
                    editor=sp.edit();
                    editor.putBoolean("checked",toggleButton.isChecked());
                    intent.putExtra("MotionControl", toggleButton.isChecked());
                } else{
                    editor=sp.edit();
                    editor.putBoolean("checked",true);
                    intent.putExtra("MotionControl",true);
                }
                editor.commit();
                startActivity(intent);
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this,HowToPlayActivity.class);

                isPlaying=false;
                songActivity.pause();

                startActivity(intent2);

            }
        });

    }



    protected void onPause() {
        super.onPause();
//        isPlaying=false;
//       songActivity.pause();



    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        songActivity.start();




    }

    @Override
    protected void onDestroy() {
        songActivity.stop();
        super.onDestroy();


    }

    @Override
    protected void onStop() {
        if(!keepPlaying){
            songActivity.pause();
        }
        editor=sp.edit();
        editor.putBoolean("checked",MotionControll);
        editor.commit();
        super.onStop();

    }

    @Override
    protected void onStart() {
        keepPlaying=false;

        super.onStart();
    }
    public void chackButton(View V){
        radioid =radioJumpingBoy .getCheckedRadioButtonId();
        cheakJumpingBoy1 =findViewById(radioid);

    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
