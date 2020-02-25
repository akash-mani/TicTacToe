package com.example.tic_tac_toe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //x=0, o=1, empty=2
    int count=0;
    int[] arr = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] win_pos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    Boolean active = true;

    public void winner_check() {
        for (int[] pos : win_pos) {
            if (arr[pos[0]] == arr[pos[1]] && arr[pos[1]] == arr[pos[2]] && arr[pos[0]] != 2) {
                String winner = "";
                if (count==0) {
                    winner = "Player 2 (o)";
                }
                else{
                    winner = "Player 1 (x)";
                }
                active = false;
                Toast.makeText(this, winner + " has won! \nClick RESET to play again.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void play (View view) {
        ImageView image = (ImageView) view;
        Log.i("Box", image.getTag().toString());
        int tapped = Integer.parseInt(image.getTag().toString());
        if (arr[tapped] == 2 && active) {
            image.setTranslationY(-2000);
            if (count == 0) {
                image.setImageResource(R.drawable.x);
                arr[tapped] = 0;
                count = 1;
            } else {
                image.setImageResource(R.drawable.o);
                arr[tapped] = 1;
                count = 0;
            }
            image.animate().translationYBy(2000).rotationBy(360).setDuration(200);
            winner_check();

        }
    }

    public void reset(View view) {
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<grid.getChildCount(); i++) {
            ImageView image = (ImageView) grid.getChildAt(i);
            image.setImageDrawable(null);
        }
        active=true;
        count=0;
        for (int i=0; i<9; i++) {
            arr[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
