package com.jumcoder.rentalmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Rent extends AppCompatActivity {

    private CardView cardView1, cardView2, cardView3, cardView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        cardView1 = findViewById(R.id.collectRentCardView);
        cardView2 = findViewById(R.id.returnRentCardView);
        cardView3 = findViewById(R.id.pendingRentCardView);
        cardView4 = findViewById(R.id.receivedRentCardView);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rent.this, CollectRent.class);
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rent.this, ReturnRent.class);
                startActivity(intent);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rent.this, PendingRent.class);
                startActivity(intent);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rent.this, ReceivedRent.class);
                startActivity(intent);
            }
        });
    }
}