package codesquad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Cube
{
	private char cube[][];
	public Cube(char[] in)
	{
		cube=new char[3][3];
		for(int i=0;i<cube.length;i++)
		{
			for(int j=0;j<cube[i].length;j++)
			{
				cube[i][j]=in[i*cube.length+j];
			}
		}
	}
	public Cube(Cube temp)
	{
		cube=new char[3][3];
		for(int i=0;i<cube.length;i++)
		{
			for(int j=0;j<cube[i].length;j++)
			{
				cube[i][j]=temp.cube[i][j];
			}
		}
	}
	public void printCube()
	{
		for(int i=0;i<cube.length;i++)
		{
			for(int j=0;j<cube[i].length;j++)
			{
				System.out.print(cube[i][j]+" ");
			}
			System.out.println();
		}
	}
	public void printCubeline(int row)
	{
		System.out.print(" ");
		System.out.print(" ");
		for(int j=0;j<cube.length;j++)
		{
			System.out.print(cube[row][j]+" ");
		}
		System.out.print(" ");
		System.out.print(" ");
	}
	public char[] upCube()
	{
		char[] word=new char[3];
		for(int i=0;i<3;i++)
		{
			word[i]=cube[0][i];
		}
		return word;
	}
	public char[] rightCube()
	{
		char[] word=new char[3];
		for(int i=0;i<3;i++)
		{
			word[i]=cube[i][2];
		}
		return word;
	}
	public char[] leftCube()
	{
		char[] word=new char[3];
		for(int i=0;i<3;i++)
		{
			word[i]=cube[i][0];
		}
		return word;
	}
	public char[] downCube()
	{
		char[] word=new char[3];
		for(int i=0;i<3;i++)
		{
			word[i]=cube[2][i];
		}
		return word;
	}
	public void set_upCube(char[] word)
	{
		for(int i=0;i<3;i++)
		{
			cube[0][i]=word[i];
		}
	}
	public void set_rightCube(char[] word)
	{
		for(int i=0;i<3;i++)
		{
			cube[i][2]=word[i];
		}
	}
	public void set_leftCube(char[] word)
	{
		for(int i=0;i<3;i++)
		{
			cube[i][0]=word[i];
		}
	}
	public void set_downCube(char[] word)
	{
		for(int i=0;i<3;i++)
		{
			cube[2][i]=word[i];
		}
	}
	public boolean equals(Cube temp)
	{
		for(int i=0;i<cube.length;i++)
		{
			for(int j=0;j<cube[i].length;j++)
			{
				if(cube[i][j]!=temp.cube[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}
}

public class Problem03 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
		long startTime=System.currentTimeMillis();
		int count=0;
		Scanner in=new Scanner(System.in);
		char[] cube_in1= {'B','B','B','B','B','B','B','B','B'};
		char[] cube_in2= {'W','W','W','W','W','W','W','W','W'};
		char[] cube_in3= {'O','O','O','O','O','O','O','O','O'};
		char[] cube_in4= {'G','G','G','G','G','G','G','G','G'};
		char[] cube_in5= {'Y','Y','Y','Y','Y','Y','Y','Y','Y'};
		char[] cube_in6= {'R','R','R','R','R','R','R','R','R'};
		Cube cube1=new Cube(cube_in1), cube2=new Cube(cube_in2), cube3=new Cube(cube_in3), cube4=new Cube(cube_in4), cube5=new Cube(cube_in5), cube6=new Cube(cube_in6);
		Cube original1=new Cube(cube1),original2=new Cube(cube2),original3=new Cube(cube3),original4=new Cube(cube4),original5=new Cube(cube5),original6=new Cube(cube6);
		printRubiksCube(cube1,cube2,cube3,cube4,cube5,cube6);
		System.out.println("무작위 섞기 메뉴 : \"Random\"");
		while(true)
		{
			System.out.print("CUBE> ");
			String input=in.nextLine();
			// 종료 시점
			if(input.equals("Q"))
			{
				System.out.println("Bye~");
				break;
			}
			if(input.equals("Random"))
			{
				shuffleCube(cube1,cube2,cube3,cube4,cube5,cube6);
				System.out.println("shuffle success.");
				continue;
			}
			count+=play(cube1,cube2,cube3,cube4,cube5,cube6,input);
			boolean check=checkSuccess(original1,original2,original3,original4,original5,original6,cube1,cube2,cube3,cube4,cube5,cube6);
			if(check)
			{
				System.out.println("Congratulations!!!");
				break;
			}
		}
		in.close();
		long endTime=System.currentTimeMillis()-startTime;
		String endTimeformat=sdf.format(new Date(endTime));
		System.out.println("경과시간: "+endTimeformat);
		System.out.println("조작갯수: "+count);
		System.out.println("이용해주셔서 감사합니다. 뚜뚜뚜.");
	}
	public static int play(Cube cube1,Cube cube2,Cube cube3,Cube cube4,Cube cube5,Cube cube6,String input)
	{	
		int count=0;
		String[] str=input.split("");
		for(int i=0;i<str.length;i++)
		{
			int tmp_index=i;
			int direction=-1;
			boolean double_flag=false;
			if(i!=str.length-1&&str[i+1].equals("2"))
			{
				double_flag=true;
				tmp_index++;
			}
			if(i!=str.length-1&&str[i+1].equals("\'"))
			{
				direction*=-1;
				tmp_index++;
				if(i!=str.length-2&&str[i+2].equals("2"))
				{
					double_flag=true;
					tmp_index+=1;
				}
			}
			if(str[i].equals("U"))
				playUp(cube2,cube3,cube4,cube5,direction,double_flag);
			else if(str[i].equals("R"))
				playRight(cube3,cube6,cube5,cube1,direction,double_flag);
			else if(str[i].equals("L"))
				playLeft(cube3,cube6,cube5,cube1,direction,double_flag);
			else if(str[i].equals("D"))
				playDown(cube2,cube3,cube4,cube5,direction,double_flag);
			else if(str[i].equals("F"))
				playFront(cube6,cube4,cube1,cube2,direction,double_flag);
			else if(str[i].equals("B"))
				playBack(cube6,cube4,cube1,cube2,direction,double_flag);
			else
			{
				System.out.println("unvalid command ["+str[i]+"]");
				break;
			}
			printRubiksCube(cube1,cube2,cube3,cube4,cube5,cube6);
			i=tmp_index;
			count++;
			if(double_flag)
				count++;
		}
		return count;
	}
	public static void playUp(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction,boolean double_flag)
	{
		moveUp(cube1,cube2,cube3,cube4,direction);
		if(double_flag)
			moveUp(cube1,cube2,cube3,cube4,direction);
		if(direction==1&&!double_flag)
			System.out.println("U\'");
		else if(direction==1&&double_flag)
			System.out.println("U\'2");
		else if(direction==-1&&!double_flag)
			System.out.println("U");
		else if(direction==-1&&double_flag)
			System.out.println("U2");
	}
	public static void playRight(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction,boolean double_flag)
	{
		moveRight(cube1,cube2,cube3,cube4,direction);
		if(double_flag)
			moveRight(cube1,cube2,cube3,cube4,direction);
		if(direction==1&&!double_flag)
			System.out.println("R\'");
		else if(direction==1&&double_flag)
			System.out.println("R\'2");
		else if(direction==-1&&!double_flag)
			System.out.println("R");
		else if(direction==-1&&double_flag)
			System.out.println("R2");
	}
	public static void playLeft(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction,boolean double_flag)
	{
		moveLeft(cube1,cube2,cube3,cube4,direction);
		if(double_flag)
			moveLeft(cube1,cube2,cube3,cube4,direction);
		if(direction==1&&!double_flag)
			System.out.println("L\'");
		else if(direction==1&&double_flag)
			System.out.println("L\'2");
		else if(direction==-1&&!double_flag)
			System.out.println("L");
		else if(direction==-1&&double_flag)
			System.out.println("L2");
	}
	public static void playDown(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction,boolean double_flag)
	{
		moveDown(cube1,cube2,cube3,cube4,direction);
		if(double_flag)
			moveDown(cube1,cube2,cube3,cube4,direction);
		if(direction==1&&!double_flag)
			System.out.println("D\'");
		else if(direction==1&&double_flag)
			System.out.println("D\'2");
		else if(direction==-1&&!double_flag)
			System.out.println("D");
		else if(direction==-1&&double_flag)
			System.out.println("D2");
	}
	public static void playFront(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction,boolean double_flag)
	{
		moveFront(cube1,cube2,cube3,cube4,direction);
		if(double_flag)
			moveFront(cube1,cube2,cube3,cube4,direction);
		if(direction==1&&!double_flag)
			System.out.println("F\'");
		else if(direction==1&&double_flag)
			System.out.println("F\'2");
		else if(direction==-1&&!double_flag)
			System.out.println("F");
		else if(direction==-1&&double_flag)
			System.out.println("F2");
	}
	public static void playBack(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction,boolean double_flag)
	{
		moveBack(cube1,cube2,cube3,cube4,direction);
		if(double_flag)
			moveBack(cube1,cube2,cube3,cube4,direction);
		if(direction==1&&!double_flag)
			System.out.println("B\'");
		else if(direction==1&&double_flag)
			System.out.println("B\'2");
		else if(direction==-1&&!double_flag)
			System.out.println("B");
		else if(direction==-1&&double_flag)
			System.out.println("B2");
	}
	public static char[][] wordMoveLeft(char[] word1,char[] word2,char[] word3,char[] word4)
	{
		char[][] result=new char[4][3];
		char[] temp=word1;
		word1=word2;
		word2=word3;
		word3=word4;
		word4=temp;
		result[0]=word1;
		result[1]=word2;
		result[2]=word3;
		result[3]=word4;
		return result;
	}
	public static char[][] wordMoveRight(char[] word1,char[] word2,char[] word3,char[] word4)
	{
		char[][] result=new char[4][3];
		char[] temp=word4;
		word4=word3;
		word3=word2;
		word2=word1;
		word1=temp;
		result[0]=word1;
		result[1]=word2;
		result[2]=word3;
		result[3]=word4;
		return result;
	}
	public static void moveUp(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction)
	{
		char[][] result;
		char[] word1=cube1.upCube();
		char[] word2=cube2.upCube();
		char[] word3=cube3.upCube();
		char[] word4=cube4.upCube();
		if(direction==-1)
			result=wordMoveLeft(word1,word2,word3,word4);
		else
			result=wordMoveRight(word1,word2,word3,word4);
		cube1.set_upCube(result[0]);
		cube2.set_upCube(result[1]);
		cube3.set_upCube(result[2]);
		cube4.set_upCube(result[3]);
	}
	public static void moveRight(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction)
	{
		char[][] result;
		char[] word1=cube1.rightCube();
		char[] word2=cube2.rightCube();
		char[] word3=cube3.rightCube();
		char[] word4=cube4.rightCube();
		if(direction==-1)
			result=wordMoveLeft(word1,word2,word3,word4);
		else
			result=wordMoveRight(word1,word2,word3,word4);
		cube1.set_rightCube(result[0]);
		cube2.set_rightCube(result[1]);
		cube3.set_rightCube(result[2]);
		cube4.set_rightCube(result[3]);
	}
	public static void moveLeft(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction)
	{
		char[][] result;
		char[] word1=cube1.leftCube();
		char[] word2=cube2.leftCube();
		char[] word3=cube3.leftCube();
		char[] word4=cube4.leftCube();
		if(direction==-1)
			result=wordMoveLeft(word1,word2,word3,word4);
		else
			result=wordMoveRight(word1,word2,word3,word4);
		cube1.set_leftCube(result[0]);
		cube2.set_leftCube(result[1]);
		cube3.set_leftCube(result[2]);
		cube4.set_leftCube(result[3]);
	}
	public static void moveDown(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction)
	{
		char[][] result;
		char[] word1=cube1.downCube();
		char[] word2=cube2.downCube();
		char[] word3=cube3.downCube();
		char[] word4=cube4.downCube();
		if(direction==-1)
			result=wordMoveLeft(word1,word2,word3,word4);
		else
			result=wordMoveRight(word1,word2,word3,word4);
		cube1.set_downCube(result[0]);
		cube2.set_downCube(result[1]);
		cube3.set_downCube(result[2]);
		cube4.set_downCube(result[3]);
	}
	public static void moveFront(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction)
	{
		char[][] result;
		char[] word1=cube1.upCube();
		char[] word2=cube2.leftCube();
		char[] word3=cube3.downCube();
		char[] word4=cube4.rightCube();
		if(direction==-1)
			result=wordMoveLeft(word1,word2,word3,word4);
		else
			result=wordMoveRight(word1,word2,word3,word4);
		cube1.set_upCube(result[0]);
		cube2.set_leftCube(result[1]);
		cube3.set_downCube(result[2]);
		cube4.set_rightCube(result[3]);
	}
	public static void moveBack(Cube cube1,Cube cube2,Cube cube3,Cube cube4,int direction)
	{
		char[][] result;
		char[] word1=cube1.downCube();
		char[] word2=cube2.rightCube();
		char[] word3=cube3.upCube();
		char[] word4=cube4.leftCube();
		if(direction==-1)
			result=wordMoveRight(word1,word2,word3,word4);
		else
			result=wordMoveLeft(word1,word2,word3,word4);
		cube1.set_downCube(result[0]);
		cube2.set_rightCube(result[1]);
		cube3.set_upCube(result[2]);
		cube4.set_leftCube(result[3]);
	}
	public static void printRubiksCube1(Cube cube)
	{
		for(int i=0;i<3;i++)
		{
			gotoMiddle();
			cube.printCubeline(i);
			System.out.println();
		}
	}
	public static void printRubiksCube4(Cube cube1,Cube cube2,Cube cube3,Cube cube4)
	{
		for(int i=0;i<3;i++)
		{
			cube1.printCubeline(i);
			cube2.printCubeline(i);
			cube3.printCubeline(i);
			cube4.printCubeline(i);
			System.out.println();
		}
	}
	public static void gotoMiddle()
	{
		for(int i=0;i<15;i++)
		{
			System.out.print(" ");
		}
	}
	public static void printRubiksCube(Cube cube1,Cube cube2,Cube cube3,Cube cube4,Cube cube5,Cube cube6)
	{
		printRubiksCube1(cube1);
		System.out.println();
		printRubiksCube4(cube2,cube3,cube4,cube5);
		System.out.println();
		printRubiksCube1(cube6);
		System.out.println();
	}
	public static void shuffleCube(Cube cube1,Cube cube2,Cube cube3,Cube cube4,Cube cube5,Cube cube6)
	{
		int[] rand_num=new int[100];
		System.out.println("Random shuffle....");
		for(int i=0;i<100;i++)
		{
			rand_num[i]=(int)(Math.random()*6);
			if(rand_num[i]==0)
				playUp(cube2,cube3,cube4,cube5,-1,false);
			else if(rand_num[i]==1)
				playRight(cube3,cube6,cube5,cube1,-1,false);
			else if(rand_num[i]==2)
				playLeft(cube3,cube6,cube5,cube1,-1,false);
			else if(rand_num[i]==3)
				playDown(cube2,cube3,cube4,cube5,-1,false);
			else if(rand_num[i]==4)
				playFront(cube6,cube4,cube1,cube2,-1,false);
			else if(rand_num[i]==5)
				playBack(cube6,cube4,cube1,cube5,2,false);
		}
		printRubiksCube(cube1,cube2,cube3,cube4,cube5,cube6);
	}
	public static boolean checkSuccess(Cube original1,Cube original2,Cube original3,Cube original4,Cube original5,Cube original6,Cube cube1,Cube cube2,Cube cube3,Cube cube4,Cube cube5,Cube cube6)
	{
		if(original1.equals(cube1)&&original2.equals(cube2)&&original3.equals(cube3)&&original4.equals(cube4)&&original5.equals(cube5)&&original6.equals(cube6))
		{
			return true;
		}
		return false;
	}
}
