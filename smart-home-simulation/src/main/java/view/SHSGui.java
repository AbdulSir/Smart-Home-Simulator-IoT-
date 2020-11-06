package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import controller.SHSController;
import controller.SHPController;
import controller.SHCController;
import model.Users;
import model.ReadingJsonFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class SHSGui extends JFrame {
	private JLabel labelProfileImage;
	private JTextArea textAreaConsoleLog;
	private JToggleButton togglebuttonSimulator;
	private JTextField houseTemp;
	private JTextField outsideTemp;
	private JTextField enterNewUsername;
	private Users user;
	private JButton newUser;
	private JComboBox comboBoxDeleteUser;
	private JButton deleteUserButton;
	private JButton pressbuttonEditContext;
	private JLabel CurrentLocation;
	private ReadingJsonFile rjFile;
	private JPanel panelView;
	private JMenuItem mntmOpen;
	private JLabel labelBoxLocation;
	private JComboBox comboBoxRole;
	private JSpinner timeSpinner;
	private JButton presstimebtn;
	private JLabel indoorHouseTempValue;
	private JLabel outdoorTemperatureValue;
	private JLabel weatherValue;
	private JLabel timeValue;
	private JLabel dateValue;
	private JComboBox comboBoxWeather;
	private JDateChooser dateChooser;
	private JComboBox doorsComboBox;
	private JButton OpenDoorsButton;
	private JComboBox lightsComboBox;
	private JButton LightsButton;
	private JComboBox OpenWindowsComboBox;
	private JButton openWindowsButton;
	private JToggleButton AutoModeToggleButton;
	private JButton lockDoorsButton;
	private JMenuItem mntmSave;
	private JMenuItem mntmLoad;
	private JComboBox comboBoxPermission;
	private JSlider slider;
	private JSpinner awayModeStartTime;
	private JSpinner awayModeStopTime;
	private JTextField timeToAlertInput;
	private JLabel labelUserPermissionValue;
	private JLabel userLocationLabel;
	private JToggleButton AwayModeToggleButton;
	private static SHPController securityController;
	private static SHCController coreController;
	private static SHSController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SHSGui frame = new SHSGui();
					// Controller
					securityController = new SHPController(frame);
					coreController = new SHCController(frame, securityController);
					controller = new SHSController(frame, coreController, securityController);
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
	public SHSGui() {
		initComponents();
	}

	/**
	 * This method contains all of the code for creating and initializing
	 * components.
	 **/
	private void initComponents() {
		/** Ui Window Icon **/
		setIconImage(Toolkit.getDefaultToolkit().getImage(SHSGui.class.getResource("/resources/shs_128.png")));

		/** Window Title **/
		setTitle("Smart Home Simulation");

		/** Termiante on close **/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/** Closes streams for log files when the application is closed by the user**/
		this.addWindowListener((new WindowListener(){
			@Override
			public void windowClosing(WindowEvent e) {
				coreController.getPrintWriter().flush();
				coreController.getPrintWriter().close();
			}
			@Override
			public void windowOpened(WindowEvent e) {
			}
			@Override
			public void windowClosed(WindowEvent e) {
			}
			@Override
			public void windowIconified(WindowEvent e) {
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			@Override
			public void windowActivated(WindowEvent e) {
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
           }));
            
		/** Window Size **/
		setBounds(100, 100, 1255, 792);

		/** Menu Bar **/
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// File Section
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		// Open Option Under File
		mntmOpen = new JMenuItem("Open");
		mntmOpen.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/open_layout.png")));
		mnFile.add(mntmOpen);

		// Save Option Under File
		mntmSave = new JMenuItem("Save");

		mntmSave.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/save.png")));
		mnFile.add(mntmSave);

		// Load Option Under File
		mntmLoad = new JMenuItem("Load");

		mntmLoad.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/load.png")));
		mnFile.add(mntmLoad);

		/** Main Panel **/
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/** Profile Panel **/
		JPanel panelProfile = new JPanel();
		panelProfile.setBackground(Color.WHITE);
		// Profile image
		labelProfileImage = new JLabel("");
		labelProfileImage.setIcon(new ImageIcon(SHSGui.class.getResource("/resources/default.png")));

		/** Horizontal Tabs **/
		JPanel panelControl = new JPanel();
		panelControl.setBackground(Color.WHITE);

		/** Console Panel **/
		JPanel panelConsole = new JPanel();
		panelConsole.setBackground(SystemColor.control);

		/** 2d Layout Panel **/
		panelView = new JPanel();

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelProfile, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panelConsole, GroupLayout.PREFERRED_SIZE, 795, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panelControl, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelProfile, GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panelView, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
								.addComponent(panelControl, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelConsole, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)))
					.addGap(10))
		);
		GroupLayout gl_panelView = new GroupLayout(panelView);
		gl_panelView.setHorizontalGroup(
				gl_panelView.createParallelGroup(Alignment.LEADING).addGap(0, 438, Short.MAX_VALUE));
		gl_panelView
				.setVerticalGroup(gl_panelView.createParallelGroup(Alignment.LEADING).addGap(0, 533, Short.MAX_VALUE));
		panelView.setLayout(gl_panelView);

		JLabel labelConsoleLog = new JLabel("Console Log");

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panelConsole = new GroupLayout(panelConsole);
		gl_panelConsole.setHorizontalGroup(gl_panelConsole.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConsole.createSequentialGroup().addGap(10).addGroup(gl_panelConsole
						.createParallelGroup(Alignment.LEADING).addComponent(labelConsoleLog)
						.addGroup(gl_panelConsole.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 645, GroupLayout.PREFERRED_SIZE)))
						.addGap(1234)));
		gl_panelConsole.setVerticalGroup(gl_panelConsole.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelConsole.createSequentialGroup().addGap(14).addComponent(labelConsoleLog).addGap(6)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		/** Console Log Text Area */
		textAreaConsoleLog = new JTextArea();
		scrollPane.setViewportView(textAreaConsoleLog);
		textAreaConsoleLog.setEditable(false);
		panelConsole.setLayout(gl_panelConsole);

		/** Horizontal Tabs **/
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		GroupLayout gl_panelControl = new GroupLayout(panelControl);
		gl_panelControl.setHorizontalGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(123, Short.MAX_VALUE)));
		gl_panelControl.setVerticalGroup(gl_panelControl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelControl.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE).addContainerGap()));

		/** SHS PANEL **/

		/** Add User **/

		/** Delete User **/

		/* Calendar **/

		/** SHC PANEL **/
		JPanel panelSHS = new JPanel();
		tabbedPane.addTab("SHS", null, panelSHS, null);
		pressbuttonEditContext = new JButton("Edit Context Of Simulator");

		JPanel panelUser = new JPanel();
		enterNewUsername = new JTextField();
		enterNewUsername.setColumns(10);
		newUser = new JButton("Add User");
		newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		comboBoxDeleteUser = new JComboBox();
		deleteUserButton = new JButton("Delete User");
		deleteUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		comboBoxPermission = new JComboBox();
		comboBoxPermission
				.setModel(new DefaultComboBoxModel(new String[] { "PARENT", "CHILDREN", "GUEST", "STRANGER" }));

		JLabel labelUserSetting = new JLabel("USER SETTING");
		GroupLayout gl_panelUser = new GroupLayout(panelUser);
		gl_panelUser.setHorizontalGroup(gl_panelUser.createParallelGroup(Alignment.LEADING).addGroup(gl_panelUser
				.createSequentialGroup()
				.addGroup(gl_panelUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelUser.createSequentialGroup().addGap(10).addGroup(gl_panelUser
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelUser.createSequentialGroup()
										.addComponent(enterNewUsername, GroupLayout.PREFERRED_SIZE, 117,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(comboBoxPermission, 0, 151, Short.MAX_VALUE))
								.addGroup(gl_panelUser.createSequentialGroup()
										.addComponent(comboBoxDeleteUser, GroupLayout.PREFERRED_SIZE, 142,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(deleteUserButton,
												GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))))
						.addGroup(gl_panelUser.createSequentialGroup().addContainerGap().addComponent(newUser,
								GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
						.addGroup(gl_panelUser.createSequentialGroup().addContainerGap().addComponent(labelUserSetting,
								GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_panelUser.setVerticalGroup(gl_panelUser.createParallelGroup(Alignment.LEADING).addGroup(gl_panelUser
				.createSequentialGroup()
				.addComponent(labelUserSetting, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panelUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(enterNewUsername, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxPermission, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(newUser, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panelUser.createParallelGroup(Alignment.LEADING).addComponent(deleteUserButton)
						.addComponent(comboBoxDeleteUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		panelUser.setLayout(gl_panelUser);

		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();
		GroupLayout gl_panelSHS = new GroupLayout(panelSHS);
		gl_panelSHS.setHorizontalGroup(
			gl_panelSHS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSHS.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSHS.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addComponent(panelUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(pressbuttonEditContext, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
						.addComponent(panel_2, 0, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelSHS.setVerticalGroup(
			gl_panelSHS.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSHS.createSequentialGroup()
					.addGap(10)
					.addComponent(panelUser, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pressbuttonEditContext, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		JLabel labelTemperatureSetting = new JLabel("TEMPERATURE SETTING");
		JLabel labelOutsideTemp = new JLabel("Outside Temp :");
		outsideTemp = new JTextField(5);
		JLabel labelOutsideTempValue = new JLabel("\u00B0C");
		JLabel labelWeather = new JLabel("Weather:");
		comboBoxWeather = new JComboBox();
		comboBoxWeather
				.setModel(new DefaultComboBoxModel(new String[] { "Cloudy", "Rainy", "Sunny", "Windy", "Snowy" }));
		JLabel labelHouseTemp = new JLabel("House Temp :");
		houseTemp = new JTextField(5);
		JLabel labelHouseTempValue = new JLabel("\u00B0C");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelTemperatureSetting, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2
								.createParallelGroup(Alignment.LEADING)
								.addComponent(labelWeather, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
								.addComponent(labelOutsideTemp, GroupLayout.PREFERRED_SIZE, 142,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(labelHouseTemp, GroupLayout.PREFERRED_SIZE, 130,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
												.addComponent(houseTemp, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(labelHouseTempValue))
										.addComponent(comboBoxWeather, Alignment.TRAILING, 0, 140, Short.MAX_VALUE)
										.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
												.addComponent(outsideTemp, GroupLayout.DEFAULT_SIZE, 124,
														Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(labelOutsideTempValue)))))
				.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(labelTemperatureSetting).addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(labelOutsideTempValue)
						.addComponent(outsideTemp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(labelOutsideTemp))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(labelWeather).addComponent(
						comboBoxWeather, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(labelHouseTemp)
						.addComponent(labelHouseTempValue).addComponent(houseTemp, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		panel_2.setLayout(gl_panel_2);

		JLabel labelDateAndTimeSetting = new JLabel("DATE AND TIME SETTING");
		dateChooser = new JDateChooser();
		JLabel labelTime = new JLabel("Time: ");
		timeSpinner = new JSpinner();
		timeSpinner.setModel(new SpinnerDateModel());
		timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));
		presstimebtn = new JButton("Set new Date & Time");
		
				slider = new JSlider();
				slider.setValue(0);
				
						slider.setToolTipText("");
						slider.setPaintLabels(true);
						slider.setSnapToTicks(true);
						slider.setMaximum(120);
						slider.setPaintTicks(true);
						slider.setMinorTickSpacing(15);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
								.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
									.addComponent(labelTime, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(timeSpinner, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
								.addComponent(labelDateAndTimeSetting, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
								.addComponent(slider, GroupLayout.PREFERRED_SIZE, 286, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(presstimebtn, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
							.addGap(2))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(labelDateAndTimeSetting, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelTime)
						.addComponent(timeSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(presstimebtn))
		);
		panel_1.setLayout(gl_panel_1);
		panelSHS.setLayout(gl_panelSHS);
		JPanel panelSHC = new JPanel();
		tabbedPane.addTab("SHC", null, panelSHC, null);
		panelSHC.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(16, 6, 277, 406);
		panelSHC.add(tabbedPane_1);

		JPanel LightsPanel = new JPanel();
		tabbedPane_1.addTab("Lights", null, LightsPanel, null);
		LightsPanel.setLayout(null);

		JLabel lblLights = new JLabel("Rooms");
		lblLights.setBounds(6, 46, 50, 16);
		LightsPanel.add(lblLights);

		lightsComboBox = new JComboBox();
		lightsComboBox.setBounds(46, 42, 204, 27);
		LightsPanel.add(lightsComboBox);

		AutoModeToggleButton = new JToggleButton("Auto Mode");
		AutoModeToggleButton.setBounds(6, 7, 244, 27);
		LightsPanel.add(AutoModeToggleButton);

		LightsButton = new JButton("Turn ON/OFF");
		LightsButton.setBounds(68, 73, 117, 29);
		LightsPanel.add(LightsButton);

		JPanel DoorsPanel = new JPanel();
		tabbedPane_1.addTab("Doors", null, DoorsPanel, null);
		DoorsPanel.setLayout(null);

		doorsComboBox = new JComboBox();
		doorsComboBox.setBounds(46, 5, 204, 27);
		DoorsPanel.add(doorsComboBox);

		JLabel lblNewLabel = new JLabel("Rooms");
		lblNewLabel.setBounds(6, 9, 54, 16);
		DoorsPanel.add(lblNewLabel);

		OpenDoorsButton = new JButton("Open/Close");
		OpenDoorsButton.setBounds(69, 32, 117, 29);
		DoorsPanel.add(OpenDoorsButton);

		lockDoorsButton = new JButton("Lock/Unlock");
		lockDoorsButton.setBounds(69, 57, 117, 29);
		DoorsPanel.add(lockDoorsButton);

		JPanel WindowsPanel = new JPanel();
		tabbedPane_1.addTab("Windows", null, WindowsPanel, null);
		WindowsPanel.setLayout(null);

		OpenWindowsComboBox = new JComboBox();
		OpenWindowsComboBox.setBounds(46, 5, 204, 27);
		WindowsPanel.add(OpenWindowsComboBox);

		JLabel lblWindows = new JLabel("Rooms");
		lblWindows.setBounds(6, 9, 54, 16);
		WindowsPanel.add(lblWindows);

		openWindowsButton = new JButton("Open/Close");
		openWindowsButton.setBounds(69, 32, 117, 29);
		WindowsPanel.add(openWindowsButton);

		/** SHP PANEL **/
		JPanel panelSHP = new JPanel();
		tabbedPane.addTab("SHP", null, panelSHP, null);
		panelSHP.setLayout(null);

		awayModeStartTime = new JSpinner();
		awayModeStartTime.setBounds(125, 17, 91, 32);
		awayModeStartTime.setModel(new SpinnerDateModel());
		awayModeStartTime.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));
		awayModeStopTime = new JSpinner();
		awayModeStopTime.setBounds(216, 17, 91, 32);
		awayModeStopTime.setModel(new SpinnerDateModel());
		awayModeStopTime.setEditor(new JSpinner.DateEditor(timeSpinner, "HH:mm"));
		panelSHP.add(awayModeStartTime);
		panelSHP.add(awayModeStopTime);
		
		JLabel setAwayModeLabel = new JLabel("Away Mode Time");
		setAwayModeLabel.setBounds(6, 25, 109, 16);
		panelSHP.add(setAwayModeLabel);
		
		timeToAlertInput = new JTextField();
		timeToAlertInput.setBounds(169, 60, 130, 26);
		panelSHP.add(timeToAlertInput);
		timeToAlertInput.setColumns(10);
		
		JLabel timeToAlertLabel = new JLabel("Time until alert (seconds)");
		timeToAlertLabel.setBounds(6, 64, 164, 16);
		panelSHP.add(timeToAlertLabel);
		
		AwayModeToggleButton = new JToggleButton("Away Mode");
		AwayModeToggleButton.setBounds(139, 463, 161, 29);
		panelSHP.add(AwayModeToggleButton);
		/** SHH PANEL **/
		JPanel panelSHH = new JPanel();
		tabbedPane.addTab("SHH", null, panelSHH, null);

		/** Add Tab **/
		JPanel panelPlus = new JPanel();
		tabbedPane.addTab("+", null, panelPlus, null);
		panelControl.setLayout(gl_panelControl);

		/** Left-most panel of GUI **/
		JPanel panelProfileInfo = new JPanel();

		/** Simulator Button **/
		togglebuttonSimulator = new JToggleButton("Simulator");

		/** House Panel **/
		JPanel panelHouseInfo = new JPanel();

		/** Outside Panel **/
		JPanel panelOutsideInfo = new JPanel();

		/** Form Open Button **/

		JPanel panel = new JPanel();

		GroupLayout gl_panelProfile = new GroupLayout(panelProfile);
		gl_panelProfile.setHorizontalGroup(
			gl_panelProfile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addGap(153)
					.addComponent(labelProfileImage, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addGap(148))
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelOutsideInfo, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelHouseInfo, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addContainerGap()
					.addComponent(togglebuttonSimulator, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 420, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelProfile.setVerticalGroup(
			gl_panelProfile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addContainerGap()
					.addComponent(togglebuttonSimulator)
					.addGap(7)
					.addComponent(labelProfileImage, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelProfileInfo, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelHouseInfo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelOutsideInfo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(192))
		);

		JLabel dateLabel = new JLabel("Date");

		dateValue = new JLabel("N/A");

		JLabel timeLabel = new JLabel("Time");

		timeValue = new JLabel("N/A");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
						.addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(timeValue, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(dateValue, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateValue, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(timeValue, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		/** House Temperature **/

		JLabel houseTempLabel = new JLabel("House Temp.");

		indoorHouseTempValue = new JLabel("0");

		GroupLayout gl_panelHouseInfo = new GroupLayout(panelHouseInfo);
		gl_panelHouseInfo.setHorizontalGroup(
			gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup()
					.addContainerGap()
					.addComponent(houseTempLabel)
					.addGap(114)
					.addComponent(indoorHouseTempValue, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelHouseInfo.setVerticalGroup(
			gl_panelHouseInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHouseInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelHouseInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(houseTempLabel)
						.addComponent(indoorHouseTempValue, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panelHouseInfo.setLayout(gl_panelHouseInfo);

		/** Weather Label **/
		JLabel outdoorTemperatureLabel = new JLabel("Outdoor Temp.");

		/** Weather ComboBox Option **/
		JLabel weatherLabel = new JLabel("Weather");

		/** Outdoor Temperature Label **/
		outdoorTemperatureValue = new JLabel("0");

		weatherValue = new JLabel("N/A");

		GroupLayout gl_panelOutsideInfo = new GroupLayout(panelOutsideInfo);
		gl_panelOutsideInfo.setHorizontalGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING).addComponent(weatherLabel)
								.addComponent(outdoorTemperatureLabel, GroupLayout.PREFERRED_SIZE, 185,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(weatherValue, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(outdoorTemperatureValue, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelOutsideInfo.setVerticalGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOutsideInfo.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(outdoorTemperatureLabel).addComponent(outdoorTemperatureValue,
										GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelOutsideInfo.createParallelGroup(Alignment.BASELINE).addComponent(weatherLabel)
								.addComponent(weatherValue))
						.addContainerGap(12, Short.MAX_VALUE)));
		panelOutsideInfo.setLayout(gl_panelOutsideInfo);

		/** Users Location **/
		JLabel labelRole = new JLabel("User");
		JLabel labelLocation = new JLabel("Location");

		/** Combo Box Role **/
		comboBoxRole = new JComboBox();

		/** Combo Box Location **/
		labelBoxLocation = new JLabel();

		JLabel labelPermission = new JLabel("Permission");
		
		userLocationLabel = new JLabel("N/A");
		
		labelUserPermissionValue = new JLabel("N/A");

		GroupLayout gl_panelProfileInfo = new GroupLayout(panelProfileInfo);
		gl_panelProfileInfo.setHorizontalGroup(
			gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING, false)
								.addComponent(labelLocation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(labelPermission, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
							.addGap(110)
							.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelProfileInfo.createSequentialGroup()
									.addComponent(userLocationLabel, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(labelUserPermissionValue, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
							.addComponent(labelBoxLocation, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addComponent(labelRole, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxRole, 0, 221, Short.MAX_VALUE)))
					.addGap(0))
		);
		gl_panelProfileInfo.setVerticalGroup(
			gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfileInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelRole)
						.addComponent(comboBoxRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addGap(6)
							.addComponent(labelBoxLocation, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
						.addGroup(gl_panelProfileInfo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelPermission, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelUserPermissionValue))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelProfileInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelLocation)
								.addComponent(userLocationLabel))))
					.addContainerGap())
		);
		panelProfileInfo.setLayout(gl_panelProfileInfo);
		panelProfile.setLayout(gl_panelProfile);
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Getter
	 */
	public JTextField getTimeToAlertInput() {
		return timeToAlertInput;
	}

	/**
	 * Setter
	 */
	public void setTimeToAlertInput(JTextField timeToAlertInput) {
		this.timeToAlertInput = timeToAlertInput;
	}

	/**
	 * Getter
	 */
	public JMenuItem getMntmOpen() {
		return mntmOpen;
	}

	/**
	 * Setter
	 */
	public void setMntmOpen(JMenuItem mntmOpen) {
		this.mntmOpen = mntmOpen;
	}

	/**
	 * Getter
	 */
	public JPanel getPanelView() {
		return panelView;
	}

	/**
	 * Setter
	 */
	public void setPanelView(JPanel panelView) {
		this.panelView = panelView;
	}

	/**
	 * Getting house temperature
	 */
	public JTextField getHouseTemp() {
		return houseTemp;
	}

	/**
	 * Setting house temperature
	 */
	public void setHouseTemp(JTextField houseTemp) {
		this.houseTemp = houseTemp;
	}

	/**
	 * Getting outside temperature
	 */
	public JTextField getOutsideTemp() {
		return outsideTemp;
	}

	/**
	 * Setting outside temperature
	 */
	public void setOutsideTemp(JTextField outsideTemp) {
		this.outsideTemp = outsideTemp;
	}

	/**
	 * Getting profile image
	 */
	public JLabel getLabelProfileImage() {
		return labelProfileImage;
	}

	/**
	 * Setting profile image
	 */
	public void setLabelProfileImage(String image_path) {
		labelProfileImage.setIcon(new ImageIcon(SHSGui.class.getResource(image_path)));
	}

	/**
	 * Getting textAreaConsoleLog
	 */
	public JTextArea getTextAreaConsoleLog() {
		return textAreaConsoleLog;
	}

	/**
	 * 
	 * Setting textAreaConsoleLog
	 */
	public void setTextAreaConsoleLog(JTextArea textAreaConsoleLog) {
		this.textAreaConsoleLog = textAreaConsoleLog;
	}

	/**
	 * Getter toggleButtonSimulator
	 */
	public JToggleButton getTogglebuttonSimulator() {
		return togglebuttonSimulator;
	}

	/**
	 * Setter toggleButtonSimulator
	 */
	public void setTogglebuttonSimulator(JToggleButton togglebuttonSimulator) {
		this.togglebuttonSimulator = togglebuttonSimulator;
	}

	/**
	 * getter comboBoxRole
	 */
	public JComboBox getJComboRole() {
		return comboBoxRole;
	}

	/**
	 * Getter user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * Getter the new user button
	 */
	public JButton getnewUserButton() {
		return newUser;
	}

	/**
	 * Getter new user name
	 */
	public JTextField getNewUserName() {
		return enterNewUsername;
	}

	/**
	 * Getter deleteUserButton
	 */
	public JButton getDeleteUserButton() {
		return deleteUserButton;
	}

	/**
	 * Getter comboBoxDeleteUser
	 */
	public JComboBox getDeleteUserBox() {
		return comboBoxDeleteUser;
	}

	/**
	 * Getter PressbuttonEditContext
	 */
	public JButton getPressbuttonEditContext() {
		return pressbuttonEditContext;
	}

	/**
	 * Setter PressbuttonEditContext
	 */
	public void setPressbuttonEditContext(JButton pressbuttonEditContext) {
		pressbuttonEditContext = pressbuttonEditContext;
	}

	/**
	 * Getter CurrentLocation
	 */
	public JLabel getCurrentLocation() {
		return CurrentLocation;
	}

	/**
	 * Setter CurrentLocation
	 */
	public void setCurrentLocation(JLabel currentLocation) {
		CurrentLocation = currentLocation;
	}

	/**
	 * Getter
	 */
	public JLabel getComboBoxLocation() {
		return labelBoxLocation;
	}

	/**
	 * Setter
	 */
	public void setComboBoxLocation(JLabel comboBoxLocation) {
		this.labelBoxLocation = comboBoxLocation;
	}

	/**
	 * Getter
	 */
	public JLabel getUserLocationLabel() {
		return userLocationLabel;
	}

	/**
	 * Setter
	 */
	public void setUserLocationLabel(JLabel userLocationLabel) {
		this.userLocationLabel = userLocationLabel;
	}

	/**
	 * Getter
	 */
	public JSpinner getTimeSpinner() {
		return timeSpinner;
	}

	/**
	 * Setter
	 */
	public void setTimeSpinner(JSpinner timeSpinner) {
		this.timeSpinner = timeSpinner;
	}

	/**
	 * Getter
	 */
	public JButton getPresstimeBtn() {
		return presstimebtn;
	}

	/**
	 * Setter
	 */
	public void setPresstimeBtn(JButton presstimeBtn) {
		this.presstimebtn = presstimeBtn;
	}

	/**
	 * Getter
	 */
	public JLabel getIndoorHouseTempValue() {
		return indoorHouseTempValue;
	}

	/**
	 * Setter
	 */
	public void setIndoorHouseTempValue(JLabel indoorHouseTempValue) {
		this.indoorHouseTempValue = indoorHouseTempValue;
	}

	/**
	 * Getter
	 */
	public JLabel getOutdoorTemperatureValue() {
		return outdoorTemperatureValue;
	}

	/**
	 * Setter
	 */
	public void setOutdoorTemperatureValue(JLabel outdoorTemperatureValue) {
		this.outdoorTemperatureValue = outdoorTemperatureValue;
	}

	/**
	 * Getter
	 */
	public JLabel getWeatherValue() {
		return weatherValue;
	}

	/**
	 * Setter
	 */
	public void setWeatherValue(JLabel weatherValue) {
		this.weatherValue = weatherValue;
	}

	/**
	 * Getter
	 */
	public JLabel getTimeValue() {
		return timeValue;
	}

	/**
	 * Setter
	 */
	public void setTimeValue(JLabel timeValue) {
		this.timeValue = timeValue;
	}

	/**
	 * Getter
	 */
	public JLabel getDateValue() {
		return dateValue;
	}

	/**
	 * Setter
	 */
	public void setDateValue(JLabel dateValue) {
		this.dateValue = dateValue;
	}

	/**
	 * Getter
	 */
	public JComboBox getComboBoxWeather() {
		return comboBoxWeather;
	}

	/**
	 * Setter
	 */
	public void setComboBoxWeather(JComboBox comboBoxWeather) {
		this.comboBoxWeather = comboBoxWeather;
	}

	/**
	 * Getter
	 */
	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	/**
	 * Setter
	 */
	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	/**
	 * Getter
	 */
	public JComboBox getDoorsComboBox() {
		return doorsComboBox;
	}

	/**
	 * Setter
	 */
	public void setDoorsComboBox(JComboBox doorsComboBox) {
		this.doorsComboBox = doorsComboBox;
	}

	/**
	 * Getter
	 */
	public JButton getOpenDoorsButton() {
		return OpenDoorsButton;
	}

	/**
	 * Setter
	 */
	public void setOpenDoorsButton(JButton openDoorsButton) {
		OpenDoorsButton = openDoorsButton;
	}

	/**
	 * Getter
	 */
	public JComboBox getLightsComboBox() {
		return lightsComboBox;
	}

	public JMenuItem getMntmSave() {
		return mntmSave;
	}

	/**
	 * Setter
	 */
	public void setLightsComboBox(JComboBox lightsComboBox) {
		this.lightsComboBox = lightsComboBox;
	}

	public void setMntmSave(JMenuItem mntmSave) {
		this.mntmSave = mntmSave;
	}

	/**
	 * Getter
	 */
	public JButton getLightsButton() {
		return LightsButton;
	}

	public JMenuItem getMntmLoad() {
		return mntmLoad;
	}

	/**
	 * Setter
	 */
	public void setLightsButton(JButton lightsButton) {
		LightsButton = lightsButton;
	}

	public void setMntmLoad(JMenuItem mntmLoad) {
		this.mntmLoad = mntmLoad;
	}

	/**
	 * Getter
	 */
	public JComboBox getOpenWindowsComboBox() {
		return OpenWindowsComboBox;
	}

	public JComboBox getComboBoxPermission() {
		return comboBoxPermission;
	}

	/**
	 * Setter
	 */
	public void setOpenWindowsComboBox(JComboBox openWindowsComboBox) {
		OpenWindowsComboBox = openWindowsComboBox;
	}

	public void setComboBoxPermission(JComboBox comboBoxPermission) {
		this.comboBoxPermission = comboBoxPermission;
	}

	/**
	 * Getter
	 */
	public JButton getOpenWindowsButton() {
		return openWindowsButton;
	}

	public JLabel getLabelUserPermissionValue() {
		return labelUserPermissionValue;

	}

	/**
	 * Setter
	 */
	public void setOpenWindowsButton(JButton openWindowsButton) {
		this.openWindowsButton = openWindowsButton;
	}

	public void setLabelUserPermissionValue(JLabel labelUserPermissionValue) {
		this.labelUserPermissionValue = labelUserPermissionValue;
	}

	/**
	 * Getter
	 */
	public JToggleButton getAutoModeToggleButton() {
		return AutoModeToggleButton;
	}

	public JSlider getSlider() {
		return slider;
	}

	/**
	 * Setter
	 */
	public void setAutoModeToggleButton(JToggleButton autoModeToggleButton) {
		AutoModeToggleButton = autoModeToggleButton;
	}

	/**
	 * Getter
	 */
	public JButton getLockDoorsButton() {
		return lockDoorsButton;
	}

	/**
	 * Setter
	 */
	public void setLockDoorsButton(JButton lockDoorsButton) {
		this.lockDoorsButton = lockDoorsButton;
	}

	/**
	 * Setter
	 */
	public void setSlider(JSlider slider) {
		this.slider = slider;
	}

	/**
	 * Getter
	 */
	public JToggleButton getAwayModeToggleButton() {
		return AwayModeToggleButton;
	}

	/**
	 * Setter
	 */
	public void setAwayModeToggleButton(JToggleButton awayModeToggleButton) {
		AwayModeToggleButton = awayModeToggleButton;
	}	
}