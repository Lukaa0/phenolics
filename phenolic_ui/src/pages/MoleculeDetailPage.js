import React, { useEffect, useState } from "react";

import PageLayout from "../components/PageLayout";
import DataTable from "../components/DataTable";

import { NAV_LINKS } from "../constants/navLinks";
import { getData } from "../services/apiServices";
import useQuery from "../utils/useQuery";

const MoleculeDetail = () => {
  const initData = { type: "", values: [] };

  const [data, setData] = useState({ name: "" });
  const [parentData, setParentData] = useState(initData);
  const [childrenData, setChildrenData] = useState(initData);
  const queryId = useQuery();

  useEffect(() => {
    (async function fetchData() {
      const data = await getData(`/molecules/${queryId}`);
      if (data) {
        setData(data);
        data.parent ? setParentData(data.parent) : setParentData(initData);
        data.children
          ? setChildrenData(data.children)
          : setChildrenData(initData);
      }
    })();
  }, [queryId]);

  return (
    <div>
      <PageLayout>
        <h1>{data.name}</h1>
        <DataTable
          header={"Phenolics"}
          rows={parentData.values}
          navTo={NAV_LINKS.PHENOLIC_DETAIL}
        />
      </PageLayout>
    </div>
  );
};

export default MoleculeDetail;
