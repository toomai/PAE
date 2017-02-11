package objects.model;

import objects.interfaces.bizz.RaisonAnnulationBizz;
import objects.interfaces.dto.RaisonAnnulationDto;
import util.Util;

public class RaisonAnnulationImpl implements RaisonAnnulationDto, RaisonAnnulationBizz {

  private String raison;
  private int id;
  private int version;


  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getRaison() {
    return raison;
  }

  public int getId() {
    return id;
  }


  public void setRaison(String raison) {
    this.raison = raison;
  }

  @Override
  public void setid(int id) {
    this.id = id;
  }

  @Override
  public boolean readyInsert() {
    boolean aRet = raison != null && id > 0;
    return aRet;
  }

  @Override
  public boolean checkParam() {
    try {
      Util.checkPositiveOu0(id);
      Util.checkStringSize(raison, 50);
    } catch (IllegalArgumentException excep) {
      return false;
    }
    return true;
  }


}
