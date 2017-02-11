package objects.models;

import dal.DalAccessServices;
import dal.DalServices;
import dao.interfaces.DemandeDao;
import dao.interfaces.DossierDao;
import dao.interfaces.MobiliteDao;
import dao.interfaces.PartenaireDao;
import dao.interfaces.PaysDao;
import dao.interfaces.RaisonAnnulationDao;
import dao.interfaces.UtilisateurDao;
import objects.controller.interfaces.DemandeUcController;
import objects.controller.interfaces.DossierUcController;
import objects.controller.interfaces.MobiliteUcController;
import objects.controller.interfaces.PartenaireUcController;
import objects.controller.interfaces.PaysUcController;
import objects.controller.interfaces.RaisonAnnulationUcController;
import objects.controller.interfaces.RechercheUcController;
import objects.controller.interfaces.UtilisateurUcController;
import objects.controller.models.DemandeUcControllerImpl;
import objects.controller.models.DossierUcControllerImpl;
import objects.controller.models.MobiliteUcControllerImpl;
import objects.controller.models.PartenaireUcControllerImpl;
import objects.controller.models.PaysUcControllerImpl;
import objects.controller.models.RaisonAnnulationUcControllerImpl;
import objects.controller.models.RechercheUcControllerImpl;
import objects.controller.models.UtilisateurUcControllerImpl;
import objects.interfaces.BizzFactory;

public class MockupFactory implements BizzFactory {

  @Override
  public Object getDto(String dto) {
    return INSTANCE.getDto(dto);
  }

  @Override
  public DalAccessServices getDalAccessServices() {
    return (DalAccessServices) new MockupDalAccessServices();
  }

  @Override
  public DalServices getDalServices() {
    return (DalServices) new MockupDalServices();
  }

  @Override
  public UtilisateurUcController getUtilisateurUcController() {
    return (UtilisateurUcController) new UtilisateurUcControllerImpl((BizzFactory) this,
        (UtilisateurDao) new MockupUtilisateurDao(), (DemandeDao) new MockupDemandeDao(),
        getDalServices(), (PartenaireDao) new MockupPartenaireDao());
  }

  @Override
  public RechercheUcController getRechercheUcController() {
    // TODO Auto-generated method stub
    return (RechercheUcController) new RechercheUcControllerImpl(getDalServices(),
        (PartenaireDao) new MockupPartenaireDao(), (UtilisateurDao) new MockupUtilisateurDao(),
        (MobiliteDao) new MockupMobiliteDao(), (PaysDao) new MockupPaysDao());
  }

  @Override
  public MobiliteUcController getMobiliteUcController() {
    // TODO Auto-generated method stub
    return (MobiliteUcController) new MobiliteUcControllerImpl((BizzFactory) this, getDalServices(),
        (MobiliteDao) new MockupMobiliteDao(), (PartenaireDao) new MockupPartenaireDao(),
        (UtilisateurDao) new MockupUtilisateurDao(), getDossierUcController());
  }

  @Override
  public DemandeUcController getDemandeUcController() {
    return (DemandeUcController) new DemandeUcControllerImpl((BizzFactory) this, getDalServices(),
        (DemandeDao) new MockupDemandeDao(), (PartenaireDao) new MockupPartenaireDao(),
        (UtilisateurDao) new MockupUtilisateurDao(), (MobiliteDao) new MockupMobiliteDao(),
        (DossierDao) new MockupDossierDao(), (PaysDao) new MockupPaysDao());
  }

  @Override
  public PartenaireUcController getPartenaireUcController() {
    return (PartenaireUcController) new PartenaireUcControllerImpl((BizzFactory) this,
        getDalServices(), (PartenaireDao) new MockupPartenaireDao(),
        (MobiliteDao) new MockupMobiliteDao());
  }

  @Override
  public Object createDaoInstance(String classe) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DossierUcController getDossierUcController() {
    // TODO Auto-generated method stub
    return (DossierUcController) new DossierUcControllerImpl((BizzFactory) this,
        (UtilisateurDao) new MockupUtilisateurDao(), (PartenaireDao) new MockupPartenaireDao(),
        getDalServices(), (DossierDao) new MockupDossierDao(),
        (MobiliteDao) new MockupMobiliteDao());
  }

  @Override
  public RaisonAnnulationUcController getRaisonAnnulationUcController() {
    // TODO Auto-generated method stub
    return (RaisonAnnulationUcController) new RaisonAnnulationUcControllerImpl((BizzFactory) this,
        getDalServices(), (RaisonAnnulationDao) new MockupRaisonAnnulationDao());
  }

  @Override
  public PaysUcController getPaysUcController() {
    // TODO Auto-generated method stub
    return (PaysUcController) new PaysUcControllerImpl((BizzFactory) this, getDalServices(),
        (PaysDao) new MockupPaysDao());
  }

}
