import java.util.*;

public class DLinkedList extends AbstractList implements List, IList {

    //delete unnecessary methods
    // (addAfter(…), addBefore(…)), bestehende löschen (remove(…),
    //delete(…))

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
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
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
    public Object get(ListItem item) {
        return null;
    }

    @Override
    public void set(ListItem item, Object data) {

    }

    @Override
    public Object remove(ListItem item) {
        return null;
    }

    @Override
    public ListItem addHead(Object data) {
        return null;
    }

    @Override
    public ListItem addTail(Object data) {
        return null;
    }

    @Override
    public ListItem addAfter(ListItem item, Object data) {
        return null;
    }

    @Override
    public ListItem addBefore(ListItem item, Object data) {
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
    public void addAfter(ListItem item, List list) {

    }

    @Override
    public void addBefore(ListItem item, List list) {

    }

    @Override
    public void conc(List list, boolean after) {

    }

    @Override
    public IList remove(ListItem startInclusive, ListItem endExclusive) {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
