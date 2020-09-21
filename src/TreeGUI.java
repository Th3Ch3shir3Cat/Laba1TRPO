import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Класс отрисовки окна
 * nameFunctional - лэйбл для наименование функционала
 * contentPane - основная панель
 * buttonsPane - панель для кнопок
 * stringForAdd - текстовое поле для ввода строки для добавления
 * buttonBalance - кнопка балансировки дерева
 * addRandomButton - кнопка для добавления рандомной строки
 * addStringButton - кнопка для добавлеия строки введенной пользователем
 * deleteButtonForNumber - кнопка для удаления узла по логическому номеру
 * deleteButtonForValue - кнопка для удаления узла по ключу
 */
public class TreeGUI extends JFrame {

    private BinaryTree binaryTree = new BinaryTree();

    private JLabel nameFunctional;
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


        nameFunctional = new JLabel("Функционал программы");
        nameFunctional.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        nameFunctional.setSize(new Dimension(180,30));
        nameFunctional.setLocation(20,10);

        comboBoxForNumber = new JComboBox<>(masForNumber);
        comboBoxForValue = new JComboBox<>(masForValue);
        comboBoxForNumber.setSize(new Dimension(40,30));
        comboBoxForValue.setSize(new Dimension(80,30));
        comboBoxForNumber.setLocation(10,220);
        comboBoxForValue.setLocation(10,290);
        comboBoxForNumber.setEditable(true);
        comboBoxForValue.setEditable(true);

        this.node = binaryTree.root;

        drawer = new DrawTree(this.node);
        contentPane.add(drawer);

        buttonsPane = new JPanel();
        buttonsPane.setPreferredSize(new Dimension(200, getHeight()));

        buttonsPane.setLayout(null);

        stringForAdd = new JTextField();
        stringForAdd.setSize(new Dimension(180,20));
        stringForAdd.setLocation(20,50);

        buttonBalance = createButton("Балансировать", 130,30,20,400);
        buttonBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                binaryTree.root = binaryTree.getBalance(0,binaryTree.sizeBinaryTree-1);
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        addStringButton = createButton("Добавить", 130,30,60,80);
        addStringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringFromLabel = stringForAdd.getText();
                binaryTree.add(stringFromLabel);
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        addRandomButton = createButton("<html>Добавить<br>случайно<br>сгенерированную<br>кнопку</html>",130,80,60,120);
        addRandomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RandomString randomString = new RandomString();
                binaryTree.add(randomString.getResultString());
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        deleteButtonForNumber = createButton("<html>Удалить по<br>логическому номеру</html>",130,60,60,210);
        deleteButtonForNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                binaryTree.delete(binaryTree.getValueFromArray(comboBoxForNumber.getSelectedIndex()));
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        deleteButtonForValue = createButton("<html>Удалить по<br>ключу</html>", 90,60,100,280);
        deleteButtonForValue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                binaryTree.delete(comboBoxForValue.getSelectedItem());
                binaryTree.updateListTops();
                updateContentPane();
            }
        });

        buttonsPane.add(nameFunctional);
        buttonsPane.add(stringForAdd);
        buttonsPane.add(addStringButton);
        buttonsPane.add(addRandomButton);
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


    private JButton createButton(String text, int width, int height, int locX, int locY){
        JButton button = new JButton(text);
        button.setSize(width, height);
        button.setLocation(locX, locY);
        return button;
    }

}
