package com.toly1994.tolyservice.service

/**
 * 作者：张风捷特烈<br></br>
 * 时间：2018/10/31 0031:23:32<br></br>
 * 邮箱：1981462002@qq.com<br></br>
 * 说明：播放接口
 */
interface IPlayer {
    fun create(musicList: ArrayList<String>)// 诞生

    fun start()// 开始

    fun stop()// 停止

    fun pause()// 暂停

    fun release()//死亡

    fun next()//下一曲

    fun prev()//上一曲

    fun isPlaying(): Boolean

    fun seek(pre_100: Int)//拖动进度
}
