package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.SellerCore;
import upskill.pt.CarDealerShip.DTOs.SellerDTO;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Seller;

import java.util.List;
import java.util.Optional;

@RestController
public class SellerController {
    @Autowired
    SellerCore sellerCore;



//    public Controller(CarData data){
//        this.carCore = new CarCore(data);
//    }


    @GetMapping(value = "/sellers", produces = "application/json")
    public ResponseEntity<Page<SellerDTO>> getSellers(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        int _page=page.orElse(0);
        int _size=size.orElse(10);

        Page<SellerDTO> sellers = this.sellerCore.GetSellers(_page,_size);

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

    @PostMapping(value = "/seller/new", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Seller> addNewCarSeller(@RequestBody Seller seller) throws CarException {
        sellerCore.AddNewCarSeller(seller);
        return new ResponseEntity<>(seller,HttpStatus.OK);
    }
}
