package objects.interfaces.bizz;

import objects.interfaces.dto.RaisonAnnulationDto;

public interface RaisonAnnulationBizz extends RaisonAnnulationDto {
  public boolean readyInsert();

  public boolean checkParam();
}
