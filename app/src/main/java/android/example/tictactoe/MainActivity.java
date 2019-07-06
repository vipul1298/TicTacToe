package android.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tv,p1,p2;
    int i, j;
    int roundCount=0;
    int count1=0;
    int count2=0;
    boolean win1=false;
    Button rst,rest;
    Button buttons[][] = new Button[3][3];
    boolean player1turn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textview);
        p1=findViewById(R.id.player1);
        p2=findViewById(R.id.player2);
        rst=findViewById(R.id.reset);
        rest=findViewById(R.id.restart);
        for (i = 0; i < 3; i++) {
            for (j = 0 ;j < 3; j++) {
                String buttonID = "button_" + i + j;
                int ResId = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(ResId);
                buttons[i][j].setOnClickListener(this);
            }
        }

        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                tv.setText(null);
                roundCount=0;
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScore();
            }
        });



    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if (player1turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        roundCount++;

        if (checkWin()) {
            if (player1turn) {
                player1wins();
                update();
            } else {
                player2wins();
                update();
            }

//            resetGame();
        }
       else if (roundCount == 9) {
            draw();
        }
        else {
            player1turn = !player1turn;
        }


    }


    private void resetGame () {
        player1turn = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setText("");
            }
        }
    }

    private boolean checkWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {

            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) &&
                    field[0][i].equals(field[2][i]) &&
                    !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }


        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }



    private void player1wins () {
        tv.setText("Player one wins");
        win1=true;
    }

    private void player2wins () {
        tv.setText("Player two wins");
        win1=false;
    }

    private void draw () {
        tv.setText("Match Draw");
    }

    private void update(){
        if(win1){
            count1++;
            p1.setText("Player1 Score: "+count1);
        }else{
            count2++;
            p2.setText("Player2 Score: "+count2);
        }
    }
    private void resetScore(){
        count1=0;
        count2=0;
        p1.setText("Player1 Score: ");
        p2.setText("Player2 Score: ");
        tv.setText(null);
    }


}
