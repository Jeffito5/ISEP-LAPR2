module Sem2App {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.lang3;
    requires commons.math3;
    requires bcrypt;
    requires barbecue;
    requires CovidReferenceValues1API;
    requires ExternalModule2API;
    requires ExternalModule3API;
    requires java.logging;
    requires Sum;
    requires Report2NHS;
    opens app.ui.gui to javafx.fxml;
    exports app.ui.gui;

}