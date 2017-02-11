package objects.controller.models;

import dal.DalServices;
import dao.interfaces.PaysDao;
import objects.controller.interfaces.DossierUcController;
import objects.controller.interfaces.PaysUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.PaysDto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

public class PaysUcControllerImpl implements PaysUcController {
  private BizzFactory factory;
  private DalServices dalServices;
  private PaysDao paysDao;
  static final Logger LOG = LogManager.getLogger(DossierUcController.class);

  /**
   * constructeur vide.
   */
  public PaysUcControllerImpl() {
    factory = BizzFactory.INSTANCE;
    for (Field fil : this.getClass().getDeclaredFields()) {
      if (!fil.getType().getSimpleName().contains("Factory")) {
        System.out.println("C'est vide");
      }
    }
  }

  /**
   * @param factory.
   * @param dalServices.
   * @param paysDao.
   */
  public PaysUcControllerImpl(BizzFactory factory, DalServices dalServices, PaysDao paysDao) {
    this.factory = factory;
    this.dalServices = dalServices;
    this.paysDao = paysDao;
  }

  @Override
  public PaysDto getPays(PaysDto pays) {
    try {
      dalServices.start();
      pays = paysDao.selectByNom(pays);
      dalServices.commit();
      dalServices.close();
      return pays;
    } catch (InternalError excep) {
      LOG.error(excep.getMessage());
      throw new InternalError();
    }
  }

}
