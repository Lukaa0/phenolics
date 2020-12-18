import React, { useEffect } from 'react';
import { useLocation } from "react-router-dom";

import DataTable from '../components/DataTable';
import PageLayout from '../components/PageLayout';

import { NAV_LINKS } from '../constants/navLinks';

const useQuery = () => {
  const params = new URLSearchParams(useLocation().search);
  
  // TODO After integration with API we should store id and send request to API and from response get name
  return params.get("name");
};

const SourceDetailPage = () => {
  const queryId = useQuery();
  useEffect(() => {
    console.log(queryId);
  });
  
  const rows = [
    { id: 1, name: 'Phenolic 1' },
    { id: 2, name: 'Phenolic 2' },
    { id: 3, name: 'Phenolic 3' },
    { id: 4, name: 'Phenolic 4' },
    { id: 5, name: 'Phenolic 5' },
  ];
  
  return (
    <PageLayout>
      <h1>{queryId}</h1>
      <DataTable header={'Phenolics'} title={queryId} rows={rows} navTo={NAV_LINKS.PHENOLIC_DETAIL} />
    </PageLayout>
  );
};

export default SourceDetailPage;
