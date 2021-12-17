package JList;
import java.util.Collection;
import java.util.logging.*;
public class JLinkedList<E> implements JList<E>{
    private int size;
    private E value;
    private Node<E> next;
    private Node<E> last;
    private Node<E> first;

    private static Logger logger = Logger.getLogger(JLinkedList.class.getName());

    @Override
    public boolean add(E e) {
        try{
            final Node<E> addition=new Node(e);

            if(first.equals(null)){
                first.next = addition;
                first = addition;
                this.size=this.size+1;
            }

            last.next = addition;
            last = addition;

            size++;

            return true;

        }catch(Exception ex){
            logger.setLevel(Level.SEVERE);
            logger.severe("Could not add new Node");
            return false;
        }
    }

    @Override
    public Object[] toArray() {
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
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public void removeDuplicates(){
        Node<E> currentNode=first;
        for(int i=0;i<size;i++){
            if(this.contains(currentNode.data)){

            }
        }
    }
}
