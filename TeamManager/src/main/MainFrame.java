package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowFocusListener;
import java.io.IOException;
import java.awt.event.WindowEvent;

/* Contains the code for the main JFrame, 
 * and methods for refreshing tables. 
 * 
 * The main method is also found here.
 */




@SuppressWarnings("serial")
public class MainFrame extends FileHandler{
	private JPanel contentPane;
	private JTable playerTable;
	private JTable matchTable;	
	private DefaultTableModel pModel;
	private DefaultTableModel mModel;
	
	public static void main(String[] args) throws Exception {
		
		try {
			MainFrame frame = new MainFrame();	
			frame.setVisible(true);				
			
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}

 
	public MainFrame() throws Exception {	
		
		/////////////////////////////////////////////
		//////////// MAIN FRAME START ///////////////
		/////////////////////////////////////////////	
		
		setTitle("Hockey Team Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 495);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// OPEN FRAME IN THE MIDDLE OF THE SCREEN
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);
	        
	    
	    // REFRESH TABLES ON WINDOW FOCUS
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				try {
					refreshTables();							
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			public void windowLostFocus(WindowEvent e) {
				try {
					refreshTables();							
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		/////// "ADD PLAYER" BUTTON //////
		JButton btnAddPlayer = new JButton("Add Player");
		btnAddPlayer.setMargin(new Insets(2, 2, 2, 2));
		btnAddPlayer.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnAddPlayer.setFocusable(false);
		btnAddPlayer.setBounds(12, 416, 89, 29);
		contentPane.add(btnAddPlayer);
		
		btnAddPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AddPlayerFrame apFrame = new AddPlayerFrame();
					apFrame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});	
		
		
		/////// "REMOVE PLAYER" BUTTON ///////
		JButton btnRemovePlayer = new JButton("Remove Player");
		btnRemovePlayer.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnRemovePlayer.setFocusable(false);
		btnRemovePlayer.setMargin(new Insets(2, 2, 2, 2));
		btnRemovePlayer.setBounds(107, 416, 90, 29);
		contentPane.add(btnRemovePlayer);
		btnRemovePlayer.setEnabled(false);
		
		btnRemovePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = playerTable.getSelectedRow();
				int column = 0;
				String playerName = playerTable.getValueAt(row, column).toString();
					
				try {
					ConfirmationFrame rpFrame = new ConfirmationFrame("Remove Player" ,playerName);
					rpFrame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
				
		
		//////// "MATCH DETAILS" BUTTON	////////			
		JButton btnMatchDetails = new JButton("Match Details");
		btnMatchDetails.setEnabled(false);
		btnMatchDetails.setMargin(new Insets(2, 2, 2, 2));
		btnMatchDetails.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnMatchDetails.setFocusable(false);
		btnMatchDetails.setBounds(734, 416, 89, 29);
		contentPane.add(btnMatchDetails);
		btnMatchDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = matchTable.getSelectedRow();
					int column = 0;
					String date = matchTable.getValueAt(row, column).toString();
					MatchDetailsFrame mdFrame = new MatchDetailsFrame(date);
					mdFrame.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		//////// "ADD NEW MATCH" BUTTON ////////
		JButton btnNewMatch = new JButton("Add New Match");
		btnNewMatch.setFocusable(false);
		btnNewMatch.setMargin(new Insets(2, 2, 2, 2));
		btnNewMatch.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnNewMatch.setBounds(889, 416, 90, 29);
		contentPane.add(btnNewMatch);
		btnNewMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					AddMatchFrame amFrame = new AddMatchFrame();
					amFrame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		//////// REMOVE MATCH BUTTON //////////
		JButton btnRemoveMatch = new JButton("Remove Match");
		btnRemoveMatch.setFocusable(false);
		btnRemoveMatch.setEnabled(false);
		btnRemoveMatch.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnRemoveMatch.setMargin(new Insets(2, 2, 2, 2));
		btnRemoveMatch.setBounds(984, 416, 89, 29);
		contentPane.add(btnRemoveMatch);
		btnRemoveMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = matchTable.getSelectedRow();
				int column = 0;
				String matchDate = matchTable.getValueAt(row, column).toString();
				try {
					ConfirmationFrame rmFrame = new ConfirmationFrame("Remove Match", matchDate);
					rmFrame.setVisible(true);					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		
		//////// EXPORT BUTTON //////////
		
		JButton btnExport = new JButton("Export Statistics");
		btnExport.setToolTipText("Export Statistics To Text File");
		btnExport.setMargin(new Insets(2, 2, 2, 2));
		btnExport.setFocusable(false);
		btnExport.setBounds(617, 416, 111, 29);
		contentPane.add(btnExport);
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					exportStats();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

	
		/////////////////////////////////////////////
		////////////// BUTTONS END //////////////////
		/////////////////////////////////////////////

		
		
		
		
		/////////////////////////////////////////////
		////////////// TABLES START /////////////////
		/////////////////////////////////////////////		
		
		/////// PLAYER TABLE ///////	
		JScrollPane teamPane = new JScrollPane();
		teamPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		teamPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Team", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		teamPane.setBounds(10, 11, 721, 403);
		contentPane.add(teamPane);
		
		playerTable = new JTable();
		playerTable.setAlignmentX(Component.LEFT_ALIGNMENT);
		playerTable.setAutoCreateRowSorter(true);
		playerTable.setFocusable(false);
		playerTable.setRowHeight(20);
		teamPane.setViewportView(playerTable);
		
		// EMPTY TABLE, VALUES WILL BE ADDED FROM THE "players.csv" FILE
		playerTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Name", "Position", "Total Points", "Goals", "Assists"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		playerTable.getColumnModel().getColumn(0).setResizable(false);
		playerTable.getColumnModel().getColumn(1).setResizable(false);
		playerTable.getColumnModel().getColumn(2).setResizable(false);
		playerTable.getColumnModel().getColumn(3).setResizable(false);
		playerTable.getColumnModel().getColumn(4).setResizable(false);
		playerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		playerTable.setFillsViewportHeight(true);
		playerTable.getTableHeader().setReorderingAllowed(false);
		pModel = (DefaultTableModel) playerTable.getModel();
		
		// SELECTING A MATCH FROM TABLE ENABLES "REMOVE PLAYER" BUTTON
		playerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override		
			public void valueChanged(ListSelectionEvent e) {
				if(playerTable.getSelectedRow() == -1) {
					btnRemovePlayer.setEnabled(false);
				}
				else {
					btnRemovePlayer.setEnabled(true);
				}
			}
		});
		
		
		//////////////MATCH TABLE//////////////	
		JScrollPane matchesPane = new JScrollPane();
		matchesPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		matchesPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Matches", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		matchesPane.setBounds(733, 11, 341, 403);
		contentPane.add(matchesPane);
		
		matchTable = new JTable();
		matchTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		matchTable.setFocusable(false);
		matchTable.setRowHeight(20);
		matchTable.setFillsViewportHeight(true);
		matchTable.getTableHeader().setReorderingAllowed(false);
		matchesPane.setViewportView(matchTable);

		
		// EMPTY TABLE, VALUES WILL BE ADDED FROM THE "matches.csv" FILE
		matchTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Date", "Opponent", "Result"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		matchTable.getColumnModel().getColumn(0).setResizable(false);
		matchTable.getColumnModel().getColumn(1).setResizable(false);
		matchTable.getColumnModel().getColumn(2).setResizable(false);	
		mModel = (DefaultTableModel) matchTable.getModel();
		
		
		// SELECTING A MATCH FROM TABLE ENABLES "REMOVE MATCH" AND "MATCH DETAILS" BUTTONS
		matchTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override		
			public void valueChanged(ListSelectionEvent e) {
				if(matchTable.getSelectedRow() == -1) {
					btnRemoveMatch.setEnabled(false);
					btnMatchDetails.setEnabled(false);
				}
				else {
					btnRemoveMatch.setEnabled(true);
					btnMatchDetails.setEnabled(true);
				}
			}
		});
		
		
}		
		/////////////////////////////////////////////
		/////////////// TABLES END //////////////////
		/////////////////////////////////////////////
		
		
				
	
	
