
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class OnboardEmployee extends JFrame {

    public OnboardEmployee() {
        setTitle("Onboard New Employee");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        JLabel heading = new JLabel("Add New Employee");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setBounds(180, 20, 300, 30);
        heading.setForeground(Color.WHITE);
        panel.add(heading);

        JLabel nameLabel = createLabel("Name:", 50, 80);
        JTextField nameField = createTextField(150, 80);
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel salaryLabel = createLabel("Salary:", 50, 130);
        JTextField salaryField = createTextField(150, 130);
        panel.add(salaryLabel);
        panel.add(salaryField);

        JLabel deptLabel = createLabel("Department:", 50, 180);
        JTextField deptField = createTextField(150, 180);
        panel.add(deptLabel);
        panel.add(deptField);

        JLabel positionLabel = createLabel("Position:", 50, 230);
        JTextField positionField = createTextField(150, 230);
        panel.add(positionLabel);
        panel.add(positionField);

        JButton addBtn = new JButton("Add Employee");
        addBtn.setBounds(330, 290, 160, 35);
        addBtn.setBackground(new Color(46, 204, 113));  // Emerald
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addBtn.setFocusPainted(false);
        panel.add(addBtn);

        // 🔙 Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(100, 290, 100, 35);
        backBtn.setBackground(new Color(220, 20, 60)); // Crimson Red
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.addActionListener(e -> dispose());
        panel.add(backBtn);

        // ➕ Insert Logic
        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String salary = salaryField.getText().trim();
            String dept = deptField.getText().trim();
            String position = positionField.getText().trim();

            if (name.isEmpty() || salary.isEmpty() || dept.isEmpty() || position.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO employee (name, salary, department, position) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setDouble(2, Double.parseDouble(salary));
                stmt.setString(3, dept);
                stmt.setString(4, position);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Employee added successfully!");
                    nameField.setText("");
                    salaryField.setText("");
                    deptField.setText("");
                    positionField.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }

    // 🧱 Helper: Label
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setBounds(x, y, 100, 30);
        label.setForeground(Color.WHITE);
        return label;
    }

    // 🧱 Helper: TextField
    private JTextField createTextField(int x, int y) {
        JTextField field = new JTextField();
        field.setBounds(x, y, 350, 30);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return field;
    }

    // 🎨 Gradient Background
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(123, 104, 238);   // Medium Slate Blue
            Color color2 = new Color(221, 160, 221);   // Plum
            GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    // 🧪 For testing
    public static void main(String[] args) {
        new OnboardEmployee();
    }
}
