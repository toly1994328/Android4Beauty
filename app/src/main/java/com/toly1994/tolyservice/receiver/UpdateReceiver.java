package com.toly1994.tolyservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.toly1994.tolyservice.app.Cons;
import com.toly1994.tolyservice.service.MusicActivity;
import com.toly1994.tolyservice.widget.ProgressView;
import org.jetbrains.annotations.Nullable;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/27/027:18:08<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：音乐进度播放监听
 * {@link com.toly1994.tolyservice.service.service.MusicPlayerWithBrStub#create}
 * {@link MusicActivity#registerReceiver()}
 */
public class UpdateReceiver extends BroadcastReceiver {
    @Nullable
    private ProgressView progressView;


    public UpdateReceiver(@Nullable ProgressView progressView) {
        this.progressView = progressView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Cons.ACTION_UPDATE.equals(intent.getAction())) {
            int progress = intent.getIntExtra(Cons.DATA_MUSIC_POSITION, 0);
            if (progressView != null) {
                progressView.setProgress(progress);
            }
        }


    }
}

