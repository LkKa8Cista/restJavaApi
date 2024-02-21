package upskill.pt.CarDealerShip.Core;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import upskill.pt.CarDealerShip.DTOs.CarModelDTO;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.Data.CarModelData;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.CarModel;

import java.util.List;

@Service
@NoArgsConstructor
public class CarModelCore {
    @Autowired
    CarModelData data;

    public CarModelCore(CarModelData data){
        this.data = data;
    }

    public Page<CarModelDTO> GetCarModels(int page, int size) {
        return this.data.findAll(PageRequest.of(page,size)).map(CarModelDTO::toCarModelDTO);
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
