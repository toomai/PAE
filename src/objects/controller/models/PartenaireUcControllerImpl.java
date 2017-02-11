package objects.controller.models;

import dal.DalServices;
import dao.interfaces.MobiliteDao;
import dao.interfaces.PartenaireDao;
import objects.controller.interfaces.PartenaireUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.PartenaireDto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ConcurrentModificationException;
import java.util.List;

public class PartenaireUcControllerImpl implements PartenaireUcController {
  private BizzFactory factory;
  private DalServices dalServices;
  private PartenaireDao partenaireDao;
  private MobiliteDao mobiliteDao;
  static final Logger LOG = LogManager.getLogger(PartenaireUcController.class);

  /**
   * @param factory Recois la factory.
   * @param dalServices Recois la dal Service.
   * @param partenaireDao Recois un partenaireDao.
   */
  public PartenaireUcControllerImpl(BizzFactory factory, DalServices dalServices,
      PartenaireDao partenaireDao, MobiliteDao mobiliteDao) {
    this.factory = factory;
    this.dalServices = dalServices;
    this.partenaireDao = partenaireDao;
    this.mobiliteDao = mobiliteDao;
  }



  @Override
  public boolean encoderPartenaire(PartenaireDto partenaire) {
    try {
      dalServices.start();
      if (partenaireDao.selectByName(partenaire) != null) {
        return true;
      }
      boolean ret = partenaireDao.insert(partenaire);
      dalServices.commit();
      dalServices.close();
      return ret;
    } catch (InternalError excep) {
      dalServices.close();
      dalServices.rollBack();
    } catch (ConcurrentModificationException excep) {
      dalServices.rollBack();
      dalServices.close();
    }
    return false;
  }

  @Override
  public PartenaireDto getAjout(PartenaireDto parte) {
    try {
      dalServices.start();
      PartenaireDto partenaire = partenaireDao.selectAjout(parte);
      dalServices.commit();
      dalServices.close();
      return partenaire;
    } catch (InternalError excep) {
      dalServices.close();
      dalServices.rollBack();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dalServices.close();
      dalServices.rollBack();
    }
    return null;
  }



  @Override
  public PartenaireDto getPartenaire(PartenaireDto parte) {
    try {
      dalServices.start();
      PartenaireDto partenaire = partenaireDao.selectByName(parte);
      dalServices.commit();
      dalServices.close();
      return partenaire;
    } catch (InternalError excep) {
      System.out.println(excep.getMessage());
      dalServices.rollBack();
      dalServices.close();
      throw new InternalError();
    } catch (ConcurrentModificationException excep) {
      System.out.println(excep.getMessage());
      dalServices.close();
      dalServices.rollBack();
      throw new InternalError();
    }
  }



  @Override
  public boolean supprimerPartenaire(PartenaireDto partenaire) {
    try {
      boolean toRet = false;
      dalServices.start();

      partenaire = partenaireDao.selectById(partenaire);
      if (!mobiliteDao.selectByPartenaire(partenaire)) {
        partenaire.setDispo(false);
        partenaireDao.update(partenaire);
        toRet = true;
      } else {
        toRet = false;
      }
      dalServices.commit();
      dalServices.close();
      return toRet;
    } catch (InternalError exec) {
      dalServices.rollBack();
      dalServices.close();
      LOG.error(exec.getMessage());
      throw exec;
    }
  }



  @Override
  public List<PartenaireDto> getPartenairesDispos() {
    try {
      dalServices.start();
      List<PartenaireDto> liste = partenaireDao.selectPartenairesDispos();
      dalServices.close();
      return liste;
    } catch (InternalError exec) {
      dalServices.rollBack();
      dalServices.close();
      LOG.error(exec.getMessage());
      throw exec;
    }
  }



  @Override
  public void rehabiliterPartenaire(PartenaireDto partenaire) {
    try {
      dalServices.start();
      partenaire = partenaireDao.selectById(partenaire);
      partenaire.setDispo(true);
      partenaireDao.update(partenaire);
      dalServices.commit();
      dalServices.close();
    } catch (InternalError exec) {
      LOG.error(exec.getMessage());
      dalServices.rollBack();
      dalServices.close();
      throw exec;
    }
  }

  public List<PartenaireDto> getListePartenairesSupprimes() {
    return partenaireDao.selectPartenairesSupprimes();
  }



}
