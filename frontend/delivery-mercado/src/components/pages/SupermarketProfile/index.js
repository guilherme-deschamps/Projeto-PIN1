import React, { Component } from "react";
import ButtonAppBar from "../../commons/AppBar";
import { Typography, Grid, Card, Divider, Button } from "@material-ui/core";
import FilterButton from "../../commons/FilterButton";
import ProductCard from "../../commons/ProductCard";
import Logo from "../../../images/pokemon.png";
import { Link } from "react-router-dom";

export default class SupermarketProfile extends Component {
  state = {
    cart: []
  };

  render() {
    const { user, supermarket } = this.props.location.state;
    return (
      <div>
        <ButtonAppBar
          user={user}
          cart={this.state.cart}
          supermarket={supermarket}
        />
        <center>
          <Card
            style={{ width: 1000, marginTop: 50, backgroundColor: "#DDDDDD" }}
          >
            <Grid
              container
              justify="space-between"
              style={{ marginTop: 20 }}
              alignItems="center"
            >
              <Grid item xs={3}>
                <img
                  src={Logo}
                  alt={`Supermercado`}
                  style={{ width: 200, height: 100 }}
                />
              </Grid>
              <Grid item xs={9}>
                <Grid container justify="center">
                  <Grid item xs={6}>
                    <Typography variant="body1">{supermarket.nome}</Typography>
                  </Grid>
                  <Grid item xs={6}>
                    <Typography variant="body1">
                      CNPJ: {supermarket.cnpj}
                    </Typography>
                  </Grid>
                  <Grid item xs={6}>
                    <Typography variant="body1">
                      E-mail: {supermarket.email}
                    </Typography>
                  </Grid>
                  <Grid item xs={6}>
                    <Typography variant="body1">
                      Telefone: {supermarket.telefone}
                    </Typography>
                  </Grid>
                </Grid>
              </Grid>
              <Grid item xs={12} style={{ marginTop: 20, marginBottom: 20 }}>
                <Divider light variant="middle" />
              </Grid>
              <Grid item xs={4}>
                <Typography>CEP: {supermarket.endereco.cep}</Typography>
              </Grid>
              <Grid item xs={4}>
                <Typography>Cidade: {supermarket.endereco.cidade}</Typography>
              </Grid>
              <Grid item xs={4}>
                <Typography>Bairro: {supermarket.endereco.bairro}</Typography>
              </Grid>
              <Grid item xs={8} style={{ marginBottom: 20, marginTop: 5 }}>
                <Typography>Rua: {supermarket.endereco.logradouro}</Typography>
              </Grid>
              <Grid item xs={4} style={{ marginBottom: 20, marginTop: 5 }}>
                <Typography>Número: {supermarket.endereco.numero}</Typography>
              </Grid>
              {user && supermarket.idUsuario === user.id && (
                <Grid item xs={12} style={{ marginBottom: 20 }}>
                  <Grid container justify="center">
                    <Grid item xs={8} style={{ marginTop: 5 }}>
                      <Divider light variant="middle" />
                    </Grid>
                    <Grid item xs={4} style={{ marginTop: 20 }}>
                      <Link
                        to={{
                          pathname: `../supermercado/${supermarket.nome}/produto`,
                          state: { user: user, supermarket: supermarket }
                        }}
                        style={{ textDecoration: "none" }}
                      >
                        <Button variant="contained" color="secondary">
                          Adicionar produto
                        </Button>
                      </Link>
                    </Grid>
                  </Grid>
                </Grid>
              )}
            </Grid>
          </Card>
        </center>
        <Grid
          container
          justify="flex-start"
          style={{ width: 700, marginLeft: 80, marginTop: 50 }}
        >
          <Grid item xs={3}>
            <Typography variant={"h6"}>Filtros: </Typography>
          </Grid>
          <Grid item xs={3}>
            <FilterButton />
          </Grid>
        </Grid>
        {supermarket !== undefined && (
          <center>
            <Grid
              container
              justify="space-around"
              style={{ width: 1280, marginTop: 50 }}
            >
              {supermarket.categorias.map(categoria =>
                categoria.produtos.map(produto => (
                  <ProductCard
                    produto={produto}
                    addToCartMethod={this.addToCard}
                  />
                ))
              )}
            </Grid>
          </center>
        )}
      </div>
    );
  }

  addToCard = product => {
    let tempCart = this.state.cart;
    tempCart.push(product);
    this.setState({
      cart: tempCart
    });
    console.log("Cart: ", this.state.cart);
  };
}
