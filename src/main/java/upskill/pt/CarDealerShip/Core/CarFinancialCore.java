package upskill.pt.CarDealerShip.Core;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import upskill.pt.CarDealerShip.DTOs.CarDTO;
import upskill.pt.CarDealerShip.Data.CarData;

import java.util.HashMap;
import java.util.Map;

@Service
@NoArgsConstructor
public class CarFinancialCore {

    @Autowired
    CarData data;

    public CarFinancialCore(CarData data) {
        this.data = data;
    }

    public Map<String, Double> GetResults(){
        long size = data.count();
        Map<String, Double> results = new HashMap<>();

        results.put("In House Total", data.getSumOfNotSold());
        results.put("Sold Total", data.getSumOfSold());
        results.put("Difference Total", (data.getSumOfPricePVR()- data.getSumOfSold()));

        return results;
    }
}
