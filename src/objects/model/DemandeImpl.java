package objects.model;

import enumere.TypeDemande;
import enumere.TypeMobilite;
import objects.interfaces.bizz.DemandeBizz;
import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import util.Util;

import java.time.LocalDate;

public class DemandeImpl implements DemandeDto, DemandeBizz {
  private int idDemande;
  private UtilisateurDto etudiant;
  private PartenaireDto partenaire;
  private TypeMobilite typeMobilite;
  private int ordrePreference;
  private TypeDemande type;
  private String periode;
  private LocalDate dateIntroduction;
  private int anneeAcademique;
  private int version;

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public int getId_demande() {
    return idDemande;
  }

  public void setIdDemande(int idDemande) {
    this.idDemande = idDemande;
  }

  public UtilisateurDto getEtudiant() {
    return etudiant;
  }

  public void setEtudiant(UtilisateurDto etudiant) {
    this.etudiant = etudiant;
  }

  public PartenaireDto getPartenaire() {
    return partenaire;
  }

  public void setPartenaire(PartenaireDto partenaire) {
    this.partenaire = partenaire;
  }

  public TypeMobilite getTypeMobilite() {
    return typeMobilite;
  }

  public void setTypeMobilite(TypeMobilite typeMobilite) {
    this.typeMobilite = typeMobilite;
  }

  public int getOrdrePreference() {
    return ordrePreference;
  }

  public void setOrdrePreference(int ordrePreference) {
    this.ordrePreference = ordrePreference;
  }

  public TypeDemande getType() {
    return type;
  }

  public void setTypeDemande(TypeDemande type) {
    this.type = type;
  }

  public String getPeriode() {
    return periode;
  }

  public void setPeriode(String periode) {
    this.periode = periode;
  }

  public LocalDate getDateIntroduction() {
    return dateIntroduction;
  }

  public void setDateIntroduction(LocalDate dateIntroduction) {
    this.dateIntroduction = dateIntroduction;
  }

  public int getAnneeAcademique() {
    return anneeAcademique;
  }

  public void setAnneeAcademique(int anneeAcademique) {
    this.anneeAcademique = anneeAcademique;
  }

  @Override
  public boolean readyInsert() {
    boolean aRet =
        anneeAcademique != 0 && etudiant != null && dateIntroduction != null && ordrePreference != 0
            && partenaire != null && type != null && periode != null && typeMobilite != null;
    return aRet;
  }

  @Override
  public boolean checkParam() {
    try {
      Util.checkPositiveOu0(idDemande);
      Util.checkPlusGrand(anneeAcademique, 2000);
      Util.checkPositive(ordrePreference);
      Util.checkPositive(version);
      Util.checkPositiveOu0(partenaire.getId());
      Util.checkStringSize(etudiant.getPseudo(), 20);
      Util.checkPositive(ordrePreference);
      LocalDate temp = LocalDate.now();
      if (!(temp.getYear() == dateIntroduction.getYear()
          && temp.getMonth() == dateIntroduction.getMonth())) {
        return false;
      }
    } catch (IllegalArgumentException excep) {
      return false;
    }
    return true;

  }
}
