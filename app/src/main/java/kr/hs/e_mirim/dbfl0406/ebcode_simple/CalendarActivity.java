package kr.hs.e_mirim.dbfl0406.ebcode_simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 유리 on 2016-07-25.
 */
public class CalendarActivity extends Activity
implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ImageView upClick = (ImageView)findViewById(R.id.upButton);
        upClick.setOnClickListener(this);

        ImageView downClick = (ImageView)findViewById(R.id.downButton);
        downClick.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch(view.getId()){
            case R.id.upButton:
                Intent up = new Intent(this, CalendarActivity.class);
                startActivity(up);
                break;
            case R.id.downButton:
                Intent down = new Intent(this, Calendar8Activity.class);
                startActivity(down);
                break;
        }

    }
}
