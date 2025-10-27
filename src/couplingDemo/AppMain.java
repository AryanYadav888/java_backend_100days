package couplingDemo;

import couplingDemo.loose.EmailNotificationService;
import couplingDemo.loose.NotificationService;
import couplingDemo.loose.PushNotificationService;
import couplingDemo.loose.SMSNotificationService;
import couplingDemo.tight.UserService;
public class AppMain {
    public static void main(String[] args) {

//        Tight Coupling
        UserService userService = new UserService();
//        userService.notifyUser("Order Placed");

//        Loose Coupling
        NotificationService emailService = new EmailNotificationService();
        couplingDemo.loose.UserService userServiceLoose = new couplingDemo.loose.UserService(emailService);
        userServiceLoose.notifyUser("Order Placed");

        NotificationService SMSService = new SMSNotificationService();
        couplingDemo.loose.UserService userServiceLoose1 = new couplingDemo.loose.UserService(SMSService);
        userServiceLoose1.notifyUser("Order Placed");

        NotificationService pushService = new PushNotificationService();
        couplingDemo.loose.UserService pushservice = new couplingDemo.loose.UserService(pushService);
        pushservice.notifyUser("Order Placed");


        /*
        Dependency Injection (DI) is a design pattern in which objects receive their
        dependencies from an external source rather than creating them internally.
        It promotes loose coupling, easier testing, and better code maintainability.
        In Spring, DI is achieved mainly through Constructor Injection or Setter Injection

        Constructor Injection - dependency is provided via constructor
        Setter injection - dependency is provided via setter method
        Field Injection - dependency is assigned directly to a field
        */

        couplingDemo.loose.UserService userServiceLooseSetter = new couplingDemo.loose.UserService();
        userServiceLooseSetter.setNotificationService(emailService);
        userServiceLooseSetter.notificationService = SMSService;    // field

    }
}
