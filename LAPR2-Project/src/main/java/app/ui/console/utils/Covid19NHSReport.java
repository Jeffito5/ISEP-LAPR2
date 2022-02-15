package app.ui.console.utils;

import app.mappers.dto.LinearRegressionOutputDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class Covid19NHSReport {

    /**
     * Method that creates the Covid-19 report.
     *
     * @param linearRegressionOutputDTO Output of data from the Linear Regression Model
     * @param workingDays Historical points days.
     * @param observed Observed positive values.
     *
     * @return String containing the Covid-19 report.
     */
    public static String createCovidReport (LinearRegressionOutputDTO linearRegressionOutputDTO,
                                            List<Date> workingDays,
                                            double[] observed)
    {
        String stats = linearRegressionOutputDTO.getStats();
        String significanceModel = linearRegressionOutputDTO.getSignificanceModel();
        String hypothesisTests = linearRegressionOutputDTO.getHypothesisTests();
        double confidenceIntervalAlpha = linearRegressionOutputDTO.getConfidenceIntervalAlpha();
        double[] predictedValues = linearRegressionOutputDTO.getPredictedValues();
        List<String> confidenceIntervals = linearRegressionOutputDTO.getConfidenceIntervals();

        Date now = new Date();
        String today = new SimpleDateFormat("dd-MM-yyyy").format(now);

        StringBuilder report = new StringBuilder();
        report.append(String.format("COVID-19 REPORT [%s]\n\n\n", today)).
                append("#### REGRESSION MODEL STATS ####\n\n").append(stats).append("\n\n\n").
                append("#### HYPOTHESIS TESTS ####\n\n").append(hypothesisTests).append("\n\n\n").
                append("#### SIGNIFICANCE MODEL ####\n\n").append(significanceModel).append("\n\n\n").
                append("#### ESTIMATED VALUES ####\n\n").
                append(String.format("%-30s%-30s%-30s%-30s%n", "Date", "Observed Positive Cases", "Estimated Positive Cases", "Interval"));

        for (int i = 0; i < workingDays.size(); i++)
        {
            String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(workingDays.get(i));
            report.append(String.format("%-30s%-30.0f%-30.2f%-30s%n", currentDate, observed[i], predictedValues[i], confidenceIntervals.get(i)));
        }
        return report.toString();
    }

}
