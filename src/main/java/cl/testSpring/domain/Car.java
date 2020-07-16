package cl.testSpring.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="TBL_CARS")
public class Car {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
	 
    @Column(name="patente")
    private String patente;
        
    @Column(name="tipoauto")
	 private String tipoAuto;
    
    @Column(name="color")
	 private String color;
    
    
    public Car(String patente, String tipoauto, String color) {
		super();
		this.patente = patente;
		this.tipoAuto = tipoauto;
		this.color = color;
	}

     public Car() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getTipoAuto() {
		return tipoAuto;
	}

	public void setTipoAuto(String tipoAuto) {
		this.tipoAuto = tipoAuto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	
     

	 
	}

