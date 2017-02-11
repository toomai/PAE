package projet;

import dal.DalServices;
import objects.interfaces.BizzFactory;
import servlets.DispatchServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;



public class Main {
  private static BizzFactory factory = BizzFactory.INSTANCE;


  private static final Logger LOG = LogManager.getLogger(Main.class);

  /**
   * @param args.0
   * @throws Exception lance l'app.
   */
  public static void main(String[] args) throws Exception {
    try {
      DalServices dalServices = factory.getDalServices();


      WebAppContext context = new WebAppContext();
      context.setContextPath("/");

      context.addServlet(new ServletHolder(new DefaultServlet()), "/");
      context.addServlet(new ServletHolder(new DispatchServlet(factory.getUtilisateurUcController(),
          factory.getRechercheUcController(), factory, factory.getMobiliteUcController(),
          factory.getDemandeUcController(), factory.getPartenaireUcController(),
          factory.getDossierUcController(), factory.getRaisonAnnulationUcController(),
          factory.getPaysUcController())), "/app");

      context.setContextPath("/");
      context.setWelcomeFiles(new String[] {"/Webcontent/index.html"});
      context.setResourceBase(".");
      context.setInitParameter("cacheControl", "no-store,no-cache, must-revalidate");
      context.setClassLoader(Thread.currentThread().getContextClassLoader());
      context.setMaxFormContentSize(50000000);

      Server server = new Server(8080);
      server.setHandler(context);
      server.start();
    } catch (Throwable excep) {
      excep.printStackTrace();
      LOG.fatal("Main qui ne démarre pas: " + excep.getMessage());
      throw new Exception();
    }
  }
}
