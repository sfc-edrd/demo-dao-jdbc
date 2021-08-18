package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	private Department department = null;
	private Seller seller = null;
	private List<Seller> sellers = new ArrayList<>();
	private String queryBody = null;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	private void runQuery(String queryBody, int i, Integer id) throws SQLException {
		conn = DB.getConnection();
		st = conn.prepareStatement(queryBody);

		st.setInt(i, id);
		rs = st.executeQuery();
	}

	private void instantiateDepartment(ResultSet rs) throws SQLException {
		
		if (department == null) 
			department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
	}
	
	private void instantiateSeller(ResultSet rs, Department department) throws SQLException {
		seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthdate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartmentId(department);
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		queryBody =  
				  "SELECT se.*, dep.Name as DepName "
				+ "FROM coursejdbc.seller as se " 
				+ "INNER JOIN coursejdbc.department as dep "
				+ "ON se.DepartmentId = dep.Id " 
				+ "WHERE se.id = ?";
				
		try {
			
			runQuery(queryBody, 1, id);
			
			if (rs.next()) {

				// Instantiate the Department
				instantiateDepartment(rs);
				
				// Instantiate the Seller
				instantiateSeller(rs, department);
				return seller;
			}
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		queryBody =  
				  "SELECT se.*, dep.Name as DepName "
				+ "FROM coursejdbc.seller as se " 
				+ "INNER JOIN coursejdbc.department as dep "
				+ "ON se.DepartmentId = dep.Id " 
				+ "WHERE dep.id = ?";
				
		try {
			
			runQuery(queryBody, 1, department.getId());
			
			while (rs.next()) {

				// Instantiate the Department
				this.department = department;
				instantiateDepartment(rs);
				
				// Instantiate the Seller
				instantiateSeller(rs, department);
				sellers.add(seller);
			}
			return sellers;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
