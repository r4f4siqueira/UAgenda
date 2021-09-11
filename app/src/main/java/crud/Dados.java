package crud;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import Entidades.Contato;

public class Dados {
    private static final List dados = new LinkedList();
    private static Integer count=1;
    private Dados(){
    }
    public static void salvar(Contato c){
        if(dados.contains(c)) {
            dados.set(dados.indexOf(c), c);
        }
        else{
            c.setId(count++);
            dados.add(c);
        }
    }

    public static void salvar(Collection c){
        dados.addAll(c);
    }
    public static List getLista(){
        return dados;
    }

    public static void remove(int i) {
        dados.remove(i);
    }
}