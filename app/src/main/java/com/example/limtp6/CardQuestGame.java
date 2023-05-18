package com.example.limtp6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardQuestGame extends AppCompatActivity {

    private List<Card> cards = new ArrayList<>();
    private CardAdapter adapter;
    private GridView gridView;
    private int firstCardIndex = -1;
    private int secondCardIndex = -1;
    private boolean isProcessing = false; // Added flag to prevent simultaneous clicks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_quest_game);

        gridView = findViewById(R.id.gridView);
        gridView.setNumColumns(4);
        gridView.setHorizontalSpacing(8);
        gridView.setVerticalSpacing(8);

        ImageButton btnRestartGame = findViewById(R.id.btnRestartGame);
        btnRestartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isProcessing) {
                    return; // Ignore clicks during processing
                }

                Card card = cards.get(position);

                if (!card.isFaceUp()) {
                    if (firstCardIndex == -1) {
                        firstCardIndex = position;
                        card.setFaceUp(true);
                        adapter.notifyDataSetChanged();
                    } else if (secondCardIndex == -1 && firstCardIndex != position) {
                        secondCardIndex = position;
                        card.setFaceUp(true);
                        adapter.notifyDataSetChanged();
                        checkForMatch();
                    }
                }
            }
        });

        createCards();
        shuffleCards();
        adapter = new CardAdapter(cards);
        gridView.setAdapter(adapter);
    }

    public void backToTitleScreen(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void restartGame() {
        // Reset the necessary variables
        firstCardIndex = -1;
        secondCardIndex = -1;
        isProcessing = false;

        // Clear the existing cards from the grid view
        cards.clear();
        adapter.notifyDataSetChanged();

        // Shuffle the cards again
        createCards();
        shuffleCards();

        // Notify the adapter of the data change
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "New Game!", Toast.LENGTH_SHORT).show();
    }



    private void createCards() {
        cards.clear(); // Clear the existing cards list
        cards.add(new Card(1, R.drawable.card_1));
        cards.add(new Card(1, R.drawable.card_1));

        cards.add(new Card(2, R.drawable.card_2));
        cards.add(new Card(2, R.drawable.card_2));

        cards.add(new Card(3, R.drawable.card_3));
        cards.add(new Card(3, R.drawable.card_3));

        cards.add(new Card(4, R.drawable.card_4));
        cards.add(new Card(4, R.drawable.card_4));

        cards.add(new Card(5, R.drawable.card_5));
        cards.add(new Card(5, R.drawable.card_5));

        cards.add(new Card(6, R.drawable.card_6));
        cards.add(new Card(6, R.drawable.card_6));

        cards.add(new Card(7, R.drawable.card_7));
        cards.add(new Card(7, R.drawable.card_7));

        cards.add(new Card(8, R.drawable.card_8));
        cards.add(new Card(8, R.drawable.card_8));
    }


    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    private void checkForMatch() {
        final Card firstCard = cards.get(firstCardIndex);
        final Card secondCard = cards.get(secondCardIndex);

        if (firstCard.getImage() == secondCard.getImage()) {
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            adapter.notifyDataSetChanged();
            checkForGameEnd();
            firstCardIndex = -1;
            secondCardIndex = -1;
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    firstCard.setFaceUp(false);
                    secondCard.setFaceUp(false);
                    adapter.notifyDataSetChanged();
                    firstCardIndex = -1;
                    secondCardIndex = -1;
                }
            }, 1000);
        }
    }

    private void goToGameOver(){
        Intent intent = new Intent(this,GameOver.class);
        startActivity(intent);
    }
    private void checkForGameEnd() {
        for (Card card : cards) {
            if (!card.isFaceUp()) {
                return;
            }
        }

        Toast.makeText(this, "Game Over! Thank you for playing.", Toast.LENGTH_SHORT).show();
        goToGameOver();
    }
}
