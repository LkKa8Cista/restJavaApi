package upskill.pt.CarDealerShip.Models;

import jakarta.persistence.*;

@Entity
public class CarModel {
    @Id
    @GeneratedValue
    public int id;
    public String name;
    @ManyToOne (cascade = CascadeType.ALL)
    public CarBrand brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }
}
