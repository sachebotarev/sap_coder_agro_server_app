package my.agro.transportation.management.dao.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import my.agro.transportation.management.dao.entity.ShippingLocation;

public interface ShippingLocationRepository extends JpaRepository<ShippingLocation, String> {
	Set<ShippingLocation> findByLocationType(String locationType);
}
