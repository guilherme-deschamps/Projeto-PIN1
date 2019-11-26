import React, { Component } from "react";
import { withStyles } from "@material-ui/core";
import Snackbar from "@material-ui/core/Snackbar";
import IconButton from "@material-ui/core/IconButton";
import CloseIcon from "@material-ui/icons/Close";

const useStyles = theme => ({
  close: {
    padding: theme.spacing(0.5)
  }
});

class MySnackbar extends Component {
  render() {
    const { classes, open, title, onClose } = this.props;
    return (
      <div>
        <Snackbar
          anchorOrigin={{
            vertical: "top",
            horizontal: "center"
          }}
          open={open}
          autoHideDuration={6000}
          onClose={onClose}
          ContentProps={{
            "aria-describedby": "message-id"
          }}
          message={<span id="message-id">{title}</span>}
          action={[
            <IconButton
              key="close"
              aria-label="close"
              color="inherit"
              className={classes.close}
              onClick={onClose}
            >
              <CloseIcon />
            </IconButton>
          ]}
        />
      </div>
    );
  }
}

export default withStyles(useStyles)(MySnackbar);
