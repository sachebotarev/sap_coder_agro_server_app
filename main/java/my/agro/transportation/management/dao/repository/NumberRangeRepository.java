package my.agro.transportation.management.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import my.agro.transportation.management.dao.entity.NumberRange;
import my.agro.transportation.management.dao.entity.NumberRangePK;


public interface NumberRangeRepository extends JpaRepository<NumberRange, NumberRangePK> {

}