public class Arista {
  
  // Alicia
  //public int id;

  public int id_v;
  public int costo;
  public int beneficio;
  
  // Alicia
  //public Arista(int o, int v, int c, int b) {
      //id = o;

  public Arista(int v, int c, int b) {
      id_v = v;
      costo = c;
      beneficio = b;
  }

	public int beneficio(){
  	return beneficio - costo ;
	}

	public void pasar(){
		beneficio = 0;
	}

  public void imprimir(){
	System.out.println("         id_v:"+id_v);
	System.out.println("            c:"+costo);
	System.out.println("            b:"+beneficio);
  }
}