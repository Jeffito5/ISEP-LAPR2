package app.ui.console;
import app.controller.ReceptionistController;
import app.mappers.dto.ClinicalAnalysisLaboratoryDto;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rui Rocha <1200735@isep.ipp.pt>
 */

public class ReceptionistUI implements Runnable {
    public ReceptionistUI() {
    }

    public void run() {

        String clinicalAnalysisLaboratoryId;

        // Clinical Analysis Laboratory Selection
        try{
            clinicalAnalysisLaboratoryId = selectClinicalAnalysisLaboratory();
        } catch(IllegalArgumentException e){
            System.out.println("\n" + e.getMessage());
            return;
        }

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Manage Clients ", new ManageClientUI()));
        options.add(new MenuItem("Register Test ", new RegisterTestUI(clinicalAnalysisLaboratoryId)));

        int option;
        do {

            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }

    /**
     * Prompts the user to select a Clinical Analysis Laboratory.
     *
     * @return Clinical Analysis Laboratory code.
     */
    public String selectClinicalAnalysisLaboratory(){
        //ATTRIBUTES
        ReceptionistController receptionistController = new ReceptionistController();
        int clinicalAnalysisLaboratoryIndex;
        String clinicalAnalysisLaboratoryCode;

        List<ClinicalAnalysisLaboratoryDto> clinicalAnalysisLaboratoryDtoList = receptionistController.getClinicalAnalysisLaboratories();

        clinicalAnalysisLaboratoryIndex = Utils.showAndSelectIndex(clinicalAnalysisLaboratoryDtoList, "\nSelect Clinical Analysis Laboratory:");

        if (clinicalAnalysisLaboratoryIndex < 0)
            throw new IllegalArgumentException("Clinical Analysis Laboratory not selected.");

        clinicalAnalysisLaboratoryCode = clinicalAnalysisLaboratoryDtoList.get(clinicalAnalysisLaboratoryIndex).getLaboratoryId();

        return clinicalAnalysisLaboratoryCode;
    }
}
