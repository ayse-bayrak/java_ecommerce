package e_commerce_app;

import e_commerce_app.balance.Balance;
import e_commerce_app.category.Category;
import e_commerce_app.discount.Discount;

import java.util.ArrayList;
import java.util.List;
//import package.name.MyClass


public class StaticConstants { // this is created for data base, you have a real database


    // customer 1--- {UUID.randomUUID(), "AYSE", "AYSE@CYDEO.COM", {"add","yayla", "06100", "adee", "state"}}
    //customer 2---
    // this is my database, it is holding all the customer
    // should be final, it is a good practice, not mandatory but it is good practice, because it is a database
    // because this structure database never gonna change, there is fix
    //This List's responsibility is bucket
    // So it is capital, that is naming convention

    public static final List<Customer> CUSTOMER_LIST  = new ArrayList<>();// customer1, customer2
    public static final List<Category> CATEGORY_LIST = new ArrayList<>(); // polymorphisM
    public static final List<Product> PRODUCT_LIST = new ArrayList<>();
    public static final List<Balance> CUSTOMER_BALANCE_LIST = new ArrayList<>(); // it can be one BALANCE LIST but it can be separated
    public static final List<Balance> GIFT_CARD_BALANCE_LIST = new ArrayList<>();
    public static final List<Discount> DISCOUNT_LIST = new ArrayList<>();

}
