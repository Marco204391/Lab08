package it.polito.tdp.borders.model;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private UndirectedGraph<Country, DefaultEdge> graph;
	private BordersDAO dao = new BordersDAO();
	
	public Model() {
		this.graph= new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
	}

	public void createGraph(int anno){
		
		//creo vertici
		Graphs.addAllVertices(graph, dao.loadAllCountries());
//		for(Country vertex : graph.vertexSet()){
//			System.out.format("vertex : %s \n", vertex.toString());
//		}
		//creo archi
		for(Border cp:  dao.getCountryPairs(anno))
		{
//			Border pc = new Border(cp.getC1(), cp.getC2());
			graph.addEdge(cp.getC1(), cp.getC2());
		}
	
		
	}
}
