package com.example.plantsecommerceapplication;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable, Saleable {
     int pId;
    String name; // product name
   String age;//product type
   int photoId;
     private String pDescription; // product description
   private BigDecimal ProductPrice;
    private static final long serialVersionUID = -4073256626483275668L;
   String  Address;


    public Product(int pId, String name, String age, int photoId, String pDescription, BigDecimal ProductPrice, String Address) {
       // setId(pId);
        this.pId=pId;
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.ProductPrice=ProductPrice;
        this.pDescription=pDescription;
        this.Address=Address;
     }

    public Product(String name) {
    }

    public Product() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Product)) return false;

        return (this.pId == ((Product) o).getId());
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = hash * prime + pId;
        hash = hash * prime + (name == null ? 0 : name.hashCode());
        hash = hash * prime + (age == null ? 0 : age.hashCode());
         hash = hash * prime + photoId;
        hash = hash * prime + (ProductPrice == null ? 0 : ProductPrice.hashCode());

          hash = hash * prime + (pDescription == null ? 0 : pDescription.hashCode());

         hash=hash * prime + (Address == null ? 0 : Address.hashCode());

        return hash;
    }


    public static final List<Product> PRODUCT_LIST = new ArrayList<Product>();


    public int getId() {
        return pId;
    }
    public void setId(int id) {
        this.pId = id;
    }


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setProductType(String age) {
        this.age = age;
    }
    public String getProductType() {
        return age;
    }

    public void setPrice(BigDecimal ProductPrice) {
        this.ProductPrice = ProductPrice;
    }
     public BigDecimal getPrice() { return ProductPrice; }

    public void setDescription(String pDescription) {
        this.pDescription = pDescription;
    }
    public String getDescription() {
        return pDescription;
    }

    public void setImageName(int photoId) {
        this.photoId = photoId;
    }
    public int getImageName() {
        return photoId;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public  String  getAddress() { return Address; }


}
