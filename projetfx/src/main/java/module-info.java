module projetfx.projetfx {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.desktop;

    opens projetfx.projetfx to javafx.fxml;
    exports projetfx.projetfx;
}
