package Controller;

import Exceptions.MyException;
import States.PrgState;
import Repository.IRepository;
import Values.RefValue;
import Values.Value;

import java.util.Map;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    List<Integer> getAddrFromHeap(Collection<Value> heapValues){
        return heapValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }
    Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr,List<Integer> heapAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e-> (symTableAddr.contains(e.getKey())|| heapAddr.contains(e.getKey())))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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
            Currentstate.getHeap().setContent(safeGarbageCollector(
                    getAddrFromSymTable(Currentstate.getSymTable().getContent()),getAddrFromHeap(Currentstate.getHeap().getValues()),
                    Currentstate.getHeap().getContent()));
            try {
                repo.logPrgStateExec();
            } catch (MyException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(Currentstate.getOut().toString());
    }
}
