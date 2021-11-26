import React, { memo, useEffect, useState } from "react";
import { ComposableMap, Geographies, Geography } from "react-simple-maps";
import { scaleQuantile } from "d3-scale";
import api from "../../api";

const geoUrl =
  "https://gist.githubusercontent.com/ppKrauss/e6c12bf84e732ca4cbf0694808619cad/raw/9d35cbb66c04fcad6c2f9b0d4545c90ee526078d/br-states.json";

const style = {
  default: {
    outline: "none",
  },
  hover: {
    outline: "none",
  },
  pressed: {
    outline: "none",
  },
};

const MapChart = ({ setTooltipContent }) => {
  const [dados, setDados] = useState([]);
  const header = {
    Authorization: `${sessionStorage.getItem("tipo")} ${sessionStorage.getItem(
      "token"
    )}`,
  };
  useEffect(() => {
    function getDados() {
    const dates = ['2021-04-13', '2021-10-13'];
      api
        .get(
          "/metricas/acessos-por-uf",
          { headers: header ,
            params:{'dataInicio':dates[0], 'dataFinal': dates[1]}}
        )
        .then((e) => {
          setDados(e.data);
        })
        .catch((e) => {
          console.log(e.response);
        });
    }

    getDados();
  }, []);

  const colorScale = scaleQuantile()
    .domain(dados.map((value) => value.contagem))
    .range(["#FAFAFA", "#DEEDFA", "#656ebf", "#7E89ED", "#3E43A3"]);
  return (
    <>
      <ComposableMap
        data-tip=""
        projectionConfig={{
          center: [0, -12],
          scale: 700,
          parallels: [-2, -22],
          rotate: [54, 0],
        }}
      >
        <Geographies geography={geoUrl}>
          {({ geographies }) =>
            geographies.map((geo) => {
              const cur = dados.find((s) => s.uf === geo.id);
              return (
                <Geography
                  key={geo.rsmKey}
                  geography={geo}
                  onMouseEnter={() => {
                    const { nome } = geo.properties;
                    setTooltipContent(
                      `${nome} - ${geo.id} <br /> contagem de acessos = ${cur?.contagem || 0}`
                    );
                  }}
                  onMouseLeave={() => {
                    setTooltipContent("");
                  }}
                  stroke="#333"
                  style={style}
                  fill={cur ? colorScale(cur.contagem) : "#EEE"}
                />
              );
            })
          }
        </Geographies>
      </ComposableMap>
    </>
  );
};

export default memo(MapChart);
