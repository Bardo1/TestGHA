package com.springtest.controller;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.testSpring.domain.Car;
import cl.testSpring.presentation.CarController;
import cl.testSpring.service.CarService;

import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CarControllerTest {

    private static Car p1;
    private static Car p2;
    private static Car p3;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
     
        p1 = new Car("Patente1", "Patente1", "Created Dummy car");
        p2 = new Car("Patente2", "Created For Update Dummy car", "");
        p3 = new Car("Patente3", "Updated Dummy car","");

    }

    @Test
    void findAll_whenNoRecord() {

        Mockito.when(carService.findAll()).thenReturn(Arrays.asList());
        assertThat(carController.findAll().size(), is(0));
        Mockito.verify(carService, Mockito.times(1)).findAll();
    }

    @Test
    void findAll_whenRecord() {

        Mockito.when(carService.findAll()).thenReturn(Arrays.asList(p1, p2));
        assertThat(carController.findAll().size(), is(2));
        Mockito.verify(carService, Mockito.times(1)).findAll();
    }

    @Test
    void create() {

        ResponseEntity<Car> p = carController.create(p1);
        Mockito.verify(carService, Mockito.times(1)).createOrUpdate(p1);
    }

    @Test
    void findById_WhenMatch() {

        Mockito.when(carService.findById("1L")).thenReturn(Optional.of(p1));
        ResponseEntity<Car> p = carController.findById("1L");
        assertThat(p.getBody(), is(p1) );
    }

    
    void findById_WhenNoMatch() {

        Mockito.when(carService.findById("2")).thenReturn(Optional.empty());
        ResponseEntity<Car> p = carController.findById("2");
        assertThat(p.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    
    void update_WhenNotFound() {

        Mockito.when(carService.findById("1")).thenReturn(Optional.empty());
        ResponseEntity<Car> p = carController.update("1", p1);
        assertThat(p.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    void update_WhenFound() {
    	
        Mockito.when(carService.findById("1L")).thenReturn(Optional.of(p1));
        //Since the Controller internally saves p1 taking args of p3.
        Mockito.when(carService.createOrUpdate(p1)).thenReturn(p3);
        assertThat(carController.update("1L", p3).getBody().getPatente(), is("Patente3"));
        Mockito.verify(carService, Mockito.times(1)).createOrUpdate(p1);
        
    }

    @Test
    void deleteById_WhenFound() {
        Mockito.when(carService.findById("1L")).thenReturn(Optional.of(p1));
        carController.deleteById("1L");
        Mockito.verify(carService, Mockito.times(1)).deleteById("1L");
    }
}