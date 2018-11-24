package com.example.brandon.control.Model;

public class Device {
    //String Clave;
    private String Id;
    private String Modelo;
    private String Serie;
    private String OS;
    private String Ram;
    private String Hdd;

    public Device() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String ram) {
        Ram = ram;
    }

    public String getHdd() {
        return Hdd;
    }

    public void setHdd(String hdd) {
        Hdd = hdd;
    }

    @Override
    public String toString() {
        return "Id: " + Id + "\n" + "Modelo: " + Modelo + "\n" +  "Serie: " + Serie + "\n" + "Sistema Operativo: " + OS + "\n" +
                "Hdd: " + Hdd + "\n" + "Ram: " + Ram;
    }
}
