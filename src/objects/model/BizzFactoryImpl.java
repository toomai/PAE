package objects.model;

import dal.DalAccessServices;
import dal.DalServices;
import dal.DalServicesImpl;
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
import objects.interfaces.BizzFactory;
import projet.PropertyLoader;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;

public class BizzFactoryImpl implements BizzFactory {
  private DalServicesImpl dal;
  private UtilisateurUcController utilUc;
  private RechercheUcController recherUc;
  private MobiliteUcController mobilUc;
  private DemandeUcController demandeUc;
  private PartenaireUcController partenaireUc;
  private DossierUcController dossierUc;
  private RaisonAnnulationUcController annulationUc;
  private PaysUcController paysUc;
  static final Logger LOG = LogManager.getLogger(BizzFactory.class);

  // Properties prop;
  /**
   * constructeur.
   */

  public BizzFactoryImpl() {
    try {
      BasicDataSource bds = new BasicDataSource();
      bds.setDriverClassName(PropertyLoader.getDbDriver());
      bds.setUrl(PropertyLoader.getDbUrl());
      bds.setUsername(PropertyLoader.getUserDb());
      bds.setPassword(PropertyLoader.getPasswordDb());
      bds.setInitialSize(4);
      bds.setMaxWaitMillis(2000);
      /*
       * Constructor cons = Class.forName(PropertyLoader.getDal()).getConstructor(String.class);
       * cons.setAccessible(true); this.dal = (DalServicesImpl)
       * cons.newInstance(PropertyLoader.getDbUrl());
       */
      this.dal = new DalServicesImpl(bds);
      Constructor cons = Class.forName(PropertyLoader.getImpl(UtilisateurDao.class.getSimpleName()))
          .getConstructor(DalAccessServices.class, BizzFactory.class);
      cons.setAccessible(true);
      cons = Class.forName(PropertyLoader.getImpl(UtilisateurUcController.class.getSimpleName()))
          .getConstructor(BizzFactory.class, UtilisateurDao.class, DemandeDao.class,
              DalServices.class, PartenaireDao.class);
      cons.setAccessible(true);
      UtilisateurDao utilisateurDao = (UtilisateurDao) createDaoInstance(
          PropertyLoader.getImpl(UtilisateurDao.class.getSimpleName()));
      DemandeDao demandeDao =
          (DemandeDao) createDaoInstance(PropertyLoader.getImpl(DemandeDao.class.getSimpleName()));



      cons = Class.forName(PropertyLoader.getImpl(UtilisateurUcController.class.getSimpleName()))
          .getConstructor(BizzFactory.class, UtilisateurDao.class, DemandeDao.class,
              DalServices.class, PartenaireDao.class);
      cons.setAccessible(true);
      PartenaireDao partenaireDao = (PartenaireDao) createDaoInstance(
          PropertyLoader.getImpl(PartenaireDao.class.getSimpleName()));
      this.utilUc = (UtilisateurUcController) cons.newInstance((BizzFactory) this, utilisateurDao,
          demandeDao, getDalServices(), partenaireDao);

      cons = Class.forName(PropertyLoader.getImpl(RechercheUcController.class.getSimpleName()))
          .getConstructor(DalServices.class, PartenaireDao.class, UtilisateurDao.class,
              MobiliteDao.class, PaysDao.class);
      cons.setAccessible(true);
      MobiliteDao mobiliteDao = (MobiliteDao) createDaoInstance(
          PropertyLoader.getImpl(MobiliteDao.class.getSimpleName()));
      PaysDao paysDao =
          (PaysDao) createDaoInstance(PropertyLoader.getImpl(PaysDao.class.getSimpleName()));

      this.recherUc = (RechercheUcController) cons.newInstance(getDalServices(), partenaireDao,
          utilisateurDao, mobiliteDao, paysDao);
      DossierDao dossierDao =
          (DossierDao) createDaoInstance(PropertyLoader.getImpl(DossierDao.class.getSimpleName()));
      cons = Class.forName(PropertyLoader.getImpl(DemandeUcController.class.getSimpleName()))
          .getConstructor(BizzFactory.class, DalServices.class, DemandeDao.class,
              PartenaireDao.class, UtilisateurDao.class, MobiliteDao.class, DossierDao.class,
              PaysDao.class);
      cons.setAccessible(true);
      this.demandeUc = (DemandeUcController) cons.newInstance(this, getDalServices(), demandeDao,
          partenaireDao, utilisateurDao, mobiliteDao, dossierDao, paysDao);

      cons = Class.forName(PropertyLoader.getImpl(PartenaireUcController.class.getSimpleName()))
          .getConstructor(BizzFactory.class, DalServices.class, PartenaireDao.class,
              MobiliteDao.class);
      cons.setAccessible(true);
      this.partenaireUc = (PartenaireUcController) cons.newInstance(this, getDalAccessServices(),
          partenaireDao, mobiliteDao);

      cons = Class.forName(PropertyLoader.getImpl(DossierUcController.class.getSimpleName()))
          .getConstructor(BizzFactory.class, UtilisateurDao.class, PartenaireDao.class,
              DalServices.class, DossierDao.class, MobiliteDao.class);
      cons.setAccessible(true);


      this.dossierUc = (DossierUcController) cons.newInstance(this, utilisateurDao, partenaireDao,
          getDalServices(), dossierDao, mobiliteDao);

      cons = Class.forName(PropertyLoader.getImpl(MobiliteUcController.class.getSimpleName()))
          .getConstructor(BizzFactory.class, DalServices.class, MobiliteDao.class,
              PartenaireDao.class, UtilisateurDao.class, DossierUcController.class);
      cons.setAccessible(true);
      this.mobilUc = (MobiliteUcController) cons.newInstance(this, getDalServices(), mobiliteDao,
          partenaireDao, utilisateurDao, dossierUc);


      cons =
          Class.forName(PropertyLoader.getImpl(RaisonAnnulationUcController.class.getSimpleName()))
              .getConstructor(BizzFactory.class, DalServices.class, RaisonAnnulationDao.class);
      cons.setAccessible(true);
      RaisonAnnulationDao annulationDao = (RaisonAnnulationDao) createDaoInstance(
          PropertyLoader.getImpl(RaisonAnnulationDao.class.getSimpleName()));
      this.annulationUc =
          (RaisonAnnulationUcController) cons.newInstance(this, getDalServices(), annulationDao);

      cons = Class.forName(PropertyLoader.getImpl(PaysUcController.class.getSimpleName()))
          .getConstructor(BizzFactory.class, DalServices.class, PaysDao.class);
      cons.setAccessible(true);
      this.paysUc = (PaysUcController) cons.newInstance(this, getDalServices(), paysDao);
    } catch (Throwable excep) {
      excep.printStackTrace();
      LOG.fatal("Crash introspection " + excep.getMessage());

      throw new RuntimeException();
    }
    /*
     * Pas encore fonctionnel try { prop = new Properties(); InputStream in =
     * getClass().getResourceAsStream("prod.properties"); System.out.println(in); prop.load(in);
     * in.close(); } catch (IOException e) { e.printStackTrace(); System.exit(1); }
     */
  }

