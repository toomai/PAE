package objects.controller.interfaces;

import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.UtilisateurDto;

import java.sql.SQLException;
import java.util.List;

public interface MobiliteUcController {
  /**
   * @param utilisateur Recoit un utilisateur.
   * @return List Renvoie un liste de mobiliteDto d'apres un utilisateur.
   */
  List<MobiliteDto> getMobilites(UtilisateurDto utilisateur);

  /**
   * @return List Renvoie une liste de mobiliteDto.
   */
  List<MobiliteDto> getAllMobilites();

  /**
   * @param mobi Recoit une mobilite.
   * @return mobiliteDto Renvoie une mobilite.
   */
  MobiliteDto getMobiliteById(MobiliteDto mobi);

  /**
   * @param mobi Recoit une mobilite.
   * @throws SQLException Renvoie une SqlException si pas d'update.
   */
  public void updateMobi(MobiliteDto mobi) throws SQLException;

  /**
   * @return List de mobiliteDto.
   */
  List<MobiliteDto> getAllMobilitesPaiementsDemandes();

  /**
   * @param utilisateur Recoit un utilisateur.
   * @return MobiliteDto Renvoie une mobilite.
   */
  MobiliteDto getMobilite(UtilisateurDto utilisateur);

  /**
   * @param mobilite Recoit une mobilite a modifier l'etat.
   * @param etat Recoit un etat.
   */
  void changeEtatMobilite(MobiliteDto mobilite, String etat);

  /**
   * @param mobi Recoit une mobilite a annule.
   */
  void updateAnnulation(MobiliteDto mobi);
}
