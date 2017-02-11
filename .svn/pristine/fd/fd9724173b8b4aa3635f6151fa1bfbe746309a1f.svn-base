package objects.interfaces;

import dal.DalAccessServices;
import dal.DalServices;
import objects.controller.interfaces.DemandeUcController;
import objects.controller.interfaces.DossierUcController;
import objects.controller.interfaces.MobiliteUcController;
import objects.controller.interfaces.PartenaireUcController;
import objects.controller.interfaces.PaysUcController;
import objects.controller.interfaces.RaisonAnnulationUcController;
import objects.controller.interfaces.RechercheUcController;
import objects.controller.interfaces.UtilisateurUcController;
import objects.model.BizzFactoryImpl;

public interface BizzFactory {
  public static final BizzFactory INSTANCE = new BizzFactoryImpl();

  DalAccessServices getDalAccessServices();

  DalServices getDalServices();

  UtilisateurUcController getUtilisateurUcController();

  RechercheUcController getRechercheUcController();

  MobiliteUcController getMobiliteUcController();

  DemandeUcController getDemandeUcController();

  PartenaireUcController getPartenaireUcController();

  DossierUcController getDossierUcController();

  RaisonAnnulationUcController getRaisonAnnulationUcController();

  Object createDaoInstance(String classe);

  Object getDto(String dto);

  PaysUcController getPaysUcController();
}
