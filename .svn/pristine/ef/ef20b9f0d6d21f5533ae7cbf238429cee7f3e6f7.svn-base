package objects.models;

import dao.interfaces.DemandeDao;
import enumere.TypeDemande;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.DemandeDto;
import objects.interfaces.dto.PartenaireDto;
import objects.interfaces.dto.UtilisateurDto;
import objects.model.BizzFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class MockupDemandeDao implements DemandeDao {
	private BizzFactory factory = new BizzFactoryImpl();

	@Override
	public void insert(DemandeDto demande) {
	}

	@Override
	public List<DemandeDto> selectAll() {
		List<DemandeDto> aReturn = new ArrayList<>();
		return aReturn;
	}

	@Override
	public List<DemandeDto> select(DemandeDto demande) {
		List<DemandeDto> aReturn = new ArrayList<>();
		DemandeDto ret = (DemandeDto) factory.getDto(DemandeDto.class
				.getSimpleName());
		ret.setIdDemande(1);
		if (ret.getId_demande() == demande.getId_demande()) {
			aReturn.add(demande);
			return aReturn;
		} else
			return null;
	}

	@Override
	public List<DemandeDto> selectByUser(String pseudo) {
		List<DemandeDto> aReturn = new ArrayList<>();
		DemandeDto ret = (DemandeDto) factory.getDto(DemandeDto.class
				.getSimpleName());
		UtilisateurDto util = (UtilisateurDto) factory
				.getDto(UtilisateurDto.class.getSimpleName());
		PartenaireDto partenaireDto = (PartenaireDto) factory
				.getDto(PartenaireDto.class.getSimpleName());
		util.setPseudo("lol");
		ret.setEtudiant(util);
		partenaireDto.setId(1);
		ret.setPartenaire(partenaireDto);
		if (ret.getEtudiant().getPseudo().equals(pseudo)) {
			aReturn.add(ret);
			return aReturn;
		} else
			return null;
	}

	@Override
	public void update(DemandeDto demande) {
	}

	@Override
	public void delete(DemandeDto demande) {
	}

	@Override
	public DemandeDto selectById(DemandeDto demande) {
		DemandeDto ret = (DemandeDto) factory.getDto(DemandeDto.class
				.getSimpleName());
		UtilisateurDto utilisateurDto = (UtilisateurDto) factory.getDto(UtilisateurDto.class
			      .getSimpleName());
		PartenaireDto partenaireDto = (PartenaireDto) factory
				.getDto(PartenaireDto.class.getSimpleName());
		ret.setIdDemande(1);
		if (ret.getId_demande() == demande.getId_demande()) {
			return demande;
		} else
			return null;
	}

	@Override
	public List<DemandeDto> selectAllByUser(UtilisateurDto user) {
		List<DemandeDto> aReturn = new ArrayList<>();
		DemandeDto ret = (DemandeDto) factory.getDto(DemandeDto.class
				.getSimpleName());
		UtilisateurDto util = (UtilisateurDto) factory
				.getDto(UtilisateurDto.class.getSimpleName());
		PartenaireDto partenaireDto = (PartenaireDto) factory
				.getDto(PartenaireDto.class.getSimpleName());
		util.setPseudo("lol");
		partenaireDto.setId(1);
		ret.setPartenaire(partenaireDto);
		ret.setEtudiant(util);
		if (ret.getEtudiant().getPseudo().equals(user.getPseudo())) {
			aReturn.add(ret);
			return aReturn;
		} else
			return null;
	}

}
