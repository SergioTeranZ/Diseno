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

  public void visitar(int id){
    System.out.println("VISITE A --------------->"+nodos.get(id).id);
    nodos.get(id).visitado = true;
  }

  public boolean sinVisitar(){
    boolean faltan = false;
    for (Map.Entry<Integer, Nodo> entry : nodos.entrySet()) {
      int key = entry.getKey();
      Nodo value = entry.getValue();
      if (value.visitado == false) {
        faltan = true;
      }
    }
    return faltan;
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
