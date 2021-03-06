import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

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
	//	-inicializamos todos los nodos del grafo. 
	//		-La distancia la ponemos a infinito
	//		-Menos la del nodo raiz
	//		-el padre de cada nodo a NULL
	//	-encolamos todos los nodos del grafo
	//	-Mientras cola != 0:
	//		-Se extrae el nodo que tiene distancia mínima
	//		-Para v en vecinos[u]:
	//			-Si ((v ∈ cola) && (distancia[v] < peso(u, v)):
	//				-El padre de v es u
	//				-Se actualiza la distancia de v con su beneficio
	
	//-Buscar camino en el arbol con mayor beneficio (Camino A)
	
	//-Eleminar beneficios de camino A
	
	//-Hacer Dijkstra desde la hoja seleccionada hasta d	
	// 	Inicialmente S contendrá el vértice origen
	// 	Para cada v en el grafo: 
	// 		si v no es el origen:
	// 			Inicialmente, el predecesor de v en el camino mínimo construido hasta el momento es origen
	// 	Mientras existan vértices para los cuales no se ha determinado el camino mínimo
	//		Elegir un vértice w en (V-S) tal que su costo sea el mayor.
	//		Se agrega w al conjunto S
	//		Para cada v en (V-S):
	//			Se escoge, entre 
	//			el camino mayor hacia v que se tiene hasta el momento, 
	//			y el camino hacia v pasando por w mediante su camino mayor, 
	// 			el de mayor costo.
	// 		Si max(D[v],D[w]+C[w,v]) = D[w]+C[w,v] entonces P[v] ← w 
	
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
					
					// Alicia
					// g.nodos.get(atr_0).agregar_vecino(atr_0,atr_1,atr_2,atr_3);	
					// g.nodos.get(atr_1).agregar_vecino(atr_1,atr_0,atr_2,atr_3);

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
		g2.resetear();
		return g2;
	} // fin funcion BFS

	public static HashMap<Integer,Nodo> Prim(Grafo g){
  	HashMap<Integer,Integer> distancia = new HashMap<Integer,Integer>();
  	HashMap<Integer,Nodo> padre = new HashMap<Integer,Nodo>();
  	HashMap<Integer,Nodo> cola = new HashMap<Integer,Nodo>();
    Nodo u = new Nodo(-1);

		//	-inicializamos todos los nodos del grafo. 
    for (Map.Entry<Integer, Nodo> uv : g.nodos.entrySet()) {
			//	-La distancia la ponemos a infinito
    	if(uv.getValue().id == 1){
				//	-Menos la del nodo raiz
				distancia.put(uv.getValue().id,0);
    	}else{
    		distancia.put(uv.getValue().id,-10000000);
    	}
			//	-el padre de cada nodo a NULL
    	padre.put(uv.getValue().id,u);
			//	-encolamos todos los nodos del grafo;
    	cola.put(uv.getKey(),uv.getValue());
    }
		//	-Mientras cola != 0:
    while(!cola.isEmpty()){
			//	-Se extrae el nodo que tiene distancia mínima
    	int mayor = -10000000;
		  for(Map.Entry<Integer, Integer> i : distancia.entrySet()){
		  	if (mayor < i.getValue()){
		  		mayor = i.getValue();
		  		u = cola.get(i.getKey());
		  	}
		  }
		  cola.remove(u.id);
		  distancia.remove(u.id);
			//	-Para v en vecinos[u]:
    	for(Arista v : u.vecinos){
				//	-Si ((v ∈ cola) && (distancia[v] < peso(u, v)):
		 		if( (cola.containsKey(v.id_v)) && (distancia.get(v.id_v) < v.beneficio()) ){
					//	-El padre de v es u
    			padre.put(v.id_v,u);
					//	-Se actualiza la distancia de v con su beneficio
    			distancia.put(v.id_v,v.beneficio());
		 		}
    	}
    }
		return padre;
	} // fin funcion Prim

  public static boolean esHoja(HashMap<Integer,Nodo> g,int h){
    boolean eshoja = true;
    for( Map.Entry<Integer, Nodo> i : g.entrySet() ){
    	if(i.getValue().id == h){
    		eshoja = false;
    	}
    } // fin for map
    return eshoja;
  } // fin funcion esHoja

  public static int costoCamino(int id,int key , int destino ,HashMap<Integer,Nodo> arbol , Grafo g){
  	int c = 0;
  	Nodo nodo_conteo = g.nodos.get(id);
  	Nodo nodo_camino = g.nodos.get(key);

  	while (nodo_camino.id != destino){
  		nodo_conteo =  g.nodos.get(nodo_camino.id);
	  	nodo_camino = arbol.get(nodo_conteo.id);

	  	for(Arista ai : nodo_conteo.vecinos){
	  		if (ai.id_v == nodo_camino.id){
	  			c += ai.beneficio();
	  		} // fin if
	  	} // fin for
  	} // fin while
  	return c;
  	}	// fin funcion costoCamino

  public static int caminoMayorBeneficio(HashMap<Integer,Nodo> arbol,Grafo g){
  	int nodo = -1;
  	int beneficio = -100000;
  	for( Map.Entry<Integer, Nodo> i : arbol.entrySet() ){
  		if (esHoja(arbol,i.getKey()) && beneficio < costoCamino(i.getValue().id,i.getKey(),-1,arbol,g) ){
  			nodo = i.getKey();
  			beneficio = costoCamino(i.getValue().id,i.getKey(),-1,arbol,g);
  		}
  	}
  	return nodo;
  } // fin funcion caminoMayorBeneficio

  // ESTE DIJKSTRA FUNCIONA A MEDIAS
	public static HashMap<Integer,Nodo> Dijkstra(Grafo g,int origen){
		// Inicialmente S contendrá el vértice origen
		ArrayList<Integer> s = new ArrayList<Integer>();
  	HashMap<Integer,Integer> costo = new HashMap<Integer,Integer>();
  	HashMap<Integer,Nodo> padre = new HashMap<Integer,Nodo>();
		s.add(origen);
		costo.put(origen,-100000000);	
		// Para cada v en el grafo: 
		for( Map.Entry<Integer, Nodo> v : g.nodos.entrySet() ){
			// si v no es el origen:
			if( v.getKey() != origen ){
				costo.put(v.getKey(),g.valorArista(v.getKey(),origen));
				// Inicialmente, el predecesor de v en el camino mínimo construido hasta el momento es origen
				padre.put(v.getKey(),g.nodos.get(origen));
			} // fin if
		} // fin for

		// Mientras existan vértices para los cuales no se ha determinado el camino mínimo
		while( g.nodos.size() - s.size() > 0 ){
			//	Elegir un vértice w en (V-S) tal que su costo sea el mayor.
			for(Map.Entry<Integer, Nodo> w : g.nodos.entrySet()){
				int mayor = -10000000;
				if(!s.contains(w.getKey())){
					if( mayor < costo.get(w.getKey()) ){
						// Se agrega w al conjunto S
						s.add(w.getKey());
						mayor = costo.get(w.getKey());
					} // fin if mayor
				} // fin if contains
					
					//	Para cada v en (V-S):
					for(Map.Entry<Integer, Nodo> v : g.nodos.entrySet()){
						if(!s.contains(v.getKey())){
							// Se escoge, entre 
							// el camino mayor hacia v que se tiene hasta el momento, 
							int dv = costo.get(v.getKey());
							int dw = costo.get(w.getKey());
							//y el camino hacia v pasando por w mediante su camino mayor, 
							int cwv = g.valorArista(w.getKey(),v.getKey());
							//el de mayor costo.
							int max = Math.max( dv , dw + cwv );
							costo.put(v.getKey(),max);	

							// Si max(D[v],D[w]+C[w,v]) = D[w]+C[w,v] entonces P[v] ← w 
							if( max == dw + cwv ){
								padre.put(v.getKey(),w.getValue());
							}
						} // fin if contains
					} // fin for map
			} //fin for map
		} // fin while
		return padre;
	}// fin funcion Dijsktra

	//PROBADNO DE NUEVO DIJKSTRA
