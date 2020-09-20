import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T>{

    Node root;
    int sizeBinaryTree;
    ArrayList<Node> arrayTops;


    BinaryTree(){
        sizeBinaryTree = 0;
        arrayTops = new ArrayList<>();
    }

    private Node addNode(Node current, T value){
        if(current == null){
            return new Node((Comparable) value);
        }
        current.numberOfVertices++;
        if(current.compareTo(new Node((Comparable) value)) > 0){
            current.left = addNode(current.left, value);
        }else if (current.compareTo(new Node((Comparable) value)) < 0){
            current.right = addNode(current.right, value);
        }else{
            return current;
        }

        return current;
    }

    public void add(T value){
        root = addNode(root, value);
        sizeBinaryTree++;
    }

    public Node deleteNode(Node current, T value){
        if(current == null) return null;
        if(current.compareTo(new Node((Comparable) value)) == 0){
            if(current.left == null && current.right == null){
                return null;
            }
            if(current.right == null){
                return current.left;
            }
            if(current.left == null){
                return current.right;
            }
            T smallestValue = findSmallestValue(current.right);
            current.value = (Comparable) smallestValue;
            current.right = deleteNode(current.right, smallestValue);
            return current;
        }
        if(current.compareTo(new Node((Comparable) value)) > 0){
            current.left = deleteNode(current.left, value);
        }
        current.right = deleteNode(current.right, value);
        return current;
    }

    private T findSmallestValue(Node root){
        return root.left == null ? (T) root.value : findSmallestValue(root.left);
    }

    public void setArrayTops(Node node){
        if(node == null) return;
        setArrayTops(node.left);
        arrayTops.add(node);
        setArrayTops(node.right);
    }

    public void traverseInOrder(Node node, int level) {
        if(node == null) return;
        traverseInOrder(node.left, level+1);
        System.out.println("Уровень = " + level + " Количество вершин в дереве " + node.numberOfVertices + " Значение = " + node.value);
        traverseInOrder(node.right,level+1);
    }


    public Node getBalance(int start, int finish){
        if(start > finish) return null;
        Node newNode;
        if(start == finish){
            newNode = arrayTops.get(start);
            newNode.left = newNode.right = null;
            newNode.numberOfVertices = 1;
            return newNode;
        }
        int middle = (start+finish)/2;
        newNode = arrayTops.get(middle);
        newNode.left = getBalance(start,middle-1);
        newNode.right = getBalance(middle+1, finish);
        newNode.numberOfVertices = finish-start+1;
        return newNode;
    }

    public void updateListTops(Node node){
        this.arrayTops = new ArrayList<>();
        setArrayTops(node);
    }

    public T getValueFromArray(int number){
        return (T) arrayTops.get(number).value;
    }
}
