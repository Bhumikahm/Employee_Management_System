
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeboardEmployee extends JFrame {

    public DeboardEmployee() {
        setTitle("Deboard Employee");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Gradient Panel Background
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(102, 0, 153);   // Dark Purple
                Color color2 = new Color(200, 160, 255); // Light Lavender
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panel.setLayout(null);

        JLabel title = new JLabel("Deboard Employee");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(180, 20, 300, 30);
        panel.add(title);

        JLabel idLabel = new JLabel("Enter Employee ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        idLabel.setForeground(Color.WHITE);
        idLabel.setBounds(80, 100, 200, 25);
        panel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idField.setBounds(250, 100, 200, 30);
        panel.add(idField);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteBtn.setBackground(new Color(124, 252, 0)); // RGB: Lime
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBounds(200, 160, 100, 35);
        panel.add(deleteBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backBtn.setBounds(320, 160, 80, 35);
        backBtn.setBackground(new Color(229, 57, 53)); // Fire Red
        backBtn.setForeground(Color.WHITE);

        panel.add(backBtn);

        deleteBtn.addActionListener((ActionEvent e) -> {
            String idText = idField.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an ID.");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String query = "DELETE FROM employee WHERE id=?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, Integer.parseInt(idText));

                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
                    idField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "No employee found with this ID.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while deleting employee.");
            }
        });

        backBtn.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }
}
