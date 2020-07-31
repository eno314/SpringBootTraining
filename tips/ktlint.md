# [ktlint](https://github.com/pinterest/ktlint) をGradleプロジェクトに追加する

## [ktlint#-with-gradle](https://github.com/pinterest/ktlint#-with-gradle)
Gradleのプロジェクトに追加するだけなら [jlleitschuh/ktlint-gradle](https://github.com/jlleitschuh/ktlint-gradle) か [jeremymailen/kotlinter-gradle](https://github.com/jeremymailen/kotlinter-gradle) のGradle pluginsを追加するだけでいい

## [ktlint-gradle](https://github.com/jlleitschuh/ktlint-gradle)

### [How to use](https://github.com/jlleitschuh/ktlint-gradle#how-to-use)

デフォルトの設定のままでいいなら、`build.gradle`等に以下の記述を追加するだけ。

```
plugins {
  id "org.jlleitschuh.gradle.ktlint" version "<current_version>"
}
```

`ktlintCheck`のタスクでチェック、`ktlintFormat`のタスクで自動フォーマットする

`org.jlleitschuh.gradle.ktlint-idea`のプラグインも追加すれば、`ktlintApplyToIdea`のタスクを実行することで、IntelliJのKotlinのスタイルがktlintと同じ設定になるので、IntelliJのフォーマッタを実行すればktlintに合わせてくれる

```
plugins {
  id "org.jlleitschuh.gradle.ktlint" version "<current_version>"
  id "org.jlleitschuh.gradle.ktlint-idea" version "<current_version>"
}
```

`ktlintApplyToIdea`のタスクを実行することで、IntelliJのKotlinのスタイルがktlintと同じ設定になるので、IntelliJのフォーマッタを実行すればktlintに合わせてくれる

### 注意点
[minimal-supported-versions](https://github.com/jlleitschuh/ktlint-gradle#minimal-supported-versions) に気をつける（特にgradleのバージョン）
古めのプロジェクトに入れようとしてうまく行かなったら、これが原因の可能性がある
