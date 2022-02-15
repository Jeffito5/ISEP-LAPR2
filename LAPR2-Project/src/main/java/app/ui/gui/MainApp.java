package app.ui.gui;

import app.controller.App;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainApp extends Application {
    private final double MINIMUM_WINDOW_WIDTH = 600.0;
    private final double MINIMUM_WINDOW_HEIGHT = 400.0;
    public static final String APP_NAME = "Many Labs";
    private Stage stage;

    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle(APP_NAME);
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        toMainScene();
        this.stage.show();
    }

    public Stage getStage() {
        return this.stage;
    }

    public void toMainScene() {
        try {
            MainUI mainUI = (MainUI) replaceSceneContent("/fxml/Main.fxml");
            mainUI.setMainApp(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        scene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        this.stage.setScene(scene);
        this.stage.sizeToScene();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert alert = AlertGUI.createAlert(Alert.AlertType.CONFIRMATION, APP_NAME,
                        "Confirmation.", "Are you sure you want to close the app?");

                if (alert.showAndWait().get() == ButtonType.CANCEL) {
                    event.consume();
                }
                else {
                    System.exit(0);
                }
            }
        });
        return (Initializable) loader.getController();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