/*	public static HashMap<Integer,Nodo> Dijkstra2(Grafo g ,int origen){
		//Initialize:	F(1)=0
  	HashMap<Integer,Integer> cola = new HashMap<Integer,Integer>();
  	cola.put(origen,0);
		//F(j) = infinity, j=2,...,n
  	for(int i=1 ; i < g.nodos.size()+1;i++){
  		System.out.println(i);
  		if (i != origen)
  			cola.put(i,-10000000);
  	}

  	HashMap<Integer,Nodo> u = new HashMap<Integer,Nodo>();
		//U={1}
		u.put(origen,g.nodos.get(origen));
		//Iterate:	While (U != {}) Do:
		int j = -200000000;
		while (!u.isEmpty()){
			// j = arg min{F(i): i in U} 
			for(Map.Entry<Integer, Integer> i : cola.entrySet()){
				if(u.containsKey(i.getKey())){
					if(j <= cola.get(i.getKey()))
						j = i.getKey();
				}
			}
			System.out.println(u);
			// U = U\{j}
			u.remove(j);
			// For (k in S(j)) Do:
			for(  ){	
				//       G = min{F(k),D(j,k) + F(j)}
				//       if(G < F(k)) then {F(k) = G; 
				//			 U = U \/ {k}}
				//     End Do.
			}
			
		//End Do.
		}
		return u;
	}
*/
	public static void main(String[] args){
		// Armar Grafo
		Grafo g = ArmarGrafo(args[0]);

		// Hacer BFS partiendo de d para hallar componente conexa
		Grafo g_conexo = BFS(g);
		
		// Realizar Prim para conseguir arbol de mayor beneficio
		HashMap<Integer,Nodo> arbol_ida = Prim(g_conexo);
		
		//-Buscar camino en el arbol con mayor beneficio (Camino A)
		int a = caminoMayorBeneficio(arbol_ida,g_conexo);
		int ca = costoCamino(arbol_ida.get(a).id,a,-1,arbol_ida,g_conexo);

		//-Eleminar beneficios de camino A
		g_conexo.usarBeneficiosArbol(a,arbol_ida,1);
	 
		//-Hacer Dijkstra desde la hoja seleccionada hasta d
		HashMap<Integer,Nodo> arbol_regreso = Dijkstra(g_conexo,a);
		//System.out.println(arbol_regreso);
		
		int cb = costoCamino(arbol_regreso.get(1).id,1,a,arbol_regreso,g_conexo);
		System.out.println("Costo total de recorrido: "+ (ca+cb));
	} // fin funcion main
} // fin class PARP
