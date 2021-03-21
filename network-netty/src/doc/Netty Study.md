# 1.netty开篇
## 1.1 官网的demo
1.  [官网](https://netty.io/)介绍的netty特性
    1.  主要用来写server和client自实现定义协议protocol的
    2.  支持TCP和UDP两种
    3.  没有可维护性、性能的隐患
2.  关于官网的几个例子
    1.  DiscardServer，本身就是支持链接多个client了
    2.  
3.  问题：
    1.  如何保持着链接不断开
    2.  channelActive时，client和server都channelActive了吗？
    3.  
4.  我以为加入到pipeline中的handler，应该分两种来排序，
    inbound和outbound？可是总不对。
5.  今天学到了不少东西
    1.  encode和decode的本质和作用方向
        1.  本质：在byte和message之间的转换
        2.  作用方向：无论是client和server，encode永远是出，decode永远是近
        3.  一个方向可以多decode或者多encode吗？有必要吗？
    2.  IdleHandler的使用-配合userEvent
    3.  学习了LoopBackTimeStamp
    4.  学习了手动input版本的 echo协议
    
# 2.实战
## 2月21日-pipeline的执行顺序
1.  pipeline是有channelHandler组成的双向链表，其执行顺序指的就是这些handler上各个
    动作的执行传递情况。
2.  整体来讲，handler分in和out两种。
    in-handler的操作会顺着in-handler正向传下去，对应的就是next指针，
    out-handler的操作会逆向传下去，对应的就是pre指针。
    如果这个链中有一个没有向下传递，那这个动作就到此为止了。
    可以从handler中用HandlerContext随时发起动作。
3.  in方向是正向，只管数据读入；out方向是反向，管数据写出。
4.  热门问题：ctx.writeAndFlush与ctx.channel().writeAndFlush的区别
    答：很简单，前者是从当前handler写入，而后者是从最末tail的handler写入。
    
    ```
        public final ChannelFuture writeAndFlush(Object msg) {
            return tail.writeAndFlush(msg);
        }
    ```
## 2月21日-小记几点
1.  in_action中有一章讲ByteBuf，其字节级操作、使用模式还是很不错的，但是我还没有看懂，以后再看不完。
2.  看懂了EmbeddedChannel的几个测试逻辑，细节还可以再看看。
3.  今日收获：
    1.  看源码+调试+书本讲解+google代码用例，是很好一种的学习方式。以后要多用这一招。
    2.  感觉也动了EventLoop等线程调用模型了。
    3.  其实看懂了流水线的读写，就看懂了一半了。
4.  todo-
    1.  差不多可以开始写哥哥的那个协议了，周末吧；估计接下来的几天很忙。
    2.  不放心的话，还可以再瞅瞅netty源码中的一些现成的协议，比如redis.
        在 io.netty.example.redis.RedisClientHandler
        
## 2月22日-
1.  netty源码编译有点问题，找不到netty-tcnative的指定版本了。`Cannot resolve io.netty:netty-tcnative:2.0.36.Final`

## 2月23日 周三
1.  我已经在关注连接管理了
    1.  [Netty实现单机百万连接和应用级别性能调优](https://blog.csdn.net/qq_33458621/article/details/98775406?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-2&spm=1001.2101.3001.4242)
        1.  可以模拟这试一下
        2.  啥时候可以上云服务器呀？
    2.  如何保持链接？
        1.  放到db中吧，好像不行，如果10w链接，那么db的并发就有10了。
            那就放到redis里？10w个redis记录也不好遍历。
            为啥要管理链接呢？
        2.  google一下呢？

## 2月24日 周四
1.  netty源码编译又失败了：
    `Could not find artifact io.netty:netty-transport-native-kqueue:zip:native-src:4.1.60.Final-SNAPSHOT in nexus-server@alipay (http://mvn.test.alipay.net:8080/artifactory/repo)`
    
```
[ERROR] 
[ERROR] Try downloading the file manually from the project website.
[ERROR] 
[ERROR] Then, install it using the command: 
[ERROR]     mvn install:install-file -DgroupId=io.netty -DartifactId=netty-transport-native-kqueue -Dversion=4.1.60.Final-SNAPSHOT -Dclassifier=native-src -Dpackaging=zip -Dfile=/path/to/file
[ERROR] 
[ERROR] Alternatively, if you host your own repository you can deploy the file there: 
[ERROR]     mvn deploy:deploy-file -DgroupId=io.netty -DartifactId=netty-transport-native-kqueue -Dversion=4.1.60.Final-SNAPSHOT -Dclassifier=native-src -Dpackaging=zip -Dfile=/path/to/file -Durl=[url] -DrepositoryId=[id]
[ERROR] 
[ERROR] 
[ERROR]   io.netty:netty-transport-native-kqueue:zip:4.1.60.Final-SNAPSHOT
[ERROR] 
```

## 2月25日 周五
1.  特别想看一下netty的相关源码
    1.  可惜没有太多时间。


# 三月
## 3月21日 周日
1.  开始做一个心跳小程序-done
2.  用spring-boot+netty+数据库，把数据保留下来
    1.  基本完成了spring-boot + netty
    2.  基本完成了spring-boot + mybatis

  