package com.toly1994.tolyservice.service

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.Handler
import android.util.Log
import java.io.File
import java.util.*
import kotlin.concurrent.timerTask


/**
 * 作者：张风捷特烈<br></br>
 * 时间：2019/1/17/017:21:57<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：播放核心类
 */
class MusicPlayer(private val mContext: Context) : Binder(), IPlayer {

    private lateinit var mPlayer: MediaPlayer
    private var isInitialized = false//是否已初始化

    private var mCurrentPos = 0//当前播放第几个音乐
    private lateinit var mMusicList: ArrayList<String>//音乐列表

    private lateinit var mTimer: Timer

    private lateinit var mHandler: Handler

    override fun create(musicList: ArrayList<String>) {
        mMusicList = musicList
        val file = File(musicList[mCurrentPos])
        val uri = Uri.fromFile(file)
        mPlayer = MediaPlayer.create(mContext, uri)
        isInitialized = true

        mTimer = Timer()//创建Timer
        mHandler = Handler()//创建Handler


        mTimer.schedule(timerTask {
            if (isPlaying()) {
                val pos = mPlayer.currentPosition;
                val duration = mPlayer.duration;
                mHandler.post {
                    if (mOnSeekListener != null) {
                        mOnSeekListener.onSeek((pos.toFloat() / duration * 100).toInt());
                    }
                }
            }
        }, 0, 1000)



        mPlayer.setOnCompletionListener {
            next()//播放完成，进入下一曲
        }
        Log.e(TAG, "诞生")
    }

    override fun start() {
        if (!isInitialized && mPlayer.isPlaying) {
            return
        }
        mPlayer.start();
        Log.e(TAG, "开始播放")
    }

    override fun next() {
        mCurrentPos++
        judgePos()//如果越界则置0
        changMusicByPos(mCurrentPos)
    }


    override fun prev() {
        mCurrentPos--
        judgePos()//如果越界则置0
        changMusicByPos(mCurrentPos)
    }

    /**
     * 越界处理
     */
    private fun judgePos() {
        if (mCurrentPos >= mMusicList.size) {
            mCurrentPos = 0
        }

        if (mCurrentPos < 0) {
            mCurrentPos = mMusicList.size - 1
        }
    }

    /**
     * 根据位置切歌
     * @param pos 当前歌曲id
     */
    private fun changMusicByPos(pos: Int) {
        mPlayer.reset()//重置
        mPlayer.setDataSource(mMusicList[pos])//设置当前歌曲
        mPlayer.prepare()//准备
        start()
        Log.e(TAG, "当前播放歌曲pos:$pos:,路径:${mMusicList[pos]}")
    }

    override fun seek(pre_100: Int) {
        pause()
        mPlayer.seekTo((pre_100 * mPlayer.duration / 100))
        start()
    }

    override fun stop() {
        if (mPlayer.isPlaying) {
            mPlayer.stop()
            mPlayer.release()
        }
        Log.e(TAG, "停止播放")
    }

    override fun pause() {
        if (mPlayer.isPlaying) {
            mPlayer.pause()
        }
        Log.e(TAG, "暂停播放")
    }

    override fun release() {
        Log.e(TAG, "销毁")
    }

    override fun isPlaying(): Boolean {
        return mPlayer.isPlaying
    }

    companion object {
        private val TAG = "MusicPlayer"
    }

    //------------设置进度监听-----------
    interface OnSeekListener {
        fun onSeek(per_100: Int);
    }

    private lateinit var mOnSeekListener: OnSeekListener

    fun setOnSeekListener(onSeekListener: OnSeekListener) {
        mOnSeekListener = onSeekListener;
    }


}
