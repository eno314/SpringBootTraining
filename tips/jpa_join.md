# SpringDataJPA のJOINに関するTIPS

## N + 1 問題

[サンプル（race_result）](../src/main/kotlin/com/github/eno314/spring/training/infrastructure/entity/RaceResult.kt)

下記のような3つのテーブルと結合しているEntityに対して、JpaRepositoryのfindAllを実行すると、自身を取得するのに1回に加えて、結合しているテーブルの数だけクエリが発行される。

レコード数が増えると結構パフォーマンスが悪くなってしまう。

例えば、race_resultが10000件レコードがある場合に全件取得した際のメトリックスは以下

```
Session Metrics {
    3200618 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    28926723 nanoseconds spent preparing 921 JDBC statements;
    1951221505 nanoseconds spent executing 921 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    17367 nanoseconds spent executing 1 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```

この問題を回避したい場合は、Queryアノテーションを使って、JOIN FETCHを使用するといい

[サンプル（RaceResultRepository.k）](../src/main/kotlin/com/github/eno314/spring/training/infrastructure/repository/RaceResultRepository.kt)

以下は同条件でのメトリックス

```
Session Metrics {
    3085255 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    3146249 nanoseconds spent preparing 1 JDBC statements;
    291841348 nanoseconds spent executing 1 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
```
