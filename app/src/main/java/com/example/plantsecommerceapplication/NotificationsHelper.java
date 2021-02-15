package com.example.plantsecommerceapplication;

public class NotificationsHelper {

    private static Notifications notifications = new Notifications();

    /**
     * Retrieve the notifications cart. Call this before perform any manipulation on the shopping cart.
     *
     * @return the notifications cart
     */
    public static Notifications getCart() {
        if (notifications == null) {
            notifications = new Notifications();
        }

        return notifications;
    }
}
