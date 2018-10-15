import java.util.*;

public class DLinkedList<E> extends AbstractList<E> implements List<E>, IList<E> {

    ListItem<E> list_head, list_tail;
    int size;


    public DLinkedList () {

    }
    

    @Override
    public void add(int index, E data) {
    	modCount++;
        ListItem newListItem = new ListItem(data);

        if(this.size == 0){
            list_head = list_tail = newListItem;
        }else{
            newListItem.previousItem = list_tail;
            list_tail.nextItem = newListItem;
            this.list_tail = newListItem;
        }
        this.size++;
    }

    @Override
    public boolean checkMembership(ListItem item) {
        return item.parentList == this;
    }

    @Override
    public ListItem head() {
        return list_head;
    }

    @Override
    public ListItem tail() {
        return list_tail;
    }

    @Override
    public ListItem next(ListItem item) {
        return item.nextItem;
    }

    @Override
    public ListItem previous(ListItem item) {
        return item.previousItem;
    }

    @Override
    public ListItem cyclicNext(ListItem item) {
        ListItem result = null;
        //check if item exists in list (stated as precondition in iList)
        if(get(item) != null){
            result = item.nextItem;
            //check if nextItem exists (won't if item = tail). If not, set nextItem to head (cyclic behaviour).
            if(result == null){
                result = this.list_head;
            }
        }else{
            throw new NoSuchElementException();
        }
        return result;
    }

    @Override
    public ListItem cyclicPrevious(ListItem item) {
        ListItem result = null;
        //check if item exists in list (stated as precondition in iList)
        if(get(item) != null){
            result = item.previousItem;
            //check if previousItem exists (won't if item = head). If not, set nextItem to tail (cyclic behaviour).
            if(result == null){
                result = this.list_tail;
            }
        }else{
            throw new NoSuchElementException();
        }
        return result;
    }

    /**
     * Deletes item while iterating.
	 * If next = true: returns successor of item or null if item is the last.
            * If next = false: returns predecessor of item or null if item is the first.
            * Precondition: item is in this list
     * */
    @Override
    public ListItem delete(ListItem item, boolean next) {
        //save pointers to be able to use them after the deletion of the item
        ListItem temp_nextItem = item.nextItem;
        ListItem temp_previousItem = item.previousItem;
        //check precondition
        if(get(item) == null){throw new NoSuchElementException();
        }else{
            //swap pointers of previous & next Item
            item.previousItem.nextItem = item.nextItem;
            item.nextItem.previousItem = item.previousItem;
            //mark the item as null for GC
            item = null;
        }

        if(next){
            return temp_nextItem;
        }else{
            return  temp_previousItem;
        }

    }

    @Override
    public ListItem cyclicDelete(ListItem item, boolean next) {
    	 //save pointers to be able to use them after the deletion of the item
        ListItem temp_nextItem = cyclicNext(item);
        ListItem temp_previousItem = cyclicPrevious(item);
        //check precondition
        if(get(item) == null){throw new NoSuchElementException();
        }else{
            //swap pointers of previous & next Item
            item.previousItem.nextItem = item.nextItem;
            item.nextItem.previousItem = item.previousItem;
            //mark the item as null for GC
            item = null;
        }

        if(next){
            return temp_nextItem;
        }else{
            return  temp_previousItem;
        }
    }

    @Override
    public E get(ListItem item) {
        return null;
    }

    @Override
    public void set(ListItem item, E data) {
    	item.m_data = data;
    }

    @Override
    public E remove(ListItem item) {
    	modCount++;
    	ListItem<E> help = delete(item, true);
        return help.m_data;
    }

    @Override
    public ListItem addHead(E data) {
    	modCount++;
    	ListItem<E> newElement = new ListItem<E>(data);
    	newElement.nextItem = list_head;
    	list_head.previousItem = newElement;
    	list_head = newElement;
        return newElement;
    }

    @Override
    public ListItem addTail(E data) {
    	modCount++;
    	ListItem<E> newElement = new ListItem<E>(data);
    	newElement.previousItem = list_tail;
    	list_tail.nextItem = newElement;
    	list_tail = newElement;
        return newElement;
    }

    @Override
    public ListItem addAfter(ListItem item, E data) {

    	modCount++;
    	if(get(item) != null) {
    		ListItem<E> newElement = new ListItem<E>(data);
    		newElement.nextItem = item.nextItem;
    		newElement.previousItem = item;
    		item.nextItem = newElement;
    		return newElement;
    	} else {
    		return addTail(data);
    	}
    }

