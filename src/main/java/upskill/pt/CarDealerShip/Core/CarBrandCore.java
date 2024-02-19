package upskill.pt.CarDealerShip.Core;

import org.springframework.beans.factory.annotation.Autowired;
import upskill.pt.CarDealerShip.Data.CarBrandData;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarBrand;

import java.util.List;

public class CarBrandCore {
    @Autowired
    CarBrandData data;

    public CarBrandCore(CarBrandData data){
        this.data = data;
    }

    public List<CarBrand> Get100CarBrands(int page) {
        int[] a = new int [2];
        if (page < 2){
            a[1] = 99;
        } else {
            a[0] = page * 100 - 100;
            a[1] = page * 100 -1;
        }
        return data.findAll().subList(a[0], Math.min(a[1] + 1, data.findAll().size()));
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
