package com.robinlee.widget;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.robinlee.androiddemo.R;

import static android.view.View.LAYER_TYPE_SOFTWARE;

public class GlowingEdgesActivity extends Activity {

    private AppCompatRadioButton mAppCompatRadioButton;
    private ImageView mImgViewGlowEdge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glowing_edges);


        mAppCompatRadioButton = (AppCompatRadioButton) findViewById(R.id.mAppCompatRadioButtonDianJiu);
        mImgViewGlowEdge = (ImageView) findViewById(R.id.mImgViewGlowEdge);
        setGlowEdge(16, mAppCompatRadioButton);
        setGlowEdge(16, mImgViewGlowEdge);
    }

    private void setGlowEdge(int blurValue, View view) {
        // An added margin to the initial image
//        int margin = 10;
//        int halfMargin = margin / 2;
//
//        // the glow radius
//        int glowRadius = 16;

        view.setLayerType(LAYER_TYPE_SOFTWARE,null);

        // the glow color
        int glowColor = Color.rgb(255, 255, 255);

        // The original image to use
        Bitmap src = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_btn_selected_glow);

        // extract the alpha from the source image
        Bitmap alpha = src.extractAlpha();

        // The output bitmap (with the icon + glow)
        Bitmap outputBitmap = Bitmap.createBitmap(src.getWidth(),
                src.getHeight(), Bitmap.Config.ARGB_8888);

        // The canvas to paint on the image
        Canvas canvas = new Canvas(outputBitmap);

        Paint paint = new Paint();
        paint.setColor(glowColor);

        // outer glow
        paint.setMaskFilter(new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(alpha, 0, 0, paint);

        // original icon
        canvas.drawBitmap(src, 0, 0, null);

        if(view instanceof RadioButton){
            view.setBackground(new BitmapDrawable(getResources(), outputBitmap));
        }else if(view instanceof ImageView){
            ((ImageView) view).setImageBitmap(outputBitmap);
        }


    }
}
