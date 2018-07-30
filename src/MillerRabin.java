//Haki Dehari
//Project 3 Phase 1 program number 1
//11/30/2016

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;


public class Part1 {

	public static void main(String[] args) {
			Part1 p1 = new Part1();

			// TODO Auto-generated method stub
			BigInteger num;
			Scanner in = new Scanner(System.in);
			System.out.print("Please input any number to find the closest prime number to it: ");
			num = in.nextBigInteger();

			System.out.println(p1.getPrime(num));

			in.close();

	}

	public BigInteger getPrime(BigInteger num){
		BigInteger original,num1;
		original = num;
		num1 = num;
		if (MillerRabin(original) == false){
			//System.out.println(original.toString() + " is not a prime");
			num = num.add(BigInteger.valueOf(1));
			num1 = num.subtract(BigInteger.valueOf(1));
			while(MillerRabin(num) == false && MillerRabin(num1) == false){
				num = num.add(BigInteger.valueOf(1));
				num1 = num.subtract(BigInteger.valueOf(1));
			}//end while loop
			if (MillerRabin(num) == true){
				return num;
			}//end if
			if (MillerRabin(num1) == true){
				return num1;
			}//end if
		}//end if
		if (MillerRabin(original) == true){
			return original;
		}//end if

		return BigInteger.ZERO;
	}


	//method to test the primality of a number
	public boolean MillerRabin(BigInteger num){
		//base cases
		if (BigInteger.valueOf(2).compareTo(num) == 0){
			return true;
		}//end if
		if (BigInteger.valueOf(1).compareTo(num) == 0 || BigInteger.valueOf(0).compareTo(num)==0){
			return false;
		}//end if
		if (num.mod(BigInteger.valueOf(2)).equals(BigInteger.valueOf(0))){
           return false;
		}//end if
		//declare variables for loops
		BigInteger nMinusOne = num.subtract(BigInteger.valueOf(1));
		BigInteger k = BigInteger.valueOf(0);
		BigInteger q;
		BigInteger rightSide = nMinusOne;

		//while loop used to find k and q for n-1 = 2^k * q
		while (rightSide.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(0)) == 0){
			rightSide = rightSide.divide(BigInteger.valueOf(2));
			k = k.add(BigInteger.valueOf(1));
		}//end while loop
		q = rightSide;

		//for loop to test primality for 10 different values of a
		for (int i = 0; i < 10; i++){
			BigInteger a = randomBigInteger(num);
			if ((a.modPow(q, num).compareTo(BigInteger.valueOf(1)) == 0)){
				return true;
			}//end if
			//nested for loop to test values between j=0 and k-1
			for (int j = 0; j < k.intValue(); j++){
				BigInteger exponent = BigInteger.valueOf(2).multiply(BigInteger.valueOf(j));
				BigInteger finalExponent = exponent.multiply(q);
				if (a.modPow(finalExponent, num).compareTo(nMinusOne) == 0){
					return true;
				}//end nested if
			}//end nested for loop
		}//end for loop

		return false;
	}//end method

	//method to generate a random BigInteger between 1 and n-1
	public BigInteger randomBigInteger(BigInteger n) {
      Random rnd = new Random();
      int maxNumBitLength = n.bitLength();
      BigInteger aRandomBigInt;
      do {
            aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
            // compare random number less than given number
      } while (aRandomBigInt.compareTo(n) > 0);
      	return aRandomBigInt;
  }//end method

	//function will generate a random number with a user specified length
	public String getRandomNumber(int length)
	{
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++)
			sb.append((char)('0' + rnd.nextInt(10)));

        return sb.toString();

    }//end getRandomNumber

}
