# Sunshine Core
A modding library, for multi-loader mod development.

## Features
- Cross-loader abstractions and events
- Registrar API and other registries
- Data Generation API (see `SDataGeneration`)
- Networking API (see `SNetworking` and `SClientNetworking`)
- Some basic class and utilities for blocks, items, etc.

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
    // with Architectury Loom
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-common:${rootProject.sunshine_core_version}"

    // others
    implementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-common:${rootProject.sunshine_core_version}" 


    // Fabric
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-fabric:${rootProject.sunshine_core_version}"


    // NeoForge
    // with ModDevGradle / NeoGradle
    implementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-neoforge:${rootProject.sunshine_core_version}"
    
    // with Architectury Loom
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-neoforge:${rootProject.sunshine_core_version}"
}
```
