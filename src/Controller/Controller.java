package Controller;

import DataStructures.MyDictionary;
import Exceptions.MyException;
import States.PrgState;
import Repository.IRepository;
import Values.RefValue;
import Values.Value;

import java.util.ArrayList;
import java.util.Map;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    private PrgState Currentstate;
    private ExecutorService executor;
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

    ///Get the addresses from all the RefValues from the SymTables
    List<Value> getSymTableValues(){
        //All the SymTables from the PrgStates
        List<Value> symTable = new ArrayList<>();
        for (PrgState p:repo.getPrgList()) {
            for(Value v:p.getSymTable().getContent().values())
                symTable.add(v);
        }
        return symTable;
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
    public void OneStepForAllPrg(List<PrgState> prgList){
            prgList.forEach(prg-> {
                try {
                    repo.logPrgStateExec(prg);
                } catch (MyException e) {
                    e.printStackTrace();
                }
            });
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = null;
        try {
            newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        prgList.addAll(newPrgList);
        prgList.forEach(prg-> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
        repo.setPrgList(prgList);

    }
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p->p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void allStep() throws MyException{
        executor= Executors.newFixedThreadPool(2);
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size()>0){
            safeGarbageCollector(getAddrFromSymTable(getSymTableValues()),getAddrFromHeap(getSymTableValues()),Currentstate.getHeap().getContent());
            OneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        repo.setPrgList(prgList);

    }

}
