package com.toly1994.tolyservice.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/25/025:8:45<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class LifeView extends View {
    private static final String TAG = "LifeCycleActivity";

    public LifeView(Context context) {
        super(context);
    }

    public LifeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "LifeView-☯☯☯☯☯-构造函数");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(TAG, "LifeView-☯☯☯☯☯-onFinishInflate: ");
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        Log.e(TAG, "LifeView-☯☯☯☯☯-onVisibilityChanged: ");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "LifeView-☯☯☯☯☯-onAttachedToWindow: ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "LifeView-☯☯☯☯☯-onMeasure: ");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "LifeView-☯☯☯☯☯-onSizeChanged: ");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "LifeView-☯☯☯☯☯-onLayout: ");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "LifeView-☯☯☯☯☯-onDraw: ");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.e(TAG, "LifeView-☯☯☯☯☯-onWindowFocusChanged: ");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        Log.e(TAG, "LifeView-☯☯☯☯☯-onWindowVisibilityChanged: ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "LifeView-☯☯☯☯☯-onDetachedFromWindow: ");
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Log.e(TAG, "LifeView-☯☯☯☯☯-onSaveInstanceState: ");
        return super.onSaveInstanceState();
    }

}
