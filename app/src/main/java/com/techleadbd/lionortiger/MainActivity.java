package com.techleadbd.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player{
        ONE, TWO, No
    }
    Player currentPlayer = Player.ONE;
    Player[] playerChoice = new Player[9];

    int[][] winnerRowsColumns = {{0, 1, 2},{3, 4, 5},{6, 7, 8},
            {0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},{2, 4, 6}};
    private Button btnReset;
    private boolean gameOver = false;
    private GridLayout mGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*playerChoice[0] = Player.No;
        playerChoice[1] = Player.No;
        playerChoice[2] = Player.No;
        playerChoice[3] = Player.No;
        playerChoice[4] = Player.No;
        playerChoice[5] = Player.No;
        playerChoice[6] = Player.No;
        playerChoice[7] = Player.No;
        playerChoice[8] = Player.No;*/

        for (int index = 0; index < playerChoice.length; index++){
            playerChoice[index] = Player.No;
        }

        btnReset = findViewById(R.id.btnReset);
        mGridLayout = findViewById(R.id.gridlayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });

    }


    public void imageViewIsTapped(View imageView){
        ImageView tappedImageView = (ImageView) imageView;

        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());
        if(playerChoice[tiTag] == Player.No && gameOver == false) {

            tappedImageView.setTranslationX(-2000);

            playerChoice[tiTag] = currentPlayer;

            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.apple);
                currentPlayer = Player.TWO;

            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tometo);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);

            //Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoice[winnerColumns[0]] ==
                        playerChoice[winnerColumns[1]] &&
                        playerChoice[winnerColumns[1]] ==
                                playerChoice[winnerColumns[2]] && playerChoice[winnerColumns[0]] != Player.No) {
                    btnReset.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winnerOfGame = "";

                    if (currentPlayer == Player.ONE) {
                        winnerOfGame = "Player TWO";

                    } else if (currentPlayer == Player.TWO) {
                        winnerOfGame = "Player ONE";
                    }

                    Toast.makeText(this, winnerOfGame + " is Winner", Toast.LENGTH_SHORT).show();

                }
            }
        }

    }
    //Reset Game Function

    private void resetTheGame(){

        for(int index = 0; index < mGridLayout.getChildCount(); index++){
            ImageView imageView = (ImageView) mGridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }


          currentPlayer = Player.ONE;

        /*playerChoice[0] = Player.No;
        playerChoice[1] = Player.No;
        playerChoice[2] = Player.No;
        playerChoice[3] = Player.No;
        playerChoice[4] = Player.No;
        playerChoice[5] = Player.No;
        playerChoice[6] = Player.No;
        playerChoice[7] = Player.No;
        playerChoice[8] = Player.No;*/

        for (int index = 0; index < playerChoice.length; index++){
            playerChoice[index] = Player.No;
        }

        gameOver = false;
        btnReset.setVisibility(View.INVISIBLE);

    }
}
