package codesquad;

import java.util.Scanner;

class Cube
{
	char cube[][];
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
}

public class Problem02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		char[] cube_in={'R','R','W','G','C','W','G','B','B'};
		Cube cube=new Cube(cube_in);
		cube.printCube();
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
			
			String[] str=input.split("");
			for(int i=0;i<str.length;i++)
			{
				int tmp_index=i;
				int direction=-1;
				if(i!=str.length-1&&str[i+1].equals("\'"))
				{
					direction*=-1;
					tmp_index=i+1;
				}
				if(str[i].equals("U"))
				{
					moveUp(cube,direction);
					if(direction==1)
						System.out.println("U\'");
					else
						System.out.println("U");
					
				}
				else if(str[i].equals("R"))
				{
					moveRight(cube,direction);
					if(direction==1)
						System.out.println("R\'");
					else
						System.out.println("R");
					
				}
				else if(str[i].equals("L"))
				{
					moveLeft(cube,direction);
					if(direction==1)
						System.out.println("L\'");
					else
						System.out.println("L");
					
				}
				else if(str[i].equals("B"))
				{
					moveDown(cube,direction);
					if(direction==1)
						System.out.println("B\'");
					else
						System.out.println("B");
					
				}
				else
				{
					System.out.println("unvalid command ["+str[i]+"]");
					break;
				}
				cube.printCube();
				i=tmp_index;
			}
		}
		in.close();
	}
	public static char[] wordMove(char[] word,int num,int direction)
	{
		if(direction==-1)
		{
			int i;
			for(int k=0;k<num;k++)
			{
				char temp=word[0];
				for(i=0;i<word.length-1;i++)
				{
					word[i]=word[i+1];
				}
				word[i]=temp;
			}
		}
		else if(direction==1)
		{
			int i;
			for(int k=0;k<num;k++)
			{
				char temp=word[word.length-1];
				for(i=word.length-1;i>0;i--)
				{
					word[i]=word[i-1];
				}
				word[0]=temp;
			}
		}
		return word;
	}
	public static void moveUp(Cube cube,int direction)
	{
		char[] word=cube.upCube();
		word=wordMove(word,1,direction);
		cube.set_upCube(word);
	}
	public static void moveRight(Cube cube,int direction)
	{
		char[] word=cube.rightCube();
		word=wordMove(word,1,direction);
		cube.set_rightCube(word);
	}
	public static void moveLeft(Cube cube,int direction)
	{
		char[] word=cube.leftCube();
		word=wordMove(word,1,direction);
		cube.set_leftCube(word);
	}
	public static void moveDown(Cube cube,int direction)
	{
		char[] word=cube.downCube();
		word=wordMove(word,1,direction);
		cube.set_downCube(word);
	}
}
