package enumere;

public enum TypeMobilite {
  ERASMUS("Erasmus+"), FAME("Fame"), ERABEL("Erabel");
  String mobilite;

  private TypeMobilite(String mobilite) {
    this.mobilite = mobilite;
  }

  public String getMobilite() {
    return this.mobilite;
  }
}
