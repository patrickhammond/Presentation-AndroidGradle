class: center, middle, title-slide
# Gradle and the Android Build System
**Patrick Hammond**  
patrick@madebyatomicrobot.com  
@patrickhammond
.brand-logo[Atomic Robot]
.license[.name[Creative Commons Attribution-ShareAlike 4.0 International] .logo[CC-BY-SA]]

---

## Presentation Links

- ### The presentation can be found here:  http://patrickhammond.github.io/Presentation-AndroidGradle

- ### Sample code can be found here:  https://github.com/patrickhammond/Presentation-AndroidGradle

- ### **Disclaimer:** Sample code is aimed to drive discussion and show concepts only.  It is not polished so use with caution!

---

## Who am I?

.portrait[![Portrait](portrait.jpeg)]

- ### Husband, Dad, Geek, Insomniac, Cook, Capsaicin Lover, Craft Beer Fan
- ### 5+ years building lots of Android apps
- ### Atomic Robot founding partner and Android lead

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
## When can I **really** use it?
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
## Structure
]
.right-column[
## Do I really have to move **everything** around?
- ### You don't have to follow the Standard Directory Layout...
- ### You *can* keep a similar directory structure...
- ### But don't. Seriously. Don't.
    - #### You'll have to do a bunch of configuration overrides
    - #### Everyone else will have to learn the non-standard structure
]

---

.left-column[
## Structure
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
## Structure
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
## Structure
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
- ### Why does this look different?
    - #### Gradle files (configuration scripts) are also Groovy scripts
    - #### Having a slight familiarity with the Groovy language and Gradle APIs can be helpful
- ### How do I know what to write?
    - #### Read other Gradle files for examples to get a feel for things (links at the end of the presentation)
    - #### In-tool code assist is limited (but improving) which can be a challenge
]

