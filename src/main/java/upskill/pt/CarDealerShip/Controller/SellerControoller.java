package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.SellerCore;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarModel;
import upskill.pt.CarDealerShip.Models.Seller;

import java.util.List;

//@RestController
public class SellerControoller{
    @Autowired
    SellerCore sellerCore;



//    public Controller(CarData data){
//        this.carCore = new CarCore(data);
//    }


    @GetMapping(value = "/sellers/{page}", produces = "application/json")
    public ResponseEntity<List<Seller>> get100CarsModels(@PathVariable("page") int page){
        List<Seller> sellers = sellerCore.Get100Sellers(page);
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @GetMapping(value= "/seller/{id}", produces = "application/json")
    public ResponseEntity<Seller> getCarSellerById(@PathVariable("id") int id) throws CarException {
        try {
            Seller seller = sellerCore.GetCarSellerById(id);
            return new ResponseEntity<>(seller,HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/model/new", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Seller> addNewCarSeller(@RequestBody Seller seller) throws CarException {
        sellerCore.AddNewCarSeller(seller);
        return new ResponseEntity<>(seller,HttpStatus.OK);
    }
}
