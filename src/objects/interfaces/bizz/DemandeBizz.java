package objects.interfaces.bizz;

import objects.interfaces.dto.DemandeDto;

public interface DemandeBizz extends DemandeDto {
  public boolean readyInsert();

  public boolean checkParam();

}
