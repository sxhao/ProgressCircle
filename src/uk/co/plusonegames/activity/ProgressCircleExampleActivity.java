package uk.co.plusonegames.activity;

import uk.co.plusonegames.progresscircle.R;
import uk.co.plusonegames.widgets.ProgressCircle;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Example showing some simple ProgressCircles in action on a black background
 * 
 * @author James R Wilding
 * 
 */
public class ProgressCircleExampleActivity extends Activity {
	int mProgress = 0;
	ProgressCircle mCircle1;
	ProgressCircle mCircle2;
	ProgressCircle mCircle3;
	ProgressCircle mCircle4;

	final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			mCircle1.setProgress((mProgress += 5) % mCircle1.getMax());
			mCircle2.setProgress(mProgress % mCircle2.getMax());
			mCircle3.setProgress((mProgress % mCircle3.getMax()) + mCircle3.getMax());
			mCircle4.setProgress(mProgress % mCircle4.getMax());
			mHandler.sendEmptyMessageDelayed(0, 20);
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mCircle1 = (ProgressCircle) findViewById(R.id.cpb_1);
		mCircle2 = (ProgressCircle) findViewById(R.id.cpb_2);
		mCircle2.setStartAngle(45.0f);
		mCircle3 = (ProgressCircle) findViewById(R.id.cpb_3);
		mCircle4 = (ProgressCircle) findViewById(R.id.cpb_4);

		mHandler.sendEmptyMessage(0);
	}
}