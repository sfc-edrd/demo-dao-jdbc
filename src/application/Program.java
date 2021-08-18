package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerdao = DaoFactory.CreateSellerDao();
		
		System.out.println("\n=========== FindByID ===========");
		Seller seller = sellerdao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=========== FindByDepartment ===========");
		Department department = new Department(2,null);
		List<Seller> sellersById = new ArrayList<>(sellerdao.findByDepartment(department));
		sellersById.stream().forEach(System.out::println);
		
		System.out.println("\n=========== FindByAll ===========");
		List<Seller> sellersByAll = new ArrayList<>();
		sellersByAll = sellerdao.findAll();
		sellersByAll.stream().forEach(System.out::println);
	}

}
