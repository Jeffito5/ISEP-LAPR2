package app.ui.console.utils.serializationUtils;

import app.ui.console.utils.exceptions.ClientStoreException;
import app.domain.store.*;
import auth.domain.store.EmployeeRoleStore;
import auth.domain.store.UserRoleStore;
import auth.domain.store.UserStore;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class FilesManager {
    /**
     * User Role file.
     */
    private final UserRoleFile userRoleFile;

    /**
     * User file.
     */
    private final UserFile userFile;

    /**
     * Employee Role file.
     */
    private final EmployeeRoleFile employeeRoleFile;

    /**
     * Clinical Analysis Laboratory file.
     */
    private final ClinicalAnalysisLaboratoryFile clinicalAnalysisLaboratoryFile;

    /**
     * Client file.
     */
    private final ClientFile clientFile;

    /**
     * Test file.
     */
    private final TestFile testFile;

    /**
     * Test type file.
     */
    private final TestTypeFile testTypeFile;

    /**
     * Employee file.
     */
    private final EmployeeFile employeeFile;

    /**
     * Parameter Category file.
     */
    private final ParameterCategoryFile parameterCategoryFile;

    /**
     * Parameter file.
     */
    private final ParameterFile parameterFile;

    /**
     * Sample file.
     */
    private final SampleFile sampleFile;

    /**
     * Constructor that instantiates the Serialization files classes.
     */
    public FilesManager()
    {
        this.userRoleFile = new UserRoleFile();
        this.userFile = new UserFile();
        this.employeeRoleFile = new EmployeeRoleFile();
        this.clinicalAnalysisLaboratoryFile = new ClinicalAnalysisLaboratoryFile();
        this.clientFile = new ClientFile();
        this.testFile = new TestFile();
        this.testTypeFile = new TestTypeFile();
        this.employeeFile = new EmployeeFile();
        this.parameterCategoryFile = new ParameterCategoryFile();
        this.parameterFile = new ParameterFile();
        this.sampleFile = new SampleFile();
    }

    // User Roles
    /**
     * Returns User Role Store from the user role file.
     *
     * @return User Role Store.
     */
    public UserRoleStore readUserRoles() {
        return userRoleFile.read();
    }

    /**
     * Saves User Role store in user role file.
     *
     * @param userRoleStore User Role Store.
     * @return True if store was successfully saved.
     */
    public boolean saveUserRoles (UserRoleStore userRoleStore) {
        return userRoleFile.save(userRoleStore);
    }

    // Employee Roles
    /**
     * Returns Employee Role Store from the employee role file.
     *
     * @return Employee Role Store.
     */
    public EmployeeRoleStore readEmployeeRoles() {
        return employeeRoleFile.read();
    }

    /**
     * Saves Employee Role store in employee role file.
     *
     * @param employeeRoleStore Employee Role Store.
     * @return True if store was successfully saved.
     */
    public boolean saveEmployeeRoles (EmployeeRoleStore employeeRoleStore) {
        return employeeRoleFile.save(employeeRoleStore);
    }

    // Users
    /**
     * Returns User Store from the user file.
     *
     * @return User Store.
     */
    public UserStore readUsers() {
        return userFile.read();
    }

    /**
     * Saves User store in user file.
     *
     * @param userStore User Store.
     * @return True if store was successfully saved.
     */
    public boolean saveUsers (UserStore userStore) {
        return userFile.save(userStore);
    }

    // Clinical Analysis Laboratories
    /**
     * Returns Clinical Analysis Laboratory Store from the CAL file.
     *
     * @return Clinical Analysis Laboratory Store.
     */
    public ClinicalAnalysisLaboratoryStore readLabs() {
        return clinicalAnalysisLaboratoryFile.read();
    }

    /**
     * Saves Clinical Analysis Laboratory store in CAL file.
     *
     * @param calStore Clinical Analysis Laboratory Store.
     * @return True if store was successfully saved.
     */
    public boolean saveLabs (ClinicalAnalysisLaboratoryStore calStore) {
        return clinicalAnalysisLaboratoryFile.save(calStore);
    }

    // Clients
    /**
     * Returns Client Store from the user file.
     *
     * @return Client Store.
     * @throws ClientStoreException if the Client Store was not found.
     */
    public ClientStore readClients() throws ClientStoreException {
        return clientFile.read();
    }

    /**
     * Saves Client store in client file.
     *
     * @param clientStore Client Store.
     * @return True if store was successfully saved.
     */
    public boolean saveClients (ClientStore clientStore) {
        return clientFile.save(clientStore);
    }

    // Tests
    /**
     * Returns Test Store from the Tests file.
     *
     * @return Test Store.
     */
    public TestStore readTests() {
        return testFile.read();
    }

    /**
     * Saves Test store in tests file.
     *
     * @param testStore Test store.
     * @return True if store was successfully saved.
     */
    public boolean saveTests (TestStore testStore) {
        return testFile.save(testStore);
    }

    // Test types
    /**
     * Returns Test Type Store from the Test Types file.
     *
     * @return Test Type Store.
     */
    public TestTypeStore readTestTypes() {
        return testTypeFile.read();
    }

    /**
     * Saves Test Type store in tests file.
     *
     * @param testTypeStore Test type store.
     * @return True if store was successfully saved.
     */
    public boolean saveTestTypes (TestTypeStore testTypeStore) {
        return testTypeFile.save(testTypeStore);
    }

    // Employees
    /**
     * Returns Employee Store from the employees file.
     *
     * @return Employee Store.
     */
    public EmployeeStore readEmployees() {
        return employeeFile.read();
    }

    /**
     * Saves Employee store in employees file.
     *
     * @param employeeStore Employee Store.
     * @return True if store was successfully saved.
     */
    public boolean saveEmployees (EmployeeStore employeeStore) {
        return employeeFile.save(employeeStore);
    }

    // Parameter Categories
    /**
     * Returns Parameter Category Store from the parameter categories file.
     *
     * @return Parameter Category Store.
     */
    public ParameterCategoryStore readParameterCategories() {
        return parameterCategoryFile.read();
    }

    /**
     * Saves Parameter Category store in parameter categories file.
     *
     * @param pcStore Parameter Category Store.
     * @return True if store was successfully saved.
     */
    public boolean saveParameterCategories (ParameterCategoryStore pcStore) {
        return parameterCategoryFile.save(pcStore);
    }

    // Parameters
    /**
     * Returns Parameter Store from the parameters file.
     *
     * @return Parameter Store.
     */
    public ParameterStore readParameters() {
        return parameterFile.read();
    }

    /**
     * Saves Parameter store in parameters file.
     *
     * @param parameterStore Parameter Store.
     * @return True if store was successfully saved.
     */
    public boolean saveParameters (ParameterStore parameterStore) {
        return parameterFile.save(parameterStore);
    }

    // Samples
    /**
     * Returns Sample Store from the samples file.
     *
     * @return Sample Store.
     */
    public SampleStore readSamples() {
        return sampleFile.read();
    }

    /**
     * Saves Sample store in samples file.
     *
     * @param sampleStore Sample store.
     * @return True if store was successfully saved.
     */
    public boolean saveSamples (SampleStore sampleStore) {
        return sampleFile.save(sampleStore);
    }

}
