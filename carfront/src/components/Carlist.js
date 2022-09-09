import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';

import { SERVER_URL } from '../constants';

const Carlist = () => {
  const [cars, setCars] = useState([]);
  const datagrid_columns = [
    { field: 'brand', headerName: 'Brand', width: 200 },
    { field: 'model', headerName: 'Model', width: 200 },
    { field: 'color', headerName: 'Color', width: 200 },
    { field: 'releasedAt', headerName: 'Released At', width: 200 },
    { field: 'price', headerName: 'Price', width: 200 },
    {
      field: '_links.self.href',
      headerName: '⛔️',
      sortable: false,
      filterable: false,
      renderCell: row => {
        return <button onClick={() => onDelClick(row.id)}>Delete</button>;
      },
    },
  ];

  useEffect(() => {
    fetchCars();
  }, []);

  const fetchCars = () => {
    fetch(SERVER_URL + 'api/cars')
      .then(response => response.json())
      .then(data => setCars(data._embedded.cars))
      .then(error => console.error(error));
  };

  const onDelClick = url => {
    fetch(url, { method: 'DELETE' })
      .then(response => fetchCars())
      .catch(error => console.error(error));
  };

  return (
    <div style={{ height: 500, width: '100%' }}>
      <DataGrid
        rows={cars}
        columns={datagrid_columns}
        getRowId={row => row._links.self.href}
      />
    </div>
  );
};

export default Carlist;
