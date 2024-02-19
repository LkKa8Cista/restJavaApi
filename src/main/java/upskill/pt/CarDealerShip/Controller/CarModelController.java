package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.CarModelCore;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarModel;

import java.util.List;

//@RestController
public class CarModelController {
    @Autowired
    CarModelCore carModelCore;



//    public Controller(CarData data){
//        this.carCore = new CarCore(data);
//    }


    @GetMapping(value = "/models/{page}", produces = "application/json")
    public ResponseEntity<List<CarModel>> get100CarsModels(@PathVariable("page") int page){
        List<CarModel> cars = carModelCore.Get100CarModels(page);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping(value= "/model/{id}", produces = "application/json")
    public ResponseEntity<CarModel> getCarBrandById(@PathVariable("id") int id) throws CarException {
        try {
            CarModel car = carModelCore.GetCarModelById(id);
            return new ResponseEntity<>(car,HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/model/new", consumes = "application/json", produces ="application/json")
    public ResponseEntity<CarModel> addNewCarBrand(@RequestBody CarModel car) throws CarException {
        carModelCore.AddNewCarModel(car);
        return new ResponseEntity<>(car,HttpStatus.OK);
    }

}
