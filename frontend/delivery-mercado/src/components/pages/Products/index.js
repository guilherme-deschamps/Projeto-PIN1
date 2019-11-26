import React, { Component } from "react";
import ButtonAppBar from "../../commons/AppBar";
import { Typography, Grid } from "@material-ui/core";
import FilterButton from "../../commons/FilterButton";
import ProductCard from "../../commons/ProductCard";

export default class Products extends Component {
  state = {
    cart: []
  };

  addToCard = product => {
    let tempCart = this.state.cart;
    tempCart.push(product);
    this.setState({
      cart: tempCart
    });
    console.log(this.state.cart);
  };

  render() {
    let produtos = [
      {
        key: 1,
        name: "Banana",
        preco: 2.0,
        marca: "Orta",
        und_medida: "quilo"
      },
      {
        key: 2,
        name: "Arroz",
        preco: 3.99,
        marca: "Urbano",
        und_medida: "unidade"
      },
      {
        key: 3,
        name: "Feijão",
        preco: 19.99,
        marca: "Tio Nico",
        und_medida: "unidade"
      }
    ];

    return (
      <div>
        <ButtonAppBar />
        <Grid
          container
          justify="flex-start"
          style={{ width: 700, marginLeft: 80, marginTop: 100 }}
        >
          <Grid item xs={3}>
            <Typography variant={"h6"}>Filtros: </Typography>
          </Grid>
          <Grid item xs={3}>
            <FilterButton />
          </Grid>
        </Grid>
        <center>
          <Grid
            container
            justify="space-around"
            style={{ width: 1280, marginTop: 50 }}
          >
            {produtos.map(item => (
              <ProductCard produto={item} addToCartMethod={this.addToCard} />
            ))}
          </Grid>
        </center>
      </div>
    );
  }
}
