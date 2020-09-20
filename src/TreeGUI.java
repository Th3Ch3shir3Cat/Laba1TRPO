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
    private JButton addRandomButton;
    private JButton addStringButton;
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
        }
        binaryTree.traverseInOrder(binaryTree.root, 0);
        binaryTree.setArrayTops(binaryTree.root);
        setMasForNumber();
        setMasForValue();
        comboBoxForNumber = new JComboBox<>(masForNumber);
        comboBoxForValue = new JComboBox<>(masForValue);

        comboBoxForNumber.setEditable(true);
        comboBoxForValue.setEditable(true);
        comboBoxForNumber.setMaximumRowCount(5);
        this.node = binaryTree.root;

        drawer = new DrawTree(this.node);
        contentPane.add(drawer);

        buttonsPane = new JPanel();
        buttonsPane.setPreferredSize(new Dimension(200, getHeight()));
        /*buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.Y_AXIS));
        buttonsPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
*/
        buttonsPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        stringForAdd = new JTextField();

        buttonBalance = new JButton();
        buttonBalance.setText("Балансировать");
        buttonBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                binaryTree.root = binaryTree.getBalance(0,binaryTree.sizeBinaryTree-1);
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        addStringButton = new JButton();
        addStringButton.setText("Добавить");
        addStringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringFromLabel = stringForAdd.getText();
                binaryTree.add(stringFromLabel);
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        addRandomButton = new JButton();
        addRandomButton.setText("Добавить случайно сгенерированную строку");
        addRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomString randomString = new RandomString();
                binaryTree.add(randomString.getResultString());
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        deleteButtonForNumber = new JButton();
        deleteButtonForNumber.setText("Удалить по логическому номеру");
        deleteButtonForNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                binaryTree.delete(binaryTree.getValueFromArray(comboBoxForNumber.getSelectedIndex()));
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        deleteButtonForValue = new JButton();
        deleteButtonForValue.setText("Удалить по ключу");
        deleteButtonForValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                binaryTree.delete(comboBoxForValue.getSelectedItem());
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 0;
        buttonsPane.add(stringForAdd,c);
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 1;
        buttonsPane.add(addStringButton,c);
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 2;
        buttonsPane.add(addRandomButton,c);
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 3;
        buttonsPane.add(comboBoxForNumber,c);
        c.gridx = 1;
        c.gridwidth = 2;
        c.gridy = 3;
        buttonsPane.add(deleteButtonForNumber,c);
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 4;
        buttonsPane.add(comboBoxForValue,c);
        c.gridx = 2;
        c.gridwidth = 2;
        c.gridy = 4;
        buttonsPane.add(deleteButtonForValue,c);
        c.gridx = 0;
        c.gridwidth = 3;
        c.gridy = 7;
        buttonsPane.add(buttonBalance,c);

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

    public void setMasForValue(){
        masForValue = new Vector<>();
        for(int i = 0; i < binaryTree.sizeBinaryTree; i++){
            masForValue.add(binaryTree.getValueFromArray(i).toString());
        }
    }

    public void updateContentPane(){
        this.contentPane.removeAll();
        updateDataForComboBox();
        drawer = new DrawTree(binaryTree.root);
        contentPane.add(drawer);
        revalidate();
        repaint();
    }

    public void updateDataForComboBox(){
        setMasForValue();
        setMasForNumber();
        comboBoxForValue.removeAllItems();
        comboBoxForNumber.removeAllItems();
        for(int i = 0; i < masForValue.size(); i++){
            comboBoxForValue.insertItemAt(masForValue.get(i),i);
            comboBoxForNumber.insertItemAt(i+1,i);
        }
        comboBoxForNumber.setSelectedIndex(0);
        comboBoxForValue.setSelectedIndex(0);
    }

}
