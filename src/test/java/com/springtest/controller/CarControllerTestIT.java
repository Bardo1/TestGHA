package com.springtest.controller;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cl.testSpring.domain.Car;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTestIT {

    private static Car p1;
    private static Car p2;
    private static Car p3;
    private static Car p4;
    private static Car p5;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void init() {
    	

        p1 = new Car("Patente1", "Patente1", "Created Dummy car");
        p2 = new Car("Patente2", "Created For Update Dummy car", "");
        p3 = new Car("Patente3", "Updated Dummy car","");
        p4 = new Car("Patente4", "Patente1", "Created Dummy car");
        p5 = new Car("Patente5", "Created For Update Dummy car", "");
    	
     /*   p1 = new Car("Patente1", "Created Dummy Product", "");
        p2 = new Car("Patente2", "Created For Update Dummy Product", "");
        p3 = new Car("Patente3", "Updated Dummy Product","");
        p4 = new Car("Patente4", "Created To be Found Dummy Product","");
        p5 = new Car("Patente5", "Created For deleting Dummy Product","");*/
    }
    
    @Test
    public void findAllTestIT() {

        ResponseEntity<Car[]> result= this.restTemplate
                .getForEntity("http://127.0.0.1:"+port+"/cars/", Car[].class);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(result.getBody(), is(notNullValue()));

    }

    @Test
    public void createTestIT() {

        HttpEntity<Car> request = new HttpEntity<>(p1);
        ResponseEntity<Car> response = restTemplate.postForEntity("http://127.0.0.1:"+port+"/cars/", request, Car.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody().getPatente(), is("Patente1"));
        restTemplate.delete("http://127.0.0.1:"+port+"/cars/"+response.getBody().getId());
    }

    
    @Test
    public void updateTestIT() {

        HttpEntity<Car> request = new HttpEntity<>(p2);
        ResponseEntity<Car> response = restTemplate.postForEntity("http://127.0.0.1:"+port+"/cars/", request, Car.class);

        String id = response.getBody().getId();
        request = new HttpEntity<Car>(p3);
        response = restTemplate.exchange("http://127.0.0.1:"+port+"/cars/"+id, HttpMethod.PUT, request, Car.class);

        assertThat(response.getBody().getPatente(), is("Patente3"));
        assertThat(response.getBody().getId(), is(id));
        restTemplate.delete("http://127.0.0.1:"+port+"/products/"+id);
    }

    @Test
    public void findByIDTestIT() {

        HttpEntity<Car> request = new HttpEntity<>(p4);
        ResponseEntity<Car> response = restTemplate.postForEntity("http://127.0.0.1:"+port+"/cars/", request, Car.class);

        String id = response.getBody().getId();
        
        response = restTemplate.getForEntity("http://127.0.0.1:"+port+"/products/"+id, Car.class);
        assertThat(response.getBody().getId(), is(id));
        assertThat(response.getBody().getTipoAuto(), equalTo(p4.getTipoAuto()));
        restTemplate.delete("http://127.0.0.1:"+port+"/cars/"+id);
    }

    @Test
    public void deleteByIDTestIT() {

        HttpEntity<Car> request = new HttpEntity<>(p5);
        ResponseEntity<Car> response = restTemplate.postForEntity("http://127.0.0.1:"+port+"/cars/", request, Car.class);

        String id = response.getBody().getId();

        restTemplate.delete("http://127.0.0.1:"+port+"/cars/"+id);
        response = restTemplate.getForEntity("http://127.0.0.1:"+port+"/cars/"+id, Car.class);

        assertThat(response.getBody(), is(nullValue()));

    }


}
