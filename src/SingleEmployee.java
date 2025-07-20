
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.*;

public class SingleEmployee extends JFrame {

    private JTextField idField;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;

    public SingleEmployee() {
        setTitle("View Single Employee");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GradientPanel panel = new GradientPanel();
        panel.setLayout(null);
        setContentPane(panel);

        JLabel heading = new JLabel("Search Employee by ID");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setBounds(170, 20, 300, 30);
        heading.setForeground(Color.WHITE);
        panel.add(heading);

        JLabel idLabel = new JLabel("Enter ID:");
        idLabel.setBounds(50, 80, 100, 30);
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        idLabel.setForeground(Color.WHITE);
        panel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(140, 80, 250, 30);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(idField);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(410, 80, 100, 30);
        searchBtn.setBackground(new Color(34,139,34)); 
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusPainted(false);
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(searchBtn);

        // Table and scroll pane
        String[] columnNames = {"ID", "Name", "Salary", "Department", "Position"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 15));
        table.setRowHeight(30);

        // Row styling
        table.setBackground(new Color(250, 255, 240)); // Light greenish
        table.setForeground(new Color(44, 62, 80));
        table.setGridColor(new Color(180, 180, 180));
        table.setSelectionBackground(new Color(255, 204, 153)); // Peach highlight
        table.setSelectionForeground(Color.BLACK);

        // Header styling
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(141,182,0)); 
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Tahoma", Font.BOLD, 16));

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 140, 480, 180);
        scrollPane.setVisible(false); // Initially hidden
        scrollPane.getViewport().setBackground(new Color(230, 255, 250)); // Light Mint

        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 102, 102), 2));
        panel.add(scrollPane);

        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setBounds(240, 340, 100, 30);
        backBtn.setBackground(new Color(255,72,72));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.addActionListener(e -> dispose());
        panel.add(backBtn);

        // Search Action
        searchBtn.addActionListener(e -> fetchEmployee());

        setVisible(true);
    }

    private void fetchEmployee() {
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an employee ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Integer.parseInt(idText));
            ResultSet rs = stmt.executeQuery();

            model.setRowCount(0);         // Clear previous
            scrollPane.setVisible(false); // Hide before showing

            if (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("department"),
                        rs.getString("position")
                };
                model.addRow(row);
                scrollPane.setVisible(true); // Show table
            } else {
                JOptionPane.showMessageDialog(this, "No employee found with ID: " + idText, "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Gradient Background Panel
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(200, 160, 255);   
            Color color2 = new Color(85, 0, 119);    // Plum Violet


            GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    public static void main(String[] args) {
        new SingleEmployee();
    }
}
