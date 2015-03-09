package rui.yyllsdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

/**
 * Created by rui on 15/3/9.
 */
public class AnimationActivity extends ActionBarActivity{

    private TextView mCountTextView;

    private View mAnimationView;

    private AnimationSet mAnimationSet;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (0 == msg.what) {
                mHandler.removeMessages(0);
                count--;
                mCountTextView.setText(count + "");
                if (count > 0) {
                    mHandler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        }
    };

    private int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);

        mCountTextView = (TextView) findViewById(R.id.count_text_view);
        mCountTextView.setText(count + "");

        mAnimationView = findViewById(R.id.circle);

        mAnimationSet = new AnimationSet(false);

        ScaleAnimation scaleAnimation1 = new ScaleAnimation(1.0f, 0.6f, 1.0f, 0.6f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation1.setDuration(500);

        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.0f, 1.25f, 1.0f, 1.25f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation2.setStartOffset(500);
        scaleAnimation2.setDuration(500);

        ScaleAnimation scaleAnimation3 = new ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation3.setStartOffset(1000);
        scaleAnimation3.setDuration(500);

        ScaleAnimation scaleAnimation4 = new ScaleAnimation(1.0f, 1.25f, 1.0f, 1.25f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation4.setStartOffset(1500);
        scaleAnimation4.setDuration(500);

        ScaleAnimation scaleAnimation5 = new ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation5.setStartOffset(2000);
        scaleAnimation5.setDuration(500);

        ScaleAnimation scaleAnimation6 = new ScaleAnimation(1.0f, 1.50f, 1.0f, 1.50f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation6.setStartOffset(2500);
        scaleAnimation6.setDuration(500);

        AlphaAnimation alphaAnimation1 = new AlphaAnimation(1.0f, 0f);
        alphaAnimation1.setStartOffset(2500);
        alphaAnimation1.setDuration(500);

        mAnimationSet.addAnimation(scaleAnimation1);
        mAnimationSet.addAnimation(scaleAnimation2);
        mAnimationSet.addAnimation(scaleAnimation3);
        mAnimationSet.addAnimation(scaleAnimation4);
        mAnimationSet.addAnimation(scaleAnimation5);
        mAnimationSet.addAnimation(scaleAnimation6);
        mAnimationSet.addAnimation(alphaAnimation1);

        mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAnimationView.clearAnimation();
                mAnimationView.setVisibility(View.GONE);
                mCountTextView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mAnimationView.startAnimation(mAnimationSet);
            }
        });

        mHandler.sendEmptyMessageDelayed(0, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

        mAnimationView.clearAnimation();
        mAnimationView.setVisibility(View.GONE);
        mCountTextView.setVisibility(View.GONE);
    }
}
