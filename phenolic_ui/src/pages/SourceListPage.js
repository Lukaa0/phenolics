import React from 'react';

import DataTable from '../components/DataTable';
import PageLayout from '../components/PageLayout';

import { NAV_LINKS } from '../constants/navLinks';

const SourceListPage = () => {
  const rows = [
    { id: 1, name: 'Source 1' },
    { id: 2, name: 'Source 2' },
    { id: 3, name: 'Source 3' },
    { id: 4, name: 'Source 4' },
    { id: 5, name: 'Source 5' },
  ];
  
  return (
    <PageLayout>
      <h1>{'Source list'}</h1>
      <DataTable header={'All sources'} rows={rows} navTo={NAV_LINKS.SOURCE_DETAIL} />
    </PageLayout>
  );
};

export  default SourceListPage;
