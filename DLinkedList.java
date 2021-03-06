import java.util.*;

public class DLinkedList<E> extends AbstractList<E> implements List<E>, IList<E> {

	private ListItem<E> list_head, list_tail;
	int size;


	public DLinkedList() {
		this.list_head = null;
		this.list_tail = null;
		size = 0;
	}

	@Override
	public boolean add(E data){
		ListItem newItem = new ListItem(data);
		newItem.parentList = this;
		if(list_head == null){
			list_head = newItem;
			list_tail = list_head;
		}else{
			list_tail.setNextItem(newItem);
			newItem.setPreviousItem(list_tail);
			list_tail = newItem;
		}
		modCount++;
		size ++;
		return true;
	}

	@Override
	public void add(int index, E data) {

		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}

		modCount++;
		ListItem newListItem = new ListItem(data);
		newListItem.parentList = this;
		if(index == 0){
			addHead(data);
		}else if (this.size == 0) {
			list_head = list_tail = newListItem;
		} else if (index == size) {
			addTail(data);
		}else {
			ListItem currentItem = list_head;
			for(int i = 0; i<index; i++){
				currentItem = currentItem.getNextItem();
			}

			ListItem previousItem = currentItem.getPreviousItem();

			previousItem.setNextItem(newListItem);
			newListItem.setPreviousItem(previousItem);
			newListItem.setNextItem(currentItem);
			currentItem.setPreviousItem(newListItem);
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
		return item.getNextItem();
	}

	@Override
	public ListItem previous(ListItem item) {
		return item.getPreviousItem();
	}

	@Override
	public ListItem cyclicNext(ListItem item) {
		ListItem result = null;
		//check if item exists in list (stated as precondition in iList)
		if (get(item) != null) {
			result = item.getNextItem();
			//check if nextItem exists (won't if item = tail). If not, set nextItem to head (cyclic behaviour).
			if (result == null) {
				result = this.list_head;
			}
		} else {
			throw new NoSuchElementException();
		}
		return result;
	}

	@Override
	public ListItem cyclicPrevious(ListItem item) {
		ListItem result = null;
		//check if item exists in list (stated as precondition in iList)
		if (get(item) != null) {
			result = item.getPreviousItem();
			//check if previousItem exists (won't if item = head). If not, set nextItem to tail (cyclic behaviour).
			if (result == null) {
				result = this.list_tail;
			}
		} else {
			throw new NoSuchElementException();
		}
		return result;
	}

	/**
	 * Deletes item while iterating.
	 * If next = true: returns successor of item or null if item is the last.
	 * If next = false: returns predecessor of item or null if item is the first.
	 * Precondition: item is in this list
	 */
	@Override
	public ListItem delete(ListItem item, boolean next) {
		//save pointers to be able to use them after the deletion of the item

		
		if (get(item) == null) {
			throw new NoSuchElementException();
		} else {
			ListItem temp_nextItem = item.getNextItem();
			ListItem temp_previousItem = item.getPreviousItem();
			if(size == 1) {
				list_head = list_tail = null;
			} else {

				//swap pointers of previous & next Item
				if (temp_previousItem != null) {
					item.getPreviousItem().setNextItem(item.getNextItem());
				} else {
					list_head = item.getNextItem();
				}
				if (temp_nextItem != null) {
					item.getNextItem().setPreviousItem(item.getPreviousItem());
				} else {
					list_tail = item.getPreviousItem();
				}
			}
			modCount++;
			size--;
			if (next) {
				return temp_nextItem;
			} else {
				return temp_previousItem;
			}
		}
		

	}

