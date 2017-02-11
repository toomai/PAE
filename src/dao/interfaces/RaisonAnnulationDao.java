package dao.interfaces;


import objects.interfaces.dto.RaisonAnnulationDto;

import java.util.List;

public interface RaisonAnnulationDao {
  boolean insert(RaisonAnnulationDto annulation);

  List<RaisonAnnulationDto> selectAll();

  RaisonAnnulationDto selectById(RaisonAnnulationDto raison);


}
