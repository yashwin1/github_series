package com.example.photo_filter;

import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView keanuImageView;
    Drawable keanuFace;

//  Inorder to work with a jpeg image in android, we need to convert it tp Bitmap, which allows us to work with individual pixels
    Bitmap bitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keanuImageView = (ImageView) findViewById(R.id.keanuImageView);

//      For inverting image
        keanuFace = getResources().getDrawable(R.drawable.keanu_reeves);
        bitmapImage = ((BitmapDrawable) keanuFace).getBitmap();
        Bitmap newPhoto = invertImage(bitmapImage);
        keanuImageView.setImageBitmap(newPhoto);

//      To create layers to overlap on each other
//        Drawable[] layers = new Drawable[2];
//        layers[1] = getResources().getDrawable(R.drawable.keanu);
//        layers[0] = getResources().getDrawable(R.drawable.frame);
//        LayerDrawable layerDrawable = new LayerDrawable(layers);
//        keanuImageView.setImageDrawable(layerDrawable);


//      To save an image in the user's device
        MediaStore.Images.Media.insertImage(getContentResolver(), newPhoto, "title", "description");

    }

//  Invert a bitmap image
    public static Bitmap invertImage(Bitmap original){
        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());

        int A, R, G, B;
        int pixelColor;
        int height = original.getHeight();
        int width = original.getWidth();

        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                pixelColor = original.getPixel(x, y);
                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                finalImage.setPixel(x, y, Color.argb(A, R, G, B));

            }
        }
        return finalImage;
    }

}
