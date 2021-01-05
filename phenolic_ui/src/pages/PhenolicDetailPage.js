import React, { useEffect } from 'react';
import { useLocation } from "react-router-dom";
import Grid from '@material-ui/core/Grid';

import DataTable from '../components/DataTable';
import PageLayout from '../components/PageLayout';

import { NAV_LINKS } from '../constants/navLinks';

const useQuery = () => {
  const params = new URLSearchParams(useLocation().search);
  
  // TODO After integration with API we should store id and send request to API and from response get name
  return params.get("name");
};

const PhenolicDetailPge = () => {
  const queryId = useQuery();
  useEffect(() => {
    console.log(queryId);
  });
  
  const parentsRows = [
    { id: 1, name: 'Source 1' },
    { id: 2, name: 'Source 2' },
    { id: 3, name: 'Source 3' },
    { id: 4, name: 'Source 4' },
    { id: 5, name: 'Source 5' },
  ];
  
  const childrenRows = [
    { id: 1, name: 'Molecule 1' },
    { id: 2, name: 'Molecule 2' },
    { id: 3, name: 'Molecule 3' },
    { id: 4, name: 'Molecule 4' },
    { id: 5, name: 'Molecule 5' },
  ];
  
  return (
    <PageLayout>
      <h1>{queryId}</h1>
      <Grid
        container
        direction="row"
        justify="center"
        alignItems="center"
        spacing={2}
      >
        <Grid item>
          <h2>{'Parents:'}</h2>
          <DataTable header={'Sources'} rows={parentsRows} navTo={NAV_LINKS.SOURCE_DETAIL} />
        </Grid>
        <Grid item>
          <h2>{'Children:'}</h2>
          <DataTable header={'Molecules'} rows={childrenRows} navTo={NAV_LINKS.MOLECULE_DETAIL} />
        </Grid>
      </Grid>
    </PageLayout>
  );
};

export default PhenolicDetailPge;
