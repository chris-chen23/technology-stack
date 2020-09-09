# spring-cloud-seata-demo

#### 介绍
基于Seata实现的分布式事务Demo
银行A与银行B服务之间转账

#### 理论
理论介绍：https://www.jianshu.com/p/4000d39e53d6


#### 使用说明

1.  启动seata-server：    sh ./bin/seata-server.sh              
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/150703_60a4fb65_5551811.jpeg "1.jpg")

2.  安装seata-common到本地厂库：    mvn install              
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/150718_7a23d0c9_5551811.jpeg "2.jpg")

3.  启动注册中心：    mvn spring-boot:run              
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/150734_362e02f6_5551811.jpeg "3.jpg")

4.  启动bank-a：     mvn spring-boot:run              
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/150750_6969ef3a_5551811.jpeg "4.jpg")

    看seata-serve输出              
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/151625_69b42299_5551811.png "11.png")

5.  启动bank-b：     mvn spring-boot:run              
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/150810_768d3893_5551811.jpeg "4-2.jpg")

    看seata-serve输出              
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/151640_dcf12738_5551811.png "12.png")


#### 使用说明

1.  正常测试：   http://127.0.0.1:8081/bank-a/updAmount              

    bank-a库中张三账户加100元               
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/151006_56485b52_5551811.jpeg "6-1.jpg")

    bank-b库中李四账户减100元        
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/151014_c51d661a_5551811.jpeg "6-2.jpg")

2.  异常测试：   http://127.0.0.1:8081/bank-a/updAmount?mode=OUT

    bank-a库、bank-b库中金额不变                 
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/151054_ac99de56_5551811.jpeg "7-1.jpg") 

    看seata-serve输出回滚信息               
