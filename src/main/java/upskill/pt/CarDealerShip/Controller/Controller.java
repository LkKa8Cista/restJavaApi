package upskill.pt.CarDealerShip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.CarCore;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Car;


import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
//@RequestMapping("/api/")
public class Controller {

    @Autowired
    CarCore carCore;



//    public Controller(CarData data){
//        this.carCore = new CarCore(data);
//    }


    @GetMapping(value = "/cars/{page}", produces = "application/json")
    public ResponseEntity<List<Car>> get100Cars(@PathVariable("page") int page){
        List<Car> cars = carCore.Get100Cars(page);
        return new ResponseEntity<>(cars,HttpStatus.OK);
    }

    @GetMapping(value= "/car/{vin}", produces = "application/json")
    public ResponseEntity<Car> getCarByVin(@PathVariable("vin") int vin) throws CarException {
        try {
            Car car = carCore.GetCarByVin(vin);
            return new ResponseEntity<>(car,HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "create", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Car> addNewCar(@RequestBody Car car) throws CarException {
        carCore.AddNewCar(car);
        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    //////////////////////////////////////////////////////////////////////////////


}
