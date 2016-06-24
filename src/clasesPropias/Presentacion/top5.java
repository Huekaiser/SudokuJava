package clasesPropias.Presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import clasesPropias.Presentacion.Controladores.ControladorPresentacion;
public class top5 extends SuperJFrame {

	private JPanel panel_1;
	private JTable table;

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 */
	public top5(final int posx, final int posy, final int width, final int height,String[][]s,int tiempo) throws ClassNotFoundException, IOException, InterruptedException {
		setBounds(posx, posy, width, height);
		String segundos;
		String horas;
		String minutos;
		menuPanel.setLayout(new BorderLayout(0, 0));
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setLayout(new BorderLayout(0, 0));
		menuPanel.add(panel_1);
		String[] headers = {"Posicion", "Usuario","Tiempo"};
		String[][] data = new String[s.length][3];
		for(int i = 0; i < s.length; ++i){
			data[i][0] = String.valueOf(i+1);
			data[i][1] = s[i][0];
			int time = Integer.parseInt(s[i][1]);
			segundos = String.format("%02d", time %60);
			horas = String.format("%02d",time /(3600));
			minutos = String.format("%02d",(time /60)%60);
			data[i][2] = horas+":"+minutos+":"+segundos;

		}

		table = new JTable(data,headers);
		panel_1.add(new JScrollPane(table), BorderLayout.CENTER);
		setTitle(getTitle()+" - Top 5");
		JOptionPane op= new JOptionPane();
		segundos = String.format("%02d", tiempo %60);
		horas = String.format("%02d",tiempo /(3600));
		minutos = String.format("%02d",(tiempo /60)%60);
		setVisible(true);
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
		op.showMessageDialog(null, "Tu tiempo ha sido: "+ horas+":"+minutos+":"+segundos, " ", JOptionPane.INFORMATION_MESSAGE);
	}

}
