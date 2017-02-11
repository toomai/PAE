package objects.model;

import objects.interfaces.dto.AdresseDto;


public class AdresseImpl implements AdresseDto {
  private String rue;
  private String ville;
  private String boite;
  private String cp;
  private String pays;
  private int numero;
  private String region;
  private int version;

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getRue() {
    return rue;
  }

  public void setRue(String rue) {
    this.rue = rue;
  }

  public String getVille() {
    return ville;
  }

  public void setVille(String ville) {
    this.ville = ville;
  }

  public String getBoite() {
    return boite;
  }

  public void setBoite(String boite) {
    this.boite = boite;
  }

  public String getCp() {
    return cp;
  }

  public void setCp(String cp) {
    this.cp = cp;
  }

  public String getPays() {
    return pays;
  }

  public void setPays(String pays) {
    this.pays = pays;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getRegion() {
    return region;
  }

}
