package e_commerce_app;

import e_commerce_app.balance.Balance;
import e_commerce_app.balance.CustomerBalance;
import e_commerce_app.balance.GiftCardBalance;
import e_commerce_app.category.Category;
import e_commerce_app.discount.Discount;
import e_commerce_app.StaticConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import static e_commerce_app.StaticConstants.*;


public class main {
    public static void main(String[] args) {

        //className.method
        DataGenerator.createCustomer();
        DataGenerator.createCategory();
        DataGenerator.createProduct();
        DataGenerator.createBalance();
        DataGenerator.createDiscount();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Customer");


        for (int i = 0; i < CUSTOMER_LIST.size(); i++) {
            System.out.println("Type " + i + " for customer:" + CUSTOMER_LIST.get(i).getUserName());
        }
        Customer customer = CUSTOMER_LIST.get(scanner.nextInt()); // we gonna catch it..
        //String i = "20"; same, instead of String type is Customer, name is anything...

        Cart cart = new Cart(customer);  // I create empty cart, empty cart is created

        while (true) {
            System.out.println("What would you like to do? Just type id for selection");
            for (int i = 0; i < prepareMenuOptions().length; i++) {
                System.out.println(i + "-" + prepareMenuOptions()[i]);
            }
            int menuSelection = scanner.nextInt();

            switch (menuSelection) {
                case 0:
                    for (Category category : StaticConstants.CATEGORY_LIST) {
                        System.out.println("Category Code: " + category.generatecategoryCode() + " category name : " + category.getName());
                    }
                   break;

                case 1: // list products // product name, product category name
                    try { // we need to catch and handling
                        for (Product product : StaticConstants.PRODUCT_LIST) { // go to category list check for each category and find the matching product categoryID with the categoryId coming form the database
                            System.out.println("Product Name:" + product.getName() + "-" + "Category Name: " + product.getCategoryName());
                        }
                    } catch (Exception e) {
                        System.err.println("Product can not be printed, because category name not found for product name:" + e.getMessage().split(",")[1]);
                    } // handling portion try catch portion mostly implementing this one so we put it in the main method
                    // catch meaning when this error happens, what you want to do in the catch portion
                    break;
                case 2: // list discounts
                    for (Discount discount : StaticConstants.DISCOUNT_LIST) {
                        System.out.println("Discount name: " + discount.getName() + " Threshold Amount: " + discount.getThresholdAmount());
                    }
                    break;
                case 3://"See Balance"

                    for (Balance balance : StaticConstants.CUSTOMER_BALANCE_LIST) {
                        if (balance.getCustomerId().toString().equals(customer.getId())) {
                            System.out.println("Customer balance: " + balance.getBalance());
                        }

                        for (Balance giftBalance : StaticConstants.GIFT_CARD_BALANCE_LIST) {
                            if (giftBalance.getCustomerId().toString().equals(customer.getId())) {
                                System.out.println("Gift balance: " + giftBalance.getBalance());
                            }
                        }

                    }

                    CustomerBalance cBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance gBalance = findGiftCardBalance(customer.getId());
                    double totalBalance = cBalance.getBalance() + gBalance.getBalance();
                    System.out.println("totalBalance = " + totalBalance);
                    System.out.println("Customer Balance = " + cBalance);
                    System.out.println("Gift Card Balance = " + gBalance);

                    break;
                case 4://"Add Balance"
                    CustomerBalance customerBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance giftCardBalance = findGiftCardBalance(customer.getId());
                    System.out.println("Which account would you like to add?");
                    System.out.println("Type 1 for Customer Balance:" + customerBalance.getBalance());
                    System.out.println("Type 2 for Gift Card  Balance:" + giftCardBalance.getBalance());
                    int balanceAccountSelection = scanner.nextInt();
                    System.out.println("How much you would like to add?");
                    double additionalAmount = scanner.nextInt();

                    switch (balanceAccountSelection){
                        case 1:
                            customerBalance.addBalance(additionalAmount);
                            System.out.println("New Customer Balance: " +customerBalance.getBalance());
                            break;
                        case 2:
                            giftCardBalance.addBalance(additionalAmount);
                            System.out.println("New Gift Card Balance: " +giftCardBalance.getBalance());
                            break;
                    }

                    break;
                case 5: //place an order, buy product happen here,--this portion stars video part 3 49:00
                    Map<Product, Integer> map = new HashMap<>();  // empty cart
                    cart.setProductMap(map);
                    while (true) { // I use while loop to keep adding more products in the cart
                        System.out.println("Which product you want to add to your chart. For exit product selection Type : exit");
                        for (Product product : StaticConstants.PRODUCT_LIST) {
                            try {
                                System.out.println("id: " + product.getId() + "price: " + product.getPrice()+
                                        "product category: " + product.getCategoryName() +
                                        "stock: " + product.getRemainingStock()+
                                        "product delivery due: " + product.getDeliveryDueDate());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        String productId = scanner.next();
                        try {
                            Product product = findProductById(productId);
                            if (!putItemToCartIfStockAvailable(cart, product)){
                                System.out.println("Stock is insufficient, please try again ");
                                continue; //next iteration
                            }
                        } catch (Exception e) {
                            System.out.println("Product does not exit, please try again");
                            continue;
                        }

                        System.out.println("Do you want to add more product. Type Y for adding more, N for exit");
                        String decision = scanner.next();
                        if (!decision.equals("Y")){
                            break; // break the loop (while loop)
                        }
                    }

//                    System.out.println("seems there are discount options. Dou you want to see amnd apply to your crat if it is applicable. Fpr no discount type no");
//                    for (Discount discount : StaticConstants.DISCOUNT_LIST) {
//                        System.out.println("discount id " + discount.getId() + " discount name: "+ discount.getName());
//                    }
//
//                    String discountId = scanner.next();
//
//                    if (!discountId.equals("no")) {
//
//
//                    }
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }
        }

    }

    private static boolean putItemToCartIfStockAvailable(Cart cart, Product product) {
        System.out.println("Please provide product count: ");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();

        // how many existing product in your cart? I need to know this one
        Integer cartCount = cart.getProductMap().get(product);
        // what can be the scenario to add product

        if (cartCount != null && product.getRemainingStock() > cartCount + count) {
            cart.getProductMap().put(product, cartCount+count);
            return true;

        }else if (product.getRemainingStock()>=count) {
        cart.getProductMap().put(product, count);
        return true;
        }

        return false;
    }

    private static Product findProductById(String productId) throws Exception {

        for (Product product : StaticConstants.PRODUCT_LIST) {
            if (product.getId().toString().equals(productId)) {
                return product;
            }
        }
        throw new Exception("Product not find");

    }
    //I' m just writing the menu in the screen, since we don't have any UI right now, you can not see in the application
        //click or whatever you want to do, this is Java perspective, we just gonna write everything in the console and then
        //we are gonna make a choice, and we are gonna put one, two, three whatever, and based on that one we're gonna do our business action items

        // inside the main and no other classes will have an access to this method,
        // that's why I'm building private only accessible inside the main class

private static CustomerBalance  findCustomerBalance(UUID customerId){

        for (Balance customerBalance : StaticConstants.CUSTOMER_BALANCE_LIST){
            if (customerBalance.getCustomerId().toString().equals(customerId.toString()))
            {
                return (CustomerBalance) customerBalance; // what is casting meaning.. this is real life example give example from project application
            }

        }

        CustomerBalance customerBalance = new CustomerBalance(customerId, 0d);// whenever I create new object in here balance I need to put database
        StaticConstants.CUSTOMER_BALANCE_LIST.add(customerBalance);
        return customerBalance;

}

    private static GiftCardBalance findGiftCardBalance(UUID customerId){  // part 3 : 15-33

        for (Balance giftCardBalance : StaticConstants.CUSTOMER_BALANCE_LIST){
            if (giftCardBalance.getCustomerId().toString().equals(customerId.toString()))
            {
                return (GiftCardBalance) giftCardBalance;
            }

        }

        GiftCardBalance giftCardBalance = new GiftCardBalance(customerId, 0d);
        StaticConstants.GIFT_CARD_BALANCE_LIST.add(giftCardBalance);
        return giftCardBalance;

    }


        private static String[] prepareMenuOptions(){ // created by 1:41 time on lab-2 videos
            return new String[] {"List Categories", "List Products", "List Discount", "See Balance", "Add Balance",
                    "Place an order", "See Cart", "See order details", "See your adress", "Close App"};// this is menu I am creating menu items right now
        }

        // static can not call something non-static
        // since main is static, method which will be called in the main method can be only static

        // design perspective is better to separate,
        // every method responsibility should be unique to that one.
    }

