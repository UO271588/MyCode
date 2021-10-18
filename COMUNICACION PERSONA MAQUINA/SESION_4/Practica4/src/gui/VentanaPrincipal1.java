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

/**
 * 
 * @author Eloy Alfredo Schmidt Rguez UO271588
 * @version 1.0.0
 *
 */

public class VentanaPrincipal1 extends JFrame {

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
	private JScrollPane spPedido;
	private JTextArea txtrPedido;
	private JLabel lblPedidoImg;
	private JButton btnDelete;
	private JComboBox<Articulo> comboBox;

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
	public VentanaPrincipal1() {

		carta = new Carta();
		pedido = new Pedido();

		setTitle("McDonald's - Espa\u00F1a");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 522);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblLogo());
		contentPane.add(getLblLogoTitle());
		contentPane.add(getLblArticulos());
		contentPane.add(getLblUnits());
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
		contentPane.add(getComboBox());

	}

	public void inicializar() {
		pedido.inicializar();
		getTxtPrice().setText("");
		getComboBox().setSelectedIndex(0);
		getSpinnerUnits().setValue(1);
		getBtnNext().setEnabled(false);
		getLblDiscount().setVisible(false);
		
		getTxtrPedido().setText("Pedido:");
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
			lblLogo.setBounds(12, 13, 204, 140);
		}
		return lblLogo;
	}

	private JLabel getLblLogoTitle() {
		if (lblLogoTitle == null) {
			lblLogoTitle = new JLabel("McDonald's");
			lblLogoTitle.setFont(new Font("Arial Black", Font.BOLD, 32));
			lblLogoTitle.setBounds(228, 66, 231, 57);
		}
		return lblLogoTitle;
	}

	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Articulos:");
			lblArticulos.setDisplayedMnemonic('r');
			lblArticulos.setBounds(33, 209, 87, 16);
		}
		return lblArticulos;
	}

	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel("Unidades:");
			lblUnits.setDisplayedMnemonic('U');
			lblUnits.setLabelFor(getSpinnerUnits());
			lblUnits.setBounds(431, 209, 132, 16);
		}
		return lblUnits;
	}

	private JSpinner getSpinnerUnits() {
		if (spinnerUnits == null) {
			spinnerUnits = new JSpinner();
			spinnerUnits.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spinnerUnits.setBounds(431, 238, 52, 22);
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
			btnAdd.setBounds(495, 238, 97, 25);
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
			btnCancel.setBounds(622, 449, 97, 25);
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
				}
			});
			btnNext.setBackground(Color.GREEN);
			btnNext.setBounds(513, 449, 97, 25);
		}
		return btnNext;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("Precio pedido:");
			lblPrice.setBounds(491, 280, 97, 16);
		}
		return lblPrice;
	}

	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
			txtPrice.setText("");
			txtPrice.setEditable(false);
			txtPrice.setBounds(491, 309, 79, 22);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}

	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("Descuento del 10%");
			lblDiscount.setForeground(Color.GREEN);
			lblDiscount.setBounds(491, 344, 124, 16);
			lblDiscount.setVisible(false);
		}
		return lblDiscount;
	}

	

	private JScrollPane getSpPedido() {
		if (spPedido == null) {
			spPedido = new JScrollPane();
			spPedido.setBounds(495, 90, 204, 116);
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
			lblPedidoImg.setBounds(495, 39, 191, 38);
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
			btnDelete.setBounds(604, 238, 97, 25);
		}
		return btnDelete;
	}

	private JComboBox<Articulo> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Articulo>();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSpinnerUnits().setValue(1);
					
				}
			});
			
			comboBox.setModel(new DefaultComboBoxModel<Articulo>(carta.getArticulos()));
			comboBox.setBounds(33, 238, 386, 22);
		}
		return comboBox;
	}
}