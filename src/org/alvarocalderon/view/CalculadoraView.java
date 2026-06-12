package org.alvarocalderon.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.alvarocalderon.controller.CalculadoraController;

public class CalculadoraView {

    private VBox raiz;
    private Label pantalla;
    GridPane cuadriculaBotones = new GridPane();

    private CalculadoraController controller;

    public CalculadoraView() {
        controller = new CalculadoraController();

        // Contenedor principal, usado como nodo raiz
        raiz = new VBox(15);
        raiz.setPadding(new Insets(20));
        raiz.setAlignment(Pos.CENTER);
        raiz.setStyle("-fx-background-color: #4A5D23;"); // Fondo Verde Oliva Oscuro

        // Pantalla de la calculadora
        pantalla = new Label("0");
        pantalla.setFont(Font.font("Consolas", FontWeight.BOLD, 40));
        pantalla.setAlignment(Pos.CENTER_RIGHT);
        pantalla.setPrefSize(250, 70);
        // Pantalla con un tono oliva muy claro y texto oscuro
        pantalla.setStyle("-fx-background-color: #E8EDE0; -fx-text-fill: #2C3518; -fx-padding: 10px; -fx-background-radius: 5px;");

        // Cuadrícula para los botones
        cuadriculaBotones.setHgap(10);
        cuadriculaBotones.setVgap(10);
        cuadriculaBotones.setAlignment(Pos.CENTER);

        // Fila 0: Nuevos operadores y División
        Button btnRaiz = crearBoton("√");
        Button btnPot = crearBoton("^");
        Button btnPorc = crearBoton("%");
        Button btnDiv = crearBoton("/");

        cuadriculaBotones.add(btnRaiz, 0, 0);
        cuadriculaBotones.add(btnPot, 1, 0);
        cuadriculaBotones.add(btnPorc, 2, 0);
        cuadriculaBotones.add(btnDiv, 3, 0);

        // Fila 1: Números y Multiplicación
        Button btn7 = crearBoton("7");
        Button btn8 = crearBoton("8");
        Button btn9 = crearBoton("9");
        Button btnMult = crearBoton("*");

        cuadriculaBotones.add(btn7, 0, 1);
        cuadriculaBotones.add(btn8, 1, 1);
        cuadriculaBotones.add(btn9, 2, 1);
        cuadriculaBotones.add(btnMult, 3, 1);

// Fila 2: Números y Resta
        Button btn4 = crearBoton("4");
        Button btn5 = crearBoton("5");
        Button btn6 = crearBoton("6");
        Button btnResta = crearBoton("-");

        cuadriculaBotones.add(btn4, 0, 2);
        cuadriculaBotones.add(btn5, 1, 2);
        cuadriculaBotones.add(btn6, 2, 2);
        cuadriculaBotones.add(btnResta, 3, 2);

        // Fila 3: Números y Suma
        Button btn1 = crearBoton("1");
        Button btn2 = crearBoton("2");
        Button btn3 = crearBoton("3");
        Button btnSuma = crearBoton("+");

        cuadriculaBotones.add(btn1, 0, 3);
        cuadriculaBotones.add(btn2, 1, 3);
        cuadriculaBotones.add(btn3, 2, 3);
        cuadriculaBotones.add(btnSuma, 3, 3);

        // Fila 4: Limpiar, Cero y el botón Igual (ocupando 2 espacios)
        Button btnC = crearBoton("C");
        Button btn0 = crearBoton("0");
        Button btnIgual = crearBoton("=");

        // El botón igual necesita ser más ancho para verse bien al ocupar 2 celdas
        btnIgual.setPrefSize(115, 55); // Ajusta el 115 según el 'hgap' (espaciado) de tu GridPane
        GridPane.setColumnSpan(btnIgual, 2);

        cuadriculaBotones.add(btnC, 0, 4);
        cuadriculaBotones.add(btn0, 1, 4);
        cuadriculaBotones.add(btnIgual, 2, 4);

        // Agregar pantalla y botones al contenedor principal
        raiz.getChildren().addAll(pantalla, cuadriculaBotones);
    }

    // Método para devolver la vista construida a la clase Main
    public VBox getView() {
        return raiz;
    }

    private Button crearBoton(String text) {
        Button btn = new Button(text);

        // Tamaño por defecto para la mayoría de los botones (excepto el '=')
        btn.setPrefSize(55, 55);
        btn.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Asignación de la paleta Verde Oliva
        if (text.matches("[0-9]")) {
            // Números: Verde Oliva intermedio
            btn.setStyle("-fx-background-color: #6B8E23; -fx-text-fill: white; -fx-background-radius: 5px; -fx-cursor: hand;");
        } else if (text.equals("C")) {
            // Botón Limpiar: Tono rojizo/tierra
            btn.setStyle("-fx-background-color: #8B4513; -fx-text-fill: white; -fx-background-radius: 5px; -fx-cursor: hand;");
        } else if (text.equals("=")) {
            // Botón Igual: Verde amarillo brillante
            btn.setStyle("-fx-background-color: #9ACD32; -fx-text-fill: #2C3518; -fx-background-radius: 5px; -fx-cursor: hand;");
        } else {
            // Operadores (incluyendo √, ^, %): Tono Oliva puro
            btn.setStyle("-fx-background-color: #556B2F; -fx-text-fill: white; -fx-background-radius: 5px; -fx-cursor: hand;");
        }

        // Evento unificado
        btn.setOnAction(e -> controller.processInput(text, pantalla));

        return btn;
    }
}
