# spring-mvc-legacy

스프링 5.3.31 버전을 기반으로 한 스프링 MVC 프로젝트 템플릿입니다.

## 프로젝트 구조

```
.
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── myapp
│   │   │               └── HomeController.java
│   │   ├── resources
│   │   │   ├── META-INF
│   │   │   ├── (application.properties)
│   │   │   ├── log4j2.xml
│   │   │   └── templates
│   │   │       └── home.html
│   │   └── webapp
│   │       ├── WEB-INF
│   │       │   ├── classes
│   │       │   ├── spring
│   │       │   │   ├── appServlet
│   │       │   │   │   └── servlet-context.xml
│   │       │   │   └── root-context.xml
│   │       │   ├── views
│   │       │   │   └── home.jsp
│   │       │   └── web.xml
│   │       └── resources
│   │           ├── css
│   │           │   └── style.css
│   │           ├── image
│   │           │   └── fish.png
│   │           └── js
│   │               └── script.js
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── myapp
│       └── resources
│           └── log4j2.xml
```

## 요구사항

- Java 8+

## Template Engine 선택

`JSP`와 `Thymeleaf` 에 대한 의존성이 추가되어 있습니다.

### JSP

- 기본으로 `JSP`가 설정되어 있어 별도의 설정이 필요 없습니다.
- `src/main/webapp/WEB-INF/views` 경로에 `*.jsp` 확장자 파일을 생성하여 작업하면 됩니다.

### Thymeleaf

1. `src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml` 파일에서 다음 빈을 삭제합니다.

```xml
<!-- JSP -->
<!-- Remove this section to use ThymeLeaf instead of JSP -->
<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <beans:property name="prefix" value="/WEB-INF/views/"/>
    <beans:property name="suffix" value=".jsp"/>
</beans:bean>
```

2. `src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml` 파일에서 다음 세 개의 빈을 주석 해제합니다.

- `templateResolver`
- `templateEngine`
- `thymeleafViewResolver`

```xml

<beans:beans>
    <!--    ThymeLeaf-->
    <!--    Uncomment this section to use ThymeLeaf instead of JSP-->
    <!-- 1 -->
    <beans:bean id="templateResolver" class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
        <beans:property name="prefix" value="classpath:/templates/"/>
        <beans:property name="suffix" value=".html"/>
        <beans:property name="templateMode" value="HTML"/>
    </beans:bean>
    <!-- 2 -->
    <beans:bean id="templateEngine" class="org.thymeleaf.spring5.SpringTemplateEngine">
        <beans:property name="templateResolver" ref="templateResolver"/>
    </beans:bean>
    <!-- 3 -->
    <beans:bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
        <beans:property name="templateEngine" ref="templateEngine"/>
    </beans:bean>
</beans:beans>
```

3. `src/main/resources/templates` 경로에 `*.html` 확장자 파일을 생성하여 작업하면 됩니다.

## 환경 변수

`src/main/resources/` 위치에 `application.properties` 파일을 생성하여 환경 변수를 설정할 수 있습니다.
데이터베이스 설정정보 등 중요한 정보는 이 파일에 작성해주세요. 깃허브에 올라가지 않도록 주의해야 합니다.

`root-context.xml` 파일의 데이터베이스 설정정보는 다음과 같습니다.

```properties
# application.properties
spring.datasource.driver-class-name=
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

## 라이브러리 추가

`JPA`, `MyBatis`와 같은 라이브러리는 [mvnrepository.com](mvnrepository.com)에서 찾아 `build.gradle` 파일에 추가해주세요.
이때, 버전
호환성은 [spring-boot-2.7.18-dependency-versions](https://docs.spring.io/spring-boot/docs/2.7.18/reference/html/dependency-versions.html#appendix.dependency-versions)
을 참고해주세요.

JPA, MyBatis 적용을 위한 예상 의존성은 다음과 같습니다.

### JPA

- `spring-orm`
- `spring-data-jpa`
- `hibernate-core`

### MyBatis

- `mybatis`
- `mybatis-spring`

### 관련 포스트

- [방명록 애플리케이션 MyBatis 적용](https://victorydntmd.tistory.com/162)
- [방명록 애플리케이션 JPA 적용](https://victorydntmd.tistory.com/202)
- [SpringMVC - Spring Mvc, H2, JPA, MyBatis 연동 환경 구성하기](https://galid1.tistory.com/554)
