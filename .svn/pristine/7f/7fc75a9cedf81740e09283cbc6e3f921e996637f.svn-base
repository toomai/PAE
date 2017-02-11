package dao.model;

import dal.DalAccessServices;
import dao.interfaces.UtilisateurDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.UtilisateurDto;
import util.Comparaison;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class UtilisateurDaoImpl implements UtilisateurDao {
  DalAccessServices access;
  BizzFactory factory;
  static final String INSERT_STATEMENT =
      "INSERT INTO mobipl.utilisateurs (nom, prenom, email, section, pseudo, statut,"
          + " mdp, date_inscription, version) VALUES(?,?,?,?,?,?,?,?,0)";
  static final String SELECT_STATEMENT =
      "SELECT  u.mdp, u.nom, u.prenom, u.sexe, u.statut, u.date_de_naissance"
          + ", u.civilite, u.rue, u.numero, u.boite, u.region, u.code_postal, u.ville, "
          + "u.nationalite, u.email, u.nbr_annee_superieur, u.num_compte, "
          + "u.titulaire_compte, u.nom_banque, u.bic, u.date_inscription, u.section,"
          + "u.version FROM mobipl.utilisateurs u WHERE u.pseudo = ?";
  static final String UPDATE_STATEMENT =
      "UPDATE mobipl.utilisateurs SET nom = ?, prenom = ?, date_de_naissance = ?,"
          + "nationalite=?, rue=?, numero=?, boite=?, region=?, code_postal=?, ville=?, email=?, nbr_annee_superieur=?, sexe = ?, num_compte=?,"
          + "titulaire_compte=?, bic=? , version = ?" + "WHERE pseudo=?"; // AND version = ?
  static final String UPDATE_STATUS_STATEMENT =
      "UPDATE mobipl.utilisateurs set statut = ? , version = ?"
          + " where pseudo = ? and version = ?";
  static final String SELECT_ALL_STATEMENT =
      "SELECT u.nom, u.prenom, u.section, u.email, u.statut, u.version from mobipl.utilisateurs u";

  static final String SELECT_PSEUDOS_ETU_STATEMENT =
      "SELECT u.pseudo FROM mobipl.utilisateurs u " + "WHERE u.statut = 'ETUDIANT'";

  static final String SELECT_ETUDIANTS_STATEMENT =
      "SELECT u.nom, u.prenom, u.pseudo, u.section, u.email from mobipl.utilisateurs u WHERE"
          + " u.statut = 'ETUDIANT'";

  static final String SELECT_BY_NAME_STATEMENT =
      "SELECT u.pseudo, u.nom, u.prenom, u.section, u.version"
          + " from mobipl.utilisateurs u where (u.nom LIKE ? AND u.prenom LIKE ?)";

  static final String COUNT_USER = "SELECT count(*) FROM mobipl.utilisateurs";
  static final Logger LOG = LogManager.getLogger(UtilisateurDao.class);

  public UtilisateurDaoImpl(DalAccessServices access, BizzFactory factory) {
    this.access = access;
    this.factory = factory;
  }

  /**
   * insert un utilisateur dans la db.
   */

  public boolean insert(UtilisateurDto utilisateur) {
    try {
      PreparedStatement ps = this.access.prepare(INSERT_STATEMENT);

      ps.setString(1, utilisateur.getNom());
      ps.setString(2, utilisateur.getPrenom());
      ps.setString(3, utilisateur.getEmail());
      ps.setString(4, "" + utilisateur.getSection());
      ps.setString(5, utilisateur.getPseudo());
      ps.setString(6, "" + utilisateur.getStatus());
      ps.setString(7, utilisateur.getMdp());
      ps.setDate(8, Date.valueOf(LocalDate.now()));

      ps.executeUpdate();
      return true;
    } catch (SQLException excep) {
      System.out.println(excep.getMessage());
      throw new InternalError();
    }
  }


  @Override
  public UtilisateurDto select(UtilisateurDto utilisateur) {
    try {
      PreparedStatement ps = this.access.prepare(SELECT_STATEMENT);
      ps.setString(1, utilisateur.getPseudo());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          utilisateur.setMdp(rs.getString(1));
          utilisateur.setNom(rs.getString(2));
          utilisateur.setPrenom(rs.getString(3));
          utilisateur.setEmail(rs.getString(15));
          utilisateur.setStatus(Comparaison.compareStatus(rs.getString(5)));
          if (rs.getString(4) != null) {
            utilisateur.setSexe(Comparaison.compareSexe(rs.getString(4)));
            utilisateur.setStatus(Comparaison.compareStatus(rs.getString(5)));
            if (rs.getDate(6) != null) {
              utilisateur.setDateNaissance(rs.getDate(6).toLocalDate());
            }
            utilisateur.setCivilite(Comparaison.compareCivilite(rs.getString(7)));
            AdresseDto adresse = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
            adresse.setRue(rs.getString(8));
            adresse.setNumero(rs.getInt(9));
            adresse.setBoite(rs.getString(10));
            adresse.setRegion(rs.getString(11));
            adresse.setCp(rs.getString(12));
            adresse.setVille(rs.getString(13));
            utilisateur.setAdresse(adresse);
            utilisateur.setNationalite(rs.getString(14));
            utilisateur.setNbrAnneeSuperieur(rs.getInt(16));
            utilisateur.setNumCompte(rs.getString(17));
            utilisateur.setTitulaire(rs.getString(18));
            utilisateur.setNomBanque(rs.getString(19));
            utilisateur.setBic(rs.getString(20));
            utilisateur.setDateInscription(rs.getDate(21).toLocalDate());
          }
          utilisateur.setSection(Comparaison.compareSection(rs.getString(22)));
          utilisateur.setNumVersion(rs.getInt(23));
          return utilisateur;
        }
      }
    } catch (SQLException excep) {
      throw new InternalError();
    }
    return null;
  }

  /*
   * Gestion de la version . L'utilisateur en param n'a pas nécessairement de numéro de version. A
   * voir le cas ou un prof update les données, le user retenu dans la session sera un prof et
   * n'aura donc pas le même num version. Envisager un systême ou lorsque qu'un prof remplace un
   * élève, remplacer la varible Session[user] par l'éleve qu'il remplace
   */

  @Override
  public boolean update(UtilisateurDto demandeur, UtilisateurDto utilisateur) {
    try {
      PreparedStatement ps = this.access.prepare(UPDATE_STATEMENT);
      // UtilisateurDto utilDeux = this.select(utilisateur);
      // if (utilDeux.getNumVersion() != demandeur.getNumVersion())
      // throw new ConcurrentModificationException();
      ps.setString(1, utilisateur.getNom());
      ps.setString(2, utilisateur.getPrenom());
      ps.setDate(3, Date.valueOf(utilisateur.getDateNaissance()));
      ps.setString(4, utilisateur.getNationalite());
      ps.setString(5, utilisateur.getAdresse().getRue());
      ps.setInt(6, utilisateur.getAdresse().getNumero());
      ps.setString(7, utilisateur.getAdresse().getBoite());
      ps.setString(8, utilisateur.getAdresse().getRegion());
      ps.setString(9, utilisateur.getAdresse().getCp());
      ps.setString(10, utilisateur.getAdresse().getVille());
      ps.setString(11, utilisateur.getEmail());
      ps.setInt(12, utilisateur.getNbrAnneeSuperieur());
      ps.setString(13, "" + utilisateur.getSexe());
      ps.setString(14, utilisateur.getNumCompte());
      ps.setString(15, utilisateur.getTitulaire());
      ps.setString(16, utilisateur.getBic());
      // !!!! A vérifier !!!!
      ps.setLong(17, (utilisateur.getNumVersion() + 1));
      // / !!!!!!!!!!!!!!!!!!!!
      ps.setString(18, utilisateur.getPseudo());
      // ps.setInt(19, utilisateur.getNumVersion());
      ps.executeUpdate();
      return true;
    } catch (SQLException excep) {
      throw new InternalError();
    }
  }

  @Override
  public void delete(UtilisateurDto utilisateur) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<UtilisateurDto> selectAll() {
    PreparedStatement ps = this.access.prepare(SELECT_ALL_STATEMENT);
    List<UtilisateurDto> results = new ArrayList<UtilisateurDto>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        UtilisateurDto user = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
        user.setNom(rs.getString(1));
        user.setPrenom(rs.getString(2));
        user.setEmail(rs.getString(4));
        user.setSection(Comparaison.compareSection(rs.getString(3)));
        user.setStatus(Comparaison.compareStatus(rs.getString(5)));
        user.setNumVersion(rs.getInt(6));
        results.add(user);
      }
      return results;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<UtilisateurDto> selectEtudiants() {
    PreparedStatement ps = this.access.prepare(SELECT_ETUDIANTS_STATEMENT);
    List<UtilisateurDto> results = new ArrayList<UtilisateurDto>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        UtilisateurDto user = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
        user.setNom(rs.getString(1));
        user.setPrenom(rs.getString(2));
        user.setPseudo(rs.getString(3));
        user.setSection(Comparaison.compareSection(rs.getString(4)));
        user.setEmail(rs.getString(5));
        results.add(user);
      }
      return results;
    } catch (SQLException excep) {
      throw new InternalError();
    }
  }

  @Override
  public UtilisateurDto selectByName(String nom, String prenom) {
    PreparedStatement ps = this.access.prepare(SELECT_BY_NAME_STATEMENT);
    UtilisateurDto utilisateur =
        (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
    try {
      ps.setString(1, "%" + nom + "%");
      ps.setString(2, "%" + prenom + "%");
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          utilisateur.setPseudo(rs.getString(1));
          utilisateur.setNom(rs.getString(2));
          utilisateur.setPrenom(rs.getString(3));
          utilisateur.setSection(Comparaison.compareSection(rs.getString(4)));
          utilisateur.setNumVersion(rs.getInt(5));
          // utilisateur.setDateNaissance(rs.getDate(7).toLocalDate());
          // AdresseDto adresse = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
          // adresse.setRue(rs.getString(9));
          // adresse.setNumero(rs.getInt(10));
          // adresse.setBoite(rs.getString(11));
          // adresse.setRegion(rs.getString(12));
          // adresse.setCp(rs.getString(13));
          // adresse.setVille(rs.getString(14));
          // utilisateur.setAdresse(adresse);
          // utilisateur.setNationalite(rs.getString(15));
          // utilisateur.setEmail(rs.getString(16));
          // utilisateur.setNbrAnneeSuperieur(rs.getInt(17));
          // utilisateur.setNumCompte(rs.getString(18));
          // utilisateur.setTitulaire(rs.getString(19));
          // utilisateur.setNomBanque(rs.getString(20));
          // utilisateur.setBic(rs.getString(21));
          // utilisateur.setDateInscription(rs.getDate(22).toLocalDate());
        }
        return utilisateur;
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public boolean updateStatus(UtilisateurDto utilisateur) {
    try {
      PreparedStatement ps = this.access.prepare(UPDATE_STATUS_STATEMENT);
      ps.setString(1, "" + utilisateur.getStatus());
      ps.setInt(2, utilisateur.getNumVersion() + 1);
      ps.setString(3, utilisateur.getPseudo());
      ps.setInt(4, utilisateur.getNumVersion());
      ps.executeUpdate();
      return true;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<String> selectEtudiantsPseudos() {
    PreparedStatement ps = this.access.prepare(SELECT_PSEUDOS_ETU_STATEMENT);
    List<String> results = new ArrayList<String>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        results.add(rs.getString(1));
      }
      return results;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  public boolean emptyOrNot() {
    PreparedStatement ps = this.access.prepare(COUNT_USER);
    try {
      ResultSet rs = ps.executeQuery();
      rs.next();
      if (rs.getInt(1) == 0) {
        return true;
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    return false;
  }
}
