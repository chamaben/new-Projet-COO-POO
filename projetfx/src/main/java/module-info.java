module projetfx.projetfx {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens projetfx.projetfx to javafx.fxml;
    exports projetfx.projetfx;
}
