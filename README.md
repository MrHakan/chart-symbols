# Chart Symbols

Android-first visual learning app for nautical chart symbols.

The starter release focuses on a fast, offline-friendly learning loop:

- visual symbol cards with Turkish explanations and English terminology;
- search and category filters;
- detail pages with meaning, recognition cues and memory tips;
- a working multiple-choice visual quiz;
- progress feedback for discovered cards;
- a GitHub Actions workflow for debug builds and `android-v*` releases.

The illustrations in this starter are intentionally simplified learning diagrams. They are not official reproductions of chart symbols and must not replace a current chart, official INT 1/Chart 1 publication, notices to mariners, or the vessel's procedures.

## Run locally

```bash
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
```

The project uses native Android with Kotlin and Jetpack Compose:

- min SDK: 26
- target/compile SDK: 35
- Java/Kotlin target: 17
- package: `com.mrhakan.chartsymbols`

## Release workflow

Pushing a tag such as `android-v0.1.0` builds a release APK and AAB and creates a GitHub release. The initial bootstrap can also be triggered by a `[release]` commit on `main`; after that, normal `android-v*` tags remain the preferred release path. The current starter uses the debug signing key for sideloadable builds; a private upload key should be wired through repository secrets before Play Store distribution.

## Content roadmap

1. Replace the starter illustrations with a reviewed, source-grounded symbol dataset.
2. Add spaced repetition, starred cards and per-category progress.
3. Add a full-screen “identify the symbol” mode and timed deck mode.
4. Add English/Turkish language selection and offline content versioning.

## Reference

The content model is designed around the IHO S-4 associated INT 1 publication family. Check the current official publication and local chart authority before finalizing educational content:

<https://iho.int/en/standards-and-specifications>
