import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MyGraph<E> implements Graph<E> {
	/* 
	 * OVERVIEW:  tipo di dato astratto che rappresenta una collezione di oggetti omogenei di tipo E,
	 *            organizzati sotto forma di grafo diretto.
	 * 
	 * AF =       <V, E> tale che V = { adjList.keySet() } e
	 *            E = { <adjList.get(i).getNode(), adjList.get(i).getAdjacentNodes()>, ... ,
	 *            <adjList.get(j).getNode(), adjList.get(j).getAdjacentNodes()>}
	 * 
	 * IR =       adjList != null &&
	 *            forall i. adjList.containsKey(i) -> i == ( adjList.get(key).getNode() &&
	 *            adjList.get(i) != null && adjList.containsKey(adjList.get(i).getPrevious()) &&
	 *            adjList.get(i).getPrevious() != adjList.get(i)
	 *            forall i. adjList.containsKey(i) -> forall j.
	 *            adjList.get(i).getAdjacentNodes().contains(j)  -> adjList.containsKey(j) 
	 */

	private HashMap<E, Node<E>> adjList;
	
	public MyGraph() {
		adjList = new HashMap<>();
	}
	

	/* aggiunge il nodo node al grafo come nodo isolato */ 
	public void addNode(E node) {
		if (node ==  null)
			throw new NullPointerException("l'argomento passato punta a null!");
		if ( this.adjList.containsKey(node) )
			throw new ExistingNodeException("il nodo da inserire è già contenuto nel grafo!");

		this.adjList.put(node, new Node<>(node));
	}
	/*
	 * REQUIRES:  node è un valore non-null, node non è contenuto nel grafo.
	 * MODIFIES:  adjList
	 * EFFECTS:   quando il metodo viene chiamato associa alla chiave node un nuovo valore Node<E>
	 *            chiamando il costruttore della classe Node<E> con il parametro node;
	 *            se node==null lancia una NullPointerException (eccezione disponibile in Java,
	 *            unchecked);
	 *            se node è già presente nel grafo lancia una ExistingNodeException (eccezione
	 *            non disponibile in Java, unchecked).      
	 */ 
	

	/* aggiunge un arco fra node1 e node2 al grafo */
	public void addEdge(E node1, E node2) {
		if ((node1 == null) || (node2 == null))
			throw new NullPointerException("l'argomento passato punta a null!");
		if ( !this.adjList.containsKey(node1) )
			throw new NonExistingNodeException("la coda dell'arco non è contenuta nel grafo!");
		if ( !this.adjList.containsKey(node2) )
			throw new NonExistingNodeException("la testa dell'arco non è contenuta nel grafo!");
		if ( this.adjList.get(node1).containsEdge(node2) )
			throw new ExistingEdgeException("l'arco da inserire è già contenuto nel grafo!");
		
		this.adjList.get(node1).addEdge(node2);
	}
	/*
	 * REQUIRES:  node1 e node2 sono valore non-null, node1 e node2 sono nodi contenuti nel grafo,
	 *            (node1, node2) è un arco non contenuto nel grafo.
	 * MODIFIES:  adjList.get(node1).getAdjacentsNodes()
	 * EFFECTS:   quando il metodo viene chiamato lancia il metodo addEdge con il parametro node1 sul valore
	 *            di tipo Node<E> associato alla chiave node1 in adjList;
	 *            se node1==null o node2==null lancia una NullPointerException (eccezione disponibile
	 *            in Java, unchecked);
	 *            se node1 o node2 non è contenuto nel grafo lancia una NonExistingNodeException (eccezione
	 *            non disponibile in Java, unchecked);
	 *            se (node1, node2) è un arco contenuto nel grafo lancia una ExistingEdgeException
	 *            (eccezione non disponibile in Java, unchecked).
	 *            
	 */

	/* rimuove il nodo node e tutti gli archi che partono o arrivano a node dal grafo */
	public void removeNode(E node) {
		if ( node == null )
			throw new NullPointerException("l'argomento passato punta a null!");
		if ( !this.adjList.containsKey(node) )
			throw new NonExistingNodeException("il nodo da rimuovere non è contenuto nel grafo!");
		
		for ( Node<E> node2 : this.adjList.values() )
			node2.removeEdge(node);
		
		this.adjList.remove(node);
	}
	/*
	 * REQUIRES:  node è un valore non-null, node è un nodo contenuto nel grafo.
	 * MODIFIES:  adjList
	 * EFFECTS:   quando il metodo viene chiamato rimuove tutti gli archi che hanno node come testa e successivamente rimuove
	 *            il nodo node con gli archi che hanno node come coda;
	 *            se node==null lancia una NullPointerException (eccezione disponibile in Java, unchecked);
	 *            se node non è contenuto nel grafo lancia una NonExistingNodeException (eccezione non disponibile in Java, unchecked).
	 */
	

	/* rimuove l'arco che parte da node1 e arriva a node2 dal grafo */
	public void removeEdge(E node1, E node2) {
		if ( node1 == null )
			throw new NullPointerException("il primo argomento passato punta a null!");
		if ( node2 == null )
			throw new NullPointerException("il secondo argomento passato punta a null!");
		if ( !this.adjList.containsKey(node1) )
			throw new NonExistingNodeException("la coda dell'arco da rimuovere non è contenuta nel grafo!");
		if ( !this.adjList.containsKey(node2) )
			throw new NonExistingNodeException("la testa dell'arco da rimuovere non è contenuta nel grafo!");
		if ( !this.adjList.get(node1).containsEdge(node2) )
			throw new NonExistingEdgeException("l'arco da rimuovere non è contenuto nel grafo!");
		
		this.adjList.get(node1).removeEdge(node2);
	}
	/*
	 * REQUIRES:  node1 e node2 sono valori non-null, node1 e node2 sono nodi contenuti nel grafo,
	 *            (node1, node2) è un arco contenuto nel grafo.
	 * MODIFIES:  adjList.get(node1)
	 * EFFECTS:   quando il metodo viene chiamato rimuove il nodo node2 dalla lista nella quale sono contenuti i nodi a cui
	 *            arriva un arco da node1 rimuovendo così l'arco (node1, node2) dal grafo;
	 *            se node1==null o node2==null lancia una NullPointerException (eccezione disponibile in Java, unchecked);
	 *            se node1 o node2 non è contenuto nel grafo lancia una NonExistingNodeException (eccezione non disponibile in
	 *            Java, unchecked);
	 *            se l'arco (node1, node2) non è contenuto nel grafo lancia una NonExistingEdgeException (eccezione non disponibile in
	 *            Java, unchecked).
	 *            
	 */

	/* restituisce i nodi adiacenti al nodo node */
	public ArrayList<E> getAdjacentNodes(E node) {
		if ( node == null )
			throw new NullPointerException("l'argomento passato punta a null!");
		if ( !adjList.containsKey(node) )
			throw new NonExistingNodeException("il nodo di cui si richiedono i nodi adiacenti non è contenuto nel grafo!");		
		
		return this.adjList.get(node).getAdjacentNodes();
	}
	/*
	 * REQUIRES:  node è un valore non-null, node è contenuto nel grafo.
	 * EFFECTS:   quando il metodo viene chiamato restitituisce una lista contenente i nodi ai quali arriva un arco da node;
	 *            se node==null lancia una NullPointerException (eccezione disponibile in Java, unchecked);
	 *            se node non è contenuto nel grafo lancia una NonExistingNodeException (eccezione non disponibile in Java, unchecked).
	 */

	/* ritorna una lista di tutti i nodi del grafo */
	public ArrayList<E> getNodes() {

		ArrayList<E> tmp =  new ArrayList<E>();
		for ( Node<E> node : this.adjList.values() )
			tmp.add(node.getNode());
		return tmp;
	}
	/*
	 * EFFECTS:   quando il metodo viene chiamato restituisce una lista contenente tutti i valori di tipo E che sono nodi del grafo.
	 */

	/* restituisce la caridinalità di V, insieme dei nodi del grafo */
	public int getNodeCount() {
		return this.adjList.size();
	}
	/*
	 * EFFECTS:   quando il metodo viene chiamato restituisce la cardinalità dell'insieme V contenente i nodi del grafo.
	 */

	/* restituisce la cardinalità di E, insieme degli archi del grafo */
	public int getEdgeCount() {
		int edgeCount = 0;
		for( Node<E> node : this.adjList.values() )
			edgeCount += node.getAdjacentCount();
		return edgeCount;
	}
	/*
	 * EFFECTS:   quando il metodo viene chiamato conta il numero di archi presenti nel grafo, 
	 *            cioè la cardinalità dell'insieme E contenente gli archi del grafo,
	 *            e lo restituisce.
	 */

	/*restituisce true se il grafo contiene il nodo node */
	public boolean containsNode(E node) {
		if ( node == null )
			throw new NullPointerException("l'argomento passato punta a null!");
		
		return this.adjList.containsKey(node);
	}
	/*
	 * REQUIRES:  node è una valore non-null.
	 * EFFECTS:   quando il metodo viene lanciato controlla che node sia contenuto fra i nodi del grafo e in caso di riscontro positivo
	 *            restituisce true, altrimenti restituisce false.
	 */

	/* restituisce true se il grafo contiene un arco da node1 a node2 */
	public boolean containsEdge(E node1, E node2) {
		if ( node1 == null )
			throw new NullPointerException("il primo argomento passato punta a null!");
		if ( node2 == null )
			throw new NullPointerException("il secondo argomento passato punta a null!");
		if ( !this.adjList.containsKey(node1) )
			throw new NonExistingNodeException("la coda dell'arco non è contenuta nel grafo!");
		if ( !this.adjList.containsKey(node2) )
			throw new NonExistingNodeException("la testa dell'arco non è contenuta nel grafo!");
		
		return this.adjList.get(node1).containsEdge(node2);
	}
	/*
	 * REQUIRES:  node1 e node2 sono valori non-null, node1 e node2 sono nodi contenuti nel grafo.
	 * EFFECTS:   quando il metodo viene chiamato controlla che la lista dei nodi ai quali arriva un arco da node1 contenga node2 e in caso
	 *            di riscontro positivo restituisce true, altrimenti restituisce false;
	 *            se node1==null o node2==null lancia una NullPointerException (eccezione disponibile in Java, unchecked);
	 *            se node1 o node2 non è contenuto nel grafo lancia una NonExistingNodeException (eccezione non disponibile in
	 *            Java, unchecked).
	 */

	/* restituisce la lunghezza del cammino minimo fra i nodi source e destination */
	public int shortestPath(E source, E destination) throws NonExistingPathException {
		if ( source == null ) 
			throw new NullPointerException("il primo argomento passato punta a null!");
		if ( destination == null )
			throw new NullPointerException("il secondo argomento passato punta a null!");
		if ( !this.adjList.containsKey(source) )
			throw new NonExistingNodeException("il nodo sorgente del cammino minimo non è contenuto nel grafo!");
		if ( !this.adjList.containsKey(destination) )
			throw new NonExistingNodeException("il nodo destinazione del cammino minimo non è contenuto nel grafo!");
		if ( source.equals(destination) )
			throw new SameSourceDestinationException("sorgente e destinazione sono lo stesso nodo!");

		initialization();

		Queue<E> queue = new LinkedList<E>();
		E current = source;
		queue.add(source);
		while ( !queue.isEmpty() ) {
			current = queue.poll();
			this.adjList.get(current).setVisited(true);
			if (current.equals(destination)){
				break;
			} else {
				for ( E node : this.adjList.get(current).getAdjacentNodes() ) {
					if ( !this.adjList.get(node).isVisited() ) {
						queue.add(node);
						this.adjList.get(node).setDistance(this.adjList.get(current).getDistance()+1);
					}
				}
			}	
		}
		if ( this.adjList.get(current).getDistance() == 0 )
			throw new NonExistingPathException("il cammino cercato non esiste!");
		return this.adjList.get(current).getDistance();
	}
	/*
	 * REQUIRES:  source e destination sono valori non-null, source e destination sono nodi contenuti nel grafo,
	 *            source e destination non sono lo stesso nodo.
	 * EFFECTS:   quando il metodo viene chiamanto partendo dal nodo source cerca attraverso una breadth first search
	 *            il nodo destination. Nella ricerca controlla prima i nodi a distanza uno, successivamente quelli a distanza due,
	 *            cioè i nodi nelle liste di adiacenza dei nodi a distanza uno; allontanandosi sempre di più da source.
	 *            Nella ricerca si preoccupa di non visitare due volte lo stesso nodo.
	 *            Una volta trovato il nodo restituisce il numero archi attraversati che
	 *            per le proprietà delle della breadth first search sarà la lunghezza del cammino minimo da source a destination;
	 *            se non raggiunge il nodo destination significa che non ci sono cammini da source a destination e lancia una
	 *            NonExistingPathException (eccezione non disponibile in Java, unchecked);
	 *            se source==null o destination==null lancia una NullPointerException (eccezione disponibile in Java, unchecked);
	 *            se source o destination non è contenuto nel grafo lancia una NonExistingNodeException (eccezione non disponibile
	 *            in Java, unchecked);
	 *            se source==destination lancia una SameSourceDestinationException (eccezione non disponibile in Java, unchecked).
	 */
	
	/* metodo privato che utilizzo per inizializzare le variabili di istanza dei nodi che saranno utilizzate dal metodo shortestPath */
	private void initialization() {
		this.adjList.values().forEach(node -> {
			node.setPrevious(null);
			node.setVisited(false);
			node.setDistance(0);
		});
	}
	
	/* restituisce il diametro del grafo, ossia la lunghezza del più lungo dei cammini minimi */
	public int getDiameter() throws NoPathsException {
		
		HashMap<E,Integer> maxShortestPaths = new HashMap<>();
		int diameter = 0;
		
		for ( E key : this.adjList.keySet() ) {
			maxShortestPaths.put(key, getMaxShortestPathFrom(key));
		}
		for ( Integer i : maxShortestPaths.values() ) {
			if ( i > diameter )
				diameter = i;
		}
		if ( diameter == 0 )
			throw new NoPathsException("Non ci sono cammini all'interno del grafo: il diametro è 0");
		return diameter;
	}
	/*
	 * EFFECTS:   quando il metodo viene chiamato cerca per ogni nodo del grafo il cammino minimo verso tutti gli altri nodi del grafo,
	 *            sfruttando le proprietà della breadth first search.
	 *            Successivamente valuta quale fra i camminimi minimi che partono dal nodo che sta valutando è il maggiore.
	 *            Infine, una volta trovato il maggior cammino minimo per ogni nodo, nei quali il nodo è sorgente del cammino, valuta
	 *            qual'è il cammino minimo più lungo fra tutti i nodi del grafo e quindi il diametro del grafo.
	 */
	
	/* metodo privato con utilizzo per calcolare i cammini minimi da un nodo a tutti gli altri nodi del grafo sfruttando
	 * le proprietà della breadth first search */
	private int getMaxShortestPathFrom(E source) {
		initialization();
		int maxShortestPath = 0;
		Queue<E> queue = new LinkedList<E>();
		E current;
		queue.add(source);
		while ( !queue.isEmpty() ) {
			current = queue.poll();
			this.adjList.get(current).setVisited(true);
			for ( E node : this.adjList.get(current).getAdjacentNodes() ) {
				if ( !this.adjList.get(node).isVisited() ) {
					queue.add(node);
					this.adjList.get(node).setDistance(this.adjList.get(current).getDistance()+1);
				}
			}
		}
		for ( Node<E> node : this.adjList.values() ) {
			if ( node.getDistance() > maxShortestPath )
				maxShortestPath = node.getDistance();
		}
		return maxShortestPath;
	}
}
