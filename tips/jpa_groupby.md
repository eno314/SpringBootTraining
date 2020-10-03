# SpringDataJPA で Entityとは別の項目を取得する

https://github.com/eno314/SpringBootTraining/pull/10

## 概要

`GROUP BY`や`MAX`、`MIN`といったSQLの関数を使って、Entityとは別の項目をJPAで取得する方法

## 実装するもの

[RaceResult](../src/main/kotlin/com/github/eno314/spring/training/infrastructure/entity/RaceResult.kt) から日毎で騎手毎の最高成績を取得する

SQLで取得する場合は以下のようになる

```
mysql> desc race_result;
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| id              | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| result          | int(11)      | NO   |     | NULL    |                |
| updated_at      | datetime(6)  | NO   |     | NULL    |                |
| jockey_id       | varchar(255) | NO   | MUL | NULL    |                |
| race_id         | bigint(20)   | NO   | MUL | NULL    |                |
| thoroughbred_id | varchar(255) | NO   | MUL | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
6 rows in set (0.00 sec)

mysql> SELECT CAST(`updated_at` AS DATE) date, MIN(result) best_result, jockey_id FROM race_result GROUP BY CAST(`updated_at` AS DATE), jockey_id;
+------------+-------------+-----------+
| date       | best_result | jockey_id |
+------------+-------------+-----------+
| 2020-08-29 |           1 | jockey_1  |
+------------+-------------+-----------+
10 rows in set (0.04 sec)
```

## Interface-based Projections を使ってマッピングする

### SQLの結果を返すためのinterfaceを用意する

SQLのSELECT文で指定する各項目をinterfaceのgetterで定義する

```
interface DailyJockeyResult {

    fun getDate(): LocalDate

    fun getBestResult(): Int

    fun getJockeyId(): String
}
```

### repositoryのqueryアノテーションを使って上記のinterfaceを返すメソッドを用意する

```
    @Query(
        nativeQuery = true,
        value = "SELECT CAST(`updated_at` AS DATE) date, MIN(result) bestResult, jockey_id jockeyId" +
            " FROM race_result" +
            " GROUP BY CAST(`updated_at` AS DATE), jockey_id"
    )
    fun findAllDailyJockeyResult(): List<DailyJockeyResult>
```

## 参考
- https://qiita.com/kagamihoge/items/b299d17a20f86f03562c
- https://www.baeldung.com/spring-data-jpa-projections#interface-based-projections
- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.interfaces


