/**
 * Student Name: Zak Kappenman
 * Student ID: 43851109
 * Honor Code: "I pledge that this submission is solely my work, and
 *              that I have neither given, nor received help from anyone."
 */

import java.util.*;

public class CacheCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Main Memory
        System.out.println("Main memory representation is A x 2^E");
        System.out.print("Value A: ");
        int a = sc.nextInt();
        System.out.print("Exponent E: ");
        int e = sc.nextInt();

        int mainMemBytes = a * ((int)Math.pow(2, e));

        System.out.println("Main Memory: " + a + " x 2^" + e + " = " + mainMemBytes + " bytes");
        
        // calculating the exponent sum for later calculations
        int memExp = 0;
        while (a % 2 == 0)
        {
            memExp++;
            a = a / 2;
        }
        memExp += e;

        // Cache Memory
        System.out.print("Cache blocks: ");
        int cacheBlocks = sc.nextInt();
        
        // checking if input is in base 2
        int n = cacheBlocks;
        while (n % 2 == 0)
        {
            n = n / 2;
        }
        if (n != 1)
        {
            System.out.print("Cache Blocks is not in Base 2. Program terminated");
            sc.close();
            return;
        }

        System.out.print("Bytes in Cache blocks: ");
        int cacheBlockBytes = sc.nextInt();
        
        // checking if input is in base 2
        n = cacheBlockBytes;
        while (n % 2 == 0)
        {
            n = n / 2;
        }
        if (n != 1)
        {
            System.out.print("Bytes in Cache Blocks is not in Base 2. Program terminated");
            sc.close();
            return;
        }

        System.out.print("k-set associative value: ");
        int k = sc.nextInt();
        
        // checking if input is in base 2
        n = k;
        while (n % 2 == 0)
        {
            n = n / 2;
        }
        if (n != 1)
        {
            System.out.print("k-set associative values is not in Base 2. Program terminated");
            sc.close();
            return;
        }

        // Memory Address
        System.out.print("Memory address in Base 16: ");
        String inputMemAddress = sc.next();
        String b16MemAddress = inputMemAddress.replace("0x", "");
        
        // checking if the input is in base 16
        for (int i = 0; i < b16MemAddress.length(); i++)
        {
            char x = b16MemAddress.charAt(i);
            if (!(x == '0' || x == '1' || x == '2' || x == '3' || x == '4' || x == '5' ||
                x == '6' || x == '7' || x == '8' || x == '9' || x == 'a' || x == 'A' ||
                x == 'b' || x == 'B' || x == 'c' || x == 'C' || x == 'd' || x == 'D' ||
                x == 'e' || x == 'E' || x == 'f' || x == 'F'))
            {
                System.out.print("Memory address is not in base 16. Program terminated");
                sc.close();
                return;
            }
        }

        int b10 = Integer.parseInt(b16MemAddress, 16);
        String b2 = Integer.toBinaryString(b10);

        // checking if the Memory Address size exceeds Main Memory size
        if (b2.length() > memExp)
        {
            System.out.print("Size of address is larger than main memory size. Program terminated");
            sc.close();
            return;
        }

        String format = "%" + memExp + "s";
        String memAddress = String.format(format, b2).replace(' ', '0');

        System.out.println("Address in binary: " + memAddress);
        sc.close();

        // Direct Cache
        System.out.println("\nDirect Cache mapping of " + inputMemAddress + " address");

        // Direct Cache: Word
        int directWord = 0;
        int dBlockBytes = cacheBlockBytes;
        while (dBlockBytes > 1)
        {
            directWord++;
            dBlockBytes = dBlockBytes / 2;
        }

        // Direct Cache: Line
        int directLine = 0; 
        int dBlocks = cacheBlocks;
        while (dBlocks > 1)
        {
            directLine++;
            dBlocks = dBlocks / 2;
        }

        // Direct Cache: Tag
        int directTag = (memExp - (directLine + directWord));

        // Direct Cache: printing line 2
        System.out.print("[TAG] " + directTag + " : ");
        System.out.print("[LINE] " + directLine + " : ");
        System.out.println("[WORD] " + directWord);

        // Direct Cache: printing line 3
        String bitDirectTag = "";
        String bitDirectLine = "";
        String bitDirectWord = "";
        for (int i = 0; i < directTag; i++)
        {
            char currentDigit = memAddress.charAt(i);
            bitDirectTag = bitDirectTag + currentDigit;
        }

        for (int i = directTag; i < (directTag + directLine); i++)
        {
            char currentDigit = memAddress.charAt(i);
            bitDirectLine = bitDirectLine + currentDigit;
        }

        for (int i = (directTag + directLine); i < memAddress.length(); i++)
        {
            char currentDigit = memAddress.charAt(i);
            bitDirectWord = bitDirectWord + currentDigit;
        }
        
        System.out.print("[TAG] " + bitDirectTag + " : ");
        System.out.print("[LINE] " + bitDirectLine + " : ");
        System.out.println("[WORD] " + bitDirectWord);

        // Associative Cache
        System.out.println("\nAssociative Cache mapping of " + inputMemAddress + " address");

        // Associative Cache: Word
        int aWord = 0;
        int aBlockBytes = cacheBlockBytes;
        while (aBlockBytes > 1)
        {
            aWord++;
            aBlockBytes = aBlockBytes / 2;
        }

        // Associative Cache: Tag
        int aTag = memExp - aWord;

        // Associative Cache: printing line 2
        System.out.print("[TAG] " + aTag + " : ");
        System.out.println("[WORD] " + aWord);

        // Associative Cache: printing line 3
        String bitATag = "";
        String bitAWord = "";

        for (int i = 0; i < aTag; i++)
        {
            char currentDigit = memAddress.charAt(i);
            bitATag = bitATag + currentDigit;
        }
        for (int i = aTag; i < memAddress.length(); i++)
        {
            char currentDigit = memAddress.charAt(i);
            bitAWord = bitAWord + currentDigit;
        }

        System.out.print("[TAG] " + bitATag + " : ");
        System.out.println("[WORD] " + bitAWord);

        // Set-Associative Cache
        System.out.println("\n" + k + "-way Cache mapping of " + inputMemAddress + " address");

        // Set-Associative Cache: Word
        int SAWord = 0;
        int SABlockBytes = cacheBlockBytes;
        while (SABlockBytes > 1)
        {
            SAWord++;
            SABlockBytes = SABlockBytes / 2;
        }

        // Set-Associative Cache: Set
        int numSets = 0;
        int tempK = k;
        while (tempK > 1)
        {
            numSets++;
            tempK = tempK / 2;
        }
        int SASet = directLine - numSets;

        // Set-Associative Cache: Tag
        int SATag = (memExp - (SASet + SAWord));

        // Set-Associative Cache: printing line 2
        System.out.print("[TAG] " + SATag + " : ");
        System.out.print("[SET] " + SASet + " : ");
        System.out.println("[WORD] " + SAWord);

        // Set-Associative Cache: printing line 3
        String bitSATag = "";
        String bitSASet = "";
        String bitSAWord = "";
        for (int i = 0; i < SATag; i++)
        {
            char currentDigit = memAddress.charAt(i);
            bitSATag = bitSATag + currentDigit;
        }

        for (int i = SATag; i < (SATag + SASet); i++)
        {
            char currentDigit = memAddress.charAt(i);
            bitSASet = bitSASet + currentDigit;
        }

        for (int i = (SATag + SASet); i < memAddress.length(); i++)
        {
            char currentDigit = memAddress.charAt(i);
            bitSAWord = bitSAWord + currentDigit;
        }
        
        System.out.print("[TAG] " + bitSATag + " : ");
        System.out.print("[SET] " + bitSASet + " : ");
        System.out.println("[WORD] " + bitSAWord);
    }
}