package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.*;

import fileManager.MoveFile;

public class MainUI {
	public static void main(String[] args){
		MainWindow window = new MainWindow();
	}
}

class MainWindow extends JFrame implements ActionListener{
	AddPath src, dst, target;
	JTextField srcPath, dstPath, tarName;
	JButton confirm, clear;
	JFileChooser fileChsr;
	Warning error;
	public MainWindow(){
		Container con = getContentPane();
		con.setLayout(new GridLayout(4,2));
		src = new AddPath("Source:");
		dst = new AddPath("Destination:");
		target = new AddPath("Target:");
		srcPath = new JTextField(20);
		dstPath = new JTextField(20);
		tarName = new JTextField(20);
		confirm = new JButton("Confirm");
		clear = new JButton("Clear");
		fileChsr = new JFileChooser("E:\\");
		error = new Warning(this, "Wrong Path.");
		src.button.addActionListener(this);
		dst.button.addActionListener(this);
		target.button.addActionListener(this);
		confirm.addActionListener(this);
		clear.addActionListener(this);
		error.button.addActionListener(this);
		con.add(src);
		con.add(srcPath);
		con.add(dst);
		con.add(dstPath);
		con.add(target);
		con.add(tarName);
		con.add(confirm);
		con.add(clear);
		pack();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		int result;
		if(e.getSource() == src.button || e.getSource() == dst.button){
			fileChsr.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			result = fileChsr.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION){
				if(e.getSource() == src.button) 
					srcPath.setText(fileChsr.getSelectedFile().getAbsolutePath());
				else 
					dstPath.setText(fileChsr.getSelectedFile().getAbsolutePath());
			}
		}
		else if(e.getSource() == target.button){
			fileChsr.setFileSelectionMode(JFileChooser.FILES_ONLY);
			result = fileChsr.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION)
				tarName.setText(fileChsr.getSelectedFile().getName());
		}
		else if(e.getSource() == confirm){
			MoveFile move = new MoveFile();
			if(!move.checkPath(srcPath.getText(), dstPath.getText()))
				error.setVisible(true);
			else{
				move.copyFile(srcPath.getText(), dstPath.getText(), tarName.getText());
			}
		}
		else if(e.getSource() == clear){
			srcPath.setText("");
			dstPath.setText("");
			tarName.setText("");
		}
		else if(e.getSource() == error.button){
			error.setVisible(false);
		}
		else{}
	}
}

class AddPath extends JPanel{
	JLabel label;
	JButton button;
	public AddPath(String s){
		setLayout(new BorderLayout());
		label = new JLabel(s, JLabel.CENTER);
		button = new JButton("+");
		add(button, "East");
		add(label, "West");
	}
}

class Warning extends JDialog{
	JLabel label;
	JButton button;
	public Warning(JFrame parent, String s){
		super();
//		setSize(300, 100);
		Container con = getContentPane();
		label = new JLabel(s, JLabel.CENTER);
		button = new JButton("OK");
		con.setLayout(new GridLayout(2,1));
		con.add(label);
		con.add(button);
		pack();
	}
}