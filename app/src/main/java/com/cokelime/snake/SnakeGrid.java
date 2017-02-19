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

    Rect rect;

    private int xMax;
    private int yMax;

    private int rectScaleX;
    private int rectScaleY;

    private int rectX = 0;
    private int rectY = 0;

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

        rect.set(rectX , rectY, rectX + 10, rectY + 10);

        canvas.drawRect(rect, paint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        xMax = w-1;
        yMax = h-1;

        rectScaleX = xMax / 8;
        rectScaleY = yMax / 8;
    }

    public void moveRight(int amount){

        rectX += amount;

        invalidate();
    }
}
