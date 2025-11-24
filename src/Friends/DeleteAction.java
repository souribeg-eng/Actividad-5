package Friends;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class DeleteAction implements ActionListener {
    private final FriendsManager manager;
    private final JTextField txtName;
    private final JFrame owner;

    public DeleteAction(FriendsManager mgr, JTextField name, JFrame owner) {
        this.manager = mgr;
        this.txtName = name;
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = txtName.getText().trim();
        try {
            boolean ok = manager.removeFriend(name);
            JOptionPane.showMessageDialog(owner, ok ? "Deleted." : "Not found.", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(owner, "An error has occured creating/opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}