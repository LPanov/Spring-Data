package exercise.productshop.repositories;

import exercise.productshop.dtos.ProductSellerDto;
import exercise.productshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.price > :lowBound and p.price < :highBound and p.buyer is null order by p.price")
    List<Product> getProductsByPriceWithNoBuyer(BigDecimal lowBound, BigDecimal highBound);

    @Query("select sum(p.price) from Product p join p.categories c where c.id = :id")
    BigDecimal getTotalPriceById(Long id);
}
