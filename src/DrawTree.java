import javax.swing.*;
import java.awt.*;

public class DrawTree extends JPanel {
    public Node node;


    public DrawTree(Node node){
        this.node = node;
    }

    public Point DrawTree(Graphics g, int StartWidth, int EndWidth, int StartHeight, int Level, Node node)
    {
        String data = String.valueOf(node.getValue());
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        int dataWidth = fm.stringWidth(data);

        Point textPos = new Point((StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);
        g.drawString(data, textPos.x, textPos.y);

        if (node.getLeft() != null) {
            Point child1 = DrawTree(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.getLeft());
            drawLine(g, textPos, child1);
        }
        if (node.getRight() != null) {
            Point child2 = DrawTree(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.getRight());
            drawLine(g, textPos, child2);
        }

        return textPos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        DrawTree(g, 0, getWidth(), 0, getHeight() / 5, node);
    }

    public void drawLine(Graphics g, Point p1, Point p2)
    {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}
