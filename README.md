# RiceBot 大米机器人
## 功能介绍
大米机器人实现了QQ官方机器人API，封装为了`rice-bot-sdk`，同时封装了插件接口，开发者可依赖`rice-bot-sdk`，实现`RiceBotPlugin`接口实现自己的机器人功能，将打包产物放在`rice-bot-x.x.x/plugins`目录下，重启机器人即可实现在不修改机器人本体的情况下添加插件功能

目录结构
```text
rice-bot-x.x.x
  ├─rice-bot-x.x.x.jar 主程序
  ├─application.yml    配置文件
  ├─start.sh           Linux启动脚本
  ├─stop.sh            Linux停止脚本
  ├─start.bat          Windows启动脚本
  ├─log                日志
  ├─lib                依赖
  └─plugins            插件
  
```
## 使用方法
1. 在[**QQ开放平台**](https://q.qq.com/)创建机器人，获取 `AppId`、`AppSecret`，并配置到环境变量`QQ_BOT_APP_ID`、`QQ_BOT_APP_SECRET`
2. 在[**高德开放平台**](https://lbs.amap.com/)创建应用，获取密钥，并配置到环境变量 `AMAP_KEY`
2. 安装 [**JDK 21**](https://www.oracle.com/cn/java/technologies/downloads/#java21) 
3. 安装 Redis 
4. 克隆项目，在项目根目录下执行 mvn package，打包产物的路径为 `dist/rice-bot-x.x.x`
5. 启动项目
   - Windows 用户双击 `start.bat` 启动项目
   - Linux 用户执行 `sh start.sh` 启动项目
> Windows下环境变量配置在系统中，Linux环境变量填写在 start.sh 中