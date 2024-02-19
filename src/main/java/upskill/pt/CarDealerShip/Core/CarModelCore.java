package upskill.pt.CarDealerShip.Core;

import org.springframework.beans.factory.annotation.Autowired;
import upskill.pt.CarDealerShip.Data.CarModelData;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarModel;

import java.util.List;

public class CarModelCore {
    @Autowired
    CarModelData data;

    public CarModelCore(CarModelData data){
        this.data = data;
    }

    public List<CarModel> Get100CarModels(int page) {
        int[] a = new int [2];
        if (page < 2){
            a[1] = 99;
        } else {
            a[0] = page * 100 - 100;
            a[1] = page * 100 -1;
        }
        return data.findAll().subList(a[0], Math.min(a[1] + 1, data.findAll().size()));
    }

    public CarModel AddNewCarModel(CarModel model) throws CarException {
        int temp = model.getId();
        if (data.existsById(temp)){
            throw new CarException("CarBrand", "AddNewCarModel", "Id already existed");
        }

        data.saveAndFlush(model);
        return model;
    }

    public CarModel GetCarModelById(int id) throws CarException {
        if(data.existsById(id)){
            return data.findById(id).get();
        } else {
            throw new CarException("Car", "GetCarModelById", "Id did not exist") ;
        }
    }
}
