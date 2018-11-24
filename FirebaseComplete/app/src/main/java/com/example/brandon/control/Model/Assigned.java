package com.example.brandon.control.Model;

public class Assigned {

    private String IdAsig;
    private String IdEquipo;
    private String Empleado;
    private String Departamento;
    private String Edificio;
    private String DireccionIP;

    public Assigned() {
    }

    public String getIdAsig() {
        return IdAsig;
    }

    public void setIdAsig(String idAsig) {
        IdAsig = idAsig;
    }

    public String getIdEquipo() {
        return IdEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        IdEquipo = idEquipo;
    }

    public String getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(String empleado) {
        Empleado = empleado;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }

    public String getEdificio() {
        return Edificio;
    }

    public void setEdificio(String edificio) {
        Edificio = edificio;
    }

    public String getDireccionIP() {
        return DireccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        DireccionIP = direccionIP;
    }

    @Override
    public String toString() {
        return "Id Asignado: " + IdAsig + "\n" + "Id Equipo: " + IdEquipo + "\n" +  "Empleado: " + Empleado + "\n" +
                "Departamento: " + Departamento+ "\n" + "Edificio: " + Edificio + "\n" + "Direccion IP: " + DireccionIP;
    }
}
