package Backend.services;

import Backend.exceptions.CarrinhoInexistenteException;
import Backend.models.*;
import Backend.repositories.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.AccessException;
import java.util.List;
import java.util.Optional;

import static Backend.models.Carrinho.*;
import static Backend.models.Carrinho.FAZENDO;
import static Backend.models.Carrinho.FEITO;

@Service
public class CarrinhoService {

    @Autowired
    CarrinhoRepository carrinhoRepository;

    public Carrinho cadastraCarrinho(String formaPagamento, double valorTotal, String situacao, Usuario usuario,
                                     Supermercado supermercado, Endereco endereco, List<Produto> produtos) {

        Carrinho novoCarrinho = new Carrinho(formaPagamento, valorTotal, situacao, usuario, supermercado, endereco, produtos);
        return carrinhoRepository.save(novoCarrinho);
    }

    public Carrinho buscaCarrinhoPorId(Long idCarrinho) throws CarrinhoInexistenteException {

        Optional opCarrinho = carrinhoRepository.findById(idCarrinho);
        if (opCarrinho.isPresent())
            return (Carrinho) opCarrinho.get();
        throw new CarrinhoInexistenteException("Carrinho não encontrado com o id especificado.");
    }

    public Carrinho avancaCarrinho(Carrinho carrinho) throws AccessException {

        switch (carrinho.getSituacao()) {
            case CRIADO:
                carrinho.setSituacao(LIDO);
                break;
            case LIDO:
                carrinho.setSituacao(FAZENDO);
                break;
            case FAZENDO:
                carrinho.setSituacao(FEITO);
                break;
            case FEITO:
                carrinho.setSituacao(ENTREGANDO);
                break;
            case ENTREGANDO:
                carrinho.setSituacao(FINALIZADO);
                break;
            case FINALIZADO:
                throw new AccessException("O carrinho ja está finalizado!");
        }
        return carrinhoRepository.save(carrinho);
    }
}
