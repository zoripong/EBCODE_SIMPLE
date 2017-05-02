package kr.hs.e_mirim.dbfl0406.ebcode_simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by 유리 on 2016-07-25.
 */
public class LandingActivity extends Activity {

    public static String TAG = "랜딩액티비티";

    Handler mh = new Handler() {
        public void handleMessage(Message msg) {

            Log.e(TAG, "랜딩 액티비티 시작!");

            Log.e(TAG, "인텐트 시작!");

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Log.e(TAG, "인텐트 잘 넘어갔음!");
            finish();

            Log.e(TAG, "자폭!!!");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Runnable task = new Runnable(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}

                mh.sendEmptyMessage(1);

            }

        };

        Thread thread = new Thread(task);
        thread.start();


    }
}