.footnote[http://groovy.codehaus.org and https://gradle.org/docs/current/dsl/]

---

.left-column[
## Syntax
## Productivity
]
.right-column[
### Gradle, please
http://gradleplease.appspot.com  

![Gradle, please](gradle_please.png)
]


---

.left-column[
## Syntax
## Productivity
]
.right-column[
### Gradle Config
Place this in gradle.properties
```
org.gradle.daemon=true
org.gradle.parallel=true
org.gradle.configureondemand=true
```

Medium size app
`./gradlew clean app:assembleProdRelease`  

- Vanilla configuration
    - Run 1: 1m 32s
    - Run 2: 1m 31s
- Optimized configuration
    - Run 1: 1m 27s
    - Run 2: 1m 12s
- Results
    - Negligble gain on first build
    - 20% gain on follow up builds
    - Your results may vary

.footnote[http://jimulabs.com/2014/10/speeding-gradle-builds/]
]

---

.left-column[
## Syntax
## Productivity
## Plugin Docs
]
.right-column[
### Plugin documentation

1. Go to https://developer.android.com/tools/building/plugin-for-gradle.html
1. Download the "Plugin Language Reference" from the right side of the page
1. Unzip
1. Open up index.html
1. Browse like you would JavaDocs
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
### Sample problem: easily supporting paid and lite versions of an app
```text
# Directory structure
myApp/
    app/
        src/
            paid/
                ...
            lite/
                ...
```

```gradle
# myApp/app/build.gradle
android {
    productFlavors {
        lite {
            applicationId 'com.mycompany.myapp.lite'
        }

        paid {
            applicationId 'com.mycompany.myapp.paid'
        }
    }
}
```
]

---

.left-column[
## Build flavors
## Build variants
]
.right-column[
### Problem: preventing debug code from accidentally being released
```text
# Directory structure
myApp/
    app/
        src/
            debug/
                # Turns on debug options
                java/com/myapp/StartupHelper.java
            release/
                # Turns on a crash reporter
                java/com/myapp/DebugHelper.java
            main/
                # Calls StartupHelper.java
                java/com/myapp/MyApplication.java
```
]

---

.left-column[
## Build flavors
## Build variants
## BuildConfig
]
.right-column[
### Build time constants
```gradle
# myApp/app/build.gradle
android {
    defaultConfig {
        buildConfigField(
            "String",
            "BUILD_TIME",
            sprintf('\"%s\"', new Date().toString()))
    }
}
```

```java
// Auto generated by Android tools with every compile
package com.mycompany.myapp;
public final class BuildConfig {
    ...
    public static final String BUILD_TIME = 
        "Tue Feb 17 22:07:00 EST 2015";
    ...
}
```

```java
// Ex: on your about screen
buildTimeView.setText(BuildConfig.BUILD_TIME);
```
]

---

.left-column[
## Build flavors
## Build variants
## BuildConfig
## Scripting
]
.right-column[
### Shell commands are accessible
```gradle
def gitSha = 'git rev-parse --short HEAD'
                .execute([], project.rootDir)
                .text
                .trim()

...

android {
    defaultConfig {
        buildConfigField(
            "String", 
            "GIT_SHA", 
            "\"${gitSha}\"")
    }
}
```
]

---

.left-column[
## Build flavors
## Build variants
## BuildConfig
## Scripting
## Optimize
]
.right-column[
### Ex: shrinkResources
```gradle
android {
    defaultConfig {
        // Prevent aapt from packaging non-matching
        // resources with the app
        resConfigs "en"
        resConfigs "nodpi", "hdpi", "xhdpi", 
                   "xxhdpi", "xxxhdpi"
    }

    buildTypes {
        release {
            // New flag to enable ProGuard
            minifyEnabled true

            // Automatically removes unused resources 
            shrinkResources true
        }
    }
}
```

.footnote[http://cyrilmottier.com/2014/08/26/putting-your-apks-on-diet/]
]

---

class: center, middle
# Customizations and Improvements
## *"How can I make this better?"*

---

.left-column[
## ProGuard
]
.right-column[
### Maintainable ProGuard
```gradle
android {
    buildTypes {
        release {
            minifyEnabled true

            proguardFiles(
                // Default proguard files
                getDefaultProguardFile(
                    'proguard-android.txt'),
                'proguard-rules.pro',
                // Library specific proguard files
                'proguard-google-play-services.pro',
                ...
            )
        }
    }
}
```

- #### Go to the link, download the specific ProGuard files, put them in your app directory
- #### Not dynamic (*.pro files will not be automagically updated if they change)

.footnote[https://github.com/krschultz/android-proguard-snippets]
]

---

.left-column[
## ProGuard
## Quality
]
.right-column[
### Quality Tools - Lint
```gradle
android {
    lintOptions {
        abortOnError true
        lintConfig file('lint.xml')
        htmlReport true
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<lint>
    <!-- Lint config goes here -->
</lint>
```

```shell
./gradlew app:lint
...
open app/build/outputs/lint-results.html
```
]

---

.left-column[
## ProGuard
## Quality
]
.right-column[
### Quality Tools - FindBugs
```gradle
# app/build.gradle
apply plugin: 'findbugs'
...
task findbugs(type: FindBugs, dependsOn: assembleDebug) {
    excludeFilter file("findbugs-exclude.xml")
    classes = fileTree('build/intermediates/classes/debug/')
    source = fileTree('src/main/java/')
    classpath = files()
    effort = 'max'
    reports {
        xml.enabled = false
        html.enabled = true
    }
}
```
]
.footnote[https://github.com/stephanenicolas/Quality-Tools-for-Android]
---

.left-column[
## ProGuard
## Quality
]
.right-column[
### Quality Tools - FindBugs (cont)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<FindBugsFilter>
    <Match>
        <Class name="~.*\.R\$.*"/>
    </Match>
    <Match>
        <Class name="~.*\.Manifest\$.*"/>
    </Match>
    <!-- All bugs in test classes, except for JUnit-specific bugs -->
    <Match>
        <Class name="~.*\.*Test" />
        <Not>
            <Bug code="IJU" />
        </Not>
    </Match>
</FindBugsFilter>
```

```shell
./gradlew app:findbugs
...

open app/build/reports/findbugs/findbugs.html
```

#### Also consider integrating PMD, CPD, CheckStyle, etc...
]

---

.left-column[
## ProGuard
## Quality
## Version checks
]
.right-column[
### Verifying dependencies are current
```gradle
# app/build.gradle
buildscript {
  repositories {
    jcenter()
  }

  dependencies {
    classpath 'com.github.ben-manes:gradle-versions-plugin:0.8'
  }
}

apply plugin: 'com.github.ben-manes.versions'

```

```shell
./gradlew app:dependencyUpdates -Drevision=release
```
]

.footnote[https://github.com/ben-manes/gradle-versions-plugin]
---

.left-column[
## ProGuard
## Quality
## Version checks
]
.right-column[
### Preventing wild card dependencies
```gradle
# build.gradle
allprojects {
  afterEvaluate { project ->
    project.configurations.all {
      resolutionStrategy.eachDependency { DependencyResolveDetails details ->
        def requested = details.requested
        if (requested.version.contains('+')) {
          throw new GradleException("Wildcard dependency forbidden: ${requested.group}:${requested.name}:${requested.version}")
        }
      }
    }
  }
}
```
(For easier to read code please see the Gist referenced in the footnote)
]
.footnote[https://gist.github.com/JakeWharton/2066f5e4f08fbaaa68fd]

---

## Outstanding Issues
- ### AAR vs APKLIB (Maven) incompatibility is still an ecosystem issue
- ### Tooling errors can sometimes be incredibly cryptic
- ### Thorough documentation and relevant (recent) StackOverflow posts limited but improving

---

## What I didn't cover...
- ### Multi project configurations in detail
- ### Android library projects
- ### CI options (ex: build numbers as code)
- ### Dealing with awkward 3rd party libraries
- ### Lots more!

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
