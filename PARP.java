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
	//  -Inicialización: sólo el nodo 1 se encuentra en B
	//  -Distmin[1]=-1
	//  -T contendrá los arcos del árbol de extensión mínima 
	//  -T =NULL 
	//  -para i=2 hasta n hacer 
	//  -  más_próximo[i] = 1 
	//  -  distmin[i] = L[i,1]
	//  -para i=1 hasta n-1 hacer 
	//  -  min=infinito
	//  -    para j=2 hasta n hacer 
	//  -      si 0 <= distmin[j] < min entonces: 
	//  -        min = distmin[j] 
	//  -        k = j
	//  -    T = T union {{mas_próximo[k], k }} 
	//  -    distmin [k]= -1 'se añade k a B
	//  -
	//  -    para j=2 hasta n hacer 
	//  -      si L[j,k] < distmin[j] entonces 
	//  -        distmin[j] = L[j,k] 
	//  -        más_próximo[j] = k
	//  -devolver T
	
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

//ALICIA
/*	public static Grafo Prim(Grafo g){
		Grafo h = new Grafo();
		Nodo aux = g.nodos.get(1);
		Nodo i = new Nodo(1);
		h.agregar_nodo(1,i);
		int k;
		Arista a;

		ArrayList<Arista> aristas = new ArrayList<Arista>();
		Iterator<Arista> it = aux.vecinos.iterator();
		while(it.hasNext()){
			a = it.next();
			aristas.add(a);
		}
		a=aristas.get(0);
		while( aristas.size()!=0 ){
			k=0;
			for(Integer j=0;j<aristas.size();j++){
				if ((aristas.get(j).beneficio-aristas.get(j).costo)> (a.beneficio-a.costo)){
					a=aristas.get(j);
					k=j;
				}
			}
			System.out.println(h.nodos);
			if (h.nodos.containsKey(a.id_v)){
				aristas.remove(k);

				for (Arista ai:aristas){
					System.out.print("("+ai.id+","+ai.id_v+")");
				}				
				System.out.println();
			}
			else{
				Nodo nuevo= new Nodo(a.id_v);
				h.agregar_nodo(nuevo.id,nuevo);
				aux=h.nodos.get(a.id);
				aux.agregar_arista(a);

				aux= g.nodos.get(a.id_v);
				it = aux.vecinos.iterator();

				while(it.hasNext()){
					a = it.next();
					aristas.add(a);
				}
				for (Arista ai:aristas){
					System.out.println(ai.id+","+ai.id_v);
				}
			}
		}
		return h;
		}
*/

