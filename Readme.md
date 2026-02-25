# Sunshine Core
An api library for multi-loader mods.

## Installation (Developers)
Latest maven version:
[![](https://jitpack.io/v/BlockNeko-11/SunshineCore.svg)](https://jitpack.io/#BlockNeko-11/SunshineCore)

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
    // Common (with Architectury Loom)
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-common:${rootProject.sunshine_core_version}"


    // Fabric
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-fabric:${rootProject.sunshine_core_version}"


    // Forge (1.20-)
    // with ForgeGradle
    implementation fg.deobf("com.github.BlockNeko-11.SunshineCore:sunshine-core-forge:${rootProject.sunshine_core_version}")
    
    // with Architectury Loom
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-forge:${rootProject.sunshine_core_version}"


    // NeoForge (1.21+) 
    // with ModDevGradle / NeoGradle
    implementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-neoforge:${rootProject.sunshine_core_version}"
    
    // with Architectury Loom
    modImplementation "com.github.BlockNeko-11.SunshineCore:sunshine-core-neoforge:${rootProject.sunshine_core_version}"
}
```
