package dao.model;

import dal.DalAccessServices;
import dao.interfaces.MobiliteDao;
import enumere.Etat;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.MobiliteDto;
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

public class MobiliteDaoImpl implements MobiliteDao {
  static final String INSERT_STATEMENT = "INSERT INTO mobipl.mobilites "
      + "(choix, etudiant, partenaire, periode, type_demande,date_debut,date_fin, etat, version) "
      + "VALUES(?,?,?,?,?,?,?,?, 0)";
  static final String SELECT_ALL_STATEMENT =
      "SELECT m.id_mobilite, m.choix, m.etudiant, m.partenaire, m.raison_annulation,"
          + " m.periode, m.type_demande, "
          + "m.date_debut, m.date_fin, m.etat, m.version FROM mobipl.mobilites m";
  static final String SELECT_STATEMENT = "SELECT m.id_mobilite, m.choix, m.etudiant,"
      + " m.partenaire, m.raison_annulation, m.periode, m.type_demande, "
      + "m.date_debut, m.date_fin, m.etat, m.version FROM mobipl.mobilites m WHERE"
      + " m.periode = ? OR m.etat= ?";
  static final String SELECT_BY_USER = "SELECT m.id_mobilite, m.choix, m.etudiant,"
      + " m.partenaire, m.raison_annulation, m.periode, m.type_demande, "
      + "m.date_debut, m.date_fin, m.etat, m.version FROM mobipl.mobilites m WHERE m.etudiant = ?";
  static final String SELECT_ID_STATEMENT =
      "SELECT m.id_mobilite, m.choix, m.etudiant, m.partenaire,"
          + " m.raison_annulation, m.periode, m.type_demande, "
          + "m.date_debut, m.date_fin, m.etat, m.version FROM mobipl.mobilites m WHERE m.id_mobilite=?";
  static final String SELECT_BY_CHOIX = "SELECT m.id_mobilite, m.choix, m.etudiant, m.partenaire,"
      + " m.raison_annulation, m.periode, m.type_demande, "
      + "m.date_debut, m.date_fin, m.etat, m.version FROM mobipl.mobilites m WHERE m.choix = ?";
  static final String UPDATE_STATEMENT = "UPDATE mobipl.mobilites SET choix = ?, etudiant = ?,"
      + " partenaire = ?, raison_annulation =?, periode =?"
      + "type_demande= ?, date_debut = ?, date_fin = ?,"
      + " version = ? where id_mobilite = ? and version = ?";

  static final String UPDATE_STATEMENT_ANNULATION =
      "UPDATE mobipl.mobilites SET raison_annulation = ?, etat = ? , version = ? where id_mobilite "
          + "= ? and version = ?";
  static final String UPDATE_ETAT_STATEMENT =
      "UPDATE mobipl.mobilites set etat= ? , version = ? where id_mobilite = ? and version = ?";

  static final String DELETE_STATEMENT = "DELETE FROM mobipl.mobilites WHERE id_mobilite =?";

  static final String SELECT_BY_USER_PARTNER_STATEMENT =
      "SELECT m.* from mobipl.mobilites m" + "where m.etudiant = ? and m.partenaire = ?";
  static final String SELECT_PAIEMENTS_MOBILITE_STATEMENT =
      "SELECT m.id_mobilite, m.etudiant, m.partenaire,"
          + "m.periode, m.type_demande, m.etat From mobipl.mobilites m where "
          + "m.etat = ? or m.etat = ?";
  static final String SELECT_BY_PARTENAIRE =
      "SELECT * FROM mobipl.mobilites m WHERE m.partenaire = ? AND (m.etat NOT LIKE 'PAYEE' AND m.etat NOT LIKE 'ANNULEE')";

  private DalAccessServices access;
  private BizzFactory factory;
  static final Logger LOG = LogManager.getLogger(MobiliteDao.class);

  public MobiliteDaoImpl(DalAccessServices access, BizzFactory factory) {
    this.access = access;
    this.factory = factory;
  }

  /*
   * Mettre cette méthode en boolean ? Plus judicieux pour savoir si la requete à réussi... Ne
   * renvoie une exception que en cas de problème de DB, pas si l'insertion rate ?
   */


