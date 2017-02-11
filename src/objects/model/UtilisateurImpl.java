package objects.model;

import enumere.Civilite;
import enumere.Section;
import enumere.Sexe;
import enumere.Status;
import objects.interfaces.bizz.UtilisateurBizz;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.UtilisateurDto;
import util.Util;

import java.time.LocalDate;
import java.util.List;

public class UtilisateurImpl implements UtilisateurDto, UtilisateurBizz {
  private String pseudo;
  private String nom;
  private String prenom;
  private String mdp;
  private String nationalite;
  private String numCompte;
  private String titulaire;
  private String nomBanque;
  private String bic;
  private Sexe sexe;
  private Status status;
  private Civilite civilite;
  private String email;
  private AdresseDto adresse;
  private int nbrAnneeSuperieur;
  private LocalDate dateNaissance;
  private LocalDate dateInscription;
  private Section section;
  private int version;
  private List<MobiliteDto> mobilites;



  public String getPseudo() {
    return pseudo;
  }

  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public AdresseDto getAdresse() {
    return this.adresse;
  }

  public void setAdresse(AdresseDto adresse) {
    this.adresse = adresse;
  }

  public String getMdp() {
    return mdp;
  }

  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  public String getNationalite() {
    return nationalite;
  }

  public void setNationalite(String nationalite) {
    this.nationalite = nationalite;
  }

  public String getNumCompte() {
    return numCompte;
  }

  public void setNumCompte(String numCompte) {
    this.numCompte = numCompte;
  }

  public String getTitulaire() {
    return titulaire;
  }

  public void setTitulaire(String titulaire) {
    this.titulaire = titulaire;
  }

  public String getNomBanque() {
    return nomBanque;
  }

  public void setNomBanque(String nomBanque) {
    this.nomBanque = nomBanque;
  }

  public String getBic() {
    return bic;
  }

  public void setBic(String bic) {
    this.bic = bic;
  }

  public Sexe getSexe() {
    return sexe;
  }

  public void setSexe(Sexe sexe) {
    this.sexe = sexe;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Civilite getCivilite() {
    return civilite;
  }

  public void setCivilite(Civilite civilite) {
    this.civilite = civilite;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getNbrAnneeSuperieur() {
    return nbrAnneeSuperieur;
  }

  public void setNbrAnneeSuperieur(int nbrAnneeSuperieur) {
    this.nbrAnneeSuperieur = nbrAnneeSuperieur;
  }

  public LocalDate getDateNaissance() {
    return dateNaissance;
  }

  public void setDateNaissance(LocalDate dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  public LocalDate getDateInscription() {
    return dateInscription;
  }

  public void setDateInscription(LocalDate dateInscription) {
    this.dateInscription = dateInscription;
  }

  public Section getSection() {
    return section;
  }

  public void setSection(Section section) {
    this.section = section;
  }

  public void setNumVersion(int version) {
    this.version = version;
  }

  public int getNumVersion() {
    return this.version;
  }

  public boolean validerMdp(String mdp) {
    return false;
  }

  public List<MobiliteDto> getMobilites() {
    return this.mobilites;
  }

  public void setMobilites(List<MobiliteDto> mobilites) {
    this.mobilites = mobilites;
  }

  public void prepare() {

  }

  @Override
  public String toString() {
    return "UtilisateurImpl [pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", mdp="
        + mdp + ", nationalite=" + nationalite + ", numCompte=" + numCompte + ", titulaire="
        + titulaire + ", nomBanque=" + nomBanque + ", bic=" + bic + ", sexe=" + sexe + ", status="
        + status + ", civilite=" + civilite + ", email=" + email + ", adresse=" + adresse
        + ", nbrAnneeSuperieur=" + nbrAnneeSuperieur + ", dateNaissance=" + dateNaissance
        + ", dateInscription=" + dateInscription + ", section=" + section + ", version=" + version
        + ", mobilites=" + mobilites + "]";
  }

  // Insertion avec données remplies.
  @Override
  public boolean readyInsert() {
    boolean aRet = pseudo != null && mdp != null && nom != null && prenom != null && email != null
        && adresse != null && dateInscription != null;
    return aRet;
  }

  // Insertion lors de la connexion

  @Override
  public boolean readyInsertConnexion() {
    boolean aRet = pseudo != null && mdp != null;
    return aRet;
  }


  @Override
  public boolean checkParam() {
    try {
      Util.checkStringSize(pseudo, 20);
      Util.checkStringSize(mdp, 166);
      Util.checkStringSize(nom, 66);
      Util.checkStringSize(prenom, 50);
      Util.checkEmail(email);
      Util.checkAdresse(adresse);
    } catch (IllegalArgumentException excep) {
      return false;
    }
    return true;
  }


}
