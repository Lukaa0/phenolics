import React, { useEffect, useState } from 'react';

import DataTable from '../components/DataTable';
import PageLayout from '../components/PageLayout';

import { NAV_LINKS } from '../constants/navLinks';
import { getData } from '../services/apiServices';

const MoleculeListPage = () => {
  const [data, setData] = useState([]);
  
  useEffect(() => {
    (async function fetchData() {
      const data = await getData('/molecules');
      if (data) {
        setData(data);
      }
    })();
  },[]);
  
  return (
    <PageLayout>
      <h1>{'Molecule list'}</h1>
      <DataTable header={'All molecules'} rows={data} navTo={NAV_LINKS.MOLECULE_DETAIL} />
    </PageLayout>
  );
};

export  default MoleculeListPage;