//SERGIO 1
/*	public static Grafo Prim(Grafo g){
		//	-Se crean listas de nodos procesados y no procesados
		Grafo arbol = new Grafo();
  	HashMap<Integer,ArrayList<Tupla>> a_visitadas = new HashMap<Integer,ArrayList<Tupla>>();
		Tupla mejor_t = new Tupla(-100000,-100000);
		int mejor_c = 0;
		int mejor_b = 0;
		
		//	-El nodo visitado es 1
		Nodo visitado = g.nodos.get(1);
		
		//	-Mientras haya nodos sin visitar:
		while(g.sinVisitar()){
			//	-Se agrega el nodo al arbol	
			//		-Se crea un nuevo nodo con lo valor del nodo visitado
			Nodo n_i = new Nodo(visitado.id);
			arbol.agregar_nodo(n_i.id,n_i);
						
			//	-Se agrega a visitadas las aristas adyacentes
			ArrayList<Tupla> adyacentes = new ArrayList<Tupla>();
			
			for (Arista a_i : visitado.vecinos ){
				
				if(g.nodos.get(a_i.id_v).visitado != true){
					
					Tupla t = new Tupla(a_i.id_v,a_i.beneficio());
					System.out.println("["+t.id+","+t.b+"]");
					
					adyacentes.add(t);
				
					//	-Se elige entre las aristas visitadas la de mayor beneficio
					if(mejor_t.b < t.b){
						mejor_t = t;
						mejor_c = a_i.costo;
						mejor_b = a_i.beneficio;
					}
				}
			} // fin for a_i
			a_visitadas.put(visitado.id,adyacentes);
			//adyacentes.clear();
			//	-Se Agrega la arista al arbol
			//		-Se crea el nodo vecino
			Arista nuevo = new Arista(mejor_t.id,mejor_c,mejor_b);
			
			//		-Se agrega el nodo vecino a la lista de vecinos del nodo visitado
			arbol.nodos.get(n_i.id).vecinos.add(nuevo);
			
			//	-Se marca como visitado el nodo
			g.visitar(visitado.id);
			System.out.println(visitado.id+"-"+visitado.visitado);
			//	-Se visita el nodo recien agregado
			visitado = g.nodos.get(mejor_t.id);
			System.out.println(visitado.id+"-"+visitado.visitado);
				
			//	-Se elimina de visitadas la arista agregada al arbol

			// Se busca la proxima mejor arista
			for (Map.Entry<Integer,ArrayList<Tupla>> entry : a_visitadas.entrySet()) {
				int key = entry.getKey();
				ArrayList<Tupla> value = entry.getValue();
				System.out.println("k:"+key);
				for(Tupla t:value){
					System.out.println("("+t.id+","+t.b+")");
					if( t.b   ){
						//visitado = 
					}
				}


			}
			mejor_t.id = -100000;
			mejor_t.b = -100000;
			arbol.imprimir();
		} // fin while g.sinVisitar
		return arbol;
	}*/

	public static Grafo Prim(Grafo g){
    // 	inicializamos todos los nodos del grafo. La distancia la ponemos a infinito y el padre de cada nodo a NULL
    // 	for each u ∈ V[G] do
  	HashMap<Integer,Integer> distancia = new HashMap<Integer,Integer>();
  	HashMap<Integer,Nodo> padre = new HashMap<Integer,Nodo>();
  	HashMap<Integer,Nodo> cola = new HashMap<Integer,Nodo>();
    Nodo u = new Nodo(-1);

    for (Map.Entry<Integer, Nodo> uv : g.nodos.entrySet()) {
    	if(uv.getValue().id == 1){
    		// 	distancia[s]=0
				distancia.put(uv.getValue().id,0);
    	}else{
    		// distancia[u] = INFINITO
    		distancia.put(uv.getValue().id,-10000000);
    	}
    	// padre[u] = NULL
    	padre.put(uv.getValue().id,u);
    	// 	encolamos todos los nodos del grafo
   		// 	Encolar(cola, V[G])
    	//Nodo n = new Nodo(uv.getValue().id);
    	cola.put(uv.getKey(),uv.getValue());
    }
    // 	while cola != 0 do
    while(!cola.isEmpty()){
    	int mayor = -10000000;
		  // 		OJO: Se extrae el nodo que tiene distancia mínima y se conserva la condición de Cola de prioridad
		  for(Map.Entry<Integer, Integer> i : distancia.entrySet()){
		  	// 		u = extraer_minimo(cola)
		  	// 		u = extraer_maximo(cola) <MODIFICADO>
		  	if (mayor < i.getValue()){
		  		mayor = i.getValue();
		  		u = cola.get(i.getKey());
		  	}
		  }
		  cola.remove(u.id);
		  distancia.remove(u.id);
	    // 		for v ∈ adyacencia[u] do
    	for(Arista v : u.vecinos){
		  	// 			if ((v ∈ cola) && (distancia[v] > peso(u, v)) do
		  	// 			if ((v ∈ cola) && (distancia[v] < peso(u, v)) do <MODIFICADO>
		 		if( (cola.containsKey(v.id_v)) && (distancia.get(v.id_v) < v.beneficio()) ){
		 			//  			padre[v] = u
    			padre.put(v.id_v,u);
					//    		distancia[v] = peso(u, v)
    			distancia.put(v.id_v,v.beneficio());
		 		}
    		for(Map.Entry<Integer, Nodo> j : padre.entrySet()){
    			System.out.println("k:"+j.getKey());
    			System.out.println("v:"+j.getValue().id+"\n");
    		}
    		System.out.println("--------------------");
    	}
    }
		return g;
	} // fin funcion Prim


	public static void main(String[] args){
		// Armar Grafo
		Grafo g = ArmarGrafo(args[0]);

		// Hacer BFS partiendo de d para hallar componente conexa
		Grafo g_conexo = BFS(g);
		
		// Realizar Prim para conseguir arbol de mayor beneficio
		Grafo g_conexo2 = Prim(g_conexo);
	} // fin funcion main
} // fin class PARP
