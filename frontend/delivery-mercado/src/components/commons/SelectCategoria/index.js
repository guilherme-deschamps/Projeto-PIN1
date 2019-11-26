import React, { Component } from "react";
import { FormControl, InputLabel, Select, MenuItem } from "@material-ui/core";

export default class SelectCategoria extends Component {
  state = {
    age: null
  };

  render() {
    const { supermarket } = this.props;

    const handleChange = event => {
      this.setState({ age: event.target.value });
    };

    return (
      <FormControl>
        <InputLabel id="demo-customized-select-label">Age</InputLabel>
        <Select
          labelId="demo-customized-select-label"
          id="demo-customized-select"
          value={this.state.age}
          onChange={handleChange}
          //   input={<BootstrapInput />}
        >
          <MenuItem value="">
            <em>None</em>
          </MenuItem>
          <MenuItem value={10}>Ten</MenuItem>
          <MenuItem value={20}>Twenty</MenuItem>
          <MenuItem value={30}>Thirty</MenuItem>
        </Select>
      </FormControl>
    );
  }
}
