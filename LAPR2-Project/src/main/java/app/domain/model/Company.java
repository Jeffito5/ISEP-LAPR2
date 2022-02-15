package app.domain.model;
import app.ui.console.utils.exceptions.ClientStoreException;
import app.domain.shared.Constants;
import app.domain.store.*;
import app.ui.console.utils.serializationUtils.FilesManager;
import app.domain.tasks.TaskManager;
import auth.AuthFacade;
import auth.domain.store.EmployeeRoleStore;
import auth.domain.store.UserRoleStore;
import auth.domain.store.UserStore;
import org.apache.commons.lang3.StringUtils;

import java.util.*;



/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    /**
     * Company designation.
     */
    private final String designation;

    /**
     * Auth facade.
     */
    private final AuthFacade authFacade;

    //STORES
    /**
     * Client store.
     */
    private ClientStore clientStore;

    /**
     * Parameter Category store.
     */
    private final ParameterCategoryStore parameterCategoryStore;

    /**
     * Employee store.
     */
    private final EmployeeStore employeeStore;

    /**
     * Employee Role store.
     */
    private final EmployeeRoleStore employeeRoleStore;

    /**
     * User store.
     */
    private final UserStore userStore;

    /**
     * Test Type store.
     */
    private final TestTypeStore testTypeStore;

    /**
     * Parameter store.
     */
    private final ParameterStore parameterStore;

    /**
     * Test store.
     */
    private final TestStore testStore;

    /**
     * Sample store.
     */
    private final SampleStore sampleStore;

    /**
     * Clinical Analysis Laboratory store.
     */
    private final ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore;

    /**
     * User Role store.
     */
    private UserRoleStore userRole;

    /**
     * File Manager (for Serialization).
     */
    private final FilesManager filesManager;

    /**
     * Task Manager.
     */
    private final TaskManager taskManager;


    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.filesManager = new FilesManager();
        this.employeeStore = readEmployees();
        this.designation = designation;
        this.userStore = readUsers();
        this.authFacade = new AuthFacade(userStore);

        try {
            clientStore = new ClientStore(readClients(), this.authFacade);
        } catch (ClientStoreException e){
            clientStore = new ClientStore(this.authFacade);
        }
        this.parameterCategoryStore = readParameterCategories();
        this.employeeRoleStore = readEmployeeRoles();
        this.testTypeStore = readTestTypes();
        this.clinicalAnalysisLaboratoryStore = readLabs();
        this.parameterStore = readParameters();
        this.testStore = readTests();
        this.sampleStore = readSamples();
        //this.reportStore=new ReportStore();
        this.taskManager = new TaskManager();
        setSendNHSReportTask();
    }

    /**
     * public Receptionist getReceptionist() {
     * return receptionist;
     * }
     **/


    //public ReportStore getReportStore(){return reportStore;}
    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    //region GET STORES
    /**
     * Returns the client store.
     *
     * @return Client Store.
     */
    public ClientStore getClientStore() {
        return this.clientStore;
    }

    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    public ParameterCategoryStore getParameterCategoryStore() {
        return this.parameterCategoryStore;
    }


    public EmployeeRoleStore getEmployeeRoleStore() {
        return this.employeeRoleStore;
    }

    public UserStore getUserStore() {
        return this.userStore;
    }

    public ClinicalAnalysisLaboratoryStore getClinicalAnalysisLaboratoryStore() {
        return this.clinicalAnalysisLaboratoryStore;
    }

    public UserRoleStore getUserRoles() {
        return this.userRole;
    }

    public TestTypeStore getTestTypeStore() {
        return this.testTypeStore;
    }

    public ParameterStore getParameterStore() {
        return this.parameterStore;
    }

    public TestStore getTestStore() {
        return this.testStore;
    }

    public SampleStore getSampleStore() {
        return this.sampleStore;
    }
    //endregion

    //region WORKING DAYS
    /**
     * Returns a list of working dates, given a start and end dates.
     *
     * @param startDate start date.
     * @param endDate end date.
     * @return List of working dates.
     * @throws IllegalArgumentException if date is before a specific limit or after
     * the present moment.
     */
    public List<Date> getWorkingDaysForIntervals (Date startDate, Date endDate){

        if(startDate.before(Constants.DATE_LOWER_LIMIT))
        {
            throw new IllegalArgumentException("Invalid start date. Start from 1/1/2021 onwards");
        }

        Date today = new Date();

        if(endDate.after(today))
        {
            throw new IllegalArgumentException("Invalid end date.");
        }


        List<Date> workingDates = new ArrayList<>();

        Date current = (Date) startDate.clone();

        while (!current.after(endDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(current);

            if (isWorkingDay(current))
            {
                workingDates.add(current);
            }

            calendar.add(Calendar.DATE, 1);
            current = calendar.getTime();
        }
        return workingDates;
    }

    /**
     * Returns a list of company working dates, given a reference date and
     * a number of historical points.
     *
     * @param referenceDate reference date.
     * @return List of working dates.
     * @throws IllegalArgumentException if date is before a specific limit or after
     * the present moment.
     */
    public List<Date> getWorkingDatesForInterval (Date referenceDate, int historicalPoints)
    {
        List<Date> workingDates = new ArrayList<>();

        if(referenceDate.before(Constants.DATE_LOWER_LIMIT))
        {
            throw new IllegalArgumentException("Invalid start date. Start from 1/1/2021 onwards");
        }

        Date today = new Date();

        if(referenceDate.after(today))
        {
            throw new IllegalArgumentException("Invalid reference date.");
        }

        while (historicalPoints > 0)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(referenceDate);

            if (isWorkingDay(referenceDate))
            {
                workingDates.add(0, referenceDate);
                historicalPoints--;
            }

            calendar.add(Calendar.DATE, -1);
            referenceDate = calendar.getTime();
        }
        return workingDates;
    }

    /**
     * Checks if a given date is a company working day.
     *
     * @param date date;
     * @return true if it is a working day.
     */
    public boolean isWorkingDay(Date date){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK) != Constants.DAY_OFF;
    }
    //endregion

    //region TASKS
    /**
     * Sets the NHS Report Task.
     */
    public void setSendNHSReportTask()
    {
        taskManager.scheduleNhsReport(this);
    }

    /**
     * Stops the NHS Report Task.
     */
    public void stopSendNHSReportTask()
    {
        taskManager.stopTasks();
    }
    //endregion

    //region SERIALIZATION
    // User Roles
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized User Role store.
     */
    public UserRoleStore readUserRoles() {
        return filesManager.readUserRoles();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param userRoleStore User Role store.
     * @return True if the User Role store was successfully saved.
     */
    public boolean saveUserRoles (UserRoleStore userRoleStore) {
        return filesManager.saveUserRoles(userRoleStore);
    }

    // Employee Roles
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Employee Role store.
     */
    public EmployeeRoleStore readEmployeeRoles() {
        return filesManager.readEmployeeRoles();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param employeeRoleStore Employee Role store.
     * @return True if the Employee Role store was successfully saved.
     */
    public boolean saveEmployeeRoles (EmployeeRoleStore employeeRoleStore) {
        return filesManager.saveEmployeeRoles(employeeRoleStore);
    }

    // Users
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized User store.
     */
    public UserStore readUsers() {
        return filesManager.readUsers();
    }

    /**
     * Method to save serialization binary file.
     *
     * @return True if the User store was successfully saved.
     */
    public boolean saveUsers () {
        return authFacade.saveUsers(this.filesManager);
    }

    // Clinical Analysis Laboratories
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Clinical Analysis Laboratory store.
     */
    public ClinicalAnalysisLaboratoryStore readLabs() {
        return filesManager.readLabs();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param calStore Clinical Analysis Laboratory store.
     * @return True if the clinical analysis laboratory store
     * was successfully saved.
     */
    public boolean saveLabs (ClinicalAnalysisLaboratoryStore calStore) {
        return filesManager.saveLabs(calStore);
    }

    // Clients
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized client store.
     */
    public ClientStore readClients() throws ClientStoreException
    {
        return filesManager.readClients();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param clientStore client store.
     * @return True if the client store was successfully saved.
     */
    public boolean saveClients (ClientStore clientStore) {
        return filesManager.saveClients(clientStore);
    }

    // Tests
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Test store.
     */
    public TestStore readTests() {
        return filesManager.readTests();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param testStore Test store.
     * @return True if the Test store was successfully saved.
     */
    public boolean saveTests (TestStore testStore) {
        return filesManager.saveTests(testStore);
    }

    // Test types
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Test Type store.
     */
    public TestTypeStore readTestTypes() {
        return filesManager.readTestTypes();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param testTypeStore Test Type store.
     * @return True if the Test Type store was successfully saved.
     */
    public boolean saveTestTypes (TestTypeStore testTypeStore) {
        return filesManager.saveTestTypes(testTypeStore);
    }

    // Employees
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Employee store.
     */
    public EmployeeStore readEmployees() {
        return filesManager.readEmployees();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param employeeStore Employee store.
     * @return True if the Employee store was successfully saved.
     */
    public boolean saveEmployees (EmployeeStore employeeStore) {
        return filesManager.saveEmployees(employeeStore);
    }

    // Parameter Categories
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Parameter Category store.
     */
    public ParameterCategoryStore readParameterCategories() {
        return filesManager.readParameterCategories();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param pcStore Parameter Category store.
     * @return True if the Parameter Category store was successfully saved.
     */
    public boolean saveParameterCategories (ParameterCategoryStore pcStore) {
        return filesManager.saveParameterCategories(parameterCategoryStore);
    }

    // Parameters
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Parameter store.
     */
    public ParameterStore readParameters() {
        return filesManager.readParameters();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param parameterStore Parameter store.
     * @return True if the Parameter store was successfully saved.
     */
    public boolean saveParameters (ParameterStore parameterStore) {
        return filesManager.saveParameters(parameterStore);
    }

    // Samples
    /**
     * Method to read serialization binary file.
     *
     * @return Serialized Sample store.
     */
    public SampleStore readSamples() {
        return filesManager.readSamples();
    }

    /**
     * Method to save serialization binary file.
     *
     * @param sampleStore Sample store.
     * @return True if the Sample store was successfully saved.
     */
    public boolean saveSamples (SampleStore sampleStore) {
        return filesManager.saveSamples(sampleStore);
    }
    //endregion
}
