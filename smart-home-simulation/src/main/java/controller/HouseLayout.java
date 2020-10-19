package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.ContextSimulation;

public class HouseLayout extends JPanel {
	private BufferedImage closedWindow;
	private BufferedImage openedWindow;
	private BufferedImage closedDoor;
	private BufferedImage openedDoor;
	private BufferedImage lightOff;
	private BufferedImage lightOn;
	private BufferedImage userImage;
	private BufferedImage blocked;
	private ContextSimulation context;

	public void drawRooms(Graphics g) {
		try {
			int offSet = 0;
			int countOutside = 0;
			int countHallway = 0;
			int countL = 0;
			userImage = ImageIO.read(getClass().getResource("/resources/user.jpg"));
			Users users = new Users();
			ArrayList<Users> usersArray = users.getUserList();
			ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
			for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
				if (i == 0) {
					g.drawRect(0, 0, 400, 100);
					g.drawString("Outside", 25, 20);
					int x = 25, y = 30;
					for (int j = 0; j < usersArray.size(); j++) {
						if (usersArray.get(j).getLocation().equals("Outside")) {
							if (countOutside == 0) {
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.WHITE);
								g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 9, y + 15);
							}
							if (countOutside > 0) {
								x += 30;
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.WHITE);
								g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 9, y + 15);
							}
							countOutside++;
						}
					}
					g.setColor(Color.BLACK);
					g.translate(0, 100);
				}
				if (i < 4) {
					drawRoom(g, rjFile.getRoomArray().get(i).toString());
					int x = 40, y = 70;
					for (int j = 0; j < usersArray.size(); j++) {
						if (rjFile.getRoomArray().get(i).toString().equals(usersArray.get(j).getLocation())) {
							if (countL == 0) {
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.WHITE);
								g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 15);
							}
							if (countL > 0) {
								if (x + 30 <= 125) x += 30;
								else if(y - 30 >= 0) y -= 30;
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.WHITE);
								g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 15);
							}
							countL++;
						}
					}
					// draw rooms on the left
					countL = 0;
					g.setColor(Color.BLACK);
					g.translate(0, 100);
				}
				if (i == 4) {
					g.translate(150, -400);
					g.drawRect(0, 0, 100, 400);
					g.drawString("Hallway", 40, 200);
					int x = 5, y = 20;
					for (int j = 0; j < usersArray.size(); j++) {
						if (("Hallway").equals(usersArray.get(j).getLocation())) {
							if (countHallway == 0) {
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.WHITE);
								g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 15);
							}
							if (countHallway > 0) {
								y += 30;
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.WHITE);
								g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 15);
							}
							countHallway++;
						}
					}
					g.setColor(Color.BLACK);
					g.translate(100, 0);
				}
				if (i >= 4) {
					drawRoom(g, rjFile.getRoomArray().get(i).toString());
					int x = 40, y = 70;
					for (int j = 0; j < usersArray.size(); j++) {
						if (rjFile.getRoomArray().get(i).toString().equals(usersArray.get(j).getLocation())) {
							if (countL == 0) {
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.WHITE);
								g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 15);
							}
							if (countL > 0) {
								if (x + 30 <= 125) x += 30;
								else if(y - 30 >= 0) y -= 30;
								g.drawImage(userImage, x, y, 25, 25, null);
								g.setColor(Color.WHITE);
								g.drawString(Integer.toString(usersArray.get(j).getUserNumber()), x + 8, y + 15);
							}
							countL++;
						}
					}
					countL = 0;
					g.setColor(Color.BLACK);
					g.translate(0, 100);
					offSet = offSet - 100;
				}
			}
			g.translate(-250, offSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawWindows(Graphics g) {
		Windows windows = new Windows();
		try {
			int offSet = 0;
			closedWindow = ImageIO.read(getClass().getResource("/resources/closedWindow.png"));
			openedWindow = ImageIO.read(getClass().getResourceAsStream("/resources/openedWindow.png"));
			blocked = ImageIO.read(getClass().getResourceAsStream("/resources/blocked.png"));
			ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
			for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
				if (i < 4) {
					g.drawImage(closedWindow, 0, 0, 25, 25, null);
					if (windows.getWindowList().get(i).isBlocked())
						g.drawImage(blocked, 25, 0, 25, 25, null);
					g.translate(0, 100);
				}
				if (i == 4)
					g.translate(250, -400);
				if (i >= 4) {
					g.drawImage(openedWindow, 125, 0, 25, 25, null);
					if (windows.getWindowList().get(i).isBlocked())
						g.drawImage(blocked, 25, 0, 25, 25, null);
					g.translate(0, 100);
					offSet = offSet - 100;
				}
			}
			g.translate(-250, offSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawDoors(Graphics g) {
		try {
			int offSet = 0;
			openedDoor = ImageIO.read(getClass().getResourceAsStream("/resources/openedDoor.png"));
			closedDoor = ImageIO.read(getClass().getResourceAsStream("/resources/closedDoor.png"));
			ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
			for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
				if (i < 4) {
					g.drawImage(openedDoor, 125, 0, 25, 25, null);
					g.translate(0, 100);
				}
				if (i == 4)
					g.translate(250, -400);
				if (i >= 4) {
					g.drawImage(closedDoor, 0, 0, 25, 25, null);
					g.translate(0, 100);
					offSet = offSet - 100;
				}
			}
			g.translate(-250, offSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawLights(Graphics g) {
		try {
			int offSet = 0;
			lightOff = ImageIO.read(getClass().getResourceAsStream("/resources/lightOff.png"));
			lightOn = ImageIO.read(getClass().getResourceAsStream("/resources/lightOn.png"));
			ReadingJsonFile rjFile = new ReadingJsonFile("myJSON.json");
			for (int i = 0; i < rjFile.getRoomArray().size(); i++) {
				if (i < 4) {
					g.drawImage(lightOff, 0, 75, 25, 25, null);
					g.translate(0, 100);
				}
				if (i == 4)
					g.translate(250, -400);
				if (i >= 4) {
					g.drawImage(lightOn, 0, 75, 25, 25, null);
					g.translate(0, 100);
					offSet = offSet - 100;
				}
			}
			g.translate(-250, offSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void drawRoom(Graphics g, String s) {
		g.drawRect(0, 0, 150, 100);
		g.drawString(s, 40, 50);
	}

	public void paint(Graphics g) {
		drawRooms(g);
		drawWindows(g);
		drawDoors(g);
		drawLights(g);
	}
}