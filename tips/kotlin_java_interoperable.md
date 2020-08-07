# KotlinとJavaの相互運用

## KotlinプロジェクトにJavaファイルを含める

`build.gradle.kts` に以下の記述を追加することで、kotlinディレクトリ以下でもJavaファイルを配置できるようになる

```:kotlin
sourceSets.main {
    java.srcDirs("src/main/kotlin")
}
```

補足 : javaディレクトリ以下は、デフォルトでJavaファイルとKotlinファイルの両方が動く
