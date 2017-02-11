package dao.interfaces;


import objects.interfaces.dto.UtilisateurDto;

import java.util.List;

/*
 * Create Read Update Delete
 */
public interface UtilisateurDao {
  /**
   * @param utilisateur Reçois un utilisateur.
   * @return true si l'insertion a reussi, false sinon.
   */
  boolean insert(UtilisateurDto utilisateur);

  /**
   * @return List Renvoi la liste des utilisateurs.
   */
  List<UtilisateurDto> selectAll();

  /**
   * @param utilisateur Reçois un utilisateur.
   * @return utilisateurDto.
   */
  UtilisateurDto select(UtilisateurDto utilisateur);

  /**
   * @param demandeur Reçois un utilisateur.
   * @param utilisateur Reçois un utilisateur.
   * @return true si la modification a ete faite, false sinon.
   */
  boolean update(UtilisateurDto demandeur, UtilisateurDto utilisateur);

  /**
   * @param utilisateur Reçois un utilisateur.
   */
  void delete(UtilisateurDto utilisateur);


  /*
   * (non-Javadoc)
   * 
   * @see dao.interfaces.PartenaireDao#selectByNameAndStad(java.lang.String, java.lang.String)
   * Mauvais paramètres !!!! DTO !!!!!
   */

  // TODO

  /**
   * @param nom Recois une chaine de caractere.
   * @param prenom Recois une chaine de caractere.
   * @return utilisateurDto Renvoie l'utilisateur d'apres le nom et le prenom.
   */
  UtilisateurDto selectByName(String nom, String prenom);

  /**
   * @return List Renvoie la liste des etudiants.
   */
  List<UtilisateurDto> selectEtudiants();

  /**
   * @param utilisateur Recois un utilisateur.
   * @return true si l'update du status a eu lieu, false sinon.
   */
  boolean updateStatus(UtilisateurDto utilisateur);

  List<String> selectEtudiantsPseudos();

  public boolean emptyOrNot();

}
