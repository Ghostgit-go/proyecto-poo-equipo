module Proyecto_Laboratorio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    exports co.edu.eia.poo.proyecto.laboratorio;
    exports co.edu.eia.poo.proyecto.vista;

    opens co.edu.eia.poo.proyecto.laboratorio to javafx.base;
    opens co.edu.eia.poo.proyecto.vista to javafx.fxml;
}