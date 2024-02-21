package upskill.pt.CarDealerShip.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import upskill.pt.CarDealerShip.Enums.Enumerators;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public double priceBuy;

    public double priceSell;

}
