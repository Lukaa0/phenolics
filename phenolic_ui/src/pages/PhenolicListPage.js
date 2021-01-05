import React  from 'react';

import DataTable from '../components/DataTable';
import PageLayout from '../components/PageLayout';

import { NAV_LINKS } from '../constants/navLinks';

const PhenolicListPage = () => {
  const rows = [
    { id: 1, name: 'Phenolic 1' },
    { id: 2, name: 'Phenolic 2' },
    { id: 3, name: 'Phenolic 3' },
    { id: 4, name: 'Phenolic 4' },
    { id: 5, name: 'Phenolic 5' },
  ];
  
  return (
    <PageLayout>
      <h1>{'Phenolic list'}</h1>
      <DataTable header={'All phenolics'}  rows={rows} navTo={NAV_LINKS.PHENOLIC_DETAIL} />
    </PageLayout>
  );
};

export  default PhenolicListPage;
