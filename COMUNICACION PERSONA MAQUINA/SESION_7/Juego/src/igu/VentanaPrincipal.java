package igu;

/**
 * 
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import java.awt.GridLayout;
import javax.swing.border.LineBorder;

import logica.Juego;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class VentanaPrincipal extends JFrame {

	private EventoCasilla ec = new EventoCasilla();
	private ProcesaDrag pd = new ProcesaDrag();
	private JPanel contentPane;
	private JButton btDado;
	private JLabel lblNaveEspacial;
	private JLabel lblEarth;
	private JLabel lblPuntos;
	private JTextField txtPuntos;
	private JPanel pnTablero;
	private JButton bt0;
	private JButton bt1;
	private JButton bt2;
	private JButton bt3;
	private JButton bt4;
	private JButton bt5;
	private JButton bt6;
	private JButton bt7;
	private JMenuBar menuBar;
	private JMenu mnJuego;
	private JMenuItem mntmNuevo;
	private JPanel pnDisparos;
	private Juego juego;
	private JMenu mnAyuda;
	private JMenuItem mntmAbout;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	private JMenu mnOpciones;
	private JMenuItem mntmNDisparos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class EventoCasilla implements PropertyChangeListener {

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			if(e.getPropertyName().contentEquals("foreground") &&  (e.getNewValue().equals(Color.BLUE))) {
			((JButton)e.getSource()).setEnabled(false);
			dispara(Integer.parseInt(((JButton)e.getSource()).getActionCommand()));
			}
		}
		
	}
	
	//PASO 1 DRAG AND DROP
	public class ProcesaDrag extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
			
		}
	}
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		juego = new Juego();
		setResizable(false);
		setTitle("Invasi\u00F3n Espacial");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/invader.jpg")));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 419);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtDado());
		contentPane.add(getLblNaveEspacial());
		contentPane.add(getLblEarth());
		contentPane.add(getLblPuntos());
		contentPane.add(getTxtPuntos());
		contentPane.add(getPnTablero());
		contentPane.add(getPnDisparos());
		
	}
	
	private void habilitaTablero(boolean estado) {
		for(int i = 0; i <getPnTablero().getComponents().length; i++) {
			(getPnTablero().getComponents()[i]).setEnabled(estado);
		}
	}
	
	private void iniciarJuego() {
		juego.lanzar();
		pintaDisparos();
		getBtDado().setEnabled(false);
		habilitaTablero(true);
	}
	
	private void pintaDisparos() {
		for(int i = 0; i<juego.getDisparos(); i++) {
			getPnDisparos().add(nuevoDisparo());
		}
		validate();
	}

	private JLabel nuevoDisparo() {
		JLabel lbDisparo = new JLabel("");
		lbDisparo.setBorder(new LineBorder(Color.GREEN, 1));
		lbDisparo.setIcon(ImagenFactoria.getImagen());
		lbDisparo.setForeground(Color.BLUE);
		//PASO 3 DRAG AND DROP
		lbDisparo.addMouseListener(pd);
		//PASO 4 DRAG AND DROP
		lbDisparo.setTransferHandler(new TransferHandler("foreground"));
		return lbDisparo;
	}
	
	private void dispara(int i) {
		juego.dispara(i);
		representaJuego(i);
	}
	
	private void representaJuego(int i) {
		getTxtPuntos().setText(String.valueOf(juego.getPuntos()));
		despintaDisparo();
		pintaCasilla(i);
		if(juego.isPartidaFinalizada()) {
			pintarTablero();
			habilitaTablero(false);
			getPnDisparos().removeAll();
			if(juego.getInvasorEncontrado()) {
				JOptionPane.showMessageDialog(null,"Has ganado, has encontrado el alien");
			}else if(juego.getMeteoritoEncontrado()) {
				JOptionPane.showMessageDialog(null,"Has perdido, te estrellaste contra un meteorito");
			}else {
				JOptionPane.showMessageDialog(null,"Has perdido, no has encontrado el alien");
			}
		}
		
	}
	
	private void inicializar() {
		juego = new Juego();
		juego.inicializarJuego();
		despintaTablero();
		getPnDisparos().removeAll();
		getPnDisparos().repaint();
		getTxtPuntos().setText("" + juego.getPuntos());
		getBtDado().setEnabled(true);
		habilitaTablero(false);
		
	}

	private void despintaTablero() {
		for(int i = 0; i<juego.getTablero().getCasillas().length; i++) {
			despintaCasilla(i);
		}
		
	}
	
	private void pintarTablero() {
		for(int i = 0; i<juego.getTablero().getCasillas().length; i++) {
			pintaCasilla(i);
		}
	}
	private void despintaDisparo() {
		getPnDisparos().remove(0);
		getPnDisparos().repaint();
		validate();
	}
	
	private void despintaCasilla(int i) {
		ImageIcon imagen = 
				ImagenFactoria.getImagen(juego.getTablero().getCasillas()[i]);
		((JButton)pnTablero.getComponent(i)).setIcon(null);
		((JButton)pnTablero.getComponent(i)).setDisabledIcon(null);
		((JButton)pnTablero.getComponent(i)).setForeground(Color.BLACK);
	}
	
	private void pintaCasilla(int i) {
		ImageIcon imagen = 
				ImagenFactoria.getImagen(juego.getTablero().getCasillas()[i]);
		((JButton)pnTablero.getComponent(i)).setIcon(imagen);
		((JButton)pnTablero.getComponent(i)).setDisabledIcon(imagen);
	}

	private JButton getBtDado() {
		if (btDado == null) {
			btDado = new JButton("");
			btDado.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					iniciarJuego();
				}
			});
			btDado.setBorderPainted(false);
			btDado.setToolTipText("Pulsa para obtener disparos");
			btDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btDado.setBounds(12, 13, 114, 109);
		}
		return btDado;
	}
	private JLabel getLblNaveEspacial() {
		if (lblNaveEspacial == null) {
			lblNaveEspacial = new JLabel("");
			lblNaveEspacial.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/spaceship1.png")));
			lblNaveEspacial.setBounds(276, 1, 163, 155);
		}
		return lblNaveEspacial;
	}
	private JLabel getLblEarth() {
		if (lblEarth == null) {
			lblEarth = new JLabel("Earth");
			lblEarth.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/earth.jpg")));
			lblEarth.setBounds(649, 1, 198, 195);
		}
		return lblEarth;
	}
	private JLabel getLblPuntos() {
		if (lblPuntos == null) {
			lblPuntos = new JLabel("Puntos");
			lblPuntos.setFont(new Font("Arial", Font.BOLD, 18));
			lblPuntos.setForeground(Color.WHITE);
			lblPuntos.setBackground(Color.BLACK);
			lblPuntos.setBounds(535, 13, 67, 34);
		}
		return lblPuntos;
	}
	private JTextField getTxtPuntos() {
		if (txtPuntos == null) {
			txtPuntos = new JTextField();
			txtPuntos.setEditable(false);
			txtPuntos.setBackground(Color.BLACK);
			txtPuntos.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			txtPuntos.setForeground(Color.GREEN);
			txtPuntos.setText(String.valueOf(juego.getPuntos()));
			txtPuntos.setBounds(535, 43, 90, 34);
			txtPuntos.setColumns(10);
		}
		return txtPuntos;
	}
	private JPanel getPnTablero() {
		if (pnTablero == null) {
			pnTablero = new JPanel();
			pnTablero.setBackground(new Color(0, 0, 255));
			pnTablero.setBorder(new LineBorder(new Color(0, 0, 255), 5));
			pnTablero.setBounds(40, 245, 825, 100);
			pnTablero.setLayout(new GridLayout(1, 8, 4, 0));
			pnTablero.add(getBt0());
			pnTablero.add(getBt1());
			pnTablero.add(getBt2());
			pnTablero.add(getBt3());
			pnTablero.add(getBt4());
			pnTablero.add(getBt5());
			pnTablero.add(getBt6());
			pnTablero.add(getBt7());
			habilitaTablero(false);
		}
		return pnTablero;
	}
	private JButton getBt0() {
		if (bt0 == null) {
			bt0 = new JButton("");
			bt0.setActionCommand("0");
			bt0.addPropertyChangeListener(ec);
			bt0.setTransferHandler(new TransferHandler("foreground"));
			bt0.setBackground(Color.BLACK);
		}
		return bt0;
	}
	private JButton getBt1() {
		if (bt1 == null) {
			bt1 = new JButton("");
			bt1.setBackground(Color.BLACK);
			bt1.setActionCommand("1");
			bt1.setTransferHandler(new TransferHandler("foreground"));
			bt1.addPropertyChangeListener(ec);
		}
		return bt1;
	}
	private JButton getBt2() {
		if (bt2 == null) {
			bt2 = new JButton("");
			bt2.setBackground(Color.BLACK);
			bt2.setActionCommand("2");
			bt2.setTransferHandler(new TransferHandler("foreground"));
			bt2.addPropertyChangeListener(ec);
		}
		return bt2;
	}
	private JButton getBt3() {
		if (bt3 == null) {
			bt3 = new JButton("");
			bt3.setBackground(Color.BLACK);
			bt3.setActionCommand("3");
			bt3.setTransferHandler(new TransferHandler("foreground"));
			bt3.addPropertyChangeListener(ec);
		}
		return bt3;
	}
	private JButton getBt4() {
		if (bt4 == null) {
			bt4 = new JButton("");
			bt4.setBackground(Color.BLACK);
			bt4.setActionCommand("4");
			bt4.setTransferHandler(new TransferHandler("foreground"));
			bt4.addPropertyChangeListener(ec);
		}
		return bt4;
	}
	private JButton getBt5() {
		if (bt5 == null) {
			bt5 = new JButton("");
			bt5.setBackground(Color.BLACK);
			bt5.setActionCommand("5");
			bt5.setTransferHandler(new TransferHandler("foreground"));
			bt5.addPropertyChangeListener(ec);
		}
		return bt5;
	}
	private JButton getBt6() {
		if (bt6 == null) {
			bt6 = new JButton("");
			bt6.setBackground(Color.BLACK);
			bt6.setActionCommand("6");
			bt6.setTransferHandler(new TransferHandler("foreground"));
			bt6.addPropertyChangeListener(ec);
		}
		return bt6;
	}
	private JButton getBt7() {
		if (bt7 == null) {
			bt7 = new JButton("");
			bt7.setBackground(Color.BLACK);
			bt7.setActionCommand("7");
			bt7.setTransferHandler(new TransferHandler("foreground"));
			bt7.addPropertyChangeListener(ec);
		}
		return bt7;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnJuego());
			menuBar.add(getMnOpciones());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnJuego() {
		if (mnJuego == null) {
			mnJuego = new JMenu("Juego");
			mnJuego.setMnemonic('J');
			mnJuego.add(getMntmNuevo());
			mnJuego.add(getSeparator());
			mnJuego.add(getMntmSalir());
		}
		return mnJuego;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					inicializar();
				}
			});
			
		}
		return mntmNuevo;
	}
	private JPanel getPnDisparos() {
		if (pnDisparos == null) {
			pnDisparos = new JPanel();
			pnDisparos.setBackground(Color.BLACK);
			pnDisparos.setBounds(176, 154, 345, 78);
		}
		return pnDisparos;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.add(getMntmAbout());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null,"Aplicacion Invasion Espacial\nPracticas CPM 2019-2020\nEII Oviedo","Acerca de Invasion Espacial",JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAbout;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
		}
		return mntmSalir;
	}
	private JMenu getMnOpciones() {
		if (mnOpciones == null) {
			mnOpciones = new JMenu("Opciones");
			mnOpciones.add(getMntmNDisparos());
		}
		return mnOpciones;
	}
	private JMenuItem getMntmNDisparos() {
		if (mntmNDisparos == null) {
			mntmNDisparos = new JMenuItem("N\u00BA Disparos");
			mntmNDisparos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						int disparos = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca el numero de disparos: ","4"));
						if(disparos<2 || disparos>6) {
							Juego.maxDisparos = 4;
							JOptionPane.showMessageDialog(null, "El numero de disparos debe estar entre 2 y 6", "Error - Nº Disparos", JOptionPane.ERROR_MESSAGE);
						}
						else {
							Juego.maxDisparos = disparos;
						}
					Juego.maxDisparos = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca el numero de disparos: ","4"));
					} catch (NumberFormatException nfe) {
						Juego.maxDisparos = 4;
					}
				}
			});
			
		}
		return mntmNDisparos;
	}
}
