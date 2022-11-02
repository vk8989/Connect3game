package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    //0--> x, 1-->o, 2-->empty

    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer=0;
    boolean gameActive=true;
    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive){
        gameState[tappedCounter]=activePlayer;
        counter.setTranslationY(-1500);
        if(activePlayer== 0) {
            counter.setImageResource(R.drawable.xview);
            activePlayer=1;
        } else {
            counter.setImageResource(R.drawable.opp);
            activePlayer=0;
        }
        counter.animate().translationYBy(1500).setDuration(300);
        for (int[] winningPosition : winningPositions){
            if (gameState[winningPosition[0]]== gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]]&&gameState[winningPosition[0]]!=2){
                //someone has won!
                gameActive=false;
                String winner="";
                if (activePlayer==1){
                    winner="x";
                }else{
                    winner="0";
                }
                Toast.makeText(this, winner+ " has won!", Toast.LENGTH_SHORT).show();
                Button playAgainButton=(Button) findViewById(R.id.playagainButton);
                TextView textView=(TextView) findViewById(R.id.textView);
                textView.setText(winner+ " has won!");
                playAgainButton.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        }
        }
    }
    public void playAgain(View view){
        Button playAgainButton=(Button) findViewById(R.id.playagainButton);
        TextView textView=(TextView) findViewById(R.id.textView);
        playAgainButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }

        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
         activePlayer=0;
         gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}