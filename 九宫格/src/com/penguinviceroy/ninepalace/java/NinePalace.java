package com.penguinviceroy.ninepalace.java;

import java.util.Scanner;


/*
 *      000
 *      000
 *      000
 *
 *      由于区间的整齐性考虑采用二维数组定义区间。
 *      游戏过程：
 *      	1.打印启动界面——选择优先顺序——3种选择
 *      	刷新
 *      	2.打印游戏区域——可以考虑一下放大？
 *      	以机器人开始为例
 *      	3.机器人选择位置
 *      	刷新
 *      	4.玩家选择位置
 *      	刷新
 *      	5.机器人选择位置——其中得有判断机制
 *      	刷新
 *      	……………………刷新时判断是否游戏结束
 *
 */
public class NinePalace {
	//二维数组存放地图
	static char[][] map = {
			{'*','*','*'},
			{'*','*','*'},
			{'*','*','*'}
	};
	//二维数组存放逻辑地图
	static int[][] lmap = {
			{0,0,0},
			{0,0,0},
			{0,0,0}
	};
	static boolean whoWin ;
	//打印地图
	static void Print() {
		for(char[] row : map) {
			if(row==map[1]||row==map[2])
				System.out.println();
			for(char s : row) {
				System.out.print(s);
			}
		}
	}
	//光标移动，回车来放置旗子
	//控制光标回到初始位置——以便于重新打印
	//游戏逻辑控制
	static boolean isOver() {
		//对角线
		if( (lmap[0][0]==1&&lmap[1][1]==1&&lmap[2][2]==1) && (lmap[0][2]==1&&lmap[1][1]==1&&lmap[2][0]==1) ) {
			whoWin = true;
			return  false;
		}

		//行
		for( int i=0; i<=2; i++) {
			if(lmap[i][0]==1&&lmap[i][1]==1&&lmap[i][2]==1){
				whoWin = true;
				return  false;
			}

		}
		//列
		for( int j=0; j<=2; j++) {
			if(lmap[0][j]==1&&lmap[1][j]==1&&lmap[2][j]==1){
				whoWin = true;
				return  false;
			}

		}
		//对角线
		if( (lmap[0][0]==2&&lmap[1][1]==2&&lmap[2][2]==2) && (lmap[0][2]==2&&lmap[1][1]==2&&lmap[2][0]==2) ){
			whoWin = false;
			return  false;
		}

		//行
		for( int i=0; i<=2; i++) {
			if(lmap[i][0]==2&&lmap[i][1]==2&&lmap[i][2]==2){
				whoWin = false;
				return  false;
			}

		}
		//列
		for( int j=0; j<=2; j++) {
			if(lmap[0][j]==2&&lmap[1][j]==2&&lmap[2][j]==2){
				whoWin = false;
				return  false;
			}

		}
		return true;
	}
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("     九宫格大战");
		System.out.println("请选择先发顺序。输入‘0’代表先手；输入‘1’代表后手");
		int order = scanner.nextInt();

		Print();

		scanner.close();
	}


}
