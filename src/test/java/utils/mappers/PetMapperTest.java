package utils.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.jpets.controller.dtos.response.PetResponse;
import com.jpets.models.PetEntity;
import com.jpets.utils.mappers.PetMapper;

public class PetMapperTest {
    @Test
    public void shouldMapPetToDto() {
        //given
        PetEntity pet = new PetEntity("123","Chispas","path","Juan Carlos",null);
    
        //when
        PetResponse petResponse = PetMapper.INSTANCE.entityToResponse( pet );
    
        //then
        assertNotNull(petResponse);
        assertEquals(petResponse.getName(), "Chispas");
        assertEquals(petResponse.getOwnerName(), "Juan Carlos");
    }    
}
