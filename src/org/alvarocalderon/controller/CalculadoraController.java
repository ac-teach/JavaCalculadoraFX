package org.alvarocalderon.controller;

import javafx.scene.control.Label;

public class CalculadoraController {
    private String op1 = "";
    private String operador = "";
    private String op2 = "";
    private boolean calculoTerminado = false;

    public void processInput(String input, Label pantalla) {
        // Si la entrada es limpiar
        if (input.equals("C")) {
            op1 = "";
            operador = "";
            op2 = "";
            pantalla.setText("");
            return;
        }

        // Si había un cálculo previo y se escribe un nuevo número, reiniciamos
        if (calculoTerminado && input.matches("[0-9]")) {
            op1 = "";
            operador = "";
            op2 = "";
        }
        calculoTerminado = false;

        // Manejo de Números
        if (input.matches("[0-9]")) {
            if (operador.isEmpty()) {
                op1 += input; // Construimos el primer operando, el permer nuemro
            } else {
                op2 += input; // Construimos el segundo operando, segundo numero
            }
            actualizarPantalla(pantalla);
        } // Manejo de Operadores Binarios (+, -, *, /, ^)
        else if (input.matches("[+\\-*/^]")) {
            //si numero1 esta lleno y numero2 vacio
            if (!op1.isEmpty() && op2.isEmpty()) {                
                operador = input; // Asignamos el operador
            //si numero1 esta lleno y numero2 esta lleno
            } else if (!op1.isEmpty() && !op2.isEmpty()) {
                // Si ya hay una operación completa (ej. 5 + 3) y presiona otro operador,
                // evaluamos la primera y encadenamos la siguiente (ej. 8 + ...)
                op1 = evaluarBinario(op1, operador, op2);
                operador = input;
                op2 = "";
            }
            actualizarPantalla(pantalla);
        } // Manejo de Operadores Unarios (√, %)
        else if (input.equals("√") || input.equals("%")) {
            if (operador.isEmpty() && !op1.isEmpty()) {
                // Se aplica al primer número si no hay operador aún
                op1 = evaluarUnario(op1, input);
            } else if (!operador.isEmpty() && !op2.isEmpty()) {
                // Se aplica al segundo número de forma aislada
                op2 = evaluarUnario(op2, input);
            }
            actualizarPantalla(pantalla);
            calculoTerminado = true;
        } 

        //Funcionamiento del boton igual
        
        else if (input.equals("=")) {
            //si op1 y operador y op2 NO estan vacios
            if (!op1.isEmpty() && !operador.isEmpty() && !op2.isEmpty()) {
                //todos estaban vacios, se genera la operacion
                op1 = evaluarBinario(op1, operador, op2);
                operador = "";
                op2 = "";
                calculoTerminado = true;
                actualizarPantalla(pantalla);
            }
        }
    }

    // Refresca la pantalla concatenando los elementos actuales
    private void actualizarPantalla(Label pantalla) {
        if (operador.isEmpty()) {
            pantalla.setText(op1);
        } else {
            pantalla.setText(op1 + " " + operador + " " + op2);
        }
    }

     // Evalúa suma, resta, multiplicación, división y potencia
    private String evaluarBinario(String n1, String op, String n2) {
        try {
            double d1 = Double.parseDouble(n1);
            double d2 = Double.parseDouble(n2);
            double resultado = 0;

            switch (op) {
                case "+":
                    resultado = d1 + d2;
                    break;
                case "-":
                    resultado = d1 - d2;
                    break;
                case "*":
                    resultado = d1 * d2;
                    break;
                case "/":
                    if (d2 == 0) {
                        return "Error"; // Prevenir división por cero
                    }
                    resultado = d1 / d2;
                    break;
                case "^":
                    resultado = Math.pow(d1, d2);
                    break;
            }
            return formatearSalida(resultado);
        } catch (Exception e) {
            return "Error";
        }
    }

// Evalúa raíz cuadrada y porcentaje
    private String evaluarUnario(String num, String op) {
        try {
            double d = Double.parseDouble(num);
            double resultado = 0;

            switch (op) {
                case "√":
                    if (d < 0) {
                        return "Error"; // No hay raíces reales de números negativos
                    }
                    resultado = Math.sqrt(d);
                    break;
                case "%":
                    resultado = d / 100.0;
                    break;
            }
            return formatearSalida(resultado);
        } catch (Exception e) {
            return "Error";
        }
    }

    // Elimina el ".0" final si el resultado es un número entero
    private String formatearSalida(double valor) {
        if (valor == (long) valor) {
            return String.format("%d", (long) valor);
        } else {
            return String.valueOf(valor); // Devuelve el decimal tal cual
        }
    }
}
