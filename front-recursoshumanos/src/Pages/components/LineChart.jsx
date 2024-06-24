import { max } from 'lodash';
import React, { useState } from 'react';
import ReactApexChart from 'react-apexcharts';


const LineChart = ({title, variable, xaxis, yaxis, yaxisMax}) => {
  const [options, setOptions] = useState({
    chart: {
      height: 200,
      type: 'line',
      zoom: {
        enabled: false
      }
    },
    dataLabels: {
      enabled: false
    },
    stroke: {
      curve: 'straight'
    },
    title: {
      text: title,
      align: 'center'
    },
    grid: {
      row: {
        colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
        opacity: 0.5
      },
    },
    xaxis: {
      categories: xaxis,
    },
    yaxis: {
      max: yaxisMax
    }
  });

  const [series, setSeries] = useState([{
    name: variable,
    data: yaxis,
  }]);

  return (
    <div>
      <div id="chart">
        <ReactApexChart options={options} series={series} type="line" />
      </div>
      <div id="html-dist"></div>
    </div>
  );
};

export default LineChart;