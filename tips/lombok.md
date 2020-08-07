# LombokのTIPS

## RequiredArgsConstructorを使いつつ、Qualifierでコンストラクタインジェクションする

Lombokの設定ファイル(`lombok.config`)に以下の記述を追加する

```
lombok.copyableAnnotations += org.springframework.beans.factory.annotation.Qualifier
```

- サンプル
  - [04fa57c](https://github.com/eno314/SpringBootTraining/commit/04fa57cf8da15c4c77869a6f3bcd5510dc0184a9)
- 参考
  - https://github.com/lc-nyovchev/lombok-spring-qualifier

## Lombokで生成されるメソッドをJacocoのカバレッジから除外する

Lombokの設定ファイル(`lombok.config`)に以下の記述を追加する

```
lombok.addLombokGeneratedAnnotation = true
```

- 参考
  - http://blog.enjoyxstudy.com/entry/2018/01/07/000000
  - https://github.com/jacoco/jacoco/pull/513
