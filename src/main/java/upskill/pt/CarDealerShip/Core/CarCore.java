package upskill.pt.CarDealerShip.Core;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import upskill.pt.CarDealerShip.DTOs.CarDTO;
import upskill.pt.CarDealerShip.DTOs.CarModelDTO;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.DTOs.SellerDTO;
import upskill.pt.CarDealerShip.Data.CarData;
import upskill.pt.CarDealerShip.Enums.Enumerators;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Car;
import upskill.pt.CarDealerShip.Models.CarModel;
import upskill.pt.CarDealerShip.Models.Color;
import upskill.pt.CarDealerShip.Models.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@NoArgsConstructor
public class CarCore {
    @Autowired
    CarData data;

    public CarCore(CarData data){
        this.data = data;
    }


    public Page<CarDTO> GetCars(int page, int size) {
        return this.data.findAll(PageRequest.of(page,size)).map(CarDTO::toCarDTO);
    }

//    public List<Car> InStockCars() {
//        List<Car> temp = data.findAll();
//        List<Car> returnable = new ArrayList<>();
//        for (Car car : temp){
//            if (car.getStatus() == Enumerators.Status.SOLD){
//                returnable.add(car);
//            }
//        }
//
//        return returnable;
//    }

    public Page<CarDTO> getInStockCars(int page, int size, String sort) {
        return this.data.findInStockCars(PageRequest.of(page,size, Sort.by(sort))).map(CarDTO::toCarDTO);
    }


    public CarDTO AddNewCar(Car car) throws CarException {
        int temp = car.getVin();
        if (data.existsById(temp)){
            throw new CarException("Car", "AddNewCar", "Vin already existed");
        }

        data.saveAndFlush(car);
        return CarDTO.toCarDTO(car);
    }

    public CarDTO GetCarByVin(int vin) throws CarException {
        if(data.existsById(vin)){
            Car car = data.findById(vin).get();
            return CarDTO.toCarDTO(car);
        } else {
            throw new CarException("Car", "GetCarByVin", "Vin did not exist") ;
        }
    }

    public CarDTO SetCarAsSold(int vin, double priceFinal) throws CarException {
        if(!data.existsById(vin)){
            throw new CarException("Car", "SetCarAsSold", "Vin did not exist") ;
        } else {
            Car temp = data.findById(vin).get();
            double valueToCompare = temp.getPriceSell();

            if (priceFinal < valueToCompare * 0.9){
                throw new CarException("Car", "SetCarAsSold", "value inputted isn´t allowed") ;
            }

            temp.setStatus(Enumerators.Status.SOLD);
            temp.setPriceFinal(priceFinal);
            data.save(temp);
            return CarDTO.toCarDTO(temp);
        }
    }

    public CarDTO SetCarStatus(int vin, String set) throws CarException {
        if(!data.existsById(vin)){
            throw new CarException("Car", "GetCarByVin", "Vin did not exist") ;
        } else {
            Car temp = data.findById(vin).get();
            switch (set.toLowerCase().trim()){
                case "b" -> temp.setStatus(Enumerators.Status.BOUGHT_ARRIVING);
                case "s" -> temp.setStatus(Enumerators.Status.IN_STOCK);
                case "p" -> temp.setStatus(Enumerators.Status.PROMISE);
                default -> temp.setStatus(Enumerators.Status.UNKNOWN);
            }

            data.save(temp);
            return CarDTO.toCarDTO(temp);
        }
    }

    public CarDTO UpdateCar(CarDTO car) throws CarException{
        if(!data.existsById(car.getVin())){
            throw new CarException("Car", "GetCarByVin", "Vin did not exist") ;
        }

        Car temp = data.findById(car.getVin()).get();

        if (car.licensePlate != null) { temp.setLicensePlate(car.getLicensePlate());}
        if (car.seats != null) { temp.setSeats(car.getSeats());}
        if (car.traction != null) { temp.setTraction(car.getTraction());}
        if (car.fuel != null) { temp.setFuel(car.getFuel());}
        if (car.type != null) { temp.setType(car.getType());}
        if (car.doors != null) { temp.setDoors(car.getDoors());}
        if (car.color != null) { temp.setColor(new Color(car.color.getId(), car.color.getCommonName(),car.color.getHexCode()));}
        if (car.model != null) { temp.setModel(new CarModel(car.model.getId(),car.model.getName(),car.model.getBrand()));}
        if (car.seller != null) { temp.setSeller(new Seller(car.seller.getId(), car.seller.getName(), car.seller.getEmail(), car.seller.getPhoneNumber()));}
        if (car.priceSell != 0) { temp.setPriceSell(car.getPriceSell());}

        data.save(temp);
        return CarDTO.toCarDTO(temp);
    }
    //NKNOWN, BOUGHT_ARRIVING, IN_STOCK, PROMISE, SOLD


//    public List<Car> CarsBySeller(int id) {
//        List<Car> temp = data.findAll();
//        List<Car> returnable = new ArrayList<>();
//        int track = 0;
//        for (Car car : temp){
//            if (car.getSeller().getId() == id){
//                returnable.add(car);
//                track++;
//            }
//        }
//        return track != 0 ? returnable : null;
//    }

    public Page<CarDTO> CarsBySeller(int page, int size, String sort, int id){
        return this.data.findCarsSoldBy((PageRequest.of(page,size,Sort.by(sort))),id).map(CarDTO::toCarDTO);
    }

    public Page<CarDTO> CarsByBrand(int page, int size, String sort, String brand){
        String brandUp = brand.toLowerCase().trim();
        return this.data.findCarsByBrand((PageRequest.of(page,size,Sort.by(sort))),brandUp).map(CarDTO::toCarDTO);
    }

    public Page<CarDTO> CarsByType(int page, int size, String sort, String type){

        int tempTypeNumber = 10;

        switch (type.toLowerCase().trim()){
            case "q" -> tempTypeNumber = 0;
            case "a" -> tempTypeNumber = 1;
            case "b" -> tempTypeNumber = 2;
            case "c" -> tempTypeNumber = 3;
            case "d" -> tempTypeNumber = 4;
            case "e" -> tempTypeNumber = 5;
            case "f" -> tempTypeNumber = 6;
            case "j" -> tempTypeNumber = 7;
            case "m" -> tempTypeNumber = 8;
            case "s" -> tempTypeNumber = 9;
        }
        return tempTypeNumber != 10 ? this.data.findCarsByType((PageRequest.of(page,size,Sort.by(sort))),tempTypeNumber).map(CarDTO::toCarDTO) : null;
    }

}
//((PageRequest.of(page,size, Sort.by(sort))).map(CarDTO::toCarDTO), id)
