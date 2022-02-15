package app.controller;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.mappers.dto.TestDto;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Luís Araújo
 */
public class ImportClinicalTestsControllerTest {
    private ImportClinicalTestsController importClinicalTestsController;
    @Before
    public void setUp() throws Exception {
        importClinicalTestsController=new ImportClinicalTestsController();
    }

    @Test
    public void getClientsFailure() {
        List<String[]> failure=new ArrayList<>();
        failure = importClinicalTestsController.getClientsFailure();
        assertNotNull(failure);
    }

    @Test
    public void saveData() {
        boolean result=importClinicalTestsController.saveData();
        //Assert
        assertTrue(result);
    }
}