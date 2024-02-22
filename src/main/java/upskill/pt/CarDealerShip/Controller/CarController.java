package upskill.pt.CarDealerShip.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.CarCore;
import upskill.pt.CarDealerShip.DTOs.CarDTO;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Car;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
//@RequestMapping("/api/")
public class CarController {

    @Autowired
    CarCore carCore;



    @GetMapping(value = "/cars", produces = "application/json")
    public ResponseEntity<CollectionModel<CarDTO>> getCars(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        int _page=page.orElse(0);
        int _size=size.orElse(10);


        Page<CarDTO> cars = this.carCore.GetCars(_page,_size);

        cars = cars.map((CarDTO d)-> {
            try {
                return d.add(linkTo(methodOn(CarController.class).getCarByVin(d.getVin())).withSelfRel());
            } catch (CarException e) {
                throw new RuntimeException(e);
            }
        });
        Link link = linkTo(methodOn(CarController.class).getCarsInStock(Optional.of(_page),Optional.of(_size),Optional.of("vin"))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if(!cars.isLast()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page + 1), Optional.of(_size))).withRel("next");
            links.add(_link);
        }
        if(!cars.isFirst()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page - 1), Optional.of(_size))).withRel("previous");
            links.add(_link);
        }

        return new ResponseEntity<>(CollectionModel.of(cars, links), HttpStatus.OK);
    }

    @GetMapping(value = "/cars/stock", produces = "application/json")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsInStock(@RequestParam Optional<Integer> page,
                                                       @RequestParam Optional<Integer> size,
                                                       @RequestParam Optional<String> sort){
        int _page=page.orElse(0);
        int _size=size.orElse(10);
        String _sort=sort.orElse("vin");

        Page<CarDTO> inStockCars = this.carCore.getInStockCars(_page,_size,_sort);

        inStockCars = inStockCars.map((CarDTO d)-> {
            try {
                return d.add(linkTo(methodOn(CarController.class).getCarByVin(d.getVin())).withSelfRel());
            } catch (CarException e) {
                throw new RuntimeException(e);
            }
        });
        Link link = linkTo(methodOn(CarController.class).getCarsInStock(Optional.of(_page),Optional.of(_size),Optional.of("vin"))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if(!inStockCars.isLast()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page + 1), Optional.of(_size))).withRel("next");
            links.add(_link);
        }
        if(!inStockCars.isFirst()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page - 1), Optional.of(_size))).withRel("previous");
            links.add(_link);
        }


        return new ResponseEntity<>(CollectionModel.of(inStockCars,links), HttpStatus.OK);
    }

