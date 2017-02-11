package objects.controller.interfaces;

import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;
import objects.interfaces.dto.UtilisateurDto;

import java.util.List;

public interface DossierUcController {
  /**
   * @param utilisateur Recoit un utilisateur.
   * @return List Renvoie un liste de dossierDto.
   */
  List<DossierDto> getDossiersParEtudiant(UtilisateurDto utilisateur);

  /**
   * @return List Renvoie une liste de dossierDto.
   */
  List<DossierDto> selectAll();

  /**
   * @param mobilite Recoit une mobiliteDto.
   * @return dossierDto.
   */
  DossierDto getDossierParMobi(MobiliteDto mobilite);

  /**
   * @param dossier Recoit un dossierDto.
   * @return DossierDto.
   */
  DossierDto getDossier(DossierDto dossier);

  /**
   * @param mobilite Recoit une mobilite.
   */
  void documentsAuDepartCompletes(MobiliteDto mobilite);

  /**
   * @param mobilite Recoit une mobilite.
   */
  void documentsAuRetourCompletes(MobiliteDto mobilite);

  /**
   * @param mobilite Recoit une mobilite.
   */
  void donneesEncodeesDansLogiciels(MobiliteDto mobilite);
}
