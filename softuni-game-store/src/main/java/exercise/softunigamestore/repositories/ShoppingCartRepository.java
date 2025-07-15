package exercise.softunigamestore.repositories;

import exercise.softunigamestore.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {

    List<ShoppingCartItem> findAllByUserId(Long userId);

    void removeAllByUserId(Long userId);
}
