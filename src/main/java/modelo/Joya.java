package modelo;

public class Joya{
    private int id;
    private String tipo;
    private String material;
    private String aleacion;
    private double precio;
    private double medida;

    public Joya() {
    }

    public Joya(int id, String tipo,String material, String aleacion, double precio, double medida) {
        this.id = id;
        this.tipo = tipo;
        this.material = material;
        this.aleacion = aleacion;
        this.precio = precio; 
        this.medida = medida;
    }

    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getAleacion() {
		return aleacion;
	}

	public void setAleacion(String aleacion) {
		this.aleacion = aleacion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getMedida() {
		return medida;
	}

	public void setMedida(double medida) {
		this.medida = medida;
	}

	@Override
	public String toString() {
		return "Joya [id=" + id + ", tipo=" + tipo + ", material=" + material + ", aleacion=" + aleacion + ", precio="
				+ precio + ", medida=" + medida + "]";
	}


    
}
