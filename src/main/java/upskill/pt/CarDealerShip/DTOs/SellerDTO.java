package upskill.pt.CarDealerShip.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import upskill.pt.CarDealerShip.Models.Seller;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO extends RepresentationModel<SellerDTO> {
    public int id;
    public String name;
    public String email;
    public int phoneNumber;

    public static SellerDTO toSellerDTO(Seller seller){
        return new SellerDTO(seller.getId(), seller.getName(), seller.getEmail(), seller.getPhoneNumber());
    }
}
