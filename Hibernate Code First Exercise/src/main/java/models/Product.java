package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @Column(nullable = false)
    private String name;

    private Double quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;

    public String getName() {
        return name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<Sale> getSales() {
        return sales;
    }
}
