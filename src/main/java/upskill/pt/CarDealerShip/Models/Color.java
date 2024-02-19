package upskill.pt.CarDealerShip.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Color {
    @Id
    @GeneratedValue
    public int id;
    public String commonName;
    public String hexCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public Color() {
    }

    public Color(String commonName, String hexCode) {
        this.commonName = commonName;
        this.hexCode = hexCode;
    }
}
