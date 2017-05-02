package kr.hs.e_mirim.dbfl0406.ebcode_simple;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 유리 on 2016-07-25.
 */
public class BookMarkActivity extends Activity implements View.OnClickListener{

    private int IntBookmarkPage;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        title = getString(R.string.bookmark);

        TextView texttest = (TextView)findViewById(R.id.texttest);

        SharedPreferences pref = getSharedPreferences("Pref", 0);
        IntBookmarkPage = pref.getInt("BookPage", 13);

        ImageView BookmarkPage = (ImageView)findViewById(R.id.appButton);
        BookmarkPage.setOnClickListener(this);

        BookmarkPage.setImageResource((R.drawable.app_page01+this.IntBookmarkPage));
        texttest.setText(title);
    }

    @Override
    public void onClick(View view) {
       Intent intent;
        switch (view.getId()){
           case R.id.appButton:
               intent = new Intent(this, BookDetailActivity.class);
               intent.putExtra("all", IntBookmarkPage);
               startActivity(intent);
               break;
       }
    }
}
