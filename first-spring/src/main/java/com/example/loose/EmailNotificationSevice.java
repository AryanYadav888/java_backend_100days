package com.example.loose;

public class EmailNotificationSevice implements NotificationService{

    @Override
    public void send(String message) {
        System.out.println("Email : "+message);
    }
}
