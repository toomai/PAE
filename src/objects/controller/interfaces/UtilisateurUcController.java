package objects.controller.interfaces;

import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;

import java.util.List;



public interface UtilisateurUcController {
  /**
   * @param utilFrontEnd Reçoit un utilisateur.
   * @return Renvoie une UtilisateurDto.
   */
  UtilisateurDto authentifie(UtilisateurDto utilFrontEnd);

  /**
   * @param utilisateur Reçoit un utilisateur.
   * @return UtilisateurDto.
   */
  UtilisateurDto identifie(UtilisateurDto utilisateur);

  /**
   * @param demandeur Reçoit un utilisateur demandeur.
   * @param utilisateur Reçoit une utilisateur a insere.
   * @return true si l'encodage a ete fait, false sinon.
   */
  boolean encoderEtudiant(UtilisateurDto demandeur, UtilisateurDto utilisateur);

  /**
   * @param demande Reçoit une demande.
   * @param name Reçoit une chaine de caractere.
   * @return true si la demande a ete encodee, false sinon.
   */
  boolean encoderDemande(DemandeDto demande, PartenaireDto partenaire);

  /**
   * @return List d'UtilisateurDto.
   */
  List<UtilisateurDto> getEtudiants();

  /**
   * @param utilisateur Reçoit un utilisateurDto.
   * @param status Reçoit le status sous chaine de caractere.
   * @return true si le status a ete chaine, false sinon.
   */
  boolean changerTypeCompte(UtilisateurDto utilisateur, String status);

  List<UtilisateurDto> getEtudiantsPseudo();

  UtilisateurDto getEtudiant(UtilisateurDto etudiant);

  public boolean emptyOrNot();
}
