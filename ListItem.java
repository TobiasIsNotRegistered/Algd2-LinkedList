//import sun.plugin.dom.exception.NoModificationAllowedException;

public class ListItem<E> {

    private ListItem nextItem, previousItem;

    E m_data;

    DLinkedList parentList;

    public ListItem(E data){
        this.m_data = data;
    }

    public ListItem getNextItem() {
        return nextItem;
    }

    public ListItem getPreviousItem() {
        return previousItem;
    }

    protected void setNextItem(ListItem nextItem) {
        this.nextItem = nextItem;
    }

    protected void setPreviousItem(ListItem previousItem) {
        this.previousItem = previousItem;
    }
}