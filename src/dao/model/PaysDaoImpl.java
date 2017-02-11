package dao.model;

import dal.DalAccessServices;
import dao.interfaces.PaysDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.PaysDto;
import util.Comparaison;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaysDaoImpl implements PaysDao {
  /*
   * static final String INSERT_STATEMENT = "INSERT INTO mobipl.pays " +
   * "(nom,type_mobilite) VALUES (?,?::type_mobilites)";
   */
  static final String SELECT_ALL_STATEMENT =
      "SELECT p.nom,p.type_mobilite,p.version " + "FROM mobipl.pays p order by p.nom asc";
  static final String SELECT_NOM_STATEMENT =
      "SELECT p.code_iso, p.nom,p.type_mobilite,p.version " + "FROM mobipl.pays p WHERE p.nom=?";
  static final String SELECT_ID_STATEMENT =
      "SELECT p.nom, p.type_mobilite, p.version FROM mobipl.pays p WHERE p.code_iso = ?";
  static final String UPDATE_STATEMENT =
      "UPDATE mobipl.pays SET type_mobilite=?,version=? WHERE nom=? and version=? ";

  private DalAccessServices access;
  private BizzFactory factory;
  static final Logger LOG = LogManager.getLogger(PaysDao.class);

  public PaysDaoImpl(DalAccessServices access, BizzFactory factory) {
    this.access = access;
    this.factory = factory;
  }

  /*
   * @Override public void insert(PaysDto pays) { try { PreparedStatement ps =
   * this.access.prepare(INSERT_STATEMENT); ps.setString(1, pays.getNom()); ps.setObject(2,
   * pays.getTypeMobilite()); ps.execute(); } catch (SQLException e) { e.printStackTrace(); throw
   * new InternalError(); } }
   */

  @Override
  public List<PaysDto> selectAll() {
    List<PaysDto> aret = new ArrayList<PaysDto>();
    PreparedStatement ps = this.access.prepare(SELECT_ALL_STATEMENT);
    try (ResultSet rs = ps.executeQuery()) {
      if (rs.next()) {
        do {
          PaysDto pays = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
          pays.setNom(rs.getString(1));
          pays.setTypeMobilite(Comparaison.compareTypeMobilite(rs.getString(2)));
          pays.setVersion(rs.getInt(3));
          aret.add(pays);
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

  public PaysDto selectById(PaysDto pays) {
    PreparedStatement ps = this.access.prepare(SELECT_ID_STATEMENT);
    try {
      ps.setString(1, pays.getCodeIso());
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        pays.setNom(rs.getString(1));
        System.out.println(rs.getString(2));
        pays.setTypeMobilite(Comparaison.compareTypeMobilite(rs.getString(2)));
        pays.setVersion(rs.getInt(3));
        return pays;
      }
      return null;
    } catch (SQLException exe) {
      exe.printStackTrace();
      LOG.error(exe.getSQLState());
      throw new InternalError();
    }
  }

  @Override
  public PaysDto selectByNom(PaysDto pays) {
    PreparedStatement ps = this.access.prepare(SELECT_NOM_STATEMENT);
    try {
      ps.setString(1, pays.getNom());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          PaysDto paysDto = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
          paysDto.setCodeIso(rs.getString(1));
          paysDto.setNom(rs.getString(2));
          paysDto.setVersion(rs.getInt(4));
          return paysDto;
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
  public void update(PaysDto pays) {
    PreparedStatement ps = this.access.prepare(UPDATE_STATEMENT);
    try {
      ps.setString(3, pays.getNom());
      ps.setInt(2, pays.getVersion() + 1);
      ps.setString(1, "" + pays.getTypeMobilite());
      ps.setInt(4, pays.getVersion());
      ps.execute();
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }
}
