import React, { Component } from "react";
import ButtonAppBar from "../../commons/AppBar";
import SelectCategoria from "../../commons/SelectCategoria";
import {
  Typography,
  Grid,
  Card,
  Divider,
  Button,
  TextField,
  MenuItem
} from "@material-ui/core";
import FilterButton from "../../commons/FilterButton";
import ProductCard from "../../commons/ProductCard";
import Logo from "../../../images/pokemon.png";

export default class RegisterProduct extends Component {
  state = {};

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  render() {
    const { user, cart, supermarket } = this.props.location.state;
    console.log(cart);
    return (
      <div>
        <ButtonAppBar user={user} />

        <center>
          <Card
            style={{
              width: 1280,
              marginTop: 50,
              backgroundColor: "#DDDDDD"
            }}
          >
            <Grid container justify="center">
              <Grid item xs={12} style={{ padding: 20 }}>
                <Typography variant="h4">Carrinho</Typography>
              </Grid>
              <Grid item xs={12} style={{ marginBottom: 20 }}>
                <Divider light variant="middle" />
              </Grid>
              <Grid container justify="space-between">
                {cart.map(produto => (
                  <Grid item xs={3}>
                    <Card style={{ width: 800, backgroundColor: "#DC7633" }}>
                      <Grid container>
                        <Grid item xs={4} style={{ padding: 20 }}>
                          <Typography variant="h5">{produto.nome}</Typography>
                        </Grid>
                        <Grid item xs={12} style={{ padding: 20 }}>
                          <Divider light variant="middle" />
                        </Grid>
                        <Grid item xs={12}>
                          <Typography variant="h4">
                            R$ {produto.preco}
                          </Typography>
                        </Grid>
                      </Grid>
                    </Card>
                  </Grid>
                ))}
              </Grid>
            </Grid>
          </Card>
        </center>
      </div>
    );
  }
}
