import java.math.BigInteger;

public class yes {

	
	public static void main(String[] args)
	{
		//printPE_1();
		printPE_2();
	}
	
	/**
	 * @param (none)
	 * @return (none)
	 * 
	 * 
	 */
	static void printPE_1(int y)
	{
		int sum = 0;
		int i = 3;
		
		for (i = 3; i < 1000; i ++ )
		{
			if ( i % 3 == 0 || i % 5 == 0)
			{
				sum += i;
			}
		}
		System.out.println(sum);
	}
	
	static void printPE_2()
	{
		int sum = 0;
		int prevNum = 1;
		int i = 2;
		
		while(i <= 4000000 )
		{
			if (i % 2 == 0){
			sum += i;
			System.out.println(sum);
		}
			System.out.println(i);
			i +=prevNum;
			prevNum = i - prevNum;
			

			
		}
		System.out.println(sum);
	}
	static void printPE_3() {
		
		long number = 600851475143L;
		
		
		
	}
}