package crud;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Dados {
    private static final List dados = new LinkedList();
    private Dados(){
    }
    public static void salvar(Object o){
        if(dados.contains(o))
            dados.set(dados.indexOf(o),o);
        else dados.add(o);
    }
    public static void salvar(Collection o){
        dados.addAll(o);
    }
    public static List getLista(){
        return dados;
    }

    public static void remove(int i) {
        dados.remove(i);
    }
}