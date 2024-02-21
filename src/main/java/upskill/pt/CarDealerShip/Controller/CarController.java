package upskill.pt.CarDealerShip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.CarCore;
import upskill.pt.CarDealerShip.DTOs.CarDTO;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Car;


import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
//@RequestMapping("/api/")
public class CarController {

    @Autowired
    CarCore carCore;



//    public Controller(CarData data){
//        this.carCore = new CarCore(data);
//    }


    @GetMapping(value = "/colors", produces = "application/json")
    public ResponseEntity<Page<CarDTO>> getCars(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        int _page=page.orElse(0);
        int _size=size.orElse(10);

        Page<CarDTO> colors = this.carCore.GetCars(_page,_size);

        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @GetMapping(value = "/cars/sold", produces = "application/json")
    public ResponseEntity<List<Car>> getSoldCars(){
        List<Car> cars = carCore.InStockCars();
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

    @GetMapping(value= "/cars/sold/{id}", produces = "application/json")
    public ResponseEntity<List<Car>> getCarsBySeller(@PathVariable("id") int id) {
        List<Car> cars = carCore.CarsBySeller(id);
        return cars != null ? new ResponseEntity<>(cars,HttpStatus.OK) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "create", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Car> addNewCar(@RequestBody Car car) throws CarException {
        carCore.AddNewCar(car);
        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    @PutMapping(value= "/car/sold/{vin}", produces = "application/json")
    public ResponseEntity<Car> setCarToSoldById(@PathVariable("vin") int vin) throws CarException {
        try {
            Car car = carCore.SetCarAsSold(vin);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }


}
