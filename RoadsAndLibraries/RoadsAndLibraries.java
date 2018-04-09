package RoadsAndLibraries;

import java.io.*;
import java.util.*;
import java.text.*;

public class RoadsAndLibraries {


    private static long[] answers = new long[]{
            //input01
//            805,
//            184,
//            80,
//            5,
//            204
            //input02
//            5649516,
//            5295483,
//            9261576,
//            3960530,
//            7629795,
//            40216260,
//            6701050,
//            40280315,
//            4614540,
//            12407190
            //input10
            5649516L,
            8432327200L,
            5948772399L,
            1713294016L,
            7902963560L,
            7246913654L,
            5405604L,
            1739991079L,
            787818609L,
            9896100000L
    };


    public static void test(){

        HashSet<Integer> s1 = new HashSet<>();
        s1.add(Integer.valueOf(1));

        HashSet<Integer> s2 = new HashSet<>();
        s2.add(Integer.valueOf(1));

        HashSet<HashSet<Integer>> container = new HashSet<>();
        container.add(s1);
        container.add(s2);
        IdentityHashMap m = new IdentityHashMap();

        m.put(s1, null);
        m.put(s2, null);


        System.out.println(m.size()+", "+ m.keySet().size());

    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTimeTot = System.currentTimeMillis();
        for (int repeat = 0; repeat < 1; repeat++) {
test();

            FileInputStream is = new FileInputStream(new File("RoadsAndLibraries/input10.txt"));

//        Scanner in = new Scanner(System.in);
            Scanner in = new Scanner(is);
            int q = in.nextInt();
            for (int a0 = 0; a0 < q; a0++) {
                long startTime = System.currentTimeMillis();

                String paramString = in.findWithinHorizon("[0-9]* [0-9]* [0-9]* [0-9]*", 0);
                String[] split = paramString.split(" ");
                int n = Integer.parseInt(split[0]);
                int m = Integer.parseInt(split[1]);
                long cLib = Long.parseLong(split[2]);
                long cRoad = Long.parseLong(split[3]);
                //in.nextLine();

//                int n = in.nextInt();
//                int m = in.nextInt();
//                long cLib = in.nextLong();
//                long cRoad = in.nextLong();
                System.out.println(MessageFormat.format("n:{0}, m:{1}, cLib:{2}, cRoad:{3}", n, m, cLib, cRoad));
                if (cLib <= cRoad) {
                    System.out.println("a library in every city!");
                    //build a library in every city
                    System.out.println((cLib * n));
                    System.out.println((cLib * n) == answers[a0] ? "correct!" : "wrong!");

                } else {
                    long init = System.currentTimeMillis();
                    //save roads (sets of connected cities)

                    IdentityHashMap<Set<Integer>, Object> uniqueSets = new IdentityHashMap<>();
                    Map<Integer, Set<Integer>> cityToSet = new HashMap<>(n);
//                    Map<Integer, Integer> cityToSetIndex = new HashMap<>(n);

                    for (int a1 = 0; a1 < m; a1++) {
                        //for each road
                        int city_1 = in.nextInt();
                        int city_2 = in.nextInt();

                        Set<Integer> city1Set = cityToSet.getOrDefault(city_1, null);
                        Set<Integer> city2Set = cityToSet.getOrDefault(city_2, null);

                        if (city1Set == null && city2Set == null) {
                            // brand new set
                            HashSet<Integer> newSet = new HashSet<>();
                            newSet.add(city_1);
                            newSet.add(city_2);
                            cityToSet.put(city_1, newSet);
                            cityToSet.put(city_2, newSet);

                            uniqueSets.put(newSet, null);
                        } else if (city1Set != null && city2Set == null) {
                            //only city1
                            city1Set.add(city_2);
                            cityToSet.put(city_2, city1Set);
//                            cityToSetIndex.put(city_2, cityToSetIndex.get(city_1));
                        } else if (city1Set == null && city2Set != null) {
                            //only city2
                            city2Set.add(city_1);
                            cityToSet.put(city_1, city2Set);
//                            cityToSetIndex.put(city_1, cityToSetIndex.get(city_2));
                        } else if (city1Set != city2Set) {
                            //different sets!
                            //merge the 2 sets
                            city1Set.addAll(city2Set);
//                            int toRemove = cityToSetIndex.get(city_2);
                            for (Integer city : city2Set) {
                                cityToSet.put(city, city1Set);
//                                cityToSetIndex.put(city, cityToSetIndex.get(city_1));
                            }
                            //wrong! should shift all others... find another way map of references?
                            uniqueSets.remove(city2Set);
//                            boolean removed = uniqueSets.remove(city2Set);
//                            if (!removed) {
//                                throw new RuntimeException();
//                            }
                        }


                    }

                    long sumConnectedSize = 0;
                    long cost = 0;
                    for (Set<Integer> cities : uniqueSets.keySet()) {
                        if (!cities.isEmpty()) {
                            sumConnectedSize += cities.size();
                            cost += (cities.size() - 1) * cRoad + cLib;
                        }
                    }
                    long islands = n - sumConnectedSize;
                    System.out.println("sets:" + uniqueSets.size() + "," + sumConnectedSize + ", islands:" + islands );
                    System.out.println((cost + (islands * cLib)));
                    System.out.println((cost + (islands * cLib)) == answers[a0] ? "correct!" : "wrong!");
                }

                System.out.println((System.currentTimeMillis() - startTime) + "ms");
                System.out.println("-----------------");

            }

            // System.out.println("finito");
        }
        System.out.println("total time: " + (System.currentTimeMillis() - startTimeTot) + "ms");
    }


    public static <T> void printSet(Collection<T> coll) {
        System.out.println();
        for (T item : coll) {
            System.out.print(item.toString() + ", ");
        }
        System.out.println();
    }
}
