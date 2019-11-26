import React, { Component } from "react";
import ButtonAppBar from "../../commons/AppBar";
import { Typography, Grid, Card, Divider } from "@material-ui/core";
import CartItemCard from "../../commons/CartItemCard";

export default class ViewCart extends Component {
  state = {
    vl_total: 0
  };
  render() {
    const { user, products } = this.props;
    return (
      <div>
        <ButtonAppBar />
        <center>
          <Card>
            <Grid container direction="column" justify="center">
              <Grid item xs={12}>
                Carrinho
              </Grid>
              <Grid item xs={12}>
                <Divider light />
              </Grid>
              {products.map(item => (
                // this.setState({ vl_total: vl_total + item.preco })
                <Grid container>
                  <CartItemCard product={item} />
                </Grid>
              ))}
            </Grid>
            <Grid item xs={1}>
              <Divider direction="vertical" light />
            </Grid>
            <Grid item xs={4}>
              <Typography style={{ marginTop: 70 }} variant="h4">
                Total
              </Typography>
              <Typography style={{ marginTop: 10 }} variant="h2">
                {this.state.vl_total}
              </Typography>
            </Grid>
          </Card>
        </center>
      </div>
    );
  }
}
