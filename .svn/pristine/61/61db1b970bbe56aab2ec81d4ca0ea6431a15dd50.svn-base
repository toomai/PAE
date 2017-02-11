package objects.model;

import enumere.TypeOrganisation;
import objects.interfaces.bizz.PartenaireBizz;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.PaysDto;
import util.Util;

public class PartenaireImpl implements PartenaireBizz, PartenaireDto {

  private String nomLegal;
  private String nomAffaire;
  private String nomComplet;
  private String departement;
  private TypeOrganisation typeOrganisation;
  private int nbEmploye;
  private AdresseDto adresse;
  private PaysDto pays;
  private String email;
  private String siteWeb;
  private String telephonne;
  private boolean ajoutParEleve;
  private int id;
  private int version;
  private boolean dispo;


  public PaysDto getPays() {
    return this.pays;
  }

  public void setPays(PaysDto pays) {
    this.pays = pays;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public int getId() {
    return id;
  }

  public String getNomLegal() {
    return nomLegal;
  }

  public String getNomAffaire() {
    return nomAffaire;
  }

  public String getNomComplet() {
    return nomComplet;
  }

  public String getDepartement() {
    return departement;
  }

  public TypeOrganisation getTypeOrganisation() {
    return typeOrganisation;
  }

  public int getNbEmploye() {
    return nbEmploye;
  }

  public AdresseDto getAdresse() {
    return adresse;
  }

  public String getEmail() {
    return email;
  }

  public String getSiteWeb() {
    return siteWeb;
  }

  public String getTelephonne() {
    return telephonne;
  }

  public void setNomLegal(String nomLegal) {
    this.nomLegal = nomLegal;
  }

  public void setNomAffaire(String nomAffaire) {
    this.nomAffaire = nomAffaire;
  }

  public void setNomComplet(String nomComplet) {
    this.nomComplet = nomComplet;
  }

  public void setDepartement(String departement) {
    this.departement = departement;
  }

  public void setTypeOrganisation(TypeOrganisation typeOrganisation) {
    this.typeOrganisation = typeOrganisation;
  }

  public void setNbEmploye(int nbEmploye) {
    this.nbEmploye = nbEmploye;
  }

  public void setAdresse(AdresseDto adresse) {
    this.adresse = adresse;
  }


  public void setEmail(String email) {
    this.email = email;
  }

  public void setSiteWeb(String siteWeb) {
    this.siteWeb = siteWeb;
  }

  public void setTelephonne(String telephonne) {
    this.telephonne = telephonne;
  }

  public void setAjoutParEleve(boolean ajoutParEleve) {
    this.ajoutParEleve = ajoutParEleve;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean getAjoutParEleve() {
    return ajoutParEleve;
  }

  @Override
  public boolean readyInsert() {
    boolean aRet = nomLegal != null && nomComplet != null && typeOrganisation != null
        && pays != null && email != null && siteWeb != null && telephonne != null;
    return aRet;
  }

  @Override
  public boolean checkParam() {
    try {
      Util.checkStringSize(nomLegal, 25);
      Util.checkStringSize(nomComplet, 50);
      Util.checkStringSize(departement, 30);
      Util.checkAdresse(adresse);
      Util.checkPays(pays);
      Util.checkEmail(email);
      Util.checkWeb(siteWeb);
      Util.checkTel(telephonne);
      Util.checkPositiveOu0(id);
      Util.checkPositiveOu0(version);
    } catch (IllegalArgumentException excep) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isDispo() {
    return this.dispo;
  }

  @Override
  public void setDispo(boolean dispo) {
    this.dispo = dispo;
  }


}
