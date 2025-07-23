package exercise.cardealer.repositories;

import exercise.cardealer.dtos.SupplierReportDto;
import exercise.cardealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("select s.id, s.name, size(s.parts) from Supplier s where s.isImporter = :isImporter")
    List<SupplierReportDto> generateReport(boolean isImporter);
}
