package com.springtest.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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

import cl.testSpring.domain.Car;
import cl.testSpring.repository.CarRepository;
import cl.testSpring.service.CarService;

import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProductServiceTest {

    private static Car p1;
    private static Car p2;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        p1 = new Car("P1", "Created Dummy Product", "");
        p2 = new Car("P2", "Created For Update Dummy Product", "");
    }

    @Test
    public void findAllTest_WhenNoRecord() {

       Mockito.when(carRepository.findAll()).thenReturn(Arrays.asList());
       assertThat(carService.findAll().size(), is(0));
       Mockito.verify(carRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findAllTest_WhenRecord() {

        Mockito.when(carRepository.findAll()).thenReturn(Arrays.asList(p1, p2));
        assertThat(carService.findAll().size(), is(2));
        assertThat(carService.findAll().get(0), is(p1));
        assertThat(carService.findAll().get(1),is(p2));
        Mockito.verify(carRepository, Mockito.times(3)).findAll();

    }

    @Test
    public void findById() {

        Mockito.when(carRepository.findById("1L")).thenReturn(Optional.of(p1));
        assertThat(carService.findById("1L"), is(Optional.of(p1)));
        Mockito.verify(carRepository, Mockito.times(1)).findById("1L");
    }


    @Test
    void createOrUpdate() {

        Mockito.when(carRepository.save(p1)).thenReturn(p1);
        assertThat(carService.createOrUpdate(p1), is(p1));
        Mockito.verify(carRepository, Mockito.times(1)).save(p1);

        Mockito.when(carRepository.save(p2)).thenReturn(p2);
        assertThat(carService.createOrUpdate(p2).getPatente(), is("P2"));
        Mockito.verify(carRepository, Mockito.times(1)).save(p2);
    }

    @Test
    void deleteById() {
        carService.deleteById("1L");
        Mockito.verify(carRepository, Mockito.times(1)).deleteById("1L");
    }
}