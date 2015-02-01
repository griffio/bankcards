# bank card example project

## Description

Display and entry of fictional Bank card information

Bank || Card number || Expiry date
Bank of UK| 1101‐2245‐3346‐111 |Jan‐2016|
Bank of USA | 4519‐4532‐4524‐2456| Feb‐2016|
Bank of Germany | 4486‐3331‐8322‐123 | May‐2017|

The initial data is loaded from an internal resource file with the example data as provided.

The implementation has these requirements:-

1. Sort the data by Expiry date in descending order
1. Replace card number after the first 4 digits with ‘x’
1. The end user should be able to enter the card data manually, one row at a time
1. Upload a CSV file with the columns in the order shown above

In this application there is no "per user" session or state. The data operations can be 'curled' easily.

```
curl http://localhost:8080/bankcards/application/list
```

The intention is for one application dataset that doesn't allow duplicate records.

Double submits or duplicate file uploads are prevented against.

### Gradle

```
./gradlew jettyRun
```

http://localhost:8080/bankcards/index.html

### Resources
GET /bankcards/index.html
GET /bankcards/application/list
POST /bankcards/application/form
POST /bankcards/application/upload

### Jersey 2

There is no dependency injection required; Jersey 2 was used instead of Spring Boot, for a change.

The application doesn't use any java view templates and is a simple Single Page Application located in index.html.

### Autovalue

Immutable value types for Java

* https://github.com/google/auto/tree/master/value

### Logback Configuration

1. Logback tries to find a file called logback.groovy in the classpath.

1. If no such file is found, logback tries to find a file called logback-test.xml in the classpath.

1. If no such file is found, it checks for the file logback.xml in the classpath..

1. If neither file is found, logback configures itself automatically using the BasicConfigurator which will cause logging output to be directed to the console.

