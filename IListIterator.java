import java.util.ListIterator;

/**
 * Interface between list iterators and lists with public list items.
 * 
 * @author Christoph Stamm
 *
 */
public interface IListIterator<E> extends ListIterator<E> {
	/**
	 * Precondition: next() or previous() has been called
	 * @return the corresponding list item of the last returned contents
	 */
	public ListItem getVisited();
}
