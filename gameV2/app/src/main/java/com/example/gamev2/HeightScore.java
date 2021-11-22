package com.example.gamev2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class HeightScore extends Activity {

    private SharedPreferences sp;
    private Integer correntScore;
    private SharedPreferences.Editor editor;
    private ListView listView;
    private String[] strings= {"0","0","0","0"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.heightscore);
        listView=findViewById(R.id.my_list_view);
        sp = getSharedPreferences("HeightScore", MODE_PRIVATE);
        editor=sp.edit();

        correntScore = sp.getInt("score", 0);
        strings[0]="1."+" "+sp.getString("editText","")+"     "+correntScore.toString();
        correntScore = sp.getInt("score2", 0);
        strings[1]="2."+" "+sp.getString("editText2","")+"     "+correntScore.toString();
        correntScore = sp.getInt("score3", 0);
        strings[2]="3."+" "+sp.getString("editText3","")+"     "+correntScore.toString();
        correntScore = sp.getInt("score4", 0);
        strings[3]="4."+" "+sp.getString("editText4","")+"     "+correntScore.toString();


        editor.commit();





        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strings);

        listView.setAdapter(adapter);









    }


}

