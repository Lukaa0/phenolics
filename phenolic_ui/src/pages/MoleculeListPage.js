import React from 'react';

import DataTable from '../components/DataTable';
import PageLayout from '../components/PageLayout';
import { NAV_LINKS } from '../constants/navLinks';

const MoleculeListPage = () => {
  const rows = [
    { id: 1, name: 'Molecule 1' },
    { id: 2, name: 'Molecule 2' },
    { id: 3, name: 'Molecule 3' },
    { id: 4, name: 'Molecule 4' },
    { id: 5, name: 'Molecule 5' },
  ];
  
  return (
    <PageLayout>
      <h1>{'Molecule list'}</h1>
      <DataTable header={'All molecules'} rows={rows} navTo={NAV_LINKS.MOLECULE_DETAIL} />
    </PageLayout>
  );
};

export  default MoleculeListPage;
