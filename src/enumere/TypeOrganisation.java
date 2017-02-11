package enumere;

public enum TypeOrganisation {

  TPE("Très petite entreprise"), PME("Petite-moyenne entreprise"), ETI(
      "Entreprise de taille intermédaire"), TGE("Très grande entreprise"), ECOLE("Ecole");
  String nom;

  private TypeOrganisation(String nom) {
    this.nom = nom;
  }

  public String getNom() {
    return nom;
  }

}
