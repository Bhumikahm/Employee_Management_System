
import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    public Home() {
        setTitle("Employee Management System");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new GradientPanel(); // background panel
        panel.setLayout(null);

        JLabel heading = new JLabel("EMS - Dashboard");
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(Color.white);
        heading.setBounds(250, 30, 400, 40);
        panel.add(heading);

        // 🌈 Gradient Buttons
        GradientButton btnAll = new GradientButton("View All Employees", new Color(0, 200, 83), new Color(0, 150, 70));
        btnAll.setBounds(100, 100, 220, 40);
        panel.add(btnAll);

        GradientButton btnOne = new GradientButton("View Employee", new Color(255, 213, 79), new Color(255, 193, 7));
        btnOne.setBounds(450, 100, 220, 40);
        panel.add(btnOne);

        GradientButton btnAdd = new GradientButton("Add Employee", new Color(3, 169, 244), new Color(2, 136, 209));
        btnAdd.setBounds(100, 180, 220, 40);
        panel.add(btnAdd);

        GradientButton btnDelete = new GradientButton("Delete Employee", new Color(229, 57, 53), new Color(198, 40, 40));
        btnDelete.setBounds(450, 180, 220, 40);
        panel.add(btnDelete);

        GradientButton btnUpdate = new GradientButton("Update Employee", new Color(156, 39, 174), new Color(123, 31, 162));
        btnUpdate.setBounds(275, 260, 220, 40);
        panel.add(btnUpdate);

       //  🎯 Add action listeners here (optional)
         btnAll.addActionListener(e -> new DisplayAll());
         btnOne.addActionListener(e -> new SingleEmployee());
         btnAdd.addActionListener(e -> new OnboardEmployee());
         btnDelete.addActionListener(e -> new DeboardEmployee());
         btnUpdate.addActionListener(e -> new UpdateEmployee());

        add(panel);
        setVisible(true);
    }

    // 🌈 Gradient Button Class
    class GradientButton extends JButton {
        private final Color color1;
        private final Color color2;

        public GradientButton(String text, Color color1, Color color2) {
            super(text);
            this.color1 = color1;
            this.color2 = color2;
            setContentAreaFilled(false);
            setForeground(Color.WHITE);
            setFont(new Font("SansSerif", Font.BOLD, 14));
            setFocusPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2));
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // 🎨 Gradient Background Panel
 // 🎨 Darker Gradient Background Panel
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(123, 31, 162); // deep purple
            Color color2 = new Color(244, 143, 177); // pink

            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
