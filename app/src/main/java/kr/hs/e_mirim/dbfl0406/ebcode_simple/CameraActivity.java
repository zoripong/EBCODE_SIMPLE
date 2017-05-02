package kr.hs.e_mirim.dbfl0406.ebcode_simple;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class CameraActivity extends Activity{

    static final int CALL_CAMERA = 0;
    static final int CALL_GALLERY =1;

    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mImage = (ImageView)findViewById(R.id.attachimage);

    }

    public void mOnClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.CameraCallButton:
                intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CALL_CAMERA);
                break;
            case R.id.GalleryCallButton :
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,CALL_GALLERY);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case CALL_CAMERA:
                    mImage.setImageBitmap((Bitmap)data.getExtras().get("data"));
                    break;

                case CALL_GALLERY:
                    try{
                        mImage.setImageBitmap(MediaStore.Images.Media.getBitmap(
                                getContentResolver(), data.getData()));
                    }catch (Exception e){;}
                    break;
            }
        }
    }
}
