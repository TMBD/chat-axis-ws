package main;

public class Vente {
	
	String codeClient;
	String codeProduit;
	String dateVente;
	String prixUnitaire;
	String quantite;
	
	public Vente(String codeClient, String codeProduit, String dateVente, String prixUnitaire, String quantite) {
		super();
		this.codeClient = codeClient;
		this.codeProduit = codeProduit;
		this.dateVente = dateVente;
		this.prixUnitaire = prixUnitaire;
		this.quantite = quantite;
	}
	
	public Vente() {
		super();
	}

	public String getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getDateVente() {
		return dateVente;
	}

	public void setDateVente(String dateVente) {
		this.dateVente = dateVente;
	}

	public String getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(String prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public String getQuantite() {
		return quantite;
	}

	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeClient == null) ? 0 : codeClient.hashCode());
		result = prime * result + ((codeProduit == null) ? 0 : codeProduit.hashCode());
		result = prime * result + ((dateVente == null) ? 0 : dateVente.hashCode());
		result = prime * result + ((prixUnitaire == null) ? 0 : prixUnitaire.hashCode());
		result = prime * result + ((quantite == null) ? 0 : quantite.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vente other = (Vente) obj;
		if (codeClient == null) {
			if (other.codeClient != null)
				return false;
		} else if (!codeClient.equals(other.codeClient))
			return false;
		if (codeProduit == null) {
			if (other.codeProduit != null)
				return false;
		} else if (!codeProduit.equals(other.codeProduit))
			return false;
		if (dateVente == null) {
			if (other.dateVente != null)
				return false;
		} else if (!dateVente.equals(other.dateVente))
			return false;
		if (prixUnitaire == null) {
			if (other.prixUnitaire != null)
				return false;
		} else if (!prixUnitaire.equals(other.prixUnitaire))
			return false;
		if (quantite == null) {
			if (other.quantite != null)
				return false;
		} else if (!quantite.equals(other.quantite))
			return false;
		return true;
	}
	
	

}
