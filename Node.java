import java.util.ArrayList;
import java.util.List;

public class Node<E> {
	
	private final E node;
	private List<E> adjacents;
	private boolean visited;
	private E previous;
	private int distance;
	
	public Node(E n) {
		node = n;
		adjacents = new ArrayList<E>();
	}
	
	/* aggiunge alla lista dei nodi adiacenti il nodo head */
	public void addEdge(E head) {
		this.adjacents.add(head);
	}

	/* rimuove dalla lista dei nodi adiacenti il nodo head */
	public void removeEdge(E head) {	
		this.adjacents.remove(head);
	}
	
	/* restituisce il valore del nodo */
	public E getNode() {
		return node;
	}
	
	/* controlla se la lista dei nodi adiacenti contiene head */
	public boolean containsEdge(E head) {
		return this.adjacents.contains(head);
	}
	
	/* restituisce la lista dei nodi adiacenti */
	public ArrayList<E> getAdjacentNodes() {
		ArrayList<E> tmp = new ArrayList<E>();
		for (int i=0; i<this.adjacents.size(); i++)
			tmp.add(this.adjacents.get(i));
		return tmp;
	}
	
	/* restituisce la cardinalità della lista dei nodi adiacenti */
	public int getAdjacentCount() {
		return this.adjacents.size();
	}
	
	/* controlla se il nodo è stato visitato da una BFS */
	public boolean isVisited(){
		return this.visited;
	}

	/* ritorna il nodo precedente della visita BFS */
	public E getPrevious() {
		return this.previous;
	}
	
	/* ritorna la distanza dal nodo sorgente della visita BFS */
	public int getDistance() {
		return this.distance;
	}
	
	/* setta il nodo come visitato o non visitato nella vista BFS */
	public void setVisited(boolean s) {
		this.visited = s;
	}
	
	/* setta p come nodo precedente nella visita BFS */
	public void setPrevious(E p) {
		this.previous = p;
	}
	
	/* setta la distanza dal nodo sorgente nella visita BFS */
	public void setDistance(int d){
		this.distance = d;
	}
	
}
