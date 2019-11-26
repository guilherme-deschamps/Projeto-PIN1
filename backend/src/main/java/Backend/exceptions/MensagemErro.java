package Backend.exceptions;

public class MensagemErro {

    private String mensagem;

    public MensagemErro(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
