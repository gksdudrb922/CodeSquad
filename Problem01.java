package codesquad;

import java.util.Scanner;

public class Problem01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		while(true)
		{
			System.out.print("> ");
			String input=in.nextLine();
			
			// quit point
			if(input.equals("Q"))
				break;
			
			String[] str=input.split(" ");
			
			// second argument is not integer, continue
			int num;
			try
			{
				num=Integer.valueOf(str[1]).intValue();
			}
			catch(NumberFormatException e)
			{
				System.out.println("input integer. (second argument)");
				continue;
			}
			
			// second argument is not in (-100 ~ 100), continue
			if(num<-100 ||num>100)
			{
				System.out.println("input N (-100 ~ 100) (second argument)");
				continue;
			}
			
			// third argument is not L or R, continue
			if(!str[2].equalsIgnoreCase("L")&&!str[2].equalsIgnoreCase("R"))
			{
				System.out.println("input L or R (third argument)");
				continue;
			}
			
			char[] word=str[0].toCharArray();
			int direction=1;
			if(str[2].equalsIgnoreCase("L"))
				direction=-1;
			if(num<0)
			{
				num*=-1;
				direction*=-1;
			}
			word=wordMove(word,num,direction);
			for(char c:word)
			{
				System.out.print(c);
			}
			System.out.println();
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
}
