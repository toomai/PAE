package objects.interfaces.dto;

import enumere.TypeOrganisation;

public interface PartenaireDto {

  boolean isDispo();

  void setDispo(boolean dispo);

  PaysDto getPays();

  void setPays(PaysDto pays);

  int getId();

  String getNomLegal();

  String getNomAffaire();

  String getNomComplet();

  String getDepartement();

  TypeOrganisation getTypeOrganisation();

  int getNbEmploye();

  AdresseDto getAdresse();

  String getEmail();

  String getSiteWeb();

  String getTelephonne();

  void setNomLegal(String nom);

  void setNomAffaire(String nom);

  void setNomComplet(String nom);

  void setDepartement(String departement);

  void setTypeOrganisation(TypeOrganisation type);

  void setNbEmploye(int nb);

  void setAdresse(AdresseDto adresse);

  void setEmail(String mail);

  void setSiteWeb(String web);

  void setTelephonne(String tel);

  void setAjoutParEleve(boolean ajout);

  void setId(int id);

  boolean getAjoutParEleve();

  public int getVersion();

  public void setVersion(int version);

}
