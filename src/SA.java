/* *********************************************************************
 * ********* Author¡¯s name(s): Yifan Chen
 * Course Title: Artificial Intelligence
 * Semester: Fall 2017
 * Assignment Number 2
 * Submission Date: 10/16/2017
 * Purpose: This program simulates the TSP Problem which has 5 places to be visited.
 * Input: java SA
 * Output: A list of 10 arbitrary S states (selected randomly) along with along with the corresponding altered state S¡¯, E() and ¦¤E values.
 * Help: I worked alone.
 * ************************************************************************
 * ****** */

public class SA {
	//****************************************************** 
	//*** Purpose: Beginning of the program and call the Simulated Annealing method to find the shortest path.	
	//*** Input:  None
	//*** Output: A list of 10 arbitrary S states (selected randomly) along with along with the corresponding altered state S¡¯, E() and ¦¤E values.
	//***		  The distance and path of global optimal solution.
	//******************************************************
    public static void main(String[] args) {
        SimulatedAnnealing sa=new SimulatedAnnealing();
        sa.initTour();
        System.out.println("Tour S                                                                                        Tour S'                                    E(S)  E(S')  ¦¤E");
        Tour besTour = sa.anneal();
        System.out.println("Final solution distance: " +besTour.getDistance());
        System.out.println("Tour: " + besTour);
    }
}