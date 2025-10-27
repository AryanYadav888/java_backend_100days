package couplingDemo.loose;

public class PushNotificationService implements NotificationService{
    @Override
    public void send(String message) {
        System.out.println("Push: "+message);
    }
}
