import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Iterator;

public class PARP{
	//-Armar Grafo
	//	-Abrir archivo
	// 	  -Ver cuantos nodos hay
	// 	  -Crear nodos
	//	  	-Poner id
	//	  -Agregar nodos al grafo
	// 	  -Crear aristas
	//	    -Agregar Aristas al nodo correspondiente
	//  -Cerrar archivo
	//-Hacer BFS partiendo de d para hallar componente conexa
	//  -creamos una cola Q
	//  -agregamos origen a la cola Q
	//  -marcamos origen como visitado
	//  -mientras Q no este vacío:
	//     -sacamos un elemento de la cola Q llamado v
	//     -para cada vertice w adyacente a v en el Grafo: 
	//       -si w no ah sido visitado:
	//         -marcamos como visitado w
	//         -insertamos w dentro de la cola Q
	//-Realizar Prim para conseguir arbol de mayor beneficio
	//-Buscar camino en el arbol con mayor beneficio (Camino A)
	//-Eleminar beneficios de camino A
	//-Hacer Dijkstra desde la hoja seleccionada hasta d
	//-Calcular Vo = Beneficio_A - Costo_B

	public static Grafo ArmarGrafo(String arch){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		Grafo g = new Grafo();
		
		try {
			// Abrir archivo
			archivo = new File (arch);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			// Lectura del fichero
			String linea;
			while((linea=br.readLine())!=null){
				// Ver cuantos nodos hay
				if (linea.contains(":")){
					// Crear Nodos
					for (int i=1;i<Integer.parseInt(linea.split(":")[1].split(" ")[2])+1; i++){
						// Poner id
						Nodo n_i = new Nodo(i);
						// Agregar nodo al grafo
						g.agregar_nodo(n_i.id,n_i);
					}
				}else if (!(linea.contains("number"))){
					// Crear Aristas
					int atr_0 = Integer.parseInt(linea.split(" ")[0]);
					int atr_1 = Integer.parseInt(linea.split(" ")[1]);
					int atr_2 = Integer.parseInt(linea.split(" ")[2]);
					int atr_3 = Integer.parseInt(linea.split(" ")[3]);
					// Agregar Aristas a nodos correspondientes
					g.nodos.get(atr_0).agregar_vecino(atr_1,atr_2,atr_3);	
					g.nodos.get(atr_1).agregar_vecino(atr_0,atr_2,atr_3);	
				} // fin if contains number
			} // fin while readline
		} // fin try
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// Cerrar archivo
			try{                    
				if( null != fr ){   
					fr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		} // fin finally
		return g;
	} // fin funcion ArmarGrafo

	public static Grafo BFS(Grafo g){
		Grafo g2 = new Grafo();
		// Creamos una cola Q
		Queue<Nodo> q = new LinkedList<Nodo>();
		// Agregamos origen a la cola Q
		q.add(g.nodos.get(1));
		// Marcamos origen como visitado
		g.nodos.get(1).visitado = true;
		// Mientras Q no este vacío:
		while( q.size() != 0 ){	
		// Sacamos un elemento de la cola Q llamado v
			Nodo v = q.poll();
			// Agregamos el nodo al grafo conexo
			g2.agregar_nodo(v.id,v);
			// Para cada vertice w adyacente a v en el Grafo:
			for (Arista w : v.vecinos){
				// Si w no ha sido visitado:
				if( g.nodos.get(w.id_v).visitado != true ){
					// Marcamos como visitado w
					g.nodos.get(w.id_v).visitado = true;
					// Insertamos w dentro de la cola Q
					q.add(g.nodos.get(w.id_v));
				} // fin if
			} // fin for aristas
		} // fin while
		return g2;
	} // fin funcion BFS

  public static Grafo Prim(Grafo g){
      Grafo h = new Grafo();
      Nodo aux = g.nodos.get(1);
      Nodo i = new Nodo(1);
      g.agregar_nodo(1,i);

      ArrayList<Arista> aristas = new ArrayList<Arista>();
      Iterator<Arista> it = aux.vecinos.iterator();
      while(it.hasNext()){
      		//System.out.println(it);
          Arista a = it.next();
          a.imprimir();
          aristas.add(a);
      }
      h.imprimir();
      return h;
  }

	public static void main(String[] args){
		// Armar Grafo
		Grafo g = ArmarGrafo(args[0]);
		// g.imprimir();
		// Hacer BFS partiendo de d para hallar componente conexa
		Grafo g_conexo = BFS(g);
		Grafo g_conexo2 = Prim(g_conexo);
		// Realizar Prim para conseguir arbol de mayor beneficio
	} // fin funcion main
} // fin class PARP
