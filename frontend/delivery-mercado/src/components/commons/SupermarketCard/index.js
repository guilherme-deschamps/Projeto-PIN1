import React, { Component } from "react";
import {
  CardMedia,
  Typography,
  Grid,
  Card,
  CardContent
} from "@material-ui/core";
import { Link } from "react-router-dom";

class Supermarketcard extends Component {
  render() {
    const { user, supermarket } = this.props;
    return (
      <Grid item>
        <Link
          to={{
            pathname: `supermercado/${supermarket.nome}`,
            state: {
              supermarket: supermarket,
              user: user
            }
          }}
          style={{ textDecoration: "none" }}
        >
          <Card
            style={{
              marginBottom: 50,
              width: 1280,
              backgroundColor: "#DC7633"
            }}
          >
            <CardContent>
              <Grid container>
                <Grid item md={4}>
                  <CardMedia image="../../images/pokemon.png" title="Pokemon" />
                </Grid>
                <Grid item md={8}>
                  <Grid container>
                    <Grid item xs={6}>
                      <Typography variant="h6">{supermarket.nome}</Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography variant="h6">
                        {supermarket.endereco.cidade}
                      </Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography variant="h6">
                        telefone: {supermarket.telefone}
                      </Typography>
                    </Grid>
                    <Grid item xs={6}>
                      <Typography variant="h6">
                        contato: {supermarket.email}
                      </Typography>
                    </Grid>
                  </Grid>
                </Grid>
              </Grid>
            </CardContent>
          </Card>
        </Link>
      </Grid>
    );
  }
}

export default Supermarketcard;
