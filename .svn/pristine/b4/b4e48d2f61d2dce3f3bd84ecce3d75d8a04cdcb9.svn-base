package objects.models;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import objects.model.BizzFactoryImpl;
import dao.interfaces.RaisonAnnulationDao;
import objects.controller.interfaces.RaisonAnnulationUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.RaisonAnnulationDto;

public class RaisonAnnulationUcControllerTest {
	BizzFactory factory = new BizzFactoryImpl();
	BizzFactory mockFactory = new MockupFactory();
	RaisonAnnulationDto raisonDto = (RaisonAnnulationDto) factory
			.getDto(RaisonAnnulationDto.class.getSimpleName());
	RaisonAnnulationUcController raisonController = mockFactory
			.getRaisonAnnulationUcController();

	// test insertion raison annulation
	@Test
	public void encoderAnnulation1() {
		raisonDto.setRaison("maladie");
		boolean ret = raisonController.encoderAnnulation(raisonDto);
		assertTrue(ret);
	}

	// test insertion raison annulation null
	@Test
	public void encoderAnnulation2() {
		boolean ret = raisonController.encoderAnnulation(raisonDto);
		assertTrue(ret);
	}

	// test select all.
	@Test
	public void selectAll1() {
		List<RaisonAnnulationDto> ret = raisonController.selectAll();
		assertNotNull(ret);
	}
	
	//test select raison avec raison existante
	@Test
	public void selectRaison1(){
		raisonDto.setRaison("mort");
		RaisonAnnulationDto ret = raisonController.selectRaison(raisonDto);
		assertNotNull(ret);
	}
	
	//test select raison avec raison inexistante
		@Test
		public void selectRaison2(){
			raisonDto.setRaison("morte");
			RaisonAnnulationDto ret = raisonController.selectRaison(raisonDto);
			assertNull(ret);
		}
}
