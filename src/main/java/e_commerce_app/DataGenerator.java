package e_commerce_app;

import e_commerce_app.balance.Balance;
import e_commerce_app.balance.CustomerBalance;
import e_commerce_app.balance.GiftCardBalance;
import e_commerce_app.category.Category;
import e_commerce_app.category.Electronic;
import e_commerce_app.category.Furniture;
import e_commerce_app.category.SkinCare;
import e_commerce_app.discount.AmountBasedDiscount;
import e_commerce_app.discount.Discount;
import e_commerce_app.discount.RateBasedDiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class  DataGenerator {

    public static void createCustomer(){

        Address address1Customer1  = new Address("add","yayla", "06100", "adee", "state");
        Address address2Customer1  = new Address("abc","bagci", "06123", "addvgb", "state");
        Address address1Customer2  = new Address("deb","etlik", "06145", "addytrt", "state");
        List<Address> customer1AddressList = new ArrayList<>();
        List<Address> customer2AddressList = new ArrayList<>();
        customer1AddressList.add(address1Customer1);
        customer1AddressList.add(address2Customer1);
        Customer customer1 = new Customer(UUID.randomUUID(), "AYSE", "AYSE@CYDEO.COM", customer1AddressList );
        Customer customer2 = new Customer(UUID.randomUUID(), "mike", "mike@gmail.com" ); // I can create without address
        //  System.out.println(custom1);

        StaticConstants.CUSTOMER_LIST.add(customer1);  // this object is persisted in the database, if it is not persisted, it is not in the database, it can be deleted whenever your application is done
        StaticConstants.CUSTOMER_LIST.add(customer2);  //this object is persisted in the database // we will hear this terminology a lot
    }//Where you gonna create sample customers, you can right away create it in your main method,
// but I don't want to create in main method
// what was if it is static either, you can call with the class name or you need to import a static

    public static void createCategory(){

        Category category1 = new Furniture(UUID.randomUUID(), "Electronic");
        Category category2 = new Furniture(UUID.randomUUID(), "Furniture");
        Category category3 = new SkinCare(UUID.randomUUID(), "Skin Care");

        StaticConstants.CATEGORY_LIST.add(category1);
        StaticConstants.CATEGORY_LIST.add(category2);
        StaticConstants.CATEGORY_LIST.add(category3);

    }

    public static void createProduct(){
        Product product1 = new Product(UUID.randomUUID(), "PS5", 230.72, 7, 7, StaticConstants.CATEGORY_LIST.get(0).getId());
        Product product2 = new Product(UUID.randomUUID(), "XBOX", 120.4, 15, 7, StaticConstants.CATEGORY_LIST.get(0).getId());
        Product product3 = new Product(UUID.randomUUID(), "CHAIR", 30.87, 85, 85, StaticConstants.CATEGORY_LIST.get(1).getId());
        Product product4 = new Product(UUID.randomUUID(), "Milk", 2.87, 185, 85, UUID.randomUUID());

        StaticConstants.PRODUCT_LIST.add(product1);
        StaticConstants.PRODUCT_LIST.add(product2);
        StaticConstants.PRODUCT_LIST.add(product3);
        StaticConstants.PRODUCT_LIST.add(product4);

    }

     public static void createBalance(){
         Balance customerBalance = new CustomerBalance(StaticConstants.CUSTOMER_LIST.get(0).getId(), 450.00);
         Balance giftCardBalance = new GiftCardBalance(StaticConstants.CUSTOMER_LIST.get(0).getId(), 500.00);

         StaticConstants.CUSTOMER_BALANCE_LIST.add(customerBalance);
         StaticConstants.GIFT_CARD_BALANCE_LIST.add(giftCardBalance);

     }

     public static void createDiscount(){
         Discount amountBasedDiscount = new AmountBasedDiscount(UUID.randomUUID(), "Buy 250 Free 50",250.00,50.00); // if you shop 250, you are gonna get 50 discount
         Discount rateBasedDiscount = new RateBasedDiscount(UUID.randomUUID(), "Buy 500 Free %15",500.00, 15.00); // if you shop 500, you are gonna get %15 discount

         StaticConstants.DISCOUNT_LIST.add(amountBasedDiscount);
         StaticConstants.DISCOUNT_LIST.add(rateBasedDiscount);
     }

}
