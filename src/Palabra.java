
public class Palabra {

	public String cadena;
	public int frecuencia;
	public float probabilidad;
	
	public Palabra() {
	}
	
	public Palabra(String cadena, int frecuencia, float probabilidad) {
		this.cadena= cadena;
		this.frecuencia= frecuencia;
		this.probabilidad= probabilidad;
	}

	public String getCadena() {
		return cadena;
	}
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public int getFrecuencia() {
		return frecuencia;
	}
	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	public float getProbabilidad() {
		return probabilidad;
	}
	public void setProbabilidad(float probabilidad) {
		this.probabilidad = probabilidad;
	}
	
}
