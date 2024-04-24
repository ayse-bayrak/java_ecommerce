package e_commerce_app;

import e_commerce_app.category.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private Double price;
    private Integer stock;
    private Integer remainingStock;
    UUID categoryId;// this needs to match categoryId in the Category object

    public Product(UUID id, String name, Double price, Integer stock, Integer remainingStock, UUID categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.remainingStock = remainingStock;
        this.categoryId = categoryId;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getRemainingStock() {
        return remainingStock;
    }

    public UUID getCategoryId() {
        return categoryId;
    }
    public String getCategoryName() throws Exception {

        for (Category category : StaticConstants.CATEGORY_LIST) {
            if (getCategoryId().toString().equals(category.getId().toString())) {
                return category.getName();
            }
        }
        //what is this.categoryId and category.getId() are different type, it can be useful convert to String and then matching with equals method

        throw new Exception("Category not found, " + getName());

        //e.getMessage == Category not found, Milk
        //with split [Category not found, Milk]
        //     index         0              1


           // return null; ---> null pointer exception
    }// u have categoryId, please go to database and check all categoryIds
    // and find matching one and return it name corresponding in that category name

    /*
    Q:if anybody ask you to in interview? What is throw and what is try catch? and what are usage
    A: In my e-commerce application, we have a product and their different categories belongs to this product.
    Amd if anybody creates some category or product with not appropriate category that is existing in the database
   , then I handle this one woth my exception handling. whenever we don't find this category I throw an exception and
   then over we are calling this method. I handle it over there with my try catch and then give a user a meaningful error meassge
     */

    public LocalDateTime getDeliveryDueDate() throws Exception {
        for (Category category : StaticConstants.CATEGORY_LIST) {
            if (getCategoryId().toString().equals(category.getId().toString())) {
                return category.findDeliveryDueDate();
            }
        }
        throw new Exception("Category could not find");
    }
}