	/**
	 * Deletes item while iterating.
	 * If next = true: returns cyclic successor of item or null if item is the only list item.
	 * If next = false: returns cyclic predecessor of item or null if item is the only list item.
	 * Precondition: item is in this list
	 *
	 * @param item to be deleted
	 * @param next controls direction of cyclic iteration
	 * @return successor of item while cyclic iterating or null if list becomes empty
	 */
	@Override
	public ListItem cyclicDelete(ListItem item, boolean next) {
		modCount++;
		//save pointers to be able to use them after the deletion of the item
		ListItem temp_nextItem = cyclicNext(item);
		ListItem temp_previousItem = cyclicPrevious(item);
		//check precondition
		if (get(item) == null) {
			throw new NoSuchElementException();
		} else {
			//swap pointers of previous & next Item
			if (this.size() != 0) {
				if (item.getPreviousItem() != null) {
					item.getPreviousItem().setNextItem(item.getNextItem());

				} else {
					//item.getNextItem().setPreviousItem(null);
					list_head = item.getNextItem();
				}
				if (item.getNextItem() != null) {
					item.getNextItem().setPreviousItem(item.getPreviousItem());
				} else {
					//item.getPreviousItem().setNextItem(null);
					list_tail = item.getPreviousItem();
				}
				this.size--;
			}
		}

		if (next) {
			return temp_nextItem;
		} else {
			return temp_previousItem;
		}
	}

	/**
	 * Precondition: item is in this list
	 *
	 * @param item
	 * @return the contents of item
	 */
	@Override
	public E get(ListItem item) {
		//TODO: UNCHECKED CAST MAY RESULT IN PROBLEMS
		return (E) item.m_data;
	}

	@Override
	public void set(ListItem item, E data) {
		item.m_data = data;
	}

	/**
	 * Deletes the item and returns its contents.
	 * Precondition: item is in this list
	 * @param item
	 * @return contents of the removed item
	 */
	@Override
	public E remove(ListItem item) {
		//precondition
		if(item.parentList != this){throw new NoSuchElementException();}

		ListItem<E> help = item;
		E temp = help.m_data;
		delete(item, true);
		return temp;

	}

	@Override
	public ListItem addHead(E data) {

		if(size==0){
			ListItem help = new ListItem<>(data);
			help.parentList = this;
			list_head = list_tail = help;
			return list_head;
		}else {

			modCount++;
			size++;
			ListItem<E> newElement = new ListItem<E>(data);
			newElement.parentList = this;
			newElement.setNextItem(list_head);
			list_head.setPreviousItem(newElement);
			list_head = newElement;
			return newElement;
		}
	}

	@Override
	public ListItem addTail(E data) {
		size++;
		modCount++;
		ListItem<E> newElement = new ListItem<E>(data);
		newElement.parentList = this;
		newElement.setPreviousItem(list_tail);
		// newElement.setNextItem(null);
		list_tail.setNextItem(newElement);
		list_tail = newElement;
		return newElement;
	}

	@Override
	public ListItem addAfter(ListItem item, E data) {
		if (item != null) {
			if (get(item) != null) {

				ListItem<E> newElement = new ListItem<E>(data);
				newElement.parentList = this;
				if (item == list_tail) {
					return addTail(data);
				} else {
					newElement.setNextItem(item.getNextItem());
					item.setNextItem(newElement);
					newElement.setPreviousItem(item);
					modCount++;
					size++;
					return newElement;
				}

			} else {
				throw new NoSuchElementException();
			}
		} else {
			return addTail(data);
			//return null;
		}

	}

	/**
	 * Inserts a new list item with given data before item and returns the new item.
	 * Precondition: item is in this list or null
	 *
	 * @param item can be null
	 * @param data
	 * @return inserted item
	 */

	@Override
	public ListItem addBefore(ListItem item, E data) {

		if (item != null) {
			if (get(item) != null) {
				if (item == list_head) {
					return addHead(data);
				} else {
					ListItem newElement = new ListItem(data);
					newElement.parentList = this;
					newElement.setPreviousItem(item.getPreviousItem());
					newElement.setNextItem(item);
					item.setPreviousItem(newElement);
					modCount++;
					size++;
					return newElement;
				}
			} else {
				throw new NoSuchElementException();
			}
		} else {
			return addHead(data);
		}
	}

