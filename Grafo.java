import java.util.HashMap;
import java.util.Map;

public class Grafo {
  public HashMap<Integer,Nodo> nodos = new HashMap<Integer,Nodo>();
  
  public void agregar_nodo(int id, Nodo n) {
      nodos.put(id,n);       
  }
  
  public void resetear(){
  	for (Map.Entry<Integer, Nodo> entry : nodos.entrySet()) {
      int key = entry.getKey();
      Nodo value = entry.getValue();
      value.visitado = false;
    }
  }

  public void imprimir(){
    for (Map.Entry<Integer, Nodo> entry : nodos.entrySet()) {
    	int key = entry.getKey();
    	Nodo value = entry.getValue();
    	System.out.println("key:"+key);
    	value.imprimir();
  	}
	}
}
