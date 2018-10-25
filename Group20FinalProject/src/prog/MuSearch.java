package prog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Stopwatch.Stopwatch;
import datatypes.Recommendation;
import datatypes.Song;
import datatypes.Songs;
import graph.Graph;
import helper.IdGetter;
import helper.RecommendSongsDFS;
import read.Read;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JEditorPane;
import javax.swing.JProgressBar;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JList;
import java.awt.SystemColor;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * GUI for MuSearch
 */

public class MuSearch {

	private JFrame frmMusearch;
	private JTextField SongName;
	private JTextField ArtistName;
	private JLabel icon;
	private JTextField Top10;
	private JTextField Top9;
	private JTextField Top8;
	private JTextField Top7;
	private JTextField Top6;
	private JTextField Top5;
	private JTextField Top4;
	private JTextField Top3;
	private JTextField Top2;
	private JTextField Top1;
	private JLabel lblNewLabel_1;
	private JLabel lblArtist;
	private JLabel lblChooseASong;
	private JTextField songSelection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MuSearch window = new MuSearch();
					window.frmMusearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MuSearch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String filename = "train_triplets.txt";
		int lines = 1000000; //number of lines to read
		
		System.out.println("Reading songs...");
		Songs songs = Read.readSongs();
		
		Graph g = new Graph(filename, lines); //creates data structure
		System.out.println(g.V());
		IdGetter getter = new IdGetter(songs, g);
		
		System.out.println("Done\n");
		
		frmMusearch = new JFrame();
		frmMusearch.getContentPane().setBackground(Color.WHITE);
		frmMusearch.setTitle("MuSearch");
		frmMusearch.setBounds(100, 100, 771, 713);
		frmMusearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMusearch.getContentPane().setLayout(null);
		
		JLabel lblANewWay = new JLabel("A new way of recommending music...");
		lblANewWay.setFont(new Font("Rockwell", Font.BOLD, 16));
		lblANewWay.setHorizontalAlignment(SwingConstants.CENTER);
		lblANewWay.setBounds(236, 145, 306, 22);
		frmMusearch.getContentPane().add(lblANewWay);
		
		SongName = new JTextField();
		SongName.setBackground(new Color(135, 206, 235));
		SongName.setHorizontalAlignment(SwingConstants.CENTER);
		SongName.setBounds(258, 173, 243, 22);
		frmMusearch.getContentPane().add(SongName);
		SongName.setColumns(10);
		
