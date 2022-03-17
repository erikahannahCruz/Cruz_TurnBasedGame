package com.example.cruz_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //creating objects
    TextView txtHeroHP , txtHeroDamage , txtEnemyHP , txtEnemyDamage , txtTurnCount , txtGameLog;
    Button btnNextTurn ;

    //Hero Stats
    int heroHP = 1000 ;
    int heroDamage = 200 ;

    //Enemy Stats
    int enemyHP = 3000 ;
    int enemyDamage = 50 ;

    // Turn Count
    int turnCount = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //stuff changed to hide title banner upon startup
        requestWindowFeature(Window.FEATURE_NO_TITLE);
            getSupportActionBar().hide();
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //HOPE THIS WORKS OH MY GOD

        //initially this was just under the super.OnCreate method
        setContentView(R.layout.activity_main);

        //set the xml stuff to the stuff here
        txtHeroHP = findViewById(R.id.txtHeroHP) ;
        txtHeroDamage = findViewById(R.id.txtHeroDamage) ;
        txtEnemyHP = findViewById(R.id.txtEnemyHP) ;
        txtEnemyDamage = findViewById(R.id.txtEnemyDamage) ;
        txtTurnCount = findViewById(R.id.txtTurnCount) ;
        txtGameLog = findViewById(R.id.txtGameLog) ;
        btnNextTurn = findViewById(R.id.btnNextTurn) ;

        // set the text views upon startup
        txtHeroHP.setText(String.valueOf(heroHP + "/1000")) ;
        txtHeroDamage.setText(String.valueOf(heroDamage + " DMG")) ;
        txtEnemyHP.setText(String.valueOf(enemyHP + "/3000")) ;
        txtEnemyDamage.setText(String.valueOf(enemyDamage + " DMG")) ;
        txtTurnCount.setText("Turn #" + String.valueOf(turnCount)) ;

        // setting listener for button
        btnNextTurn.setOnClickListener(this) ;

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnNextTurn:

                /*hero's turn*/
                if(turnCount % 2 == 0) {

                    //for attacking
                    enemyHP = enemyHP - heroDamage ;
                    turnCount++ ;
                    txtEnemyHP.setText(String.valueOf(enemyHP + "/3000")) ;
                    txtTurnCount.setText("Turn #" + String.valueOf(turnCount)) ;
                    txtGameLog.setText("Hero deals " + String.valueOf(heroDamage) + "\ndamage to the enemy." ) ;
                    btnNextTurn.setText("Enemy's turn") ;

                    //Win condition
                    if (enemyHP <= 0) {
                        txtGameLog.setText("Hero deals " + String.valueOf(heroDamage) + "\ndamage to the enemy. You Win!" ) ;
                        btnNextTurn.setText("Retry?") ;
                        //Reset variables
                        heroHP = 1000 ;
                        enemyHP = 3000 ;
                        turnCount = 0 ;
                        //lock buttons ADD KA PA NG CODE
                    }
                    else {}

                }

                /*Enemy's turn*/
                else if (turnCount % 2 == 1) {
                    //during attacking
                    heroHP = heroHP - enemyDamage ;
                    turnCount++ ;
                    txtHeroHP.setText(String.valueOf(heroHP + "/1000")) ;
                    txtTurnCount.setText("Turn #" + String.valueOf(turnCount)) ;
                    txtGameLog.setText(String.valueOf("Enemy deals " + String.valueOf(enemyDamage) + "\ndamage to the hero.")) ;
                    btnNextTurn.setText("Hero's turn") ;

                    //Lose Condition
                    if (heroHP <= 0) {
                        txtGameLog.setText(String.valueOf("Enemy deals " + String.valueOf(enemyDamage) + "\ndamage to the hero. You Lose!")) ;
                        btnNextTurn.setText("Retry?") ;
                        //Reset variables
                        heroHP = 1000 ;
                        enemyHP = 3000 ;
                        turnCount = 0 ;
                        //lock buttons ADD KA PA NG CODE
                    }
                    else {}
                }

                break;
        }

    }

}