package com.toly1994.anotherapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.toly1994.anotherapp.R;
import com.toly1994.anotherapp.model.NameViewModel;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/4/26/026:22:39<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：Map<key,liveData>
 */
public class ViewDataActivity extends AppCompatActivity {
    private static final String TAG = "ViewPagerActivity";
    private NameViewModel model;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.id_tv);

        model = new NameViewModel();

        model.getNameEvent().observe(this, s -> {
            tv.setText(s);
        });

        tv.setOnClickListener(v -> {
            new Thread(() -> {
                model.getNameEvent().postValue("toly");
            }).start();
        });
    }

}
