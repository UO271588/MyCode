package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Articulo;
import logic.Carta;
import logic.Pedido;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.awt.Insets;
/**
 * 
 * @author Eloy Alfredo Schmidt Rguez UO271588
 * @version 1.0.0
 *
 */

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblLogoTitle;
	private JLabel lblArticulos;
	private JLabel lblUnits;
	private JSpinner spinnerUnits;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnNext;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private Carta carta;
	private Pedido pedido;
	private JLabel lblDiscount;
	private JLabel lblArticleUnits;
	private JScrollPane spPedido;
	private JTextArea txtrPedido;
	private JLabel lblPedidoImg;
	private JButton btnDelete;
	private JComboBox<Articulo> comboBox;
	private JLabel lblDiscountIcon;
	private JTextPane txtpnDiscount;
	private JLabel lblImgProducto;
	private JMenuBar menuBar;
	private JMenu mnPedido;
	private JMenuItem mntmNuevo;
	private JMenuItem mntmSalir;
	private JSeparator separator;
	private JMenu mnAyuda;
	private JMenuItem mntmContenidos;
	private JMenuItem mntmAcercaDe;
	private JSeparator separator_1;
	private JPanel panelFiltro;
	private JButton btnHamburgesas;
	private JButton btnBebidas;
	private JButton btnComplementos;
	private JButton btnPostres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
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
		setResizable(false);

		carta = new Carta();
		pedido = new Pedido();

		setTitle("McDonald's - Espa\u00F1a");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 514);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanelFiltro());
		contentPane.add(getLblLogo());
		contentPane.add(getLblLogoTitle());
		contentPane.add(getLblArticulos());
		contentPane.add(getLblUnits());
		contentPane.add(getComboBox());
		contentPane.add(getLblArticleUnits());
		contentPane.add(getSpinnerUnits());
		contentPane.add(getBtnAdd());
		contentPane.add(getBtnCancel());
		contentPane.add(getBtnNext());
		contentPane.add(getLblPrice());
		contentPane.add(getTxtPrice());
		contentPane.add(getLblDiscount());
		contentPane.add(getSpPedido());
		contentPane.add(getLblPedidoImg());
		contentPane.add(getBtnDelete());
		contentPane.add(getLblDiscountIcon());
		contentPane.add(getTxtpnDiscount());
		contentPane.add(getLblImgProducto());
		
		

	}

	public void inicializar() {
		pedido.inicializar();
		getTxtPrice().setText("");
		getComboBox().setSelectedIndex(0);
		getSpinnerUnits().setValue(1);
		getBtnNext().setEnabled(false);
		getLblDiscount().setVisible(false);
		getLblArticleUnits().setText("Nº de unds: " + pedido.getUnidades((Articulo) getComboBox().getSelectedItem()));
		getTxtrPedido().setText("Pedido:");
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
			lblLogo.setBounds(127, 11, 219, 166);
		}
		return lblLogo;
	}

	private JLabel getLblLogoTitle() {
		if (lblLogoTitle == null) {
			lblLogoTitle = new JLabel("McDonald's");
			lblLogoTitle.setFont(new Font("Arial Black", Font.BOLD, 36));
			lblLogoTitle.setBounds(330, 58, 280, 87);
		}
		return lblLogoTitle;
	}

	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Articulos:");
			lblArticulos.setLabelFor(getComboBox());
			lblArticulos.setDisplayedMnemonic('r');
			lblArticulos.setBounds(155, 188, 87, 16);
		}
		return lblArticulos;
	}

	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel("Unidades:");
			lblUnits.setDisplayedMnemonic('U');
			lblUnits.setLabelFor(getSpinnerUnits());
			lblUnits.setBounds(551, 188, 132, 16);
		}
		return lblUnits;
	}

	private JSpinner getSpinnerUnits() {
		if (spinnerUnits == null) {
			spinnerUnits = new JSpinner();
			spinnerUnits.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spinnerUnits.setBounds(551, 215, 52, 22);
		}
		return spinnerUnits;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("A\u00F1adir");
			btnAdd.setMnemonic('A');
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Articulo articulo = (Articulo) getComboBox().getSelectedItem();
					pedido.add(articulo, (int) getSpinnerUnits().getValue());
					getTxtPrice().setText(String.format("%.2f €", pedido.calcularTotal()));
					getLblArticleUnits()
							.setText("Nº de unds: " + pedido.getUnidades((Articulo) getComboBox().getSelectedItem()));
					getTxtrPedido().setText(pedido.toString());
					if (pedido.getDescuento()) {
						getLblDiscount().setVisible(true);
					}
					if (!getBtnNext().isEnabled()) {
						getBtnNext().setEnabled(true);
					}
				}
			});
			btnAdd.setBackground(Color.GREEN);
			btnAdd.setBounds(613, 215, 97, 25);
		}
		return btnAdd;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancelar");
			btnCancel.setMnemonic('C');
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			btnCancel.setBackground(Color.RED);
			btnCancel.setBounds(720, 412, 97, 25);
		}
		return btnCancel;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Siguiente");
			btnNext.setMnemonic('S');
			btnNext.setEnabled(false);
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					mostrarVentanaRegistro();
				}
			});
			btnNext.setBackground(Color.GREEN);
			btnNext.setBounds(613, 412, 97, 25);
		}
		return btnNext;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("Precio pedido:");
			lblPrice.setBounds(613, 248, 97, 16);
		}
		return lblPrice;
	}

	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrice.setText("");
			txtPrice.setEditable(false);
			txtPrice.setBounds(613, 275, 79, 22);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}

	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("Descuento del 10%");
			lblDiscount.setForeground(Color.GREEN);
			lblDiscount.setBounds(613, 308, 124, 16);
			lblDiscount.setVisible(false);
		}
		return lblDiscount;
	}

	private JLabel getLblArticleUnits() {
		if (lblArticleUnits == null) {
			lblArticleUnits = new JLabel("units");
			lblArticleUnits.setText("Nº de unds: " + pedido.getUnidades((Articulo) getComboBox().getSelectedItem()));
			lblArticleUnits.setBounds(155, 248, 231, 16);
		}
		return lblArticleUnits;
	}

	private void mostrarVentanaRegistro() {
		VentanaRegistro frame = new VentanaRegistro(pedido, this);
		frame.setVisible(true);
	}

	private JScrollPane getSpPedido() {
		if (spPedido == null) {
			spPedido = new JScrollPane();
			spPedido.setBounds(613, 75, 204, 116);
			spPedido.setViewportView(getTxtrPedido());
			spPedido.setVisible(false);
			
		}
		return spPedido;
	}

	private JTextArea getTxtrPedido() {
		if (txtrPedido == null) {
			txtrPedido = new JTextArea();
			txtrPedido.setText("Pedido:\r\nTotal: 0,00 \u20AC");
		}
		return txtrPedido;
	}

	private JLabel getLblPedidoImg() {
		if (lblPedidoImg == null) {
			lblPedidoImg = new JLabel("");
			lblPedidoImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					getSpPedido().setVisible(true);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					getSpPedido().setVisible(false);
				}
			});
			lblPedidoImg.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/pedido.png")));
			lblPedidoImg.setBounds(613, 26, 191, 38);
		}
		return lblPedidoImg;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Eliminar");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int eliminar = pedido.eliminarUnidadesArticulo((int) getSpinnerUnits().getValue(),
							(Articulo) getComboBox().getSelectedItem());
					if (eliminar == -1) {
						JOptionPane.showMessageDialog(null,
								"El numero de unidades a eliminar es mayor que el numero de unidades en el pedido");
					}
					getTxtPrice().setText(String.format("%.2f €", pedido.calcularTotal()));
					getLblArticleUnits()
							.setText("Nº de unds: " + pedido.getUnidades((Articulo) getComboBox().getSelectedItem()));
					getTxtrPedido().setText(pedido.toString());
					if (!pedido.getDescuento()) {
						getLblDiscount().setVisible(false);
					}
					if (pedido.calcularTotal() == 0.0) {
						getBtnNext().setEnabled(false);
					}
				}
			});
			btnDelete.setMnemonic('E');
			btnDelete.setBackground(Color.GREEN);
			btnDelete.setBounds(720, 214, 97, 25);
		}
		return btnDelete;
	}

	private JComboBox<Articulo> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Articulo>();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getSpinnerUnits().setValue(1);
					getLblArticleUnits().setText("Nº de unds: " + pedido.getUnidades((Articulo) comboBox.getSelectedItem()));
					getLblImgProducto().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/"+ ((Articulo)comboBox.getSelectedItem()).getCodigo() + ".png")));
				}
			});
			comboBox.setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulos()));
			comboBox.setBounds(155, 215, 386, 22);
		}
		return comboBox;
	}
	private JLabel getLblDiscountIcon() {
		if (lblDiscountIcon == null) {
			lblDiscountIcon = new JLabel("");
			lblDiscountIcon.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/sale101.png")));
			lblDiscountIcon.setBounds(494, 308, 97, 108);
		}
		return lblDiscountIcon;
	}
	private JTextPane getTxtpnDiscount() {
		if (txtpnDiscount == null) {
			txtpnDiscount = new JTextPane();
			txtpnDiscount.setFont(new Font("Arial Black", Font.PLAIN, 14));
			txtpnDiscount.setEditable(false);
			txtpnDiscount.setForeground(Color.RED);
			txtpnDiscount.setText("       Mc Happy Day \r\n  10% de descuento en \r\npedidos de mas de 50 \u20AC");
			txtpnDiscount.setBounds(619, 331, 198, 85);
		}
		return txtpnDiscount;
	}
	private JLabel getLblImgProducto() {
		if (lblImgProducto == null) {
			lblImgProducto = new JLabel("");
			lblImgProducto.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/HA01.png")));
			lblImgProducto.setBounds(280, 275, 160, 130);
			
		}
		return lblImgProducto;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnPedido());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnPedido() {
		if (mnPedido == null) {
			mnPedido = new JMenu("Pedido");
			mnPedido.setMnemonic('P');
			mnPedido.add(getMntmNuevo());
			mnPedido.add(getSeparator());
			mnPedido.add(getMntmSalir());
		}
		return mnPedido;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
			mntmNuevo.setMnemonic('N');
			mntmNuevo.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return mntmNuevo;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			mntmSalir.setMnemonic('S');
		}
		return mntmSalir;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('d');
			mnAyuda.add(getMntmContenidos());
			mnAyuda.add(getSeparator_1());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmContenidos() {
		if (mntmContenidos == null) {
			mntmContenidos = new JMenuItem("Contenidos");
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
			mntmContenidos.setMnemonic('C');
			mntmContenidos.setEnabled(false);
		}
		return mntmContenidos;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de ...");
			mntmAcercaDe.setMnemonic('A');
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null,"Aplicacion para Mc'Donalds\nPracticas CPM 2019-2020\nEII Oviedo","Acerca de Mc'Donalds-España",JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAcercaDe;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			panelFiltro = new JPanel();
			panelFiltro.setBackground(Color.WHITE);
			panelFiltro.setBounds(10, 27, 123, 405);
			panelFiltro.setLayout(new GridLayout(4, 1, 0, 1));
			panelFiltro.add(getBtnHamburgesas());
			panelFiltro.add(getBtnBebidas());
			panelFiltro.add(getBtnComplementos());
			panelFiltro.add(getBtnPostres());
		}
		return panelFiltro;
	}
	private JButton getBtnHamburgesas() {
		if (btnHamburgesas == null) {
			btnHamburgesas = new JButton("Hamburguesas");
			btnHamburgesas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulosEspecificos("Hamburguesa")));
					getComboBox().setSelectedIndex(0);
				}
			});
			btnHamburgesas.setHorizontalTextPosition(SwingConstants.CENTER);
			btnHamburgesas.setVerticalAlignment(SwingConstants.TOP);
			btnHamburgesas.setBackground(Color.WHITE);
			btnHamburgesas.setMargin(new Insets(0, 0, 0, 0));
			btnHamburgesas.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnHamburgesas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Hamburguesa.png")));
		}
		return btnHamburgesas;
	}
	private JButton getBtnBebidas() {
		if (btnBebidas == null) {
			btnBebidas = new JButton("Bebidas");
			btnBebidas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulosEspecificos("Bebida")));
					getComboBox().setSelectedIndex(0);
				}
			});
			btnBebidas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Bebida.png")));
			btnBebidas.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnBebidas.setVerticalAlignment(SwingConstants.TOP);
			btnBebidas.setMargin(new Insets(0, 0, 0, 0));
			btnBebidas.setHorizontalTextPosition(SwingConstants.CENTER);
			btnBebidas.setBackground(Color.WHITE);
		}
		return btnBebidas;
	}
	private JButton getBtnComplementos() {
		if (btnComplementos == null) {
			btnComplementos = new JButton("Complementos");
			btnComplementos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulosEspecificos("Complemento")));
					getComboBox().setSelectedIndex(0);
				}
			});
			btnComplementos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Complemento.png")));
			btnComplementos.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnComplementos.setVerticalAlignment(SwingConstants.TOP);
			btnComplementos.setMargin(new Insets(0, 0, 0, 0));
			btnComplementos.setHorizontalTextPosition(SwingConstants.CENTER);
			btnComplementos.setBackground(Color.WHITE);
		}
		return btnComplementos;
	}
	private JButton getBtnPostres() {
		if (btnPostres == null) {
			btnPostres = new JButton("Postres");
			btnPostres.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulosEspecificos("Postre")));
					getComboBox().setSelectedIndex(0);
				}
			});
			btnPostres.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Postre.png")));
			btnPostres.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnPostres.setVerticalAlignment(SwingConstants.TOP);
			btnPostres.setMargin(new Insets(0, 0, 0, 0));
			btnPostres.setHorizontalTextPosition(SwingConstants.CENTER);
			btnPostres.setBackground(Color.WHITE);
		}
		return btnPostres;
	}
}
