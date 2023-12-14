import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("test.txt"));
        ArrayList<String> cols = new ArrayList<>();
        ArrayList<String> tiltedCols = new ArrayList<>();
        String line = scan.nextLine();
        for(int i = 0; i<line.length(); i++) {
        cols.add(Character.toString(line.charAt(i)));
        }
        
        while(scan.hasNextLine()) {
            line = scan.nextLine();
            for(int i = 0; i<line.length(); i++) {
                cols.set(i, cols.get(i).concat(Character.toString(line.charAt(i))));
            }
        }

        for(int k = 0; k<cols.size(); k++) {
            tiltedCols.add("");
            String[] split = cols.get(k).split("#");
            int c = 0;
            while(cols.get(k).charAt(c) == '#') {
                tiltedCols.set(k, tiltedCols.get(k).concat("#"));
                System.out.println("hej");
                c++;
            }
            for(int i = 0; i<split.length; i++) {
                char[] sorted = split[i].toCharArray();
                Arrays.sort(sorted);
                for(int j = sorted.length-1; j >= 0; j--) {
                    tiltedCols.set(k, tiltedCols.get(k).concat(Character.toString(sorted[j])));
                }
                tiltedCols.set(k, tiltedCols.get(k).concat("#"));
            }
            while(tiltedCols.get(k).length() < cols.get(k).length()) {
                tiltedCols.set(k, tiltedCols.get(k).concat("#"));
            }
        }
          System.out.println(tiltedCols);
        int part1 = 0;
        for(int i = 0; i<tiltedCols.size(); i++) {
            for(int j = 0; j<tiltedCols.get(0).length(); j++) {
                if(tiltedCols.get(i).charAt(j) == 'O') {
                    part1 += tiltedCols.get(0).length()-j;
                }
            }
            
        }
        System.out.println("part1 = " + part1);
    }
}
