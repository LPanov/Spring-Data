package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.DeviceType;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Query("select d from Device d where d.deviceType = :type and d.price < :price and d.storage >= :storage order by lower(d.brand)")
    List<Device> findByTypePriceAndStorage(DeviceType type, Double price, Integer storage);
}
