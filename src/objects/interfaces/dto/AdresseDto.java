package objects.interfaces.dto;

public interface AdresseDto {
  String getRue();

  void setRue(String rue);

  String getVille();

  void setVille(String ville);

  String getBoite();

  void setBoite(String boite);

  String getCp();

  void setCp(String cp);

  String getPays();

  void setPays(String pays);

  int getNumero();

  void setNumero(int numero);

  String getRegion();

  void setRegion(String region);

  public int getVersion();

  public void setVersion(int version);
}
