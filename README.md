# bank card example project

## Description

Display and entry of fictional Bank card information

Bank || Card number || Expiry date
Bank of UK| 1101‐2245‐3346‐111 |Jan‐2016|
Bank of USA | 4519‐4532‐4524‐2456| Feb‐2016|
Bank of Germany | 4486‐3331‐8322‐123 | May‐2017|

### Gradle

```
./gradlew run
```

### Autovalue

Immutable value types for Java

* https://github.com/google/auto/tree/master/value

### Logback Configuration

1. Logback tries to find a file called logback.groovy in the classpath.

1. If no such file is found, logback tries to find a file called logback-test.xml in the classpath.

1. If no such file is found, it checks for the file logback.xml in the classpath..

1. If neither file is found, logback configures itself automatically using the BasicConfigurator which will cause logging output to be directed to the console.

