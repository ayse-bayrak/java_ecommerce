package e_commerce_app.category;

import java.time.LocalDateTime;
import java.util.UUID;

public class Electronic extends Category {
    public Electronic(UUID id, String name) {
        super(id, name); // super is used to if I want to call anything from the parent class
    }


//First concrete class always responsible with implementation
    @Override
    public LocalDateTime findDeliveryDueDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.plusDays(4);
    }

    @Override
    public String generatecategoryCode() {
        return "EL-" + getId().toString().substring(0,8);
    }
}
