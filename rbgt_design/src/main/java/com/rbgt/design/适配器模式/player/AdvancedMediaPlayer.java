package com.rbgt.design.适配器模式.player;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 15:50
 */
public interface AdvancedMediaPlayer {

    /***
     * 播放 - Vlc
     * 
     * @param fileName
     * @return void
     * @author yucw
     * @date 2022-04-27 15:51
     */
    void playVlc(String fileName);

    /***
     * 播放 - Mp4
     * 
     * @param fileName
     * @return void
     * @author yucw
     * @date 2022-04-27 15:51
     */
    void playMp4(String fileName);

}
