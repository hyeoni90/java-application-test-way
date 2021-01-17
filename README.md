# Java Application Testing Way Example
Java, Spring 애플리케이션을 테스트 하는 방법들에 대한 예제 

## Junit5

### 테스트 기본 원칙
> 단위 테스트 원칙 - **F.I.R.S.T**
> * Fast: 유닛테스트는 빨라야 한다.
> * Isolated: 다른 테스트에 종속적인 테스트는 절대 작성하지 않는다.
> * Repeatable: 테스트는 실행할 때 마다 같은 결과를 만들어야한다.
> * Self-validating: 테스트는 스스로 결과물에 대한 옳고 그름을 판단할 수 있어야 한다. 
> * Timely: 유닛 테스트는 production 코드가 테스트를 통과하기 직전에 구성되어야 한다. 
      이는 테스트 주도 개발(TDD) 방법론에 적합한 원칙이나 실제로 적용되지 않는 경우도 있다.

단위 테스트 (unit test), 통합 테스트 (integration test), 인수테스트 (acceptance test) 등 다양한 테스트가 존재한다.
각 테스트의 목적과 상황에 맞게 테스트를 구성하는 것도 중요하다.

- Assertion 활용한 테스트 코드 작성 방법
 
### Extension Model
- Junit 4: @RunWith(Runner), TestRule, MethodRule
- Junit 5: Extension

#### Register Extension
1. Declarative Registration - @ExtendWith({extensionInstanceName}.class)
```java
@ExtendWith({FindSlowTestExtension}.class)
class ReportTest {
    ...
}
``` 

2. Programmatic Registration - @RegisterExtension
```java
class ReportTest {
    
    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension({THRESHOLD});
}
``` 

3. Automatic Registration - ServiceLoader  
```
ex) junit-platform.properties

junit.jupiter.extensions.autodetection.enabled = true
```

references
- https://www.baeldung.com/junit-5-extensions

## Mockito

## JMeter 
`성능 측정 및 부하 테스트 기능을 제공하는 오픈 소스 자바 애플리케이션`으로, 
CLI 를 지원하여 CI or CD 툴과 연동할때 편리하며 UI 사용하는 것보다 메모리 등 시스템 리소스를 적게 사용한다.

** JMeter 도 시스템 리소스를 사용하기 때문에, 테스트 하고자 하는 애플리케이션과 서버가 분리된 환경에서 테스트 해야한다.

### 애플리케이션 테스트 지원 형태 )
- 웹 http, https
- SOAP / REST web service
- FTP
- DataBase (JDBC 사용)
- Mail (SMTP, POP3, IMAP)
- etc ...

### 주요 개념
- Thread Group: 한 스레드 당 유저 한명 (유저의 그룹이라고 이해 하고, 유저의 수)
- Sampler: 어떤 유저가 해야하는 액션 (HTTP 요청 같은 것들을 하나의 sampler 라고 본다.)
- Listener: 응답을 받았을 할 일 (리포팅, 검증, 그래프 그리기 등)
- Configuration: Sampler 가 사용할  설정 값 (HTTP header, Cookie, JDBC connection ...)
- Assertion: 응답이 성공적인지 확인하는 방법 

### JMeter 설치 및 실행 
1. Binary 압축 파일 다운로드 [http://jmeter.apache.org/download_jmeter.cgi ]
Binarires > Download `apache-jmeter-5.4.zip` > Unzip download file
```shell script
$ cd Download/apache-jmeter-5.4
$ cd bin ./jmeter
```

2. Homebrew 로 설치하기 [https://formulae.brew.sh/formula/jmeter]
```shell script
$ brew install jmeter
$ brew upgrade jmeter 
$ open /usr/local/bin/jmeter 
```

### Reference
- Apache JMeter [https://jmeter.apache.org/]
