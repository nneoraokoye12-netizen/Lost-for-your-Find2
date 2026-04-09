package edu.bpi.lostforyourfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView cardOuterwear;
    private CardView cardAccessories;
    private CardView cardElectronics;
    private CardView cardWaterBottles;

    private TextView outerwearEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize
        outerwearEmoji = findViewById(R.id.outerwear);

        // When the outerwear emoji is tapped, go to the Outerwear page
        outerwearEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Outerwear.class);
                startActivity(intent);
            }
        });
    }
}