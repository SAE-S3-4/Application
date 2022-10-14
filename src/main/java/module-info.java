module com.example.sae_s3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens sae_s3.findthebreach to javafx.fxml;
    exports sae_s3.findthebreach;
}