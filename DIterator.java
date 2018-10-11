import java.util.ConcurrentModificationException;

public class DIterator<E> implements IListIterator<E> {


    @Override
    public ListItem getVisited() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public E previous() {
        return null;
    }

    @Override
    public int nextIndex() {
        return 0;
    }

    @Override
    public int previousIndex() {
        return 0;
    }

    @Override
    public void remove() {
        if (m_curModCount != modCount) throw new ConcurrentModificationException();
        if (m_returned == null) {
            throw new IllegalStateException();
        } else {
            if (m_returned == m_next) {
                m_next = m_returned.m_next;
            } else {
                m_index--;
            }
            DLinkedList.this.remove(m_returned);
            m_returned = null;
            m_curModCount++;
        }
    }

    @Override
    public void set(E o) {
        if (m_returned == null) {
            throw new IllegalStateException();
        } else {
            m_returned.m_data = data;
        }
    }

    @Override
    public void add(E o) {

    }
}
