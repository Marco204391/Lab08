package it.polito.tdp.borders.model;

public class Country {

	private String siglaStato;

	
	public Country(String siglaStato) {
		super();
		this.siglaStato = siglaStato;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return siglaStato;
	}
	
	
	
}
