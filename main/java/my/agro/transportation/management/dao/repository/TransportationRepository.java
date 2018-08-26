package my.agro.transportation.management.dao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import my.agro.transportation.management.dao.entity.Transportation;

public interface TransportationRepository extends JpaRepository<Transportation, String> {
}