import java.util.ArrayList;

public class Nodo {
  public int id;
  public ArrayList<Arista> vecinos;
  public boolean par;
  
  public Nodo(int n){
    id = n;
    vecinos = new ArrayList<>();
    par = true;
  }

  public void imprimir(){
    System.out.println("Nodo id:"+ id);
  } 

  public void agregar_vecino(int n, int c, int b ) {
    Arista a = new Arista(n,c,b);
    vecinos.add(a);
    int tam = vecinos.size();
    par = tam%2==0;
  }
}
