import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
/* 
 * Test è utilizzato per testare i metodi del grafo. Si considerano prima i casi "sensibili" per vedere come si comporta l'implementazione.
 * Sono state utlizzate principalmente eccezioni unchecked per gestire possibili errori riguardanti gli argomenti inseriti dall'utilizzatore.
 * In questo main però queste eccezioni verrano catturate stampando il messaggio dell'eccezione. Si decide di catturarle per evitare che
 * il programma termini come dovrebbe fare, in una situazione di normale utilizzo, in seguito al lancio di un'eccezione unchecked e quindi
 * per inserire tutti i test dei metodi in un singolo main.
 * Successivamente si dà la possibilità di instanziare un grafo di String sul quale testare tutti i metodi.
 */
		
		/*
		 * TEST 1
		 */
		
		System.out.println("Test su grafo vuoto: \n");
		MyGraph<Integer> intGraph = new MyGraph<Integer>();
		
		Integer nodo1 = new Integer(23);
		System.out.println("Provando a rimuovere un nodo:");
		try { 
			intGraph.removeNode(nodo1);
		} catch (NonExistingNodeException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando a rimuovere un arco");
		Integer nodo2 = new Integer(13);
		try {
			intGraph.removeEdge(nodo1, nodo2);
		} catch (NonExistingNodeException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando ad aggiungere un arco:");
		try {
			intGraph.addEdge(nodo1, nodo2);
		} catch (NonExistingNodeException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando a chiedere i nodi adiacenti a un nodo:");
		try {
			intGraph.getAdjacentNodes(nodo1);
		} catch (NonExistingNodeException e){
			System.out.println(e); 
		}
		
		System.out.println("Provando a chiedere i nodi del grafo:");
		ArrayList<Integer> tmp = intGraph.getNodes();
		if ( tmp.isEmpty() )
			System.out.println("Come mi aspettavo restituisce una lista vuota");
		
		System.out.println("Provando a chiedere la cardinalità dell'insieme V dei nodi del grafo "
				+ "e dell'insieme E degli archi del grafo:");
		System.out.println(intGraph.getNodeCount()+" "+intGraph.getEdgeCount());
		
		System.out.println("Provando a chiedere se il grafo contiene un nodo:");
		System.out.println(intGraph.containsNode(nodo1));
		
		System.out.println("Provando a chiedere se il grafo contiene un arco:");
		try {
			intGraph.containsEdge(nodo1, nodo2);
		} catch (NonExistingNodeException e){
			System.out.println(e); 
		}
		
		System.out.println("Provando a chiedere il cammino minimo fra due nodi:");
		try {
			intGraph.shortestPath(nodo1, nodo2);
		} catch (NonExistingNodeException e){
			System.out.println(e); 
		} catch (NonExistingPathException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Provando a chiedere i nodi adiacenti a un nodo:");
		try {
			intGraph.getDiameter();
		} catch (NoPathsException e){
			System.out.println(e); 
		}
		
		/*
		 * TEST 2
		 */
		
		System.out.println("\n\nTest su grafo con un nodo: \n");
		MyGraph<String> stringGraph = new MyGraph<String>();
		
		String nodo3 = new String("Simone");
		stringGraph.addNode(nodo3);
		
		System.out.println("Provando ad aggiungere un nodo già presente:");
		try {
			stringGraph.addNode(nodo3);
		} catch (ExistingNodeException e) {
			System.out.println(e);
		}		
		
		System.out.println("Provando a rimuovere un arco");
		String nodo4 = new String("Dalila");
		try {
			stringGraph.removeEdge(nodo3, nodo4);
		} catch (NonExistingNodeException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando ad aggiungere un arco:");
		try {
			stringGraph.addEdge(nodo3, nodo4);
		} catch (NonExistingNodeException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando a chiedere i nodi adiacenti al nodo [Simone]:");
		ArrayList<String> tmp2 = stringGraph.getAdjacentNodes(nodo3);
		if ( tmp2.isEmpty() )
			System.out.println("Come mi aspettavo restituisce una lista vuota");
		
		System.out.println("Provando a chiedere i nodi del grafo:");
		System.out.println( stringGraph.getNodes() );
		
		System.out.println("Provando a chiedere la cardinalità dell'insieme V dei nodi del grafo "
				+ "e dell'insieme E degli archi del grafo:");
		System.out.println(stringGraph.getNodeCount()+" "+stringGraph.getEdgeCount());
		
		System.out.println("Provando a chiedere se il grafo contiene il nodo [Simone]:");
		System.out.println(stringGraph.containsNode(nodo3));
		
		System.out.println("Provando a chiedere se il grafo contiene un arco:");
		try {
			stringGraph.containsEdge(nodo3, nodo4);
		} catch (NonExistingNodeException e){
			System.out.println(e); 
		}
		
		System.out.println("Provando a chiedere il cammino minimo fra due nodi:");
		try {
			stringGraph.shortestPath(nodo3, nodo4);
		} catch (NonExistingNodeException e){
			System.out.println(e); 
		} catch (NonExistingPathException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Provando a chiedere i nodi adiacenti a un nodo:");
		try {
			stringGraph.getDiameter();
		} catch (NoPathsException e){
			System.out.println(e); 
		} 

		/*
		 * TEST 3
		 */
		
		System.out.println("\n\nTest passando argomenti che puntano a null\n");
		Integer nodo5=null;
		Integer nodo6=null;
		
		System.out.println("Provando ad aggiungere un nodo:");
		try {
			intGraph.addNode(nodo5);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando a rimuovere un nodo:");
		try { 
			intGraph.removeNode(nodo5);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando a rimuovere un arco");
		try {
			intGraph.removeEdge(nodo5, nodo6);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando ad aggiungere un arco:");
		try {
			intGraph.addEdge(nodo5, nodo6);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando a chiedere i nodi adiacenti a un nodo:");
		try {
			intGraph.getAdjacentNodes(nodo5);
		} catch (NullPointerException e){
			System.out.println(e); 
		}
		
		System.out.println("Provando a chiedere se il grafo contiene un nodo:");
		try {
			intGraph.containsNode(nodo5);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		System.out.println("Provando a chiedere se il grafo contiene un arco:");
		try {
			intGraph.containsEdge(nodo5, nodo6);
		} catch (NullPointerException e){
			System.out.println(e); 
		}
		
		System.out.println("Provando a chiedere il cammino minimo fra due nodi:");
		try {
			intGraph.shortestPath(nodo5, nodo6);
		} catch (NullPointerException e){
			System.out.println(e); 
		} catch (NonExistingPathException e) {
			System.out.println(e.getMessage());
		}
		
		/*
		 * TEST 4
		 */
		
		MyGraph<String> graph = new MyGraph<>();
		boolean cont = true;
		String todo;
		String todo2;
		Scanner input = new Scanner(System.in);
		System.out.println("\n\nE' stato creato un grafo vuoto di String.");


		cont = true;
		while (cont) {
			System.out.println("\n\nScegliere il metodo da applicare al grafo:\n");
			System.out.println("addNode");
			System.out.println("addEdge");
			System.out.println("removeNode");
			System.out.println("removeEdge");
			System.out.println("getAdjacentNodes");
			System.out.println("getNodes");
			System.out.println("getNodeCount");
			System.out.println("getEdgeCount");
			System.out.println("containsNode");
			System.out.println("containsEdge");
			System.out.println("shortestPath");
			System.out.println("getDiameter");
			System.out.println("terminaProgramma");
			System.out.println("(Per scegliere digitare una fra le possibilità date sopra)");
			todo = input.nextLine();
			switch (todo) {
			case "addNode":
				System.out.println("Digitare il valore (di tipo String) nodo da inserire:");
				todo = input.nextLine();
				graph.addNode(todo);
				System.out.println("E' stato inserito il nodo "+todo+".");
				break;
			case "addEdge":
				System.out.println("Digitare il valore (di tipo String) del nodo coda dell'arco da inserire:");
				todo = input.nextLine();
				System.out.println("Digitare il valore (di tipo String) del nodo testa dell'arco da inserire:");
				todo2 = input.nextLine();
				graph.addEdge(todo, todo2);
				System.out.println("E' stato inserito l'arco ("+todo+", "+todo2+").");
				break;
			case "removeNode":
				System.out.println("Digitare il valore (di tipo String) del nodo da rimuovere:");
				todo = input.nextLine();
				graph.removeNode(todo);
				System.out.println("E' stato rimosso il nodo "+todo+".");
				break;
			case "removeEdge":
				System.out.println("Digitare il valore (di tipo String) del nodo coda dell'arco da rimuovere:");
				todo = input.nextLine();
				System.out.println("Digitare il valore (di tipo String) del nodo testa dell'arco da rimuovere:");
				todo2 = input.nextLine();
				graph.removeEdge(todo, todo2);
				System.out.println("E' stato rimosso l'arco ("+todo+", "+todo2+").");
				break;
			case "getAdjacentNodes":
				System.out.println("Digitare il valore (di tipo String) del nodo di cui si chiedono i nodi adiacenti:");
				todo = input.nextLine();
				tmp2 = graph.getAdjacentNodes(todo);
				System.out.println("I nodi adiacenti di "+todo+" sono:");
				for (String nodo : tmp2)
					System.out.println(nodo);
				break;
			case "getNodes":
				System.out.println("I nodi del grafo sono:");
				tmp2 = graph.getNodes();
				for (String nodo : tmp2)
					System.out.println(nodo);
				break;
			case "getNodeCount":
				System.out.println("La cardinalità dell'insieme V dei nodi del grafo è:");
				System.out.println(graph.getNodeCount());
				break;
			case "getEdgeCount":
				System.out.println("La cardinalità dell'insieme E degli archi del grafo è:");
				System.out.println(graph.getEdgeCount());
				break;
			case "containsNode":
				System.out.println("Digitare il valore (di tipo String) del nodo per cui si chiede se appartiene al grafo:");
				todo = input.nextLine();
				if (graph.containsNode(todo))
					System.out.println("Il nodo "+todo+" appartiene al grafo.");
				else
					System.out.println("Il nodo "+todo+" non appartiene al grafo.");
				break;
			case "containsEdge":
				System.out.println("Digitare il valore (di tipo String) della coda dell'arco per cui si chiede se appartiene al grafo:");
				todo = input.nextLine();
				System.out.println("Digitare il valore (di tipo String) della testa dell'arco per cui si chiede se appartiene al grafo:");
				todo2 = input.nextLine();
				if (graph.containsEdge(todo, todo2))
					System.out.println("L'arco ("+todo+", "+todo2+") appartiene al grafo.");
				else
					System.out.println("L'arco ("+todo+", "+todo2+") non appartiene al grafo.");
				break;
			case "shortestPath":
				System.out.println("Digitare il valore (di tipo String) del nodo sorgente del cammino:");
				todo = input.nextLine();
				System.out.println("Digitare il valore (di tipo String) del nodo destinazione del cammino:");
				todo2 = input.nextLine();
				System.out.println("Il cammino minimo fra i due nodi è lungo:");
				try {
					System.out.println(graph.shortestPath(todo, todo2));
				} catch (NonExistingPathException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "getDiameter":
				System.out.println("Il diametro del grafo è:");
				try {
					System.out.println(graph.getDiameter());
				} catch (NoPathsException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "terminaProgramma":
				System.out.println("Termino il programma...");
				cont = false;
				break;
			default:
				System.out.println("Scelta non valida. Riprovare...");
				break;
				
			}
		}
		input.close();
	}
}
