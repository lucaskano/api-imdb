# Project : api-server
### Server Project
#### 1. OBJECTIVE: 
The customer has requested a way to query for movie titles in [IMDB](https://www.imdb.com/)
<br><br>

#### 2. STACK : 
- [JAVA](https://docs.oracle.com/en/java/javase/11/docs/api/index.html) (*version 11*)
- [Gradle](https://docs.gradle.org/6.1.1/release-notes.html) (*version 6.4.1*)
- [Guice](https://github.com/google/guice) (*version 4.0*)
- [JSoup](https://jsoup.org) (*version 1.3.1*)
- [Apache Commons](https://commons.apache.org) (*version 3.0*)
- [Log4J](https://logging.apache.org/log4j/2.x) (*version 1.2.17*)
- [JUnit](https://junit.org/) (*version 4.12*)
<br><br>

#### 3. TECHNICAL DETAILS: 
- JSoup was used to parse IMDB's html.
  - URL's examples (Movies):
    - [Coringa](https://www.imdb.com/title/tt7286456/?ref_=hm_fanfav_tt_3_pd_fp1)
    - [Vingadores: Ultimato](https://www.imdb.com/title/tt4154796/?ref_=hm_fanfav_tt_6_pd_fp1) 
    - [Parasita](https://www.imdb.com/title/tt6751668/?ref_=hm_fanfav_tt_4_pd_fp1)
<br><br>

- After obtaining the content, the application converts it to a class object and filters only the titles movies. Finally, a response object is generated based on the defined output format
<br><br> 

#### 4. ANOTHER TECHNICAL DETAILS:
- [Guice](https://github.com/google/guice) for dependency injection
- The [Optional](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Optional.html) Java Class was used for dealing with *null*
- The number port 1026 ([TCP](https://pt.wikipedia.org/wiki/Transmission_Control_Protocol)) is readed for requests
- Support for multiple concurrent accesses
- The Uncle Bob's [Clean Code](https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882) principles was respected
- For get the movies titles, the application do a GET request in IMDB's URL.
<br><br>

#### 5.INPUT FORMAT :
- The protocol is text and the input is with format:
```text
<query length>:<query>
```
- *query_length* : is the length of the query
- *query* : is the movie title to looking for
<br><br>

#### 6. OUTPUT/RESPONSE FORMAT:
- The response is a movie title list is String format.
- Each movie title was separated with LF (\n).
- The accounted app or movie separator - LF (\n) - with response duration 
- The protocol is text and the response is with format:

```text
<payload length>:<payload>
```

- *payload_length* : is the length of the response content
- *payload* : is the response content
<br><br>

#### 7. ERROR LIST : 
- In case of error, the application will return the code and the message for better details.
- Errors code possibles : *EnumApiException.java*
<br><br>

#### 8.  RUNNING THE PROJECT  
##### 8.1. HOW GENERATE THE .JAR FILE :
This task compiles, tests, and assembles the code into a JAR file. You can run it like this:
```text
./gradlew build
```
After a few seconds, "BUILD SUCCESSFUL" indicates that the build has completed.
The jar file will be generated at *\<project\>/build/libs/*
<br><br>
##### 8.2. HOW RUN THE PROJECT :
You can run it like this:
```text
./gradlew run
```
##### 8.3. HOW RUN FROM THE OUTPUT JAR  :
You can run it like this:
```text
./gradlew runWithExecJarExecutable
```

OBS.: The project use Java version 11.<br>
The *gradle.build* file explicit the Java version.

<br><br>

# CALLING THE SERVER RUNNING CLIENT OR USING TELNET.