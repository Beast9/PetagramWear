package com.anncode.aplicacioncontactos;

/**
 * Created by isaachernandezquinonez on 06/07/16.
 */

public class Mascota {

    private String idMascota;
    private String imagen;
    private String nombre;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private int likes;

    public Mascota() {
    }

    public Mascota(String idMascota, String imagen, String nombre, String link, int likes) {
        this.idMascota = idMascota;
        this.imagen = imagen;
        this.nombre = nombre;
        this.link = link;
        this.likes = likes;
    }

    public String getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

