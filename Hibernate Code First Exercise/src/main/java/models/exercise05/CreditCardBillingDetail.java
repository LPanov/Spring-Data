package models.exercise05;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "cc")
public class CreditCardBillingDetail extends BaseBillingDetail {
    @Column(name = "type")
    private String type;

    @Column(name = "expiration_month" )
    private LocalDate expirationMonth;

    @Column(name = "expiration_year")
    private LocalDate expirationYear;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(LocalDate expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public LocalDate getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(LocalDate expirationYear) {
        this.expirationYear = expirationYear;
    }
}
