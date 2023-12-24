module com.partsinventory {
    requires transitive java.sql;
    requires org.xerial.sqlitejdbc;
    
    requires transitive org.slf4j;

    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.partsinventory to javafx.fxml, java.sql;
    opens com.partsinventory.configuration to javafx.fxml, java.sql;
    opens com.partsinventory.controller to javafx.fxml, java.sql;

    exports com.partsinventory;
}