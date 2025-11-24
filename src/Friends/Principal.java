package Friends;
// ...existing code...
import javax.swing.*;
import java.awt.*;

public class Principal {
    private JFrame frame;
    private JTextField txtName;
    private JTextField txtNumber;
    private FriendsManager manager;

    public Principal() {
        try {
            manager = new FriendsManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error has occured creating/opening the file");
            System.exit(1);
        }
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Friends");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(8,8));

        JPanel form = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(6, 6, 6, 6);
    c.anchor = GridBagConstraints.WEST;

    // Labels y campos con columnas (tamaño "normal")
    JLabel lblName = new JLabel("Nombre:");
    txtName = new JTextField(18);       // 18 columnas ~ tamaño normal
    JLabel lblNumber = new JLabel("Numero:");
    txtNumber = new JTextField(12);     // 12 columnas para números

    c.gridx = 0; c.gridy = 0;
    form.add(lblName, c);
    c.gridx = 1; c.gridy = 0;
    form.add(txtName, c);

    c.gridx = 0; c.gridy = 1;
    form.add(lblNumber, c);
    c.gridx = 1; c.gridy = 1;
    form.add(txtNumber, c);

    JPanel buttons = new JPanel(); // FlowLayout por defecto
    JButton btnCreate = new JButton("Create");
    JButton btnRead = new JButton("Read");
    JButton btnUpdate = new JButton("Update");
    JButton btnDelete = new JButton("Delete");
    JButton btnClean = new JButton("Clean Fields");
    buttons.add(btnCreate);
    buttons.add(btnRead);
    buttons.add(btnUpdate);
    buttons.add(btnDelete);
    buttons.add(btnClean);

    frame.add(form, BorderLayout.CENTER);
    frame.add(buttons, BorderLayout.SOUTH);

    // assign actions (mantén tus clases Action)
    btnCreate.addActionListener(new CreateAction(manager, txtName, txtNumber, frame));
    btnRead.addActionListener(new ReadAction(manager, frame));
    btnUpdate.addActionListener(new UpdateAction(manager, txtName, txtNumber, frame));
    btnDelete.addActionListener(new DeleteAction(manager, txtName, frame));
    btnClean.addActionListener(e -> { txtName.setText(""); txtNumber.setText(""); });

    frame.pack();
    frame.setLocationRelativeTo(null);
    }

    public void show() { frame.setVisible(true); }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Principal().show());
    }
}