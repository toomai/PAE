package dao.model;

import dal.DalAccessServices;
import dao.interfaces.PartenaireDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.AdresseDto;
import objects.interfaces.dto.PartenaireDto;
import util.Comparaison;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartenaireDaoImpl implements PartenaireDao {
  static final Logger LOG = LogManager.getLogger(PartenaireDao.class);

  private DalAccessServices access;
  private BizzFactory factory;

  static final String INSERT_STATEMENT = "INSERT INTO mobipl.partenaires"
      + "(nom_legal,nom_affaire,nom_complet,departement,type_organisation,nbr_employe,"
      + "rue,numero,boite,pays,region,code_postal,ville,email,site_web,tel,ajout_par_eleve, version, dispo)"
      + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,true)";

  static final String SELECT_PARTENAIRE_IN =
      "SELECT p.id_partenaire,p.nom_legal,p.nom_affaire,p.nom_complet,p.departement,"
          + "p.type_organisation,p.nbr_employe,p.rue,p.numero,p.boite,p.pays,p.region,p.code_postal"
          + ",p.ville,p.email,p.site_web, p.tel,p.ajout_par_eleve, p.version, p.dispo "
          + "FROM mobipl.partenaires p WHERE p.id_partenaire =? ";

  static final String SELECT_STATEMENT =
      "SELECT p.id_partenaire,p.nom_legal,p.nom_affaire,p.nom_complet,p.departement,"
          + "p.type_organisation,p.nbr_employe,p.rue,p.numero,p.boite,p.pays,p.region,p.code_postal"
          + ",p.ville,p.email,p.site_web, p.tel,p.ajout_par_eleve, p.version, p.dispo "
          + "FROM mobipl.partenaires p WHERE p.id_partenaire =?";

  static final String SELECT_STATEMENT_BYNAME =
      "SELECT p.id_partenaire,p.nom_legal,p.nom_affaire,p.nom_complet,p.departement,"
          + "p.type_organisation,p.nbr_employe,p.rue,p.numero,p.boite,p.pays,p.region,p.code_postal"
          + ",p.ville,p.email,p.site_web, p.tel,p.ajout_par_eleve, p.version, p.dispo "
          + "FROM mobipl.partenaires p WHERE p.nom_legal=? ";

  static final String SELECT_ALL_STATEMENT =
      "SELECT p.id_partenaire,p.nom_legal,p.nom_affaire,p.nom_complet,p.departement,"
          + "p.type_organisation,p.nbr_employe,p.rue,p.numero,p.boite,p.pays,p.region,p.code_postal"
          + ",p.ville,p.email,p.site_web, p.tel,p.ajout_par_eleve, p.version, p.dispo "
          + "FROM mobipl.partenaires p";

  static final String DELETE_STATEMENT =
      "DELETE FROM mobipl.partenaires WHERE id_partenaire =? and " + "version = ?";

  static final String SELECT_ID_STATEMENT =
      "SELECT p.id_partenaire FROM mobipl.partenaires p " + "WHERE p.nom_legal = ?";
  static final String SELECT_AJOUT =
      "SELECT p.ajout_par_eleve FROM mobipl.partenaires p " + "WHERE p.nom_legal = ?";

  static final String SELECT_STATEMENT_AJOUT =
      "SELECT p.id_partenaire,p.nom_legal,p.nom_affaire,p.nom_complet,p.departement,"
          + "p.type_organisation,p.nbr_employe,p.rue,p.numero,p.boite,p.pays,p.region,p.code_postal"
          + ",p.ville,p.email,p.site_web, p.tel,p.ajout_par_eleve, p.version, p.dispo "
          + "FROM mobipl.partenaires p WHERE p.ajout_par_eleve = false and p.dispo = true";

  static final String SELECT_DISPO =
      "SELECT p.id_partenaire,p.nom_legal,p.nom_affaire,p.nom_complet,p.departement,"
          + "p.type_organisation,p.nbr_employe,p.rue,p.numero,p.boite,p.pays,p.region,p.code_postal"
          + ",p.ville,p.email,p.site_web, p.tel,p.ajout_par_eleve, p.version, p.dispo "
          + "FROM mobipl.partenaires p WHERE  p.dispo = true";

  static final String UPDATE_STATEMENT =
      "UPDATE mobipl.partenaires SET nom_legal=?, nom_affaire=?,nom_complet=?,departement=?,"
          + "type_organisation=?,nbr_employe=?,rue=?,numero=?,boite=?,pays=?,region=?,code_postal=?"
          + ",ville=?,email=?,site_web=?, tel=?,ajout_par_eleve=?, version = ?, dispo = ? WHERE "
          + "id_partenaire =? and version = ? ";
  static final String SELECT_SUPPRIME =
      "SELECT p.id_partenaire, p.nom_legal, p.nom_affaire, p.nom_complet, p.departement,"
          + "p.type_organisation, p.nbr_employe, p.rue, p.numero, p.boite, p.pays, p.region, p.code_postal"
          + ", p.ville, p.email, p.site_web, p.tel, p.ajout_par_eleve, p.version, p.dispo "
          + " FROM mobipl.partenaires p WHERE p.dispo = false and p.ajout_par_eleve = false";

  /*
   * static final String SELECT_BY_NAME_AND_DEPARTEMENT =
   * "SELECT p.* from mobipl.partenaires p where (p.nom_legal LIKE ? AND p.departement LIKE ?)";
   */
  public PartenaireDaoImpl(DalAccessServices access, BizzFactory factory) {
    this.access = access;
    this.factory = factory;
  }

  /**
   * inserer un partenaire en DB.
   */
  // Probleme des adresses
  public boolean insert(PartenaireDto partenaire) {
    try {
      PreparedStatement ps = this.access.prepare(INSERT_STATEMENT);
      ps.setString(1, partenaire.getNomLegal());
      ps.setString(2, partenaire.getNomAffaire());
      ps.setString(3, partenaire.getNomComplet());
      ps.setString(4, partenaire.getDepartement());
      ps.setString(5, "" + partenaire.getTypeOrganisation());
      ps.setInt(6, partenaire.getNbEmploye());
      ps.setString(7, partenaire.getAdresse().getRue());
      ps.setInt(8, partenaire.getAdresse().getNumero());
      ps.setString(9, partenaire.getAdresse().getBoite());

      ps.setString(10, partenaire.getAdresse().getPays());
      ps.setString(11, partenaire.getAdresse().getRegion());
      ps.setString(12, partenaire.getAdresse().getCp());
      ps.setString(13, partenaire.getAdresse().getVille());
      ps.setString(14, partenaire.getEmail());
      ps.setString(15, partenaire.getSiteWeb());
      ps.setString(16, partenaire.getTelephonne());
      ps.setBoolean(17, partenaire.getAjoutParEleve());
      ps.execute();
      return true;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<PartenaireDto> selectAll() {
    List<PartenaireDto> aret = new ArrayList<>();
    try {
      PreparedStatement ps = this.access.prepare(SELECT_ALL_STATEMENT);

      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          do {
            aret.add(lectureResultset(rs));
          } while (rs.next());
          return aret;
        } else {
          return null;
        }
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  private PartenaireDto lectureResultset(ResultSet rs) {
    PartenaireDto part = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    try {
      part.setId(rs.getInt(1));
      part.setNomLegal(rs.getString(2));
      part.setNomAffaire(rs.getString(3));
      part.setNomComplet(rs.getString(4));
      part.setDepartement(rs.getString(5));
      part.setTypeOrganisation(Comparaison.compareTypeOrganisation(rs.getString(6)));
      part.setNbEmploye(rs.getInt(7));

      AdresseDto ad = (AdresseDto) factory.getDto(AdresseDto.class.getSimpleName());
      ad.setRue(rs.getString(8));
      ad.setNumero(rs.getInt(9));
      ad.setBoite(rs.getString(10));
      ad.setPays(rs.getString(11));
      ad.setRegion(rs.getString(12));
      ad.setCp(rs.getString(13));
      ad.setVille(rs.getString(14));

      part.setEmail(rs.getString(15));
      part.setSiteWeb(rs.getString(16));
      part.setTelephonne(rs.getString(17));
      part.setAjoutParEleve(rs.getBoolean(18));
      part.setVersion(rs.getInt(19));
      part.setAdresse(ad);
      part.setDispo(rs.getBoolean(20));
      return part;
    } catch (SQLException excep) {
      throw new InternalError();
    }
  }

  @Override
  public PartenaireDto selectById(PartenaireDto partenaire) {
    PreparedStatement ps = this.access.prepare(SELECT_STATEMENT);
    try {
      ps.setInt(1, partenaire.getId());

      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return lectureResultset(rs);
        } else {

          return null;
        }
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public PartenaireDto selectByName(PartenaireDto partenaire) {
    PreparedStatement ps = this.access.prepare(SELECT_STATEMENT_BYNAME);
    try {
      ps.setString(1, partenaire.getNomLegal());

      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          PartenaireDto temp = lectureResultset(rs);
          return temp;

        } else {
          return null;
        }
      }
    } catch (SQLException err) {
      err.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public boolean update(PartenaireDto partenaire) {
    PreparedStatement ps = this.access.prepare(UPDATE_STATEMENT);
    try {
      ps.setString(1, partenaire.getNomLegal());
      ps.setString(2, partenaire.getNomAffaire());
      ps.setString(3, partenaire.getNomComplet());
      ps.setString(4, partenaire.getDepartement());
      ps.setString(5, "" + partenaire.getTypeOrganisation());
      ps.setInt(6, partenaire.getNbEmploye());
      ps.setString(7, partenaire.getAdresse().getRue());
      ps.setInt(8, partenaire.getAdresse().getNumero());
      ps.setString(9, partenaire.getAdresse().getBoite());
      ps.setString(10, partenaire.getAdresse().getPays());
      ps.setString(11, partenaire.getAdresse().getRegion());
      ps.setString(12, partenaire.getAdresse().getCp());
      ps.setString(13, partenaire.getAdresse().getVille());
      ps.setString(14, partenaire.getEmail());
      ps.setString(15, partenaire.getSiteWeb());
      ps.setString(16, partenaire.getTelephonne());
      ps.setBoolean(17, partenaire.getAjoutParEleve());
      ps.setInt(18, partenaire.getVersion() + 1);
      ps.setBoolean(19, partenaire.isDispo());
      ps.setInt(20, partenaire.getId());
      ps.setInt(21, partenaire.getVersion());
      ps.execute();
      return true;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public void delete(PartenaireDto partenaire) {
    PreparedStatement ps = this.access.prepare(DELETE_STATEMENT);
    try {
      ps.setInt(1, partenaire.getId());
      ps.setInt(2, partenaire.getVersion());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          return;
        }
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<PartenaireDto> selectNames() {
    PreparedStatement ps = this.access.prepare(SELECT_ALL_STATEMENT);
    List<PartenaireDto> results = new ArrayList<PartenaireDto>();
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        results.add(lectureResultset(rs));
      }
      return results;
    } catch (SQLException excep) {

      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public PartenaireDto selectId(String name) {
    PreparedStatement ps = this.access.prepare(SELECT_ID_STATEMENT);
    PartenaireDto partenaire = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    try {
      ps.setString(1, name);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        partenaire.setId(rs.getInt(1));
      }
      return partenaire;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  public PartenaireDto selectAjout(PartenaireDto part) {
    PreparedStatement ps = this.access.prepare(SELECT_AJOUT);
    PartenaireDto partenaire = (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
    try {
      ps.setString(1, part.getNomLegal());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        part.setAjoutParEleve(rs.getBoolean(1));
      }
      return partenaire;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<PartenaireDto> selectPartenairesDispos() {
    PreparedStatement ps = this.access.prepare(SELECT_DISPO);
    List<PartenaireDto> liste = new ArrayList<>();
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        liste.add(lectureResultset(rs));
      }
      return liste;
    } catch (SQLException exe) {
      exe.printStackTrace();
      LOG.error(exe.getMessage());
      throw new InternalError(exe.getSQLState());
    }
  }

  @Override
  public List<PartenaireDto> selectPartenairesSupprimes() {
    PreparedStatement ps = this.access.prepare(SELECT_SUPPRIME);
    List<PartenaireDto> liste = new ArrayList<>();
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        liste.add(lectureResultset(rs));
      }
      return liste;
    } catch (SQLException excep) {
      excep.printStackTrace();
      LOG.error(excep.getMessage());
      throw new InternalError(excep.getSQLState());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see dao.interfaces.PartenaireDao#selectByNameAndStad(java.lang.String, java.lang.String)
   * Mauvais paramètres !!!! DTO !!!!!
   */

  // TODO

  /*
   * (non-Javadoc)
   * 
   * @see dao.interfaces.PartenaireDao#selectByNameAndStad(java.lang.String, java.lang.String)
   * Mauvais paramètres !!!! DTO !!!!!
   */

  // TODO
}
