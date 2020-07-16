package cl.testSpring.presentation;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cl.testSpring.domain.Car;
import cl.testSpring.exception.ServiceException;
import cl.testSpring.service.CarService;
import lombok.extern.slf4j.Slf4j;

//http://localhost:5000/api/v1/cars
@RestController
@RequestMapping({"/api/v1/cars"})
@Slf4j
public class CarController {

    @Autowired
    private  CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}
 
    @GetMapping
    public List<Car> findAll() {
        return carService.findAll();
    }

    @PostMapping
    public ResponseEntity<Car> create(@Valid @RequestBody Car car) {
    	log.error("Que llega."+car);
    	if ( null == car.getColor() || null == car.getPatente() || null == car.getTipoAuto()) {
			log.error("Bad request : Faltan parametrós.");
			throw new ServiceException(String.valueOf(HttpStatus.BAD_REQUEST.value()),"Deben enviarse todos los parámetros.");			
		}
        return ResponseEntity.ok(carService.createOrUpdate(car));
    }
   
    @GetMapping("/insertDeApi/")
    public List<Car> insertApi() {
    	
    	List<Car> losAutosIngresados= new ArrayList<>();
    	RestTemplate restTemplate = new RestTemplate();
    	//Llamada a la api por resttemplate
    	ResponseEntity<List<Car>> response = restTemplate.exchange(
    	  "https://arsene.azurewebsites.net/LicensePlate",
    	  HttpMethod.GET,
    	  null,
    	  new ParameterizedTypeReference<List<Car>>(){});
    	List<Car> losAutosTraidos = response.getBody();
    	losAutosTraidos.stream().parallel().forEach(response1 -> {
				log.info("Parsing Autos");
				Car ca = new Car();
		    	try {	
		    		ca.setPatente(response1.getPatente());
		    		ca.setTipoAuto(response1.getTipoAuto());
		    		ca.setColor(response1.getColor());
			    	ca = carService.createOrUpdate(ca);
			    	 losAutosIngresados.add(ca); 	
		    	}catch (Exception e) {
		    		log.info("por el catch ");
		    	}
		});

    	return losAutosIngresados;
    }
    
  
	@GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable String id) {

        Optional<Car> car = carService.findById(id);
        if (!car.isPresent()) {
			throw new ServiceException(HttpStatus.MULTI_STATUS.toString(), "Car with id " + id + " does not exist.");
        }

        return ResponseEntity.ok(car.get());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Car> update(@PathVariable String id, @Valid @RequestBody Car car) {
        
        Optional<Car> p = carService.findById(id);

        if (!p.isPresent()) {
			throw new ServiceException(HttpStatus.MULTI_STATUS.toString(), "Car with id " + id + " does not exist.");
        }
                
        p.get().setPatente(car.getPatente());
        p.get().setTipoAuto(car.getTipoAuto());
        p.get().setColor(car.getColor());

            
        return ResponseEntity.ok(carService.createOrUpdate(p.get()));
    }

    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        Optional<Car> p = carService.findById(id);

        if (!p.isPresent()) {
			throw new ServiceException(HttpStatus.MULTI_STATUS.toString(), "Car with id " + id + " does not exist.");
        }

        carService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
