package com.example.touchpressureview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchPressureView extends View {
	private Paint paint = new Paint();
	private int x, y, preasure,alpha;
	private MyThread myThread = new MyThread();

	public TouchPressureView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public TouchPressureView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		paint.setTextSize(30);
		paint.setColor(Color.RED);
		paint.setAlpha(alpha);
		canvas.drawText(preasure + "", 0, 30, paint);
		// paint.setStyle(Style.STROKE);//…Ë÷√Õº–Œø’–ƒ
		canvas.drawCircle(x, y, preasure + 30, paint);
		myThread.run();

	}

	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		getPreasure(x, y, 0);
		return super.onTouchEvent(event);
	}

	public void getPreasure(final int x, final int y, final int preasure) {
		this.x = x;
		this.y = y;
		this.preasure = preasure;
		alpha=255;
		myThread.run();
	}

	class MyThread extends Thread {

		@Override
		public void run() {
			synchronized (this) {

				super.run();
				if (preasure < 100||alpha>0) {
					preasure+=3;
					alpha-=5;
					postInvalidate();
					Log.w("liuy", alpha+"");
				}
			}
		}

	}

}
