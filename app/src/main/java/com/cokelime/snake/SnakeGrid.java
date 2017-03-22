package com.cokelime.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Yichi.Chen on 2/18/2017.
 */

public class SnakeGrid extends View {

    private static final int DELTA = 10;
    private static final int SNAKE_BODY_WIDTH = 10;
    private static final int SNAKE_BODY_SEGEMENTS = 5;

    private int xMax;
    private int yMax;

    private int rectScaleX;
    private int rectScaleY;

    private int rectX = 0;
    private int rectY = 0;

    enum LastMove {
        up, down, left, right
    }

    private LastMove action = LastMove.right;

    Paint paint;

    private List<SnakeBody> snakes;

    private boolean firstRun = true;

    public SnakeGrid(Context context) {
        super(context);
        paint = new Paint();

        paint.setColor(Color.BLACK);
        initSnake();
    }

    public SnakeGrid(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        paint = new Paint();


        paint.setColor(Color.BLACK);
        initSnake();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //initialize

        for (int i = snakes.size() - 1; i >= 0; i--) {
            //start with last and go from tail to head
            //update the last one with the coordinates of next until the head
            String log;
            if (i - 1 >= 0) {
                Rect current = snakes.get(i);
                current = snakes.get(i - 1);
                snakes.set(i, current);
                canvas.drawRect(current, paint);
                log = MessageFormat.format("currI= {2} X: {0}, Y: {1}: Right: {3}", current.left, rectY, i, current.right);
            } else {
                //head at index 0, update it to the global position
                //Rect head = snakes.get(i);
                Rect rect = new Rect(rectX, rectY, rectX + SNAKE_BODY_WIDTH, rectY + SNAKE_BODY_WIDTH);
                //rect.set(rectX, rectY, rectX + 10, rectY + 10);
                snakes.set(i, rect);
                canvas.drawRect(rect, paint);
                log = MessageFormat.format("currI= {2} X: {0}, Y: {1} Right: {3}", rect.left, rectY, i, rect.right);
            }
            Log.d(TAG, log);
        }



        //update


        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }

        switch (getAction()) {
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
        xMax = w - 1;
        yMax = h - 1;

        rectX = xMax / 2;
        rectY = yMax / 2;

        rectScaleX = xMax / 8;
        rectScaleY = yMax / 8;
    }

    public void moveRight() {

        if (rectX + DELTA < xMax) {
            rectX += DELTA;
            invalidate();
        }
    }

    public void moveLeft() {

        if (rectX - DELTA > 0) {
            rectX -= DELTA;
            invalidate();
        }

    }

    public void moveUp() {

        if (rectY - DELTA > 0) {
            rectY -= DELTA;
            invalidate();
        }
    }

    public void moveDown() {
        if (rectY + DELTA < yMax) {
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


    private void initSnake(){

        if(snakes == null){

            snakes = new ArrayList<>();

            for (int i = 0; i < SNAKE_BODY_SEGEMENTS; i++) {
                int currentX = rectX - DELTA * i;
                snakes.add(new SnakeBody(currentX, rectY, currentX + SNAKE_BODY_WIDTH, rectY + SNAKE_BODY_WIDTH));

            }
        }

    }

}
