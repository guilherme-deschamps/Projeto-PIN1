import React, { Component } from "react";
import { Typography, Grid } from "@material-ui/core";
import ButtonAppBar from "../../commons/AppBar";
import SupermarketCard from "../../commons/SupermarketCard";

export default class Supermarket extends Component {
  state = {
    supermercados: [],
    user: null
  };

  getSupermercados = async () => {
    let response = await fetch(
      "http://localhost:9090/api/supermercados"
    ).then(res => res.json());
    this.setState({ supermercados: response });
  };

  componentWillMount = async () => {
    this.getUser();
    this.getSupermercados();
  };

  getUser = async () => {
    if (
      localStorage.getItem("userId") !== undefined &&
      localStorage.getItem("userId") !== null
    ) {
      const response = await fetch(
        `http://localhost:9090/api/usuario/${localStorage.getItem("userId")}`
      ).then(res => res.json());
      this.setState({ user: response });
    } else {
      this.setState({ user: null });
    }
  };

  render() {
    return (
      <div>
        <ButtonAppBar user={this.state.user} cart={[]} />
        <Typography
          style={{ textAlign: "center", marginTop: 60 }}
          variant="h3"
          xs={12}
        >
          Supermercados
        </Typography>
        <Grid
          container
          justify="center"
          alignItems="center"
          direction="column"
          style={{ marginTop: 80 }}
        >
          {console.log(this.state)}
          {this.state.supermercados.map(item => (
            <Grid item xs={12}>
              <SupermarketCard supermarket={item} user={this.state.user} />
            </Grid>
          ))}
        </Grid>
      </div>
    );
  }
}
