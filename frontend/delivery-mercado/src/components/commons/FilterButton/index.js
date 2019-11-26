import React from "react";
import { ButtonGroup, Button } from "@material-ui/core";
import ArrowDropDownIcon from "@material-ui/icons/ArrowDropDown";

export default function FilterButton() {
  const options = ["Todos", "Categorias", "Preço"];
  const [open, setOpen] = React.useState(false);
  const anchorRef = React.useRef(null);
  const [selectedIndex, setSelectedIndex] = React.useState(0);

  const handleClick = () => {
    console.info(`You clicked ${options[selectedIndex]}`);
  };

  const handleMenuItemClick = (event, index) => {
    setSelectedIndex(index);
    setOpen(false);
  };

  const handleToggle = () => {
    setOpen(prevOpen => !prevOpen);
  };

  const handleClose = event => {
    if (anchorRef.current && anchorRef.current.contains(event.target)) {
      return;
    }

    setOpen(false);
  };

  return (
    <ButtonGroup variant="contained" ref={anchorRef} aria-label="split button">
      <Button onClick={handleClick}>{options[selectedIndex]}</Button>
      <Button
        size="small"
        aria-controls={open ? "split-button-menu" : undefined}
        aria-expanded={open ? "true" : undefined}
        aria-label="select merge strategy"
        aria-haspopup="menu"
        onClick={handleToggle}
      >
        <ArrowDropDownIcon />
      </Button>
    </ButtonGroup>
  );
}
