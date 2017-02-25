import java.util.HashMap;
import java.util.Map;

public class Grafo {
  public HashMap<Integer,Nodo> nodos = new HashMap<Integer,Nodo>();
  
  public void agregar_nodo(int id, Nodo n) {
      nodos.put(id,n);       
  }

  public void imprimir(){
  	for (Map.Entry<Integer, Nodo> entry : nodos.entrySet()) {
    	int key = entry.getKey();
    	Nodo value = entry.getValue();
    	System.out.println(key+":\n    id:"+value.id);
    	System.out.println("    vecinos:");
    	for(Arista a_i : value.vecinos){
    		System.out.println("         id_v:"+a_i.id_v);
    		System.out.println("            c:"+a_i.costo);
    		System.out.println("            b:"+a_i.beneficio);
    	}
  	}
	}
}
