package com.example.mauricio.local;

public class VideoModel {

     private String titulo, descripcion, urlVideo;
     private int img;

    public VideoModel() {
        }

    public VideoModel(String titulo, String descripcion, int imgButton, String urlVideo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlVideo = urlVideo;
        this.img = imgButton;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int imgButton) {
        this.img = imgButton;
    }

    public void setUrlVideo(String urlImagen) {
        this.urlVideo = urlVideo;
    }
}
