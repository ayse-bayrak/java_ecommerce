package e_commerce_app.category;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Category {  // I will create one parent class called Category

    private UUID id;  // COMMON THINGS
    private String name;  // COMMON THINGS

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract LocalDateTime findDeliveryDueDate(); // I need to implement every child class
    // abstraction method
    // I know what should be inside the class? But I don't know how to implement
    // it needs to calculate the delivery time but I don't know how to calculate yet?
    //Every category is going to decide how to implement it?  -->
    //for abstract method, class must be abstract
    //if my class has any abstract method inside,  this class can be either interface or abstract class.

    //Interface can not have constructor.
    //with Java 8 inside interface I can have default and static method.

    public String generatecategoryCode() {
        return id.toString().substring(0,8).concat("-").concat(name.substring(0,2));
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "ozzy_e_commerce_lab.category.Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", generatecategoryCode='" + generatecategoryCode() + '\'' +
                '}';
    }
}
