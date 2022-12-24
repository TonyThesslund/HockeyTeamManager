package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.Insets;

// Contains the code for the JFrame for adding new matches.

@SuppressWarnings("serial")
public class AddMatchFrame extends MainFrame {
	
	private JPanel contentPane;
	private JTable goalTable;
	private JTextField txtOpponent;
	private ArrayList<Goal> matchGoals = new ArrayList<Goal>();
	private DefaultTableModel gModel;

	public AddMatchFrame() throws Exception{
		setTitle("Add New Match");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 820, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// OPEN FRAME IN THE CENTER OF THE SCREEN
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);

		
		
		/////////////////////////////////////////////
		/// LABELS, TEXTFIELDS, COMBOBOXES START ////
		/////////////////////////////////////////////
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setBounds(8, 11, 46, 14);
		contentPane.add(lblDay);
		
		JComboBox<Integer> cbDay = new JComboBox<Integer>();
		cbDay.setBounds(8, 28, 55, 22);
		for(int i = 1; i <= 31; i++) {
			cbDay.addItem(i);
		}
		contentPane.add(cbDay);
		
		
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(73, 11, 46, 14);
		contentPane.add(lblMonth);
		
		JComboBox<Integer> cbMonth = new JComboBox<Integer>();
		cbMonth.setBounds(73, 28, 55, 22);
		for(int i = 1; i <= 12; i++) {
			cbMonth.addItem(i);
		}
		contentPane.add(cbMonth);
		
		
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(138, 11, 46, 14);
		contentPane.add(lblYear);
		
		JComboBox<Integer> cbYear = new JComboBox<Integer>();
		cbYear.setBounds(138, 28, 78, 22);
		for(int i = 2020; i <= 2050; i++) {
			cbYear.addItem(i);
		}
		contentPane.add(cbYear);
		
		
		
		JLabel lblOpponent = new JLabel("Opponent");
		lblOpponent.setBounds(8, 61, 102, 14);
		contentPane.add(lblOpponent);
		
		txtOpponent = new JTextField();
		txtOpponent.setBounds(8, 79, 133, 20);
		contentPane.add(txtOpponent);
		txtOpponent.setColumns(10);
		
		JLabel lblRequired = new JLabel("REQUIRED");
		lblRequired.setVisible(true);
		lblRequired.setForeground(Color.RED);
		lblRequired.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRequired.setBounds(151, 82, 123, 14);
		contentPane.add(lblRequired);
		
		JLabel lblScorer = new JLabel("Scorer");
		lblScorer.setBounds(8, 156, 46, 14);
		contentPane.add(lblScorer);
		
		JComboBox<String> cbScorer = new JComboBox<String>();

		cbScorer.setBounds(8, 175, 150, 22);
		cbScorer.addItem("Opponent");
			for(Player p : getPlayerArray()) {
				cbScorer.addItem(p.getName());
			}
		contentPane.add(cbScorer);
				
		JLabel lblAssist = new JLabel("Assist");
		lblAssist.setBounds(168, 156, 46, 14);
		contentPane.add(lblAssist);
		
