package com.rbgt.design.适配器模式.player;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 15:51
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("支持播放Vlc视频文件......");
    }

    @Override
    public void playMp4(String fileName) {

    }
}
