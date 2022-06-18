# Kotlin Data Parser

This project can be managed from the command line using [Gradle][1].
You don't need to install Gradle yourself, as a script is included that
will download it automatically if necessary.  You can also use
the IntelliJ IDE - by using the *Import Project* option and specifying
Gradle.  The instructions below assume the use of the command line.


## Building The Application

You can build the application on the command line with

    ./gradlew build

Omit the `./` on Windows.  Note that this will be fairly slow the first
time it runs, as it will need to download Gradle itself and the project's
dependencies.  Subsequent builds should be a lot faster.

## Running The Application

### With Gradle

You can run the application with command line arguments using the `--args` option.  For example, to specify a data filename as the sole
command line argument, you do this:

    ./gradlew run --args data/bradford.txt

To specify a data filename and a year as command line arguments, you
need to enclose both in quotes, like this:

    ./gradlew run --args "data/bradford.txt 2018"

Specifiying a year displays a graph of the wettest months for that particular year and location.

### Outside of Gradle

You can generate a self-contained executable JAR file for the application
with

    ./gradlew jar

This will appear in the `build/libs` subdirectory, as the file `parser.jar`.
It can be run with commands like this:

    java -jar parser.jar
    java -jar parser.jar ../../data/bradford.txt

## Packaging

Code can be compressed as a `.zip` archive with:

    ./gradlew compress

[1]: https://gradle.org

---

_This project is from 2019, the updates in 2022 was from me migrating from GitLab to GitHub_
