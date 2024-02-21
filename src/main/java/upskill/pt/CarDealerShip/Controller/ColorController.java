package upskill.pt.CarDealerShip.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upskill.pt.CarDealerShip.Core.ColorCore;
import upskill.pt.CarDealerShip.DTOs.ColorDTO;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Color;

import java.util.List;
import java.util.Optional;

@RestController
public class ColorController {
    @Autowired
    ColorCore colorCore;

    @GetMapping(value = "/colors", produces = "application/json")
    public ResponseEntity<Page<ColorDTO>> getColors(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        int _page=page.orElse(0);
        int _size=size.orElse(10);

        Page<ColorDTO> colors = this.colorCore.GetColors(_page,_size);

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
