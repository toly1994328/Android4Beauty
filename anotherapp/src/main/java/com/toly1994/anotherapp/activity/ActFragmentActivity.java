package com.toly1994.anotherapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.toly1994.anotherapp.R;
import com.toly1994.anotherapp.fragment.BoxFragment;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/26/026:12:00<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：
 */
public class ActFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initFragment();
    }

    /**
     * 动态加载Fragment
     */
    private void initFragment() {
        //2.fm开启事务
        //3.动态添加 （控件id,fragment对象）
        getSupportFragmentManager().beginTransaction().add(R.id.fl_title, new BoxFragment()).commit();//4.提交事务
    }
}