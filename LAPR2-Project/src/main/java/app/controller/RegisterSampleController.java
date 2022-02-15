package app.controller;

import app.domain.model.Company;
import app.domain.model.Sample;
import app.domain.model.Test;
import app.domain.shared.Constants;
import app.domain.store.SampleStore;
import app.domain.store.TestStore;
import app.mappers.TestMapper;
import app.mappers.dto.TestDto;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luís Araújo
 */
public class RegisterSampleController {
    private final SampleStore ss;
    private final Company company;
    private final App app;
    private Sample sample = new Sample();
    private Barcode barcodeUPCA;
    private TestMapper testMapper;
    private TestStore testStore;
    private Test test;

    /**
     * Constructs an instance of the Controller,
     * using singleton to access the app instance.
     */
    public RegisterSampleController() {
        // Singleton
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.ss = company.getSampleStore();
        this.testMapper = new TestMapper();
        this.testStore = new TestStore();
    }

    /**
     * Method to verify if the upca barcode is created
     *
     * @param path
     * @return true if the upca barcode is created
     */
    public boolean createUPCA(String path) {
        try {
            sample.createUPCA(path);
            return true;
        } catch (IllegalArgumentException | BarcodeException e) {
            System.out.println("\nERROR: " + e.getMessage());
            return false;
        }
    }

    /**
     * Calls the addSample method from the store to add the sample.
     *
     * @return true if the Sample was added.
     */
    public boolean addSampleUPCA() {
        return ss.addSample(barcodeUPCA);
    }

    /**
     * Method that returns a list of test filtered by their state (Registered) and by their lab id that must be the same that
     * the med lab tech chose
     *
     * @param clinicalAnalysisLaboratoryCode
     * @return
     */
    public List<TestDto> getTestsRegisted(String clinicalAnalysisLaboratoryCode) {
        List<Test> tests = new ArrayList<>();
        List<Test> testsRegisted = new ArrayList<>();
        tests = company.getTestStore().getTests();
        for (Test test : tests) {
            if (test.getCurrentState().equals("Registered")) {
                if (test.getClinicalAnalysisLaboratory().getLaboratoryId().equals(clinicalAnalysisLaboratoryCode)) {
                    testsRegisted.add(test);
                }
            }
        }
        return testMapper.toDTO(testsRegisted);
    }

    /**
     * Method to add a sample to a test
     *
     * @param code
     * @throws BarcodeException
     */
    public void addTestSample(String code) throws BarcodeException {
        test = company.getTestStore().getTest(code);
        test.addTestSample(sample);
    }

    /**
     * Method that calls the method above and returns true if the upca barcode is created
     *
     * @param code
     * @return true if the upca barcode is created
     * @throws BarcodeException
     */
    public boolean createSample(String code) throws BarcodeException {
        sample = new Sample();
        return createUPCA(code);
    }

    /**
     * Method to return a list of tests with only the nhs code using the mapper and dto classes
     *
     * @return a list of tests with only the nhs code
     */
    public List<TestDto> getTests() {
        List<Test> testList = testStore.getTests();
        return testMapper.toDTO(testList);
    }

    /**
     * Method that changes the state of a test after it receives a sample (or more)
     */
    public void setTestStateToCollected() {
        test.setCurrentState(Constants.STATE_SAMPLES_COLLECTED);
    }

    public boolean saveTests()
    {
        return company.saveTests(testStore);
    }
}
