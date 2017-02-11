package objects.controller.models;

import dal.DalServices;
import dao.interfaces.RaisonAnnulationDao;
import objects.controller.interfaces.RaisonAnnulationUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.RaisonAnnulationDto;

import java.util.ConcurrentModificationException;
import java.util.List;

public class RaisonAnnulationUcControllerImpl implements RaisonAnnulationUcController {



  private DalServices dal;
  private RaisonAnnulationDao annulation;
  private BizzFactory bizz;

  /**
   * @param dalServices.
   * @param demandeDao.
   * @param partenaireDao.
   */
  public RaisonAnnulationUcControllerImpl(BizzFactory bizz, DalServices dalServices,
      RaisonAnnulationDao annulationDao) {
    this.dal = dalServices;
    this.annulation = annulationDao;
    this.bizz = bizz;

  }

  @Override
  public boolean encoderAnnulation(RaisonAnnulationDto annulationMotif) {
    try {
      dal.start();

      boolean ret = annulation.insert(annulationMotif);
      dal.commit();
      dal.close();
      return ret;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dal.rollBack();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dal.rollBack();
    }
    dal.close();
    return false;
  }

  @Override
  public List<RaisonAnnulationDto> selectAll() {
    return annulation.selectAll();
  }

  @Override
  public RaisonAnnulationDto selectRaison(RaisonAnnulationDto raison) {

    return annulation.selectById(raison);

  }

}
