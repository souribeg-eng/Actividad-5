package Friends;
// ...existing code...
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class UpdateAction implements ActionListener {
    private final FriendsManager manager;
    private final JTextField txtName;
    private final JTextField txtNumber;
    private final JFrame owner;

    public UpdateAction(FriendsManager mgr, JTextField name, JTextField number, JFrame owner) {
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
            boolean ok = manager.updateFriend(name, num);
            JOptionPane.showMessageDialog(owner, ok ? "Updated." : "Not found.", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(owner, "A number format has occured", "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(owner, "An error has occured creating/opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
