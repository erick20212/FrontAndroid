package Dto;

import java.util.UUID;

public class CategoriaDTO {
    private UUID categoriaId;  // ID único de la categoría
    private String nombre;      // Nombre de la categoría
    private String imagenUrl;

    // Constructor vacío (por si necesitas deserializar sin valores iniciales)
    public CategoriaDTO() {}

    // Constructor con todos los campos
    public CategoriaDTO(UUID categoriaId, String nombre, String descripcion) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;


    }

    // Getters y setters
    public UUID getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

}