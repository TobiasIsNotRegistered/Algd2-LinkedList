public class ListItem {

    ListItem nextItem, previousItem;
    Object data;

    DLinkedList parentList;

    public ListItem(Object data){
        this.data = data;
    }
}