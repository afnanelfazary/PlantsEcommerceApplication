package com.example.plantsecommerceapplication;

public class WishListHelper {
    private static WishList wishList = new WishList();

    /**
     * Retrieve the WishList cart. Call this before perform any manipulation on the shopping cart.
     *
     * @return the WishList  cart
     */
    public static WishList getCart() {
        if (wishList == null) {
            wishList = new WishList();
        }

        return wishList;
    }
}
