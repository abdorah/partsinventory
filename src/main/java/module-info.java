module com.partsinventory {
    requires transitive java.sql;
    requires org.xerial.sqlitejdbc;

    requires transitive org.slf4j;

    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires transitive lombok;

    opens com.partsinventory to javafx.fxml;
    opens com.partsinventory.configuration to javafx.fxml, java.sql;
    opens com.partsinventory.controller to javafx.fxml;
    opens com.partsinventory.service to java.sql;
    opens com.partsinventory.model to lombok;

    exports com.partsinventory;
    exports com.partsinventory.configuration;
    exports com.partsinventory.controller;
    exports com.partsinventory.service;
    exports com.partsinventory.model;
}