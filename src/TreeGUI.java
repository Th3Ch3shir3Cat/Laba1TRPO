import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class TreeGUI extends JFrame {

    private BinaryTree binaryTree = new BinaryTree();


    private JPanel contentPane;
    private JPanel buttonsPane;
    private JTextField stringForAdd;
    private JButton buttonBalance;
    private JButton addButton;
    private JButton deleteButtonForNumber;
    private JButton deleteButtonForValue;

    private Node node;
    private DrawTree drawer;

    private Vector<Integer> masForNumber;
    private JComboBox<Integer> comboBoxForNumber;

    private JComboBox<String> comboBoxForValue;
    private Vector<String> masForValue;

    public TreeGUI() {

        setTitle("Лабораотная работа №1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);

        Container container = getContentPane();

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        masForValue = new Vector<>();

        for(int i = 0; i < 5; i++){
            RandomString randomString = new RandomString();
            String string = randomString.getResultString();
            binaryTree.add(string);
            masForValue.add(string);
        }
        binaryTree.traverseInOrder(binaryTree.root, 0);
        binaryTree.setArrayTops(binaryTree.root);
        setMasForNumber();
        comboBoxForNumber = new JComboBox<>(masForNumber);
        comboBoxForValue = new JComboBox<>(masForValue);

        comboBoxForNumber.setEditable(true);
        comboBoxForValue.setEditable(true);
        comboBoxForNumber.setMaximumRowCount(5);
        this.node = binaryTree.root;

        drawer = new DrawTree(this.node);
        contentPane.add(drawer);

        buttonsPane = new JPanel();
        buttonsPane.setSize(200,500);
        buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.Y_AXIS));
        buttonsPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        stringForAdd = new JTextField(4);

        buttonBalance = new JButton();
        buttonBalance.setText("Балансировать");
        buttonBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.removeAll();
                binaryTree.root = binaryTree.getBalance(0,binaryTree.sizeBinaryTree-1);
                binaryTree.updateListTops(binaryTree.root);
                drawer = new DrawTree(binaryTree.root);
                contentPane.add(drawer);
                revalidate();
                repaint();
            }
        });

        addButton = new JButton();
        addButton.setText("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.removeAll();
                RandomString randomString = new RandomString();
                binaryTree.add(randomString.getResultString());
                binaryTree.updateListTops(binaryTree.root);
                drawer = new DrawTree(binaryTree.root);
                contentPane.add(drawer);
                revalidate();
                repaint();
            }
        });

        deleteButtonForNumber = new JButton();
        deleteButtonForNumber.setText("Удалить по логическому номеру");
        deleteButtonForNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.removeAll();
                binaryTree.root = binaryTree.deleteNode(binaryTree.root,binaryTree.getValueFromArray(comboBoxForNumber.getSelectedIndex()));
                binaryTree.updateListTops(binaryTree.root);
                drawer = new DrawTree(binaryTree.root);
                contentPane.add(drawer);
                revalidate();
                repaint();
            }
        });

        deleteButtonForValue = new JButton();
        deleteButtonForValue.setText("Удалить по ключу");
        deleteButtonForValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.removeAll();
                binaryTree.root = binaryTree.deleteNode(binaryTree.root,comboBoxForValue.getSelectedItem());
                binaryTree.updateListTops(binaryTree.root);
                drawer = new DrawTree(binaryTree.root);
                contentPane.add(drawer);
                revalidate();
                repaint();
            }
        });

        buttonsPane.add(stringForAdd);
        buttonsPane.add(addButton);
        buttonsPane.add(comboBoxForNumber);
        buttonsPane.add(deleteButtonForNumber);
        buttonsPane.add(comboBoxForValue);
        buttonsPane.add(deleteButtonForValue);
        buttonsPane.add(buttonBalance);

        container.add(contentPane);
        container.add(buttonsPane, BorderLayout.EAST);
        setVisible(true);
    }

    public void setMasForNumber(){
        masForNumber = new Vector<>();
        for(int i = 0; i < binaryTree.sizeBinaryTree; i++){
            masForNumber.add(i+1);
        }
    }


}
