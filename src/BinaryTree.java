import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T>{

    Node root;

    ArrayList<Node> arrayTops;

    BinaryTree(){
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
    }



    public void traverseInOrder(Node node, int level) {
        if(node == null) return;
        traverseInOrder(node.left, level+1);
        System.out.println("Уровень = " + level + " Количество вершин в дереве " + node.numberOfVertices + " Значение = " + node.value);
        this.arrayTops.add(new Node(node.value,node.left, node.right));
        traverseInOrder(node.right,level+1);
    }


    public Node getNode_n(Node node, int number){
        if(node == null) return null;
        if(number > node.numberOfVertices) return null;
        if(node.left != null){
            int ll = node.left.numberOfVertices;
            if(number <= ll) return getNode_n(node.left, number);
            number -= ll;
            System.out.println("\n" + number + "\n");
        }
        number--;
        if(number <= 0) return node;
        return getNode_n(node.right, number);
    }

    public Node getBalance(List<Node> arrayTops, int start, int finish){
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
        newNode.left = getBalance(arrayTops,start,middle-1);
        newNode.right = getBalance(arrayTops, middle+1, finish);
        newNode.numberOfVertices = finish-start+1;
        return newNode;
    }


}
