package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public HashMap<Integer,Country> loadAllCountries() {
      HashMap<Integer,Country>mappaTotCountry= new HashMap<Integer,Country>();

		List<Country> vertici = new ArrayList<Country>();

		String sql = "SELECT ccode,StateAbb,StateNme " + "FROM country " + "ORDER BY StateAbb ";

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			mappaTotCountry.put(rs.getInt("ccode"),new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme")));

			}
			conn.close();
			return mappaTotCountry;
			//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database Error -- loadAllCountries");
			throw new RuntimeException("Database Error");
		}
	}
	

	public List<Border> getCountryPairs(int anno) {

		List<Border> archi = new ArrayList<Border>();
		final String sql = "SELECT * " + "FROM contiguity " + "WHERE year<=? AND conttype=1 ";
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {



				archi.add(new Border(rs.getInt("state1no"), rs.getInt("state2no"), rs.getString("state1ab"), rs.getString("state2ab"), rs.getInt("year")));
//				Country tmp1 = new Country(rs.getString("State1ab"));
//				Country tmp2 = new Country(rs.getString("State2ab"));
//				
//				Border c= new Border(tmp1,tmp2);
//				//String c1 =rs.getString("State1ab");
//				//String c2 =rs.getString("State2ab");
//				archi.add(c);
				
			}
			
			conn.close();
			return archi;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
			
	}
}
