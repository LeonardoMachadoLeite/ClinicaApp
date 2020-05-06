package com.milkaxe_studios.clinicaapp.exceptions;

public class ControllerUnexpectedResult extends Exception{

    public ControllerUnexpectedResult(String controller) {
        super(String.format("Unexpected result in controller %s.", controller));
    }

    public ControllerUnexpectedResult(String msg, String controller) {
        super(String.format("%s\nUnexpected result in controller %s.", msg, controller));
    }

}
