package upskill.pt.CarDealerShip.Models;
import upskill.pt.CarDealerShip.Enums.Enumerators;
import jakarta.persistence.*;

@Entity
public class CarBrand {
    @Id
    @GeneratedValue
    public int id;
    public String name;
    @Enumerated(EnumType.ORDINAL)
    public Enumerators.Country country;

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

    public Enumerators.Country getCountry() {
        return country;
    }

    public void setCountry(Enumerators.Country country) {
        this.country = country;
    }
}
