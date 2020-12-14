package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {
Button button;
    MediaPlayer over;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        over=MediaPlayer.create(GameOverActivity.this,R.raw.gameover);
        over.start();
        button=(Button) findViewById(R.id.btnReset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Newgame();
            }
        });
    }
    public void Newgame(){
        over.release();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

     void ReStartGame(View v)
    {
        onCreate(Bundle.EMPTY);
    }

}
