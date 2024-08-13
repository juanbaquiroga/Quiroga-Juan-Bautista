package test;

import dao.Impl.OdontologoDaoH2;
import dao.Impl.OdontologoDaoMemoria;
import model.Odontologo;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OdontologoServiceMemoriaTest {
    static Logger logger = Logger.getLogger(test.OdontologoServiceMemoriaTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoMemoria());

    @Test
    @DisplayName("Testeamos que el odontologo se guarde en la db")
    void caso1(){
        //dado
        Odontologo odontologo = new Odontologo("h73889", "mariano", "closs");
        //cuando
        Odontologo odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);
        //entonces
        assertNotNull(odontologoDesdeDB.getId());

    }

    @Test
    @DisplayName("Testeamos que traiga a todos los odontologos")
    void caso2(){
//      //dado
        Odontologo odontologo1 = new Odontologo("b1234", "juan", "perez");
        Odontologo odontologo2 = new Odontologo("c5678", "mario", "gomez");
        Odontologo odontologoDesdeDB1 = odontologoService.guardarOdontologo(odontologo1);
        Odontologo odontologoDesdeDB2 = odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = new ArrayList<>();
        //cuando
        odontologos = odontologoService.buscarTodos();
        //entonces
        assertFalse(odontologos.isEmpty());
    }
}
