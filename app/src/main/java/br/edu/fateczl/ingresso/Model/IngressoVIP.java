package br.edu.fateczl.ingresso.Model;

public class IngressoVIP extends Ingresso {
    private String funcao;

    public IngressoVIP(String codigoIdentificador, float valor, String funcao) {
        super(codigoIdentificador, valor);
        this.funcao = funcao;
    }

    @Override
    public float valorFinal(float taxaConveniencia) {
        float valorBase = super.valorFinal(taxaConveniencia);
        return valorBase * 1.18f;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}