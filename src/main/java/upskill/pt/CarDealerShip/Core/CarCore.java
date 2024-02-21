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


    public Car AddNewCar(Car car) throws CarException {
        int temp = car.getVin();
        if (data.existsById(temp)){
            throw new CarException("Car", "AddNewCar", "Vin already existed");
        }

        data.saveAndFlush(car);
        return car;
    }

    public CarDTO GetCarByVin(int vin) throws CarException {
        if(data.existsById(vin)){
            Car car = data.findById(vin).get();
            return CarDTO.toCarDTO(car);
        } else {
            throw new CarException("Car", "GetCarByVin", "Vin did not exist") ;
        }
    }

    public Car SetCarAsSold(int vin) throws CarException {
        if(!data.existsById(vin)){
            throw new CarException("Car", "GetCarByVin", "Vin did not exist") ;
        } else {
            Car temp = data.findById(vin).get();
            temp.setStatus(Enumerators.Status.SOLD);
            data.save(temp);
            return temp;
        }
    }


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
}
//((PageRequest.of(page,size, Sort.by(sort))).map(CarDTO::toCarDTO), id)
