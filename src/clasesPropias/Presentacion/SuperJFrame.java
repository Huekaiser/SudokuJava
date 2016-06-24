package clasesPropias.Presentacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesPropias.Presentacion.Controladores.ControladorPresentacion;

abstract class SuperJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JPanel menuPanel;
	protected JPanel panelAux;
	protected JMenuBar menuBar;
	protected JButton btnSalir;
	protected JButton btnLogout;
	protected JPanel panelAuxMedio;
	private JButton btnNewButton;
	/**
	 * Create the frame.
	 */
	public SuperJFrame() {
		//Opcion por defecto
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	//setBounds(100, 100, 450, 300);	
		//Opciones generales
			//Imagen
		ImageIcon img = new ImageIcon("./res/img/icon.png");
		setIconImage(img.getImage());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		menuPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) menuPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		contentPane.add(menuPanel, BorderLayout.CENTER);
		//Panel inferior
		JPanel panelAux = new JPanel();
		contentPane.add(panelAux, BorderLayout.SOUTH);
		panelAux.setLayout(new BorderLayout(0, 0));
		btnSalir = new JButton("Back");
		panelAux.add(btnSalir, BorderLayout.WEST);

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "Seguro que desea cerrar su sesi�n?","Cerrar Sesion",0);
				if(dialogResult == 0){
					setVisible(false);
					Double x = getBounds().getX();
					Double y = getBounds().getY();
					Double w = getBounds().getWidth();
					Double h = getBounds().getHeight();
					ControladorPresentacion.setUser("");
					ControladorPresentacion.VisibleLogin(x.intValue(),y.intValue(),w.intValue(),h.intValue());
				}
			}
		});
		panelAux.add(btnLogout, BorderLayout.EAST);
		
		panelAuxMedio = new JPanel();
		panelAux.add(panelAuxMedio,BorderLayout.CENTER);
		panelAuxMedio.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		//Label
		setTitle("Sudoku Grupo 45");
	}
	protected void setBottomVisibility(Boolean b){
		btnSalir.setVisible(b);
		btnLogout.setVisible(b);
	}
	protected void backListener(ActionListener act){
		btnSalir.addActionListener(act);
	}
	
}
