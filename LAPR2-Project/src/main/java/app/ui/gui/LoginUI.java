package app.ui.gui;

import app.controller.App;
import app.controller.AuthController;
import app.controller.ReceptionistController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luís Araújo
 */
public class LoginUI {
    private App app;
    private MainApp mainApp;
    private AuthController ctrl;

    public LoginUI(MainApp mainApp) {
        app = App.getInstance();
        this.mainApp = mainApp;
        this.ctrl = new AuthController();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public MainApp getMainApp() {
        return this.mainApp;
    }

    public AuthController getController() {
        return this.ctrl;
    }

    public void doLogout()
    {
        this.ctrl.doLogout();
    }

    public void toLoginScene1() {
        try {
            LoginScene1UI loginScene1UI
                    = (LoginScene1UI) this.mainApp.
                    replaceSceneContent("/fxml/LoginScene1.fxml");
            loginScene1UI.setLoginUI(this);
            loginScene1UI.setController(this.ctrl);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toClientMenu() {
        try {
            ClientSceneGUI clientSceneGUI
                    = (ClientSceneGUI) this.mainApp.
                    replaceSceneContent("/fxml/ClientScene.fxml");
            clientSceneGUI.setMainApp(this.mainApp);
            clientSceneGUI.setLoginUI(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toLabCoordinatorMenu () {
        try {
            LabCoordinatorGUI labCoordinatorGUI = (LabCoordinatorGUI) this.mainApp.
                    replaceSceneContent("/fxml/LaboratoryCoordinatorScene.fxml");
            labCoordinatorGUI.setMainApp(this.mainApp);
            labCoordinatorGUI.setLoginUI(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toCliChemTechMenu ()
    {
        try {
            ClinicalChemistryTechnologistGUI clinicalChemistryTechnologistGUI = (ClinicalChemistryTechnologistGUI) this.mainApp.
                    replaceSceneContent("/fxml/ClinicalChemistryTechnologistMenu.fxml");
            clinicalChemistryTechnologistGUI.setMainApp(this.mainApp);
            clinicalChemistryTechnologistGUI.setLoginUI(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toReceptionistMenu (String calId)
    {
        try {
            ReceptionistGUI receptionistGUI = (ReceptionistGUI) this.mainApp.
                    replaceSceneContent("/fxml/ReceptionistMenu.fxml");
            receptionistGUI.setMainApp(this.mainApp);
            receptionistGUI.setLoginUI(this);
            receptionistGUI.setLabCode(calId);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toAdminMenu ()
    {
        try {
            AdministratorGUI administratorGUI = (AdministratorGUI) this.mainApp.
                    replaceSceneContent("/fxml/AdministratorMenu.fxml");
            administratorGUI.setMainApp(this.mainApp);
            administratorGUI.setLoginUI(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void toSpecDocMenu ()
    {
        try {
            SpecialistDoctorGUI specialistDoctorGUI = (SpecialistDoctorGUI) this.mainApp.
                    replaceSceneContent("/fxml/SpecialistDoctorMenu.fxml");
            specialistDoctorGUI.setMainApp(this.mainApp);
            specialistDoctorGUI.setLoginUI(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectLab(){
        try {
            ReceptionistController receptionistController = new ReceptionistController();
            SelectLaboratoryScene selectLaboratoryScene = (SelectLaboratoryScene) this.mainApp.
                    replaceSceneContent("/fxml/SelectLaboratoryScene.fxml");
            selectLaboratoryScene.setMainApp(this.mainApp);
            selectLaboratoryScene.setReceptionistController(receptionistController);
            selectLaboratoryScene.setLoginUI(this);
            selectLaboratoryScene.initCombo();
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


