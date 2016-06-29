# bank card example project

## Description

Display and entry of fictional Bank card information


Bank            | Card number        | Expiry date
----------------|--------------------|------------
Bank of UK      | 1101‐2245‐3346‐111 | Jan‐2016 |
Bank of USA     | 4519‐4532‐4524‐2456| Feb‐2016 |
Bank of Germany | 4486‐3331‐8322‐123 | May‐2017 |

The initial data is loaded from an internal resource file with the example data as provided.

[Screen Shot](https://cloud.githubusercontent.com/assets/346896/5993331/0bdee3b6-aa45-11e4-9106-805c6e037f9a.png)

The implementation has these requirements:-

1. Sort the data by Expiry date in descending order
1. Replace card number after the first 4 digits with ‘x’
1. The end user should be able to enter the card data manually, one row at a time
1. Upload a CSV file with the columns in the order shown

The Csv supported would look like.

```
A & B Bank,1234-4532-4524-2456,Oct-2017
Z & A Bank,1234-4532-4524-2456,Oct-2017
Q & A Bank,1234-4532-4524-1111,Jan-2022
```

In this application there is no "per user" session or state. The data operations can be 'curled' easily.

```
curl http://localhost:8080/bankcards/application/list
```

The intention is for one application dataset that doesn't allow duplicate records.

Double submits or duplicate file uploads are prevented against.

### War file

A war is available in the /dist folder.
This was deployed in the latest Jetty 9 on Java 7.

### Gradle

With the repository cloned locally, proceed to download the internet by executing gradlew.

```
./gradlew jettyRun
```

http://localhost:8080/bankcards/index.html

### Resources

* GET /bankcards/index.html
* GET /bankcards/application/list
* POST /bankcards/application/form
* POST /bankcards/application/upload

### Jersey 2.22 Servlets

There is no dependency injection required; Jersey (2.23.1) was used instead of Spring Boot, for a change.

The application doesn't use any java view templates and is a simple Single Page Application located in index.html.

### Autovalue

Immutable value types for Java

* [AutoValue](https://github.com/google/auto/tree/master/value)

### Logback Configuration

1. Logback tries to find a file called logback.groovy in the classpath.

1. If no such file is found, logback tries to find a file called logback-test.xml in the classpath.

1. If no such file is found, it checks for the file logback.xml in the classpath..

1. If neither file is found, logback configures itself automatically using the BasicConfigurator which will cause logging output to be directed to the console.
