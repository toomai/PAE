package objects.models;

import dao.interfaces.DossierDao;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.DossierDto;
import objects.interfaces.dto.MobiliteDto;
import objects.model.BizzFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MockupDossierDao implements DossierDao {

	private BizzFactory factory = new BizzFactoryImpl();

	@Override
	public void insert(DossierDto dossier) {
	}

	@Override
	public List<DossierDto> selectAll() {
		List<DossierDto> aReturn = new ArrayList<>();
		return aReturn;
	}

	@Override
	public DossierDto select(DossierDto dossier) {
		DossierDto ret = (DossierDto) factory.getDto(DossierDto.class
				.getSimpleName());
		MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
		mobiliteDto.setId(1);
		ret.setMobilite(mobiliteDto);
		if (ret.getMobilite().getId() == dossier.getMobilite().getId()) {
			return ret;
		} else {
			return null;
		}
	}

	@Override
	public void update(DossierDto dossier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(DossierDto dossier) {
		// TODO Auto-generated method stub

	}

	@Override
	public DossierDto selectByMobi(MobiliteDto mobilite) {
		DossierDto ret = (DossierDto) factory.getDto(DossierDto.class
				.getSimpleName());
		MobiliteDto mobiliteDto = (MobiliteDto) factory.getDto(MobiliteDto.class.getSimpleName());
		mobiliteDto.setId(1);
		ret.setMobilite(mobiliteDto);
		if (ret.getMobilite().getId() == mobilite.getId()) {
			return ret;
		} else {
			return null;
		}
	}

}
