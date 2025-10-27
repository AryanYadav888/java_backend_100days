package couplingDemo.tight;

import couplingDemo.tight.NotificationService;

public class UserService {
    NotificationService notificationService = new NotificationService();

    public void notifyUser(String message){
        notificationService.send("Notification hello");
    }
}
