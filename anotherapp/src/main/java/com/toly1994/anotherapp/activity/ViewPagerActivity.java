package com.toly1994.anotherapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.toly1994.anotherapp.R;
import com.toly1994.anotherapp.fragment.BoxFragment;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/26/026:22:39<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ViewPager viewPager = findViewById(R.id.id_vp);

        String[] colors = new String[]{"#F73815", "#FAA43E", "#FCE73C", "#51F81E", "#1E94F8", "#8CE9F4", "#B24DF4"};
        String[] info = new String[]{"红", "橙", "黄", "绿", "蓝", "靛", "紫"};

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return BoxFragment.newInstance(colors[position],info[position]);
            }

            @Override
            public int getCount() {
                return colors.length;
            }
        });
    }

}
