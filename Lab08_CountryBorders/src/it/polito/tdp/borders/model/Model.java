package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;
import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private UndirectedGraph<Country, Border> graph;
	private BordersDAO dao = new BordersDAO();
	private	Map <Integer, Country>mappaTotCountry ;
	private	ArrayList<Border>listaBordersAnno;
	
	
	public Model() {
		mappaTotCountry=  new HashMap<Integer,Country>(dao.loadAllCountries());
	}

	
	public String calcolaConfini(int anno) {	
		listaBordersAnno=new ArrayList<Border>(dao.getCountryPairs(anno));
		createGraph(listaBordersAnno);

		String result="";
		for(Country c: graph.vertexSet()){
			result+= c+"  numero di confini:"+graph.degreeOf(c)+"\n";
		}
		result+= "\n\nComponenti connesse : "+ numeroComponentiConnesse();
		result+= "\n\nNumero dei paesi nella massima componente: "+ numeroMassimoConnessioni();
		return result;

	}
	private void createGraph(ArrayList<Border> listaBordersAnno){
		 graph=new SimpleGraph<Country,Border>(Border.class);
		
		 
		 //creo vertici

		 for(int i: mappaTotCountry.keySet()){
			 Country c= mappaTotCountry.get(i);
		   if(!graph.containsVertex(c)){
			   graph.addVertex(c);
		   }

		 }
		 for(Border b : listaBordersAnno){
				Country c1= mappaTotCountry.get(b.getState1no());
			    Country c2= mappaTotCountry.get(b.getState2no());	
					 if(!graph.containsEdge(b)){	 
					     graph.addEdge(c1,c2,b);
					 }
				 } 
			}

			private int numeroMassimoConnessioni() {
				int result=0;
				ConnectivityInspector<Country, Border> ci = new ConnectivityInspector<Country, Border>(graph);
				List <Set<Country>>list= new ArrayList<Set<Country>>(ci.connectedSets());
				for(Set<Country> set : list){
					if(set.size()>result){
					result=set.size();}
				}

				return result;

			}

			private int numeroComponentiConnesse() {
				ConnectivityInspector<Country, Border> ci = new ConnectivityInspector<Country, Border>(graph);
				List <Set<Country>>list= new ArrayList<Set<Country>>(ci.connectedSets());
				return list.size();
			}

}
