public class ListItem {

    ListItem nextItem, previousItem;
    Object m_data;

    DLinkedList parentList;

    public ListItem(Object data){
        this.m_data = data;
    }
}