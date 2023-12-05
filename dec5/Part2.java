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
                Scanner scan = new Scanner(new File("input5.txt"));
                String[] seedStrings = scan.nextLine().split(" ");
                HashSet<Range> rangeSet = new HashSet<>();
                for (int i = 1; i < seedStrings.length; i += 2) {
                    long start = Long.parseLong(seedStrings[i]);
                    long range = Long.parseLong(seedStrings[i + 1]);
                    rangeSet.add(new Range(start, range + start));
                }

                System.out.println("added all ranges to map");

                for (Range r : rangeSet) {
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
                        for (Range r : rangeSet) {
                            if (r.contains(sourceRange)) {
                                rangeSet.add(new Range(r.start, sourceRange.start - 1));
                                rangeSet.add(destinationRange);
                                rangeSet.add(new Range(sourceRange.end + 1, r.end));
                                rangeSet.remove(r);
                                break;
                            } else if (!r.contains(sourceRange)) {
                                // nothing
                            } else {
                                if (r.start >= sourceRange.start) {
                                    rangeSet.add(new Range(sourceRange.start, sourceRange.end));
                                    rangeSet.add(new Range(sourceRange.end + 1, r.end));
                                    rangeSet.remove(r);
                                    break;
                                } else if (sourceRange.end >= r.end) {
                                    rangeSet.add(new Range(r.start, sourceRange.start - 1));
                                    rangeSet.add(new Range(sourceRange.start, sourceRange.end));
                                    rangeSet.remove(r);
                                    break;
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
                long min = Long.MAX_VALUE;
                for (Range r : rangeSet) {
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
