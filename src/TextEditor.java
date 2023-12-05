import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor extends JFrame{

	private static JMenuBar menubar;
	
	private static JMenu menuFile;
	private static JMenu menuFont;
	
	//Items in menuFile
	private static JMenuItem newItem;
	private static JMenuItem openItem;
	private static JMenuItem saveItem;
	private static JMenuItem saveasItem;
	private static JMenuItem exitItem;
	private static JMenuItem HighLight;
	
	//Items in menuFont
	private static JRadioButtonMenuItem Monospaced;
	private static JRadioButtonMenuItem Serif;
	private static JRadioButtonMenuItem sanSerif;
	private static JCheckBoxMenuItem Italic;
	private static JCheckBoxMenuItem Bold;
	

	private static JTextArea textArea;
	private static JFileChooser fileChooser;
	
	public static File file;
	public static String filename;
	private static JFileChooser fileOpen;
	private static JFileChooser fileSave;
	
	public TextEditor() {
		setLooknFeel();
		setTitle("Text Editor");
		setPreferredSize(new Dimension(450,450));
		setLayout(new BorderLayout());
		
		
		setupMenu();
		
		textArea = new JTextArea();
		add(textArea);
		textArea.addKeyListener(new Control.ZoomListener());
		textArea.setFont(new Font("Serif", Font.PLAIN,12));
		
		pack();
		setVisible(true);
		
	}
	
	private void setLooknFeel() {
		//set Look n Feels
		try {	  
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e) {
			System.out.println("Look and Feel is not set!");
		}
	}

	private void setupMenu() {
		menubar = new JMenuBar();
		
		menuFile = new JMenu("File");
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuFont = new JMenu("Font");
		menuFont.setMnemonic(KeyEvent.VK_T);
		
		menubar.add(menuFile);
		menubar.add(menuFont);
		
		//Item in menuFile
		newItem = new JMenuItem("New");
		newItem.setMnemonic(KeyEvent.VK_N);
		newItem.addActionListener(new Control.NewListener());
		
		openItem = new JMenuItem("Open");
		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.addActionListener(new Control.OpenListener());
		
		saveItem = new JMenuItem("Save");
		saveItem.setMnemonic(KeyEvent.VK_S);
		saveItem.addActionListener(new Control.SaveListener());
		
		saveasItem = new JMenuItem("Save As..");
		saveasItem.setMnemonic(KeyEvent.VK_A);
		saveasItem.addActionListener(new Control.SaveListener());
		
		exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_E);
		exitItem.addActionListener(new Control.ExitListener());
		
		//adding Items into menuFile
		menuFile.add(newItem);
		menuFile.add(openItem);
		menuFile.add(saveItem);
		menuFile.add(saveasItem);
		menuFile.add(exitItem);
		
		//Item in menuFont
		Monospaced = new JRadioButtonMenuItem("Monospaced");
		Monospaced.addItemListener(new Control.CheckBoxListener());
		Serif = new JRadioButtonMenuItem("Serif");
		Serif.addItemListener(new Control.CheckBoxListener());	
		sanSerif = new JRadioButtonMenuItem("SanSerif");	
		sanSerif.addItemListener(new Control.CheckBoxListener());
		Italic = new JCheckBoxMenuItem("Italic");
		Italic.addItemListener(new Control.CheckBoxListener());
		Bold = new JCheckBoxMenuItem("Bold");
		Bold.addItemListener(new Control.CheckBoxListener());
		HighLight = new JMenuItem("Hightlight");
		HighLight.addActionListener(new Control.HighlightListener());
		
		//Group RadioButton
		ButtonGroup group = new ButtonGroup();
		group.add(Monospaced);
		group.add(Serif);
		group.add(sanSerif);
		
		//adding Item to menuFont
		menuFont.add(Monospaced);	
		menuFont.add(Serif);
		menuFont.add(sanSerif);
		menuFont.addSeparator();
		menuFont.add(Italic);
		menuFont.add(Bold);
		menuFont.addSeparator();
		menuFont.add(HighLight);
	
		setJMenuBar(menubar);
		
		//create FileChoosers
		fileOpen = new JFileChooser();
		fileSave = new JFileChooser();
	}
	
	
	public static void createFileChooser() {
		fileChooser = new JFileChooser();
	}
	
	
	public static String getFilename() {
		return filename;
	}

	public static void setFilename(String filename) {
		TextEditor.filename = filename;
	}

	public static JMenuBar getMenubar() {
		return menubar;
	}

	public void setMenubar(JMenuBar menubar) {
		this.menubar = menubar;
	}

	public static JMenu getMenuFile() {
		return menuFile;
	}

	public void setMenuFile(JMenu menuFile) {
		this.menuFile = menuFile;
	}

	public static JMenu getMenuFont() {
		return menuFont;
	}

	public void setMenuFont(JMenu menuFont) {
		this.menuFont = menuFont;
	}

	public static JMenuItem getNewItem() {
		return newItem;
	}

	public void setNewItem(JMenuItem newItem) {
		this.newItem = newItem;
	}

	public static JMenuItem getOpenItem() {
		return openItem;
	}

	public void setOpenItem(JMenuItem openItem) {
		this.openItem = openItem;
	}

	public static JMenuItem getSaveItem() {
		return saveItem;
	}

	public void setSaveItem(JMenuItem saveItem) {
		this.saveItem = saveItem;
	}

	public static JMenuItem getSaveasItem() {
		return saveasItem;
	}

	public void setSaveasItem(JMenuItem saveasItem) {
		this.saveasItem = saveasItem;
	}

	public static JMenuItem getExitItem() {
		return exitItem;
	}

	public void setExitItem(JMenuItem exitItem) {
		this.exitItem = exitItem;
	}

	public static JRadioButtonMenuItem getMonospaced() {
		return Monospaced;
	}

	public void setMonospaced(JRadioButtonMenuItem monospaced) {
		Monospaced = monospaced;
	}

	public static JRadioButtonMenuItem getSerif() {
		return Serif;
	}

	public void setSerif(JRadioButtonMenuItem serif) {
		Serif = serif;
	}

	public static JRadioButtonMenuItem getSanSerif() {
		return sanSerif;
	}

	public void setSanSerif(JRadioButtonMenuItem sanSerif) {
		this.sanSerif = sanSerif;
	}

	public static JCheckBoxMenuItem getItalic() {
		return Italic;
	}

	public void setItalic(JCheckBoxMenuItem italic) {
		Italic = italic;
	}

	public static JCheckBoxMenuItem getBold() {
		return Bold;
	}

	public void setBold(JCheckBoxMenuItem bold) {
		Bold = bold;
	}

	public static JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public static JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public static File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public static JFileChooser getFileOpen() {
		return fileOpen;
	}

	public void setFileOpen(JFileChooser fileOpen) {
		this.fileOpen = fileOpen;
	}

	public static JFileChooser getFileSave() {
		return fileSave;
	}

	public void setFileSave(JFileChooser fileSave) {
		this.fileSave = fileSave;
	}

}

