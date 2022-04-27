package com.rbgt.design.适配器模式.player;

/**
 * 媒体播放器和更高级的媒体播放器创建接口。
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-27 15:49
 */
public interface MediaPlayer {

    /***
     * 媒体播放器和更高级的媒体播放器创建接口。
     * 
     * @param audioType
     * @param fileName
     * @return void
     * @author yucw
     * @date 2022-04-27 15:50
     */
    void play(String audioType, String fileName);

}
