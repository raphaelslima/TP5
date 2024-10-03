public class Rotacoes {

    // Verifica qual rotação usar para balancear a árvore
    public Nodo verificaBalancemaneto(Nodo atual) {
        if (atual.getBal() > 1) {// se o balanceamento está pesando à direita
            if (atual.getEsquerda() != null && atual.getEsquerda().getBal() < 0) { //RL
                atual.setEsquerda(rotacaoSimplesEsquerda(atual.getEsquerda()));
            }
            return rotacaoSimplesDireita(atual);
        }
    
        if (atual.getBal() < -1) { // se o balanceamento está pesando à esquerda
            if (atual.getDireita() != null && atual.getDireita().getBal() > 0) {
                atual.setDireita(rotacaoSimplesDireita(atual.getDireita())); //LR
            }
            return rotacaoSimplesEsquerda(atual);
        }
    
        return atual; 
    }
    
    public Nodo rotacaoSimplesDireita(Nodo atual) {
        if(atual.getEsquerda() == null){
            return atual;
        }
        Nodo raiz = atual.getEsquerda(); // novo pai será o filho 
        atual.setEsquerda(raiz.getDireita()); // move o filho para o lugar do pai
        raiz.setDireita(atual); // pai agora é filho
        atual.calculaBal(); // recalcula o balanceamento do pai
        raiz.calculaBal(); // recalcula o balanceamento do novo pai
        return raiz; // nova raiz da subárvore
    }

    public Nodo rotacaoSimplesEsquerda(Nodo atual) {
        if(atual.getDireita() == null){
            return atual;
        }
        Nodo raiz = atual.getDireita(); // novo pai será o filho 
        atual.setDireita(raiz.getEsquerda()); // move o filho para o lugar do pai
        raiz.setEsquerda(atual); // pai agora é filho
        atual.calculaBal(); // recalcula o balanceamento do pai
        raiz.calculaBal(); // recalcula o balanceamento do novo pai
        return raiz; // nova raiz da subárvore     
    }

    public Nodo rotacaoDuplaEsquerda(Nodo atual) {
        atual.setEsquerda(rotacaoSimplesEsquerda(atual.getEsquerda())); // rotação simples à esquerda no filho esquerdo
        return rotacaoSimplesDireita(atual); // rotação simples à direita na raiz
    }

    public Nodo rotacaoDuplaDireita(Nodo atual) {
        atual.setDireita(rotacaoSimplesDireita(atual.getDireita())); // rotação simples à direita no filho direito
        return rotacaoSimplesEsquerda(atual); // rotação simples à esquerda na raiz
    }
}
