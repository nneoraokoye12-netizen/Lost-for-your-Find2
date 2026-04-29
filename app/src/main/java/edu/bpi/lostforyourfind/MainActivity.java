package edu.bpi.lostforyourfind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView outerwearEmoji;
    private TextView accessoriesEmoji;
    private TextView electronicsEmoji;
    private TextView waterBottlesEmoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind all 4 emoji TextViews using their IDs from the XML
        outerwearEmoji   = findViewById(R.id.outerwear);
        accessoriesEmoji = findViewById(R.id.accessories);
        electronicsEmoji = findViewById(R.id.electronics);
        waterBottlesEmoji = findViewById(R.id.waterbottles);

        // Outerwear emoji → Outerwear page
        outerwearEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Outerwear.class);
                startActivity(intent);
            }
        });

        // Accessories emoji → Accessories page
        accessoriesEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Accessories.class);
                startActivity(intent);
            }
        });

        // Electronics emoji → Electronics page
        electronicsEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Electronics.class);
                startActivity(intent);
            }
        });

        // Water Bottles emoji → WaterBottles page
        waterBottlesEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Waterbottles.class);
                startActivity(intent);
            }
        });
    }
}