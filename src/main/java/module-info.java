module fr.univ_amu.iut {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires jakarta.persistence;
    opens fr.univ_amu.iut to javafx.fxml;
    exports fr.univ_amu.iut;
    exports fr.univ_amu.iut.components;
    opens fr.univ_amu.iut.components to javafx.fxml;
    exports fr.univ_amu.iut.tools;
    opens fr.univ_amu.iut.tools to javafx.fxml;

    exports fr.univ_amu.iut.dao.beans;
    opens fr.univ_amu.iut.dao.beans;
}