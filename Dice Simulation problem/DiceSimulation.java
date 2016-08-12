class Die {
    // Since "throw" is a Java keyword, we use "roll"
    int roll() {
        return 1 + (int)(6 * Math.random());
    }
}

public class DiceSimulation {
    private int trials = 0,
                profit = 0,
                won    = 0,
                lost   = 0;

    private Die die1 = new Die(),
                die2 = new Die();

    // Constructor to set the number of trials
    public DiceSimulation(int newTrials){
        trials = newTrials;
    }

    /**
     * One trial consists of rolling a pair of dice and calculating the sum.
     * The sum is then checked with the given values in the problem,
     * Profit and Loss is calculated based on the sum.
     */
    public int runTrial() {
        // Sum of both the pair of the dies
        int pairSum = die1.roll() + die2.roll();
        // Switch case for every possible sum value
        switch (pairSum) {
            case 12:
                profit += 150;
                won += 1;
                break;
            case 11:
                profit += 100;
                won += 1;
                break;  
            case 10:
                profit += 50;
                won += 1;
                break;
            case 7:
            case 8:
            case 9:
                won += 1;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                profit -= 50;
                lost += 1;
                break;
            default:
                System.out.println("Something went wrong in rolling dice!");
                break;
        }
        return pairSum;
    }

    public void report() {
        System.out.println("After " + trials + " simulations: ");
        System.out.println("Total win: " + won);
        System.out.println("Total loss: " + lost);
        System.out.println("Total net profit: " + (double)profit + " cents");
        System.out.println("The average net profit in each game: " + (double)profit/trials + " cents");
        if((double)profit > 0){
            System.out.println("You're making profit! It is a good idea to play in this game!");
        }
        else{
            System.out.println("You're making loss! It is not a good idea to play in this game!");
        }
    }

    public static void main(String[] args) {
        // get the trial count input from the user
        // int trials = Integer.parseInt(args[0]);
        int trials = 1000;
        DiceSimulation sim = new DiceSimulation(trials);
        // Running the simulation for given count
        for (int count = 0; count < trials; count++) {
            sim.runTrial();
        }
        // Reporting the end result
        sim.report();
    }
}