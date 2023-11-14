package Commands;

import Controller.Controller;

public class RunExample extends Command{
    private Controller ctr;

    public RunExample(String key, String description, Controller ctr) {
        super(key, description);
        this.ctr = ctr;
    }

    public void execute() {
        try {
            ctr.allStep();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
