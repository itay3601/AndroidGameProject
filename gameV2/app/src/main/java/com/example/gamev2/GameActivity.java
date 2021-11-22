package com.example.gamev2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameActivity extends AppCompatActivity implements PropertyChangeListener , SensorEventListener {
    private GameViewTest gameview;
    private  GameViewTest gamviwe2;
    private MediaPlayer song;
    private Point point;
    private SharedPreferences sp;
    private Steps counter;
    private Context context=this;
    private Intent intent;
    private SharedPreferences.Editor editor;
    private AlertDialog.Builder builder2;
    private boolean noPlaying;
    private Handler mHandler;
    private AlertDialog.Builder gameOver;

    private Sensor sensor;
    private SensorManager manager;

    private boolean MotionContol;
    private boolean omBreackPresAlow=true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp=getSharedPreferences("HeightScore",MODE_PRIVATE);
        editor=sp.edit();

        counter=new Steps();
        song=MediaPlayer.create(this,R.raw.gameplaysoundtrack);
        song.start();
        song.setVolume(getIntent().getFloatExtra("volume",100),getIntent().getFloatExtra("volume",100));

        Point point =new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        gameview =new GameViewTest(this,point.x,point.y);
        intent=new Intent(this,this.getClass());

        //MotionContol = getIntent().getBooleanExtra("MotionControl",true);
        MotionContol=sp.getBoolean("checked",true);
        if(MotionContol) {
            manager = (SensorManager) getSystemService(SENSOR_SERVICE);
            if (manager != null) {
                sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
        }
        gameview.setMotionControl(MotionContol);
        gameview.addPropertyChangeListener(this);

        setContentView(gameview);
        gameview.newGame();


    }


    public GameActivity() {
    }

    @Override
    protected void onPause() {
        super.onPause();
        song.pause();
        noPlaying=true;
//        SharedPreferences.Editor editor=sp.edit();
//        editor.putInt("score",counter.getConterStep());
//        editor.commit();
        if(sensor!=null)
        {
            manager.unregisterListener(this);
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(noPlaying){
            song.start();
            //getRightThread();
        }
        if(sensor!=null)
        {
            manager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }




    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();




    }
    @Override
    protected void onNewIntent(Intent intent)  {
        super.onNewIntent(intent);


    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {
        if (omBreackPresAlow) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_game, null);
            Button newGame = dialogView.findViewById(R.id.newGameBtn);
            Button backBtn = dialogView.findViewById(R.id.BeakBtn);
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor = sp.edit();


                    if (sp.getInt("score", 0) < counter.getConterStep()) {
                        editor.commit();
                        updateScore();
                        editor = sp.edit();
                        editor.putInt("score", counter.getConterStep());

                        builder2 = new AlertDialog.Builder(builder.getContext());
                        View dialogViewScore = getLayoutInflater().inflate(R.layout.edit_text_high_score, null);
                        final EditText editText = dialogViewScore.findViewById(R.id.edit_text);
                        Button okBtn = dialogViewScore.findViewById(R.id.okBtn);
                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editor.commit();
                                updateScoreText();
                                editor = sp.edit();

                                editor.putString("editText", editText.getText().toString());
                                editor.putBoolean("firstrun", true);
                                editor.commit();

                                gameview.destroy();
                                song.pause();
                                GameActivity.super.onBackPressed();

                            }
                        });
                        builder2.setView(dialogViewScore).show();
                    } else {
                        editor.commit();

                        gameview.destroy();
                        song.pause();
                        finish();
                        GameActivity.super.onBackPressed();
                    }

                }
            });
            newGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor = sp.edit();
                    if (sp.getInt("score", 0) < counter.getConterStep()) {
                        editor.commit();
                        updateScore();
                        editor = sp.edit();
                        editor.putInt("score", counter.getConterStep());
                        builder2 = new AlertDialog.Builder(builder.getContext());
                        View dialogViewScore = getLayoutInflater().inflate(R.layout.edit_text_high_score, null);
                        final EditText editText = dialogViewScore.findViewById(R.id.edit_text);
                        Button okBtn = dialogViewScore.findViewById(R.id.okBtn);
                        okBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editor.commit();
                                updateScoreText();
                                editor = sp.edit();

                                editor.putString("editText", editText.getText().toString());
                                editor.putBoolean("firstrun", true);

                                editor.commit();
                                gameview.destroy();
                                song.pause();
                                GameActivity.super.onBackPressed();
                                startActivity(intent);
                            }
                        });
                        builder2.setView(dialogViewScore).show();


                    } else {
                        editor.commit();
                        gameview.destroy();
                        song.pause();
                        GameActivity.super.onBackPressed();
                        startActivity(intent);
                    }


                }
            });


            builder.setView(dialogView).show();

        }
    }
    public void updateScoreText(){
        editor=sp.edit();
        editor.putString("editText4",sp.getString("editText3",""));
        editor.putString("editText3",sp.getString("editText2",""));
        editor.putString("editText2",sp.getString("editText",""));
        editor.commit();


    }
    public void updateScore(){
        editor=sp.edit();
        editor.putInt("score4",sp.getInt("score3",0));
        editor.putInt("score3",sp.getInt("score2",0));
        editor.putInt("score2",sp.getInt("score",0));
        editor.commit();


    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Game Over"))
        {
            omBreackPresAlow=false;
            viewGameOverAlert();
            worker();
        }


    }

    private void viewGameOverAlert()
    {
        Looper.prepare();

        View view = getLayoutInflater().inflate(R.layout.dialog_game,null);
        Button positive_btn = view.findViewById(R.id.newGameBtn);
        Button negative_btn = view.findViewById(R.id.BeakBtn);
        gameOver = new AlertDialog.Builder(GameActivity.this);
        positive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor=sp.edit();
                if(sp.getInt("score",0)<counter.getConterStep()){
                    editor.commit();
                    updateScore();
                    editor=sp.edit();
                    editor.putInt("score",counter.getConterStep());
                    builder2=new AlertDialog.Builder(gameOver.getContext());
                    View dialogViewScore =getLayoutInflater().inflate(R.layout.edit_text_high_score,null);
                    final EditText editText=dialogViewScore.findViewById(R.id.edit_text);
                    Button okBtn =dialogViewScore.findViewById(R.id.okBtn);
                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editor.commit();
                            updateScoreText();
                            editor=sp.edit();

                            editor.putString("editText",editText.getText().toString());
                            editor.putBoolean("firstrun",true);

                            editor.commit();
                            gameview.destroy();
                            song.pause();
                            GameActivity.super.onBackPressed();
                            startActivity(intent);
                        }
                    });
                    builder2.setView(dialogViewScore).show();




                }else{
                    editor.commit();
                    gameview.destroy();
                    song.pause();
                    GameActivity.super.onBackPressed();
                    startActivity(intent);}


            }

        });
        negative_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor=sp.edit();
                if(sp.getInt("score",0)<counter.getConterStep()){
                    editor.commit();
                    updateScore();
                    editor=sp.edit();
                    editor.putInt("score",counter.getConterStep());

                    builder2=new AlertDialog.Builder(gameOver.getContext());
                    View dialogViewScore =getLayoutInflater().inflate(R.layout.edit_text_high_score,null);
                    final EditText editText=dialogViewScore.findViewById(R.id.edit_text);
                    Button okBtn =dialogViewScore.findViewById(R.id.okBtn);
                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editor.commit();
                            updateScoreText();
                            editor=sp.edit();

                            editor.putString("editText",editText.getText().toString());
                            editor.putBoolean("firstrun",true);
                            editor.commit();

                            gameview.destroy();
                            song.pause();
                            GameActivity.super.onBackPressed();

                        }
                    });
                    builder2.setView(dialogViewScore).show();
                }else {
                    editor.commit();

                    gameview.destroy();
                    song.pause();
                    GameActivity.super.onBackPressed();}

            }

        });

        gameOver.setCancelable(false);
        gameOver.setView(view);
        mHandler = new Handler(Looper.getMainLooper())
        {
            @Override
            public void handleMessage(Message message)
            {
                gameOver.show();
            }
        };


    }

    private void worker()
    {
        Message message = mHandler.obtainMessage();
        message.sendToTarget();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values[0]>2.0)
        {
            gameview.SensorTo_rightButton(false);
            gameview.SensorTo_leftButton(true);
        }
        else if(event.values[0] <-2.0)
        {
            gameview.SensorTo_leftButton(false);
            gameview.SensorTo_rightButton(true);
        }
        else if(event.values[0] > -2.0 && event.values[0]<2.0)
        {
            gameview.SensorTo_rightButton(false);
            gameview.SensorTo_leftButton(false);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}



