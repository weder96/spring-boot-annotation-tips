# Spring Boot: Everything you need to know, and what nobody told you.

For all those who seek to solidify a career in application development using Java, they should also seek all knowledge in OOP (Object Oriented Programming), as I mentioned in my article [15 Tips to Become a Java Expert!](https://dev.to/weder96/15-tips-to-become-a-java-expert-1acj), in the tip of learning a framework/lib, we must develop on other fronts that will support us in our career as a developer .

In the early days of application development using Java, it is known that the process of configuring development environments was a complex, boring and time-consuming moment.

But, over time, tools were created that had these functions configured and fell in love with developers, and the one that is most used in gigantic environments or even for small projects, this framework is called **Spring Boot** by Pivotal, it is It's possible to make our life a lot easier, a lot, lol.

Those of us who are part of a team of developers know that the time to finish our activities is short and of a lot of responsibility, and you can't waste time configuring a project instead of developing it, so in this article we'll go through the points you should know of this Framework, in addition to tips on best practices in its use.

Our mission here is to try to help you understand SpringBoot, to help you simplify the development of applications (Java), reducing the amount of configurations and optimizations, but remember not to reset the configurations, because they exist, but today we configure an application at a speed that 10 years ago was unthinkable.

In this article we will cover the following order to master the **Spring Boot**:

This article is divided into 4 parts:

[Spring Boot: Everything you need to know, and what nobody told you.](https://dev.to/weder96/spring-boot-everything-you-need-to-know-and-what-nobody-told-you-o4j)
[How to start learning Spring boot](https://dev.to/weder96/how-to-start-learning-spring-boot-4df)
[Questions About Microservices and Asynchronous Services(Rabbit, Kafka, SQS)](https://dev.to/weder96/questions-about-microservices-and-asynchronous-services-rabbit-kafka-sqs-4j12)
[Exception Handling, Spring Security, Exceptions and Validations, Upload, Download, test and Deploy (Cloud)](https://dev.to/weder96/exception-handling-spring-security-exceptions-and-validations-upload-download-test-and-deploy-cloud-3ili)

Here we will start with Theory, on:

**1. What is Spring** <br/>
**2. What is Spring Boot?**<br/>
**3. The Spring Components**<br/>
**4. Spring Boot Starter**<br/>
**5. Spring Boot AutoConfigurator**<br/>
**6. Spring Boot Actuator**<br/>

-------------------------------------------------- -------------------------------------------------- -------
### **1. What is Spring**

Spring is a Java framework that was created with the aim of facilitating the development of applications, using the concepts of Inversion of Control and Dependency Injection. Inside it, there's Spring MVC and Core Technologies (it's the base of Spring, inside it's the dependency injection package).


### **2. What is Spring Boot?**

Spring Boot is a framework that was born from Spring (MVC), a framework developed for the Java platform based on design patterns, IOC (inversion of control) and ID (dependency injection), both of which are standards for project that helps a lot to leave the code uncoupled.

Spring framework was created to **simplify** the **configurations** for web applications, however you still configured a lot of xml files, which did not manage to meet your objective, but which already helped you in great situations, in addition to the project, come out with your layers well defined.


Therefore, a new project was added to the framework to change this game and abstract all the complexity that a complete configuration can bring: **O Spring Boot**.

So **Spring Boot 1.0** was released in **April 2014**. The **Spring Framework 4.2.0**, in this simpler and more direct development model, this framework was decisive for the use of the **Spring ecosystem to become the darling of Programmers**.

**But what does it deliver that made it grow so much?**

It provides most of the components needed in general applications in a pre-configured way, enabling an application running in production quickly, reducing the configuration and deployment effort, being already coupled to the tomcat Application server.

In short, Spring Boot is a pre-configured template for developing and running Spring-based applications.

-------------------------------------------------- -------------------------------------------------- -------

### **3. The Spring Components**

[spring-boot](https://spring.io/projects/spring-boot), at the time of writing this article, is in version 3.0.1, and it is part of other [spring. io](https://spring.io/projects) with about 23 libs that help you build your applications:

1. [Spring Boot](https://spring.io/projects/spring-boot)
2. [Spring Framework](https://spring.io/projects/spring-framework)
3. [Spring Data](https://spring.io/projects/spring-data)
4. [Spring Cloud](https://spring.io/projects/spring-cloud)
5. [Spring Cloud Data Flow](https://spring.io/projects/spring-cloud-dataflow)
6. [Spring Security](https://spring.io/projects/spring-security)
7. [Spring for GraphQL](https://spring.io/projects/spring-graphql)
8. [Spring Session](https://spring.io/projects/spring-session)
9. [Spring Integration](https://spring.io/projects/spring-integration)
10. [Spring HATEOAS](https://spring.io/projects/spring-hateoas)
12. [Spring REST Docs](https://spring.io/projects/spring-restdocs)
13. [Spring Batch](https://spring.io/projects/spring-batch)
14. [Spring AMQP](https://spring.io/projects/spring-amqp)
15. [Spring CredHub](https://spring.io/projects/spring-credhub)
16. [Spring Flo](https://spring.io/projects/spring-flo)
17. [Spring for Apache Kafka](https://spring.io/projects/spring-kafka)
18. [Spring LDAP](https://spring.io/projects/spring-ldap)
19. [Spring Shell](https://spring.io/projects/spring-shell)
20. [Spring Statemachine](https://spring.io/projects/spring-statemachine)
21. [Spring Vault](https://spring.io/projects/spring-vault)
22. [Spring Web Flow](https://spring.io/projects/spring-webflow)
23. [Spring Web Services](https://spring.io/projects/spring-ws)

*But with that many libs, how can I identify what I should understand first?**

Let's divide this first part into 3 fragments:

- Spring Boot Starter
- Spring Boot AutoConfigurator
- Spring Boot Actuator

-------------------------------------------------- -------------------------------------------------- -------
### **4. Spring Boot Starter**

When we create an application we want to provide access to this application so we develop REST services; that we are currently using behind libs like Spring MVC, Tomcat and Jackson among many other dependencies for a single application.

So Spring Boot to help us decrease the number of manually added dependencies just by adding a dependency. Therefore, instead of manually specifying the dependencies, it is only necessary to add the following lib to our pom.xml:

```
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-web</artifactId>
</dependency>

```

When you run the maven command below:

```
mvn dependency:tree
```

Result:

![dependency](../../images/dependency.png)


This command shows us all the dependencies of a lib:
in the case each level shows if it has other internal dependencies or not, in the first level we have:

org.springframework.boot:spring-boot-starter-json
org.springframework.boot:spring-boot-starter-tomcat
org.springframework:spring-web

As seen when running the command, Springboot's function is to combine the various dependencies arising from a Spring Boot project into a single dependency, eliminating the need to configure multiple dependencies in Maven or Gradle.

We noticed that if we had to add one by one this process would be very laborious. Therefore, Spring Boot uses starters in order to significantly decrease it.


Remember that this lib is the one that enables you to create controllers to use the annotations that we'll see later.

-------------------------------------------------- -------------------------------------------------- -------

### **5. Spring Boot AutoConfigurator**

Spring Boot autoconfiguration attempts to automatically configure your Spring application based on the jar dependencies you have added.

For example, if HSQLDB is in your classpath and you haven't manually configured any database connection beans, Spring Boot will automatically configure an in-memory database.

You need to opt-in to automatic configuration by adding @EnableAutoConfiguration or @SpringBootApplication annotations to one of your @Configuration classes.

Yes, but all the settings are in the [official documentation](https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-auto-configuration.html#:~: text=Spring%20Boot%20auto%2Dconfiguration%20attempts,configures%20an%20in%2Dmemory%20database.), about this process.


It is responsible for managing the configuration process of a Spring Boot application, providing default settings and merging them with possible custom settings, you can create annotated classes that configure some function of your application.

In a Spring Boot application, the AutoConfigurator can be seen using the traditional @SpringBootApplication annotation, which is above the application's initialization method.

Internally, the aSpringBootApplication annotation is a combination of Spring's traditional @Configuration, @ComponentScan, and @EnableAutoConfiguration annotations.


-------------------------------------------------- -------------------------------------------------- -------

### **6. Spring Boot Actuator**

The Spring Boot Actuator, it provides us with endpoints that make it easier to obtain metrics from our application.

When configuring maven with the dependency below:

```
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```


Spring Boot AutoConfigurator defines that the web server should be exposed on localhost on port 8080, but you can redefine this port. But who makes the provision of this configuration on the web server is the Actuator.

In my github there is a project [SpringBootActuatorPrometheus](https://github.com/weder96/SpringBootActuatorPrometheus) that teaches how to activate it and interconnect with Prometheus and Kibana, and visualize its graphs with micrometer metrics and spring boot Statistic.