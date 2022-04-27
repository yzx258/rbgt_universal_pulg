package com.rbgt.design.适配器模式;

import com.rbgt.design.适配器模式.adapter.MediaAdapter;
import com.rbgt.design.适配器模式.player.MediaPlayer;

/**
 * 实现了 MediaPlayer 接口的实体类
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 15:57
 */
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {

        // 播放 mp3 音乐文件的内置支持
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        }
        // mediaAdapter 提供了播放其他文件格式的支持
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
