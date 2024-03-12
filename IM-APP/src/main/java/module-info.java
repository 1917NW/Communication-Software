module com.lxy.imapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lxy.imapp to javafx.fxml;
    opens com.lxy.imapp.controller to javafx.fxml;
    exports com.lxy.imapp;
}