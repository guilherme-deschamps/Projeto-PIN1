import React, { Component } from "react";
import {
  Card,
  Grid,
  CardActionArea,
  CardMedia,
  CardContent,
  Typography,
  Button
} from "@material-ui/core";

export default class ProductCard extends Component {
  render() {
    const { produto, addToCartMethod } = this.props;
    return (
      <Grid item xs={3} style={{ textAlign: "center", marginBottom: 50 }}>
        <Card>
          <CardActionArea>
            <CardMedia
              height="70"
              component="img"
              image="../../../images/pokemon.png"
            />
          </CardActionArea>
          <CardContent>
            <Typography
              gutterBottom
              style={{ marginBottom: 15, fontSize: "medium" }}
            >
              {produto.nome}
            </Typography>
            <Grid container direction="column">
              <Grid item xs={12}>
                {/* <Typography variant={"h3"} style={{ color: "#e6e600" }}> */}
                <Typography variant={"h3"}>R$ {produto.preco}</Typography>
                <Typography variant={"body2"}>{produto.undMedida}</Typography>
              </Grid>
              <Grid item style={{ marginTop: 20 }}>
                <Button
                  color="secondary"
                  variant="outlined"
                  fullWidth
                  onClick={() => addToCartMethod(produto)}
                >
                  Adicionar ao Carrinho
                </Button>
              </Grid>
            </Grid>
          </CardContent>
        </Card>
      </Grid>
    );
  }
}
