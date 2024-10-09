package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Village village = new Village("le village des irréductibles", 10, 5);
		Gaulois obelix = new Gaulois("Obélix", 25);
		village.ajouterHabitant(obelix);
		Etal etal = new Etal();
		
		village.afficherVillageois();
		etal.acheterProduit(-10, obelix);
		etal.occuperEtal(obelix, "tomates", 20);
		etal.libererEtal();
		etal.acheterProduit(10, null);
		etal.acheterProduit(-10, obelix);
		System.out.println("Fin du test");
		}

}
