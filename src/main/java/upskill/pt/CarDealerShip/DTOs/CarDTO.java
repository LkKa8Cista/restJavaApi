package upskill.pt.CarDealerShip.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import upskill.pt.CarDealerShip.Enums.Enumerators;
import upskill.pt.CarDealerShip.Models.Car;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO extends RepresentationModel<CarDTO> {
    public int vin;
    public String licensePlate;
    public Enumerators.Seats seats;
    public Enumerators.Traction traction;
    public Enumerators.Fuel fuel;
    public Enumerators.Type type;
    public Enumerators.Doors doors;
    public ColorDTO color;
    public Enumerators.Status status;
    public CarModelDTO model;
    public SellerDTO seller;

    public static CarDTO toCarDTO(Car car){
        return new CarDTO(car.getVin(), car.getLicensePlate(), car.getSeats(),car.getTraction(),
                car.getFuel(),car.getType(),car.getDoors(), ColorDTO.toColorDTO(car.getColor()),car.getStatus(),
                CarModelDTO.toCarModelDTO(car.getModel()),SellerDTO.toSellerDTO(car.getSeller()));
    }
}
