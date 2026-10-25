import java.util.*;

public class Myframing {

    // 1. Character Count Framing
    static String characterCount(String data) {
        return String.format("%04d", data.length()) + data;
    }

    // 2. Character Stuffing
    static String characterStuffing(String data) {
        StringBuilder frame = new StringBuilder("F");

        for (char ch : data.toCharArray()) {
            if (ch == 'F' || ch == 'E')
                frame.append('E');

            frame.append(ch);
        }

        return frame.append('F').toString();
    }

    // 3. Bit Stuffing
    static String bitStuffing(String bits) {
        StringBuilder frame = new StringBuilder("01111110");
        int ones = 0;

        for (char bit : bits.toCharArray()) {
            frame.append(bit);

            if (bit == '1') {
                ones++;
                if (ones == 5) {
                    frame.append('0');
                    ones = 0;
                }
            } else {
                ones = 0;
            }
        }

        return frame.append("01111110").toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter data for character count: ");
        String data = sc.nextLine();
        System.out.println("Framed data: " + characterCount(data));

        System.out.print("\nEnter data for character stuffing: ");
        data = sc.nextLine();
        System.out.println("Framed data: " + characterStuffing(data));

        System.out.print("\nEnter binary data for bit stuffing: ");
        String bits = sc.nextLine();
        System.out.println("Framed data: " + bitStuffing(bits));
       
        sc.close();
    }
}