![输入图片说明](https://images.gitee.com/uploads/images/2020/0414/151133_5037cb9f_5551811.jpeg "7-2.jpg")


## Spring Cloud 快速集成 Seata
### 官方地址：http://seata.io/zh-cn/
### 官方Github：https://github.com/seata/seata-samples/blob/master/doc/quick-integration-with-spring-cloud.md#registryconf
### 1. 添加依赖
Maven

```
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alibaba-seata</artifactId>
        <version>2.1.0.RELEASE</version>
</dependency>
```
需要注意的是Spring Cloud Alibaba 的毕业版本的 GroupId 是 com.alibaba.cloud

spring-cloud-starter-alibaba-seata这个依赖中只依赖了spring-cloud-alibaba-seata，所以在项目中添加spring-cloud-starter-alibaba-seata和spring-cloud-alibaba-seata是一样的

### 2. 添加Seata 配置文件
registry.conf
该配置用于指定 TC 的注册中心和配置文件，默认都是 file; 如果使用其他的注册中心，要求 Seata-Server 也注册到该配置中心上

registry.conf
```
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "file"

  nacos {
    serverAddr = "localhost"
    namespace = "public"
    cluster = "default"
  }
  eureka {
    serviceUrl = "http://localhost:8761/eureka"
    application = "default"
    weight = "1"
  }
  redis {
    serverAddr = "localhost:6379"
    db = "0"
  }
  zk {
    cluster = "default"
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  consul {
    cluster = "default"
    serverAddr = "127.0.0.1:8500"
  }
  etcd3 {
    cluster = "default"
    serverAddr = "http://localhost:2379"
  }
  sofa {
    serverAddr = "127.0.0.1:9603"
    application = "default"
    region = "DEFAULT_ZONE"
    datacenter = "DefaultDataCenter"
    cluster = "default"
    group = "SEATA_GROUP"
    addressWaitTime = "3000"
  }
  file {
    name = "file.conf"
  }
}

config {
  # file、nacos 、apollo、zk、consul、etcd3
  type = "file"

  nacos {
    serverAddr = "localhost"
    namespace = "public"
    cluster = "default"
  }
  consul {
    serverAddr = "127.0.0.1:8500"
  }
  apollo {
    app.id = "seata-server"
    apollo.meta = "http://192.168.1.204:8801"
  }
  zk {
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  etcd3 {
    serverAddr = "http://localhost:2379"
  }
  file {
    name = "file.conf"
  }
}
```
file.conf
该配置用于指定TC的相关属性；如果使用注册中心也可以将配置添加到配置中心

file.conf
```
transport {
  # tcp udt unix-domain-socket
  type = "TCP"
  #NIO NATIVE
  server = "NIO"
  #enable heartbeat
  heartbeat = true
  #thread factory for netty
  thread-factory {
    boss-thread-prefix = "NettyBoss"
    worker-thread-prefix = "NettyServerNIOWorker"
    server-executor-thread-prefix = "NettyServerBizHandler"
    share-boss-worker = false
    client-selector-thread-prefix = "NettyClientSelector"
    client-selector-thread-size = 1
    client-worker-thread-prefix = "NettyClientWorkerThread"
    # netty boss thread size,will not be used for UDT
    boss-thread-size = 1
    #auto default pin or 8
    worker-thread-size = 8
  }
  shutdown {
    # when destroy server, wait seconds
    wait = 3
  }
  serialization = "seata"
  compressor = "none"
}
service {
  #vgroup->rgroup
  vgroup_mapping.my_test_tx_group = "default"
  #only support single node
  default.grouplist = "127.0.0.1:8091"
  #degrade current not support
  enableDegrade = false
  #disable
  disable = false
  #unit ms,s,m,h,d represents milliseconds, seconds, minutes, hours, days, default permanent
  max.commit.retry.timeout = "-1"
  max.rollback.retry.timeout = "-1"
}

client {
  async.commit.buffer.limit = 10000
  lock {
    retry.internal = 10
    retry.times = 30
  }
  report.retry.count = 5
}

## transaction log store
store {
  ## store mode: file、db
  mode = "file"

  ## file store
  file {
    dir = "sessionStore"

    # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
    max-branch-session-size = 16384
    # globe session size , if exceeded throws exceptions
    max-global-session-size = 512
    # file buffer size , if exceeded allocate new buffer
    file-write-buffer-cache-size = 16384
    # when recover batch read size
    session.reload.read_size = 100
    # async, sync
    flush-disk-mode = async
  }

  ## database store
  db {
    ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
    datasource = "dbcp"
    ## mysql/oracle/h2/oceanbase etc.
    db-type = "mysql"
    url = "jdbc:mysql://127.0.0.1:3306/seata"
    user = "mysql"
    password = "mysql"
    min-conn = 1
    max-conn = 3
    global.table = "global_table"
    branch.table = "branch_table"
    lock-table = "lock_table"
    query-limit = 100
  }
}
lock {
  ## the lock store mode: local、remote
  mode = "remote"

  local {
    ## store locks in user's database
  }

  remote {
    ## store locks in the seata's server
  }
}
recovery {
  committing-retry-delay = 30
  asyn-committing-retry-delay = 30
  rollbacking-retry-delay = 30
  timeout-retry-delay = 30
}

transaction {
  undo.data.validation = true
  undo.log.serialization = "jackson"
}

## metrics settings
metrics {
  enabled = false
  registry-type = "compact"
  # multi exporters use comma divided
  exporter-list = "prometheus"
  exporter-prometheus-port = 9898
}
```
需要注意的是 service.vgroup_mapping这个配置，在 Spring Cloud 中默认是${spring.application.name}-fescar-service-group，可以通过指定application.properties的 spring.cloud.alibaba.seata.tx-service-group这个属性覆盖，但是必须要和 file.conf 中的一致，否则会提示 no available server to connect

###  3. 注入数据源
Seata 通过代理数据源的方式实现分支事务；MyBatis 和 JPA 都需要注入 io.seata.rm.datasource.DataSourceProxy, 不同的是，MyBatis 还需要额外注入 org.apache.ibatis.session.SqlSessionFactory

MyBatis

```
@Configuration
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        return sqlSessionFactoryBean.getObject();
    }
}
```

### 4. 添加 undo_log 表
在业务相关的数据库中添加 undo_log 表，用于保存需要回滚的数据

```
CREATE TABLE `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `branch_id`     BIGINT(20)   NOT NULL,
    `xid`           VARCHAR(100) NOT NULL,
    `context`       VARCHAR(128) NOT NULL,
    `rollback_info` LONGBLOB     NOT NULL,
    `log_status`    INT(11)      NOT NULL,
    `log_created`   DATETIME     NOT NULL,
    `log_modified`  DATETIME     NOT NULL,
    `ext`           VARCHAR(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
```

### 5. 启动 Seata-Server

在 https://github.com/seata/seata/releases 下载相应版本的 Seata-Server，修改 registry.conf为相应的配置(如果使用 file 则不需要修改)，解压并通过以下命令启动:

```
sh ./bin/seata-server.sh
```

### 6. 使用@GlobalTransactional开启事务
在业务的发起方的方法上使用@GlobalTransactional开启全局事务，Seata 会将事务的 xid 通过拦截器添加到调用其他服务的请求中，实现分布式事务