	@Override
	public void moveToHead(ListItem item) {

		if (item.parentList == this) {

			if(item != list_head) {

				modCount++;
				if(item != list_tail) {
					item.getPreviousItem().setNextItem(item.getNextItem());
					item.getNextItem().setPreviousItem(item.getPreviousItem());
					item.setNextItem(list_head);
					list_head.setPreviousItem(item);
					list_head = item;
				} else {
					item.getPreviousItem().setNextItem(item.getNextItem());
					//item.getNextItem().setPreviousItem(item.getPreviousItem());
					item.setNextItem(list_head);
					list_tail = item.getPreviousItem();
					list_head.setPreviousItem(item);
					list_head = item;	
				}
			}
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public void moveToTail(ListItem item) {
		if (item.parentList == this) {
			if(item != list_tail) {
				modCount++;
				if(item != list_head) {

					item.getPreviousItem().setNextItem(item.getNextItem());
					item.getNextItem().setPreviousItem(item.getPreviousItem());
					item.setPreviousItem(list_tail);
					list_tail.setNextItem(item);
					list_tail = item;
				} else {
					//item.getPreviousItem().setNextItem(item.getNextItem());
					item.getNextItem().setPreviousItem(item.getPreviousItem());
					item.setPreviousItem(list_tail);
					list_head = item.getNextItem();
					list_tail.setNextItem(item);
					list_tail = item;
				}

			}
		}
		else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Rotates the list until item is the new head in O(1) time.
	 * Precondition: item is in this list
	 *
	 * @param item
	 */
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
		return new DIterator();
	}

	@Override
	public IListIterator<E> listIterator(int index) {
		DIterator it = new DIterator();
		it.m_index = index;
		return it;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
    @Override
    public boolean contains(Object o) {
        boolean success = false;
        ListItem current = list_head;

        for(int i = 0; i<size; i++){
            current = current.getNextItem();
            if(current != null && current.m_data.equals((E)o)){
                success = true;
                return success;
            }
        }

        return success;
    }
	 */

	@Override
	public Iterator<E> iterator() {
		return this.listIterator();
	}

	/*
    @Override
    public Object[] toArray() {
        //instantiate new array
        Object[] temp = new Object[size()];
        DIterator dieter = new DIterator();
        //add next items recursively
        for (int i = 0; i < size; i++) {
            if (dieter.hasNext()) {
                temp[i] = dieter.next();
            }
        }

        return temp;
    }
	 */

	/*
    @Override
    public boolean add(E e) {
        ListItem newItem = new ListItem(e);

        //if empty list
        if (this.list_head == null && this.list_tail == null) {
            modCount++;
            this.list_head = newItem;
            this.list_tail = this.list_head;
            this.size++;
            return true;
        } else {
            modCount++;
            this.list_tail.setNextItem(newItem);
            newItem.setPreviousItem(this.list_tail);
            newItem.setNextItem(null);
            this.list_tail = newItem;
            size++;
            return true;
        }
            }
	 */


	@Override
	public E get(int index) {

		//precondition
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		//cycle to index recursively
		ListItem current = list_head;
		for (int i = 0; i == index; i++) {
			current = current.getNextItem();
		}

		return (E) current.m_data;
	}


	/*
	 * DIterator class in DLinkedList class
	 */
	public class DIterator implements IListIterator<E> {

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
			if (m_next != null || m_previous != null) {
				return m_returned;
			} else {
				throw new IllegalStateException();
			}
		}

		@Override
		public boolean hasNext() {
			if (m_next != null) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Returns the next element in the list and advances the cursor position.
		 * This method may be called repeatedly to iterate through the list,
		 * or intermixed with calls to {@link #previous} to go back and forth.
		 * (Note that alternating calls to {@code next} and {@code previous}
		 * will return the same element repeatedly.)
		 *
		 * @return the next element in the list
		 * @throws NoSuchElementException if the iteration has no next element
		 */
		@Override
		public E next() {
			if (hasNext()) {
				m_index++;
				m_previous = m_returned;
				m_returned = m_next;
				m_next = m_returned.getNextItem();
				return m_returned.m_data;
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public boolean hasPrevious() {
			if (m_previous != null) {
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
			m_previous = m_returned.getPreviousItem();
			return m_returned.m_data;
		}

		@Override
		public int nextIndex() {
			if (m_index == size) {
				return size;
			}
			return m_index + 1;
		}

		@Override
		public int previousIndex() {
			if (m_index == 0) {
				return -1;
			}
			return m_index - 1;
		}

		@Override
		public void remove() {
			//checks if Iterator still useful is
			if (m_curModCount != modCount) throw new ConcurrentModificationException();
			if (m_returned == null) {
				throw new IllegalStateException();
			} else {
				if (m_returned == m_next) {
					m_next = m_returned.getNextItem();
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