//    @GetMapping(value = "/cars/sold", produces = "application/json")
//    public ResponseEntity<List<Car>> getSoldCars(){
//        List<Car> cars = carCore.InStockCars();
//        return new ResponseEntity<>(cars,HttpStatus.OK);
//    }

    @GetMapping(value= "/car/{vin}", produces = "application/json")
    public ResponseEntity<CarDTO> getCarByVin(@PathVariable("vin") int vin) throws CarException {
        try {
            CarDTO car = carCore.GetCarByVin(vin);
            return new ResponseEntity<>(car,HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value= "/cars/sold/{id}", produces = "application/json")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsBySeller(@PathVariable("id") int id,
                                                     @RequestParam Optional<Integer> page,
                                                     @RequestParam Optional<Integer> size,
                                                     @RequestParam Optional<String> sort) {
        int _page=page.orElse(0);
        int _size=size.orElse(10);
        String _sort=sort.orElse("vin");

        Page<CarDTO> carsBySeller = this.carCore.CarsBySeller(_page,_size,_sort, id);

        carsBySeller = carsBySeller.map((CarDTO d)-> {
            try {
                return d.add(linkTo(methodOn(CarController.class).getCarByVin(d.getVin())).withSelfRel());
            } catch (CarException e) {
                throw new RuntimeException(e);
            }
        });
        Link link = linkTo(methodOn(CarController.class).getCarsInStock(Optional.of(_page),Optional.of(_size),Optional.of("vin"))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if(!carsBySeller.isLast()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page + 1), Optional.of(_size))).withRel("next");
            links.add(_link);
        }
        if(!carsBySeller.isFirst()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page - 1), Optional.of(_size))).withRel("previous");
            links.add(_link);
        }


        return new ResponseEntity<>(CollectionModel.of(carsBySeller, links),HttpStatus.OK);
    }

    @GetMapping(value= "/cars/brand/{name}", produces = "application/json")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsByBrand(@PathVariable("name") String name,
                                                                   @RequestParam Optional<Integer> page,
                                                                   @RequestParam Optional<Integer> size,
                                                                   @RequestParam Optional<String> sort) {
        int _page=page.orElse(0);
        int _size=size.orElse(10);
        String _sort=sort.orElse("vin");

        Page<CarDTO> carsByBrand = this.carCore.CarsByBrand(_page,_size,_sort, name);

        carsByBrand = carsByBrand.map((CarDTO d)-> {
            try {
                return d.add(linkTo(methodOn(CarController.class).getCarByVin(d.getVin())).withSelfRel());
            } catch (CarException e) {
                throw new RuntimeException(e);
            }
        });
        Link link = linkTo(methodOn(CarController.class).getCarsInStock(Optional.of(_page),Optional.of(_size),Optional.of("vin"))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if(!carsByBrand.isLast()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page + 1), Optional.of(_size))).withRel("next");
            links.add(_link);
        }
        if(!carsByBrand.isFirst()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page - 1), Optional.of(_size))).withRel("previous");
            links.add(_link);
        }


        return new ResponseEntity<>(CollectionModel.of(carsByBrand, links),HttpStatus.OK);
    }

    @GetMapping(value= "/cars/type/{name}", produces = "application/json")
    public ResponseEntity<CollectionModel<CarDTO>> getCarsByType(@PathVariable("name") String name,
                                                                  @RequestParam Optional<Integer> page,
                                                                  @RequestParam Optional<Integer> size,
                                                                  @RequestParam Optional<String> sort) {
        int _page=page.orElse(0);
        int _size=size.orElse(10);
        String _sort=sort.orElse("vin");

        Page<CarDTO> carsByType = this.carCore.CarsByType(_page,_size,_sort, name);

        if (carsByType == null){
            return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        carsByType = carsByType.map((CarDTO d)-> {
            try {
                return d.add(linkTo(methodOn(CarController.class).getCarByVin(d.getVin())).withSelfRel());
            } catch (CarException e) {
                throw new RuntimeException(e);
            }
        });
        Link link = linkTo(methodOn(CarController.class).getCarsInStock(Optional.of(_page),Optional.of(_size),Optional.of("vin"))).withSelfRel();
        List<Link> links = new ArrayList<>();
        links.add(link);
        if(!carsByType.isLast()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page + 1), Optional.of(_size))).withRel("next");
            links.add(_link);
        }
        if(!carsByType.isFirst()) {
            Link _link = linkTo(methodOn(CarController.class).getCars(Optional.of(_page - 1), Optional.of(_size))).withRel("previous");
            links.add(_link);
        }


        return new ResponseEntity<>(CollectionModel.of(carsByType, links),HttpStatus.OK);
    }

    @PostMapping(value = "create", consumes = "application/json", produces ="application/json")
    public ResponseEntity<CarDTO> addNewCar(@RequestBody Car car) throws CarException {
        return new ResponseEntity<>(carCore.AddNewCar(car),HttpStatus.OK);
    }



    @PutMapping(value= "/car/sold/{vin}", produces = "application/json")
    public ResponseEntity<CarDTO> setCarToSoldById(@PathVariable("vin") int vin) throws CarException {
        try {
            CarDTO car = carCore.SetCarAsSold(vin);
            return new ResponseEntity<>(car, HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value= "/car/update/{vin}", produces = "application/json")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("vin") int vin,
                                            @RequestBody CarDTO carDTO) throws CarException {
        if (carDTO.getVin() != vin){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(carCore.UpdateCar(carDTO), HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value= "/car/{vin}/status/{type}", produces = "application/json")
    public ResponseEntity<CarDTO> setCarStatus(@PathVariable("vin") int vin, @PathVariable("type") String type) throws CarException {

        String temp = type.toLowerCase().trim();

        if (temp.equals("b") || temp.equals("i") || temp.equals("p") || temp.equals("s") ) {
            try {
                CarDTO car = carCore.SetCarStatus(vin, type);
                return new ResponseEntity<>(car, HttpStatus.OK);
            } catch (CarException exception) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
