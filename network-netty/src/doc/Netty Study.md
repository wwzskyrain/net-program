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
5.  