package edu.umass.cs.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private ColorPicker colorPicker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.colorPicker = (ColorPicker) findViewById(R.id.colorPicker);
        this.colorPicker.setColor(0xFFB6C1);

        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        //this.imageView.setImageDrawable(getResources().getDrawable(R.drawable.test, getApplicationContext().getTheme()));

        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            int height = photo.getHeight();
            int width = photo.getWidth();
            imageView.setImageBitmap(photo);
            String temp = String.format("Height: %d, Width: %d", height, width);
            Toast.makeText(MainActivity.this, temp, Toast.LENGTH_LONG).show();
        }
    }


}