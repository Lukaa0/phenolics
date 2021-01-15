import React, { Fragment, useState } from "react";
import { Grid } from "@material-ui/core";

import Search from "../components/Search";
import DataTable from "../components/DataTable";

const searchValue = "";

const PageLayout = (props) => {
  const [value, setValue] = useState(searchValue);

  const handleChange = (e) => {
    const { value } = e.target;
    setValue(value);
  };

  const rows = [
    { id: 1, name: "Search does not work yet. Not connected to API" },
  ];

  return (
    <Fragment>
      <Search value={value} handleChange={handleChange} />
      <Grid container direction="column" justify="center" alignItems="center">
        {value ? <DataTable rows={rows} /> : props.children}
      </Grid>
    </Fragment>
  );
};

export default PageLayout;
