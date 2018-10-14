public class ListItem<E> {

    ListItem nextItem, previousItem;
    E m_data;

    DLinkedList parentList;

    public ListItem(E data){
        this.m_data = data;
    }
}