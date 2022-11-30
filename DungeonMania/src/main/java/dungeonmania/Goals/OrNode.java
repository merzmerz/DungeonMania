package dungeonmania.Goals;

public class OrNode implements GoalNode {
    private GoalNode e1;
    private GoalNode e2;

    public OrNode(GoalNode e1, GoalNode e2) {
        this.e1 = e1;
        this.e2 = e2;
    }


    @Override
    public boolean compute() {
        return (e1.compute() || e2.compute());
    } 
    
}

