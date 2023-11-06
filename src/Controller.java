
import Exceptions.MyException;
import States.PrgState;
import Repository.IRepository;
public class Controller {
    private IRepository repo;
    private PrgState Currentstate;

    //if flag is 0 then it won't print the state's info
    //if flag is 1 then it will print the state's info
    private int showFlag=0;
    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public void setFlag(){
        if(showFlag==0)
            showFlag=1;
        else
            showFlag=0;
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

    public void chooseState(int index) throws MyException{
        if(index >= repo.size())
            throw new MyException("Invalid index");
        else
        Currentstate = repo.get(index);
    }
    public void oneStep(){
        Currentstate.oneStep();
    }

    public void allStep(){
        while(Currentstate.getStk().isEmpty()==false){
            oneStep();
            if(showFlag==1)
                System.out.println(Currentstate.toString());
        }
        System.out.println(Currentstate.getOut().toString());
    }
}
