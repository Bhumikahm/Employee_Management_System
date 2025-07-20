
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateEmployee extends JFrame {

    public UpdateEmployee() {
        setTitle("Update Employee");
        setSize(650, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Gradient background panel
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(106, 90, 205);   // Slate Blue  
                Color color2 = new Color(135, 206, 250);  // Light Sky Blue

                g2.setPaint(new GradientPaint(0, 0, color1, w, h, color2));
                g2.fillRect(0, 0, w, h);
            }
        };
        panel.setLayout(null);

        JLabel title = new JLabel("Update Employee Details");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(180, 20, 400, 30);
        panel.add(title);

        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        idLabel.setForeground(Color.WHITE);
        idLabel.setBounds(100, 80, 120, 25);
        panel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idField.setBounds(230, 80, 250, 28);
        panel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(100, 130, 120, 25);
        panel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameField.setBounds(230, 130, 250, 28);
        panel.add(nameField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        salaryLabel.setForeground(Color.WHITE);
        salaryLabel.setBounds(100, 180, 120, 25);
        panel.add(salaryLabel);

        JTextField salaryField = new JTextField();
        salaryField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        salaryField.setBounds(230, 180, 250, 28);
        panel.add(salaryField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        deptLabel.setForeground(Color.WHITE);
        deptLabel.setBounds(100, 230, 120, 25);
        panel.add(deptLabel);

        JTextField deptField = new JTextField();
        deptField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        deptField.setBounds(230, 230, 250, 28);
        panel.add(deptField);

        JLabel posLabel = new JLabel("Position:");
        posLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        posLabel.setForeground(Color.WHITE);
        posLabel.setBounds(100, 280, 120, 25);
        panel.add(posLabel);

        JTextField posField = new JTextField();
        posField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        posField.setBounds(230, 280, 250, 28);
        panel.add(posField);

        JButton updateBtn = new JButton("Update");
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateBtn.setBackground(new Color(0, 200, 83)); // Green
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setBounds(200, 340, 100, 35);
        panel.add(updateBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backBtn.setBackground(new Color(229, 57, 53)); // Red
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(320, 340, 100, 35);
        panel.add(backBtn);

        updateBtn.addActionListener((ActionEvent e) -> {
            String idText = idField.getText().trim();
            String name = nameField.getText().trim();
            String salaryText = salaryField.getText().trim();
            String department = deptField.getText().trim();
            String position = posField.getText().trim();

            if (idText.isEmpty() || name.isEmpty() || salaryText.isEmpty() || department.isEmpty() || position.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                String query = "UPDATE employee SET name=?, salary=?, department=?, position=? WHERE id=?";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setInt(2, Integer.parseInt(salaryText));
                pstmt.setString(3, department);
                pstmt.setString(4, position);
                pstmt.setInt(5, Integer.parseInt(idText));

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                    idField.setText("");
                    nameField.setText("");
                    salaryField.setText("");
                    deptField.setText("");
                    posField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "No employee found with this ID.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while updating.");
            }
        });

        backBtn.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }
}
