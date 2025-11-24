package Friends;
// ...existing code...
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class ReadAction implements ActionListener {
    private final FriendsManager manager;
    private final JFrame owner;

    public ReadAction(FriendsManager mgr, JFrame owner) {
        this.manager = mgr;
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            List<String> lines = manager.getAllFriends();
            JTextArea area = new JTextArea(15, 40);
            area.setEditable(false);
            for (String l : lines) area.append(l + "\n");
            JScrollPane scroll = new JScrollPane(area);
            JOptionPane.showMessageDialog(owner, scroll, "Contacts", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(owner, "An error has occured creating/opening the file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}