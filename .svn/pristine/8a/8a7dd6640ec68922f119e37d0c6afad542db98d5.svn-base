package objects.controller.interfaces;

import java.util.List;

import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.PaysDto;
import objects.interfaces.dto.UtilisateurDto;

public interface RechercheUcController {

  /**
   * @return List de mobiliteDto.
   */
  List<MobiliteDto> effectuerRechercheMobilite();

  /**
   * @param recherche Recoit une chaine de caractere.
   * @return PartenaireDto.
   */
  PartenaireDto effectuerRecherchePartenaire(PartenaireDto partenaire);

  /**
   * @return List de tous les partenaires sous forme de chaine de caracteres.
   */
  List<PartenaireDto> getAllPartenaire();

  /**
   * @return List de tous les partenaires sous forme de PartenaireDto.
   */
  List<PartenaireDto> getAllPartenairesDispo();

  /**
   * @return List d'UtilisateurDto.
   */
  List<UtilisateurDto> effectuerRechercheUtilisateurs();

  /**
   * @param nom Reçoit une chaine de caractere.
   * @param prenom Reçoit un chaine de caractere.
   * @return UtilisateurDto.
   */
  UtilisateurDto selectUserByNames(String nom, String prenom);

  /**
   * @param user Reçoit une chaine de caractere.
   * @param string Reçoit une chaine de caractere.
   * @return MobiliteDto.
   */
  /**
   * @return List de PaysDto.
   */
  List<PaysDto> getPays();

  MobiliteDto selectMobByUserAndPart(String user, String string);

  MobiliteDto selectMobByUserAndPart(String user, PartenaireDto partenaire);
}
