package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.CarBrandCore;
import upskill.pt.CarDealerShip.DTOs.CarBrandDTO;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarBrand;

import java.util.List;
import java.util.Optional;

@RestController
public class CarBrandController {
    @Autowired
    CarBrandCore carBrandCore;



//    public Controller(CarData data){
//        this.carCore = new CarCore(data);
//    }


    @GetMapping(value = "/brands", produces = "application/json")
    public ResponseEntity<Page<CarBrandDTO>> getBrands(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        int _page=page.orElse(0);
        int _size=size.orElse(10);

        Page<CarBrandDTO> brands = this.carBrandCore.GetCarBrands(_page,_size);

        return new ResponseEntity<>(brands, HttpStatus.OK);
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
