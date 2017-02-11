package objects.controller.interfaces;

import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.MobiliteDto;

import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.UtilisateurDto;

import java.util.List;

public interface DemandeUcController {

  /**
   * @return List Renvoi une liste de demandes.
   */
  List<DemandeDto> listerDemandes();

  /**
   * @param demande Recois une demande.
   * @return demandeDto.
   */
  DemandeDto donnerDemandesAvecId(DemandeDto demande);

  /**
   * @param demande Recois une demande.
   * @return true si la demande a ete supprimer, false sinon.
   */
  boolean supprimerDemande(DemandeDto demande);

  /**
   * @param pseudo Recois un chaine de caractere.
   * @return List de demandeDto d'apres cette chaine.
   */
  List<DemandeDto> listerDemandesParUser(String pseudo);

  boolean confirmerDemande(DemandeDto demande, MobiliteDto mobiliteDto);

  /**
   * @param user Recois un utilisateur.
   * @return List de demandeDto d'apres cet utilisateur.
   */
  List<DemandeDto> listerDemandesParUserFull(UtilisateurDto user);

  /**
   * @param demande Recois une demande.
   * @return true si la demande a ete encode, false sinon.
   */
  boolean encoderDemande(DemandeDto demande);

}
