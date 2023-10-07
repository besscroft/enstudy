<h1 align="center">
<img width="28" src="./doc/image/maskable-icon.png">
嗯学英语
</h1>

<p align="center">
学英语，就嗯学！
</p>
<p align="center">
  <img src="https://img.shields.io/badge/%E5%BC%80%E5%8F%91%E8%BF%9B%E5%BA%A6-%E5%BC%80%E5%8F%91%E4%B8%AD-brightgreen?style=flat-square" alt="开发进度">
  <a href="https://github.com/besscroft/enstudy/blob/master/LICENSE"><img src="https://img.shields.io/github/license/besscroft/enstudy?style=flat-square" alt="许可证"></a>
  <img src="https://img.shields.io/github/repo-size/besscroft/enstudy?style=flat-square&color=328657" alt="存储库大小">
</p>


<p align="center">
  <img width="360" src="./doc/image/enstudy.png">
</p>

### 代码贡献

[提出新想法 & 提交 Bug](https://github.com/besscroft/enstudy/issues/new) | [Fork & Pull Request](https://github.com/besscroft/enstudy/fork)

enstudy 欢迎各种贡献，包括但不限于改进，新功能，文档和代码改进，问题和错误报告。

### 如何部署

#### 数据库

`doc/sql/enstudy-schema.sql` 文件是表结构，可以先创建表，`doc/sql/enstudy-data.sql` 是数据，创建表之后导入。

`doc/mongo/enstudy.gz` 是存储在 MongoDB 的单词详细数据，先创建 `book` 集合，然后你需要使用 MongoRestore 命令来进行导入。

默认登录用户 `enstudy`，默认登录密码 `666666`

#### 后端

你可以用我打包好的 Docker 镜像进行部署，latest 版本对应最新版本的代码，你也可以根据自己的需求来选择对应的版本。

* 示例命令：

```shell
docker run -d --name enstudy \
  -p 8080:8080 \
  -e SPRING_PROFILE="mysql" \
  -e JAVA_OPTS="-Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8" \
  -e DB_URL="localhost:3306"
  -e DB_NAME="enstudy"
  -e DB_USERNAME="root"
  -e DB_PASSWORD="666666"
  -e DB_SSL="false"
  -e MONGO_URI="mongodb+srv://用户名:密码@cluster.mongodb.net/?ssl=true&authSource=admin"
  -e MONGO_DATABASE="enstudy"
  -e MONGO_USERNAME="mongoadmin"
  -e MONGO_PASSWORD="password"
  -e MONGO_SSL="false"
  -e MONGO_AUTHENTICATION_DATABASE="admin"
  -e REDIS_DATABASE="0"
  -e REDIS_HOST="localhost"
  -e REDIS_PORT="6379"
  -e REDIS_PASSWORD="password"
  -e REDIS_SSL="false"
  besscroft/enstudy:latest
```

> 注意，请根据实际情况修改配置。比如端口，数据库版本以及其它配置等。

| 名称          | 说明            | 默认值                                                           |
| ------------- |---------------|---------------------------------------------------------------|
| SPRING_PROFILE | 配置文件版本        | mysql                                                         |
| JAVA_OPTS | JVM 命令行参数     | -Xms512m -Xmx512m -Duser.timezone=GMT+08 -Dfile.encoding=UTF8 |
| DB_URL    | 数据库连接地址       | localhost:3306                                                             |
| DB_NAME    | 数据库名称         | enstudy                                                             |
| DB_USERNAME    | 数据库连接用户名      | root                                                             |
| DB_PASSWORD    | 数据库连接密码       | 666666                                                             |
| DB_SSL    | 是否启用 SSL      | false                                                         |
| MONGO_URI    | MongoDB URI   | mongodb+srv://用户名:密码@cluster.mongodb.net/?ssl=true&authSource=admin                                                              |
| MONGO_DATABASE    | MongoDB 数据库名称 | enstudy                                                              |
| MONGO_USERNAME    | MongoDB 连接用户名 | mongoadmin                                                              |
| MONGO_PASSWORD    | MongoDB 连接密码    | password                                                              |
| MONGO_SSL    | 是否启用 SSL      | false                                                             |
| MONGO_AUTHENTICATION_DATABASE | 用于身份验证的数据库名称  | admin                                                              |
| REDIS_DATABASE    | Redis数据库索引    | 0                                                             |
| REDIS_HOST    | Redis服务器地址    | localhost                                                              |
| REDIS_PORT    | Redis服务器连接端口  | 6379                                                              |
| REDIS_PASSWORD    | Redis服务器连接密码  | password                                                              |
| REDIS_SSL    | 是否启用 SSL      | false                                                             |
| DOC_ENABLE    | api 文档启用      | false                                                             |
| DOC_UI_ENABLE    | swagger ui 启用      | false                                                             |

> 端口可以自定义，docker 容器内的程序端口为 8080，你可以自定义对应的宿主机的端口，以及网络类型。请注意，容器内连接主机端口，可以使用 ip 172.17.0.1。
> 如果部署在 k8s 中，增加实例副本也是没问题的，注意配置好 Service 即可，网络的配置也和 Docker 不一样需要注意！

#### 前端

直接 Fork 本项目，然后更改 `heming-web/.env.production` 文件下的 `PROXY_URL` 的值，指向后端地址就行了。
你用 PostMan 能请求成功的地址填上去即可，然后部署到 Vercel，采用 `pnpm run build` 命令，部署成功后就能访问了！
当然，直接在你自己的 nodejs 部署也是一样的。

### 在线开发

你可以使用 Gitpod 进行在线开发：

<p><a href="https://gitpod.io/#https://github.com/besscroft/enstudy" rel="nofollow"><img src="https://camo.githubusercontent.com/1eb1ddfea6092593649f0117f7262ffa8fbd3017/68747470733a2f2f676974706f642e696f2f627574746f6e2f6f70656e2d696e2d676974706f642e737667" alt="Open in Gitpod" data-canonical-src="https://gitpod.io/button/open-in-gitpod.svg" style="max-width:100%;"></a></p>

或者克隆到本地开发:

```shell
git clone https://github.com/besscroft/enstudy.git

cd heming-web

pnpm i

pnpm run dev
```

如果您有任何建议，欢迎反馈！

您可以将服务部署在 `DigitalOcean` ，如果你愿意走我的邀请链接注册，可以获得100美元的信用额度。

<a href="https://www.digitalocean.com/?refcode=6841be7284cc&utm_campaign=Referral_Invite&utm_medium=Referral_Program&utm_source=badge"><img src="https://web-platforms.sfo2.cdn.digitaloceanspaces.com/WWW/Badge%201.svg" alt="DigitalOcean Referral Badge" /></a>

### 感谢

本项目使用 JetBrains 的开源许可证，基于 IntelliJ IDEA 开发，感谢！

![JetBrains 徽标（主要） logo](https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg)

![IntelliJ IDEA logo](https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA.svg)
