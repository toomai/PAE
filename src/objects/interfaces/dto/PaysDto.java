package objects.interfaces.dto;

import enumere.TypeMobilite;

public interface PaysDto {

  public String getCodeIso();

  public void setCodeIso(String code);

  public String getNom();

  public void setNom(String nom);

  public TypeMobilite getTypeMobilite();

  public void setTypeMobilite(TypeMobilite typeMobilite);

  public int getVersion();

  public void setVersion(int version);

}
