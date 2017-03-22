package com.cokelime.snake;

import android.graphics.Rect;

/**
 * Created by Yichi.Chen on 3/20/2017.
 */

public class SnakeBody {
    private int left;
    private int top;
    private int right;
    private int down;
    private Rect rect;

    public SnakeBody(int l, int t, int r, int d){
        this.left = l;
        this.top = t;
        this.right = r;
        this.down = d;
        rect = new Rect(left,top,right,down);
    }

    public Rect getRect() {
        return rect;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
        updateRect();
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
        updateRect();
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
        updateRect();
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
        updateRect();
    }

    private void updateRect(){
        rect.set(left,top,right,down);
    }

}
