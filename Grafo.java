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

  public void usarBeneficiosArbol(int a,HashMap<Integer,Nodo> arbol,int b){
    // buscar padre de a en arbol
    int padre = arbol.get(a).id;
    // mientras a != b:
    while(a != b ){
      // buscar a en el grafo
      // eliminar beneficio de arista (a,padre)
      for(Arista ai: nodos.get(a).vecinos){
        if(ai.id_v == padre){
          ai.beneficio = 0;
        }
      }
      // eliminar beneficio de arista (padre,a)
      for (Arista ai : nodos.get(padre).vecinos){ 
        if( ai.id_v == a){
          ai.beneficio = 0;
        }
      }
      // a es su padre ahora
      a = padre;
      // padre es padre de a
      padre = arbol.get(a).id;
    }
  }

  public int valorArista(int id1, int id2){
    int r = -10000000;
    for (Map.Entry<Integer, Nodo> entry : nodos.entrySet()) {
      for (Arista ai : entry.getValue().vecinos){
        if(entry.getKey()==id1 && ai.id_v==id2 )
          r = ai.beneficio();
      }
    }
    return r;
  }

  public void imprimir(){
    for (Map.Entry<Integer, Nodo> entry : nodos.entrySet()) {
    	int key = entry.getKey();
    	Nodo value = entry.getValue();
    	//System.out.println("key:"+key);
    	value.imprimir();
  	}
	}
}
