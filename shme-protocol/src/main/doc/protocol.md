# 1.协议概述
## 1.1 数据包类型
包类型名 | 说明 | 方向 | 应答 | 校验 | 发送重试 | 备注  
---|---|---|---|---|---|---
登入 | 建立链接 | 上行 | 是 | 是 | 是
信息上报 | 信息上报 | 上行 | 是 | 是 | 否 | 哪些内容需要上报？
参数查询 | | 下行 | 是 | 是 | 是 |
参数设置 | 参数设置 |  下行 | 是 | 是 | 是 
控制命令 | 控制命令 | 下行 | 是 | 是 | 是 
终端校时 | 终端校时 | 上行 | 是 | 是 | 是 | 一直重试
心跳包  |  心跳包  | 上行 | 是 | 否 | 否 | 指定时间没有心跳要断开连接

   