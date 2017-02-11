package enumere;

public enum Etat {
  EN_PREPARATION("En pr"
      + "éparation"), A_PAYER("A payer"), SOLDE("Solde à payer"), PAYEE("payée"), ANNULE(
      "Annulé");
  private String etat;

  private Etat(String etat) {
    this.etat = etat;
  }

  public String getEtat() {
    return this.etat;
  }
}
