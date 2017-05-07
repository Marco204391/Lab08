package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		System.out.println("Lista di tutte le nazioni:");
		List<Country> countries = dao.loadAllCountries();

		System.out.println("Lista di tutti i confini");
		for(Border c : dao.getCountryPairs(2000)){
			System.out.format(" %s %s \n", c.getC1(), c.getC2());
		}
	}
}