	/////////////////////////////////////////////
	/////////////// METHODS START ///////////////
	/////////////////////////////////////////////
	
	// METHOD TO RELOAD TABLES
	public void refreshTables() throws Exception{
		
		getPlayerArray().clear(); 	// CLEAR ARRAYLIST	
		getPlayerArray(); 			// GET DATA FROM "players.csv"	
		pModel.setRowCount(0); 		// CLEAR TABLE
	
		
		// FILL TABLE WITH DATA FROM ARRAYLIST		
		for(int i = 0; i < getPlayerArray().size(); i++) {
			pModel.insertRow(pModel.getRowCount(), 
					new Object [] {
					getPlayerArray().get(i).getName(), 
					getPlayerArray().get(i).getPosition(), 
					getPlayerArray().get(i).getPoints(),
					getPlayerArray().get(i).getGoals(),
					getPlayerArray().get(i).getAssists()
					});
		}
		
		
		getMatchArray().clear();	// CLEAR ARRAYLIST
		getMatchArray();			// GET DATA FROM "matches.csv"
		mModel.setRowCount(0);		// CLEAR TABLE
		
		// FILL MATCH TABLE WITH DATA FROM ARRAYLIST
		for(int i = 0; i < getMatchArray().size(); i++) {
			mModel.insertRow(mModel.getRowCount(), 
					new Object [] {
					formatter.format(getMatchArray().get(i).getDate()),
					getMatchArray().get(i).getOpponent(), 
					getMatchArray().get(i).getResult() + "   " + getMatchArray().get(i).getScoreline(), 
					});
		}
	}
	
}

