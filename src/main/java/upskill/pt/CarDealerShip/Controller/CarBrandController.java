package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.CarBrandCore;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarBrand;

import java.util.List;

//@RestController
public class CarBrandController {
    @Autowired
    CarBrandCore carBrandCore;



//    public Controller(CarData data){
//        this.carCore = new CarCore(data);
//    }


    @GetMapping(value = "/brands/{page}", produces = "application/json")
    public ResponseEntity<List<CarBrand>> get100Cars(@PathVariable("page") int page){
        List<CarBrand> cars = carBrandCore.Get100CarBrands(page);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping(value= "/brand/{id}", produces = "application/json")
    public ResponseEntity<CarBrand> getCarBrandById(@PathVariable("id") int id) throws CarException {
        try {
            CarBrand car = carBrandCore.GetCarBrandById(id);
            return new ResponseEntity<>(car,HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "brand/new", consumes = "application/json", produces ="application/json")
    public ResponseEntity<CarBrand> addNewCarBrand(@RequestBody CarBrand car) throws CarException {
        carBrandCore.AddNewCarBrand(car);
        return new ResponseEntity<>(car,HttpStatus.OK);
    }

}
