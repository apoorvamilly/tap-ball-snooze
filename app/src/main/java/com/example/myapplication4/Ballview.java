package com.example.myapplication4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Ballview extends View
{
    private Bitmap ball[]=new Bitmap[2];
    private int ballX=10;
    private int ballY;
    private int ballSpeed;

    private int yellowX,yellowY,yellowSpeed=16;
    private Paint yellowPaint=new Paint();

    private int redX,redY,redSpeed=20;
    private Paint redPaint=new Paint();

    private int greenX, greenY, greenSpeed= 30;
    private Paint greenPaint=new Paint();
    private int score,lifeCounterofBall, yellowCounter=0, nightday=0;


    private int canvasWidth,canvasHeight;



    private boolean touch =false;


    private Bitmap backgroundImage;
    private Paint scorepaint =new Paint();
    private Bitmap life[]= new Bitmap[2];
    public Ballview(Context context) {
        super(context);
        ball[0]= BitmapFactory.decodeResource(getResources(),R.drawable.bolo);
        ball[1]=BitmapFactory.decodeResource(getResources(),R.drawable.fall1);
        backgroundImage =BitmapFactory.decodeResource(getResources(),R.drawable.sky);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);


        scorepaint.setColor(Color.BLACK);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorepaint.setAntiAlias(true);

        life[0]=BitmapFactory.decodeResource(getResources(),R.drawable.fall);
        life[1]=BitmapFactory.decodeResource(getResources(),R.drawable.die);
        ballY=500;

        score =0;
        lifeCounterofBall=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight();

        canvas.drawBitmap(backgroundImage,0,0,null);
        int minballY=ball[0].getHeight();
        int maxballY=canvasHeight-ball[0].getHeight()*3;
        ballY=ballY+ballSpeed;

        if(ballY<minballY)
        {
            ballY=minballY;
        }
        if(ballY>maxballY)
        {
            ballY=maxballY;
        }
        ballSpeed=ballSpeed+2;

        if(touch)
        {
            canvas.drawBitmap(ball[1],ballX,ballY,null);
            touch=false;
        }

        else
        {
            canvas.drawBitmap(ball[0],ballX,ballY,null);
        }
        yellowX=yellowX-yellowSpeed;
        if(hitballChecker(yellowX,yellowY))
        {
            score=score+10;

            //change screen every 200 points
            //if score is perfectly divisible by 200 , change screen
            if(score % 200 == 0){
                if((score/200)%2==0){
                    backgroundImage =BitmapFactory.decodeResource(getResources(),R.drawable.sky);
                }
                else{
                    backgroundImage =BitmapFactory.decodeResource(getResources(),R.drawable.night);
                }


            }

            

            yellowX=yellowX-100;
        }

        if(yellowX<0)
        {
            yellowCounter++;
            yellowX=canvasWidth+21;
            yellowY=(int)Math.floor(Math.random()*(maxballY-minballY)+minballY);
        }

        canvas.drawCircle(yellowX,yellowY,30,yellowPaint);



        redX=redX-redSpeed;
        if(hitballChecker(redX,redY))
        {

            redX=-100;
            lifeCounterofBall--;
            if(lifeCounterofBall==0)
            {
                Toast.makeText(getContext(), "GAME OVER", Toast.LENGTH_SHORT).show();
                Intent gameoverIntent=new Intent(getContext(),GameOverActivity.class);
                gameoverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(gameoverIntent);
            }
        }

        if(redX<0)
        {
            redX=canvasWidth+21;
            redY=(int)Math.floor(Math.random()*(maxballY-minballY)+minballY);
        }

        canvas.drawCircle(redX,redY,50,redPaint);


        greenX = greenX- greenSpeed;
        if(hitballChecker(greenX,greenY))
        {
            greenX = -100;

            if(lifeCounterofBall<3)
            {
                lifeCounterofBall++;
            }

        }

        if(greenX<0 && yellowCounter==5)
        {   yellowCounter=0;
            greenX=canvasWidth+21;
            greenY=(int)Math.floor(Math.random()*(maxballY-minballY)+minballY);
            //            greenY=(int)Math.floor(Math.random()*(maxballY-minballY)+minballY);

        }
        canvas.drawCircle(greenX,greenY,20,greenPaint);

        canvas.drawText("Score:"+score,20,60,scorepaint);
        for(int i=0;i<3;i++)
        {
            int x=(int)(380 + life[0].getWidth()*1.5*i);
            int y=30;
            if(i<lifeCounterofBall)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }
            else
            {
                canvas.drawBitmap(life[1],x,y,null);
            }
        }

        canvas.drawText("Score:"+score,20,60,scorepaint);


    }

    public boolean hitballChecker(int x,int y)
    {
        if(ballX<x && x<(ballX + ball[0].getWidth())&& ballY <y && y<(ballY + ball[0].getHeight()))
        {
            return true;
        }
        return false;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            touch=true;


            ballSpeed=-30;


        }
        return true;
    }
}
