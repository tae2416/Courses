import java.util.Arrays;
import java.util.Scanner;

class CSCE586_HW1
{
    public static int euclidGCD(int m, int n) {
        int count = 0;

        while(n != 0){
            int r = m % n;
            m = n;
            n = r;
            count++;
        }
        System.out.printf("The number of iterations for Euclid is:  %d\n", count);
        System.out.println("");

        return m;
    }

    public static int intGCD(int m, int n){
        int r, r1, t, count = 0, loop = 1;      
        t = Math.min(m,n);

        while (loop > 0){
            r = m % t; 
            if (r == 0){
                r1 = n % t;
                if (r1 == 0){
                    loop = 0;
                }
            } 
            t--;
            count++;
        }
        t++;

        System.out.printf("The number of iterations for consecutive integers is:  %d\n", count);
        System.out.println("");

        return t;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("");
        System.out.println("");
        System.out.print("Enter the problem #:  ");
        int probNum = Integer.parseInt(scanner.nextLine());

        switch (probNum){
            case 1: 
                System.out.println("");
                System.out.println("");
                System.out.print("Enter the 1st integer for GCD check:  ");
                int num1 = Integer.parseInt(scanner.nextLine());
                System.out.println("");

                System.out.print("Enter the 2nd integer for GCD check:  ");
                int num2 = Integer.parseInt(scanner.nextLine());
                System.out.println("");

                int GCD1 = euclidGCD(num1, num2);
                System.out.printf("The GCD by Euclid is:  %d\n", GCD1);
                System.out.println("");
                
                int GCD2 = intGCD(num1, num2);
                System.out.printf("The GCD by Consecutive Integer Check is:  %d\n", GCD2);
                System.out.println("");

                break;
            case 2:
                System.out.println("");
                System.out.println("");
                System.out.print("Enter the number of lockers:  ");
                int lockNum = Integer.parseInt(scanner.nextLine());
                System.out.println("");

                int[][] lockers = new int[lockNum][lockNum];
                //Initialize nxn array
                for (int i = 0; i < lockNum; i++){
                    for (int j = 0; j <  lockNum; j++){
                        lockers[i][j] = 0;                      
                    }
                }

                //Make i-th run
                for (int i = 0; i < lockNum; i++){
                    for (int j = 0; (i+1)*(j+1) <= lockNum; j++){                       
                        if (lockers[i][(i+1)*(j+1)-1] == 0) {
                            lockers[i][(i+1)*(j+1)-1] = 1;
                        }else {
                            lockers[i][(i+1)*(j+1)-1] = 0;
                        }
                    
                        //Update state for the (i+1)-th run; copy i-th run to next state
                        if (i < lockNum-1){
                            for (int k = i+1; k < lockNum; k++){
                                lockers[k][(i+1)*(j+1)-1] = lockers[k-1][(i+1)*(j+1)-1];
                            }
                        }
                    }                      
                }

                System.out.println("The stae of the lockers:");
                System.out.println("");
                for (int i = 0; i < lockNum; i++){
                    System.out.println(Arrays.toString(lockers[i]));
                }
                System.out.println("");

                break;
            case 3: 
                int[] numZ = {60, 35, 81, 98, 14, 17};
                int[] Count = new int[numZ.length];
                int[] Sorted = new int[numZ.length];
                int k = 0;

                for (int i = 0; i < numZ.length; i++){
                    Count[i] = 0;
                }

                for (int i = 0; i < numZ.length-1; i++){
                    for (int j = i+1; j < numZ.length; j++){
                        if (numZ[i] < numZ[j]){
                            Count[j] = Count[j] + 1;
                        }else{
                            Count[i] = Count[i] + 1;
                        }
                    k++;
                    System.out.printf("The array Count at interation %d:  ", k);
                    System.out.println(Arrays.toString(Count));                
                    System.out.println("");
                    }
                }

                for (int i = 0; i < numZ.length; i++){
                    Sorted[Count[i]] = numZ[i];
                }
                System.out.print("Unsorted array:  ");
                System.out.println(Arrays.toString(numZ));                
                System.out.println("");
                System.out.print("Sorted array:  ");
                System.out.println(Arrays.toString(Sorted));                
                System.out.println("");

                break;
            case 8:
                System.out.println("");
                System.out.println("");
                System.out.print("Enter the 1st string for anagram check:  ");
                char[] str1 = scanner.nextLine().toCharArray();
                System.out.println("");

                System.out.print("Enter the 2nd string for anagram check:  ");
                char[] str2 = scanner.nextLine().toCharArray();
                System.out.println("");
                
                Boolean anagram = true;

                if (str1.length != str2.length){
                    anagram = false;
                }else{
                    int i = 0;
                    int j = 0;
                    char temp;

                    while (anagram && (i < str1.length)){
                        j = i;
                        while (j < str2.length){
                            if (str1[i] == str2[j]){
                                temp = str2[i];
                                str2[i] = str2[j];
                                str2[j] = temp;
                                j = str2.length;
                            }else{
                                if (j == str2.length-1){
                                    anagram = false;
                                }
                            }
                            j++;
                        }
                        i++;
                    }
                }

                if (anagram){
                    System.out.println("The strings are anagrams.");
                }else{
                    System.out.println("The strings are not anagrams.");
                }

                break;
            default:
                System.out.println("Invalid problem #.");
                break;
        }   
    }
}