package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ingredient;

public class IngredientDAO {
	private JDBCUtil jdbcUtil = null;
	
	public IngredientDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	//생성
	public int create(Ingredient ingre) throws SQLException {
		String sql = "INSERT INTO Ingredient VALUES (?, ?, ?)";		
		Object[] param = new Object[] {ingre.getRecipe_id(), 
				ingre.getIngredient_id(), ingre.getQuantity()};		
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	//업데이트
	public int update(Ingredient ingre) throws SQLException {
		String sql = "UPDATE Ingredient "
					+ "SET quantity=? "
					+ "WHERE ingredient_id=?, recipe_id=?";
		
		Object[] param = new Object[] {ingre.getQuantity(),ingre.getIngredient_id(),ingre.getRecipe_id()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	//삭제
	public int remove(int ingreId, int recipe_id) throws SQLException {
		String sql = "DELETE FROM Ingredient WHERE ingredient_id=?, recipe_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ingreId, recipe_id});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	//찾기
	public Ingredient findIngredient(int recipId) throws SQLException {
        String sql = "SELECT ii.icategory AS category, ii.iname AS name, i.quantity AS quant"
        			+ "FROM Ingredient i, ingredient_info ii "
        			+ "WHERE i.ingredient_id=ii.ingredient_id and i.recipe_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {recipId});	// JDBCUtil에 query문과 매개 변수 설정
		Ingredient ingre = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				ingre = new Ingredient(		// Ingredient 객체를 생성하여 커뮤니티 정보를 저장
					rs.getInt("ingredient_id"),
					recipId,
					rs.getString("quant"),
					rs.getString("category"),
					rs.getString("name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return ingre;
	}
	
	public String findIngredientname(int ingreId) throws SQLException {
        String sql = "SELECT iname "
        			+ "FROM ingredient_info "
        			+ "WHERE ingredient_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ingreId});	// JDBCUtil에 query문과 매개 변수 설정
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				String iname = rs.getString("iname");
				return iname;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return "없음";
	}
	
	//전체리스트
	public List<Ingredient> findIngredientList() throws SQLException {
        String sql = "SELECT i.recipe_id AS recipeid, i.ingredient_id AS ingredientid, "
        		+ "ii.icategory AS category, ii.iname AS name, i.quantity AS quantity "
        		   + "FROM Ingredient i, ingredient_info ii "
        		   + "WHERE i.ingredient_id = ii.ingredient_id "
        		   + "GROUP BY i.recipe_id, i.ingredient_id "
        		   + "ORDER BY ii.iname";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Ingredient> ingreList = new ArrayList<Ingredient>();	// Ingredient들의 리스트 생성
			while (rs.next()) {
				Ingredient ingre = new Ingredient(			// Ingredient 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("ingredientid"),
						rs.getInt("recipeid"),
						rs.getString("quantity"),
						rs.getString("category"),
						rs.getString("name"));
				ingreList.add(ingre);				// List에 Ingredient 객체 저장
			}		
			return ingreList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<Ingredient> findAllingredientList() throws SQLException {
        String sql = "SELECT icategory, ingredient_id, iname "
     		   + "FROM ingredient_info "
     		   + "GROUP BY icategory, ingredient_id, iname";
//     		   + "ORDER BY iname";

		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Ingredient> ingreList = new ArrayList<Ingredient>();	// Ingredient들의 리스트 생성
			while (rs.next()) {
				Ingredient ingre = new Ingredient(			// Ingredient 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("ingredient_id"),
						0,
						null,
						rs.getString("icategory"),
						rs.getString("iname"));
				ingreList.add(ingre);				// List에 Ingredient 객체 저장
			}		
			return ingreList;		
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;

	}
	
	public List<Ingredient> findCategoryingredientList(String category) throws SQLException {
        String sql = "SELECT ingredient_id, iname " + 
        		"FROM ingredient_info " + 
        		"WHERE icategory = ?" + 
        		"ORDER BY iname";
        		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {category});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Ingredient> ingreList = new ArrayList<Ingredient>();	// Ingredient들의 리스트 생성
			while (rs.next()) {
				Ingredient ingre = new Ingredient(			// Ingredient 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("ingredient_id"),
						0,
						null,
						null,
						rs.getString("iname"));
				ingreList.add(ingre);
				System.out.printf("%s\n",ingre);// List에 Ingredient 객체 저장
			}		
			return ingreList;		
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public int findIdByName(String iname) throws SQLException {
        String sql = "SELECT ingredient_id "
    			+ "FROM ingredient_info "
    			+ "WHERE iname=? ";              
	jdbcUtil.setSqlAndParameters(sql, new Object[] {iname});	// JDBCUtil에 query문과 매개 변수 설정

	try {
		ResultSet rs = jdbcUtil.executeQuery();		// query 실행
		if (rs.next()) {						// 학생 정보 발견
			return rs.getInt("ingredient_id");
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {
		jdbcUtil.close();		// resource 반환
	}
	return 0;
	}
}

