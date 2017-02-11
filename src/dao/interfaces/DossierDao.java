package dao.interfaces;

import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;

import java.sql.SQLException;
import java.util.List;

public interface DossierDao {
  /**
   * @param dossier Reçois un dossier en parametre.
   * @throws SQLException insère dans la table dossiers.
   */
  void insert(DossierDto dossier);

  /**
   * @return List Null si vide.
   * @throws SQLException Renvoi tout le contenu de la table dossiers.
   */
  List<DossierDto> selectAll();

  /**
   * @return dossier Null si rien trouvé.
   * @throws SQLException Recherche par id Mobilite.
   */
  DossierDto select(DossierDto dossier);

  /**
   * @param dossier Reçois un dossier en parametre.
   * @throws SQLException Update via id Mobilite.
   */
  void update(DossierDto dossier);

  /**
   * @param dossier Reçois un dossier en parametre.
   * @throws SQLException Delete via idMobilite.
   */
  void delete(DossierDto dossier);

  /**
   * @param mobilite Reçois une mobilite en parametre.
   * @return dossierDto Renvoi les dossier d'apres un mobilite.
   */
  public DossierDto selectByMobi(MobiliteDto mobilite);

}
