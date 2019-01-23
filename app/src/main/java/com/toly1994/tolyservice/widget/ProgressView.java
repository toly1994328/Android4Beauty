package com.toly1994.tolyservice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.toly1994.tolyservice.R;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/10/31 0031:14:03<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ProgressView extends View {

    private int mBarHeight;
    private int mBarWidth;
    private float progress = 0f;

    private int mBarColor = 0xff49F642;
    private Paint mDefaultPaint;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    /**
     * 初始化
     *
     * @param attrs 自定义属性
     */
    private void init(AttributeSet attrs) {
        TypedArray ta = attrs == null ? null : getContext().obtainStyledAttributes(attrs, R.styleable.ProgressView);
        mBarColor = ta.getColor(R.styleable.ProgressView_z_bar_color, mBarColor);
        ta.recycle();//一定记得回收！！！
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mBarWidth = MeasureSpec.getSize(widthMeasureSpec);
        mBarHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mBarWidth, mBarHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDefaultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDefaultPaint.setColor(mBarColor);


        canvas.drawRect(0, 0, progress*mBarWidth, mBarHeight, mDefaultPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                this.progress = (event.getX() - getLeft()) * 1.f / mBarWidth;
                invalidate();
                if (mOnDragListener != null) {
                    mOnDragListener.onDrag((int) (progress * 100));
                }
                break;
        }
        return true;
    }

    public void setProgress(int progress) {
        this.progress = progress*1.0f/100;
        invalidate();
    }

    public interface OnProgressChangeListener {
        void onChange(int progress);
    }

    private OnProgressChangeListener mOnProgressChangeListener;

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        mOnProgressChangeListener = onProgressChangeListener;
    }

    public interface OnDragListener {
        void onDrag(int pre_100);
    }

    private OnDragListener mOnDragListener;

    public void setOnDragPVListener(OnDragListener onDragListener) {
        mOnDragListener = onDragListener;
    }

    /**
     * 适配dp
     *
     * @param dp dp数字
     * @return px 数字
     */
    public float dp2px(float dp) {
        final Float scale = getContext().getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    /**
     * 适配dp
     *
     * @param id dimen的值
     * @return px 数字
     */
    public int dimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }

    /**
     * 适配dp
     *
     * @param id dimen的值
     * @return px 数字
     */
    public int color(int id) {
        return ContextCompat.getColor(getContext(), id);
    }

}
