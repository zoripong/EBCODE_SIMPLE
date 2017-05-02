package kr.hs.e_mirim.dbfl0406.ebcode_simple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView settings = (ImageView)findViewById(R.id.settingsButton);
        settings.setOnClickListener(this);

        ImageView shelf = (ImageView)findViewById(R.id.bookshelfButton);
        shelf.setOnClickListener(this);

        ImageView calendar = (ImageView)findViewById(R.id.calendarButton);
        calendar.setOnClickListener(this);

        ImageView booklet = (ImageView)findViewById(R.id.bookletButton);
        booklet.setOnClickListener(this);

        ImageView info = (ImageView)findViewById(R.id.info);
        info.setOnClickListener(this);


    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.settingsButton:
                Intent settingsintent = new Intent(this, SettingsActivity.class);
                startActivity(settingsintent);
                break;
            case R.id.bookshelfButton:
                Intent Bookintent = new Intent(this, BookActivity.class);
                startActivity(Bookintent);
                break;
            case R.id.bookletButton:
                Intent Bookmarkintent = new Intent(this, BookMarkActivity.class);
                startActivity(Bookmarkintent);
                break;
            case R.id.calendarButton:
                Intent calendarintent = new Intent(this, CalendarActivity.class);
                startActivity(calendarintent);
                break;
            case R.id.info:
                Intent infointent = new Intent(this, InfoActivity.class);
                startActivity(infointent);
                break;

        }
    }
}
