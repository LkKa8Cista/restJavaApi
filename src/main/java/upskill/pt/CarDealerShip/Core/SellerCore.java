package upskill.pt.CarDealerShip.Core;

import org.springframework.beans.factory.annotation.Autowired;
import upskill.pt.CarDealerShip.Data.SellerData;
import upskill.pt.CarDealerShip.Exceptions.CarException;
import upskill.pt.CarDealerShip.Models.Seller;

import java.util.List;

public class SellerCore {
    @Autowired
    SellerData data;

    public SellerCore(SellerData data){
        this.data = data;
    }

    public List<Seller> Get100Sellers(int page) {
        int[] a = new int [2];
        if (page < 2){
            a[1] = 99;
        } else {
            a[0] = page * 100 - 100;
            a[1] = page * 100 -1;
        }
        return data.findAll().subList(a[0], Math.min(a[1] + 1, data.findAll().size()));
    }

    public Seller AddNewCarSeller(Seller seller) throws CarException {
        int temp = seller.getId();
        if (data.existsById(temp)){
            throw new CarException("Seller", "AddNewCarSeller", "Id already existed");
        }

        data.saveAndFlush(seller);
        return seller;
    }

    public Seller GetCarSellerById(int id) throws CarException {
        if(data.existsById(id)){
            return data.findById(id).get();
        } else {
            throw new CarException("Seller", "GetCarSellerById", "Id did not exist") ;
        }
    }
}
