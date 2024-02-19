package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.ColorCore;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Color;

import java.util.List;

//@RestController
public class ColorController {
    @Autowired
    ColorCore colorCore;

    @GetMapping(value = "/colors/{page}", produces = "application/json")
    public ResponseEntity<List<Color>> get100Colors(@PathVariable("page") int page){
        List<Color> colors = colorCore.Get100Colors(page);
        return new ResponseEntity<>(colors, HttpStatus.OK);
    }

    @GetMapping(value= "/color/{id}", produces = "application/json")
    public ResponseEntity<Color> getColorById(@PathVariable("id") int id) throws CarException {
        try {
            Color color = colorCore.GetColorById(id);
            return new ResponseEntity<>(color,HttpStatus.OK);
        } catch (CarException exception) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/color/new", consumes = "application/json", produces ="application/json")
    public ResponseEntity<Color> addNewColor(@RequestBody Color color) throws CarException {
        colorCore.AddNewColor(color);
        return new ResponseEntity<>(color,HttpStatus.OK);
    }
}
