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
}

public class Problem02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		char[] cube_in={'R','R','W','G','C','W','G','B','B'};
		Cube cube=new Cube(cube_in);
		cube.printCube();
		in.close();
	}
}
