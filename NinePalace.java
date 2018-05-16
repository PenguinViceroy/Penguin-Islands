package com.penguinviceroy.ninepalace.java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Random;


/**
 * 我爱JAVA JAVA爱我
 * @author 水方子
 * @version 1.0
 * 我爱JAVA JAVA爱我
 */
public class NinePalace implements MouseMotionListener, MouseListener{

    int[][] map = {
			{5,5,5},
			{5,5,5},
			{5,5,5}
	};

	JFrame jf = new JFrame("九宫格大战");
	JButton[][] button = new JButton[3][3];


	public void init() {
		jf.setLayout(null);		//自定义布局
		jf.setSize(660, 680);
		jf.setResizable(false); //固定大小
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initButton();

	}

	public void initButton() {
		int x = 10;
		int y = 10; 	//位置定义
		for(int i=0; i<button.length; i++) {
			for(int j=0; j<button[i].length; j++) {
				button[i][j] = new JButton();
				button[i][j].setBounds(x,y,200,200); //设置位置
				jf.add(button[i][j]);
				button[i][j].addMouseListener(this);
				button[i][j].addMouseMotionListener(this); //不是很了解this，大致意思是设置鼠标感知
				x += 210;
			}
			x = 10;
			y += 210;
		}
		jf.setVisible(true);
	}




	//必需
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }

	//鼠标移动但尚未点击时
	JButton tbutton = new JButton();
	public void mouseMoved(MouseEvent e) {
		if(tbutton.getText()=="选择这里？"||tbutton.getText()=="")
			tbutton.setText("");
		tbutton = (JButton)e.getSource();
		if(tbutton.getText()=="选择这里？"||tbutton.getText()=="")
			tbutton.setText("选择这里？");

	}

	Random rand = new Random();
	int time = 1;
	//点击落子
	public void mousePressed(MouseEvent e) {

		if(tbutton.getText()!="RIVAL"&&tbutton.getText()!="YOU") {

			tbutton.setText("YOU");
			//落子后标定位置_通过逻辑数组
			int i = 0;
			int j = 0;
			int ti = 0;
			int tj = 0;
			for(i=0; i<3; i++) {
				for(j=0; j<3; j++) {
					if(button[i][j]==tbutton) {
						map[i][j] = 1;
						time++;
						ti = i;
						tj = j;
					}
				}
			}
			//判断
			if(Over(ti,tj)==true) {
				JOptionPane.showMessageDialog(jf, "人类获胜!", "见分晓",JOptionPane.INFORMATION_MESSAGE);

				System.exit(0);
			}

		//机器人落子
			boolean flag = true;
			int ri = 0;
			int rj = 0;
			while(flag) {
				ri = (int)rand.nextInt(3);
				rj = (int)rand.nextInt(3);
				if(time>=9)		//防死循环
					break;
				if(button[ri][rj].getText()!="YOU"&&button[ri][rj].getText()!="RIVAL") {
					button[ri][rj].setText("RIVAL");
					map[ri][rj] = 0;
					flag = false;
					time++;
				}
			}
			if(Over(ri,rj)==true) {
				JOptionPane.showMessageDialog(jf, "机器获胜", "见分晓",JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
	}
	//判断输赢
    public boolean Over (int i, int j) {
		if(i==j) {
			if(map[0][0]+map[1][1]+map[2][2]==3)
				return true;
			if(map[0][0]+map[1][1]+map[2][2]==0)
				return true;
		}
		if(i+j==2) {
			if(map[0][2]+map[1][1]+map[2][0]==3)
				return true;
			if(map[0][2]+map[1][1]+map[2][0]==0)
				return true;
		}
		if((i>=0&&i<=2)&&(j>=0&&j<=2)) {
			if(map[i][0]+map[i][1]+map[i][2]==3)
				return true;
			if(map[0][j]+map[1][j]+map[2][j]==3)
				return true;
			if(map[i][0]+map[i][1]+map[i][2]==0)
				return true;
			if(map[0][j]+map[1][j]+map[2][j]==0)
				return true;
		}
			return false;
	}

	public static void main(String[] args) {
		new NinePalace().init();
	}
}