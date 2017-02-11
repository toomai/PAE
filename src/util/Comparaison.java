package util;

import enumere.Civilite;
import enumere.Etat;
import enumere.Section;
import enumere.Sexe;
import enumere.Status;
import enumere.TypeDemande;
import enumere.TypeMobilite;
import enumere.TypeOrganisation;

public class Comparaison {

  /**
   * @param typeDem renvoie le type de demande.
   */
  public static TypeDemande compareTypeDemande(String typeDem) {
    TypeDemande type = null;
    if (typeDem != null) {
      switch (typeDem.trim()) {
        case "SMS":
          type = TypeDemande.SMS;
          break;
        case "SMP":
          type = TypeDemande.SMP;
          break;
        default:
          type = null;
          break;
      }
    }
    return type;
  }

  /**
   * @param typeMob renvoie le type de mobilite.
   */
  public static TypeMobilite compareTypeMobilite(String typeMob) {
    TypeMobilite type = null;
    if (typeMob != null) {
      switch (typeMob.trim()) {
        case "Erasmus+":
          type = TypeMobilite.ERASMUS;
          break;
        case "Fame":
          type = TypeMobilite.FAME;
          break;
        case "Erabel":
          type = TypeMobilite.ERABEL;
          break;
        case "ERASMUS":
          type = TypeMobilite.ERASMUS;
          break;
        case "FAME":
          type = TypeMobilite.FAME;
          break;
        case "ERABEL":
          type = TypeMobilite.ERABEL;
          break;
        default:
          type = null;
          break;
      }
    }
    return type;
  }

  /**
   * @param etat renvoie l'etat.
   */
  public static Etat compareEtat(String etat) {
    Etat et = null;
    if (etat != null) {
      switch (etat.trim()) {
        case "EN_PREPARATION":
          et = Etat.EN_PREPARATION;
          break;
        case "A_PAYER":
          et = Etat.A_PAYER;
          break;
        case "SOLDE":
          et = Etat.SOLDE;
          break;
        case "PAYEE":
          et = Etat.PAYEE;
          break;
        case "ANNULE":
          et = Etat.ANNULE;
          break;
        default:
          et = null;
          break;
      }
    }
    return et;
  }

  /**
   * @param type renvoie le type d'organisation.
   */
  public static TypeOrganisation compareTypeOrganisation(String type) {
    TypeOrganisation typeOrg = null;
    if (type != null) {
      switch (type.trim()) {
        case "TPE":
          typeOrg = TypeOrganisation.TPE;
          break;
        case "PME":
          typeOrg = TypeOrganisation.PME;
          break;
        case "ETI":
          typeOrg = TypeOrganisation.ETI;
          break;
        case "TGE":
          typeOrg = TypeOrganisation.TGE;
          break;
        case "ECOLE":
          typeOrg = TypeOrganisation.ECOLE;
          break;
        default:
          typeOrg = null;
          break;
      }
    }
    return typeOrg;
  }

  /**
   * @param civilite renvoie la civilite.
   */
  public static Civilite compareCivilite(String civilite) {
    Civilite civ = null;
    if (civilite != null) {
      switch (civilite.trim()) {
        case "M":
          civ = Civilite.M;
          break;
        case "MLLE":
          civ = Civilite.MLLE;
          break;
        case "MME":
          civ = Civilite.MME;
          break;
        default:
          civ = null;
          break;
      }
    }
    return civ;
  }

  /**
   * @param section renvoie la section.
   */
  public static Section compareSection(String section) {
    Section sec = null;
    if (section != null) {
      switch (section) {
        case "BIN":
          sec = Section.BIN;
          break;
        case "BBM":
          sec = Section.BBM;
          break;
        case "BCH":
          sec = Section.BCH;
          break;
        case "BIM":
          sec = Section.BIM;
          break;
        case "BDI":
          sec = Section.BDI;
          break;
        default:
          sec = null;
      }
    }
    return sec;
  }

  /**
   * @param status renvoie le status.
   */
  public static Status compareStatus(String status) {
    Status stat = null;
    if (status != null) {
      switch (status) {
        case "ETUDIANT":
          stat = Status.ETUDIANT;
          break;
        case "PROFESSEUR":
          stat = Status.PROFESSEUR;
          break;
        default:
          stat = null;
          break;
      }
    }
    return stat;
  }

  /**
   * @param sexe renvoie le sexe.
   */
  public static Sexe compareSexe(String sexe) {
    Sexe sex = null;
    if (sexe != null) {
      switch (sexe) {
        case "M":
          sex = Sexe.M;
          break;
        case "F":
          sex = Sexe.F;
          break;
        default:
          sex = null;
          break;
      }
    }
    return sex;
  }
}
