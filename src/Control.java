import java.awt.Font;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;

public class Control extends JFrame{	
	
	
	//Exit Function
	public static class ExitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int reply = JOptionPane.showConfirmDialog(null, "Bạn có chắc sẽ thoát?","Text Editor", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		
	}
	
	//New Function
	public static class NewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			// TODO Auto-generated method stub
			int reply = JOptionPane.showConfirmDialog(null, "Tạo mới ?","Text Editor", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				TextEditor.getTextArea().setText("");
			}
			TextEditor.filename = null;
	 	}
	 }
	
	
	
	//Open Function
	public static class OpenListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int chooserStatus;
			JFileChooser chooser = new JFileChooser();
			chooserStatus = chooser.showOpenDialog(null);
			if (chooserStatus == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				TextEditor.filename = selectedFile.getPath();
				if (!openFile(TextEditor.filename)) {
					JOptionPane.showMessageDialog(null, "Error Reading " + TextEditor.filename, "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		private boolean openFile(String filename) {
			boolean status;
			String inputLine, editorString = "";
			try {
				File file = new File(filename);
				Scanner inputFile = new Scanner(file);
				
				while (inputFile.hasNext()) {
					inputLine = inputFile.nextLine();
					editorString += inputLine + "\n";
				}
				TextEditor.getTextArea().setText(editorString);
				inputFile.close();
				status = true;
			} catch (IOException e) {
				status = false;
				e.printStackTrace();
			}
			
			return status;
		}
		
	}

	//Save function
	public static class SaveListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int chooserStatus;
			if (e.getActionCommand() == "saveasItem" || TextEditor.filename == null) {
				TextEditor.createFileChooser();
				chooserStatus = TextEditor.getFileChooser().showSaveDialog(null);
				if (chooserStatus == JFileChooser.APPROVE_OPTION) {
					File selectedfile = TextEditor.getFileChooser().getSelectedFile();
					TextEditor.filename = selectedfile.getPath();
				}
			}
			//Save File
			if (!saveFile(TextEditor.filename)) {
				JOptionPane.showMessageDialog(null, "Error Saving " + TextEditor.filename, "Error",JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Saved " + TextEditor.filename, "Success",JOptionPane.PLAIN_MESSAGE);
			}
			
		}
		private boolean saveFile(String filename) {
			boolean status;
			String S;
			PrintWriter outputFile;
			
			
			try {
				//open the file
				outputFile = new PrintWriter(filename);
			
				S = TextEditor.getTextArea().getText();
				outputFile.println(S);//write content to a file
				outputFile.close();
				
				status = true;
			} catch (IOException e) {
				// TODO: handle exception
				status = false;
				e.printStackTrace();
			}
			return status;
			
		
		}
		
	}	
	
	//Check boxes in menuFont Listener
	public static class CheckBoxListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			
			int fontSize = TextEditor.getTextArea().getFont().getSize();
			
			String selectedText = TextEditor.getTextArea().getSelectedText();
			String editorString;
			if (TextEditor.getMonospaced().isSelected()) {
				TextEditor.getTextArea().setFont(new Font("Monospaced",Font.PLAIN,fontSize));
			}
			
			if (TextEditor.getSerif().isSelected()) {
				TextEditor.getTextArea().setFont(new Font("Serif",Font.PLAIN,fontSize));
			}
			
			if (TextEditor.getSanSerif().isSelected()) {
				TextEditor.getTextArea().setFont(new Font("sanSerif",Font.PLAIN,fontSize));
			}
			
			String font = TextEditor.getTextArea().getFont().getFamily();
			
			if (TextEditor.getBold().isSelected()) {
				if (TextEditor.getItalic().isSelected()) {
					TextEditor.getTextArea().setFont(new Font(font, Font.ITALIC + Font.BOLD, fontSize));
				} else {
					TextEditor.getTextArea().setFont(new Font(font, Font.BOLD,fontSize));
				} 
			}
			else if (TextEditor.getItalic().isSelected()) {
				TextEditor.getTextArea().setFont(new Font(font,Font.ITALIC,fontSize));		
			} else TextEditor.getTextArea().setFont(new Font(font,Font.PLAIN,fontSize));
		}
	}
	
	public static class HighlightListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Highlighter highlight = TextEditor.getTextArea().getHighlighter();
			Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
			
			String selectedText = TextEditor.getTextArea().getSelectedText();
			
			int pos1,pos2;
			pos1 = TextEditor.getTextArea().getSelectionStart();
			pos2 = pos1+selectedText.length();
						
			try {
				highlight.addHighlight(pos1, pos2, painter);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public static class ZoomListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			String font = TextEditor.getTextArea().getFont().getFamily();
			int fontStyle = TextEditor.getTextArea().getFont().getStyle();
			
			if (e.getKeyCode() == KeyEvent.VK_F1) {
				TextEditor.getTextArea().setFont(new Font(font, fontStyle, TextEditor.getTextArea().getFont().getSize()+2));
			}
			if (e.getKeyCode() == KeyEvent.VK_F2) {
				TextEditor.getTextArea().setFont(new Font(font, fontStyle, TextEditor.getTextArea().getFont().getSize()-2));
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
