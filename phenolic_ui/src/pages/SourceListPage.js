import React, { useEffect, useState } from 'react';

import DataTable from '../components/DataTable';
import PageLayout from '../components/PageLayout';

import { getData } from '../services/apiServices';
import { NAV_LINKS } from '../constants/navLinks';

const SourceListPage = () => {
  const [data, setData] = useState([]);
  
  useEffect(() => {
    (async function fetchData() {
      const data = await getData('/sources');
      if (data) {
        setData(data);
      }
    })();
  },[]);
  
  return (
    <PageLayout>
      <h1>{'Source list'}</h1>
      <DataTable header={'All sources'} rows={data} navTo={NAV_LINKS.SOURCE_DETAIL} />
    </PageLayout>
  );
};

export  default SourceListPage;
