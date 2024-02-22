package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import upskill.pt.CarDealerShip.Core.CarFinancialCore;
import upskill.pt.CarDealerShip.DTOs.CarDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CarFinancialController {

    @Autowired
    CarFinancialCore carFinancialCore;

    @GetMapping(value= "/financial", produces = "application/json")
    public ResponseEntity<Map<String,Double>> getResults(){
        return new ResponseEntity<>(carFinancialCore.GetResults(), HttpStatus.OK);
    }
}
