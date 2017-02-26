import java.util.ArrayList;

public class Nodo {
  public int id;
  public ArrayList<Arista> vecinos;
  public boolean par;
  public boolean visitado;
  
  public Nodo(int n){
    id = n;
    vecinos = new ArrayList<>();
    visitado = false;
  }

  public void imprimir(){
    System.out.println("      id:"+ id);
    System.out.println("  visita:"+ visitado);
    System.out.println(" vecinos:");
    for(Arista a_i : vecinos){
      a_i.imprimir();
    }    
  }

  public void agregar_vecino(int n, int c, int b ) {
    Arista a = new Arista(n,c,b);
    vecinos.add(a);
  }

// Alicia
/*
  public void agregar_vecino(int o,int n, int c, int b ) {
    Arista a = new Arista(o,n,c,b);
    vecinos.add(a);
  }

  public void agregar_arista(Arista a){
    vecinos.add(a);  
  }*/
}