		ArtistName = new JTextField();
		ArtistName.setBackground(new Color(135, 206, 235));
		ArtistName.setHorizontalAlignment(SwingConstants.CENTER);
		ArtistName.setBounds(258, 218, 243, 22);
		frmMusearch.getContentPane().add(ArtistName);
		ArtistName.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Song = "";
				String Artist = "";
				try {
					if (SongName.getText().equals("") && ArtistName.getText().equals("")) {
						Top1.setText("Please input a song and/or an artist name");
						Top1.setEnabled(true);
						Top2.setText("");
						Top3.setText("");
						Top4.setText("");
						Top5.setText("");
						Top6.setText("");
						Top7.setText("");
						Top8.setText("");
						Top9.setText("");
						Top10.setText("");
						
						frmMusearch.getContentPane().add(Top1);
					}
					else {
						Song = String.format(SongName.getText(), e.getActionCommand());
						Artist = String.format(ArtistName.getText(), e.getActionCommand());
						
						String SongID = (String) getter.getId(Song, Artist);
						RecommendSongsDFS r = new RecommendSongsDFS(g, SongID);
						Recommendation[] R = new Recommendation[10];
						int count = 0;
						for (Recommendation i : r.getTopN(10)) {
							R[count] = i; 
							count++;
						}
						Top1.setText("1. " + songs.getById(R[0].name()) + ", " + R[0].count());
						Top2.setText("2. " + songs.getById(R[1].name()) + ", " + R[1].count());
						Top3.setText("3. " + songs.getById(R[2].name()) + ", " + R[2].count());
						Top4.setText("4. " + songs.getById(R[3].name()) + ", " + R[3].count());
						Top5.setText("5. " + songs.getById(R[4].name()) + ", " + R[4].count());
						Top6.setText("6. " + songs.getById(R[5].name()) + ", " + R[5].count());
						Top7.setText("7. " + songs.getById(R[6].name()) + ", " + R[6].count());
						Top8.setText("8. " + songs.getById(R[7].name()) + ", " + R[7].count());
						Top9.setText("9. " + songs.getById(R[8].name()) + ", " + R[8].count());
						Top10.setText("10. " + songs.getById(R[9].name()) + ", " + R[9].count());
						
						Top1.setEnabled(true);
						Top2.setEnabled(true);
						Top3.setEnabled(true);
						Top4.setEnabled(true);
						Top5.setEnabled(true);
						Top6.setEnabled(true);
						Top7.setEnabled(true);
						Top8.setEnabled(true);
						Top9.setEnabled(true);
						Top10.setEnabled(true);
						
					}
				} catch (Exception except) {
					// TODO: handle exception
				}
			}
		});
		btnSearch.setBounds(306, 253, 130, 25);
		frmMusearch.getContentPane().add(btnSearch);
		
		icon = new JLabel("");
		Image icon_label = new ImageIcon(this.getClass().getResource("/MuSearch_ICON.png")).getImage();
		icon.setIcon(new ImageIcon(icon_label));
		icon.setBounds(317, 13, 119, 131);
		frmMusearch.getContentPane().add(icon);
		
		Top1 = new JTextField();
		Top1.setBackground(new Color(135, 206, 235));
		Top1.setEnabled(false);
		Top1.setForeground(Color.BLACK);
		Top1.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top1.setColumns(10);
		Top1.setBounds(12, 403, 725, 25);
		frmMusearch.getContentPane().add(Top1);
		
		Top2 = new JTextField();
		Top2.setBackground(new Color(135, 206, 235));
		Top2.setEnabled(false);
		Top2.setForeground(Color.BLACK);
		Top2.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top2.setColumns(10);
		Top2.setBounds(12, 428, 725, 25);
		frmMusearch.getContentPane().add(Top2);
		
		Top3 = new JTextField();
		Top3.setBackground(new Color(135, 206, 235));
		Top3.setEnabled(false);
		Top3.setForeground(Color.BLACK);
		Top3.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top3.setColumns(10);
		Top3.setBounds(12, 453, 725, 25);
		frmMusearch.getContentPane().add(Top3);
		
		Top4 = new JTextField();
		Top4.setBackground(new Color(135, 206, 235));
		Top4.setEnabled(false);
		Top4.setForeground(Color.BLACK);
		Top4.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top4.setColumns(10);
		Top4.setBounds(12, 478, 725, 25);
		frmMusearch.getContentPane().add(Top4);
		
		Top5 = new JTextField();
		Top5.setBackground(new Color(135, 206, 235));
		Top5.setEnabled(false);
		Top5.setForeground(Color.BLACK);
		Top5.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top5.setColumns(10);
		Top5.setBounds(12, 503, 725, 25);
		frmMusearch.getContentPane().add(Top5);
		
		Top6 = new JTextField();
		Top6.setBackground(new Color(135, 206, 235));
		Top6.setEnabled(false);
		Top6.setForeground(Color.BLACK);
		Top6.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top6.setColumns(10);
		Top6.setBounds(12, 528, 725, 25);
		frmMusearch.getContentPane().add(Top6);
		
		Top7 = new JTextField();
		Top7.setBackground(new Color(135, 206, 235));
		Top7.setEnabled(false);
		Top7.setForeground(Color.BLACK);
		Top7.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top7.setColumns(10);
		Top7.setBounds(12, 553, 725, 25);
		frmMusearch.getContentPane().add(Top7);
		
		Top8 = new JTextField();
		Top8.setBackground(new Color(135, 206, 235));
		Top8.setEnabled(false);
		Top8.setForeground(Color.BLACK);
		Top8.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top8.setColumns(10);
		Top8.setBounds(12, 578, 725, 25);
		frmMusearch.getContentPane().add(Top8);
		
		Top9 = new JTextField();
		Top9.setBackground(new Color(135, 206, 235));
		Top9.setEnabled(false);
		Top9.setForeground(Color.BLACK);
		Top9.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top9.setColumns(10);
		Top9.setBounds(12, 603, 725, 25);
		frmMusearch.getContentPane().add(Top9);
		
		Top10 = new JTextField();
		Top10.setBackground(new Color(135, 206, 235));
		Top10.setEnabled(false);
		Top10.setForeground(Color.BLACK);
		Top10.setFont(new Font("Rockwell", Font.PLAIN, 15));
		Top10.setColumns(10);
		Top10.setBounds(12, 628, 725, 25);
		frmMusearch.getContentPane().add(Top10);
		
		JLabel lblNewLabel = new JLabel("Recommendations");
		lblNewLabel.setFont(new Font("Rockwell", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(258, 372, 243, 30);
		frmMusearch.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Song:");
		lblNewLabel_1.setFont(new Font("Rockwell", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(202, 171, 44, 25);
		frmMusearch.getContentPane().add(lblNewLabel_1);
		
		lblArtist = new JLabel("Artist:");
		lblArtist.setFont(new Font("Rockwell", Font.PLAIN, 15));
		lblArtist.setBounds(202, 218, 44, 25);
		frmMusearch.getContentPane().add(lblArtist);
		
		lblChooseASong = new JLabel("Choose a Song");
		lblChooseASong.setFont(new Font("Rockwell", Font.BOLD, 15));
		lblChooseASong.setBounds(316, 301, 120, 19);
		frmMusearch.getContentPane().add(lblChooseASong);
		
		songSelection = new JTextField();
		songSelection.setText("After pressing search, go to console to pick a song");
		songSelection.setHorizontalAlignment(SwingConstants.CENTER);
		songSelection.setColumns(10);
		songSelection.setBackground(new Color(135, 206, 235));
		songSelection.setBounds(224, 321, 306, 22);
		frmMusearch.getContentPane().add(songSelection);
	}
}
