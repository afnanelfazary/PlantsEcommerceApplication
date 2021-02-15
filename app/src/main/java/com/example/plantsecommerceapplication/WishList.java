package com.example.plantsecommerceapplication;

import com.example.plantsecommerceapplication.exception.ProductNotFoundException;

import com.example.plantsecommerceapplication.exception.ProductNotFoundException;
        import com.example.plantsecommerceapplication.exception.QuantityOutOfRangeException;

        import java.io.Serializable;
        import java.math.BigDecimal;
        import java.util.HashMap;
        import java.util.Map;
        import java.util.Map.Entry;
        import java.util.Set;


/**
 * A representation of shopping cart.
 * <p/>
 * A shopping cart has a map of {@link Saleable} products to their corresponding quantities.
 */
public class WishList implements Serializable {
    private static final long serialVersionUID = 42L;

    private Map<Saleable, Integer> wishListItemMap = new HashMap<Saleable, Integer>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private int totalQuantity = 0;

    /**
     * Add a quantity of a certain {@link Saleable} product to this shopping cart
     *
     * @param sellable the product will be added to this shopping cart
     * @param quantity the amount to be added
     */
    public void add(final Saleable sellable, int quantity) {
        if (wishListItemMap.containsKey(sellable)) {
            wishListItemMap.put(sellable, wishListItemMap.get(sellable) + quantity);
        } else {
            wishListItemMap.put(sellable, quantity);
        }

        totalPrice = totalPrice.add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity += quantity;
    }

    /**
     * Set new quantity for a {@link Saleable} product in this shopping cart
     *
     * @param sellable the product which quantity will be updated
     * @param quantity the new quantity will be assigned for the product
     * @throws ProductNotFoundException    if the product is not found in this shopping cart.
     * @throws QuantityOutOfRangeException if the quantity is negative
     */
    public void update(final Saleable sellable, int quantity) throws ProductNotFoundException, QuantityOutOfRangeException {
        if (!wishListItemMap.containsKey(sellable)) throw new ProductNotFoundException();
        if (quantity < 0)
            throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative.");

        int productQuantity = wishListItemMap.get(sellable);
        BigDecimal productPrice = sellable.getPrice().multiply(BigDecimal.valueOf(productQuantity));

        wishListItemMap.put(sellable, quantity);

        totalQuantity = totalQuantity - productQuantity + quantity;
        totalPrice = totalPrice.subtract(productPrice).add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }

    /**
     * Remove a certain quantity of a {@link Saleable} product from this shopping cart
     *
     * @param sellable the product which will be removed
     * @param quantity the quantity of product which will be removed
     * @throws ProductNotFoundException    if the product is not found in this shopping cart
     * @throws QuantityOutOfRangeException if the quantity is negative or more than the existing quantity of the product in this shopping cart
     */
    public void remove(final Saleable sellable, int quantity) throws ProductNotFoundException, QuantityOutOfRangeException {
        if (!wishListItemMap.containsKey(sellable)) throw new ProductNotFoundException();

        int productQuantity = wishListItemMap.get(sellable);

        if (quantity < 0 || quantity > productQuantity)
            throw new QuantityOutOfRangeException(quantity + " is not a valid quantity. It must be non-negative and less than the current quantity of the product in the shopping cart.");

        if (productQuantity == quantity) {
            wishListItemMap.remove(sellable);
        } else {
            wishListItemMap.put(sellable, productQuantity - quantity);
        }

        totalPrice = totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    /**
     * Remove a {@link Saleable} product from this shopping cart totally
     *
     * @param sellable the product to be removed
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
    public void remove(final Saleable sellable) throws ProductNotFoundException {
        if (!wishListItemMap.containsKey(sellable)) throw new ProductNotFoundException();

        int quantity = wishListItemMap.get(sellable);
        wishListItemMap.remove(sellable);
        totalPrice = totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    /**
     * Remove all products from this shopping cart
     */
    public void clear() {
        wishListItemMap.clear();
        totalPrice = BigDecimal.ZERO;
        totalQuantity = 0;
    }

    /**
     * Get quantity of a {@link Saleable} product in this shopping cart
     *
     * @param sellable the product of interest which this method will return the quantity
     * @return The product quantity in this shopping cart
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
    public int getQuantity(final Saleable sellable) throws ProductNotFoundException {
        if (!wishListItemMap.containsKey(sellable)) throw new ProductNotFoundException();
        return wishListItemMap.get(sellable);
    }

    /**
     * Get total cost of a {@link Saleable} product in this shopping cart
     *
     * @param sellable the product of interest which this method will return the total cost
     * @return Total cost of the product
     * @throws ProductNotFoundException if the product is not found in this shopping cart
     */
    public BigDecimal getCost(final Saleable sellable) throws ProductNotFoundException {
        if (!wishListItemMap.containsKey(sellable)) throw new ProductNotFoundException();
        return sellable.getPrice().multiply(BigDecimal.valueOf(wishListItemMap.get(sellable)));
    }

    /**
     * Get total price of all products in this shopping cart
     *
     * @return Total price of all products in this shopping cart
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Get total quantity of all products in this shopping cart
     *
     * @return Total quantity of all products in this shopping cart
     */
    public int getTotalQuantity() {
        return totalQuantity;
    }

    /**
     * Get set of products in this shopping cart
     *
     * @return Set of {@link Saleable} products in this shopping cart
     */
    public Set<Saleable> getProducts() {
        return wishListItemMap.keySet();
    }

    /**
     * Get a map of products to their quantities in the shopping cart
     *
     * @return A map from product to its quantity in this shopping cart
     */
    public Map<Saleable, Integer> getItemWithQuantity() {
        Map<Saleable, Integer> wishListItemMap = new HashMap<Saleable, Integer>();
        wishListItemMap.putAll(this.wishListItemMap);
        return wishListItemMap;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        for (Map.Entry<Saleable, Integer> entry : wishListItemMap.entrySet()) {
            strBuilder.append(String.format("Product: %s, Unit Price: %f, Quantity: %d%n", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue()));
        }
        strBuilder.append(String.format("Total Quantity: %d, Total Price: %f", totalQuantity, totalPrice));

        return strBuilder.toString();
    }
}
