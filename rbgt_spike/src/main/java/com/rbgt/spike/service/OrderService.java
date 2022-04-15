package com.rbgt.spike.service;

/**
 * File Description
 *
 * @author 俞春旺
 * @company 厦门市宜车时代
 * @date 2022-04-13 17:56
 */
public interface OrderService {

    /***
     * 秒杀商品
     * 
     * @param id
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-13 18:02
     */
    String kill(String id);

    /***
     * 秒杀商品+签名
     * 
     * @param goodsId
     * @param userId
     * @param md5
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-15 17:35
     */
    String kill(String goodsId, String userId, String md5);

    /***
     * 生成MD5签名
     * 
     * @param goodsId
     * @param userId
     * @return java.lang.String
     * @author yucw
     * @date 2022-04-15 17:15
     */
    String getMd5(String goodsId, String userId);

}
