package objects.interfaces.dto;

import enumere.Civilite;
import enumere.Section;
import enumere.Sexe;
import enumere.Status;

import java.time.LocalDate;


public interface UtilisateurDto {
  int getNumVersion();

  String getPseudo();

  String getNom();

  String getPrenom();

  String getNationalite();

  String getNumCompte();

  String getTitulaire();

  String getNomBanque();

  String getBic();

  Sexe getSexe();

  Status getStatus();

  Civilite getCivilite();

  Section getSection();

  String getEmail();

  AdresseDto getAdresse();

  String getMdp();

  int getNbrAnneeSuperieur();

  LocalDate getDateNaissance();

  LocalDate getDateInscription();

  void setPseudo(String pseudo);

  void setNom(String nom);

  void setPrenom(String prenom);

  void setMdp(String mdp);

  void setNationalite(String nationalite);

  void setNumCompte(String numCompte);

  void setTitulaire(String titulaire);

  void setNomBanque(String nomBanque);

  void setBic(String bic);

  void setSexe(Sexe sexe);

  void setStatus(Status status);

  void setCivilite(Civilite civilite);

  void setEmail(String email);

  void setAdresse(AdresseDto adresse);

  void setNbrAnneeSuperieur(int nbrAnneeSuperieur);

  void setDateNaissance(LocalDate dateNaissance);

  void setDateInscription(LocalDate dateInscription);

  void setSection(Section section);

  void setNumVersion(int version);

}
