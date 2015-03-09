package rui.yyllsdemo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by rui on 15/3/10.
 */
public class AutoFitImageView extends ImageView{

    private float mWidthAndHeightRatio;

    public AutoFitImageView(Context context) {
        super(context);
    }

    public AutoFitImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoFitImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (null != bitmap) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                mWidthAndHeightRatio = (width * 1.0f) / height;
                invalidate();
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mWidthAndHeightRatio > 0) {
            int width = getMeasuredWidth();
            int height = (int) (width / mWidthAndHeightRatio);
            setMeasuredDimension(width, height);
        }
    }
}
