package cl.testSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.testSpring.domain.Car;
import cl.testSpring.repository.CarRepository;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findById(String id) {
        return carRepository.findById(id);
    }

    
    public Car createOrUpdate(Car car) {
        return carRepository.save(car);
    }

    
    public void deleteById(String id) {
    	carRepository.deleteById(id);
    }
}
