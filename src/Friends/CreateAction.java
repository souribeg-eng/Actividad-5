package Friends;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class CreateAction implements ActionListener {
    private final FriendsManager manager;
    private final JTextField txtName;
    private final JTextField txtNumber;
    private final JFrame owner;

    public CreateAction(FriendsManager mgr, JTextField name, JTextField number, JFrame owner) {
        this.manager = mgr;
        this.txtName = name;
        this.txtNumber = number;
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = txtName.getText().trim();
        String numText = txtNumber.getText().trim();
        try {
            long num = Long.parseLong(numText);
            boolean ok = manager.addFriend(name, num);
            if (ok) {
                JOptionPane.showMessageDialog(owner, "Friend added.", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(owner, "The contact exists", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(owner, "A number format has occured", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(owner, "An error has occured creating/opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
