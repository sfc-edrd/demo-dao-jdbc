package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerdao = DaoFactory.CreateSellerDao();
		Seller seller = sellerdao.findById(3);
		
		//Seller seller = new Seller(1, "Bob", "bob@gmail.com", new Date(), 3000.0, department);
		
		System.out.println(seller);

	}

}
