package enumere;

public enum Civilite {
  M("Monsieur"), MLLE("Mademoiselle"), MME("Madame");
  String civilite;

  private Civilite(String civilite) {
    this.civilite = civilite;
  }

  public String getCivilite() {
    return civilite;
  }


}
