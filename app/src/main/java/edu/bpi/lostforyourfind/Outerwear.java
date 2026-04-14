package edu.bpi.lostforyourfind;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Outerwear extends AppCompatActivity {

    // The 4 item cards
    private CardView item1, item2, item3, item4;

    // Item data: name, date, location, emoji, pickupRoom
    private final String[][] items = {
            { "Navy Puffer Jacket",        "March 18, 2025", "1st Floor Hallway", "🧥", "101" },
            { "Gray Nike Hoodie",          "March 20, 2025", "Gymnasium",         "👕", "102" },
            { "Black Rain Jacket",         "March 21, 2025", "Main Entrance",     "🧥", "103" },
            { "Red & White Varsity Jacket","March 22, 2025", "Cafeteria",         "🧣", "104" }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outerwear);

        // Back button
        TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);

        item1.setOnClickListener(v -> showClaimDialog(items[0]));
        item2.setOnClickListener(v -> showClaimDialog(items[1]));
        item3.setOnClickListener(v -> showClaimDialog(items[2]));
        item4.setOnClickListener(v -> showClaimDialog(items[3]));
    }

    // Popup dialog
    private void showClaimDialog(String[] itemData) {
        // itemData = { name, date, location, emoji, pickupRoom }
        String name     = itemData[0];
        String date     = itemData[1];
        String location = itemData[2];
        String emoji    = itemData[3];
        String room     = itemData[4];

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_claim_item);
        dialog.setCancelable(true);

        // Make dialog corners round (transparent window background)
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            android.view.WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
            dialog.getWindow().setAttributes(lp);
        }

        // Wire up views
        TextView tvEmoji    = dialog.findViewById(R.id.dialogEmoji);
        TextView tvTitle    = dialog.findViewById(R.id.dialogTitle);
        TextView tvItemName = dialog.findViewById(R.id.dialogItemName);
        TextView tvDate     = dialog.findViewById(R.id.dialogDate);
        TextView tvLocation = dialog.findViewById(R.id.dialogLocation);
        Button   btnClaim   = dialog.findViewById(R.id.btnClaim);
        Button   btnDismiss = dialog.findViewById(R.id.btnDismiss);

        // Populate data
        tvEmoji.setText(emoji);
        tvItemName.setText(name);
        tvDate.setText(date);
        tvLocation.setText(location);


        btnClaim.setOnClickListener(v -> {
            // Animate the title swap
            tvTitle.animate()
                    .alpha(0f)
                    .setDuration(180)
                    .withEndAction(() -> {
                        tvTitle.setText("Go to Room " + room + " for your item!");
                        tvTitle.animate().alpha(1f).setDuration(180).start();
                    })
                    .start();

            // Update subtitle
            tvItemName.setText("Head to Room " + room + " with your student ID.");

            // Swap buttons: hide Claim, rename Dismiss to "Got it!"
            btnClaim.setVisibility(View.GONE);
            btnDismiss.setText("Got it! 👍");
            btnDismiss.setTextColor(getColor(android.R.color.white));
        });

        btnDismiss.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();

        // Slide-up entrance for the dialog
        if (dialog.getWindow() != null) {
            dialog.getWindow().getDecorView()
                    .animate()
                    .translationY(0f)
                    .alpha(1f)
                    .setDuration(250)
                    .start();
        }
    }

    private void animateCardsIn() {
        CardView[] cards = { item1, item2, item3, item4 };
        for (int i = 0; i < cards.length; i++) {
            final CardView card = cards[i];
            card.setAlpha(0f);
            card.setTranslationY(50f);
            card.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(350)
                    .setStartDelay(120 + (i * 90L))
                    .start();
        }
    }

}