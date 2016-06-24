package clasesPropias.Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import clasesPropias.Presentacion.Controladores.ControladorPresentacion;
import clasesPropias.Presentacion.Controladores.ControladorRanking;
public class Ranking extends SuperJFrame {

	private JPanel panel_1;
	private JTable table;
	private JLabel lblScore;
	private JLabel label;

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Ranking(final int posx, final int posy, final int width, final int height) throws ClassNotFoundException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(posx, posy, width, height);
		menuPanel.setLayout(new BorderLayout(0, 0));
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setLayout(new BorderLayout(0, 0));
		menuPanel.add(panel_1);
		String[] headers = {"Posicion", "Usuario","Puntuacion Total"};
		
		/*Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding"}};*/

		ControladorRanking cr = new ControladorRanking();
		
		ArrayList<ArrayList<String>> data ;
		data = cr.getRanking();

		String pos = "Posicion";
		String punt = "Puntuacion";
		String[][] datos = new String[data.size()][3];
		for (int i = 0; i < data.size(); i++) {
			datos[i][0] = data.get(i).get(0);
			datos[i][1] = data.get(i).get(1);
			datos[i][2] = data.get(i).get(2);
			if(datos[i][1].equals(ControladorPresentacion.getUser())){
				pos = datos[i][0]+"."+datos[i][1];
				punt = datos[i][2];
			}
		}
		System.out.println(ControladorPresentacion.getUser());
		table = new JTable(datos,headers);
		panel_1.add(new JScrollPane(table), BorderLayout.CENTER);
		
	
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblScore = new JLabel(punt);
		
				lblScore.setBorder(new EmptyBorder(5,5,5,5));
				lblScore.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(lblScore, BorderLayout.EAST);
				
				label = new JLabel(pos);
				label.setHorizontalAlignment(SwingConstants.LEFT);
				label.setBorder(new EmptyBorder(5,5,5,5));
				panel.add(label, BorderLayout.WEST);
				setTitle(getTitle()+" - Ranking");
		backListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				Double x = getBounds().getX();
				Double y = getBounds().getY();
				Double w = getBounds().getWidth();
				Double h = getBounds().getHeight();
				ControladorPresentacion.VisibleMenu(x.intValue(),y.intValue(),w.intValue(),h.intValue());
			}
		});
		btnLogout.setVisible(false);
	}
	
}
