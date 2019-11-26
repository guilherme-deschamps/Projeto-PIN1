import React, { Component } from "react";
import {
  Typography,
  Button,
  TextField,
  Grid,
  Card,
  Divider
} from "@material-ui/core";
import { Link } from "react-router-dom";
import history from "../../../history";
import { Upload, Icon, message } from "antd";
import "./AntStylesheet.css";
import CloudDownloadRounded from "@material-ui/icons/CloudDownloadRounded";

const { Dragger } = Upload;

class RegisterSupermarket extends Component {
  state = {
    name: "",
    cnpj: "",
    email: "",
    fone: "",
    cep: "",
    city: "",
    neighborhood: "",
    street: "",
    number: "",
    imgSupermarket: null,
    arquivos: []
  };

  async handleFileChange(info) {
    let fileListSlice = [...info.fileList];
    fileListSlice = fileListSlice.slice(-this.props.filesLimit);

    fileListSlice = fileListSlice.map(file => {
      return file;
    });
    let fileList = fileListSlice;

    let fileListFinal = [];

    fileList.map(file => {
      fileListFinal.push(file.originFileObj);
    });
    this.setState({
      arquivos: fileListFinal
    });
    console.log(this.state.arquivos);
  }

  handleChange = e => {
    console.log("Evento: ", e.target.value);
    this.setState({
      [e.target.name]: e.target.value
    });
  };

  register = async user => {
    let formData = new FormData();

    formData.append("nome", this.state.name);
    formData.append("telefone", this.state.fone);
    formData.append("email", this.state.email);
    formData.append("cnpj", this.state.cnpj);
    formData.append("end_cep", this.state.cep);
    formData.append("end_cidade", this.state.city);
    formData.append("end_bairro", this.state.neighborhood);
    formData.append("end_logradouro", this.state.street);
    formData.append("end_numero", this.state.number);
    for (const file of this.state.arquivos)
      formData.append("img_supermercado", file);

    await fetch(
      `http://localhost:9090/api/supermercado/cadastro/usuario/${user.id}`,
      {
        method: "POST",
        body: formData
      }
    ).then(function(response) {
      if (response.status === 200) {
        alert("Supermercado cadastrado com sucesso!");
        history.push("/");
      } else {
        alert("Algum dos dados informados é inválido. Tente novamente!");
      }
    });
  };

  render() {
    const { user } = this.props.location.state;
    return (
      <div style={{ backgroundColor: "#ED7644", height: "47.1em" }}>
        <Grid container justify="center">
          <Grid item xs={4}>
            <Card style={{ marginTop: 50, padding: 10 }}>
              <Typography
                variant="h5"
                style={{ textAlign: "center", padding: 15 }}
              >
                Cadastrar supermercado
              </Typography>
              <Grid container justify={"space-between"}>
                <Grid item xs={12}>
                  <TextField
                    variant="outlined"
                    name="name"
                    label="Nome"
                    fullWidth
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={6}>
                  <TextField
                    variant="outlined"
                    name="cnpj"
                    label="CNPJ"
                    fullWidth
                    style={{ marginTop: 10 }}
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={5}>
                  <TextField
                    variant="outlined"
                    name="fone"
                    label="Telefone"
                    fullWidth
                    style={{ marginTop: 10 }}
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    variant="outlined"
                    name="email"
                    label="E-mail"
                    fullWidth
                    style={{ marginTop: 10, marginBottom: 20 }}
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={12}>
                  <Divider light variant="middle" />
                </Grid>
                <Grid item xs={3}>
                  <TextField
                    variant="outlined"
                    name="cep"
                    label="CEP"
                    fullWidth
                    style={{ marginTop: 20 }}
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={8}>
                  <TextField
                    variant="outlined"
                    name="city"
                    fullWidth
                    label="Cidade"
                    style={{ marginTop: 20 }}
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    variant="outlined"
                    name="neighborhood"
                    label="Bairro"
                    fullWidth
                    style={{ marginTop: 10 }}
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={8}>
                  <TextField
                    variant="outlined"
                    name="street"
                    label="Rua"
                    fullWidth
                    style={{ marginTop: 10 }}
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={3}>
                  <TextField
                    variant="outlined"
                    name="number"
                    label="Número"
                    fullWidth
                    style={{ marginTop: 10 }}
                    onChange={e => this.handleChange(e)}
                  />
                </Grid>
                <Grid item xs={12} style={{ marginTop: 20 }}>
                  <Grid container justify="center" alignItems="center">
                    <Grid item xs={7}>
                      <Typography variant="body2">
                        Insira uma foto para o supermercado:
                      </Typography>
                    </Grid>
                    <Grid item xs={4}>
                      <Dragger
                        name="areaUpload"
                        multiple={true}
                        action="https://www.mocky.io/v2/5185415ba171ea3a00704eed"
                        onChange={info => this.handleFileChange(info)}
                        showPreviewIcon={false}
                        showRemoveIcon={false}
                        filesLimit={1}
                        fileList={this.state.fileList}
                        style={{ minHeight: 50 }}
                      >
                        <center>
                          <p className="ant-upload-drag-icon">
                            <CloudDownloadRounded
                              color="primary"
                              style={{ fontSize: 20 }}
                            />
                          </p>
                          <p className="ant-upload-text">Area de upload</p>
                        </center>
                      </Dragger>
                    </Grid>
                  </Grid>
                </Grid>
              </Grid>
              <Grid container justify="space-between" style={{ marginTop: 20 }}>
                <Grid>
                  <Link to="/" style={{ textDecoration: "none" }}>
                    <Button
                      color="secondary"
                      variant="outlined"
                      fullWidth
                      onClick={() => {}}
                    >
                      Voltar
                    </Button>
                  </Link>
                </Grid>
                <Grid>
                  <Button
                    color="secondary"
                    variant="contained"
                    fullWidth
                    onClick={() => this.register(user)}
                  >
                    Cadastrar
                  </Button>
                </Grid>
              </Grid>
            </Card>
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default RegisterSupermarket;
