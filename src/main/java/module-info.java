module ma.proj.examen {
    requires javafx.controls;
    requires javafx.fxml;


    opens ma.proj.examen to javafx.fxml;
    exports ma.proj.examen;
}