package com.cokelime.snake;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

    private SnakeGrid grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = (SnakeGrid) findViewById(R.id.grid);

        Button rightButton = (Button) findViewById(R.id.rightButton);

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grid.moveRight(5);
            }
        });

    }
}
