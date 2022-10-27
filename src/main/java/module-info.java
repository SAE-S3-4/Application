module com.example.sae_s3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires jakarta.persistence;
    opens fr.univ_amu.iut to javafx.fxml;
    exports fr.univ_amu.iut;
}