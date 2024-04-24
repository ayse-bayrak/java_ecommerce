package e_commerce_app.category;

import java.time.LocalDateTime;
import java.util.UUID;

public class SkinCare extends Category {
    public SkinCare(UUID id, String name) {
        super(id, name);
    }

    public LocalDateTime findDeliveryDueDate() {
        return LocalDateTime.now();
    }

//since same category id we don't need to override generatecategoryCode() in here
//directly it can be use from parent class's method beacuse i inherited it from parent class

}

