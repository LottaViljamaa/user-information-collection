package user.information.collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import user.information.collection.userInformation.BasicInformation;
import user.information.collection.userInformation.ContactInformation;
import user.information.collection.userInformation.InformationCollection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class InformationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InformationService userInformationService;

    @Autowired
    private ObjectMapper objectMapper;

    private InformationCollection mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new InformationCollection(
                new BasicInformation("Matti", "Meikäläinen", "010101-1234", "Suomi", "Mies"),
                new ContactInformation("010101-1234", "matti.meikalainen@esimerkki.com", "1234567890", "Esimerkkikatu", "Esimerkki", "00100")
        );
    }

    @Test
    public void testGetUserByPersonalIdentityCode_Success() throws Exception {
        given(userInformationService.getUserByPersonalIdentityCode("010101-1234")).willReturn(mockUser);

        mockMvc.perform(get("/users/get/010101-1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUser)));
    }

    /*
    @Test
    public void testGetUserByPersonalIdentityCode_NotFound() throws Exception {
        given(userInformationService.getUserByPersonalIdentityCode("999999-8888"))
                .willThrow(new IllegalArgumentException("User not found with the provided personal identity code."));

        mockMvc.perform(get("/users/get/999999-8888")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    String responseBody = result.getResponse().getContentAsString();
                    assertTrue(responseBody.contains("User not found with the provided personal identity code."));
                });    }


     */
}

