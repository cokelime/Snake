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

    public SnakeBody(){}

    public SnakeBody(Rect rect, int l, int t, int r, int d){
        this.rect = rect;
        this.left = l;
        this.top = t;
        this.right = r;
        this.down = d;
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }
}
