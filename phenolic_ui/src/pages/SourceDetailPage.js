import React, { useEffect, useState } from "react";

import DataTable from "../components/DataTable";
import PageLayout from "../components/PageLayout";

import { NAV_LINKS } from "../constants/navLinks";
import { getData } from "../services/apiServices";
import useQuery from "../utils/useQuery";

const SourceDetailPage = () => {
  const initData = { type: "", values: [] };

  const [data, setData] = useState({ name: "" });
  const [childrenData, setChildrenData] = useState(initData);
  const queryId = useQuery();

  useEffect(() => {
    (async function fetchData() {
      const data = await getData(`/sources/${queryId}`);
      if (data) {
        setData(data);
        data.children
          ? setChildrenData(data.children)
          : setChildrenData(initData);
      }
    })();
  }, [queryId]);

  return (
    <PageLayout>
      <h1>{data.name}</h1>
      <DataTable
        header={"Phenolics"}
        rows={childrenData.values}
        navTo={NAV_LINKS.PHENOLIC_DETAIL}
      />
    </PageLayout>
  );
};

export default SourceDetailPage;
