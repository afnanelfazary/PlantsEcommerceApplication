package com.example.plantsecommerceapplication;

class OrderHelper {

    private static Orders orders = new Orders();

    /**
     * Retrieve the Orders cart. Call this before perform any manipulation on the orders cart.
     *
     * @return the orders cart
     */
    public static Orders getCart() {
        if (orders == null) {
            orders = new Orders();
        }

        return orders;
    }
}

