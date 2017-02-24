package com.cokelime.snake;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import static android.content.ContentValues.TAG;

public class Main extends Activity implements View.OnTouchListener{

    private SnakeGrid grid;
    private GestureDetector detector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = (SnakeGrid) findViewById(R.id.grid);

        detector = new GestureDetector(this, onSwipeListener);
        grid.setOnTouchListener(this);

        Button rightButton = (Button) findViewById(R.id.rightButton);

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid.moveRight();
            }
        });

        Button leftButton = (Button) findViewById(R.id.leftButton);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid.moveLeft();
            }
        });

        Button upButton = (Button) findViewById(R.id.upButton);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid.moveUp();
            }
        });

        Button downButton = (Button) findViewById(R.id.downButton);

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid.moveDown();
            }
        });

    }

    //http://stackoverflow.com/questions/13095494/how-to-detect-swipe-direction-between-left-right-and-up-down
    //from this answer http://stackoverflow.com/a/26387629
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //Log.d(TAG, "onTouch: log event here jc");
        detector.onTouchEvent(motionEvent);
        return true;
    }

    private OnSwipeListener onSwipeListener = new OnSwipeListener() {

        @Override
        public boolean onSwipe(Direction direction) {
            Log.d(TAG, "onSwipe");
            if (direction == Direction.left){
                Log.d(TAG, "onSwipe: log event here left");
                if(grid.getAction() != SnakeGrid.LastMove.right) {
                    grid.moveLeft();
                    grid.setAction(SnakeGrid.LastMove.left);
                    return true;
                }
            }
            else if (direction == Direction.right){
                Log.d(TAG, "onSwipe: log event here right");
                if(grid.getAction() != SnakeGrid.LastMove.left) {
                    grid.moveRight();
                    grid.setAction(SnakeGrid.LastMove.right);
                    return true;
                }
            }
            else if (direction == Direction.up){
                Log.d(TAG, "onSwipe: log event here up");
                if(grid.getAction() != SnakeGrid.LastMove.down) {
                    grid.moveUp();
                    grid.setAction(SnakeGrid.LastMove.up);
                    return true;
                }
            }
            else if (direction == Direction.down){
                Log.d(TAG, "onSwipe: log event here down");
                if(grid.getAction() != SnakeGrid.LastMove.up) {
                    grid.moveDown();
                    grid.setAction(SnakeGrid.LastMove.down);
                    return true;
                }
            }

            return super.onSwipe(direction);
        }
    };


}
