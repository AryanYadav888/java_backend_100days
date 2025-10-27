# Day 9 – Java Spring Framework: Tight/Loose Coupling, Dependency Injection, and IoC

## 🚀 What I Learned

### 1. Tight Coupling vs Loose Coupling

- **Tight Coupling:** Classes depend directly on each other.
    ```
    // Tight coupling example
    public class NotificationService {
        public void send(String message) {
            System.out.println("Notification: " + message);
        }
    }

    public class UserService {
        NotificationService notificationService = new NotificationService();
        public void notifyUser(String msg) {
            notificationService.send(msg);
        }
    }
    ```
    - Changing `NotificationService` requires changing `UserService`.

- **Loose Coupling:** Classes depend on interfaces or abstractions.
    ```
    // Loose coupling with interface
    public interface NotificationService {
        void send(String message);
    }

    public class EmailNotificationService implements NotificationService {
        public void send(String message) {
            System.out.println("Email: " + message);
        }
    }

    public class SMSNotificationService implements NotificationService {
        public void send(String message) {
            System.out.println("SMS: " + message);
        }
    }

    public class UserService {
        private NotificationService notificationService;
        // Constructor Injection
        public UserService(NotificationService notificationService) {
            this.notificationService = notificationService;
        }
        public void notifyUser(String msg) {
            notificationService.send(msg);
        }
    }

    // In main code:
    NotificationService service = new EmailNotificationService();
    UserService userService = new UserService(service);
    userService.notifyUser("Order Placed");
    ```

### 2. Strategy Pattern

- Allows switching between implementations dynamically:

    ```
    // Change implementation easily
    NotificationService service = new SMSNotificationService();
    UserService userService = new UserService(service); // Now sends SMS
    ```

### 3. Dependency Injection Types

- **Constructor Injection**
    ```
    public class UserService {
        private NotificationService notificationService;
        public UserService(NotificationService notificationService) {
            this.notificationService = notificationService;
        }
    }
    ```
- **Setter Injection**
    ```
    public class UserService {
        private NotificationService notificationService;
        public void setNotificationService(NotificationService notificationService) {
            this.notificationService = notificationService;
        }
    }
    ```
- **Field Injection** (not recommended in pure Java)
    ```
    @Autowired
    private NotificationService notificationService;
    ```

### 4. IoC (Inversion of Control) & Spring Container

- Spring manages object creation and wiring:
    ```
    <!-- Bean definition in Spring's application-context.xml -->
    <bean id="emailService" class="com.example.EmailNotificationService"/>
    <bean id="userService" class="com.example.UserService">
        <constructor-arg ref="emailService"/>
    </bean>
    ```
- Get bean from container:
    ```
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    UserService userService = context.getBean("userService", UserService.class);
    ```

### 5. Spring Project Setup

- Use Maven/Gradle for dependencies.
    ```
    <!-- pom.xml -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.3.0</version>
    </dependency>
    ```

### 6. Spring Beans

- Beans are managed objects by Spring.
- Configuration via XML or annotations (`@Component`, `@Bean`).

### 7. Key Takeaways

- Prefer loose coupling and rely on interfaces.
- Use dependency injection for easier testing and maintenance.
- Spring’s IoC container helps manage objects and dependencies.

## 📁 Example Project Structure
src/
 └── main/
      ├── java/
      │    └── com/example/...
      └── resources/
           └── application-context.xml



## Resources

- Spring Documentation: https://spring.io/projects/spring-framework

