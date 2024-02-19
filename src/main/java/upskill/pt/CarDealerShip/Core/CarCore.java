package upskill.pt.CarDealerShip.Core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upskill.pt.CarDealerShip.Data.CarData;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Car;

import java.util.List;

@Service
public class CarCore {
    @Autowired
    CarData data;

    public CarCore(CarData data){
        this.data = data;
    }

    public List<Car> Get100Cars(int page) {
        int[] a = new int [2];
        if (page < 2){
            a[1] = 99;
        } else {
            a[0] = page * 100 - 100;
            a[1] = page * 100 -1;
        }
        return data.findAll().subList(a[0], Math.min(a[1] + 1, data.findAll().size()));
    }

    public Car AddNewCar(Car car) throws CarException {
        int temp = car.getVin();
        if (data.existsById(temp)){
            throw new CarException("Car", "AddNewCar", "Vin already existed");
        }

        data.saveAndFlush(car);
        return car;
    }

    public Car GetCarByVin(int vin) throws CarException {
        if(data.existsById(vin)){
            return data.findById(vin).get();
        } else {
            throw new CarException("Car", "GetCarByVin", "Vin did not exist") ;
        }
    }
}
