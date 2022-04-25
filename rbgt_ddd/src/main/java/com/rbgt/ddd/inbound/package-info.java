/**
 * inbound(输入端/北向网关)： 1.remote(远程服务)： 1.1 controller(控制器接口): 1.2 provider(feign接口提供者): feign接口实现类 1.3 consumer(消息消费者)：
 * 2.local(本地服务)： 2.1 assembler(组装器): 把远程服务的请求参数组装成领域层的领域对象，供领域层的业务逻辑使用 2.2
 * listener(本地事件监听器)：聚合间发送领域事件(SpringUtil.publishEvent), 这个包定义接口，由local.service.cmd的命令端应用服务实现 2.2.1
 * 常用注解：@EventListener(会和发送事件方法公用一个事务) @TransactionalEventListener(发送事件方法事务提交之后再执行) @Async(异步执行事件消费) 2.3
 * service(应用服务)：按CQRS把应用服务分离成C端和Q端 2.3.1 cmd(命令端应用服务)：增删改方法，需要经过领域层，负责组合编排领域服务，完成整个业务用例。 2.3.2
 * qry(查询端应用服务)：查询方法，直接依赖注入Mapper/Dao接口,直接查controller或provider需要的DTO, 不需要经过领域层
 */
package com.rbgt.ddd.inbound;