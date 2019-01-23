package com.toly1994.tolyservice.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.toly1994.tolyservice.R;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2018/11/1 0001:12:45<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：两张图片的点击切换 TODO 可拓展为n张
 */
public class ToggleImageView extends android.support.v7.widget.AppCompatImageView {

    private Drawable mOddSrc;
    private int mCurrentCount;//是否是奇数次点击
    private Drawable mEvenSrc;

    public ToggleImageView(Context context) {
        this(context, null);

    }

    public ToggleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * 初始化
     *
     * @param attrs 自定义属性
     */
    private void init(AttributeSet attrs) {
        TypedArray ta = attrs == null ? null : getContext()
                .obtainStyledAttributes(attrs, R.styleable.ToggleImageView);
        mOddSrc = ta.getDrawable(R.styleable.ToggleImageView_z_toggle_src);
        ta.recycle();//一定记得回收！！！
        mEvenSrc = getDrawable();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {
            ObjectAnimator alpha = ObjectAnimator.ofFloat(
                    this, "alpha", 1f, .1f, 1f).setDuration(100);
            alpha.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    //第一次点击--设置mOddSrc
                    setImageDrawable(mCurrentCount % 2 == 0 ? mOddSrc : mEvenSrc);
                    mCurrentCount++;

                    if (mCurrentCount == 2) {
                        mCurrentCount = 0;
                    }

                    if (mOnAlphaListener != null) {
                        mOnAlphaListener.click(ToggleImageView.this, mCurrentCount);
                    }
                }
            });
            alpha.start();
        }
        return true;
    }

    public interface OnAlphaListener {
        void click(View view, int count);
    }

    private OnAlphaListener mOnAlphaListener;

    public void setOnAlphaListener(OnAlphaListener onAlphaListener) {
        mOnAlphaListener = onAlphaListener;
    }
}
