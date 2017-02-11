package dao.interfaces;

import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.UtilisateurDto;

import java.sql.SQLException;
import java.util.List;

public interface DemandeDao {

  /**
   * @param demande Reçois une demande a inserer en parametre.
   * @throws SQLException Renvoie un exception sinon.
   */
  void insert(DemandeDto demande);

  /**
   * @return Liste des demandeDto.
   * @throws SQLException Renvoie tout le contnenu de la table.
   */
  List<DemandeDto> selectAll();

  /**
   * @return Liste demandeDto correspondant aux critères.
   * @throws SQLException Critères: période et état.
   */
  List<DemandeDto> select(DemandeDto demande);

  /*
   * (non-Javadoc)
   * 
   * @see dao.interfaces.PartenaireDao#selectByNameAndStad(java.lang.String, java.lang.String)
   * Mauvais paramètres !!!! DTO !!!!!
   */

  // TODO
  List<DemandeDto> selectByUser(String pseudo);

  /**
   * @param demande Reçois une demande a inserer en parametre.
   * @throws SQLException Update selon id.
   */
  void update(DemandeDto demande);

  /**
   * @param demande Reçois une demande a inserer en parametre.
   * @throws SQLException Delete selon id.
   */
  void delete(DemandeDto demande);

  /**
   * @param demande Reçois une demande a inserer en parametre.
   * @return demandeDto Liste demandeDto correspondant aux critères.
   * @throws SQLException Renvoie un exception sinon.
   */
  DemandeDto selectById(DemandeDto demande);

  /**
   * @param user Reçois un utilisateur.
   * @return List Renvoie une liste de demandeDto.
   */
  List<DemandeDto> selectAllByUser(UtilisateurDto user);
}
