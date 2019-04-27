package com.toly1994.anotherapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.toly1994.anotherapp.R;
import com.toly1994.anotherapp.fragment.BoxFragment;
import com.toly1994.anotherapp.widgit.VPTFadeScale;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/26/026:22:39<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ViewPagerActivity extends AppCompatActivity {
    private static final String TAG = "ViewPagerActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ViewPager viewPager = findViewById(R.id.id_vp);

        String[] colors = new String[]{"#F73815", "#FAA43E", "#FCE73C", "#51F81E", "#1E94F8", "#8CE9F4", "#B24DF4"};
        String[] info = new String[]{"红", "橙", "黄", "绿", "蓝", "靛", "紫"};


        viewPager.setPageTransformer(true, new VPTFadeScale());

        //[]ViewPager滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 当页面滑动过程中的回调
             * @param position             当前滑动页面的位置
             * @param positionOffset       下一页在当前页所占的宽度百分比
             * @param positionOffsetPixels 下一页在当前页所占的宽度像素值
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "onPageScrolled: " + "position==>"
                        + position + "----positionOffset==>"
                        + positionOffset + "----positionOffsetPixels" + positionOffsetPixels);
            }
            /**
             * 某个页面被选中(从0计数) 翻页成功才会调用
             * @param position 翻页后的视图在集合中位置
             */
            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: " + position);
            }
            /**
             * 页面状态发生变化的回调  1 滑动开始到手指离开前  2 手指离开后到结束之间 0  滑动结束
             * @param state 状态
             */
            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e(TAG, "onPageScrollStateChanged: " + state);
            }
        });


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return BoxFragment.newInstance(colors[position], info[position]);
            }

            @Override
            public int getCount() {
                return colors.length;
            }
        });
    }

}
