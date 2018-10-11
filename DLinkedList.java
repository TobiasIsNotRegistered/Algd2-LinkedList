import java.util.*;

public class DLinkedList<E> extends AbstractList<E> implements List<E>, IList<E> {

    ListItem list_head, list_tail;
    int size;


    public DLinkedList () {

    }

    @Override
    public void add(int index, E data) {
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
        return false;
    }

    @Override
    public ListItem head() {
        return null;
    }

    @Override
    public ListItem tail() {
        return null;
    }

    @Override
    public ListItem next(ListItem item) {
        return null;
    }

    @Override
    public ListItem previous(ListItem item) {
        return null;
    }

    @Override
    public ListItem cyclicNext(ListItem item) {
        return null;
    }

    @Override
    public ListItem cyclicPrevious(ListItem item) {
        return null;
    }

    @Override
    public ListItem delete(ListItem item, boolean next) {
        return null;
    }

    @Override
    public ListItem cyclicDelete(ListItem item, boolean next) {
        return null;
    }

    @Override
    public E get(ListItem item) {
        return null;
    }

    @Override
    public void set(ListItem item, E data) {

    }

    @Override
    public E remove(ListItem item) {
        return null;
    }

    @Override
    public ListItem addHead(E data) {
        return null;
    }

    @Override
    public ListItem addTail(E data) {
        return null;
    }

    @Override
    public ListItem addAfter(ListItem item, E data) {
        return null;
    }

    @Override
    public ListItem addBefore(ListItem item, E data) {
        return null;
    }

    @Override
    public void moveToHead(ListItem item) {

    }

    @Override
    public void moveToTail(ListItem item) {

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
    public IListIterator<E> listIterator() {
        return null;
    }

    @Override
    public IListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
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
}