  @Override
  public Object createDaoInstance(String classe) {
    try {
      Constructor cons =
          Class.forName(classe).getConstructor(DalAccessServices.class, BizzFactory.class);
      cons.setAccessible(true);
      return cons.newInstance(getDalAccessServices(), (BizzFactory) this);
    } catch (Throwable excep) {
      LOG.error("Erreur introspection : " + excep.getMessage());
      throw new RuntimeException();
    }
  }

  private Object createInstance(String classe) {
    try {
      Constructor cons = Class.forName(classe).getConstructor();
      cons.setAccessible(true);
      return cons.newInstance();
    } catch (Throwable excep) {
      LOG.error("Erreur introspection : " + excep.getMessage());
      return null;
    }
  }

  @Override
  public Object getDto(String dto) {
    return createInstance(PropertyLoader.getImpl(dto));
  }

  @Override
  public DalAccessServices getDalAccessServices() {
    return (DalAccessServices) this.dal;
  }

  @Override
  public DalServices getDalServices() {
    return (DalServices) this.dal;
  }

  @Override
  public UtilisateurUcController getUtilisateurUcController() {
    return this.utilUc;
  }

  @Override
  public RechercheUcController getRechercheUcController() {
    return this.recherUc;
  }

  @Override
  public DossierUcController getDossierUcController() {
    return this.dossierUc;
  }

  @Override
  public MobiliteUcController getMobiliteUcController() {
    return this.mobilUc;
  }

  @Override
  public RaisonAnnulationUcController getRaisonAnnulationUcController() {
    return this.annulationUc;
  }

  @Override
  public DemandeUcController getDemandeUcController() {
    return this.demandeUc;
  }

  @Override
  public PartenaireUcController getPartenaireUcController() {
    return this.partenaireUc;
  }

  @Override
  public PaysUcController getPaysUcController() {
    return this.paysUc;
  }
}
