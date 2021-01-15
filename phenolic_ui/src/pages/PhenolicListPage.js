import React, { useState, useEffect } from "react";

import DataTable from "../components/DataTable";
import PageLayout from "../components/PageLayout";

import { NAV_LINKS } from "../constants/navLinks";
import { getData } from "../services/apiServices";

const PhenolicListPage = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    (async function fetchData() {
      const data = await getData("/phenolics");
      if (data) {
        setData(data);
      }
    })();
  }, []);

  return (
    <PageLayout>
      <h1>{"Phenolic list"}</h1>
      <DataTable
        header={"All phenolics"}
        rows={data}
        navTo={NAV_LINKS.PHENOLIC_DETAIL}
      />
    </PageLayout>
  );
};

export default PhenolicListPage;
