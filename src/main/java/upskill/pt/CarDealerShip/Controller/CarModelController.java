package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.CarModelCore;
import upskill.pt.CarDealerShip.DTOs.CarModelDTO;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarModel;

import java.util.List;
import java.util.Optional;

@RestController
public class CarModelController {
    @Autowired
    CarModelCore carModelCore;



//    public Controller(CarData data){
//        this.carCore = new CarCore(data);
//    }


    @GetMapping(value = "/models", produces = "application/json")
    public ResponseEntity<Page<CarModelDTO>> getModels(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        int _page=page.orElse(0);
        int _size=size.orElse(10);

        Page<CarModelDTO> models = this.carModelCore.GetCarModels(_page,_size);

        return new ResponseEntity<>(models, HttpStatus.OK);
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
