package objects.controller.interfaces;

import objects.interfaces.dto.PaysDto;

public interface PaysUcController {
  /**
   * @param pays Recoit un pays.
   * @return paysDto.
   */
  PaysDto getPays(PaysDto pays);
}
