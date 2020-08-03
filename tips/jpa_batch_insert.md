# SpringDataJPA + MySQL でBatch Insert (Update / Delete)する

https://github.com/eno314/SpringBootTraining/pull/3

## パフォーマンスチューニングに有効な統計情報を表示する

設定ファイルに以下を追記する

```:yaml
spring:
...
  jpa:
...
    properties:
      hibernate:
        generate_statistics: true
```

## 単純にinsert,update,deleteを大量に実行してみる

[d55e225](https://github.com/eno314/SpringBootTraining/commit/d55e225b8dd59c5930a1df58f45d91eeaa5e0485)

### 実行結果 : insert

| acquiring | preparing | executing | flushes |
|---|---|---|---|
| 約 2 ミリ秒 | 約 182 ミリ秒 | 約 4333 ミリ秒 | 約 2611 ミリ秒 |

```
$ curl localhost:8080/debug/performance/bigInsertQueries                                                                                                 [jpa-batch-insert]
spending time : 5450 ms.%
```

```
Session Metrics {
    2250185 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    181849975 nanoseconds spent preparing 2000 JDBC statements;
    4333282021 nanoseconds spent executing 2000 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    2611180270 nanoseconds spent executing 1 flushes (flushing a total of 1000 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```


### 実行結果 update

2回insertのデバッグを呼び出すことで、updateの検証とする

| acquiring | preparing | executing | flushes |
|---|---|---|---|
| 約 2 ミリ秒 | 約 88 ミリ秒 | 約 4853 ミリ秒 | 約 2260 ミリ秒 |

```
$ curl localhost:8080/debug/performance/bigInsertQueries                                                                                                +[jpa-batch-insert]
spending time : 5696 ms.
```

```
Session Metrics {
    2314935 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    88638813 nanoseconds spent preparing 2000 JDBC statements;
    4853681393 nanoseconds spent executing 2000 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    2259942610 nanoseconds spent executing 1 flushes (flushing a total of 1000 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

### 実行結果 delete

| acquiring | preparing | executing | flushes |
|---|---|---|---|
| 約 3 ミリ秒 | 約 71 ミリ秒 | 約 4793 ミリ秒 | 約 24630 ミリ秒 |

```
$ curl localhost:8080/debug/performance/bigDeleteQueries                                                                                                +[jpa-batch-insert]
spending time : 5543 ms.
```

```
Session Metrics {
    3278651 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    71198604 nanoseconds spent preparing 2000 JDBC statements;
    4792831156 nanoseconds spent executing 2000 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    2463092343 nanoseconds spent executing 1 flushes (flushing a total of 1000 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

## Batch の設定して実行してみる

[1a7c037](https://github.com/eno314/SpringBootTraining/commit/1a7c037ccd9d73483bdc58b3eed3bd2bf799e1e8)

### 実行結果 : insert

| acquiring | preparing | executing | flushes |
|---|---|---|---|
| 約 2 ミリ秒 | 約 23 ミリ秒 | 約 1532 + 49 ミリ秒  | 約 71 ミリ秒 |

```
$ curl localhost:8080/debug/performance/bigInsertQueries                                                                                                 [jpa-batch-insert]
spending time : 1815 ms.
```

```
Session Metrics {
    1636913 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    23440266 nanoseconds spent preparing 1001 JDBC statements;
    1532306980 nanoseconds spent executing 1000 JDBC statements;
    48935765 nanoseconds spent executing 10 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    71311054 nanoseconds spent executing 1 flushes (flushing a total of 1000 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

- flushesが一番効果出ている
- バッチの実行も行われていることが解析結果から分かる
    - `48935765 nanoseconds spent executing 10 JDBC batches;`
    
### 実行結果 : update

| acquiring | preparing | executing | flushes |
|---|---|---|---|
| 約 3 ミリ秒 | 約 16 ミリ秒 | 約 1911 + 818 ミリ秒  | 約 845 ミリ秒 |

```
$ curl localhost:8080/debug/performance/bigInsertQueries                                                                                                 [jpa-batch-insert]
spending time : 2965 ms.
```

```
Session Metrics {
    3065246 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    15747415 nanoseconds spent preparing 1001 JDBC statements;
    1910878948 nanoseconds spent executing 1000 JDBC statements;
    817980030 nanoseconds spent executing 10 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    844586540 nanoseconds spent executing 1 flushes (flushing a total of 1000 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

- insertと比較して、batchやflushesの実行時間が遅め
- バッチ実行はちゃんと行われている
    - どんなSQLでやっているかはちょっと気になったが、show-sqlだと分からず。。

### 実行結果 : delete

| acquiring | preparing | executing | flushes |
|---|---|---|---|
| 約 2 ミリ秒 | 約 16 ミリ秒 | 約 1955 + 495 ミリ秒  | 約 507 ミリ秒 |

```
$ curl localhost:8080/debug/performance/bigDeleteQueries                                                                                                 [jpa-batch-insert]
spending time : 2694 ms.
```

```
Session Metrics {
    1781645 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    16333189 nanoseconds spent preparing 1001 JDBC statements;
    1954971920 nanoseconds spent executing 1000 JDBC statements;
    494530421 nanoseconds spent executing 10 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    506765039 nanoseconds spent executing 1 flushes (flushing a total of 1000 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

- batchやflushesの実行時間はinsertとupdateの中間
- 削除もバッチ実行はちゃんと行われている

## その他

- order_inserts(update/delete) の設定を追加してみたが、解決結果はほぼ一緒だったので、指定しなくても良さそう
    - [aa84314](https://github.com/eno314/SpringBootTraining/commit/aa84314db6b23563e074f31764c944e53df472a0)

## 参考
- https://dzone.com/articles/50-best-performance-practices-for-hibernate-5-amp
- https://qiita.com/rubytomato@github/items/81481485cf49645f8ef5


