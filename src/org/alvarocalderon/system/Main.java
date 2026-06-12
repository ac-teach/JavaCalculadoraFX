
package org.alvarocalderon.system;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.alvarocalderon.controller.CalculadoraController;
import org.alvarocalderon.view.CalculadoraView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Inicializamos la vista (que a su vez crea el controlador)
        CalculadoraView calculadora = new CalculadoraView();
        
        // Creamos la escena utilizando el contenedor principal de la vista
        Scene escena = new Scene(calculadora.getView(), 300, 420);
        
  
        
        // Configuramos la ventana
        primaryStage.setTitle("Calculadora Oliva");
        primaryStage.setScene(escena);
        primaryStage.setResizable(false); // Fija el tamaño como la de Microsoft
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}