    @Override
    public ListItem addBefore(ListItem item, E data) {
    	modCount++;
    	if(get(item) != null) {
    		ListItem newElement = new ListItem(data);
    		newElement.previousItem = item.previousItem;
    		newElement.nextItem = item;
    		item.previousItem = newElement;
    		return newElement;
    	} else {
    		return addHead(data);
    	}
    }

    @Override
    public void moveToHead(ListItem item) {
    	modCount++;
    	if(item.parentList == this) {
    		item.previousItem.nextItem = item.nextItem;
    		item.nextItem.previousItem = item.previousItem;
    		item.nextItem = list_head;
    		list_head.previousItem = item;
    		list_head = item;
    	}
    }

    @Override
    public void moveToTail(ListItem item) {
    	modCount++;
    	if(item.parentList == this) {
    		item.previousItem.nextItem = item.nextItem;
    		item.nextItem.previousItem = item.previousItem;
    		item.previousItem = list_tail;
    		list_tail.nextItem = item;
    		list_tail = item;
    	}
    }

    @Override
    public void rotate(ListItem item) {

    }

    @Override
    public void swap(ListItem item1, ListItem item2) {

    }

    @Override
    public void reverse() {

    }

    @Override
    public void addAfter(ListItem item, List<E> list) {

    }

    @Override
    public void addBefore(ListItem item, List<E> list) {

    }

    @Override
    public void conc(List<E> list, boolean after) {

    }

    @Override
    public IList<E> remove(ListItem startInclusive, ListItem endExclusive) {
        return null;
    }

    @Override
    /**
     * Returns the iterator corresponding to the DLinkedList
     */
    public IListIterator<E> listIterator() {
        return new DIterator<E>();
    }

    @Override
    public IListIterator<E> listIterator(int index) {
    	DIterator<E> it = new DIterator<E>();
    	it.m_index = index;
        return it;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
    	if(size == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return this.listIterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
    
    /*
     * DIterator class in DLinkedList class
     */
    public class DIterator<E> implements IListIterator<E> {

    	private ListItem<E> m_returned;
    	private ListItem<E> m_next;
    	private ListItem<E> m_previous;
    	private int m_index;
    	private int m_curModCount;
    	
    	    	
    	
        public DIterator() {
			super();
			this.m_returned = (ListItem<E>) list_head;
			this.m_next = m_returned;
			this.m_previous = null;
			this.m_index = 0;
			this.m_curModCount = modCount;
		}

		@Override
        public ListItem getVisited() {
        	if(m_next != null || m_previous !=null) {
        		return m_returned;
        	} else {
        		throw new IllegalStateException(); 
            }
        }

        @Override
        public boolean hasNext() {
        	if(m_next != null) {
        		return true;
        	} else {
        		return false;
        	}
        }

        @Override
        public E next() {
        	m_index++;
        	m_previous = m_returned;
        	m_returned = m_next;
        	m_next = m_returned.nextItem;
            return m_returned.m_data;
        }

        @Override
        public boolean hasPrevious() {
        	if(m_previous != null) {
        		return true;
        	} else {
        		return false;
        	}
        }

        @Override
        public E previous() {           
            m_index--;
        	m_next = m_returned;
        	m_returned = m_previous;
        	m_previous = m_returned.previousItem;
            return m_returned.m_data;
        }

        @Override
        public int nextIndex() {
        	if(m_index == size) {
        		return size;
        	}
            return m_index +1;
        }

        @Override
        public int previousIndex() {
        	if(m_index == 0) {
        		return -1;
        	}
            return m_index -1;
        }

        @Override
        public void remove() {
        	//checks if Iterator still useful is
            if (m_curModCount != modCount) throw new ConcurrentModificationException();
            if (m_returned == null) {
                throw new IllegalStateException();
            } else {
                if (m_returned == m_next) {
                    m_next = m_returned.nextItem;
                } else {
                    m_index--;
                }
                DLinkedList.this.remove(m_returned);
                m_returned = null;
                m_curModCount++;
            }
        }

        @Override
        public void set(E data) {
            if (m_returned == null) {
                throw new IllegalStateException();
            } else {
                m_returned.m_data = data;
            }
        }

        @Override
        public void add(E o) {
        	if (m_curModCount != modCount) throw new ConcurrentModificationException();
              
                m_index++;
                m_next = DLinkedList.this.addAfter(m_previous, o);
                m_curModCount++; 
        }
    }

    
}