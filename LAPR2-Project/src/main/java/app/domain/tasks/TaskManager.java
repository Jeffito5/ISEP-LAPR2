package app.domain.tasks;

import app.domain.model.Company;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */
public class TaskManager {

    /**
     * Covid-19 NHS Report Timer.
     */
    private final Timer reportTimer;

    /**
     * Constructs an instance of the Task Manager.
     */
    public TaskManager()
    {
        reportTimer = new Timer();
    }

    /**
     * Schedules the Covid-19 NHS Report Task.
     *
     * @param company Company.
     */
    public void scheduleNhsReport(Company company)
    {
        // Every day at 6am the task runs
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 6);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        // In case the defined hour has passed, it will set the time to the next day
        Calendar now = Calendar.getInstance();

        if (today.before(now))
        {
            today.add(Calendar.DATE, 1);
        }

        SendNhsReportTask sendNhsReportTask = new SendNhsReportTask(company);
        //Timer timer = new Timer();
        reportTimer.schedule(sendNhsReportTask, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

    /**
     * Stops all tasks.
     */
    public void stopTasks()
    {
        reportTimer.cancel();
        reportTimer.purge();
    }

}
