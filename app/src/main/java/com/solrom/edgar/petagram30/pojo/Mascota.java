package com.solrom.edgar.petagram30.pojo;

/**
 * Created by edgarsr on 01/10/17.
 */

public class Mascota {

    private int id;
    private String nombre;
    private int numLikes;
    private int foto;

    public Mascota (String nombre, int numLikes, int foto){
        this.nombre = nombre;
        this.numLikes = numLikes;
        this.foto = foto;
    }

    public Mascota(){

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
