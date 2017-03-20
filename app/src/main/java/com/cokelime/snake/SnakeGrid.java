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
import java.util.LinkedList;

import static android.content.ContentValues.TAG;

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

    private LinkedList<Rect> snakes;

    private boolean firstRun = true;

    public SnakeGrid(Context context) {
        super(context);
        rect = new Rect();
        paint = new Paint();

        paint.setColor(Color.BLACK);
        snakes = new LinkedList<>();
    }

    public SnakeGrid(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);
        rect = new Rect();
        paint = new Paint();


        paint.setColor(Color.BLACK);
        snakes = new LinkedList<>();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //initialize
        if (firstRun) {
            for (int i = 0; i < 5; i++) {
                int currentX = rectX - DELTA * i;
                rect = new Rect(currentX, rectY, currentX + 10, rectY + 10);
                snakes.addLast(rect);
                String log = MessageFormat.format("X: {0}, Y: {1}, currentX: {2}", rectX, rectY, currentX);
                Log.d(TAG, log);
                canvas.drawRect(rect, paint);
            }
            firstRun = false;
        } else {
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
                    rect.set(rectX, rectY, rectX + 10, rectY + 10);
                    snakes.set(i, rect);
                    canvas.drawRect(rect, paint);
                    log = MessageFormat.format("currI= {2} X: {0}, Y: {1} Right: {3}", rect.left, rectY, i, rect.right);
                }
                Log.d(TAG, log);
            }
        }


        //update


        try {
            Thread.sleep(1000);
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


    public enum LastMove {
        up, down, left, right
    }
}
