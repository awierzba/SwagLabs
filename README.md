## Description ##

Automated tests project executed on Sauce Demo page.

Tests are written in Java 11 with Selenide and JUnit5 usage.

Tests are executed on Chrome browser in headless mode.

## Setup ##

Use command:

```
mvn test
```

To disable headless mode use command:

```
mvn test -D"selenide.headless=false"
```

or change `selenide.headless` property in `selenide.properties` to `false`.
