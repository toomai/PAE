package dao.model;

import dal.DalAccessServices;
import dao.interfaces.RaisonAnnulationDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.RaisonAnnulationDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RaisonAnnulationDaoImpl implements RaisonAnnulationDao {

  private DalAccessServices access;
  private BizzFactory factory;

  static final String INSERT_STATEMENT =
      "INSERT INTO mobipl.raison_annulations" + "(raison,version)" + "VALUES(?,0)";
  static final String SELECT_ALL_STATEMENT =
      "SELECT r.id_raison,r.raison,r.version FROM mobipl.raison_annulations r";
  static final String SELECT_ID =
      "SELECT r.id_raison,r.raison,r.version FROM mobipl.raison_annulations r where r.raison=?";



  public RaisonAnnulationDaoImpl(DalAccessServices access, BizzFactory factory) {
    this.access = access;
    this.factory = factory;
  }

  @Override
  public boolean insert(RaisonAnnulationDto annulation) {
    try {
      PreparedStatement ps = this.access.prepare(INSERT_STATEMENT);
      ps.setString(1, annulation.getRaison());

      ps.execute();
      return true;
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();
    }
  }

  @Override
  public List<RaisonAnnulationDto> selectAll() {
    List<RaisonAnnulationDto> aret = new ArrayList<>();
    PreparedStatement ps = this.access.prepare(SELECT_ALL_STATEMENT);
    try (ResultSet rs = ps.executeQuery()) {
      if (rs.next()) {
        do {
          RaisonAnnulationDto raison =
              (RaisonAnnulationDto) factory.getDto(RaisonAnnulationDto.class.getSimpleName());
          raison.setid(rs.getInt(1));
          raison.setRaison(rs.getString(2));
          raison.setVersion(rs.getInt(3));
          aret.add(raison);
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
  public RaisonAnnulationDto selectById(RaisonAnnulationDto raison) {
    PreparedStatement ps = this.access.prepare(SELECT_ID);
    try {
      ps.setString(1, raison.getRaison());
      RaisonAnnulationDto raisn =
          (RaisonAnnulationDto) factory.getDto(RaisonAnnulationDto.class.getSimpleName());
      try (ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
          raisn.setid(rs.getInt(1));
          raisn.setRaison(rs.getString(2));
          raisn.setVersion(rs.getInt(3));
          return raisn;
        } else {
          return null;
        }
      }
    } catch (SQLException excep) {
      excep.printStackTrace();
      throw new InternalError();

    }
  }
}
