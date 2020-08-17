# ModelMapperのTIPS

## Builderパターンのクラスをマッピングする

[9b50775](https://github.com/eno314/SpringBootTraining/commit/9b50775be68782829e48ad0ed402949032acdfb7)

ModelMapper の configuration に `NameTransformers.builder()` と `NamingConventions.builder()` の設定をすればよい

```
    ModelMapper().apply {
        configuration.apply {
            destinationNameTransformer = NameTransformers.builder()
            destinationNamingConvention = NamingConventions.builder()
        }
    }
```

- 参考
  - https://hepokon365.hatenablog.com/entry/2019/02/28/205009

- ちなみに、MapStructを使えば、Builderを意識することなくマッピングしてくれる
  - [359a69b](https://github.com/eno314/SpringBootTraining/commit/359a69b2724a7377c19800c8610538c4b4f742c2)
