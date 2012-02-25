package uk.co.plusonegames.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * A circular progress bar that uses a wedge (arc) to mask a bitmap. Set the
 * indeterminateDrawable to your full circle resource.
 * 
 * @author James R Wilding
 * 
 */
public class ProgressCircle extends ProgressBar {

	private float mStartAngle = -90.0f;
	private float mAngle = 0.0f;
	private final Paint mPaint = new Paint();
	private final PorterDuffXfermode mPorter = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

	public ProgressCircle(Context context) {
		this(context, null);
	}

	public ProgressCircle(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.progressBarStyle);
	}

	public ProgressCircle(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * Sets the starting angle of the progress, 0 = EAST
	 * 
	 * @param startAngle
	 *            default -90.0f (NORTH)
	 */
	public void setStartAngle(float startAngle) {
		mStartAngle = startAngle;
	}

	/**
	 * Sets the progress, 0 = no progress shown, max = full progress shown
	 * 
	 * @param progress
	 *            in the range of 0-max
	 */
	@Override
	public synchronized void setProgress(int progress) {
		super.setProgress(progress);
		float per = (float) progress / (float) getMax();
		mAngle = 360.0f * per;
		invalidate();
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		Drawable d = getIndeterminateDrawable();
		if (d != null) {
			RectF rect = new RectF(d.getBounds());

			mPaint.setXfermode(null);
			canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
			canvas.translate(getPaddingLeft(), getPaddingTop());
			canvas.drawBitmap(((BitmapDrawable) d).getBitmap(), null, rect, mPaint);
			// d.draw(canvas);

			// Draw the wedge bigger than the drawable
			mPaint.setXfermode(mPorter);
			rect.left -= rect.right;
			rect.top -= rect.bottom;
			rect.right *= 2;
			rect.bottom *= 2;

			canvas.drawArc(rect, mStartAngle + mAngle, 360 - mAngle, true, mPaint);

			canvas.restore();
		}
	}
}
