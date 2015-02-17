class: center, middle, title-slide
# Gradle and the new Android Build System
**Patrick Hammond**  
patrick@madebyatomicrobot.com  
@patrickhammond
.brand-logo[Atomic Robot]
.license[.name[Creative Commons Attribution-ShareAlike 4.0 International] .logo[CC-BY-SA]]

---

class: center
## You can find the presentation and sample code on GitHub:
https://github.com/patrickhammond/Presentation-AndroidGradle
![QR Code](http://chart.googleapis.com/chart?cht=qr&chs=250x250&choe=UTF-8&chld=H&chl=http://goo.gl/y83O9O)

### Sample code is aimed to drive discussion and show concepts only. It is not polished so use with caution.

---

.wide-left-column[
.portrait[![Portrait](portrait.jpeg)]
]
.thin-right-column[
## Who am I?
- 5+ years building Android apps
- Atomic Robot founding partner
- Husband, Dad, Geek, Insomniac, Cook, Capsaicin Lover, Craft Beer Fan
]

---

class: center, middle
# Overview
## *"What is it, why should I care, and when can I use it?"*

---

.left-column[
## What is it?
]
.right-column[
.center[![Gradle logo](gradle_logo.gif)]
> ## "Gradle is an advanced build toolkit that manages dependencies and allows you to define custom build logic."
]
.footnote[https://developer.android.com/tools/building/plugin-for-gradle.html]

---

.left-column[
## What is it?
]
.right-column[
## History
- ### Jason Voegele maintained an Android Gradle plugin prior to Google's work   
- ### Android Gradle plugin work started in early 2013 with the intent to replace Ant and Eclipse
- ### Finally left beta with Version 1.0 in Dec 2014
]
.footnote[https://github.com/jvoegele/gradle-android-plugin] 

---

.left-column[
## What is it?
## Why should I care?
]
.right-column[
.center[![Eclipse Ant meme](meme_eclipse_ant_bad_time.jpg)]
]

---

.left-column[
## What is it?
## Why should I care?
]
.right-column[
## Support
- ### Gradle/Android Studio are supported tools moving forward
- ### Ant/Eclipse will **not** receive significant support in the future
- ### Alternative: Maven support is good but lags behind Android tooling updates
  - ### Poor Android/Eclipse/Maven integration
]

---

.left-column[
## What is it?
## Why should I care?
]
.right-column[
## New Build System Goals
- ### Make it easy to reuse code and resources
- ### Make it easy to create several variants of an application, either for multi-apk distribution or for different flavors of an application
- ### Make it easy to configure, extend and customize the build process
- ### Good IDE integration
]
.footnote[http://tools.android.com/tech-docs/new-build-system/user-guide] 

---

.left-column[
## What is it?
## Why should I care?
## When can I use it?
]
.right-column[
> ### "I tried it before and my project broke with every plugin update..."
--Any Android developer who used the Android Gradle plugin before v1.0

.center[![Pain meme](pain.gif)]
]

---

.left-column[
## What is it?
## Why should I care?
## When can I use it?
]
.right-column[
## Great news!
- ### No longer in beta (Dec 2014)
- ### Breaking changes should now be rare
> ### "Starting with Android Studio 1.0 and the Gradle plugin for Android 1.0, compatibility is a critical requirement."
]
.footnote[http://tools.android.com/tech-docs/new-build-system/version-compatibility]

---

class: center, middle
# Getting Started
## *"What do I need, why are things different, what are all the new files, and how do I start making it work for me?"*

---

.left-column[
## Software
]
.right-column[
- ### Android SDK, Java, etc  (the usual suspects)
- ### Must use Android Studio (1.0+)
  - ### IntelliJ probably works too
  - ### Eclipse won't work (Feb 2015)
]

---

.left-column[
## Software
## Project
]
.right-column[
### Standard project structure
```
/yourApp
    app/
        src/
            main/
                java/
                    ...
                res/
                    ...
                AndroidManifest.xml
            androidTest/
                java/
                    ...
        build.gradle
        proguard-rules.pro
    gradle/wrapper/
        gradle-wrapper.jar
        gradle-wrapper.properties
    build.gradle
    settings.gradle
    gradlew
    gradlew.bat
    gradle.properties
```
### Yikes!  Not as scary as it looks...
]

---

.left-column[
## Software
## Project
]
.right-column[
### Directories
```
*/yourApp                                Project
*   app/                                App module
*       src/                            Code goes here
*           main/                       Main variant
                java/                   
                    ...
                res/
                    ...
                AndroidManifest.xml
            androidTest/
                java/
                    ...
        build.gradle
        proguard-rules.pro
    gradle/wrapper/
        gradle-wrapper.jar
        gradle-wrapper.properties
    build.gradle
    settings.gradle
    gradlew
    gradlew.bat
    gradle.properties
```

- Multi-project configuration is recommended to support, library projects, Wear apps, etc
- Modules follow the Standard Directory Layout
- We'll talk more about the "main variant" later
]

---

.left-column[
## Software
## Project
]
.right-column[
### Code, resources, manifests, etc
```
/yourApp                                Project
    app/                                App module
        src/                            Code goes here
            main/                       Main variant
*               java/                   Code
*                   ...                 Code
*               res/                    Code
*                   ...                 Code
*               AndroidManifest.xml     Code
            androidTest/
                java/
                    ...
        build.gradle
*       proguard-rules.pro              Proguard
    gradle/wrapper/
        gradle-wrapper.jar
        gradle-wrapper.properties
    build.gradle
    settings.gradle
    gradlew
    gradlew.bat
    gradle.properties
```

- You can sometimes wholesale move an existing Eclipse project into `app/src/main` and be most of the way through a migration.
]

---

.left-column[
## Software
## Project
]
.right-column[
### Automated tests
```
/yourApp                                Project
    app/                                App module
        src/                            Code goes here
            main/                       Main variant
                java/                   Code
                    ...                 Code
                res/                    Code
                    ...                 Code
                AndroidManifest.xml     Code
*           androidTest/                Tests
*               java/                   Tests
*                   ...                 Tests
        build.gradle
        proguard-rules.pro              Proguard
    gradle/wrapper/
        gradle-wrapper.jar
        gradle-wrapper.properties
    build.gradle
    settings.gradle
    gradlew
    gradlew.bat
    gradle.properties
```

- Automated tests no longer have a dedicated project, they live side-by-side your app code in a special `androidTest` directory.

]

---

.left-column[
## Software
## Project
]
.right-column[
### Project config files
```
/yourApp                                Project
    app/                                App module
        src/                            Code goes here
            main/                       Main variant
                java/                   Code
                    ...                 Code
                res/                    Code
                    ...                 Code
                AndroidManifest.xml     Code
            androidTest/                Tests
                java/                   Tests
                    ...                 Tests
*       build.gradle                    Gradle project file
        proguard-rules.pro              Proguard
    gradle/wrapper/
        gradle-wrapper.jar
        gradle-wrapper.properties
*   build.gradle                        Gradle project file
*   settings.gradle                     Gradle project file
    gradlew
    gradlew.bat
    gradle.properties
```

- `app/build.gradle` - App specific build and config
- `build.gradle` - Project wide build and config
- `settings.gradle` - Declares the project modules
]

---

.left-column[
## Software
## Project
]
.right-column[
### Gradle wrapper files
```
/yourApp                                Project
    app/                                App module
        src/                            Code goes here
            main/                       Main variant
                java/                   Code
                    ...                 Code
                res/                    Code
                    ...                 Code
                AndroidManifest.xml     Code
            androidTest/                Tests
                java/                   Tests
                    ...                 Tests
        build.gradle                    Gradle project file
        proguard-rules.pro              Proguard
*   gradle/wrapper/                     Gradle wrapper
*       gradle-wrapper.jar              Gradle wrapper
*       gradle-wrapper.properties       Gradle wrapper
    build.gradle                        Gradle project file
    settings.gradle                     Gradle project file
*   gradlew                             Gradle wrapper
*   gradlew.bat                         Gradle wrapper
    gradle.properties                   
```

- `gradlew/gradlew.bat` - executable scripts to install/run Gradle
- `gradle/wrapper/gradle-wrapper.properties` - occasionally needs modified when updating the Android plugin version

]

---

.left-column[
## Software
## Project
]
.right-column[
### Gradle config
```
/yourApp                                Project
    app/                                App module
        src/                            Code goes here
            main/                       Main variant
                java/                   Code
                    ...                 Code
                res/                    Code
                    ...                 Code
                AndroidManifest.xml     Code
            androidTest/                Tests
                java/                   Tests
                    ...                 Tests
        build.gradle                    Gradle project file
        proguard-rules.pro              Proguard
    gradle/wrapper/                     Gradle wrapper
        gradle-wrapper.jar              Gradle wrapper
        gradle-wrapper.properties       Gradle wrapper
    build.gradle                        Gradle project file
    settings.gradle                     Gradle project file
    gradlew                             Gradle wrapper 
    gradlew.bat                         Gradle wrapper
*   gradle.properties                   Gradle config
```

- Tweak this if you need to adjust Gradle environment or execution values (ex: you need to increase the max JVM heap size)
]

---

.left-column[
## Software
## Project
## Example
]
.right-column[
- ### Lets do this
    - #### Look at the three main Gradle project files
        - ##### `dependencies`
        - ##### `android`
        - ##### `repositories`
        - ##### `buildscript`
    - #### Add Play Services and the support library to our app
        - `compile 'com.android.support:support-v4:21.0.3'`
        - `compile 'com.google.android.gms:play-services-base:6.5.87'`
]

---

class: center, middle
# Migrating from Eclipse
## *"I'm sold! How do I get off Eclipse?"*

---

.left-column[
## Version Control
]
.right-column[
- ### If you're using version control and want to preserve history avoid the in-tool migration scripts
  - #### Those migration tools **will not** preserve VCS history (as of Jan 2014)
  - #### Drop down to the command line and use the VCS commands to move things around
  - #### Example:   
  `git mv AndroidManifest app/src/main/AndroidManifest.xml`
- ### Move your code, resources, manifests into their final locations
]

---

.left-column[
## Version Control
## Gradle files
]
.right-column[
- ### If you're not using migration tool
    - #### Create a blank Android project in Android Studio (using your app's package name, etc)
    - #### Copy all the Gradle files over to the project being migrated
    - #### Is this a lame way to do a migration: **very**
        - ##### There are only a few files to move
        - ##### You will likely only do this once (or very infrequently)
- ### If you are using the migration tool this should be taken care of for you
]

---

.left-column[
## Version Control
## Gradle files
## Tool Differences
]
.right-column[
- ### Android Studio vs Eclipse: this is its own talk!
- ### Biggest development workflow differences
    - #### Figure out where the "Sync Project with Gradle Files" menu option is in Android Studio
    - #### No longer need to manually manage JARs, etc (except for some 3rd party libraries...)
    - #### No longer need to open up library projects in your workspace just to build your app
    - #### Keyboard shortcuts all different (there is an "Eclipse" mode)
    - #### No incremental compilation (ie: the realtime Errors view in Eclipse)
]

---

class: center, middle
# Using the Android Plugin
## *"How do I really start using this thing?"*

---

.left-column[
## Syntax
]
.right-column[
### Why does this look different?
- ### Gradle files are written in the Groovy language.
- ### Having a slight familiarity with the Groovy language can be helpful for more advanced project setup.
]
.footnote[http://groovy.codehaus.org]

---

.left-column[
## Syntax
## Plugin Docs
]
.right-column[
### There are lots of configuation options!
### Recommend you download the documentation because it isn't accessible in Android Studio.
### Steps:
1. Go to https://developer.android.com/tools/building/plugin-for-gradle.html
1. Download the "Plugin Language Reference" from the right side of the page.
1. Unzip.
1. Open up index.html
1. Browse like you would JavaDocs.
]

---

.left-column[
## Syntax
## Plugin Docs
## Gradle Wrapper
]
.right-column[
### Use the Gradle wrapper and make sure it is checked into VCS
]

---

.left-column[
## Syntax
## Plugin Docs
## Gradle Wrapper
## Gradle Daemon
]
.right-column[
### Gradle daemon
]

---

.left-column[
## Syntax
## Plugin Docs
## Gradle Wrapper
## Gradle Daemon
## Issues
]
.right-column[
### Outstanding Issues
- ### AAR vs APKLIB (Maven) incompatibility is still an issue
- ### Tooling errors can sometimes (frequently) be cryptic
- ### Thorough documentation and relevant (recent) StackOverflow posts still improving
]

---

class: center, middle
# Intermediate features
## *"Boring! Show me the cool stuff!"*

---

.left-column[
## Build flavors
]
.right-column[
### Build flavors (paid vs free, Google vs Amazon)
]

---

.left-column[
## Build flavors
## Build variants
]
.right-column[
### Build variants (production vs debug)
]

---

.left-column[
## Build flavors
## Build variants
## BuildConfig
]
.right-column[
### Build time constants
]

---

.left-column[
## Build flavors
## Build variants
## BuildConfig
## Scripting
]
.right-column[
### Scriptability
]

---

.left-column[
## Build flavors
## Build variants
## BuildConfig
## Scripting
## Optimizations
]
.right-column[
### Ex: shrinkResources
]

---

class: center, middle
# Easy Integrations
## *"Show me the money!"*

---

.left-column[
## ProGuard Hell
]
.right-column[
https://github.com/krschultz/android-proguard-snippets
- Not dynamic, but improves readability
]

---

.left-column[
## ProGuard Hell
## Quality
]
.right-column[
### Quality Tools - Lint
]

---

.left-column[
## ProGuard Hell
## Quality
]
.right-column[
### Quality Tools - FindBugs
]

---

.left-column[
## ProGuard Hell
## Quality
]
.right-column[
### Quality Tools - PMD
]

---

.left-column[
## ProGuard Hell
## Quality
## Version checks
]
.right-column[
### Ensuring dependency versions are up to date
]

---

.left-column[
## ProGuard Hell
## Quality
## Version checks
]
.right-column[
### Preventing wild card dependencies
### https://gist.github.com/JakeWharton/2066f5e4f08fbaaa68fd
]

---

.left-column[
## ProGuard Hell
## Quality
## Version checks
## Productivity
]
.right-column[
### http://gradleplease.appspot.com
]

---

### Useful links

#### Documentation
- https://gradle.org
- https://developer.android.com/sdk/installing/studio-build.html
- https://developer.android.com/tools/building/plugin-for-gradle.html
- http://tools.android.com/tech-docs/new-build-system/user-guide

#### Videos
- "Dive into the Gradle-based Android Build System"  
https://www.youtube.com/watch?v=popb1n1_fA8

#### Interesting Gradle files/snippets
- Project configuration utilizing interesting features
  https://github.com/LiveTyping/u2020-mvp/blob/master/app/build.gradle
- Adding Android support annotations to a plain Java module  
https://gist.github.com/JakeWharton/e8a3685feb6a94b23393

---

class: center, middle
# Questions?

![Android](androidify.gif)
# Thank you!
