package edu.bpi.lostforyourfind;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Waterbottles extends AppCompatActivity {

    private CardView item1, item2, item3, item4;

    // { name, date, location, emoji, pickupRoom }
    private final String[][] items = {
            { "Green Hydro Flask",      "March 17, 2025", "Gymnasium",  "🧴", "301" },
            { "Pink Stanley Tumbler",   "March 19, 2025", "Cafeteria",  "🥤", "302" },
            { "Clear Nalgene Bottle",   "March 20, 2025", "Room 215",   "💧", "303" },
            { "Gray Contigo Travel Mug","March 21, 2025", "Main Office","☕", "304" }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterbottles);

        // Back button
        TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Bind cards
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);

        // Staggered entrance animation
        animateCardsIn();

        // Click listeners
        item1.setOnClickListener(v -> showClaimDialog(items[0]));
        item2.setOnClickListener(v -> showClaimDialog(items[1]));
        item3.setOnClickListener(v -> showClaimDialog(items[2]));
        item4.setOnClickListener(v -> showClaimDialog(items[3]));
    }

    private void showClaimDialog(String[] itemData) {
        String name     = itemData[0];
        String date     = itemData[1];
        String location = itemData[2];
        String emoji    = itemData[3];
        String room     = itemData[4];

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_claim_item);
        dialog.setCancelable(true);

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

        // Populate
        tvEmoji.setText(emoji);
        tvItemName.setText(name);
        tvDate.setText(date);
        tvLocation.setText(location);

        // CLAIM → swap to success message
        btnClaim.setOnClickListener(v -> {
            tvTitle.animate()
                    .alpha(0f)
                    .setDuration(180)
                    .withEndAction(() -> {
                        tvTitle.setText("✅ Successful! Go to Room " + room + " for your item!");
                        //tvTitle.setTextColor(getColor(R.color.claim_green));
                        tvTitle.animate().alpha(1f).setDuration(180).start();
                    })
                    .start();

            tvItemName.setText("Head to Room " + room + " with your student ID.");
            btnClaim.setVisibility(View.GONE);
            btnDismiss.setText("Got it! 👍");
            //btnDismiss.setBackgroundResource(R.drawable.btn_claim_bg);
            btnDismiss.setTextColor(getColor(android.R.color.white));
        });

        // DISMISS → close
        btnDismiss.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
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