package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MatchDetailsFrame extends FileHandler {
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel Model;

	MatchDetailsFrame(String date) throws Exception {
		Match match = null;
		for(Match m : getMatchArray()) {
			if(formatter.format(m.getDate()).equals(date)) {
				match = m;
			}
		}		
		
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Match Details  " + match.getOpponent() + " " + date);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 488, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		// OPEN FRAME IN THE CENTER OF THE SCREEN
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
	    setLocation(x, y);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblScoreline = new JLabel();
		lblScoreline.setHorizontalTextPosition(SwingConstants.LEADING);
		lblScoreline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScoreline.setBounds(283, 6, 167, 76);
		lblScoreline.setFont(new Font("Tahoma", Font.PLAIN, 52));
		lblScoreline.setText(match.getScoreline());
		contentPane.add(lblScoreline);
		
		JLabel lblResult = new JLabel();
		lblResult.setHorizontalTextPosition(SwingConstants.LEFT);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 53));
		lblResult.setHorizontalAlignment(SwingConstants.LEFT);
		lblResult.setBounds(21, 11, 215, 66);
		lblResult.setText(match.getResult());
		contentPane.add(lblResult);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 452, 184);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setRowHeight(20);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Time", "Player", "Assist"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});	
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		
		Model = (DefaultTableModel) table.getModel();		
		for(Goal g : match.getGoals()) {
			Model.insertRow(Model.getRowCount(), 
					new Object [] {
					g.getTime(),
					g.getPlayer(), 
					g.getAssist(), 
					});
		}
		
		scrollPane.setViewportView(table);			
	}
}


