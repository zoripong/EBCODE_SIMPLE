package kr.hs.e_mirim.dbfl0406.ebcode_simple;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BookActivity extends Activity
    implements View.OnClickListener {

    private boolean BooleanStar;
    ImageView ImageStar;
    String IntentStringNull = "안녕";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        ImageStar = (ImageView) findViewById(R.id.star1);
        ImageStar.setOnClickListener(this);

        ImageView appbutton = (ImageView) findViewById(R.id.appButton);
        appbutton.setOnClickListener(this);

        SharedPreferences pref = getSharedPreferences("PrefTest", MODE_PRIVATE);

        BooleanStar = pref.getBoolean("star", false);
        if(BooleanStar==true)ImageStar.setImageResource(R.drawable.yellowstar);
        else ImageStar.setImageResource(R.drawable.star);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.star1:
                this.BooleanStar = !this.BooleanStar;
                SharedPreferences pref = getSharedPreferences("PrefTest", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putBoolean("star", BooleanStar);
                edit.commit();

                if(BooleanStar==true)ImageStar.setImageResource(R.drawable.yellowstar);
                else ImageStar.setImageResource(R.drawable.star);

                break;

            case R.id.appButton:
                Intent appbook = new Intent(this, BookDetailActivity.class);
                appbook.putExtra("key", IntentStringNull);
                startActivity(appbook);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences pref = getSharedPreferences("PrefTest", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        this.BooleanStar = pref.getBoolean("star", false);
        edit.commit();
    }
}

