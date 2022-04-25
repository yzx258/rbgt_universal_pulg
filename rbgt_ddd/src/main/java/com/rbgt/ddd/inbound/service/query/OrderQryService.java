package com.rbgt.ddd.inbound.service.query;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * Q端应用服务-订单查询服务(查)
 *
 * 1.Q端就跟原来的service类的查询接口一样，通过调mapper接口，返回前端要的DTO
 * 2.直接调基础设施层(infra)的持久化框架接口(例如mybatis的mapper接口)，查询前端需要的DTO，不需要经过领域层，也不需要调仓储接口。 3.可以用spring.data.redis方法注解来加查询缓存
 * 4.如果返给前端的DTO的部分字段在当前微服务的数据实体中不存在，那就需要调其它微服务feign接口来获取(IProductApiClient) 5.这里OrderQryService类没有接口，因为接口可有可无，都行。
 *
 * @author lvweijie
 * @date 2021-08-12 07:59
 */
@Service
@RequiredArgsConstructor
public class OrderQryService {

}
