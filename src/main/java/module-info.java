module com.partsinventory {
    requires java.desktop;
    requires transitive java.sql;
    requires org.xerial.sqlitejdbc;
    
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    
    requires transitive org.slf4j;

    requires com.github.librepdf.openpdf;

    opens com.partsinventory to javafx.fxml;
    opens com.partsinventory.helper to javafx.fxml, java.sql;
    opens com.partsinventory.controller to javafx.fxml;
    opens com.partsinventory.service to java.desktop, java.sql, com.github.librepdf.openpdf;

    exports com.partsinventory;
    exports com.partsinventory.helper;
    exports com.partsinventory.controller;
    exports com.partsinventory.service;
    exports com.partsinventory.model;
}