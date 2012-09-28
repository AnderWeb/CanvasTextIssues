package com.example.canvastext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("NewApi")
public class CanvasView extends View {

	private TextPaint mPaint;
	private float mPrevX;
	private float mPosX=0;
	private String mText;
	private Matrix mMatrix;
	private Camera mCamera;
	private float mTextWidth;
	
	public CanvasView(Context context) {
		super(context);
		init(context);
	}

	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CanvasView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context){
		setClickable(true);
		mText = context.getResources().getString(R.string.hello_world);
		mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(0xFFFF0000);
		mPaint.setTextSize(60);
		mPaint.setShadowLayer(3, 0, 1, 0x44000000);
		mMatrix = new Matrix();
		mCamera = new Camera();
		mTextWidth = mPaint.measureText(mText);

	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		final Matrix matrix = mMatrix;
		final Camera camera = mCamera;
		final float totalW = getWidth();
		final float totalH = getHeight();
		final float halfW = totalW/2f;
		final float halfH = totalH/2f;
        camera.save();
		camera.rotateY(360*(mPosX/totalH));
        camera.getMatrix(matrix);
		camera.restore();
        matrix.preTranslate(-halfW, -halfH);
        matrix.postTranslate(halfW, halfH);
        canvas.save();
		canvas.concat(matrix);

		float x = (int) (((totalW - mTextWidth) * 0.5f));
		float y = halfH;

		canvas.drawText(mText, x, y, mPaint);
		canvas.restore();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		switch(action){
			case MotionEvent.ACTION_DOWN:{
				mPrevX = event.getX();
				break;
			}
			case MotionEvent.ACTION_MOVE:{
				float xDelta = event.getX()-mPrevX;
				mPosX+=xDelta;
				mPrevX = event.getX();
				invalidate();
				break;
			}
		}
		return super.onTouchEvent(event);
	}

}
