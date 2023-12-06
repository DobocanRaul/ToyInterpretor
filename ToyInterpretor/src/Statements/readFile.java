package Statements;

import Exceptions.MyException;
import Expressions.Exp;
import States.PrgState;
import Types.IntType;
import Types.StringType;
import Types.Type;
import Values.IntValue;
import Values.StringValue;
import Values.Value;

import java.io.BufferedReader;
import java.io.*;
public class readFile implements IStmt{
    Exp exp;
    String var_name;

    public readFile(Exp e, String v){
        exp = e;
        var_name = v;
    }

    public String toString(){
        return "readFile(" + exp.toString() + ", " + var_name.toString() + ")";
    }

    public IStmt deepCopy(){
        return new readFile(exp.deepcopy(), var_name);
    }

    public PrgState execute(PrgState state) throws MyException {
        if(state.getSymTable().lookup(var_name))
        {
            if(state.getSymTable().get(var_name).getType().equals(new IntType())){
                Value id=exp.eval(state.getSymTable(),state.getHeap());
                if(id.getType().equals(new StringType())){
                    try{
                        BufferedReader br=state.getFileTable().get((StringValue) id);
                        String line;
                        line=br.readLine();
                        int number=Integer.parseInt(line);
                        state.getSymTable().add(var_name, new IntValue(number));
                    }
                    catch (IOException e){
                        throw new MyException("File not found!");
                    }
                }
                    else throw new MyException("Variable for filepath not of type string!");


                }
                else throw new MyException("Variable not of type int!");
        }
        else{
                throw new MyException ("Variable not defined!");
        }
        return state;
    }

}
