package kr.hs.e_mirim.dbfl0406.ebcode_simple;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by 유리 on 2016-08-12.
 */
public class CropImageView extends Activity implements View.OnClickListener {

    Intent intent; // 받아온 인텐트 변수
    Bitmap IntentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);


//        IntentImage = (Bitmap)intent.getExtras().get("ImageView");
//
//        imgView.setImageBitmap(IntentImage);

        ImageView crop_image = (ImageView)findViewById(R.id.CropView);
        Button redBtn = (Button)findViewById(R.id.redColorScan);
        Button blueBtn = (Button)findViewById(R.id.blueColorScan);
        Button greenBtn = (Button)findViewById(R.id.greenColorScan);
        Button yellowBtn = (Button)findViewById(R.id.yellowColorScan);
        Button allBtn = (Button)findViewById(R.id.allColorScan);

        redBtn.setOnClickListener(this);
        blueBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        yellowBtn.setOnClickListener(this);
        allBtn.setOnClickListener(this);

        intent = getIntent();
        IntentImage = (Bitmap) intent.getExtras().get("Image");
        crop_image.setImageBitmap(IntentImage);



    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.redColorScan:
                createThreadAndDialog();
                intent = new Intent(this, BookDetailActivity.class);
                intent.putExtra("all",-2);
                startActivity(intent);
                break;
            case R.id.blueColorScan:
                createThreadAndDialog();
                intent = new Intent(this, BookDetailActivity.class);
                intent.putExtra("all", -4);
                startActivity(intent);
                break;
            case R.id.greenColorScan:
                createThreadAndDialog();
                intent = new Intent(this, BookDetailActivity.class);
                intent.putExtra("all", -3);
                startActivity(intent);
                break;
            case R.id.yellowColorScan:
                createThreadAndDialog();
                intent = new Intent(this, BookDetailActivity.class);
                intent.putExtra("all", -1);
                startActivity(intent);
                break;
            case R.id.allColorScan:
                createThreadAndDialog();
                intent = new Intent(this, BookDetailActivity.class);
//                intent.putExtra("all",allIntentImage);
                intent.putExtra("all", -5);
                startActivity(intent);
                break;
        }

    }
    private ProgressDialog loagindDialog;
    private void createThreadAndDialog() {

        loagindDialog = ProgressDialog.show(this, "로딩 중",
                "잠시만 기다려 주세요...", true, false);

        Thread thread = new Thread(new Runnable() {
            private static final int LOADING_TIME = 3000;
            @Override
            public void run() {
                // 시간걸리는 처리
                handler.sendEmptyMessageDelayed(0, LOADING_TIME);
            }
        });
        thread.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            loagindDialog.dismiss(); // 다이얼로그 삭제
            // View갱신
        }
    };
}
