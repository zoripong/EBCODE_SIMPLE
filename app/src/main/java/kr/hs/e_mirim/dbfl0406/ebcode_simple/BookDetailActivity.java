
package kr.hs.e_mirim.dbfl0406.ebcode_simple;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BookDetailActivity extends Activity {

    private static final String TAG = "TestImageCropActivity";

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;

    private Uri mImageCaptureUri;
    private AlertDialog mDialog; // 여기까지 카메라를 위한 변수

    private int mNowPage = 0;  // 최소 페이지 수
    private int mMaxPage = 12; //최대 페이지 수
    Button mButtonPrev; // 이전장 버튼 변수명
    Button mButtonNext; // 다음장 버튼 변수명

    ImageView mImageViewPage; // 전체 책 페이지 이미지

    Button CaptureButton; // 카메라 버튼 변수명

    ImageView mButtonStar; // 즐겨찾기 변수명
    private boolean BooleanBookmark; // 클릭 상태 변수 명 (true - false)
    private int IntBookmarkPage;

    int BookmarkIntentPage = -1;//북마크 페이지로부터 받아온 페이지
    Bitmap BookmarkIntentImage; //북마크 페이지로부터 받아올 이미지
    String StringIntentNull; //북마크 페이지로부터 받아올 널값
    Intent FromIntent; // 받아올 인텐트들


    int allint; //스캔 칼라
    Intent fromintent; //받아올 인텐트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        mButtonPrev = (Button)findViewById(R.id.beforePage);
        mButtonNext = (Button)findViewById(R.id.nextpage);
        CaptureButton = (Button)findViewById(R.id.CaptureButton);
        mImageViewPage = (ImageView)findViewById(R.id.pageImageView);
        mButtonStar = (ImageView)findViewById(R.id.bookmarkButton);
        Button noteButton = (Button)findViewById(R.id.NoteButton);

        fromintent = getIntent();

        allint = fromintent.getExtras().getInt("all", 0);
        Log.e("하이", "all : "+allint);
        mImageViewPage.setImageResource((R.drawable.app_page01+allint));
        if(allint < 0) {
            mNowPage = allint + 12;
        }

        SharedPreferences pref = getSharedPreferences("Pref", 0);
        BooleanBookmark = pref.getBoolean("Bookmark", false);
        IntBookmarkPage = pref.getInt("BookPage", 13);

        if(BooleanBookmark == true && mNowPage==IntBookmarkPage ||allint == IntBookmarkPage) {
            mButtonStar.setImageResource(R.drawable.bookmark_ribbon);
        }
        else mButtonStar.setImageResource(R.drawable.bookmark_ribbon_opacity);

//        IntentUri = BitmapFactory.decodeResource(getResources(), R.drawable.imageview);

//
//        BookmarkIntentImage = BitmapFactory.decodeResource(getResources(), R.drawable.preview);//이미지 초기값
//        FromIntent = getIntent();
//
//        BookmarkIntentImage = (Bitmap)FromIntent.getExtras().get();
//        BookmarkIntentPage = (Integer)FromIntent.getExtras().get();
//        StringIntentNull = (String)FromIntent.getExtras().get("key");
//



    }

    //    public void mOnClick(View v){