		JComboBox<String> cbAssist = new JComboBox<String>();
		cbAssist.setBounds(168, 175, 150, 22);
		cbAssist.addItem("Opponent");
		cbAssist.setEnabled(false);
		for(Player p : getPlayerArray()) {
			cbAssist.addItem(p.getName());
		}
		contentPane.add(cbAssist);
		
			
		// ITEMLISTENER TO CHECK IF SCORER IS OPPONENT
		// IF SCORER IS OPPONENT -> DISABLE ASSIST COMBOBOX AND SET SELECTED INDEX TO 0 ("Opponent")
		cbScorer.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String selection = cbScorer.getSelectedItem().toString();
				if(selection == "Opponent") {
					cbAssist.setEnabled(false);
					cbAssist.setSelectedIndex(0);

				}
				else {
					cbAssist.setEnabled(true);	
					cbAssist.setSelectedIndex(1);
				}
				
			}
			
		});
		
		JLabel lblPeriod = new JLabel("Period");
		lblPeriod.setBounds(8, 208, 46, 14);
		contentPane.add(lblPeriod);
		
		JComboBox<Integer> cbPeriod = new JComboBox<Integer>();
		cbPeriod.addItem(1);
		cbPeriod.addItem(2);
		cbPeriod.addItem(3);
		cbPeriod.setBounds(8, 225, 55, 22);
		contentPane.add(cbPeriod);
		
		
		
		JLabel lblMinute = new JLabel("Minute");
		lblMinute.setBounds(73, 208, 46, 14);
		contentPane.add(lblMinute);
		
		JComboBox<Integer> cbMinute = new JComboBox<Integer>();
		cbMinute.setBounds(73, 225, 55, 22);
		for(int i = 0; i < 20; i++) {
			cbMinute.addItem(i);
		}
		contentPane.add(cbMinute);
		
		
		
		JLabel lblSecond = new JLabel("Second");
		lblSecond.setBounds(138, 208, 46, 14);
		contentPane.add(lblSecond);
		
		JComboBox<Integer> cbSecond = new JComboBox<Integer>();
		cbSecond.setBounds(138, 225, 55, 22);
		for(int i = 0; i < 60; i++) {
			cbSecond.addItem(i);
		}
		contentPane.add(cbSecond);		
		
		JLabel lblAddGoal = new JLabel("Add Goal");
		lblAddGoal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddGoal.setBounds(8, 137, 91, 14);
		contentPane.add(lblAddGoal);
				
		JLabel lblScoreline = new JLabel();		
		lblScoreline.setFont(new Font("Tahoma", Font.PLAIN, 55));
		lblScoreline.setBounds(671, 11, 123, 64);
		contentPane.add(lblScoreline);
		getScoreline(lblScoreline);
		
		/////////////////////////////////////////////
		//// LABELS, TEXTFIELDS, COMBOBOXES END /////
		/////////////////////////////////////////////
		
		
		
		/////////////////////////////////////////////
		/////////////// BUTTONS START ///////////////
		/////////////////////////////////////////////
		
		////// ADD GOAL TO ARRAY
		JButton btnAddGoal = new JButton("Add Goal");
		btnAddGoal.setFocusable(false);
		btnAddGoal.setBounds(203, 208, 115, 39);
		contentPane.add(btnAddGoal);
		btnAddGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String scorer = ((String) cbScorer.getSelectedItem());
				String assist = ((String) cbAssist.getSelectedItem());
				int period = ((int) cbPeriod.getSelectedItem());
				int minute = ((int) cbMinute.getSelectedItem());
				int second = ((int) cbSecond.getSelectedItem());
				matchGoals.add(new Goal(scorer,assist,period,minute,second));
				cbScorer.setSelectedIndex(0);
				cbAssist.setSelectedIndex(0);
				refreshGoals();
				getScoreline(lblScoreline);
				
				
			}
		});
		
		
		///////////////// REMOVE GOAL BUTTON ///////////////////
		JButton btnRemoveGoal = new JButton("Remove Goal");
		btnRemoveGoal.setFocusable(false);
		btnRemoveGoal.setMargin(new Insets(2, 2, 2, 2));
		btnRemoveGoal.setEnabled(false);
		btnRemoveGoal.setBounds(375, 48, 107, 23);
		contentPane.add(btnRemoveGoal);
		
		// REMOVES GOAL FROM ARRAYLIST USING THE INDEX FROM SELECTED TABLEROW
		btnRemoveGoal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = goalTable.getSelectedRow();
				ArrayList<Goal> tempArray = new ArrayList<Goal>();
				for(int i = 0; i < matchGoals.size(); i++) {
					if(i != row) {
						tempArray.add(matchGoals.get(i));
					}
				}
				matchGoals = tempArray;	
				refreshGoals();
				getScoreline(lblScoreline);
			}
		});
		
		
		
		//////////////// SUBMIT MATCH BUTTON /////////////////
		JButton btnAddMatch = new JButton("Add Match");
		btnAddMatch.setFocusable(false);
		btnAddMatch.setEnabled(false);
		btnAddMatch.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnAddMatch.setBounds(8, 267, 355, 64);
		contentPane.add(btnAddMatch);	
		
		
		// DOCUMENTLISTENER TO ENABLE "ADD MATCH" BUTTON ONLY AFTER OPPONENT IS FILLED OUT
		txtOpponent.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				if (txtOpponent.getText().equals("")) {
					btnAddMatch.setEnabled(false);
					lblRequired.setVisible(true);
				}
				else {
					btnAddMatch.setEnabled(true);
					lblRequired.setVisible(false);
				}
			}
			public void removeUpdate(DocumentEvent e) {
				if (txtOpponent.getText().equals("")) {
					btnAddMatch.setEnabled(false);
					lblRequired.setVisible(true);
				}
				else {
					btnAddMatch.setEnabled(true);
					lblRequired.setVisible(false);
				}
			}
			public void insertUpdate(DocumentEvent e) {
				if (txtOpponent.getText().equals("")) {
					btnAddMatch.setEnabled(false);
					lblRequired.setVisible(true);
				}
				else {
					btnAddMatch.setEnabled(true);
					lblRequired.setVisible(false);
				}
			}
		});
		
		
		///////// WRITE MATCHES AND GOALS TO CSV FILES ////////
		btnAddMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String matchDate = cbDay.getSelectedItem() + "." + cbMonth.getSelectedItem() + "." + cbYear.getSelectedItem();
					
					// WRITE MATCH INFORMATION TO matches.csv
					File matchFile = new File("src//matches.csv");
					if(!matchFile.exists()) {					
						matchFile.createNewFile();
					}
					
					String matchLine = matchDate + ";" + txtOpponent.getText();
					FileWriter mfw = new FileWriter(matchFile, true);
					BufferedWriter mbw = new BufferedWriter(mfw);
					PrintWriter mpw = new PrintWriter(mbw);					
					mpw.println(matchLine);
					mpw.close();
					
					// WRITE GOALS TO goals.csv
					File goalFile = new File("src//goals.csv");
					if(!goalFile.exists()) {					
						goalFile.createNewFile();
					}
					for(Goal g : matchGoals) {
						String goalLine = matchDate + ";" + g.getPlayer() + ";" + g.getAssist() + ";" + g.getPeriod() + ";" + g.getMinute() + ";" + g.getSecond();
						
						FileWriter gfw = new FileWriter(goalFile, true);
						BufferedWriter gbw = new BufferedWriter(gfw);
						PrintWriter gpw = new PrintWriter(gbw);
						
						gpw.println(goalLine);
						gpw.close();
					}
									
					dispose();
					
				}
				catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		/////////////////////////////////////////////
		//////////////// BUTTONS END ////////////////
		/////////////////////////////////////////////
		
		
		
		
		
		/////////////////////////////////////////////
		//////////////// TABLE START ////////////////
		/////////////////////////////////////////////
  
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new TitledBorder(null, "Goals", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(375, 82, 419, 251);
		contentPane.add(scrollPane);
		
		goalTable = new JTable();
		goalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		goalTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(goalTable);
		
		goalTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Time", "Scorer", "Assist"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
		goalTable.getColumnModel().getColumn(0).setResizable(false);
		goalTable.getColumnModel().getColumn(1).setResizable(false);
		goalTable.getColumnModel().getColumn(2).setResizable(false);
		gModel = (DefaultTableModel) goalTable.getModel();
		
		goalTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override		
			public void valueChanged(ListSelectionEvent e) {
				if(goalTable.getSelectedRow() == -1) {
					btnRemoveGoal.setEnabled(false);
				}
				else {
					btnRemoveGoal.setEnabled(true);
				}
			}
		});
		
		
		
		/////////////////////////////////////////////
		//////////////// TABLE END //////////////////
		/////////////////////////////////////////////
		
		
}
	
	
	/////////////////////////////////////////////
	/////////////// METHODS START ///////////////
	/////////////////////////////////////////////
	
	// METHOD TO REFRESH THE GOAL TABLE WHEN GOAL IS ADDED OR REMOVED
	public void refreshGoals() {
		gModel.setRowCount(0);		// CLEAR TABLE
		
		// FILL TABLE WITH DATA FROM ARRAYLIST
		for(int i = 0; i < matchGoals.size(); i++) {
			gModel.insertRow(gModel.getRowCount(), 
					new Object [] {
					matchGoals.get(i).getTime(),
					matchGoals.get(i).getPlayer(), 
					matchGoals.get(i).getAssist(), 
					});
		
			// SORT GOALS BY TIME
			Collections.sort(matchGoals, new Comparator<Goal>() {

				@Override
				public int compare(Goal o1, Goal o2) {
					if(o1.getTimeInSeconds() > o2.getTimeInSeconds()) {
						return 1;
					}
					else {
						return -1;
					}
				}
				
			});
		}
	}
	
	// METHOD TO GET SCORELINE. SETS THE COLOR OF SCORELINE LABEL ACCORDINGLY
	public void getScoreline(JLabel lbl) {
		int score = 0;
		int opponentScore = 0;
		for (int i = 0; i < matchGoals.size();i++) {
			if(matchGoals.get(i).getPlayer().equals("Opponent")) {
				opponentScore = opponentScore + 1;
			}
			else {
				score = score + 1;
			}
		}
		
		if(score > opponentScore) {
			lbl.setForeground(Color.GREEN);
		}
		else if(score == opponentScore) {
			lbl.setForeground(Color.GRAY);
		}
		else {
			lbl.setForeground(Color.RED);
		}
		
		lbl.setText(score + " - " + opponentScore);
	}
}
