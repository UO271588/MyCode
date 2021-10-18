package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.help.*;
import java.net.*;
import java.io.*;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import player.MusicPlayer;
import player.MyFile;

import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private MusicPlayer musicPlayer;
	private DefaultListModel<MyFile> modelolistLibrary;
	private DefaultListModel<MyFile> modelolistPlay;
	private JPanel contentPane;
	private JPanel pnNorte;
	private JSlider slider;
	private JPanel pnVolumen;
	private JLabel lblLogo;
	private JTextField txtVolumen;
	private JLabel lblVolumen;
	private JPanel pnCentro;
	private JPanel pnLib;
	private JLabel lblLibreria;
	private JPanel pnPlayList;
	private JPanel pnBotones1;
	private JButton btnAddToPlaylist;
	private JButton btnDelete;
	private JLabel lblPlaylist;
	private JScrollPane scrollPaneLibreria;
	private JList<MyFile> listLibreria;
	private JPanel pnBotones2;
	private JButton btnPlay;
	private JButton btnStop;
	private JButton btnAvanzar;
	private JButton btnRetroceder;
	private JButton btnBorrar;
	private JScrollPane scrollPanePlayList;
	private JList<MyFile> listPlay;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JMenuItem mntmExit;
	private JSeparator separator;
	private JFileChooser selector;
	private JMenu mnOptions;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JMenu mnPlay;
	private JMenuItem mntmContents;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Properties p = new Properties();
					p.put("logoString", "");
					HiFiLookAndFeel.setCurrentTheme(p);
					UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
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
		musicPlayer = new MusicPlayer();
		setMinimumSize(new Dimension(650, 300));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		setTitle("EII Music Player");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				getMnFile().grabFocus();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
		cargaAyuda();
	}

	/**
	 * Crea el panel Norte de la aplicacion
	 * 
	 * @return
	 */
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setBackground(Color.BLACK);
			pnNorte.setLayout(new GridLayout(1, 3, 0, 0));
			pnNorte.add(getLblLogo());
			pnNorte.add(getSlider());
			pnNorte.add(getPnVolumen());
		}
		return pnNorte;
	}

	/**
	 * Crea el slider del volumen de la aplicacion
	 * 
	 * @return
	 */
	private JSlider getSlider() {
		if (slider == null) {
			slider = new JSlider();
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					getTxtVolumen().setText(String.valueOf(slider.getValue()));
					setVolume(getSlider().getValue());
				}
			});
			slider.setBackground(Color.BLACK);
			slider.setForeground(Color.WHITE);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.setMinorTickSpacing(5);
			slider.setMajorTickSpacing(20);
		}
		return slider;
	}

	private void setVolume(double vol) {
		double volMax = getSlider().getMaximum();
		musicPlayer.setVolume(vol, volMax);

	}

	/**
	 * Crea el panel de volumen de la aplicacion
	 * 
	 * @return
	 */
	private JPanel getPnVolumen() {
		if (pnVolumen == null) {
			pnVolumen = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnVolumen.getLayout();
			flowLayout.setVgap(15);
			pnVolumen.setBackground(Color.BLACK);
			pnVolumen.add(getLblVolumen());
			pnVolumen.add(getTxtVolumen());
		}
		return pnVolumen;
	}

	/**
	 * Crea la label para el logo de la aplicacion
	 * 
	 * @return
	 */
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setBackground(Color.BLACK);
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
		}
		return lblLogo;
	}

	/**
	 * Crea el jtextfield para representar el volumen de la aplicacion
	 * 
	 * @return
	 */
	private JTextField getTxtVolumen() {
		if (txtVolumen == null) {
			txtVolumen = new JTextField();
			txtVolumen.setEditable(false);
			txtVolumen.setHorizontalAlignment(SwingConstants.CENTER);
			txtVolumen.setText("50");
			txtVolumen.setBorder(null);
			txtVolumen.setFont(new Font("Tahoma", Font.BOLD, 28));
			txtVolumen.setForeground(Color.WHITE);
			txtVolumen.setBackground(Color.BLACK);
			txtVolumen.setColumns(3);
		}
		return txtVolumen;
	}

	/**
	 * Crea el label para el volumen de la aplicacion
	 * 
	 * @return
	 */
	private JLabel getLblVolumen() {
		if (lblVolumen == null) {
			lblVolumen = new JLabel("Vol:");
			lblVolumen.setFont(new Font("Tahoma", Font.BOLD, 28));
			lblVolumen.setForeground(new Color(255, 69, 0));
		}
		return lblVolumen;
	}

	/**
	 * Crea el panel del centro de la aplicacion
	 * 
	 * @return
	 */
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBorder(null);
			pnCentro.setBackground(Color.BLACK);
			pnCentro.setLayout(new GridLayout(1, 2, 5, 0));
			pnCentro.add(getPnLib());
			pnCentro.add(getPnPlayList());
		}
		return pnCentro;
	}

	/**
	 * Crea el panel para la parte de la libreria de musica
	 * 
	 * @return
	 */
	private JPanel getPnLib() {
		if (pnLib == null) {
			pnLib = new JPanel();
			pnLib.setBorder(null);
			pnLib.setBackground(Color.BLACK);
			pnLib.setLayout(new BorderLayout(0, 0));
			pnLib.add(getLblLibreria(), BorderLayout.NORTH);
			pnLib.add(getPnBotones1(), BorderLayout.SOUTH);
			pnLib.add(getScrollPaneLibreria(), BorderLayout.CENTER);
		}
		return pnLib;
	}

	/**
	 * Crea el lbl para libreria
	 * 
	 * @return
	 */
	private JLabel getLblLibreria() {
		if (lblLibreria == null) {
			lblLibreria = new JLabel("\u266A Library:");
			lblLibreria.setDisplayedMnemonic('L');
			lblLibreria.setLabelFor(getListLibreria());
			lblLibreria.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblLibreria.setForeground(new Color(255, 69, 0));
		}
		return lblLibreria;
	}

	/**
	 * Crea el panel para la playlist de la aplicacion
	 * 
	 * @return
	 */
	private JPanel getPnPlayList() {
		if (pnPlayList == null) {
			pnPlayList = new JPanel();
			pnPlayList.setBackground(Color.BLACK);
			pnPlayList.setLayout(new BorderLayout(0, 0));
			pnPlayList.add(getLblPlaylist(), BorderLayout.NORTH);
			pnPlayList.add(getPnBotones2(), BorderLayout.SOUTH);
			pnPlayList.add(getScrollPanePlayList(), BorderLayout.CENTER);
		}
		return pnPlayList;
	}

	/**
	 * Crea el panel de botones 1
	 * 
	 * @return
	 */
	private JPanel getPnBotones1() {
		if (pnBotones1 == null) {
			pnBotones1 = new JPanel();
			pnBotones1.setLayout(new GridLayout(1, 2, 0, 0));
			pnBotones1.add(getBtnAddToPlaylist());
			pnBotones1.add(getBtnDelete());
		}
		return pnBotones1;
	}

	/**
	 * Crea el boton add to playlist
	 * 
	 * @return
	 */
	private JButton getBtnAddToPlaylist() {
		if (btnAddToPlaylist == null) {
			btnAddToPlaylist = new JButton("Add to Playlist");
			btnAddToPlaylist.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for (int i = 0; i < getListLibreria().getSelectedValuesList().size(); i++) {
						if(modelolistPlay.indexOf(listLibreria.getSelectedValuesList().get(i)) == -1) {
							modelolistPlay.addElement(listLibreria.getSelectedValuesList().get(i));
						}
					}
				}
			});
			btnAddToPlaylist.setMnemonic('A');
			btnAddToPlaylist.setMargin(new Insets(0, 0, 0, 0));
			btnAddToPlaylist.setFont(new Font("Dialog", Font.BOLD, 14));
			btnAddToPlaylist.setBackground(UIManager.getColor("Button.background"));
			btnAddToPlaylist.setForeground(Color.WHITE);
		}
		return btnAddToPlaylist;
	}

	/**
	 * Crea el boton delete de la aplicacion
	 * 
	 * @return
	 */
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					borrarMult();
				}
			});
			btnDelete.setMnemonic('D');
			btnDelete.setMargin(new Insets(0, 0, 0, 0));
			btnDelete.setFont(new Font("Dialog", Font.BOLD, 14));
			btnDelete.setForeground(Color.WHITE);
			btnDelete.setBackground(UIManager.getColor("Button.background"));
		}
		return btnDelete;
	}

	private void borrarMult() {
		int[] indices = listLibreria.getSelectedIndices();
		for (int i = 0; i < indices.length ; i++) {
			if (indices[i] != -1) {
				modelolistLibrary.remove(indices[i]-i);				
			}
		}
	}

	private int search(File archivo) {
		for (int i = 0; i < modelolistPlay.size(); i++) {
			if (modelolistPlay.get(i).equals(archivo)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Crea el lbl de la playlist
	 * 
	 * @return
	 */
	private JLabel getLblPlaylist() {
		if (lblPlaylist == null) {
			lblPlaylist = new JLabel("\u266A PlayList:");
			lblPlaylist.setLabelFor(getListPlay());
			lblPlaylist.setDisplayedMnemonic('P');
			lblPlaylist.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblPlaylist.setForeground(new Color(255, 69, 0));
		}
		return lblPlaylist;
	}

	/**
	 * Crea el scrollPanel de la libreria
	 * 
	 * @return
	 */
	private JScrollPane getScrollPaneLibreria() {
		if (scrollPaneLibreria == null) {
			scrollPaneLibreria = new JScrollPane();
			scrollPaneLibreria.setBorder(new LineBorder(new Color(255, 69, 0), 6, true));
			scrollPaneLibreria.setViewportView(getListLibreria());
		}
		return scrollPaneLibreria;
	}

	/**
	 * Crea la lista de la libreria de musica
	 * 
	 * @return
	 */
	private JList<MyFile> getListLibreria() {
		if (listLibreria == null) {
			modelolistLibrary = new DefaultListModel<MyFile>();
			listLibreria = new JList<MyFile>(modelolistLibrary);
			listLibreria.setFont(new Font("Tahoma", Font.PLAIN, 14));
			listLibreria.setForeground(Color.WHITE);
			listLibreria.setBackground(Color.BLACK);
		}
		return listLibreria;
	}

	/**
	 * Crea el panel de botones 2
	 * 
	 * @return
	 */
	private JPanel getPnBotones2() {
		if (pnBotones2 == null) {
			pnBotones2 = new JPanel();
			pnBotones2.setLayout(new GridLayout(1, 5, 0, 0));
			pnBotones2.add(getBtnRetroceder());
			pnBotones2.add(getBtnPlay());
			pnBotones2.add(getBtnStop());
			pnBotones2.add(getBtnAvanzar());
			pnBotones2.add(getBtnBorrar());
		}
		return pnBotones2;
	}

	/**
	 * Crea el boton de play
	 * 
	 * @return
	 */
	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("\u25BA");
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					play();
				}
			});
			btnPlay.setForeground(Color.WHITE);
			btnPlay.setBackground(UIManager.getColor("Button.background"));
			btnPlay.setMargin(new Insets(0, 0, 0, 0));
			btnPlay.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btnPlay;
	}

	private void play() {
		if (listPlay.getSelectedIndex() == -1) {
			listPlay.setSelectedIndex(0);
		}
		if(!modelolistPlay.isEmpty()) {
			musicPlayer.play(listPlay.getSelectedValue().getF());
		}
	}

	/**
	 * Crea el boton de stop
	 * 
	 * @return
	 */
	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("\u25A0");
			btnStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					stop();
				}
			});
			btnStop.setForeground(Color.WHITE);
			btnStop.setBackground(UIManager.getColor("Button.background"));
			btnStop.setMargin(new Insets(0, 0, 0, 0));
			btnStop.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btnStop;
	}

	private void stop() {
		musicPlayer.stop();
	}

	/**
	 * Crea el boton de Avanzar
	 * 
	 * @return
	 */
	private JButton getBtnAvanzar() {
		if (btnAvanzar == null) {
			btnAvanzar = new JButton("\u25BA\u25BA");
			btnAvanzar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					avanzar();
				}
			});
			btnAvanzar.setMargin(new Insets(0, 0, 0, 0));
			btnAvanzar.setFont(new Font("Dialog", Font.BOLD, 14));
			btnAvanzar.setForeground(Color.WHITE);
			btnAvanzar.setBackground(UIManager.getColor("Button.background"));
		}
		return btnAvanzar;
	}

	private void avanzar() {
		if (listPlay.getSelectedIndex() == modelolistPlay.getSize() - 1) {
			listPlay.setSelectedIndex(0);
		} else {
			listPlay.setSelectedIndex(listPlay.getSelectedIndex() + 1);
		}
		play();
	}

	/**
	 * Crea el boton de retroceder
	 * 
	 * @return
	 */
	private JButton getBtnRetroceder() {
		if (btnRetroceder == null) {
			btnRetroceder = new JButton("\u25C4\u25C4");
			btnRetroceder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					retroceder();
				}
			});
			btnRetroceder.setMargin(new Insets(0, 0, 0, 0));
			btnRetroceder.setFont(new Font("Dialog", Font.BOLD, 14));
			btnRetroceder.setBackground(UIManager.getColor("Button.background"));
			btnRetroceder.setForeground(Color.WHITE);
		}
		return btnRetroceder;
	}

	private void retroceder() {
		if (listPlay.getSelectedIndex() == 0) {
			listPlay.setSelectedIndex(0);
		} else {
			listPlay.setSelectedIndex(listPlay.getSelectedIndex() - 1);
		}
		play();
	}

	/**
	 * Crea el boton de borrar
	 * 
	 * @return
	 */
	private JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar");
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrar();
				}
			});
			btnBorrar.setMnemonic('B');
			btnBorrar.setForeground(Color.WHITE);
			btnBorrar.setBackground(UIManager.getColor("Button.background"));
			btnBorrar.setMargin(new Insets(0, 0, 0, 0));
			btnBorrar.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btnBorrar;
	}

	private void borrar() {
		int index = listPlay.getSelectedIndex();

		if (index == -1) {
			return;
		}
		if (index == 0 && !modelolistPlay.isEmpty()) {
			if (musicPlayer.getCanSonando() != null) {
				if (musicPlayer.getCanSonando().equals(listPlay.getSelectedValue())) {
					stop();
				}
			}

			modelolistPlay.remove(index);
			if (!modelolistPlay.isEmpty()) {
				listPlay.setSelectedIndex(index);
			}

		} else if (index == modelolistPlay.size() - 1) {
			if (musicPlayer.getCanSonando() != null) {
				if (musicPlayer.getCanSonando().equals(listPlay.getSelectedValue())) {
					stop();
				}
			}

			modelolistPlay.remove(index);
			if (!modelolistPlay.isEmpty()) {
				listPlay.setSelectedIndex(index - 1);
			}

		} else if (index > 0 && index < modelolistPlay.size() - 1) {
			if (musicPlayer.getCanSonando() != null) {
				if (musicPlayer.getCanSonando().equals(listPlay.getSelectedValue())) {
					stop();
					modelolistPlay.remove(index);
				}
			}

			modelolistPlay.remove(index);
			listPlay.setSelectedIndex(index);
		}
	}

	/**
	 * Crea el scrool panel para la playlist de musica
	 * 
	 * @return
	 */
	private JScrollPane getScrollPanePlayList() {
		if (scrollPanePlayList == null) {
			scrollPanePlayList = new JScrollPane();
			scrollPanePlayList.setBorder(new LineBorder(new Color(255, 69, 0), 6, true));
			scrollPanePlayList.setViewportView(getListPlay());
		}
		return scrollPanePlayList;
	}

	/**
	 * Crea la lista para la playlist
	 * 
	 * @return
	 */
	private JList<MyFile> getListPlay() {
		if (listPlay == null) {
			modelolistPlay = new DefaultListModel<MyFile>();
			listPlay = new JList<MyFile>(modelolistPlay);
			listPlay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPlay.setForeground(Color.WHITE);
			listPlay.setBackground(Color.BLACK);
		}
		return listPlay;
	}

	/**
	 * Crea menu de opciones
	 * 
	 * @return
	 */
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	/**
	 * Crea el menu file
	 * 
	 * @return
	 */
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('F');
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	/**
	 * Crea el menuItem open
	 * 
	 * @return
	 */
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open ...");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cargarMusica();
				}
			});
			mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		}
		return mntmOpen;
	}

	/**
	 * Metodo que carga la musica en la libreria
	 */
	private void cargarMusica() {
		int respuesta = getSelector().showOpenDialog(null);
		if (respuesta == JFileChooser.APPROVE_OPTION) {
			for (int i = 0; i < selector.getSelectedFiles().length; i++) {
				boolean encontrado = false;
				MyFile fichero = new MyFile(selector.getSelectedFiles()[i]);
				for (int j = 0; j < modelolistLibrary.size(); j++) {
					if (modelolistLibrary.get(j).equals(fichero)) {
						encontrado = true;
						break;
					}
				}

				if (!encontrado) {
					modelolistLibrary.addElement(fichero);
				}
			}
		}
	}

	/**
	 * Crea el menu item Exit
	 * 
	 * @return
	 */
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
		}
		return mntmExit;
	}

	/**
	 * Crea un separador
	 * 
	 * @return
	 */
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	/**
	 * Crea un selector de ficheros o archivos
	 * 
	 * @return
	 */
	private JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
			selector.setFileFilter(new FileNameExtensionFilter("Archivos mp3", "mp3"));
			
			
			//Con esta opcion fija el direcctorio en en el escritorio
			String desktopPath = System.getProperty("user.home") + "/Desktop";
			selector.setCurrentDirectory(new File(desktopPath));
		}
		return selector;
	}

	/**
	 * Crea el menu Options
	 * 
	 * @return
	 */
	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('O');
		}
		return mnOptions;
	}

	/**
	 * Crea el menu Help
	 * 
	 * @return
	 */
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.add(getMntmContents());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	/**
	 * Crea el menu item About
	 * 
	 * @return
	 */
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Icon icono = new ImageIcon(getClass().getResource("/img/logoTitulo.png"));
					JOptionPane.showMessageDialog(null,
							"EII Music Player\nAplicacion para reproducir musica\nEloy Alfredo Schmidt Rodríguez UO271588",
							"About EII Music Player", JOptionPane.PLAIN_MESSAGE, icono);
				}
			});
		}
		return mntmAbout;
	}

	/**
	 * Crea el menu Play
	 * 
	 * @return
	 */
	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setMnemonic('l');
		}
		return mnPlay;
	}
	
	private void cargaAyuda(){

		   URL hsURL;
		   HelpSet hs;

		    try {
			    	File fichero = new File("help/ayuda.hs");
			    	hsURL = fichero.toURI().toURL();
			        hs = new HelpSet(null, hsURL);
			      }

		    catch (Exception e){
		      System.out.println("Ayuda no encontrada");
		      return;
		   }

		   HelpBroker hb = hs.createHelpBroker();
		   hb.initPresentation();

		   hb.enableHelpKey(getRootPane(),"introduccion", hs);
		   hb.enableHelpOnButton(getMntmContents(), "introduccion", hs);
		   hb.enableHelp(getListLibreria(), "añadir", hs);
		   hb.enableHelp(getBtnPlay(), "reproducir", hs);
		 }

	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
		}
		return mntmContents;
	}
}
