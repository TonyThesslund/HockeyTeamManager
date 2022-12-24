package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Insets;

/* Contains the code for the JFrame for 
 * confirming or declining the removal of matches or players.
 */

@SuppressWarnings("serial")
public class ConfirmationFrame extends MainFrame {

	private JPanel contentPane;

	public ConfirmationFrame(String action,String searchValue) throws Exception{
		setTitle(action);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 268, 113);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		// OPEN FRAME IN THE CENTER OF THE SCREEN
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Are you sure?");
		lblNewLabel.setBounds(87, 11, 89, 14);
		
		
		// CONFIRM BUTTON
		JButton btnRemoveConfirm = new JButton("Confirm");
		btnRemoveConfirm.setMargin(new Insets(2, 2, 2, 12));
		btnRemoveConfirm.setBounds(44, 36, 77, 23);
		btnRemoveConfirm.setFocusTraversalKeysEnabled(false);
		btnRemoveConfirm.setFocusable(false);
		btnRemoveConfirm.addActionListener(new ActionListener( ) {
			
			// REMOVES CORRECT LINE FROM CORRECT FILE
			// IF REMOVING MATCH, ALSO REMOVES GOALS
			public void actionPerformed(ActionEvent e) {
				try {					
					String a = "Remove Player";
					if (action.equals(a)) {					
						removeLine("src//players.csv",findLine("src//players.csv", searchValue));
						dispose();
					}
					else {
						removeLine("src//matches.csv",findLine("src//matches.csv", searchValue));
						removeGoals(searchValue);
						dispose();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		// "DECLINE" BUTTON
		JButton btnRemoveDecline = new JButton("Decline");
		btnRemoveDecline.setFocusTraversalKeysEnabled(false);
		btnRemoveDecline.setMargin(new Insets(2, 2, 2, 2));
		btnRemoveDecline.setBounds(127, 36, 77, 23);
		btnRemoveDecline.setFocusable(false);
		contentPane.setLayout(null);
		contentPane.add(btnRemoveConfirm);
		contentPane.add(btnRemoveDecline);
		contentPane.add(lblNewLabel);
		
		
		// CLICKING "DECLINE" SHUTS THE WINDOW
		btnRemoveDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
