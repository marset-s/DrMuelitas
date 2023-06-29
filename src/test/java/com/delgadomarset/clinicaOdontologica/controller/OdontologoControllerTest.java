package com.delgadomarset.clinicaOdontologica.controller;


import com.delgadomarset.clinicaOdontologica.dto.OdontologoDto;
import com.delgadomarset.clinicaOdontologica.entity.Odontologo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OdontologoControllerTest {

    @Autowired
    private OdontologoController odontologoController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void deberiaRegistarUnOdontologoMedianteApi() throws Exception {
        Odontologo odontologo = new Odontologo(
                "998888888888888",
                "Luciana",
                "Murga"
        );
        OdontologoDto odontologoDto = new OdontologoDto(
                1L,
                "998888888888888",
                "Luciana",
                "Murga"
        );

        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payload = writer.writeValueAsString(odontologo);
        String expectedResponse = writer.writeValueAsString(odontologoDto);

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos/registrar")
                        .content(String.valueOf(payload))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(content().contentType("application/json"))
                .andReturn();

        Assertions.assertEquals(expectedResponse, response.getResponse().getContentAsString());

    }

    @Test
    @Order(2)
    void deberiaEcnotrarOdontologoConIdPorApi(){
       try{
            mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/{id}", 1))
                   .andDo(print())
                   .andExpect(content().contentType("application/json"))
                   .andExpect(status().is(200))
                   .andExpect(MockMvcResultMatchers.jsonPath("$.matricula").value("998888888888888"))
                   .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Murga"));

       }catch (Exception e){
           e.printStackTrace();
       }
    }



}
