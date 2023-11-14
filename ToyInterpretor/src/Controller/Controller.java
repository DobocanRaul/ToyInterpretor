package Controller;

import Exceptions.MyException;
import States.PrgState;
import Repository.IRepository;
public class Controller {
    private IRepository repo;
    private PrgState Currentstate;

    //if flag is 0 then it won't print the state's info
    //if flag is 1 then it will print the state's info
    private int showFlag=1;
    private int alreadyExecuted=0;
    public Controller(IRepository repo) {
        this.repo = repo;
        this.Currentstate = repo.get(0);
    }

    public void clean(){
        Currentstate.clean();
    }
    public void add(PrgState obj) {
        repo.add(obj);
    }

    public void remove(int index) {
        repo.remove(index);
    }

    public PrgState get(int index) {
        return repo.get(index);
    }

    public int size() {
        return repo.size();
    }

    public void oneStep(){
        Currentstate.oneStep();
    }

    public void allStep(){
        if (alreadyExecuted==0){

            alreadyExecuted=1;
        }
        else {
            Currentstate.clean();
        }
        try {
            repo.logPrgStateExec();
        } catch (MyException e) {
            System.out.println(e.toString());
        }
        while(Currentstate.getStk().isEmpty()==false){
            oneStep();
            try {
                repo.logPrgStateExec();
            } catch (MyException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(Currentstate.getOut().toString());
    }
}
