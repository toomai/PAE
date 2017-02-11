package objects.model;

import enumere.Etat;
import enumere.TypeDemande;
import enumere.TypeMobilite;
import objects.interfaces.bizz.MobiliteBizz;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import util.Util;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class MobiliteImpl implements MobiliteDto, MobiliteBizz {
  private int id;
  private int choix;
  private UtilisateurDto etudiant;
  private PartenaireDto partenaire;
  private int annulationId;

  private Etat etat;
  private String periode;
  private TypeDemande type;
  private LocalDate dateDebut;
  private LocalDate dateFin;
  private int version;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    Util.checkPositive(id);
    this.id = id;
  }


  public int getChoix() {
    return choix;
  }


  public void setChoix(int choix) {
    Util.checkPositive(choix);
    this.choix = choix;
  }

  public void setEtat(Etat etat) {
    this.etat = etat;
  }

  public Etat getEtat() {
    return this.etat;
  }

  public UtilisateurDto getEtudiant() {
    return etudiant;
  }

  public void setEtudiant(UtilisateurDto etudiant) {
    this.etudiant = etudiant;
  }

  public String getPeriode() {
    return periode;
  }

  public void setPeriode(String periode) {
    this.periode = periode;
  }

  public LocalDate getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(LocalDate dateDebut) {
    this.dateDebut = dateDebut;
  }

  public LocalDate getDateFin() {
    return dateFin;
  }

  public void setDateFin(LocalDate dateFin) {
    this.dateFin = dateFin;
  }

  public TypeDemande getType() {
    return type;
  }

  public void setType(TypeDemande type) {
    this.type = type;
  }

  @Override
  public PartenaireDto getPartenaire() {
    return this.partenaire;
  }

  @Override
  public void setPartenaire(PartenaireDto partenaire) {
    this.partenaire = partenaire;
  }

  @Override
  public void setTypeDemande(TypeDemande type) {
    this.type = type;
  }

  @Override
  public int getVersion() {
    return this.version;
  }

  @Override
  public void setVersion(int version) {
    this.version = version;
  }

  public int getAnnulationId() {
    return annulationId;
  }

  public void setAnnulationId(int annulationId) {
    this.annulationId = annulationId;
  }

  public boolean readyInsert() {
    boolean toRet = choix != 0 && dateDebut != null && dateFin != null && etat != null
        && etudiant != null && partenaire != null && periode != null && type != null;
    return toRet;
  }

  @Override
  public boolean checkDate() {
    if (periode.equals("Q1")) {
      if (dateDebut.getMonth().compareTo(Month.SEPTEMBER) < 0) {
        return false;
      }
    } else {
      if (dateDebut.getMonth().compareTo(Month.APRIL) > 0) {
        return false;
      }
    }
    Period period = Period.between(dateDebut, dateFin);
    if (this.type.equals(TypeDemande.SMS)) {
      return period.getMonths() >= 3;
    } else {
      if (partenaire.getPays().getTypeMobilite().equals(TypeMobilite.FAME)) {
        return period.getMonths() >= 3;
      } else {
        return period.getMonths() >= 2;
      }
    }
  }

  @Override
  public boolean checkParam() {
    try {
      Util.checkPositiveOu0(id);
      Util.checkPositive(choix);
      Util.checkPositiveOu0(version);
      Util.checkPositiveOu0(annulationId);
      Util.checkStringSize(etudiant.getPseudo(), 20);
      Util.checkPositiveOu0(partenaire.getId());
    } catch (IllegalArgumentException excep) {
      System.out.println(excep.getMessage());
      return false;
    }
    return true;
  }



}
