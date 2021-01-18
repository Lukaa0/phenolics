import React, { useEffect, useState } from "react";

import PageLayout from "../components/PageLayout";
import DataTable from "../components/DataTable";
import List from "../components/List";

import { NAV_LINKS } from "../constants/navLinks";
import { getData } from "../services/apiServices";
import useQuery from "../utils/useQuery";
import Grid from "@material-ui/core/Grid";
import Box from "@material-ui/core/Box";

const MoleculeDetail = () => {
  const initData = { type: "", values: [] };
  const initDetailData = { msfragments: [], lambdas: [] };

  const [data, setData] = useState({ name: "" });
  const [parentData, setParentData] = useState(initData);
  const [detailData, setDetailData] = useState(initDetailData);
  const queryId = useQuery();

  useEffect(() => {
    (async function fetchData() {
      const data = await getData(`/molecules/${queryId}`);
      if (data) {
        setData(data);
        data.parent ? setParentData(data.parent) : setParentData(initData);
        data.details
          ? setDetailData({
              msfragments: mapMsfragmentsData(data.details.msfragments),
              lambdas: mapLambdasData(data.details.lambdas),
            })
          : setDetailData(initDetailData);
      }
    })();
  }, [queryId]);

  const mapMsfragmentsData = (data) => {
    return data.map((d) => ({
      id: d.id,
      value: `Value: ${d.value}, Percentage: ${d.percentage}`,
    }));
  };

  const mapLambdasData = (data) => {
    return data.map((d) => ({
      id: d.id,
      value: `WaveLength: ${d.waveLength}, Shoulder: ${
        d.percentage ? "YES" : "NO"
      }`,
    }));
  };

  return (
    <div>
      <PageLayout>
        <h1>{data.name}</h1>
        <Grid
          container
          direction="row"
          justify="flex-start"
          alignItems="center"
          spacing={4}
          style={{paddingLeft: '3rem'}}
        >
          <Grid item>
            <h3>{"Msfragments:"}</h3>
            <List items={detailData.msfragments} />
          </Grid>
          <Grid item>
            <h3>{"Lambdas:"}</h3>
            <List items={detailData.lambdas} />
          </Grid>
        </Grid>
        <Grid item>
          <h2>{"Parents:"}</h2>
          <DataTable
            header={"Phenolics"}
            rows={parentData.values}
            navTo={NAV_LINKS.PHENOLIC_DETAIL}
          />
        </Grid>
      </PageLayout>
    </div>
  );
};

export default MoleculeDetail;
