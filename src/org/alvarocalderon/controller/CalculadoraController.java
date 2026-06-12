/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.alvarocalderon.controller;


import javafx.scene.control.Label;

public class CalculadoraController {
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewInput = true;

    public void processInput(String input, Label display) {
        if (display.getText().equals("Error")) {
            display.setText("0");
        }

        if (input.matches("[0-9]")) {
            if (startNewInput) {
                display.setText(input);
                startNewInput = false;
            } else {
                display.setText(display.getText() + input);
            }
        } else if (input.equals("C")) {
            display.setText("0");
            firstOperand = 0;
            operator = "";
            startNewInput = true;
        } else if (input.equals("=")) {
            if (!operator.isEmpty()) {
                calculate(display);
                operator = "";
                startNewInput = true;
            }
        } else { // Es un operador (+, -, *, /)
            if (!operator.isEmpty() && !startNewInput) {
                // Si ya hay una operación pendiente y el usuario presiona otro operador,
                // calculamos el resultado parcial para poder seguir encadenando.
                calculate(display);
            }
            try {
                firstOperand = Double.parseDouble(display.getText());
            } catch (NumberFormatException e) {
                firstOperand = 0;
            }
            operator = input;
            startNewInput = true;
        }
    }

    private void calculate(Label display) {
        try {
            double secondOperand = Double.parseDouble(display.getText());
            double result = 0;
            
            switch (operator) {
                case "+": result = firstOperand + secondOperand; break;
                case "-": result = firstOperand - secondOperand; break;
                case "*": result = firstOperand * secondOperand; break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
                default: return;
            }

            // Eliminar el decimal si el resultado es un número entero
            if (result == (long) result) {
                display.setText(String.format("%d", (long) result));
            } else {
                display.setText(String.valueOf(result));
            }
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }
}