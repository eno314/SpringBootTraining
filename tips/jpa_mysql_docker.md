# Spring Boot Starter Data JPA + MySQL の開発環境をdockerを使って作る

https://github.com/eno314/SpringBootTraining/pull/2

## MySQLのローカル環境をdockerで構築

[1ea09f0](https://github.com/eno314/SpringBootTraining/commit/1ea09f0a3205cfaaaa008429b1ad80470d9eee02)

### docker-compose.ymlでMySQLのDBサーバーを定義する

```:yml
version: '3'
services:
  db:
    image: mysql:5.7
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: spring_boot_training
      MYSQL_USER: dev
      MYSQL_PASSWORD: dev
      TZ: 'Asia/Tokyo'
    command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
    volumes:
      - ./db/mysql:/var/lib/mysql  # MySQLのシステムファイルをマウントする
    ports:
      - "3306:3306"
```

以下の記載で、コンテナのmysqlのシステムファイルが`./db/mysql`以下に置かれるようになり、これを消さない限りdockerを起動し直してもMySQLは同じ状態になる

```
    volumes:
      - ./db/mysql:/var/lib/mysql
```

### DBサーバーの起動とMySQLにログイン


ターミナルでプロジェクト直下に移動し、以下のコマンドを実行すればログインできる

```
# docker-composeをバックグラウンド起動
$ docker-compose up -d

# MySQLにログイン
$ docker-compose exec db bash -c "mysql -h127.0.0.1 -udev -p spring_boot_training"
```

### 参考記事
* (docker-composeでspring-boot+mysqlのアプリケーションを起動)[https://qiita.com/yamii/items/b2b5e6b6a7aff6d590d8]
* (docker-compose でMySQL環境簡単構築)[https://qiita.com/A-Kira/items/f401aea261693c395966]

## Sprig Data JPA でDockerで立てたMySQLに接続する

[5c0ceef](https://github.com/eno314/SpringBootTraining/commit/5c0ceef05b4c9412633a270223a50f62ee4008dc)

### MySQLへの接続設定 application.yml

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/spring_boot_training
    username: dev
    password: dev
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### 適当なentityクラス

```
@Entity
data class Jockey(
    @Id
    val id: String,

    @Column(nullable = false)
    val name: String
)
```

### アプリケーション起動して、テーブルが作成されていることの確認

```
$ docker-compose exec db bash -c "mysql -h127.0.0.1 -udev -p spring_boot_training"

mysql> show tables;
+--------------------------------+
| Tables_in_spring_boot_training |
+--------------------------------+
| jockey                         |
+--------------------------------+
1 row in set (0.00 sec)

mysql> desc jockey;
+-------+--------------+------+-----+---------+-------+
| Field | Type         | Null | Key | Default | Extra |
+-------+--------------+------+-----+---------+-------+
| id    | varchar(255) | NO   | PRI | NULL    |       |
| name  | varchar(255) | NO   |     | NULL    |       |
+-------+--------------+------+-----+---------+-------+
2 rows in set (0.00 sec)
```

## シンプルなSpringアプリケーションを用意

[8867664](https://github.com/eno314/SpringBootTraining/commit/8867664f5343ab4c559b5f2a3ec244b12c3b8c19)

### 動作確認

```
# POSTで登録
$ curl http://localhost:8080/jockey/1 -XPOST -d 'name=藤田菜七子'

# GETで取得
$ curl http://localhost:8080/jockey/1
{"id":"1","name":"藤田菜七子","updatedAt":"2020-08-02T00:43:24.954109"}
```

## docker-composeでSpringアプリケーションも動かす

- [84c1c42](https://github.com/eno314/SpringBootTraining/commit/84c1c422e59678e72f52a55b6f83012a4d660c2e)

### docker-composeでMySQLとSpringアプリケーションを起動

```
$ docker-compose up
```

コンテナ内に必要なパッケージ全部インストールするため、結構時間が掛かる

CIツール上で動かすのに限定し、ローカルで開発する場合は以下のようにする

### MySQLだけdocker-composeで起動し、アプリケーションは通常起動

```
$ docker-compose down

$ docker-compose up -d db

# ここはIntelliJから起動する等でもOK
$ ./gradlew bootRun
```
