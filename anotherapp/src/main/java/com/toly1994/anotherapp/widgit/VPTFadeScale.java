package com.toly1994.anotherapp.widgit;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/27/027:0:33<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class VPTFadeScale implements ViewPager.PageTransformer {
    private static float MIN_SCALE = 0.7f;

    //A==>B  A的position 0==>-1   B的position 1==>0
    @Override
    public void transformPage(View page, float position) {
        int width = page.getWidth();
        int height = page.getHeight();

        if (position < -1) {//非A、B页
            page.setAlpha(1);
        } else if (position <= 0) {//A页的动画
            page.setAlpha(1 + position * 2);
            page.setScaleX(1);
            page.setScaleY(1);

            page.setPivotX(0);
            page.setPivotY(height / 2);

            page.setRotationX(-100 * position);
            page.setRotationY(-100 * position);

        } else if (position <= 1) {//B页的动画
            page.setAlpha(1 - position);
            page.setTranslationX(width * (-position));
//            0.75~1
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }
    }
}
