package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        button=(Button) findViewById(R.id.btnReset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Newgame();
            }
        });
    }
    public void Newgame(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

     void ReStartGame(View v)
    {
        onCreate(Bundle.EMPTY);
    }

}
