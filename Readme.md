EN | [简体中文](./Readme_cn.md)

# Sunshine Core
A Minecraft modding library, provides some tools and APIs for multi-loader modding.

## Features
- Registrar API and other registries
- Data Generation API (see `SDataGeneration`)
- Networking API (see `SNetworking` (for Server Side) and `SClientNetworking` (for Client Side))

## Version Supporting

| Minecraft Version | Status     | Latest Version | Supported Loaders |
|-------------------|------------|----------------|-------------------|
| 1.21~1.21.1       | ✅ Mainline | 1.4.1          | Fabric, NeoForge  |

## Installation (Developers)
The library is published on [JitPack](https://jitpack.io/#BlockNeko-11/SunshineCore).

### Add repository
In your `build.gradle`: 
```gradle
repositories {
    maven {
        url = "https://jitpack.io/"
    }
}
```

### Add dependency
In your `build.gradle`:
```gradle
dependencies {
    // Common
    // if you use Architectury Loom
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-common:${rootProject.sunshine_core_version}"

    // other situations
    implementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-common:${rootProject.sunshine_core_version}" 


    // Fabric
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-fabric:${rootProject.sunshine_core_version}"


    // NeoForge
    // if you use ModDevGradle / NeoGradle
    implementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-neoforge:${rootProject.sunshine_core_version}"
    
    // if you use Architectury Loom
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-neoforge:${rootProject.sunshine_core_version}"
}
```
