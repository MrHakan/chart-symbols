# Chart Symbols

Android-first visual learning app for nautical chart symbols.

The starter release focuses on a fast, offline-friendly learning loop:

- visual symbol cards with Turkish explanations and English terminology;
- search and category filters;
- detail pages with meaning, recognition cues and memory tips;
- a working multiple-choice visual quiz;
- progress feedback for discovered cards;
- a GitHub Actions workflow for debug builds and `android-v*` releases.

The app now uses original vector redraws in the visual language of paper nautical charts: black construction lines, magenta aids/restrictions and blue bathymetry. They are designed for visual learning and are not official IHO/UKHO reproductions or navigation-safe chart data. They must not replace a current chart, official INT 1/Chart 1 publication, notices to mariners, or the vessel's procedures.

## Symbol source and licensing note

The names, meanings and visual conventions are reviewed against the official IHO standards page and the U.S. Office of Coast Survey's U.S. Chart No. 1 reference. The repository does not copy pages, scans or graphic assets from IHO/UKHO publications. Exact official IHO/UKHO artwork requires permission from the relevant rights holder.

- IHO standards and publications: <https://iho.int/en/standards-and-specifications>
- U.S. Chart No. 1 reference: <https://nauticalcharts.noaa.gov/publications/us-chart-1.html>
- NOAA chart-symbol FAQ and public-domain historical-chart note: <https://nauticalcharts.noaa.gov/learn/faq.html>

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

Pushing a tag such as `android-v0.2.0` builds a release APK and AAB and creates a GitHub release. The configured bootstrap can also be triggered by a `[release]` commit on `main`; after that, normal `android-v*` tags remain the preferred release path. The current starter uses the debug signing key for sideloadable builds; a private upload key should be wired through repository secrets before Play Store distribution.

## Content roadmap

1. Expand the reviewed, source-grounded symbol catalog beyond the starter set.
2. Add spaced repetition, starred cards and per-category progress.
3. Add a full-screen “identify the symbol” mode and timed deck mode.
4. Add English/Turkish language selection and offline content versioning.

## Reference

The content model is designed around the IHO S-4 associated INT 1 publication family. Check the current official publication and local chart authority before finalizing educational content:

<https://iho.int/en/standards-and-specifications>
