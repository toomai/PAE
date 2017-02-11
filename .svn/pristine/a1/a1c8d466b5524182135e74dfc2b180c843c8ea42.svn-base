package objects.models;

import java.util.ArrayList;
import java.util.List;

import objects.interfaces.BizzFactory;
import objects.interfaces.dto.RaisonAnnulationDto;
import objects.model.BizzFactoryImpl;
import dao.interfaces.RaisonAnnulationDao;

public class MockupRaisonAnnulationDao implements RaisonAnnulationDao {
	private BizzFactory factory = new BizzFactoryImpl();

	@Override
	public boolean insert(RaisonAnnulationDto annulation) {
		return true;
	}

	@Override
	public List<RaisonAnnulationDto> selectAll() {
		List<RaisonAnnulationDto> aReturn = new ArrayList<>();
		return aReturn;
	}

	@Override
	public RaisonAnnulationDto selectById(RaisonAnnulationDto raison) {
		RaisonAnnulationDto ret = (RaisonAnnulationDto) factory
				.getDto(RaisonAnnulationDto.class.getSimpleName());
		ret.setRaison("mort");
		if (ret.getRaison().equals(raison.getRaison())) {
			return ret;
		} else {
			return null;
		}
	}

}
