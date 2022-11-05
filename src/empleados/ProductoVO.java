package empleados;

public class ProductoVO {

/*Todo los atributos*/
    int idproducto;
    String nombre;
    double precio;
    int stock;
    String tipo;
    private byte[] foto;

public ProductoVO(){}

/*Todo los codigos get*/

    public int getIdproducto() {
        return idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getTipo() {
        return tipo;
    }

    
    
    //set

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
 



    /**
     * @return the foto
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

}
