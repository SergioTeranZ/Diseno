public class Arista {
    public int id;
    public int id_v;
    public int costo;
    public int beneficio;
    
    public Arista(int o, int v, int c, int b) {
        id = o;
        id_v = v;
        costo = c;
        beneficio = b;
    }

    public void imprimir(){
		System.out.println("         id_v:"+id_v);
		System.out.println("            c:"+costo);
		System.out.println("            b:"+beneficio);
    }
}