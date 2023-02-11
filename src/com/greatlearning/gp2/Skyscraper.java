package com.greatlearning.gp2;

import java.util.*;

public class Skyscraper {

    // stores the constructed floors in the order which they are inserted, first floor is for day 1, second floor for day 2, etc
    static List<Integer> recievedFloor = new ArrayList<Integer>();
    static Map<Integer, List<Integer>> constructionPlanMap= new LinkedHashMap<Integer, List<Integer>>();
    static TreeSet<Integer> tempSortedFloors = new TreeSet<Integer>();

    public static void main(String[] args) {
        System.out.println("Test");

        // 1. Take no. of floors required to build
        System.out.println("enter the total no of floors in the building");
        Scanner sc = new Scanner(System.in);
        int totalFloor = sc.nextInt();

        // 2. For each day, take floor built on that day
        receiveConstructedFloor(totalFloor, sc);

        // 3. determine day wise construct order
        computeConstruction(totalFloor);

        // 4. Print the daily floor plan
        displayResult();
    }

    private static void displayResult() {

        for (Map.Entry<Integer, List<Integer>> entry : constructionPlanMap.entrySet()) {
            System.out.println("Day: " + entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    private static void computeConstruction(int totalFloor) {

        // initially the highest floor would be needed to construct first
        int targetConstructionFloor = totalFloor;
        int dayNo = 0;
        for (int currentFloorSize:
             recievedFloor) {
            dayNo++;
            if (currentFloorSize == targetConstructionFloor)
            {
                // we have got the highest floor which is yet to be placed
                addFloorsForTheDay(dayNo, currentFloorSize);

                // since current target is met, new target needs to be set
                // in next iteration target floor would be less by the already placed floors
                targetConstructionFloor = targetConstructionFloor - constructionPlanMap.get(dayNo).size();
            } else {
                // no construction is possible for the day
                // add empty data for that
                constructionPlanMap.put(dayNo, new ArrayList<>());

                // store the constructed floor in sorted order in the temporary list
                tempSortedFloors.add(currentFloorSize);
            }

        }
    }

    private static void addFloorsForTheDay(int dayNo, int currentFloorSize) {

        List<Integer> floorList = new ArrayList<Integer>();
        // add the first floor needs to be placed, which is the current floor
        floorList.add(currentFloorSize);
        if (tempSortedFloors.size() > 0)
        {
            TreeSet<Integer> descSortedSet = (TreeSet<Integer>)tempSortedFloors.descendingSet();
            for (int floor:
                    descSortedSet) {
                floorList.add(floor);
            }
        }
        // add the floor placing plan for the specific day as main result structure
        constructionPlanMap.put(dayNo,floorList);

        // once all floors are added for the day, clean the temporary floors
        tempSortedFloors.clear();
    }

    private static void receiveConstructedFloor(int totalFloor, Scanner sc) {

        for (int day = 1; day <= totalFloor; day++) {
            System.out.println("enter the floor size given on day: " + day);
            int receivedFloor = sc.nextInt();
            recievedFloor.add(receivedFloor);
        }
    }
}
