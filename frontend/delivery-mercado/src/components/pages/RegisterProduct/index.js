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
  state = {
    nome: "",
    marca: "",
    preco: 0,
    undMedida: "",
    categoria: "",
    novaCategoria: {}
  };

  handleChange = event => {
    this.setState({
      [event.target.name]: event.target.value
    });
  };

  cadastrarCategoria = async () => {
    let formData = new FormData();
    formData.append("nome", this.state.categoria);
    formData.append(
      "id_supermercado",
      this.props.location.state.supermarket.id
    );
    let response = await fetch("http://localhost:9090/api/categoria/cadastro", {
      method: "POST",
      body: formData
    }).then(res => res.json());
    this.setState({
      novaCategoria: response
    });
  };

  product = async () => {
    let categoria = this.props.location.state.supermarket.categorias.filter(
      item => item.nome === this.state.categoria
    );
    let formData = new FormData();
    formData.append("nome", this.state.nome);
    formData.append("preco", this.state.preco);
    formData.append("marca", this.state.marca);
    formData.append("unidade_medida", this.state.undMedida);
    formData.append("id_categoria", categoria[0].id);

    let response = await fetch("http://localhost:9090/api/produto/cadastro", {
      method: "POST",
      body: formData
    }).then(res => res.json());
  };

  render() {
    const { user, supermarket } = this.props.location.state;

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
              <Grid item xs={3} style={{ padding: 20 }}>
                <Typography variant="h4">Produto</Typography>
              </Grid>
              <Grid item xs={12} style={{ marginBottom: 20 }}>
                <Divider light variant="middle" />
              </Grid>
              <Grid item xs={12}>
                <Grid container justify="center" alignItems="center">
                  <Grid item xs={6}>
                    <TextField
                      variant="outlined"
                      name="nome"
                      label="Nome"
                      fullWidth
                      onChange={e => this.handleChange(e)}
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <TextField
                      variant="outlined"
                      name="marca"
                      label="Marca"
                      fullWidth
                      onChange={e => this.handleChange(e)}
                    />
                  </Grid>
                </Grid>
                <Grid container justify="center" alignItems="center">
                  <Grid item xs={6}>
                    <TextField
                      variant="outlined"
                      name="preco"
                      label="Preço"
                      fullWidth
                      onChange={e => this.handleChange(e)}
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <TextField
                      variant="outlined"
                      name="undMedida"
                      label="Unidade"
                      fullWidth
                      onChange={e => this.handleChange(e)}
                    />
                  </Grid>
                </Grid>
              </Grid>
              <Grid container justify="flex-start" alignItems="center">
                <Grid item xs={6}>
                  {console.log("aqui: ", supermarket.categorias)}
                  {supermarket.categorias.length !== 0 ? (
                    <TextField
                      required
                      id="outlined-select-currency"
                      select
                      autoComplete=""
                      label="Categoria"
                      name="categoria"
                      value={this.state.categoria || ""}
                      fullWidth
                      onChange={this.handleChange}
                      margin="dense"
                      variant="outlined"
                    >
                      {supermarket.categorias.map(option => (
                        <MenuItem key={option} value={option.nome}>
                          {option.nome}
                        </MenuItem>
                      ))}
                    </TextField>
                  ) : (
                    <>
                      <TextField
                        variant="outlined"
                        name="categoria"
                        label="Categoria"
                        fullWidth
                        onChange={e => this.handleChange(e)}
                      />
                    </>
                  )}
                </Grid>
                <Grid item xs={2}>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={this.cadastrarCategoria}
                    fullWidth
                  >
                    Cadastrar categoria
                  </Button>
                </Grid>
              </Grid>
              <Grid
                container
                justify="flex-end"
                alignItems="center"
                style={{ marginTop: 20 }}
              >
                <Grid item xs={12} md={2}>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={this.product}
                    fullWidth
                  >
                    Cadastrar
                  </Button>
                </Grid>
              </Grid>
            </Grid>
          </Card>
        </center>
      </div>
    );
  }
}
