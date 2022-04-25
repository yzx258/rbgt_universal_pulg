/**
 * outbound(输出端/南向网关)： 1.port(端口): 1.1 client(防腐层接口): 1.2 repository(仓储接口): 1.3 publisher(消息发布者接口): 2.adapter(适配器): 2.1
 * client(防腐层实现): 适配其他微服务的feign接口或缓存等第三方服务接口，入参和反参必须是领域对象或单个基础类型，不能用DTO(会污染领域层，领域层没有DTO这个概念) 2.2 repository(仓储实现):
 * 适配数据持久化接口 2.3 publisher(消息发布者实现): 适配事件总线和MQ接口
 */
package com.rbgt.ddd.outbound;