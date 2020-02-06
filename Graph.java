import java.util.*;

public interface Graph<E> {
	/* OVERVIEW:  tipo di dato astratto che rappresenta una collezione di oggetti omogenei di tipo E,
	 * organizzati sotto forma di grafo diretto.
	 * 
	 * Typical Element: <V, E> dove V={v_0, v_1, ..., v_k} e E={<v_i, v_j>, ..., <v_m, v_n>} con tutti i v oggetti di tipo E
	 */
	
	/* aggiunge il nodo node al grafo come nodo isolato */ 
	public void addNode(E node) throws ExistingNodeException, NullPointerException;
	/* REQUIRES: vertex è un valore non-null
	 * MODIFIES: l'insieme V
	 * EFFECTS: aggiunge il nodo node al grafo come nodo isolato,
	 * se node è già presente nel grafo lancia una ExistingNodeException (eccezione non disponibile in java, checked);
	 * se node=null lancia una NullPointerException (eccezione disponibile in Java, unchecked).
	 */
	
	/* aggiunge un arco fra node1 e node2 al grafo */
	public void addEdge(E node1, E node2) throws ExistingEdgeException, NullPointerException, NonExistingNodeException;
	/* REQUIRES: node1 e node2 sono valori non-null, node1 e node2 devono appartenere al grafo
	 * MODIFIES: l'insieme E
	 * EFFECTS: aggiunge un arco da node1 a node2;
	 * se esiste già un arco da node1 a node2 lancia una ExistingEdgeException (eccezione non disponibile in Java, unchecked);
	 * se node1=null o node2=null lancia una NullPointerException (eccezione disponibile in Java, unchecked);
	 * se node1 o node2 non appartiene al grafo lancia una NonExistingNodeException (eccezione non disponibile in Java, unchecked). 
	 */
	
	/* rimuove il nodo node e tutti gli archi che partono o arrivano a node dal grafo */
	public void removeNode(E node) throws NonExistingNodeException, NullPointerException;
	/* REQUIRES: node è un valore non-null
	 * MODIFIES: gli insiemi V ed E
	 * EFFECTS: se il nodo node è un nodo isolato lo rimuove dal grafo,
	 * se non è isolato rimuove il nodo e tutti gli archi di cui è testa o coda;
	 * se node non appartiene al grafo lancia una NonExistingNodeException (eccezione non disponibile in Java, checked);
	 * se node=null lancia una NullPointerException (eccezione disponibile in Java, unchecked).
	 */
	
	/* rimuove l'arco che parte da node1 e arriva a node2 dal grafo */
	public void removeEdge(E node1, E node2) throws NonExistingNodeException, NonExistingEdgeException, NullPointerException;
	/* REQUIRES: node1 e node2 sono valori non-null
	 * MODIFIES: l'insieme E
	 * EFFECTS: rimuove l'arco che va da node1 a node2;
	 * se node1 o node2 non appartiene al grafo lancia una NonExistingNodeException (eccezione non disonibile in Java, unchecked);
	 * se non esiste un arco che va da node1 a node2 lancia una NonExistingEdgeException (eccezione non disponibile in Java, checked);
	 * se node1=null o node2=null lancia una NullPointerException (eccezione disponibile in Java, unchecked). 
	 */
	
	/* restituisce i nodi adiacenti al nodo node */
	public ArrayList<E> getAdjacentNodes(E node) throws NonExistingNodeException, NullPointerException;
	/* REQUIRES: node è un valore non-null
	 * EFFECTS: restituisce i nodi ai quali arriva un arco che parte da node, se non è un nodo isolato
	 * restituisce null;
	 * se node=null lancia una NullPoiterException (eccezione non disponibile in Java, unchecked);
	 * se node non appartiene al grafo lancia una NonExistingNodeException (eccezione non disponibile in Java, unchecked). 
	 */
	
	/* ritorna una lista di tutti i nodi del grafo */
	public ArrayList<E> getNodes();
	/* EFFECTS: restituisce i nodi contenuti nel grafo, se il grafo è vuoto restituisce una lista vuota
	 */
	
	/* restituisce la caridinalità di V, insieme dei nodi del grafo */
	public int getNodeCount();
	/* EFFECTS: restituisce il numero di nodi contenuti nel grafo, se il grafo è vuoto restituisce 0
	 */
	
	/* restituisce la cardinalità di E, insieme degli archi del grafo */
	public int getEdgeCount();
	/* restituisce il numero di archi del grafo, se il grafo non ha archi restituisce 0 
	 */
	
	/*restituisce true se il grafo contiene il nodo node */
	public boolean containsNode(E node) throws NullPointerException;
	/* REQUIRES: node è un valore non-null
	 * EFFECTS: restituisce true se il nodo node è contenuto nel grafo, false altrimenti;
	 * se node=null lancia una NullPointerException (eccezione disponibile in Java, unchecked) 
	 */
	
	/* restituisce true se il grafo contiene un arco da node1 a node2 */
	public boolean containsEdge(E node1, E node2);
	/* REQUIRES: node1 e node2 sono valori non-null
	 * EFFECTS: restituisce true se node2 è adiacente e node1, cioè se esiste un arco che parte da node1 e arriva a node2,
	 * altrimenti restituisce false;
	 * se node1=null o node2=null lancia una NullPointerException (eccezione disponibile in Java, unchecked) 
	 */
	
}
