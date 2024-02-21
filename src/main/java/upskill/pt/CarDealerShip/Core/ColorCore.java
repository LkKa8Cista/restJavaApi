package upskill.pt.CarDealerShip.Core;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.Data.ColorData;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Color;

import java.util.List;

@Service
@NoArgsConstructor
public class ColorCore {
    @Autowired
    ColorData data;

    public ColorCore(ColorData data){
        this.data = data;
    }

    public Page<ColorDTO> GetColors(int page, int size) {
        return this.data.findAll(PageRequest.of(page,size)).map(ColorDTO::toColorDTO);
    }

    public Color AddNewColor(Color color) throws CarException {
        int temp = color.getId();
        if (data.existsById(temp)){
            throw new CarException("Color", "AddNewColor", "Id already existed");
        }

        data.saveAndFlush(color);
        return color;
    }

    public Color GetColorById(int id) throws CarException {
        if(data.existsById(id)){
            return data.findById(id).get();
        } else {
            throw new CarException("Color", "GetColorById", "Id did not exist") ;
        }
    }
}
