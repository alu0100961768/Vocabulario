	public class Pareja{
		
		String palabra;
		int valor;
		
		public Pareja() {
			this.palabra= "";
			this.valor= -1;
		}
		
		public Pareja(String word) {
			this.palabra= word;
			this.valor= 1;
		}
		
		public String getPalabra() {
			return palabra;
		}
		public void setPalabra(String palabra) {
			this.palabra = palabra;
		}
		public int getValor() {
			return valor;
		}
		public void setValor(int valor) {
			this.valor = valor;
		}
		
		
		
	}