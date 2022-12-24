package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Font;

// AddPlayerFrame contains the code for the JFrame for adding new players to the team.

@SuppressWarnings("serial")
public class AddPlayerFrame extends FileHandler{

	private JPanel contentPane;
	private JTextField txtPlayerName;

	AddPlayerFrame() throws Exception{
		setAlwaysOnTop(true);
		setTitle("Add Player");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 334, 184);
		setResizable(false);
		setFocusable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		setContentPane(contentPane);
			
		// OPEN FRAME IN THE CENTER OF THE SCREEN
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);

		
		txtPlayerName = new JTextField();
		txtPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPlayerName.setBounds(15, 36, 150, 26);
		txtPlayerName.setColumns(10);
		
		JComboBox<String> cbPosition = new JComboBox<String>();
		cbPosition.setBounds(175, 36, 128, 26);
		cbPosition.setModel(new DefaultComboBoxModel<String>(new String[] {"Center", "Left Defence", "Left Wing", "Right Defence", "Right Wing"}));

		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setBounds(15, 16, 120, 14);
		lblPlayerName.setLabelFor(txtPlayerName);
		
		JLabel lblNewLabel = new JLabel("Player Position");
		lblNewLabel.setBounds(173, 16, 93, 14);
		
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setEnabled(false);
		btnAddPlayer.setMargin(new Insets(2, 1, 2, 1));
		btnAddPlayer.setFocusable(false);
		btnAddPlayer.addActionListener(new ActionListener() {
			
			// WRITE PLAYER TO CSV FILE
			public void actionPerformed(ActionEvent e) {
				try {
					File file = new File("src//players.csv");
					if(!file.exists()) {					
						file.createNewFile();
					}
					
					String line = txtPlayerName.getText() + ";" + cbPosition.getSelectedItem();
					FileWriter fw = new FileWriter(file, true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw);					
					pw.println(line);
					pw.close();
					
					dispose();
					
				}
				catch (Exception e1){
					
				}
			}
		});
		btnAddPlayer.setBounds(15, 78, 288, 44);
		contentPane.setLayout(null);
		contentPane.add(txtPlayerName);
		contentPane.add(cbPosition);
		contentPane.add(lblPlayerName);
		contentPane.add(lblNewLabel);
		contentPane.add(btnAddPlayer);
		
		
		// DOCUMENT LISTENER TO ENABLE "ADD PLAYER" BUTTON WHEN NAME IS FILLED OUT
		
		txtPlayerName.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (txtPlayerName.getText().equals("")) {
					btnAddPlayer.setEnabled(false);
				}
				else {
					btnAddPlayer.setEnabled(true);
				}
			}
			public void removeUpdate(DocumentEvent e) {
				if (txtPlayerName.getText().equals("")) {
					btnAddPlayer.setEnabled(false);
				}
				else {
					btnAddPlayer.setEnabled(true);
				}
			}
			public void insertUpdate(DocumentEvent e) {
				if (txtPlayerName.getText().equals("")) {
					btnAddPlayer.setEnabled(false);
				}
				else {
					btnAddPlayer.setEnabled(true);
				}
			}
		});

	}
}
