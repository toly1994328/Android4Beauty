package com.toly1994.tolyservice.service.service;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.RemoteException;
import com.toly1994.tolyservice.IMusicPlayerService;
import com.toly1994.tolyservice.app.Cons;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/1/23/023:17:11<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：MusicPlayerStub--Binder对象+广播更新进度
 */
public class MusicPlayerWithBrStub extends IMusicPlayerService.Stub {
    private static final String TAG = "MusicPlayerWithBrStub";
    private MediaPlayer mPlayer;
    private boolean isInitialized = false;//是否已初始化
    private int mCurrentPos = 0;//当前播放第几个音乐
    private List<String> mMusicList;//音乐列表
    private Context mContext;
    private Timer mTimer;

    public MusicPlayerWithBrStub(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void create(List<String> filePaths) throws RemoteException {
        mMusicList = filePaths;
        File file = new File(mMusicList.get(mCurrentPos));
        Uri uri = Uri.fromFile(file);
        mPlayer = MediaPlayer.create(mContext, uri);
        isInitialized = true;


        //构造函数中
        mTimer = new Timer();//创建Timer

        //开始方法中
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mPlayer.isPlaying()) {
                    int pos = mPlayer.getCurrentPosition();
                    int duration = mPlayer.getDuration();

                    //发送广播更新进度
                    Intent intent = new Intent(Cons.ACTION_UPDATE);
                    int progress = (int) (pos * 100.f / duration);
                    intent.putExtra(Cons.DATA_MUSIC_POSITION, progress);
                    mContext.sendBroadcast(intent);
                }
            }
        }, 0, 1000);


        mPlayer.setOnCompletionListener(mp -> {
            try {
                next();//播放完成，进入下一曲
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void start() throws RemoteException {
        if (!isInitialized && mPlayer.isPlaying()) {
            return;
        }
        mPlayer.start();
    }

    @Override
    public void stop() throws RemoteException {

    }

    @Override
    public void pause() throws RemoteException {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }


    @Override
    public void prev() throws RemoteException {
        mCurrentPos--;
        judgePos();//如果越界则置0
        changMusicByPos(mCurrentPos);
    }

    @Override
    public void next() throws RemoteException {
        mCurrentPos++;
        judgePos();//如果越界则置0
        changMusicByPos(mCurrentPos);
    }

    @Override
    public void release() throws RemoteException {
        mTimer.cancel();
        mPlayer.release();
    }

    @Override
    public boolean isPlaying() throws RemoteException {
        return mPlayer.isPlaying();
    }

    @Override
    public void seek(int pre_100) throws RemoteException {
        pause();
        mPlayer.seekTo((pre_100 * mPlayer.getDuration() / 100));
        start();
    }


    /**
     * 越界处理
     */
    private void judgePos() {
        if (mCurrentPos >= mMusicList.size()) {
            mCurrentPos = 0;
        }

        if (mCurrentPos < 0) {
            mCurrentPos = mMusicList.size() - 1;
        }
    }

    /**
     * 根据位置切歌
     *
     * @param pos 当前歌曲id
     */
    private void changMusicByPos(int pos) {
        mPlayer.reset();//重置
        try {
            mPlayer.setDataSource(mMusicList.get(pos));//设置当前歌曲
            mPlayer.prepare();//准备
            start();
        } catch (IOException | RemoteException e) {
            e.printStackTrace();
        }
    }


    //------------设置进度监听-----------
    public interface OnSeekListener {
        void onSeek(int per_100);
    }

    private OnSeekListener mOnSeekListener;

    public void setOnSeekListener(OnSeekListener onSeekListener) {
        mOnSeekListener = onSeekListener;
    }
}