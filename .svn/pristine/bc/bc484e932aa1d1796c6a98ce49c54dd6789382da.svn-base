package dao.interfaces;

import objects.interfaces.dto.PaysDto;

import java.sql.SQLException;
import java.util.List;

public interface PaysDao {

  /**
   * @param demande Reçois une demande a inserer en parametre.
   * @throws SQLException Renvoie un exception sinon.
   */
  /* void insert(PaysDto demande); */

  /**
   * @return Liste des demandeDto.
   * @throws SQLException Renvoie tout le contnenu de la table.
   */
  List<PaysDto> selectAll();

  /**
   * @param demande Reçois une demande a inserer en parametre.
   * @return demandeDto Liste demandeDto correspondant aux critères.
   * @throws SQLException Renvoie un exception sinon.
   */
  PaysDto selectByNom(PaysDto demande);

  /**
   * @param demande Reçois une demande a inserer en parametre.
   * @throws SQLException Update selon id.
   */
  void update(PaysDto demande);

  PaysDto selectById(PaysDto pays);
}
