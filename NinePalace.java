package com.penguinviceroy.ninepalace.java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Random;


/**
 * �Ұ�JAVA JAVA����
 * @author ˮ����
 * @version 1.0
 * �Ұ�JAVA JAVA����
 */
public class NinePalace implements MouseMotionListener, MouseListener{

    int[][] map = {
			{5,5,5},
			{5,5,5},
			{5,5,5}
	};

	JFrame jf = new JFrame("�Ź����ս");
	JButton[][] button = new JButton[3][3];


	public void init() {
		jf.setLayout(null);		//�Զ��岼��
		jf.setSize(660, 680);
		jf.setResizable(false); //�̶���С
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initButton();

	}

	public void initButton() {
		int x = 10;
		int y = 10; 	//λ�ö���
		for(int i=0; i<button.length; i++) {
			for(int j=0; j<button[i].length; j++) {
				button[i][j] = new JButton();
				button[i][j].setBounds(x,y,200,200); //����λ��
				jf.add(button[i][j]);
				button[i][j].addMouseListener(this);
				button[i][j].addMouseMotionListener(this); //���Ǻ��˽�this��������˼����������֪
				x += 210;
			}
			x = 10;
			y += 210;
		}
		jf.setVisible(true);
	}




	//����
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

	//����ƶ�����δ���ʱ
	JButton tbutton = new JButton();
	public void mouseMoved(MouseEvent e) {
		if(tbutton.getText()=="ѡ�����"||tbutton.getText()=="")
			tbutton.setText("");
		tbutton = (JButton)e.getSource();
		if(tbutton.getText()=="ѡ�����"||tbutton.getText()=="")
			tbutton.setText("ѡ�����");

	}

	Random rand = new Random();
	int time = 1;
	//�������
	public void mousePressed(MouseEvent e) {

		if(tbutton.getText()!="RIVAL"&&tbutton.getText()!="YOU") {

			tbutton.setText("YOU");
			//���Ӻ�궨λ��_ͨ���߼�����
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
			//�ж�
			if(Over(ti,tj)==true) {
				JOptionPane.showMessageDialog(jf, "�����ʤ!", "������",JOptionPane.INFORMATION_MESSAGE);

				System.exit(0);
			}

		//����������
			boolean flag = true;
			int ri = 0;
			int rj = 0;
			while(flag) {
				ri = (int)rand.nextInt(3);
				rj = (int)rand.nextInt(3);
				if(time>=9)		//����ѭ��
					break;
				if(button[ri][rj].getText()!="YOU"&&button[ri][rj].getText()!="RIVAL") {
					button[ri][rj].setText("RIVAL");
					map[ri][rj] = 0;
					flag = false;
					time++;
				}
			}
			if(Over(ri,rj)==true) {
				JOptionPane.showMessageDialog(jf, "������ʤ", "������",JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}
	}
	//�ж���Ӯ
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