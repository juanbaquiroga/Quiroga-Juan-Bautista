package test;


import dao.Impl.OdontologoDaoH2;
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

class OdontologoServiceTest {
    static Logger logger = Logger.getLogger(OdontologoServiceTest.class);
    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void cargarTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./parcial;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

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
        List<Odontologo> odontologos = new ArrayList<>();
        //cuando
        odontologos = odontologoService.buscarTodos();
        //entonces
        assertFalse(odontologos.isEmpty());
    }
}