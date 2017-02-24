package com.cokelime.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Yichi.Chen on 2/18/2017.
 */

public class SnakeGrid extends View {

    private static final int DELTA = 10;

    Rect rect;

    private int xMax;
    private int yMax;

    private int rectScaleX;
    private int rectScaleY;

    private int rectX = 0;
    private int rectY = 0;

    private LastMove action = LastMove.right;

    Paint paint;

    public SnakeGrid(Context context) {
        super(context);
        rect =  new Rect();
        paint = new Paint();

        paint.setColor(Color.BLACK);
    }

    public SnakeGrid(Context context, AttributeSet attributeSet){

        super(context, attributeSet);
        rect =  new Rect();
        paint = new Paint();


        paint.setColor(Color.BLACK);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        rect.set(rectX , rectY, rectX + 100, rectY + 10);

        canvas.drawRect(rect, paint);

        try{
            Thread.sleep(500);
        }catch (InterruptedException ex){

        }
        switch (getAction()){
            case up:
                moveUp();
                break;
            case down:
                moveDown();
                break;
            case left:
                moveLeft();
                break;
            case right:
                moveRight();
                break;
            default:
                moveRight();
                break;
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        xMax = w-1;
        yMax = h-1;

        rectX = xMax/2;
        rectY = yMax /2;

        rectScaleX = xMax / 8;
        rectScaleY = yMax / 8;
    }

    public void moveRight(){

        if(rectX + DELTA < xMax) {
            rectX += DELTA;
            invalidate();
        }
    }

    public void moveLeft(){

        if(rectX - DELTA > 0){
            rectX -= DELTA;
            invalidate();
        }

    }

    public void moveUp(){

        if(rectY - DELTA > 0){
            rectY -= DELTA;
            invalidate();
        }
    }

    public void moveDown(){
        if(rectY + DELTA < yMax) {
            rectY += DELTA;
            invalidate();
        }
    }


    public LastMove getAction() {
        return action;
    }

    public void setAction(LastMove action) {
        this.action = action;
    }


    public enum LastMove {
        up,down,left,right
    }
}
