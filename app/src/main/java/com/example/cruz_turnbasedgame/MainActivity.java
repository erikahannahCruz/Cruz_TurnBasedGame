package com.example.cruz_turnbasedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //creating objects
    TextView txtHeroHP , txtHeroDamage , txtEnemyHP , txtEnemyDamage , txtTurnCount , txtGameLog;
    Button btnNextTurn , btnHeal , btnIncreaseDMG , btnNewGame ;

    //Hero Stats
    int heroHP = 1000 ;
    int heroMinDamage = 150;
    int heroMaxDamage = 350;
    int heroMinHeal = 300 ;
    int heroMaxHeal= 850 ;
    int heroDamage ;
    Boolean heroDamageIncrease = false;

    //Enemy Stats
    int enemyHP = 3000 ;
    int enemyMinDamage = 50;
    int enemyMaxDamage = 250 ;
    int enemyDamage ;

    // Turn Count
    int turnCount = 0 ;

    //randomizer
    Random randomizer = new Random() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //stuff changed to hide title banner upon startup
        requestWindowFeature(Window.FEATURE_NO_TITLE);
            getSupportActionBar().hide();
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        btnHeal = findViewById(R.id.btnHeal) ;
        btnIncreaseDMG = findViewById(R.id.btnIncreaseDMG) ;
        btnNewGame = findViewById(R.id.btnNewGame) ;

        // set the text views upon startup
        txtHeroHP.setText(String.valueOf(heroHP + "/1000 HP")) ;
        txtHeroDamage.setText(String.valueOf(String.valueOf(heroMinDamage) + " DMG to\n" + String.valueOf(heroMaxDamage) + " DMG")) ;
        txtEnemyHP.setText(String.valueOf(enemyHP + "/3000 HP")) ;
        txtEnemyDamage.setText(String.valueOf(String.valueOf(enemyMinDamage) + " DMG to \n" + String.valueOf(enemyMaxDamage) + " DMG")) ;
        txtTurnCount.setText("Turn #" + String.valueOf(turnCount)) ;

        // setting listener for button
        btnNextTurn.setOnClickListener(this) ;
        btnHeal.setOnClickListener(this);
        btnIncreaseDMG.setOnClickListener(this);
        btnNewGame.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //NEXT TURN BUTTON
        switch(v.getId()) {
            case R.id.btnNextTurn:


                /*hero's turn*/
                if(turnCount % 2 == 0) {

                    heroDamage = randomizer.nextInt(heroMaxDamage - heroMinDamage) + heroMinDamage;

                    //for attacking
                    enemyHP = enemyHP - heroDamage ;
                    turnCount++ ;
                    txtEnemyHP.setText(String.valueOf(enemyHP + "/3000 HP")) ;
                    txtTurnCount.setText("Turn #" + String.valueOf(turnCount)) ;
                    txtGameLog.setText("Hero deals " + String.valueOf(heroDamage) + "\ndamage to the enemy." ) ;
                    btnNextTurn.setText("Enemy's turn") ;

                    btnNewGame.setEnabled(false);

                    //Disable the Heal and attack increase button
                    btnHeal.setEnabled(false) ;
                    btnIncreaseDMG.setEnabled(false) ;

                    //Win condition
                    if (enemyHP <= 0) {
                        txtGameLog.setText("Hero deals " + String.valueOf(heroDamage) + "\ndamage to the enemy. You Win!" ) ;
                        btnNextTurn.setText("Victory!") ;
                        //Reset variables
                        heroHP = 1000 ;
                        enemyHP = 3000 ;
                        turnCount = 0 ;
                        btnHeal.setEnabled(false) ;
                        btnIncreaseDMG.setEnabled(false) ;
                        btnNextTurn.setEnabled(false);
                        btnNewGame.setEnabled(true);

                    }
                    else {}

                }

                /*Enemy's turn*/
                else if (turnCount % 2 == 1) {

                    enemyDamage = randomizer.nextInt(enemyMaxDamage - enemyMinDamage) + enemyMinDamage ;

                    //during attacking
                    heroHP = heroHP - enemyDamage ;
                    turnCount++ ;
                    txtHeroHP.setText(String.valueOf(heroHP + "/1000 HP")) ;
                    txtTurnCount.setText("Turn #" + String.valueOf(turnCount)) ;
                    txtGameLog.setText(String.valueOf("Enemy deals " + String.valueOf(enemyDamage) + "\ndamage to the hero.")) ;
                    btnNextTurn.setText("Hero's turn") ;

                    btnNewGame.setEnabled(false);

                    //Enable the Heal and attack increase button
                    btnHeal.setEnabled(true) ;
                    btnIncreaseDMG.setEnabled(true) ;


                    //Critical hit indicator
                    if (enemyDamage >= 200) {

                        txtGameLog.setText(String.valueOf("Enemy deals " + String.valueOf(enemyDamage) + " damage \nto the hero. A critical hit!")) ;

                    }

                    else {}

                    //Lose Condition
                    if (heroHP <= 0) {
                        txtGameLog.setText(String.valueOf("Enemy deals " + String.valueOf(enemyDamage) + "\ndamage to the hero. You Lose!")) ;
                        btnNextTurn.setText("Defeat.") ;
                        btnHeal.setEnabled(false) ;
                        btnIncreaseDMG.setEnabled(false) ;
                        btnNextTurn.setEnabled(false);
                        btnNewGame.setEnabled(true);
                    }
                    else {}
                }

                break;
        }

        //HEAL BUTTON
        switch(v.getId()) {
            case R.id.btnHeal:

                //Hero Heal Randomizer
                int heroHeal = randomizer.nextInt(heroMaxHeal - heroMinHeal) + heroMinHeal;

                //Adds health to hero
                heroHP = heroHP + heroHeal;
                txtHeroHP.setText(String.valueOf(heroHP + "/1000 HP")) ;

                txtGameLog.setText("Hero heals " + heroHeal + " HP.");

                turnCount++ ;

                //Disable Button
                btnHeal.setEnabled(false) ;
                btnIncreaseDMG.setEnabled(false) ;
                btnNewGame.setEnabled(false);

                //Full recovery indicator
                if (heroHP == 1000) {
                    txtGameLog.setText("Hero heals " + heroHeal + " HP.\nA full recovery!");

                }

                else if (heroHP > 1000) {
                    txtGameLog.setText("Hero heals " + heroHeal + " HP.\nDivine recovery!");

                }

                else{}

        }

        //ATTACK INCREASE BUTTON
        switch(v.getId()) {
            case R.id.btnIncreaseDMG:


        }


        //RETRY BUTTON
        switch(v.getId()) {
            case R.id.btnNewGame:

                btnNextTurn.setText("First turn") ;

                heroHP = 1000 ;
                enemyHP = 3000 ;
                turnCount = 0 ;

                txtHeroHP.setText(String.valueOf(heroHP + "/1000 HP")) ;
                txtEnemyHP.setText(String.valueOf(enemyHP + "/3000 HP")) ;

                txtGameLog.setText(String.valueOf("Round Begins!")) ;
                txtTurnCount.setText("Turn #" + String.valueOf(turnCount)) ;

                btnHeal.setEnabled(true) ;
                btnIncreaseDMG.setEnabled(true) ;
                btnNextTurn.setEnabled(true);

        }

    }

}