import { Component } from "react";
import { Card, Grid, CardMedia, Typography } from "@material-ui/core";

export default class CartItemCard extends Component {
  render() {
    const { product } = this.props;
    return (
      <Grid item xs={7}>
        <Card>
          <Grid container>
            <Grid item xs={8}>
              <Grid container justify="space-around">
                <Grid item xs={3}>
                  <CardMedia />
                </Grid>
                <Grid container>
                  <Grid item xs={3}>
                    <Typography variant="body1">{product.nome}</Typography>
                  </Grid>
                  <Grid item xs={3}>
                    <Typography variant="body1">
                      Marca: {product.marca}
                    </Typography>
                  </Grid>
                  <Grid item xs={3}>
                    <Typography variant="body1">
                      Quantidade: {product.qtd}
                    </Typography>
                  </Grid>
                  <Grid item xs={5}>
                    Valor: R$ {product.preco} por {product.undMedida}
                  </Grid>
                  <Grid item xs={5}>
                    Total: R$ {product.preco * product.qtd}
                  </Grid>
                </Grid>
              </Grid>
            </Grid>
          </Grid>
        </Card>
      </Grid>
    );
  }
}