//        Intent intent;
//        switch (v.getId()){
//            case R.id.CameraCallButton:
//                intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, CALL_CAMERA);
//                break;
//
//            case R.id.GalleryCallButton:
//                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent,CALL_GALLERY);
//                break;

    public void BtnClicked(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.beforePage:

                this.mNowPage--;
                if(this.mNowPage < 0)
                {
                    Toast.makeText(this, "첫 번째 페이지 입니다.", Toast.LENGTH_SHORT).show();
                    mNowPage = 0;
                }
                else{
                    mImageViewPage.setImageResource((R.drawable.app_page01+this.mNowPage));
                    SharedPreferences pref = getSharedPreferences("Pref", 0);
                    BooleanBookmark = pref.getBoolean("Bookmark", false);
                    IntBookmarkPage = pref.getInt("BookPage", 13);

                    if(BooleanBookmark == true && mNowPage==IntBookmarkPage) mButtonStar.setImageResource(R.drawable.bookmark_ribbon);
                    else mButtonStar.setImageResource(R.drawable.bookmark_ribbon_opacity);

                }
                break;

            case R.id.nextpage:
                this.mNowPage++;
                if(this.mNowPage > this.mMaxPage)
                {
                    Toast.makeText(this, "마지막 페이지 입니다.", Toast.LENGTH_SHORT).show();
                    mNowPage = this.mMaxPage;
                }
                else{
                    mImageViewPage.setImageResource((R.drawable.app_page01+this.mNowPage));
                    SharedPreferences pref = getSharedPreferences("Pref", 0);
                    BooleanBookmark = pref.getBoolean("Bookmark", false);
                    IntBookmarkPage = pref.getInt("BookPage", 13);

                    if(BooleanBookmark == true && mNowPage==IntBookmarkPage) mButtonStar.setImageResource(R.drawable.bookmark_ribbon);
                    else mButtonStar.setImageResource(R.drawable.bookmark_ribbon_opacity);

                }
                break;

            case R.id.bookmarkButton:

                this.BooleanBookmark = !this.BooleanBookmark;
                IntBookmarkPage = mNowPage;

                SharedPreferences pref = getSharedPreferences("Pref", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putBoolean("Bookmark", BooleanBookmark);
                edit.putInt("BookPage", IntBookmarkPage);
                edit.commit();

                if(BooleanBookmark==true && mNowPage == IntBookmarkPage)mButtonStar.setImageResource(R.drawable.bookmark_ribbon);
                else mButtonStar.setImageResource(R.drawable.bookmark_ribbon_opacity);

                break;

            case R.id.NoteButton:

                intent = new Intent(this, NoteActivity.class);
                startActivity(intent);
                break;

            case R.id.CaptureButton:
                mDialog = createDialog();
                mDialog.show();
                break;
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences pref = getSharedPreferences("Pref", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        this.BooleanBookmark = pref.getBoolean("Bookmark", false);
        this.IntBookmarkPage = pref.getInt("BookPage", 13);
        edit.commit();

    }

    private AlertDialog createDialog() {
        final View innerView = getLayoutInflater().inflate(R.layout.image_crop_row, null);

        Button camera = (Button)innerView.findViewById(R.id.btn_camera_crop);
        Button gellary = (Button)innerView.findViewById(R.id.btn_gellary_crop);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doTakePhotoAction();
                setDismiss(mDialog);
            }
        });

        gellary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                doTakeAlbumAction();
                setDismiss(mDialog);
            }
        });

        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("이미지 Crop");
        ab.setView(innerView);

        return  ab.create();
    }


    private void setDismiss(AlertDialog dialog){
        if(dialog!=null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    private void doTakePhotoAction() {

        Log.i(TAG, "doTakePhotoAction()");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mImageCaptureUri = createSaveCropFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    private void doTakeAlbumAction() {

        Log.i(TAG, "doTakeAlbumAction()");

//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG,"resultCode 값 :"+resultCode+"RESULT_OK값 :"+RESULT_OK);
        Log.d(TAG, "onActivityResult");
//        if(resultCode != RESULT_OK){
//            Log.e(TAG, "왜 안되는지 알아버렸다.");
//            return;
//        }
        switch(requestCode){
            case PICK_FROM_ALBUM:
            {
                Log.d(TAG, "PICK_FROM_ALBUM");
                mImageCaptureUri = data.getData();
//                Log.d(TAG, "PICK_FROM_ALBUM-1");
//                File oringinal_file = getImageFile(mImageCaptureUri);
//                Log.d(TAG, "PICK_FROM_ALBUM-2");
//
//                mImageCaptureUri = createSaveCropFile();
//                Log.d(TAG, "PICK_FROM_ALBUM-3");
//                File copy_file = new File(mImageCaptureUri.getPath());
//                Log.d(TAG, "PICK_FROM_ALBUM-4");
//                copyFile(oringinal_file, copy_file);
//                Log.d(TAG, "PICK_FROM_ALBUM-5");
            }
            case PICK_FROM_CAMERA:
            {
                Log.d(TAG, "PICK_FROM_CAMERA");

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("output", mImageCaptureUri);
//
//                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);
                Log.w(TAG, "잘 됐니..?");
                break;
            }
            case CROP_FROM_CAMERA:
            {
                Log.w(TAG, "CROP_FROM_CAMERA");
                Log.w(TAG, "mImageCaptureUri = " +mImageCaptureUri);

                String full_path = mImageCaptureUri.getPath();
                String photo_path = full_path.substring(8, full_path.length());


                Log.w(TAG, "full_path = "+full_path);
                Log.w(TAG, "비트맵 Image path = "+photo_path);

                Bitmap photo = BitmapFactory.decodeFile(photo_path);
//                IntentUri = BitmapFactory.decodeResource(getResources(), R.drawable.imageview);
//                intentImageView.setImageBitmap(IntentUri);
//
//                intent = new Intent(Intent_before.this, Intent_After.class);
//                intent.putExtra("ImageView",IntentUri);
//                intent.putExtra("TextView", IntentString);
//                startActivity(intent);


//                mPhotoImageView.setImageBitmap(photo);
                Intent imageView = new Intent(BookDetailActivity.this, CropImageView.class);
                imageView.putExtra("Image", (Bitmap)photo);
                startActivity(imageView);

                break;

            }
        }
    }
    //srcFile : 복사 할 file
    //destFile : 복사 될 파일
    private static boolean copyFile(File srcFile, File destFile) {
        boolean result = false;
        try{
            InputStream in = new FileInputStream(srcFile);
            try{
                result = copyToFile(in,destFile);
            }finally {
                in.close();
            }
        }catch (IOException e){
            result = false;
        }
        return result;
    }

    private static boolean copyToFile(InputStream inputStream, File destFile) {
        try{
            OutputStream out = new FileOutputStream(destFile);
            try{
                byte[] buffer = new byte[4096];
                int bytesRead;
                while((bytesRead = inputStream.read(buffer))>=0){
                    out.write(buffer, 0, bytesRead);
                }
            }finally {
                out.close();
            }
            return true;
        }catch (IOException e){
            return false;
        }
    }

    private File getImageFile(Uri mImageCaptureUri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        if(mImageCaptureUri == null){
            mImageCaptureUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        Cursor mCursor = getContentResolver().query(mImageCaptureUri, projection, null, null, MediaStore.Images.Media.DATE_MODIFIED+"desc");
        if(mCursor == null || mCursor.getCount() < 1){
            return null;
        }
        int column_index = mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        mCursor.moveToFirst();

        String path = mCursor.getString(column_index);

        if(mCursor != null){
            mCursor.close();
            mCursor = null;
        }
        return new File(path);

    }

    private Uri createSaveCropFile() {

        Uri uri;
        String url = "tmp_"+String.valueOf(System.currentTimeMillis())+".jpg";
        uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        return uri;
    }
    private ImageView mPhotoImageView;

    private void setLayout(){
        mPhotoImageView = (ImageView)findViewById(R.id.image);
    }
}






