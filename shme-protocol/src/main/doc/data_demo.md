# 1.卫卫发的报文
```
//登录消息
7E 01 FE 01 36 30 30 30 30 30 30 30 30 36 01 00 1C 15 02 16 10 35 1E 00 00 38 39 38 36 30 34 35 34 30 39 32 30 39 30 32 36 34 35 35 31 D0 7E 


//实时数据消息
7E 02 FE 01 36 30 30 30 30 30 30 30 30 36 01 00 36 15 02 16 10 36 11 01 00 06 EC FF 50 02 66 0C 38 00 00 00 00 02 75 BC DD 08 04 FF 00 00 00 00 00 00 00 00 00 FF FF 00 00 00 00 00 00 00 00 FF 00 00 00 00 00 00 00 F2 7E 


设备每次启动，都是先登录，登录成功之后，开始传送实时消息 
```

2. 数据包定义