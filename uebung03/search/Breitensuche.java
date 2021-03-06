package search;

import components.PathFinder;

import lists.Node;
import lists.NodeListImpl;

/**
 * Generische Klasse Breitensuche die von Pathfinder erbt und das Interface  SearchStrategy implementiert
 * die Klasse durchsucht Erbstufe auf Erbstufe vom bestimmten Knoten an alle Kindknoten nach einem Wert ist dieser im Knoten
 * enthalten speichert er den Knoten in eine Liste die dann nachdem alle Knoten durchlaufen sidn ausgegeben wird.
 * @author Chris Harsch und Severin Kohler
 *
 * @param <T>
 */
public class Breitensuche<T> extends PathFinder implements SearchStrategy<T> {

	@Override
	/**
	 * Methode durchsucht die Knoten ab dem Startknoten an nachdem Wert hierbei wird immer nur 1 Knoten
	 * Ueberprueft folgende werden in eine warteliste eingetragen und beim naechsten Durchlauf der Schleife 
	 * Ueberprueft. Abbruchbedingung ist hierbei das es keine Knoten mehr in der Warteschlange mehr gibt
	 *@param Node<Node<T>> node ist der uebergebene Knoten(Startknoten) 
	 * @param T toSearch ist der zu suchende Wert, Typ generisch
	 */
	public NodeListImpl<Node<T>> search(T node, T toSearch) {
		NodeListImpl found = new NodeListImpl();
		NodeListImpl warte = new NodeListImpl();
		warte.addFirst(node);
		path.clear();
		path.add(node);
		while (!warte.isEmpty()) {
			node = (T) warte.getLast();
			 if (toSearch.equals(((Node<T>) node).getValue())) {
				found.add(node);
			}
			warte.removeLast();
			for (Object node2 : ((Node<T>) node).getChildren()) {
				if (!path.contains(node2)) {
					warte.addFirst(node2);
					path.add(node2);
				}
			}
		}
		return found;
	}

	@Override
	/**
	 * Gibt den in search gespeicherten Wert zurueck, dieser beinhalted den Suchweg
	 * der Methode search beim letzten Aufruf.
	 * @param path wird geerbt aus der Funktion Pathfinder
	 */
	public NodeListImpl<Node> getPath() {
		return path;
	}
}
