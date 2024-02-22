package upskill.pt.CarDealerShip.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import upskill.pt.CarDealerShip.Models.Car;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarFinancialDTO extends RepresentationModel<CarFinancialDTO> {
    public int vin;
    public double priceBuy;
    public double priceSell;
    public double priceFinal;

    public static CarFinancialDTO toCarDTO(Car car){
        return new CarFinancialDTO(car.getVin(), car.getPriceBuy(),  car.getPriceSell(), car.getPriceFinal());
    }
}
