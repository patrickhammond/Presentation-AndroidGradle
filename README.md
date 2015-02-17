---

# Gradle: Powering the new Android Build Tools

----
## Introduction

### What is it?
- "Gradle is an advanced build toolkit that manages dependencies and allows you to define custom build logic" (https://developer.android.com/tools/building/plugin-for-gradle.html)
- Google's Android plugin work started in early 2013 and reached 1.0 in Dec 2014
  - Fun fact: Jason Voegele (from Cincinnati!) had the leading (only?) Android Gradle plugin prior to Google's work (https://github.com/jvoegele/gradle-android-plugin)

### Why should I care?
- Support
  - Gradle/Android Studio are supported tools moving forward; Ant/Eclipse will not receive significant support.  If you stay on these you're going to have a bad day.
  - Alternative: Maven support is good, but lags behind tooling updates

- Goals of the new Build System (http://tools.android.com/tech-docs/new-build-system/user-guide)
  - Make it easy to reuse code and resources
  - Make it easy to create several variants of an application, either for multi-apk distribution or for different flavors of an application
  - Make it easy to configure, extend and customize the build process
  - Good IDE integration

- When can I use it in my apps?
  - Now!  Android Studio and the Android Gradle plugin recently reached 1.0 (December 2014)
  - "But I tried it before and my project was constantly breaking with updates" (http://i.giphy.com/ONAYuLIfsk8tq.gif).  Good news: breaking changes should be rare now (http://tools.android.com/tech-docs/new-build-system/version-compatibility)

----

## Getting Started

### Requirements
- Must use Android Studio (unsure if vanilla IntelliJ will work)
TODO 

### Different (standard) project structure
TODO 

### The different Gradle files
multiple build.gradle files, settings.gradle, gradlew, gradle.properties, gradle wrapper, etc
TODO 

### Example #1: Add Play Services and the support library to an app
- TODO Snippet

### Help! I'm using Eclipse
- If you're using version control and want to preserve history DO NOT use the tooling migration scripts to migrate to Gradle; they won't preserve history (as of Jan 2014).
  - Drop down to the CLI and use the VCS commands to move things around (ex: `git mv AndroidManifest app/src/main/AndroidManifest.xml`)
  - TODO Sample migration snippet
      - TODO: Gradle wrapper https://github.com/mgrzechocinski/dagger2-example/blob/master/build.gradle
- If you're used to having library projects your app depends on open in your workspace, those are correctly managed now without needing to awkwardly keep them open just to build your app.

### Help! I don't know what options are available
1. Go to https://developer.android.com/tools/building/plugin-for-gradle.html
1. Download the "Plugin Language Reference" from the right side of the page.
1. Unzip.
1. Open up index.html
1. Browse like you would JavaDocs.

Having a slight familiarity with Groovy (http://groovy.codehaus.org/) syntax also helps.

### Advice

#### Use the Gradle wrapper and make sure it is checked into VCS
TODO 

#### Gradle daemon
TODO 

### Outstanding Issues
- AAR vs APKLIB incompatibility is still an issue
- Tooling errors can sometimes (frequently) be cryptic
- Thorough documentation and relevant (recent) StackOverflow posts still improving

----

## Interesting features

### Build flavors (paid vs free, Google vs Amazon)

### Build variants (production vs debug)

### Build time constants

### Scriptability

### More
- shrinkResources

----

## Easy power ups

### Better managing ProGuard Hell
https://github.com/krschultz/android-proguard-snippets
- Not dynamic, but improves readability

### Quality tools

##### Lint
TODO 

#### FindBugs 
TODO 

#### PMD
TODO 

#### CPD
TODO 

----

## Useful links

### Documentation
- https://gradle.org
- https://developer.android.com/sdk/installing/studio-build.html
- https://developer.android.com/tools/building/plugin-for-gradle.html
- http://tools.android.com/tech-docs/new-build-system/user-guide

### Videos
- "Dive into the Gradle-based Android Build System" (https://www.youtube.com/watch?v=popb1n1_fA8)

### Productivity
- http://gradleplease.appspot.com

### Interesting Gradle files/snippets
- Preventing wild card dependencies (https://gist.github.com/JakeWharton/2066f5e4f08fbaaa68fd)
- Project configuration utilizing interesting features (https://github.com/LiveTyping/u2020-mvp/blob/master/app/build.gradle)
- Adding Android support annotations to a plain Java module (https://gist.github.com/JakeWharton/e8a3685feb6a94b23393)
