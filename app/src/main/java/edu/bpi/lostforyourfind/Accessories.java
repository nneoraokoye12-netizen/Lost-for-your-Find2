package edu.bpi.lostforyourfind;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Accessories extends AppCompatActivity {

    private CardView item1, item2, item3, item4, item5;

    // { name, date, location, emoji, pickupRoom }
    private final String[][] items = {
            { "Blue JanSport Backpack", "March 17, 2025", "PE Hallway",  "🎒", "105" },
            { "Set of House Keys",      "March 19, 2025", "Library",     "🔑", "106" },
            { "Black Sunglasses",       "March 20, 2025", "Cafeteria",   "🕶️", "107" },
            { "Silver Necklace",        "March 21, 2025", "Stairwell B", "📿", "108" },
            { "Beige Crossbody Bag",    "March 22, 2025", "Room 105",    "👜", "109" }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);

        // Back button
        TextView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);
        item4 = findViewById(R.id.item4);
        item5 = findViewById(R.id.item5);

        //entrance animation
        animateCardsIn();

        // Click listeners
        item1.setOnClickListener(v -> showClaimDialog(items[0]));
        item2.setOnClickListener(v -> showClaimDialog(items[1]));
        item3.setOnClickListener(v -> showClaimDialog(items[2]));
        item4.setOnClickListener(v -> showClaimDialog(items[3]));
        item5.setOnClickListener(v -> showClaimDialog(items[4]));
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

        TextView tvEmoji    = dialog.findViewById(R.id.dialogEmoji);
        TextView tvTitle    = dialog.findViewById(R.id.dialogTitle);
        TextView tvItemName = dialog.findViewById(R.id.dialogItemName);
        TextView tvDate     = dialog.findViewById(R.id.dialogDate);
        TextView tvLocation = dialog.findViewById(R.id.dialogLocation);
        Button   btnClaim   = dialog.findViewById(R.id.btnClaim);
        Button   btnDismiss = dialog.findViewById(R.id.btnDismiss);

        tvEmoji.setText(emoji);
        tvItemName.setText(name);
        tvDate.setText(date);
        tvLocation.setText(location);

        btnClaim.setOnClickListener(v -> {
            tvTitle.animate()
                    .alpha(0f)
                    .setDuration(180)
                    .withEndAction(() -> {
                        tvTitle.setText("Go to Room " + room + " for your item!");
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
        CardView[] cards = { item1, item2, item3, item4, item5 };
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
