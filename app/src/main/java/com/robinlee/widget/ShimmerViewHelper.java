package com.robinlee.widget;

import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.robinlee.androiddemo.R;

/**
 * Created by robinlee on 3/18/16.
 */
public class ShimmerViewHelper {

    public interface AnimationSetupCallback{
        void onSetupAnimation(View target);
    }

    public static final String TAG = "ShimmerViewHelper";
    private static final int DEFAULT_REFLECTION_COLOR = 0xFFFFFFFF;

    private View view;
    private Paint paint;

    private float gradientX;
    private LinearGradient linearGradient;

    private Matrix linearGradientMatrix;
    private int primaryColor;
    private int reflectionColor;
    private boolean isShimmering;
    private boolean isSetup;

    private AnimationSetupCallback callback;

    public ShimmerViewHelper(View view, Paint paint, AttributeSet attributeSet){
        this.view = view;
        this.paint = paint;
        init(attributeSet);
    }

    public float getGradientX(){
        return gradientX;
    }

    public void setGradientX(float gradientX){
        this.gradientX = gradientX;
        view.invalidate();
    }

    public boolean isShimmering(){
        return isShimmering;
    }

    public void setShimmering(boolean isShimmering){
        this.isShimmering = isShimmering;
    }

    public boolean isSetUp(){
        return isSetup;
    }

    public void setAnimationSetupCallback(AnimationSetupCallback callback){
        this.callback = callback;
    }

    public int getPrimaryColor(){
        return primaryColor;
    }

    public void setPrimaryColor(int primaryColor){
        this.primaryColor = primaryColor;
        if(isSetup){
            resetLinearGradient();
        }
    }

    public int getReflectionColor(){
        return reflectionColor;
    }

    public void setReflectionColor(int reflectionColor){
        this.reflectionColor = reflectionColor;
        if(isSetup) {
            resetLinearGradient();
        }
    }

    private void init(AttributeSet attributeSet){
        reflectionColor = DEFAULT_REFLECTION_COLOR;

        if(attributeSet != null){
            TypedArray typedArray = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.ShimmerView, 0, 0);
            if(typedArray != null){
                try{
                    reflectionColor = typedArray.getColor(R.styleable.ShimmerView_reflectionColor, DEFAULT_REFLECTION_COLOR);
                }catch (Exception e){
                    Log.e(TAG, "Error while creating the view", e);
                }finally {
                    typedArray.recycle();
                }
            }
        }

        linearGradientMatrix = new Matrix();
    }

    private void resetLinearGradient(){
        linearGradient = new LinearGradient(-view.getWidth(), 0, 0, 0,
                new int[]{
                        primaryColor,
                        reflectionColor,
                        primaryColor,
                },
                new float[]{
                        0,
                        0.5f,
                        1
                },
                Shader.TileMode.CLAMP
        );

        paint.setShader(linearGradient);
    }

    protected void onSizeChanged() {

        resetLinearGradient();

        if(!isSetup){

            isSetup = true;

            if(callback != null){
                callback.onSetupAnimation(view);
            }
        }
    }

    public void onDraw() {

        if(isShimmering){

            if(paint.getShader() == null){
                paint.setShader(linearGradient);
            }

            linearGradientMatrix.setTranslate(2 * gradientX, 0);

            linearGradient.setLocalMatrix(linearGradientMatrix);
        }else {
            paint.setShader(null);
        }

    }

}