  public boolean selectByPartenaire(PartenaireDto partenaire) {
    PreparedStatement ps = this.access.prepare(SELECT_BY_PARTENAIRE);
    try {
      ps.setInt(1, partenaire.getId());
      ResultSet rs = ps.executeQuery();
      return rs.next();
    } catch (SQLException exec) {
      LOG.error(exec.getSQLState());
      throw new InternalError(exec.getMessage());
    }
  }

  @Override
  public void insert(MobiliteDto mobilite) {

    PreparedStatement ps = this.access.prepare(INSERT_STATEMENT);
    try {
      ps.setInt(1, mobilite.getChoix());
      ps.setString(2, mobilite.getEtudiant().getPseudo());
      ps.setInt(3, mobilite.getPartenaire().getId());
      ps.setString(4, mobilite.getPeriode());
      ps.setString(5, "" + mobilite.getType());
      ps.setDate(6, Date.valueOf(mobilite.getDateDebut()));
      ps.setDate(7, Date.valueOf(mobilite.getDateFin()));
      ps.setString(8, "" + mobilite.getEtat());
      ps.execute();
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  /*
   * TODO: enuméré (non-Javadoc)
   * 
   * @see dao.interfaces.MobiliteDao#selectAll()
   */
  @Override
  public List<MobiliteDto> selectAll() {
    List<MobiliteDto> aret = new ArrayList<>();
    PreparedStatement ps = this.access.prepare(SELECT_ALL_STATEMENT);
    try (ResultSet rs = ps.executeQuery()) {
      if (rs.next()) {
        do {
          MobiliteDto mobilite = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
          mobilite.setId(rs.getInt(1));
          // mobilite.setChoix(factory.getDemandeDto().set)
          UtilisateurDto util =
              (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
          util.setPseudo(rs.getString(3));
          mobilite.setEtudiant(util);
          PartenaireDto partenaire =
              (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
          partenaire.setId(4);
          mobilite.setPartenaire(partenaire);
          // mobilite.setRaisonAnullatino(rs.get);
          mobilite.setPeriode(rs.getString(6));
          mobilite.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(7)));
          mobilite.setDateDebut(rs.getDate(8).toLocalDate());
          mobilite.setDateFin(rs.getDate(9).toLocalDate());
          mobilite.setEtat(Comparaison.compareEtat(rs.getString(10)));
          mobilite.setVersion(rs.getInt(11));
          aret.add(mobilite);
        } while (rs.next());
        return aret;
      } else {
        return null;
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  /*
   * TODO énumré réglé problème etat (non-Javadoc)
   * 
   * @see dao.interfaces.MobiliteDao#select(objects.interfaces.MobiliteDto)
   */
  public List<MobiliteDto> select(MobiliteDto mobilite) {
    List<MobiliteDto> aret = new ArrayList<>();
    PreparedStatement ps = this.access.prepare(SELECT_STATEMENT);
    try {
      ps.setString(1, mobilite.getPeriode());
      ps.setObject(2, mobilite.getEtat());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          do {
            MobiliteDto mobiliteAInserer =
                (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
            mobiliteAInserer.setId(rs.getInt(1));
            // mobilite.setChoix(factory.getDemandeDto().set)
            UtilisateurDto util =
                (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
            util.setPseudo(rs.getString(5));
            mobiliteAInserer.setEtudiant(util);
            // mobilite.setPartenaire(rs.)
            // mobilite.setRaisonAnullatino(rs.get);
            mobiliteAInserer.setPeriode(rs.getString(6));
            mobiliteAInserer.setDateDebut(rs.getDate(7).toLocalDate());
            mobiliteAInserer.setDateFin(rs.getDate(8).toLocalDate());
            mobiliteAInserer.setEtat(Comparaison.compareEtat(rs.getString(9)));
            mobiliteAInserer.setVersion(rs.getInt(10));
            aret.add(mobiliteAInserer);
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

  @Override
  public List<MobiliteDto> selectParUtilisateur(UtilisateurDto utilisateur) {
    try {
      List<MobiliteDto> aret = new ArrayList<>();
      PreparedStatement ps = this.access.prepare(SELECT_BY_USER);
      ps.setString(1, utilisateur.getPseudo());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          do {
            MobiliteDto mobiliteAInserer =
                (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
            mobiliteAInserer.setId(rs.getInt(1));
            mobiliteAInserer.setChoix(rs.getInt(2));
            UtilisateurDto util =
                (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
            util.setPseudo(rs.getString(3));
            mobiliteAInserer.setEtudiant(util);
            PartenaireDto partenaire =
                (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
            partenaire.setId(rs.getInt(4));
            mobiliteAInserer.setPartenaire(partenaire);
            // mobilite.setRaisonAnullatino(rs.get);
            mobiliteAInserer.setPeriode(rs.getString(6));
            mobiliteAInserer.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(7)));
            mobiliteAInserer.setDateDebut(rs.getDate(8).toLocalDate());
            mobiliteAInserer.setDateFin(rs.getDate(9).toLocalDate());
            mobiliteAInserer.setEtat(Comparaison.compareEtat(rs.getString(10)));
            mobiliteAInserer.setVersion(rs.getInt(11));
            aret.add(mobiliteAInserer);
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

  @Override
  public void update(MobiliteDto mobilite) throws SQLException {
    PreparedStatement ps = this.access.prepare(UPDATE_STATEMENT);
    ps.setInt(1, mobilite.getChoix());
    try {
      ps.setString(2, mobilite.getEtudiant().getPseudo());
      ps.setInt(3, mobilite.getPartenaire().getId());
      ps.setInt(4, mobilite.getAnnulationId());
      ps.setString(6, "" + mobilite.getType());
      ps.setString(5, mobilite.getPeriode());
      ps.setDate(7, Date.valueOf(mobilite.getDateDebut()));
      ps.setDate(8, Date.valueOf(mobilite.getDateFin()));
      ps.setString(9, "" + mobilite.getEtat());
      ps.setInt(10, mobilite.getVersion() + 1);
      ps.setInt(11, mobilite.getId());
      ps.setInt(12, mobilite.getVersion());
      ps.executeUpdate();
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public void delete(MobiliteDto mobilite) {
    PreparedStatement ps = this.access.prepare(DELETE_STATEMENT);
    try {
      ps.setInt(1, mobilite.getId());
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



  public MobiliteDto selectByChoix(MobiliteDto mobilite) {
    PreparedStatement ps = this.access.prepare(SELECT_BY_CHOIX);
    try {
      ps.setInt(1, mobilite.getChoix());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        UtilisateurDto util = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
        mobilite.setId(rs.getInt(1));
        util.setPseudo(rs.getString(3));
        mobilite.setEtudiant(util);
        PartenaireDto partenaire =
            (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
        partenaire.setId(rs.getInt(4));
        mobilite.setPartenaire(partenaire);
        mobilite.setAnnulationId(5);
        mobilite.setPeriode(rs.getString(6));
        mobilite.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(7)));
        if (rs.getDate(8) != null) {
          mobilite.setDateDebut(rs.getDate(8).toLocalDate());
          mobilite.setDateFin(rs.getDate(9).toLocalDate());
        }
        mobilite.setEtat(Comparaison.compareEtat(rs.getString(10)));
        mobilite.setVersion(rs.getInt(11));
        return mobilite;
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      LOG.error(e.getSQLState());
      throw new InternalError();
    }
  }

  @Override
  public MobiliteDto selectById(MobiliteDto mobi) {
    PreparedStatement ps = this.access.prepare(SELECT_ID_STATEMENT);
    try {
      int id = mobi.getId();
      ps.setInt(1, id);
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          MobiliteDto mobilite = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
          mobilite.setId(id);
          UtilisateurDto util =
              (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
          util.setPseudo(rs.getString(3));
          mobilite.setEtudiant(util);
          PartenaireDto partenaire =
              (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
          partenaire.setId(rs.getInt(4));
          mobilite.setPartenaire(partenaire);
          mobilite.setAnnulationId(5);
          mobilite.setPeriode(rs.getString(6));
          mobilite.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(7)));
          if (rs.getDate(8) != null) {
            mobilite.setDateDebut(rs.getDate(8).toLocalDate());
            mobilite.setDateFin(rs.getDate(9).toLocalDate());
          }
          mobilite.setEtat(Comparaison.compareEtat(rs.getString(10)));
          mobilite.setVersion(rs.getInt(11));
          return mobilite;
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
  public MobiliteDto selectByUserAndPartner(String user, int idPartenaire) {
    PreparedStatement ps = this.access.prepare(SELECT_BY_USER_PARTNER_STATEMENT);
    MobiliteDto mobilite = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
    try {
      ps.setString(1, user);
      ps.setInt(2, idPartenaire);
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          mobilite.setId(rs.getInt(1));
          UtilisateurDto utilisateur =
              (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
          utilisateur.setPseudo(rs.getString(3));
          mobilite.setEtudiant(utilisateur);
          mobilite.setPeriode(rs.getString(6));
          PartenaireDto partenaire =
              (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
          partenaire.setId(rs.getInt(4));
          mobilite.setPartenaire(partenaire);
          mobilite.setEtat(Comparaison.compareEtat(rs.getString(7)));
          mobilite.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(8)));
          mobilite.setDateDebut(rs.getDate(9).toLocalDate());
          mobilite.setDateFin(rs.getDate(10).toLocalDate());
        }
        return mobilite;
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<MobiliteDto> getAllMobilitesPaiementDemande() {
    List<MobiliteDto> aret = new ArrayList<MobiliteDto>();
    try {
      PreparedStatement ps = this.access.prepare(SELECT_PAIEMENTS_MOBILITE_STATEMENT);
      ps.setString(1, "" + Etat.A_PAYER);
      ps.setString(2, "" + Etat.SOLDE);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        MobiliteDto mobilite = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
        mobilite.setId(rs.getInt(1));
        UtilisateurDto etudiant =
            (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
        etudiant.setPseudo(rs.getString(2));
        mobilite.setEtudiant(etudiant);
        PartenaireDto partenaire =
            (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
        partenaire.setId(rs.getInt(3));
        mobilite.setPartenaire(partenaire);
        mobilite.setPeriode(rs.getString(4));
        mobilite.setTypeDemande(Comparaison.compareTypeDemande(rs.getString(5)));
        mobilite.setEtat(Comparaison.compareEtat(rs.getString(6)));
        aret.add(mobilite);
      }
      return aret;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public MobiliteDto selectByUser(UtilisateurDto utilisateur) {
    PreparedStatement ps = this.access.prepare(SELECT_BY_USER);
    try {
      ps.setString(1, utilisateur.getPseudo());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        MobiliteDto mobilite = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
        mobilite.setId(rs.getInt(1));
        UtilisateurDto user = (UtilisateurDto) factory.getDto(UtilisateurDto.class.getSimpleName());
        user.setPseudo(rs.getString(3));
        mobilite.setEtudiant(user);
        PartenaireDto partenaire =
            (PartenaireDto) factory.getDto(PartenaireDto.class.getSimpleName());
        partenaire.setId(rs.getInt(4));
        mobilite.setPartenaire(partenaire);
        mobilite.setPeriode(rs.getString(5));
        mobilite.setVersion(rs.getInt(11));
        return mobilite;
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
    return null;
  }

  @Override
  public void updateEtat(MobiliteDto mobilite) {
    PreparedStatement ps = this.access.prepare(UPDATE_ETAT_STATEMENT);
    try {
      ps.setString(1, "" + mobilite.getEtat());
      ps.setInt(2, mobilite.getVersion() + 1);
      ps.setInt(3, mobilite.getId());
      ps.setInt(4, mobilite.getVersion());
      ps.executeUpdate();
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  public void updateAnnulation(MobiliteDto mobilite) {
    PreparedStatement ps = this.access.prepare(UPDATE_STATEMENT_ANNULATION);
    try {
      ps.setInt(1, mobilite.getAnnulationId());

      ps.setString(2, "" + mobilite.getEtat());
      ps.setInt(3, mobilite.getVersion() + 1);
      ps.setInt(4, mobilite.getId());
      ps.setInt(5, mobilite.getVersion());

      ps.executeUpdate();

    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }

  }
}
