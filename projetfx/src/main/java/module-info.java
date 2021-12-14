module projetfx.projetfx {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;

    opens projetfx.projetfx to javafx.fxml;
    exports projetfx.projetfx;
}
