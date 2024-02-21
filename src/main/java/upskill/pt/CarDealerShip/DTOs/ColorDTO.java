package upskill.pt.CarDealerShip.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import upskill.pt.CarDealerShip.Models.Color;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColorDTO extends RepresentationModel<ColorDTO> {
    public int id;
    public String commonName;
    public String hexCode;
    public static ColorDTO toColorDTO(Color color){
        return new ColorDTO(color.getId(), color.getCommonName(), color.getHexCode());
    }
}
