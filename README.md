
Download
--------
Add the JitPack repository to your root build.gradle:

```groovy
	allprojects {
		repositories {
			maven { url "https://jitpack.io" }
		}
	}
```
Add the Gradle dependency:
```groovy
	dependencies {
		compile 'com.github.amitsahni.sparta:base:1.0.2' // UI
        compile 'com.github.amitsahni.sparta:adapter:1.0.2' // Adapter
	}
```