package upskill.pt.CarDealerShip.Core;

import org.springframework.beans.factory.annotation.Autowired;
import upskill.pt.CarDealerShip.Data.ColorData;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Color;

import java.util.List;

public class ColorCore {
    @Autowired
    ColorData data;

    public ColorCore(ColorData data){
        this.data = data;
    }

    public List<Color> Get100Colors(int page) {
        int[] a = new int [2];
        if (page < 2){
            a[1] = 99;
        } else {
            a[0] = page * 100 - 100;
            a[1] = page * 100 -1;
        }
        return data.findAll().subList(a[0], Math.min(a[1] + 1, data.findAll().size()));
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
