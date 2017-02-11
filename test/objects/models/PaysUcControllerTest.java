package objects.models;

import objects.controller.interfaces.PaysUcController;
import objects.interfaces.BizzFactory;
import objects.interfaces.dto.PaysDto;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import objects.model.BizzFactoryImpl;

import org.junit.Test;

public class PaysUcControllerTest {
	BizzFactory factory = new BizzFactoryImpl();
	BizzFactory mockFactory = new MockupFactory();
	PaysDto paysDto = (PaysDto) factory.getDto(PaysDto.class.getSimpleName());
	PaysUcController paysController = mockFactory.getPaysUcController();
	
	//Test select by nom pays existant
	@Test
	public void selectByNom1(){
		paysDto.setNom("Belgique");
		PaysDto ret = paysController.getPays(paysDto);
		assertNotNull(ret);
	}
	
	//Test select by nom pays inexistant
		@Test
		public void selectByNom2(){
			paysDto.setNom("Belgiqué");
			PaysDto ret = paysController.getPays(paysDto);
			assertNull(ret);
		}

}
