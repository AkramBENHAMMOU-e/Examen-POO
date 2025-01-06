module ma.proj.examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ma.proj.examen to javafx.fxml;
    opens ma.proj.examen.controller to javafx.fxml;
    opens ma.proj.examen.model to javafx.fxml;
    exports ma.proj.examen.controller;
    exports ma.proj.examen.model;
    opens ma.proj.examen.dao to javafx.fxml;
    exports ma.proj.examen.dao;
    opens ma.proj.examen.connectionDB to javafx.fxml;
    exports ma.proj.examen.connectionDB;
    exports ma.proj.examen;
}