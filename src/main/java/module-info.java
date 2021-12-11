module com.main.prjmtg {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.main.prjmtg to javafx.fxml;
    exports com.main.prjmtg;
}