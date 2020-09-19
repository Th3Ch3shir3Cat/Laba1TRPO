public class Node<T extends Comparable<T>> implements Comparable<Node<T>>{

    T value;
    Node left;
    Node right;
    int numberOfVertices;

    Node(T value){
        this.value = value;
        this.left = null;
        this.right = null;
        this.numberOfVertices = 0;
    }

    Node(T value, Node left, Node right){
        this.value = value;
        this.left = left;
        this.right = right;
        this.numberOfVertices = 0;
    }
    public T getValue() {
        return this.value;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    @Override
    public int compareTo(Node<T> o) {
        return value.compareTo(o.value);
    }
}
