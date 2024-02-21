package upskill.pt.CarDealerShip.Core;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import upskill.pt.CarDealerShip.DTOs.CarBrandDTO;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.Data.CarBrandData;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarBrand;

import java.util.List;

@Service
@NoArgsConstructor
public class CarBrandCore {
    @Autowired
    CarBrandData data;

    public CarBrandCore(CarBrandData data){
        this.data = data;
    }

    public Page<CarBrandDTO> GetCarBrands(int page, int size) {
        return this.data.findAll(PageRequest.of(page,size)).map(CarBrandDTO::toCarBrandDTO);
    }

    public CarBrand AddNewCarBrand(CarBrand brand) throws CarException {
        int temp = brand.getId();
        if (data.existsById(temp)){
            throw new CarException("CarBrand", "AddNewCarBrand", "Id already existed");
        }

        data.saveAndFlush(brand);
        return brand;
    }

    public CarBrand GetCarBrandById(int id) throws CarException {
        if(data.existsById(id)){
            return data.findById(id).get();
        } else {
            throw new CarException("Car", "GetCarBrandById", "Id did not exist") ;
        }
    }
}
