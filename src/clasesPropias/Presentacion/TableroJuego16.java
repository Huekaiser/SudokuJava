package clasesPropias.Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import clasesComunes.Usuario_comp;
import clasesPropias.Dominio.Clases.MyEntry;
import clasesPropias.Dominio.Clases.Tablero;
import clasesPropias.Dominio.Controladores.ControladorPartida;
import clasesPropias.Presentacion.Controladores.ControladorPresentacion;

public class TableroJuego16 extends SuperTableroJuego{

	private Integer[] id;
	protected ArrayList<JTextField> celdas;
	protected ControladorPartida cp;
	private int quedan;
	
	public TableroJuego16(final int posx, final int posy, final int width, final int height, final String dif, final boolean cargar, String nomP, Integer id2) throws ClassNotFoundException, IOException{
		setBounds(posx,posy,width,height);
		celdas = new ArrayList<JTextField>();
		panelJuego.setLayout(new GridLayout(16, 0, 0, 0));
		int regionBorder = 4;
		for(int i = 0; i < 16*16; ++i){
			JTextField tmp = new JTextField(String.valueOf(i%64));
			tmp.setPreferredSize(new Dimension(30,30));
			tmp.setHorizontalAlignment(JTextField.CENTER);
			tmp.setBorder(new LineBorder(new Color(0, 0, 0), 1));

			//Calculo borde
			if((i % 64 >= 48 && i % 64 <=63)  && i < 240){
				if((i % 4 == 3 && i % 16 != 15)){
					tmp.setBorder(new MatteBorder(1, 1, regionBorder, regionBorder, (Color) new Color(0, 0, 0)));
				}else
					tmp.setBorder(new MatteBorder(1, 1, regionBorder, 1, (Color) new Color(0, 0, 0)));
			}else if(i % 4 == 3 && i % 16 != 15){
				tmp.setBorder(new MatteBorder(1, 1, 1, regionBorder, (Color) new Color(0, 0, 0)));
			}else{
				tmp.setBorder(new LineBorder(new Color(0, 0, 0), 1));
			}
			celdas.add(tmp);
			panelJuego.add(celdas.get(i));
		}
		//Get Valor Partida
		cp = new ControladorPartida();
		Usuario_comp u = new Usuario_comp(ControladorPresentacion.getUser(),null);
		int n=3;
		if(dif.equals("Facil")) n=1;
		else if(dif.equals("Normal")) n=2;
		Tablero t= new Tablero(16,n);
		id= new Integer[1];
		boolean nuevo= true;
		if(cargar){
			id[0]=-1;
			nuevo=false;
		}
		else id[0]=id2;
		//System.out.println("sigo queriendo leer el: "+id[0]);
		cp.init(u, cargar, nuevo, t, id , nomP);
		Integer [][] mat= new Integer [16][16];
		mat=cp.getMatrizValores();

		for ( int i3 = 0; i3 < mat.length; i3++) {
			for (int j = 0; j < mat.length; j++) {
				if(mat[i3][j]!=0){
					celdas.get(i3*16+j).setText(mat[i3][j].toString());
					if(cp.isPordefecto(i3,j)){
						celdas.get(i3*16+j).setEditable(false);
						celdas.get(i3*16+j).setFont(new Font("Verdana", Font.BOLD, 12));
					}
				}
				else{
					celdas.get(i3*16+j).setText("");
					celdas.get(i3*16+j).addFocusListener(new FocusAdapter() {
						@Override
						public void focusLost(FocusEvent e){
							int x, y;
							x=0;
							y=0;
							for (int i2  = 0; i2  < 16; i2 ++) {
								for (int j2 = 0; j2 < 16; j2++) {
									if(e.getSource()==celdas.get(i2*16+j2)){
										x=i2;
										y=j2;
									}
								}
							}
							if(valido(celdas.get(x*16+y).getText())){
								if(!cp.setNum(x, y, Integer.parseInt(celdas.get(x*16+y).getText()))){
									celdas.get(x*16+y).setText("");
									JOptionPane op= new JOptionPane();
									op.showMessageDialog(null, "Número no válido","Error", JOptionPane.ERROR_MESSAGE);
								}
								else{
									boolean acabado= true;
									Integer[][] matP= new Integer[16][16];
									matP=cp.getMatrizValores();
									for (int i = 0; i < 16; i++) {
										for (int j2 = 0; j2 < 16; j2++) {
											if(matP[i][j2]==0){
												++quedan;
												acabado=false;
											}
										}
									}
									//aqui llama a mostrar el record!! :D :D :D :D :D
									if(acabado){
										try {
											cp.actualizarRanking(ControladorPresentacion.getUser(), Math.max(cp.getPuntuacion()*cp.getDificultad(),0));
										} catch (ClassNotFoundException | IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										cp.setTiempo(ControladorPresentacion.getUser(), (int) time);
										MyEntry m= new MyEntry(ControladorPresentacion.getUser(), (int) time);
										try {
											cp.actualizarRecords(16, cp.getId(), cp.getDificultad(), m);
										} catch (ClassNotFoundException | IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										JTime.stop();
										String[][]s=cp.getRecordTablero();
										//Llamada al top5
										JOptionPane op= new JOptionPane();
										op.showMessageDialog(null, "Felicidades!! Has terminado el Sudoku","Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
										setVisible(false);
										Double x2 = getBounds().getX();
										Double y2 = getBounds().getY();
										Double w = getBounds().getWidth();
										Double h = getBounds().getHeight();
										ControladorPresentacion.VisibleTop5(x2.intValue(),y2.intValue(),w.intValue(),h.intValue(), s, time);
									}

								}
							}

							else{
								//System.out.println("casilla: "+x+ " " + y);
								celdas.get(x*16+y).setText("");
								cp.setNum(x, y, 0); //System.out.println("he puesto un 0"); 
							}
						}

						private boolean valido(String text) {
							for (Integer i = 0; i <= 16; i++) {
								if(text.equals(i.toString())) return true;
							}
							return false;
						}
					});
			}
		}
	}
	menuGuardar.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane jo= new JOptionPane("Guardar", 1);
			//String r= jo.showInputDialog(null,"Introduce el nombre de la partida");
			//String r = JOptionPane.showInputDialog(null, "Introduce el nombre de la partida", "");
			String r = (String) JOptionPane.showInputDialog(null, "Introduce el nombre de la partida", "Guardar", JOptionPane.DEFAULT_OPTION, null, null, null);
			if(r!=null)
				try {
					cp.setTiempoPartida(time);
					cp.guardarPartida(r);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	});
	menuAyuda.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			quedan=0;
			Integer[][] matP= new Integer[16][16];
			matP=cp.getMatrizValores();
			for (int i = 0; i < 16; i++) {
				for (int j2 = 0; j2 < 16; j2++) {
					if(matP[i][j2]==0){
						++quedan;
					}
				}
			}
			//System.out.println(quedan);
			if(quedan>1){
				if(!cp.ayuda()) {
					JOptionPane op= new JOptionPane();
					op.showMessageDialog(null, "Has cometido algún error","Ayuda", JOptionPane.ERROR_MESSAGE);
					//cp.s
				}
				else{
					Integer[][] matA= new Integer[16][16];
					matA= cp.getMatrizValores();
					for (int i = 0; i < matA.length; i++) {
						for (int j = 0; j < matA.length; j++) {
							if(matA[i][j]!=0 && celdas.get(i*16+j).getText().equals("")){
								celdas.get(i*16+j).setText(matA[i][j].toString());
								celdas.get(i*16+j).setFont(new Font("Verdana", 0, 12));
								celdas.get(i*16+j).setForeground(Color.RED);
								celdas.get(i*16+j).setEditable(false);
								//hay que ponerla por defecto
							}
						}
					}
				}

			}
			else {
				JOptionPane op= new JOptionPane();
				op.showMessageDialog(null, "Mételo tu borja","Ayuda", JOptionPane.WARNING_MESSAGE);
			}
		}
	});
	if(cargar) time=cp.getTiempoPartida();
	JTime.start();
}
}
