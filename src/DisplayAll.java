
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DisplayAll extends JFrame {

    public DisplayAll() {
        setTitle("All Employees");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GradientPanel gradientPanel = new GradientPanel();
        gradientPanel.setLayout(new BorderLayout());
        setContentPane(gradientPanel);

        // Table column headers
        String[] columns = {"ID", "Name", "Salary", "Department", "Position"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        table.getTableHeader().setBackground(new Color(141, 182, 0));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(Color.GRAY);

        // 🧾 Add data from DB
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM employee";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("department"),
                        rs.getString("position")
                };
                model.addRow(row);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading employee data:\n" + e.getMessage());
            e.printStackTrace();
        }

        // 🎨 Custom row colors (zebra style)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                Color light = new Color(220, 248, 255);  // Soft sky blue
                Color dark = new Color(204, 236, 221);   // Minty green


                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? light : dark);
                    c.setForeground(Color.BLACK);
                } else {
                    c.setBackground(new Color(255, 204, 128));  // Selected row color
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        gradientPanel.add(scrollPane, BorderLayout.CENTER);

        // 🔘 Back Button
        JButton backBtn = new JButton(" Back");
        backBtn.setBackground(new Color(255, 72, 72));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setPreferredSize(new Dimension(80, 40));
        backBtn.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backBtn);

        gradientPanel.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // 🌈 Gradient Background Panel
    class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int w = getWidth();
            int h = getHeight();

            Color color1 = new Color(63, 94, 251);   // Purple-Blue
            Color color2 = new Color(252, 70, 107);  // Pink-Red

            GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
    }

    // 🧪 For testing directly
    public static void main(String[] args) {
        new DisplayAll();
    }
}
