package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche {
		private Etal[] etals;
		
		private Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			
			for (int i = 0; i < nbEtal; i++) {
				etals[i] = new Etal();
			}
		}
		
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		
		private int trouverEtalLibre() {
			int indiceEtal = -1;
			
			for (int i = 0; i < etals.length && indiceEtal == -1; i++) {
				if (! etals[i].isEtalOccupe()) {
					indiceEtal = i;
				}
			}
			
			return indiceEtal;
		}
		
		
		private Etal[] trouverEtals(String produit) {
			int nbEtalsUtilise = 0;
			
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					nbEtalsUtilise++;
				}
			}
			
			Etal[] etalsUtilises = new Etal[nbEtalsUtilise];
			
			for (int i = 0, j = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					etalsUtilises[j] = etals[i];
					j++;
				}
			}
			
			return etalsUtilises;
		}
		
		
		private Etal trouverVendeur(Gaulois gaulois) {
			Etal devanture = null;
			
			for (int i = 0, j = 0; i < etals.length && j == 0;  i++ ) {
				if (etals[i].getVendeur() == gaulois) {
					devanture = etals[i];
					j = 1;
				}
			}
			
			return devanture;
		}
		
		
		private String afficherMarcher() {
			int nbEtalVide = 0;
			StringBuilder leMarche = new StringBuilder();
			
			for (int i = 0; i < etals.length; i++) {
				if(etals[i].isEtalOccupe()) {
					leMarche.append(etals[i].afficherEtal());
				} else {
					nbEtalVide++;
				}
			}
			
			if (nbEtalVide > 0) {
				leMarche.append("Il reste " + nbEtalVide + " �tals non utilis�s sur le march�.");
			}
			
			return leMarche.toString();
		}
	}
	
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder installationVendeur = new StringBuilder();
		installationVendeur.append(vendeur.getNom() + " cherche un endroit pour vendre " 
		+ nbProduit + " " + produit + "\n");
		
		int indiceEtal = marche.trouverEtalLibre();
		
		if(indiceEtal == -1) {
			installationVendeur.append("Il n'y a malheureusement plus de place pour " + vendeur.getNom());
		} else {
			marche.utiliserEtal(indiceEtal, vendeur, produit, nbProduit);
			installationVendeur.append("Le vendeur " + vendeur.getNom() + " vend des " + produit +
					" à l'étal n° " + indiceEtal);
		}
		
		return installationVendeur.toString();
		
	}
	
	public String rechercherVendeursProduit(String produit) {
		
	}
}


















