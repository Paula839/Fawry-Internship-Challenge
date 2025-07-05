package Product.Interfaces;

import java.time.LocalDate;

public interface ExpirableProduct extends Product {
    default boolean isExpired() {
        return LocalDate.now().isAfter(getExpiryDate());
    }
    LocalDate getExpiryDate();

}
