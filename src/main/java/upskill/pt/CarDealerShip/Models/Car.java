package upskill.pt.CarDealerShip.Models;

import upskill.pt.CarDealerShip.Enums.Enumerators;
import jakarta.persistence.*;

@Entity
public class Car {
    @Id
    public int vin;
    public String licensePlate;
    @Enumerated(EnumType.ORDINAL)
    public Enumerators.Seats seats;
    @Enumerated(EnumType.ORDINAL)
    public Enumerators.Traction traction;
    @Enumerated(EnumType.ORDINAL)
    public Enumerators.Fuel fuel;
    @Enumerated(EnumType.ORDINAL)
    public Enumerators.Type type;
    @Enumerated(EnumType.ORDINAL)
    public Enumerators.Doors doors;
    @ManyToOne (cascade = CascadeType.ALL)
    public Color color;
    @Enumerated(EnumType.ORDINAL)
    public Enumerators.Status status;

    @ManyToOne (cascade = CascadeType.ALL)
    public CarModel model;

    @ManyToOne (cascade = CascadeType.ALL)
    public Seller seller;

    public Car() {
    }


    public int getVin() {
        return vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Enumerators.Seats getSeats() {
        return seats;
    }

    public Enumerators.Traction getTraction() {
        return traction;
    }

    public Enumerators.Fuel getFuel() {
        return fuel;
    }

    public Enumerators.Type getType() {
        return type;
    }

    public Enumerators.Doors getDoors() {
        return doors;
    }

    public Color getColor() {
        return color;
    }

    public Enumerators.Status getStatus() {
        return status;
    }

    public CarModel getModel() {
        return model;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }


    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setSeats(Enumerators.Seats seats) {
        this.seats = seats;
    }

    public void setTraction(Enumerators.Traction traction) {
        this.traction = traction;
    }

    public void setFuel(Enumerators.Fuel fuel) {
        this.fuel = fuel;
    }

    public void setType(Enumerators.Type type) {
        this.type = type;
    }

    public void setDoors(Enumerators.Doors doors) {
        this.doors = doors;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setStatus(Enumerators.Status status) {
        this.status = status;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
