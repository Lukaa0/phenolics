import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";

const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
    position: 'relative',
    overflow: 'auto',
    maxHeight: 360,
  },
}));

export default function InsetList(props) {
  const classes = useStyles();

  return (
    <List component="nav" className={classes.root} aria-label="contacts">
      {props.items.map((item) => (
        <ListItem>
          <ListItemText key={item.id} primary={item.value} />
        </ListItem>
      ))}
    </List>
  );
}
