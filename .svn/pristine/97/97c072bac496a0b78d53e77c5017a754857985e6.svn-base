package dao.model;

import dal.DalAccessServices;
import dao.interfaces.DemandeDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import util.Comparaison;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class DemandeDaoImpl implements DemandeDao {
  static final String INSERT_STATEMENT =
      "INSERT INTO mobipl.demandes (etudiant, partenaire, type_mobilite,ordre_preference, "
          + "type_demande,periode ,date_introduction, annee_academique,version) VALUES(?,?,?,?,"
          + "?,?,?,?,0)";
  static final String SELECT_ALL_STATEMENT =
      "SELECT d.id_demande,d.etudiant,d.partenaire, d.type_mobilite,d.ordre_preference,"
          + "d.type_demande, d.periode,d.date_introduction,d.annee_academique,d.version FROM"
          + " mobipl.demandes d";
  static final String SELECT_ID_STATEMENT =
      "SELECT d.partenaire,d.etudiant,d.type_mobilite,d.ordre_preference,d.periode, "
          + " d.date_introduction,d.annee_academique, d.type_demande, d.version FROM mobipl.demandes d WHERE "
          + "d.id_demande=?";
  static final String SELECT_STATEMENT =
      "SELECT d.id_demande,d.etudiant,d.type_mobilite,d.ordre_preference,d.type_demande,d.periode, "
          + "d.date_introduction,d.annee_academique,d.version FROM mobipl.demandes d WHERE "
          + "d.id_demande=? ORDER BY d.ordre_preference";

  static final String SELECT_STATEMENT_BY_USER =
      "SELECT d.id_demande,d.partenaire,d.periode,d.version "
          + "FROM mobipl.demandes d WHERE d.etudiant=?";
  static final String SELECT_STATEMENT_BY_USER_FULL =
      "SELECT d.id_demande,d.etudiant,d.type_mobilite,d.ordre_preference,d.type_demande,d.periode, "
          + "d.date_introduction,d.annee_academique,d.version, d.partenaire FROM mobipl.demandes d WHERE "
          + "d.etudiant=? AND d.id_demande NOT IN (SELECT m.choix FROM mobipl.mobilites m)";
  static final String UPDATE_STATEMENT =
      "UPDATE mobipl.demandes SET etudiant = ?, partenaire = ?, type_mobilite = ?,"
          + " ordre_preference =?,type= ?, periode = ?, date_introduction = ?, "
          + "annee_academique = ?, version=? WHERE id_demande = ? and version=?";
  static final String DELETE_STATEMENT = "DELETE FROM mobipl.demandes WHERE id_demande =?";
  static final Logger LOG = LogManager.getLogger(DemandeDao.class);

  private DalAccessServices access;
  private BizzFactory factory;


  /*
   * construct. @param dalaccess, @param factory.
   */
  public DemandeDaoImpl(DalAccessServices access, BizzFactory factory) {
    this.access = access;
    this.factory = factory;
  }

  @Override
  public void insert(DemandeDto demande) {
    PreparedStatement ps = this.access.prepare(INSERT_STATEMENT);
    try {
      ps.setString(1, demande.getEtudiant().getPseudo());
      PartenaireDto partenaire = demande.getPartenaire();

      if (partenaire != null) {
        ps.setInt(2, partenaire.getId());
      } else {
        ps.setNull(2, 0);
      }
      ps.setString(3, "" + demande.getTypeMobilite());
      ps.setInt(4, demande.getOrdrePreference());
      ps.setString(5, "" + demande.getType());
      ps.setString(6, demande.getPeriode());
      ps.setDate(7, Date.valueOf(demande.getDateIntroduction()));
      ps.setInt(8, demande.getAnneeAcademique());
      ps.execute();
    } catch (SQLException excep) {
      LOG.error(excep.getSQLState());
      excep.printStackTrace();
      throw new InternalError();
    }
  }



  @Override
  public List<DemandeDto> selectAll() {
    List<DemandeDto> aret = new ArrayList<>();
    PreparedStatement ps = this.access.prepare(SELECT_ALL_STATEMENT);
    try (ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        DemandeDto demande = (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());
        demande.setIdDemande(rs.getInt(1));
        UtilisateurDto util =
            (UtilisateurDto) (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
        util.setPseudo(rs.getString(2));
        demande.setEtudiant(util);
        PartenaireDto partenaire =
            (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
        partenaire.setId(rs.getInt(3));
        demande.setPartenaire(partenaire);
        demande.setTypeMobilite(Comparaison.compareTypeMobilite(rs.getString(4)));
        demande.setOrdrePreference(rs.getInt(5));
        demande.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(6)));
        demande.setPeriode(rs.getString(7));
        if (rs.getDate(8) != null) {
          demande.setDateIntroduction(rs.getDate(8).toLocalDate());
        } else {
          demande.setDateIntroduction(null);
        }
        demande.setAnneeAcademique(rs.getInt(9));
        aret.add(demande);
      }
      return aret;
    } catch (SQLException excep) {
      LOG.error(excep);
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public void update(DemandeDto demande) {
    PreparedStatement ps = this.access.prepare(UPDATE_STATEMENT);
    try {
      ps.setString(1, demande.getEtudiant().getPseudo());
      ps.setInt(2, demande.getPartenaire().getId());
      ps.setString(3, "" + demande.getTypeMobilite());
      ps.setInt(4, demande.getOrdrePreference());
      ps.setString(5, "" + demande.getType());
      ps.setString(6, demande.getPeriode());
      ps.setDate(7, Date.valueOf(demande.getDateIntroduction()));
      ps.setInt(8, demande.getAnneeAcademique());
      ps.setInt(9, demande.getVersion() + 1);
      ps.setInt(10, demande.getId_demande());
      ps.setInt(11, demande.getVersion());
      ps.execute();
    } catch (SQLException excep) {
      LOG.error(excep.getSQLState());
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public void delete(DemandeDto demandeParam) {
    PreparedStatement ps = this.access.prepare(DELETE_STATEMENT);
    int id = demandeParam.getId_demande();
    try {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException excep) {
      LOG.error(excep.getSQLState());
      excep.printStackTrace();
      throw new InternalError();
    }
  }


  @Override
  public List<DemandeDto> select(DemandeDto demande) {
    List<DemandeDto> aret = new ArrayList<>();
    PreparedStatement ps = this.access.prepare(SELECT_STATEMENT);
    try {
      ps.setInt(1, demande.getId_demande());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          do {
            DemandeDto demandeAInserer =
                (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());
            demandeAInserer.setIdDemande((rs.getInt(1)));
            UtilisateurDto util =
                (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
            util.setPseudo(rs.getString(5));
            demande.setEtudiant(util);
            demande.setTypeMobilite(Comparaison.compareTypeMobilite(rs.getString(3)));
            demande.setOrdrePreference(rs.getInt(4));
            demande.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(5)));
            demande.setPeriode(rs.getString(6));
            demande.setDateIntroduction(rs.getDate(7).toLocalDate());
            demande.setAnneeAcademique(rs.getInt(8));
            demande.setVersion(rs.getInt(9));
            aret.add(demandeAInserer);
          } while (rs.next());
          return aret;
        } else {
          return null;
        }
      }
    } catch (SQLException excep) {
      LOG.error(excep.getSQLState());
      excep.printStackTrace();
      throw new InternalError();
    }
  }



  @Override
  public DemandeDto selectById(DemandeDto demande) {
    PreparedStatement ps = this.access.prepare(SELECT_ID_STATEMENT);
    try {
      ps.setInt(1, demande.getId_demande());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          UtilisateurDto util =
              (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
          PartenaireDto parten =
              (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
          parten.setId(rs.getInt(1));
          util.setPseudo(rs.getString(2));
          demande.setEtudiant(util);
          demande.setPartenaire(parten);
          demande.setTypeMobilite(Comparaison.compareTypeMobilite(rs.getString(3)));
          demande.setOrdrePreference(rs.getInt(4));
          demande.setPeriode(rs.getString(5));
          if (rs.getDate(6) != null) {
            demande.setDateIntroduction(rs.getDate(6).toLocalDate());
          }
          demande.setAnneeAcademique(rs.getInt(7));
          demande.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(8)));
          demande.setVersion(rs.getInt(9));
          return demande;
        } else {
          return null;
        }
      }
    } catch (SQLException excep) {
      LOG.error(excep.getSQLState());
      excep.printStackTrace();
      throw new InternalError();
    }
  }



  @Override
  public List<DemandeDto> selectByUser(String pseudo) {
    PreparedStatement ps = this.access.prepare(SELECT_STATEMENT_BY_USER);
    List<DemandeDto> results = new ArrayList<DemandeDto>();
    try {
      ps.setString(1, pseudo);
      System.out.println(ps.toString());
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          DemandeDto demande = (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());
          demande.setIdDemande(rs.getInt(1));
          PartenaireDto partenaire =
              (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
          partenaire.setId(rs.getInt(2));
          demande.setPeriode(rs.getString(3));
          demande.setVersion(rs.getInt(4));

          results.add(demande);
        }
        return results;
      }
    } catch (SQLException excep) {
      LOG.error(excep.getSQLState());
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<DemandeDto> selectAllByUser(UtilisateurDto user) {
    List<DemandeDto> aret = new ArrayList<>();
    PreparedStatement ps = this.access.prepare(SELECT_STATEMENT_BY_USER_FULL);
    try {
      ps.setString(1, user.getPseudo());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          do {
            DemandeDto demandeAInserer =
                (DemandeDto) factory.getDto(DemandeDto.class.getSimpleName());
            PartenaireDto partenaire =
                (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
            demandeAInserer.setIdDemande((rs.getInt(1)));

            demandeAInserer.setEtudiant(user);
            demandeAInserer.setTypeMobilite(Comparaison.compareTypeMobilite(rs.getString(3)));
            demandeAInserer.setOrdrePreference(rs.getInt(4));
            demandeAInserer.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(5)));
            demandeAInserer.setPeriode(rs.getString(6));
            demandeAInserer.setDateIntroduction(rs.getDate(7).toLocalDate());
            demandeAInserer.setAnneeAcademique(rs.getInt(8));
            demandeAInserer.setVersion(rs.getInt(9));
            partenaire.setId(rs.getInt(10));
            demandeAInserer.setPartenaire(partenaire);
            aret.add(demandeAInserer);
          } while (rs.next());
          return aret;
        } else {
          return null;

        }
      }
    } catch (SQLException excep) {
      LOG.error(excep.getSQLState());
      excep.printStackTrace();
      throw new InternalError();
    }
  }


}
