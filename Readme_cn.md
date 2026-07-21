[English](./Readme.md) | 简体中文

# Sunshine Core
一个为 Minecraft 跨加载器模组开发的模组库。

## 特性
- 注册 API（详见 `Registrar` 类） 和其他注册项
- 数据生成 API（详见 `SDataGeneration` 类）
- 网络通信 API（详见 `SNetworking` 类（服务端侧）和 `SClientNetworking` 类（客户端侧））

## 版本支持

| MC 版本       | 维护状态   | 最新版本  | 支持的模组加载器         |
|-------------|--------|-------|------------------|
| 1.21~1.21.1 | ✅ 主线版本 | 1.4.3 | Fabric, NeoForge |

## 依赖
| 模组名称       | 模组版本 | 模组加载器  | 下崽链接                                            |
|------------|------|--------|-------------------------------------------------|
| Fabric API | 最新   | Fabric | [Modrinth](https://modrinth.com/mod/fabric-api) |

## 导入依赖
本模组库可以在 [JitPack](https://jitpack.io/#BlockNeko-11/SunshineCore) 上找到。

### 添加仓库
在你的 `build.gradle` 文件中添加：
```gradle
repositories {
    maven {
        url = "https://jitpack.io/"
    }
}
```

### 添加依赖
在你的 `build.gradle` 文件中添加：
```gradle
dependencies {
    // Common
    // 使用 Architectury Loom 时
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-common:${rootProject.sunshine_core_version}"

    // 其他情况时
    implementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-common:${rootProject.sunshine_core_version}" 


    // Fabric
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-fabric:${rootProject.sunshine_core_version}"


    // NeoForge
    // 使用 ModDevGradle 或 NeoGradle 时
    implementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-neoforge:${rootProject.sunshine_core_version}"
    
    // 使用 Architectury Loom 时
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-neoforge:${rootProject.sunshine_core_version}"
}
```

## 贡献
### 环境要求
- Java 21 (Minecraft 1.20.5+ 需要 Java 21)

### 构建
1. 克隆本仓库。
2. 在项目目录下运行 `./gradlew build` 或 `gradlew build`。

然后，你可以在 `fabric/build/libs` 和 `neoforge/build/libs` 找到可以放进 mods 文件夹的 jar 文件。

## 许可证
Sunshine Core 项目遵循 [Apache 2.0 许可证](./LICENSE)。
