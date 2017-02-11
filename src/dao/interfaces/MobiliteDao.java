package dao.interfaces;

import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;

import java.sql.SQLException;
import java.util.List;



public interface MobiliteDao {

  public boolean selectByPartenaire(PartenaireDto partenaire);

  /**
   * @param mobilite Reçois une mobilite en parametre.
   * @throws SQLException, Renvoie une Exception si query erroné.
   */
  void insert(MobiliteDto mobilite);

  /**
   * @return Liste des mobiliteDto
   * @throws SQLException, Renvoie une Exception si query erroné.
   */
  List<MobiliteDto> selectAll();

  /**
   * @return Liste mobiliteDto correspondant aux critères, null si vide
   * @throws SQLException Critères: période et état.
   */
  List<MobiliteDto> select(MobiliteDto mobilite);

  /**
   * @param utilisateur Reçois une utilisateur en parametre.
   * @return mobiliteDto null si vide
   * @throws SQLException Renvoie une Exception si query erroné.
   */

  List<MobiliteDto> selectParUtilisateur(UtilisateurDto utilisateur);

  MobiliteDto selectByChoix(MobiliteDto mobilite);

  MobiliteDto selectById(MobiliteDto mobi);

  /**
   * @param mobilite Reçoit une mobilite en parametre.
   * @throws SQLException Update selon id.
   */
  void update(MobiliteDto mobilite) throws SQLException;

  /**
   * @param mobilite Reçoit une mobilite en parametre.
   * @throws SQLException Delete selon id.
   */
  void delete(MobiliteDto mobilite);

  /*
   * (non-Javadoc)
   * 
   * @see dao.interfaces.PartenaireDao#selectByNameAndStad(java.lang.String, java.lang.String)
   * Mauvais paramètres !!!! DTO !!!!!
   */

  // TODO
  /**
   * @param user Reçoit un utilisateur.
   * @param idPartenaire Reçoit l'id d'un partenaire.
   * @return mobiliteDto d'apres l'utilisateur et l'id du partenaire.
   */
  MobiliteDto selectByUserAndPartner(String user, int idPartenaire);

  /**
   * @return List Renvoie un liste de mobiliteDto.
   */
  List<MobiliteDto> getAllMobilitesPaiementDemande();

  /**
   * @param utilisateur Reçoit un utilisateur.
   * @return mobiliteDto Renvoie une mobilitedto d'apres un utilisateur.
   */
  MobiliteDto selectByUser(UtilisateurDto utilisateur);

  /**
   * @param mobilite Rçoit une mobilite.
   */
  void updateEtat(MobiliteDto mobilite);

  /**
   * @param mobilite Recoit un mobiliteDto.
   */
  void updateAnnulation(MobiliteDto mobilite);

}
