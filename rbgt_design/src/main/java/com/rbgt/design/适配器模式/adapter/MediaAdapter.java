package com.rbgt.design.适配器模式.adapter;

import com.rbgt.design.适配器模式.player.AdvancedMediaPlayer;
import com.rbgt.design.适配器模式.player.MediaPlayer;
import com.rbgt.design.适配器模式.player.Mp4Player;
import com.rbgt.design.适配器模式.player.VlcPlayer;

/**
 * 实现了 MediaPlayer 接口的适配器类
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 15:54
 */
public class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
