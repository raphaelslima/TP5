public class Rotacoes {


    public Nodo rotacaoSimplesDireita(Nodo atual) {
        Nodo raiz = atual.getEsquerda();
        atual.setEsquerda(raiz.getDireita());
        raiz.setDireita(atual);
        atual.calculaBal(); 
        raiz.calculaBal(); 
        return raiz; 
    }

    public Nodo rotacaoSimplesEsquerda(Nodo atual) {
        Nodo raiz = atual.getDireita();
        atual.setDireita(raiz.getEsquerda());
        raiz.setEsquerda(atual);
        atual.calculaBal(); //
        raiz.calculaBal(); 
        return raiz; 
    }

    public Nodo rotacaoDuplaDireita(Nodo atual) {
        atual.setDireita(rotacaoSimplesDireita(atual.getDireita())); 
        return rotacaoSimplesEsquerda(atual); 
    }

    public Nodo rotacaoDuplaEsquerda(Nodo atual) {
        atual.setEsquerda(rotacaoSimplesEsquerda(atual.getEsquerda())); 
        return rotacaoSimplesDireita(atual); 
    }
}
