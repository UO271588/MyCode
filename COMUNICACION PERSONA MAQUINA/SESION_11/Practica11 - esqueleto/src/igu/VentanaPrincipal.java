package igu;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import logica.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.LineBorder;
import java.awt.CardLayout;
import javax.swing.border.TitledBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Dimension;

public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblLblnombre;
	private Carta carta;
	private Pedido pedido;
	private JPanel pnInfo1;
	private JPanel pnlLogo;
	private JPanel pnArticulos;
	private AccionBoton aB;
	private JPanel pnBts1;
	private JPanel pnBts2;
	private JPanel pnBts3;
	private JPanel pnContenidos;
	private JPanel pn2;
	private JPanel pn3;
	private JPanel pnDatosCliente;
	private JLabel lbNombre;
	private JTextField txtNombre;
	private JLabel lbA�o;
	private JComboBox<String> cbA�os;
	private JLabel lbPasw1;
	private JPasswordField psw1;
	private JLabel lbPasw2;
	private JPasswordField psw2;
	private JPanel pn1;
	private JPanel pnFormulario;
	private JPanel pnDatosPedido;
	private JRadioButton rbLocal;
	private JRadioButton rbLlevar;
	private JPanel pnInfo2;
	private JPanel pnConfirmacion;
	private JPanel pnInfo3;
	private JLabel lbAviso;
	private JLabel lbOk;
	private JLabel lbCodigo;
	private JTextField txCodigo;
	private final ButtonGroup grPedido = new ButtonGroup();
	private JTextField txtPrecio;
	private JButton btnAnular1;
	private JButton btnSiguiente1;
	private JTabbedPane pnPedido;
	private JScrollPane scrComida;
	private JScrollPane scrBebida;
	private JList <Articulo>listComida;
	private JList <Articulo>listBebida;
	private DefaultListModel<Articulo> modeloListComida;
	private DefaultListModel<Articulo> modeloListBebida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			    	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setMinimumSize(new Dimension(590, 494));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				asociaImagenBotones();
			}
		});
		carta = new Carta();
		pedido = new Pedido();
		aB = new AccionBoton();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's Espa�a");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 820);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlLogo(), BorderLayout.NORTH);
		contentPane.add(getPnContenidos(), BorderLayout.CENTER);
	}
	
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		}
		return lblLogo;
	}
	private JLabel getLblLblnombre() {
		if (lblLblnombre == null) {
			lblLblnombre = new JLabel("McDonald's");
			lblLblnombre.setFont(new Font("Arial Black", Font.PLAIN, 44));
			lblLblnombre.setForeground(Color.BLACK);
		}
		return lblLblnombre;
	}

	
	protected void inicializar() {
		pedido.inicializar();
		//COMPLETAR
	}
	
	private JPanel getPnInfo1() {
		if (pnInfo1 == null) {
			pnInfo1 = new JPanel();
			pnInfo1.setBackground(Color.WHITE);
			pnInfo1.setLayout(new BorderLayout(0, 0));
			pnInfo1.add(getPnPedido(), BorderLayout.CENTER);
			pnInfo1.add(getPnBts1(),BorderLayout.SOUTH);
		
		}
		return pnInfo1;
	}
	private JPanel getPnlLogo() {
		if (pnlLogo == null) {
			pnlLogo = new JPanel();
			pnlLogo.setBackground(Color.WHITE);
			pnlLogo.setLayout(new GridLayout(1, 0, 0, 0));
			pnlLogo.add(getLblLogo());
			pnlLogo.add(getLblLblnombre());
		}
		return pnlLogo;
	}
	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setBorder(new LineBorder(Color.ORANGE, 4));
			pnArticulos.setBackground(Color.WHITE);
			// COMPLETAR 1
			pnArticulos.setLayout(new GridLayout(carta.getListaArticulos().size()/5,5,3,3));
			creaBotonesTablero();
	
		}
		return pnArticulos;
	}
	
	private void creaBotonesTablero() {
		pnArticulos.removeAll();
		for(int i = 0; i < carta.getListaArticulos().size();i++) {
			pnArticulos.add(nuevoBoton(i));
		}
		
	}

	private void setImagenAdaptada(JButton boton, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(boton.getWidth(),boton.getHeight(), Image.SCALE_SMOOTH);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 boton.setIcon(icon);
	}
	

	private void asociaImagenBotones() {
		for (int i = 0; i < pnArticulos.getComponents().length; i++)
		{
			JButton boton = (JButton) (pnArticulos.getComponents()[i]);
			setImagenAdaptada(boton,carta.getListaArticulos().get(i).getRutaFoto());
		}
	}

	private JButton nuevoBoton(Integer posicion) {
		JButton boton = new JButton("");
		boton.setBackground(Color.white);
		boton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		boton.setToolTipText(carta.getListaArticulos().get(posicion).toString());
		boton.setActionCommand(posicion.toString());
		boton.setIcon(new ImageIcon(VentanaPrincipal.class.getResource(carta.getListaArticulos().get(posicion).getRutaFoto())));
		boton.addActionListener(aB);
		
		return boton;
	}

	class AccionBoton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton) e.getSource();
			a�adirAPedido(Integer.parseInt(bt.getActionCommand()));
		}
	}
	
	//COMPLETAR 2
	private void a�adirAPedido(int posArticuloEnCarta) {
		Articulo a = carta.getListaArticulos().get(posArticuloEnCarta);
		pedido.add(a, 1);
		mostrarEnLista(a);
		getTxtPrecio().setText("Precio: "+String.format("%.2f",pedido.calcularTotalSinIva()));
		if(!getBtnSiguiente1().isEnabled()) {
			getBtnSiguiente1().setEnabled(true);
		}
		
	}
	
	//COMPLETAR 3
	private void mostrarEnLista(Articulo a) {
		if(a.getTipo().equals("Bebida")) {
			modeloListBebida.addElement(a);
		}else {
			modeloListComida.addElement(a);
		}
	}
	
	private JPanel getPnBts1() {
		if (pnBts1 == null) {
			pnBts1 = new JPanel();
			pnBts1.setBackground(Color.WHITE);
			pnBts1.setLayout(new GridLayout(1, 3, 0, 0));
			pnBts1.add(getTxtPrecio());
			pnBts1.add(getBtnAnular1());
			pnBts1.add(getBtnSiguiente1());
		}
		return pnBts1;
	}
	
	private void anularPedido() {
		modeloListComida.removeAllElements();
		modeloListBebida.removeAllElements();
		getTxtPrecio().setText("Precio. 0,00");
		pedido.inicializar();
	}
	
	private JPanel getPnBts2() {
		if (pnBts2 == null) {
			pnBts2 = new JPanel();
			pnBts2.setBackground(Color.WHITE);
			pnBts2.setLayout(new GridLayout(1, 3, 0, 0));
		}
		return pnBts2;
	}
	
	private JPanel getPnBts3() {
		if (pnBts3 == null) {
			pnBts3 = new JPanel();
			pnBts3.setBackground(Color.WHITE);
			pnBts3.setLayout(new GridLayout(1, 3, 0, 0));
		}
		return pnBts3;
	}

	private JPanel getPnContenidos() {
		if (pnContenidos == null) {
			pnContenidos = new JPanel();
			pnContenidos.setLayout(new CardLayout(0, 0));
			pnContenidos.add(getPn1(), "pn1");
			pnContenidos.add(getPn2(), "pn2");
			pnContenidos.add(getPn3(), "pn3");
		}
		return pnContenidos;
	}
	
	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
			pn2.setBackground(Color.WHITE);
			pn2.setLayout(new BorderLayout(0, 0));
			pn2.add(getPnFormulario(), BorderLayout.CENTER);
			pn2.add(getPnInfo2(), BorderLayout.SOUTH);
		}
		return pn2;
	}
	
	private JPanel getPn3() {
		if (pn3 == null) {
			pn3 = new JPanel();
			pn3.setBackground(Color.WHITE);
			pn3.setLayout(new BorderLayout(0, 0));
			pn3.add(getPnConfirmacion());
			pn3.add(getPnInfo3(), BorderLayout.SOUTH);
		}
		return pn3;
	}
	
	private JComboBox<String> getCbA�os() {
		if (cbA�os == null) {
			String[]a�os = new String[90];
			for (int i=0;i<90;i++){
				String a�o = ""+((90-i)+1920);
				a�os[i]= a�o;
			}
			cbA�os = new JComboBox<String>();
			cbA�os.setFont(new Font("Arial", Font.PLAIN, 14));
			cbA�os.setModel(new DefaultComboBoxModel<String>(a�os));
			cbA�os.setBounds(new Rectangle(193, 77, 157, 25));
		}
		return cbA�os;
	}
	
	private boolean isVacio() {
		return (txtNombre.getText().equals("")||(String.valueOf(psw1.getPassword()).equals(""))||(String.valueOf(psw2.getPassword()).equals(""))); 
	
	}
	
	private boolean isIncorrecta() {
		return (!String.valueOf(psw1.getPassword()).equals(String.valueOf(psw2.getPassword())));
	}
	
	private JPanel getPnDatosCliente() {
		if (pnDatosCliente == null) {
			pnDatosCliente = new JPanel();
			pnDatosCliente.setBounds(104, 58, 558, 224);
			pnDatosCliente.setBorder(new TitledBorder(null, "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnDatosCliente.setBackground(Color.WHITE);
			pnDatosCliente.setLayout(null);
			pnDatosCliente.add(getLbNombre());
			pnDatosCliente.add(getTxtNombre());
			pnDatosCliente.add(getLbA�o());
			pnDatosCliente.add(getCbA�os());
			pnDatosCliente.add(getLbPasw1());
			pnDatosCliente.add(getPsw1());
			pnDatosCliente.add(getLbPasw2());
			pnDatosCliente.add(getPsw2());
		}
		return pnDatosCliente;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel();
			lbNombre.setText("Nombre y Apellidos:");
			lbNombre.setFont(new Font("Arial", Font.PLAIN, 14));
			lbNombre.setDisplayedMnemonic('N');
			lbNombre.setBounds(30, 31, 132, 20);
		}
		return lbNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
			txtNombre.setBounds(193, 24, 330, 25);
		}
		return txtNombre;
	}
	private JLabel getLbA�o() {
		if (lbA�o == null) {
			lbA�o = new JLabel("A\u00F1o de nacimiento:");
			lbA�o.setFont(new Font("Arial", Font.PLAIN, 14));
			lbA�o.setDisplayedMnemonic('A');
			lbA�o.setBounds(30, 81, 121, 16);
		}
		return lbA�o;
	}

	private JLabel getLbPasw1() {
		if (lbPasw1 == null) {
			lbPasw1 = new JLabel();
			lbPasw1.setText("Password:");
			lbPasw1.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPasw1.setDisplayedMnemonic('P');
			lbPasw1.setBounds(new Rectangle(13, 123, 105, 16));
			lbPasw1.setBounds(30, 129, 105, 16);
		}
		return lbPasw1;
	}
	private JPasswordField getPsw1() {
		if (psw1 == null) {
			psw1 = new JPasswordField();
			psw1.setFont(new Font("Arial", Font.PLAIN, 14));
			psw1.setBounds(new Rectangle(176, 121, 218, 25));
			psw1.setBounds(193, 122, 218, 25);
		}
		return psw1;
	}
	private JLabel getLbPasw2() {
		if (lbPasw2 == null) {
			lbPasw2 = new JLabel();
			lbPasw2.setText("Reintroduzca password:");
			lbPasw2.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPasw2.setDisplayedMnemonic('R');
			lbPasw2.setBounds(new Rectangle(13, 167, 151, 16));
			lbPasw2.setBounds(30, 181, 151, 16);
		}
		return lbPasw2;
	}
	private JPasswordField getPsw2() {
		if (psw2 == null) {
			psw2 = new JPasswordField();
			psw2.setFont(new Font("Arial", Font.PLAIN, 14));
			psw2.setBounds(new Rectangle(176, 163, 218, 25));
			psw2.setBounds(193, 172, 218, 25);
		}
		return psw2;
	}
	
	public boolean comprobarCampos() {
		if (isVacio()) {
			JOptionPane.showMessageDialog(null, "Error: Hay alg�n campo en blanco");
			return false;
		}
		else
			if (isIncorrecta()) {
				JOptionPane.showMessageDialog(null, "Error: Las passwords no coinciden");
				return false;
			}
		return true;
	 }

	private void mostrarPn3() {
		if (comprobarCampos())
		{   
			// COMPLETAR
		}
	}
	
	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setLayout(new BorderLayout(0, 0));
			pn1.add(getPnArticulos(), BorderLayout.CENTER);
			pn1.add(getPnInfo1(), BorderLayout.SOUTH);
		}
		return pn1;
	}
	
	private JPanel getPnFormulario() {
		if (pnFormulario == null) {
			pnFormulario = new JPanel();
			pnFormulario.setBorder(new LineBorder(Color.ORANGE, 4));
			pnFormulario.setBackground(Color.WHITE);
			pnFormulario.setLayout(null);
			pnFormulario.add(getPnDatosCliente());
			pnFormulario.add(getPnDatosPedido());
		}
		return pnFormulario;
	}
	private JPanel getPnDatosPedido() {
		if (pnDatosPedido == null) {
			pnDatosPedido = new JPanel();
			pnDatosPedido.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del pedido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnDatosPedido.setBackground(Color.WHITE);
			pnDatosPedido.setBounds(112, 304, 204, 60);
			pnDatosPedido.add(getRbLocal());
			pnDatosPedido.add(getRbLlevar());
		}
		return pnDatosPedido;
	}
	private JRadioButton getRbLocal() {
		if (rbLocal == null) {
			rbLocal = new JRadioButton();
			grPedido.add(rbLocal);
			rbLocal.setText("Local");
			rbLocal.setSelected(true);
			rbLocal.setMnemonic('L');
			rbLocal.setFont(new Font("Arial", Font.PLAIN, 14));
			rbLocal.setBounds(new Rectangle(17, 27, 94, 24));
			rbLocal.setBackground(Color.WHITE);
		}
		return rbLocal;
	}
	private JRadioButton getRbLlevar() {
		if (rbLlevar == null) {
			rbLlevar = new JRadioButton();
			grPedido.add(rbLlevar);
			rbLlevar.setText("Llevar");
			rbLlevar.setMnemonic('r');
			rbLlevar.setFont(new Font("Arial", Font.PLAIN, 14));
			rbLlevar.setBounds(new Rectangle(115, 27, 86, 24));
			rbLlevar.setBackground(Color.WHITE);
		}
		return rbLlevar;
	}
	private JPanel getPnInfo2() {
		if (pnInfo2 == null) {
			pnInfo2 = new JPanel();
			pnInfo2.setBackground(Color.WHITE);
			pnInfo2.setLayout(new BorderLayout(0, 0));
			pnInfo2.add(getPnBts2(),BorderLayout.SOUTH);
		}
		return pnInfo2;
	}
	private JPanel getPnConfirmacion() {
		if (pnConfirmacion == null) {
			pnConfirmacion = new JPanel();
			pnConfirmacion.setBorder(new LineBorder(Color.ORANGE, 4));
			pnConfirmacion.setBackground(Color.WHITE);
			pnConfirmacion.setLayout(null);
			pnConfirmacion.add(getLbAviso());
			pnConfirmacion.add(getLbOk());
			pnConfirmacion.add(getLbCodigo());
			pnConfirmacion.add(getTxCodigo());
		}
		return pnConfirmacion;
	}
	private JPanel getPnInfo3() {
		if (pnInfo3 == null) {
			pnInfo3 = new JPanel();
			pnInfo3.setBackground(Color.WHITE);
			pnInfo3.setLayout(new BorderLayout(0, 0));
			pnInfo3.add(getPnBts3(),BorderLayout.SOUTH);
		}
		return pnInfo3;
	}
	
	private void finalizar() {
		pedido.grabarPedido(getTxCodigo().getText());
		inicializar();
		//COMPLETAR
	}
	
	private JLabel getLbAviso() {
		if (lbAviso == null) {
			lbAviso = new JLabel("Estamos procesando su pedido");
			lbAviso.setFont(new Font("Tahoma", Font.BOLD, 28));
			lbAviso.setBounds(135, 104, 478, 35);
		}
		return lbAviso;
	}
	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel("");
			lbOk.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ok.png")));
			lbOk.setBounds(50, 91, 73, 52);
		}
		return lbOk;
	}
	private JLabel getLbCodigo() {
		if (lbCodigo == null) {
			lbCodigo = new JLabel("El c\u00F3digo de recogida es:");
			lbCodigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbCodigo.setBounds(138, 172, 191, 26);
		}
		return lbCodigo;
	}
	private JTextField getTxCodigo() {
		if (txCodigo == null) {
			txCodigo = new JTextField();
			txCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txCodigo.setEditable(false);
			txCodigo.setText(FileUtil.setFileName());
			txCodigo.setBounds(341, 161, 191, 45);
			txCodigo.setColumns(10);
		}
		return txCodigo;
	}
	private JTextField getTxtPrecio() {
		if (txtPrecio == null) {
			txtPrecio = new JTextField();
			txtPrecio.setFocusable(false);
			txtPrecio.setEditable(false);
			txtPrecio.setForeground(Color.WHITE);
			txtPrecio.setFont(new Font("Arial Black", Font.BOLD, 16));
			txtPrecio.setBackground(Color.ORANGE);
			txtPrecio.setText("Precio: 0,00");
			txtPrecio.setColumns(10);
		}
		return txtPrecio;
	}
	private JButton getBtnAnular1() {
		if (btnAnular1 == null) {
			btnAnular1 = new JButton("Anular");
			btnAnular1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					anularPedido();
				}
			});
			
			btnAnular1.setMnemonic('A');
			btnAnular1.setForeground(Color.WHITE);
			btnAnular1.setFont(new Font("Arial Black", Font.BOLD, 16));
			btnAnular1.setBackground(Color.RED);
		}
		return btnAnular1;
	}
	private JButton getBtnSiguiente1() {
		if (btnSiguiente1 == null) {
			btnSiguiente1 = new JButton("Siguiente");
			btnSiguiente1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout)getPnContenidos().getLayout()).next(getPnContenidos());
				}
			});
			btnSiguiente1.setEnabled(false);
			btnSiguiente1.setMnemonic('S');
			btnSiguiente1.setForeground(Color.WHITE);
			btnSiguiente1.setFont(new Font("Arial Black", Font.BOLD, 16));
			btnSiguiente1.setBackground(Color.GREEN);
		}
		return btnSiguiente1;
	}
	private JTabbedPane getPnPedido() {
		if (pnPedido == null) {
			pnPedido = new JTabbedPane(JTabbedPane.TOP);
			pnPedido.addTab("Comida", null, getScrComida(), null);
			pnPedido.setDisplayedMnemonicIndexAt(0, 0);
			pnPedido.addTab("Bebida", null, getScrBebida(), null);
			pnPedido.setDisplayedMnemonicIndexAt(1, 0);
		}
		return pnPedido;
	}
	private JScrollPane getScrComida() {
		if (scrComida == null) {
			scrComida = new JScrollPane();
			scrComida.setViewportView(getListComida());
		}
		return scrComida;
	}
	private JScrollPane getScrBebida() {
		if (scrBebida == null) {
			scrBebida = new JScrollPane();
			scrBebida.setViewportView(getListBebida());
		}
		return scrBebida;
	}
	private JList<Articulo> getListComida() {
		if (listComida == null) {
			listComida = new JList();
			modeloListComida = new DefaultListModel<Articulo>();
			listComida.setModel(modeloListComida);
		}
		return listComida;
	}
	private JList<Articulo> getListBebida() {
		if (listBebida == null) {
			listBebida = new JList();
			modeloListBebida = new DefaultListModel<Articulo>();
			listBebida.setModel(modeloListBebida);
		}
		return listBebida;
	}
}
