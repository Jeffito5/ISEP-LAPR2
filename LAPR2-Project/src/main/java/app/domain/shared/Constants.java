package app.domain.shared;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    // Roles
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String ROLE_MED_LAB_TECH= "MEDICAL LAB TECHNICIAN";
    public static final String ROLE_SPEC_DOC="SPECIALIST DOCTOR";
    public static final String ROLE_CLI_CHEM_TECH="CLINICAL CHEMISTRY TECHNOLOGIST";
    public static final String ROLE_LAB_COORD="LABORATORY COORDINATOR";

    public static final String PARAMS_FILENAME = "src/main/resources/config/config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    public static final String PARAMS_SORTING_ALGORITHM = "Company.SortingAlgorithm";
    public static final String PARAMS_REGRESSION_MODEL = "Company.RegressionModel";
    public static final String PARAMS_REGRESSION_START_DATE = "RegressionModel.StartDate";
    public static final String PARAMS_REGRESSION_END_DATE = "RegressionModel.EndDate";
    public static final String PARAMS_REGRESSION_HISTORICAL_POINTS = "RegressionModel.HistoricalPoints";
    public static final String PARAMS_REGRESSION_HYPOTHESIS_TESTS_ALPHA = "RegressionModel.HypothesisTestAlpha";
    public static final String PARAMS_REGRESSION_SIGNIFICANCE_ALPHA = "RegressionModel.SignificanceAlpha";
    public static final String PARAMS_REGRESSION_CONFIDENCE_ALPHA = "RegressionModel.ConfidenceIntervalAlpha";
    public static final String PARAMS_BENCHMARK="Benchmark";
    public static final String PARAMS_BRUTEFORCE="BruteForce";

    // States
    public static final String STATE_CREATED = "CREATED";
    public static final String STATE_REGISTERED = "REGISTERED";
    public static final String STATE_SAMPLES_COLLECTED = "SAMPLES_COLLECTED";
    public static final String STATE_SAMPLES_ANALYZED = "SAMPLES_ANALYZED";
    public static final String STATE_VALIDATED = "VALIDATED";

    // External Modules
    public static final String PARAMS_COVID_EXTERNAL_MODULE_API = "app.domain.model.externalModules.adapters.ExternalModuleAdapter1";
    public static final String PARAMS_BLOOD_EXTERNAL_MODULE_API = "app.domain.model.externalModules.adapters.ExternalModuleAdapter2";

    // Linear Regression Models
    public static final String PARAMS_SIMPLE_LINEAR_REGRESSION = "app.domain.model.algorithms.linearRegression.adapters.SimpleLinearRegressionAdapter";
    public static final String PARAMS_MULTIPLE_LINEAR_REGRESSION = "app.domain.model.algorithms.linearRegression.adapters.MultipleLinearRegressionAdapter";

    // Company's Day Off
    public static final int DAY_OFF = Calendar.SUNDAY;

    // Parameters
    public static final String COVID_PARAMETER_NAME = "Covid";
    public static final String COVID_PARAMETER = "IgGAN";
    public static final double COVID_POSITIVE_UPPER_LIMIT = 1.4;

    // Search Date Limit
    private static final LocalDate OPENING_DATE = LocalDate.of(2021, 1, 1);
    public static final Date DATE_LOWER_LIMIT = java.util.Date.from(OPENING_DATE.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
}
