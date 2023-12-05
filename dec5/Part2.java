import java.io.File;
import java.util.*;

public class Part2 {

    static class Range {
        long start;
        long end;
        long size;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        boolean contains(Range r) {
            return start <= r.start && end >= r.end;
        }

        public String toString() {
            return ("start = " + start + " end = " + end);
        }

        public static void main(String[] args) {
            try {
                Scanner scan = new Scanner(new File("test.txt"));
                String[] seedStrings = scan.nextLine().split(" ");
                ArrayList<Range> rangeList = new ArrayList<>();
                for (int i = 1; i < seedStrings.length; i += 2) {
                    long start = Long.parseLong(seedStrings[i]);
                    long range = Long.parseLong(seedStrings[i + 1]);
                    rangeList.add(new Range(start, range + start-1));
                }


                for (Range r : rangeList) {
                    System.out.println(r.toString());
                }

                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    if (line.equals("")) {
                        //
                    } else if (line.contains("map")) {
                        //
                    } else {
                        Range sourceRange = new Range(Long.parseLong(line.split(" ")[1]),
                                Long.parseLong(line.split(" ")[1]) + Long.parseLong(line.split(" ")[2]) - 1);
                        Range destinationRange = new Range(Long.parseLong(line.split(" ")[0]),
                                Long.parseLong(line.split(" ")[0]) + Long.parseLong(line.split(" ")[2]) - 1);
                        System.out.println("sourcreange: " + sourceRange.toString());
                        System.out.println("desrange: " + destinationRange.toString());
                        for (int i = 0; i<rangeList.size(); i++) {
                            if (rangeList.get(i).contains(sourceRange)) {
                                System.out.println("contains");
                                rangeList.add(new Range(rangeList.get(i).start, sourceRange.start - 1));
                                rangeList.add(destinationRange);
                                rangeList.add(new Range(sourceRange.end + 1, rangeList.get(i).end));
                                rangeList.remove(i);
                            } else if (!rangeList.get(i).contains(sourceRange)) {
                                System.out.println("doesnt contain");
                                // nothing
                            } else {
                                System.out.println("else");
                                if (rangeList.get(i).start >= sourceRange.start) {
                                    rangeList.add(new Range(sourceRange.start, sourceRange.end));
                                    rangeList.add(new Range(sourceRange.end + 1, rangeList.get(i).end));
                                    rangeList.remove(rangeList.get(i));
                                } else if (sourceRange.end >= rangeList.get(i).end) {
                                    rangeList.add(new Range(rangeList.get(i).start, sourceRange.start - 1));
                                    rangeList.add(new Range(sourceRange.start, sourceRange.end));
                                    rangeList.remove(rangeList.get(i));
                                } else {

                                    System.out.println("missed all");
                                    continue;
                                }
                            }
                        }
                    }
                    for (Range r : rangeList) {
                        System.out.println(r.toString());
                    }
                    System.out.println("");


                }
                long min = Long.MAX_VALUE;
                for (Range r : rangeList) {
                    // System.out.println(r.toString());
                    if (r.start < min) {
                        min = r.start;
                    }
                }
                System.out.println(min);
            } catch (Exception e) {
                System.out.println("hej");
                e.printStackTrace();
            }

        }
    }
}
