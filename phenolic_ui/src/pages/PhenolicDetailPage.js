import React, { useEffect, useState } from 'react';
import Grid from '@material-ui/core/Grid';

import DataTable from '../components/DataTable';
import PageLayout from '../components/PageLayout';

import { NAV_LINKS } from '../constants/navLinks';
import { DATA_TYPES } from '../constants/dataTypes';
import { getData } from '../services/apiServices';
import useQuery from '../utils/useQuery';

const PhenolicDetailPge = () => {
  const initData = {type: '', values: []};
  
  const [data, setData] = useState({name: ''});
  const [parentData, setParentData] = useState(initData);
  const [childrenData, setChildrenData] = useState(initData);
  
  const queryId = useQuery();
  
  useEffect(() => {
    (async function fetchData() {
      const data = await getData(`/phenolics/${queryId}`);
      if (data) {
        setData(data);
        data.parent ? setParentData(data.parent) : setParentData(initData);
        data.children ? setChildrenData(data.children) : setChildrenData(initData);
      }
    })();
  },[queryId]);
  
  return (
    <PageLayout>
      <h1>{data.name}</h1>
      <Grid
        container
        direction="row"
        justify="center"
        alignItems="center"
        spacing={2}
      >
        <Grid item>
          <h2>{'Parents:'}</h2>
          <DataTable
            header={parentData.type === DATA_TYPES.SOURCE ? 'Sources' : 'Phenolics'}
            rows={parentData.values}
            navTo={parentData.type === DATA_TYPES.SOURCE ? NAV_LINKS.SOURCE_DETAIL : NAV_LINKS.PHENOLIC_DETAIL}
          />
        </Grid>
        <Grid item>
          <h2>{'Children:'}</h2>
          <DataTable
            header={childrenData.type === DATA_TYPES.PHENOLIC ? 'Phenolics' : 'Molecules'}
            rows={childrenData.values}
            navTo={childrenData.type === DATA_TYPES.PHENOLIC ? NAV_LINKS.PHENOLIC_DETAIL : NAV_LINKS.MOLECULE_DETAIL}
          />
        </Grid>
      </Grid>
    </PageLayout>
  );
};

export default PhenolicDetailPge;
