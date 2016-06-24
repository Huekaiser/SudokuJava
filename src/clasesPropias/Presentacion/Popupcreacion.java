package clasesPropias.Presentacion;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import clasesPropias.Dominio.Clases.Tablero;
import clasesPropias.Dominio.Controladores.ComprobarDificultad;
import clasesPropias.Dominio.Controladores.ComprobarSolucionUnica;
import clasesPropias.Dominio.Controladores.ControladorCreacionTablero;
import clasesPropias.Dominio.Controladores.GenerarSudoku;
import clasesPropias.Persistencia.GestorFicherosTableros;
import clasesPropias.Presentacion.Controladores.ControladorPresentacion;

public class Popupcreacion extends SuperJFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelOculto;
	private JComboBox<Integer> comboCasillas;
	private JButton btnImportar;
	private JButton btnCrear;
	private JComboBox<Integer> comboTamTab; 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Popupcreacion frame = new Popupcreacion(100, 100, 450, 300);
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
	public Popupcreacion(int posx, int posy, int width, int height) {
		//Operaciones principales
		setTitle(getTitle()+" - Opciones Creacion");
		setBounds(posx,posy,width,height);
		btnLogout.setVisible(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		menuPanel.add(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		final JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(3, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("Tam Tab");
		panel_1.add(lblNewLabel);

		comboTamTab = new JComboBox<Integer>();
		comboTamTab.addItem(9);
		comboTamTab.addItem(16);
		panel_1.add(comboTamTab);

		comboTamTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer t = (Integer) comboTamTab.getSelectedItem();
				cambioNCasillas(t);
			}
		});
		JLabel lblNCasillas = new JLabel("Tipo Creaci\u00F3n");
		panel_1.add(lblNCasillas);

		ButtonGroup group = new ButtonGroup();

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JRadioButton rdbtnManual = new JRadioButton("Manual");
		rdbtnManual.setSelected(true);
		rdbtnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisiblilityAuto(false);
			}
		});
		panel_4.add(rdbtnManual);
		group.add(rdbtnManual);

		JRadioButton rdbtnAuto = new JRadioButton("Automatico");
		rdbtnAuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				setVisiblilityAuto(true);
			}
		});
		panel_4.add(rdbtnAuto);
		group.add(rdbtnAuto);

		panelOculto = new JPanel();
		panel.add(panelOculto);
		panelOculto.setLayout(new GridLayout(2, 2, 0, 0));

		JLabel lblDificultad = new JLabel("Dificultad");
		panelOculto.add(lblDificultad);

		JComboBox<String> comboDif = new JComboBox<String>();
		comboDif.addItem("Facil");
		comboDif.addItem("Medio");
		comboDif.addItem("Dificil");
		panelOculto.add(comboDif);

		JLabel lblNCPre = new JLabel("N\u00BA Casillas Predeterminadas");
		panelOculto.add(lblNCPre);

		comboCasillas = new JComboBox<Integer>();
		cambioNCasillas(9);
		panelOculto.add(comboCasillas);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		btnCrear = new JButton("Aceptar");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = String.valueOf((Integer)comboTamTab.getSelectedItem());
				int comboTam = Integer.parseInt(str);
				String diff= (String) comboDif.getSelectedItem();
				int nc= Integer.parseInt(String.valueOf((Integer)comboCasillas.getSelectedItem()));
				ControladorCreacionTablero cct= new ControladorCreacionTablero();
				int dif=0;
				switch (diff) {
				case "Facil": dif = 1;
				break;
				case "Medio": dif = 2;
				break;
				case "Dificil": dif = 3;
				break;
				default:
				}
				int r=0;
				Double x = getBounds().getX();
				Double y = getBounds().getY();
				Double w = getBounds().getWidth();
				Double h = getBounds().getHeight();
				Tablero t = new Tablero(comboTam,dif);
				try {
					JDialog j = new JDialog();
					j.setBounds( x.intValue(), y.intValue(), width, height);
					j.setTitle("Cargando");
					//	j.setVisible(true);
					j.show();
					////System.out.println("esteban");
					if(cct.creacionAutomatica(comboTam,dif,comboTam*comboTam-nc)){
						j.setVisible(false);
					}
					else{
						JOptionPane jop= new JOptionPane();
						r= jop.showOptionDialog(null, //Component parentComponent
								"¿Desea seguir intentandolo?", //Object message,
								"Time out", //String title
								JOptionPane.YES_NO_OPTION, //int optionType
								JOptionPane.INFORMATION_MESSAGE, //int messageType
								null, //Icon icon,
								null, //Object[] options,
								"Metric");

						while(r==0){
							if(cct.creacionAutomatica(comboTam,dif,comboTam*comboTam-nc)){
								r=2;
								j.setVisible(false);
							}
							else{
								r= jop.showOptionDialog(null, //Component parentComponent
										"¿Desea seguir intentandolo?", //Object message,
										"Time out", //String title
										JOptionPane.YES_NO_OPTION, //int optionType
										JOptionPane.INFORMATION_MESSAGE, //int messageType
										null, //Icon icon,
										null, //Object[] options,
										"Metric");}
						}
						if(r==1){
							j.show(false);
							////System.out.println("he tardado un puto huebo");
							ControladorPresentacion.VisibleOpcionesCrear(posx, posy, width, height);

							setVisible(false);
						}

					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(r==0 || r==2){
					t= cct.getTablero();

					if (comboTam == 9) {
						TableroCreacion9 tc = new TableroCreacion9(x.intValue(),y.intValue(),w.intValue(),h.intValue(),diff,t);
						tc.setVisible(true);
					}
					else {
						TableroCreacion16 tc = new TableroCreacion16(x.intValue(),y.intValue(),w.intValue(),h.intValue(),diff,t);
						tc.setVisible(true);
					}
				}
			}
		});
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_3.add(btnCrear);

		btnImportar = new JButton("Importar");
		panel_3.add(btnImportar);
		btnImportar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str = String.valueOf((Integer)comboTamTab.getSelectedItem());
				int comboTam = Integer.parseInt(str);
				////System.out.println("aaaa" + str);
				String fileName = JOptionPane.showInputDialog("Di un nombre de tablero","");
				GestorFicherosTableros gft = new GestorFicherosTableros();
				Tablero t = new Tablero(comboTam);
				try {
					setVisible(false);
					Double x = getBounds().getX();
					Double y = getBounds().getY();
					Double w = getBounds().getWidth();
					Double h = getBounds().getHeight();
					////System.out.println(fileName);
					if(fileName !=null){
						if (!fileName.equals("")){
							if(gft.existe(fileName)){
								t = gft.importTablero(fileName,comboTam);
								GenerarSudoku gs = new GenerarSudoku();
								Tablero t2 = new Tablero(comboTam);
								ComprobarSolucionUnica csu = new ComprobarSolucionUnica();

								csu.resolver(t); 
								if(csu.solucionValida(t)) {
									if(csu.tieneSolucionUnica(t)) {

										csu.resolver(t);
										gs.eliminarCandidatos(t);
										gs.copiar(t2, t);
										for(int i = 0; i<comboTam; ++i) {
											for(int j = 0; j<comboTam; ++j) {
												t2.setCandidatsTauler(i, j, t.getCandidatsTauler(i, j));
											}
										}
										ComprobarDificultad cd = new ComprobarDificultad(t2);
										//	t2.escribir();
										//	t2.escribirCandidatos();
										int d = cd.getDificultad();


										t.setDificultad(d);
										////System.out.println(d);
										String diff = null;
										switch (d) {
										case 1: diff = "Facil";
										break;
										case 2: diff = "Medio";
										break;
										case 3: diff = "Dificil";
										break;
										default:
										}

										if (comboTam == 9) {
											TableroCreacion9 tc = new TableroCreacion9(x.intValue(),y.intValue(),w.intValue(),h.intValue(),diff,t);
											tc.setVisible(true);
										}
										else {
											TableroCreacion16 tc = new TableroCreacion16(x.intValue(),y.intValue(),w.intValue(),h.intValue(),diff,t);
											tc.setVisible(true);
										}
									}

									else {
										JOptionPane.showMessageDialog(null, "tiene solucion, pero esta no es unica");
										setVisible(true);
									}
								}
								else {
									JOptionPane.showMessageDialog(null, "no tiene solucion");
									setVisible(true);
								}
							}
							else{
								////System.out.println("el nombre esta mal");
								JOptionPane.showMessageDialog(null, "no existe el tablero");
								setVisible(true);
							}
						}
						else{
							////System.out.println("he dado per esta vacio");
							JOptionPane.showMessageDialog(null, "Introduce un nombre valido");
							setVisible(true);
						}
					}
					else {
						ControladorPresentacion.VisibleOpcionesCrear(posx, posy, width, height);
					}


				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}

		});

							setVisiblilityAuto(false);
							backListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									setVisible(false);
									Double x = getBounds().getX();
									Double y = getBounds().getY();
									Double w = getBounds().getWidth();
									Double h = getBounds().getHeight();
									ControladorPresentacion.VisibleMenu(x.intValue(),y.intValue(),w.intValue(),h.intValue());
								}
							});

	}
	private void setVisiblilityAuto(boolean b){
		panelOculto.setVisible(b);
		btnImportar.setVisible(!b);
		btnCrear.setVisible(b);
	}
	private void cambioNCasillas(int n){
		comboCasillas.removeAllItems();
		if(n == 9){
			for(int i = 28; i <= 50; ++i){
				comboCasillas.addItem(i);
			}
		}else{
			for(int i = 140; i <= 250; ++i){
				comboCasillas.addItem(i);
			}
		}
	}
}
