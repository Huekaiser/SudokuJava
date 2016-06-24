package clasesPropias.Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Ranking2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblScore;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranking2 frame = new Ranking2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ranking2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		String[] headers = {"Posicion", "Usuario","Puntuacion Total"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding"}};
		table = new JTable(data,headers);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		
	
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblScore = new JLabel("Puntuacion");
		
				lblScore.setBorder(new EmptyBorder(5,5,5,5));
				lblScore.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(lblScore, BorderLayout.EAST);
				
				label = new JLabel("Posicion");
				label.setHorizontalAlignment(SwingConstants.LEFT);
				label.setBorder(new EmptyBorder(5,5,5,5));
				panel.add(label, BorderLayout.WEST);
		setTitle("Ranking");
	}

}
