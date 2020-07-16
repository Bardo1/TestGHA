package cl.testSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.testSpring.domain.Car;


@Repository
public interface CarRepository extends JpaRepository<Car, String> {
}
