import java.io.*;

public class PARP{
	//-Armar Grafo
	//	-Abrir archivo
	// 	-Ver cuantos nodos hay
	// 	-Crear nodos
	//		-Poner id
	//	-Agregar nodos al grafo
	// 	-Crear aristas
	//	-Agregar Aristas al nodo correspondiente
	//-Verificar si es conexo
	//-Buscar componente C tal que d pertenece C
	//-Realizar Prim para conseguir arbol de mayor beneficio
	//-Buscar camino en el arbol con mayor beneficio (Camino A)
	//-Eleminar beneficios de camino A
	//-Realizar Prim para conseguir camino de regreso a d
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
					//Arista a_i = new Arista(atr_1,atr_2,atr_3);
					//System.out.println(g.nodos.get(atr_0).agregar_vecino);
					g.nodos.get(atr_0).agregar_vecino(atr_1,atr_2,atr_3);	
					g.nodos.get(atr_1).agregar_vecino(atr_0,atr_2,atr_3);	
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepcion.
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

	public static void main(String[] args){

		Grafo g = ArmarGrafo(args[0]);
		g.imprimir();
	} // fin funcion main
} // fin class PARP
