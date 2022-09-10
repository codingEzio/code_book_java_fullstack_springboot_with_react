import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import Snackbar from '@mui/material/Snackbar';

import { SERVER_URL } from '../constants';
import AddCar from './AddCar';
import EditCar from './EditCar';

const Carlist = () => {
  const [cars, setCars] = useState([]);
  const [open, setOpen] = useState(false);

  const datagrid_columns = [
    { field: 'brand', headerName: 'Brand', width: 200 },
    { field: 'model', headerName: 'Model', width: 200 },
    { field: 'color', headerName: 'Color', width: 200 },
    { field: 'releasedAt', headerName: 'Released At', width: 200 },
    { field: 'price', headerName: 'Price', width: 200 },
    {
      field: '_links.self.href',
      headerName: '',
      sortable: false,
      filterable: false,
      renderCell: row => {
        <EditCar data={row} />;
      },
    },
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

  const addCar = car => {
    fetch(SERVER_URL + 'api/cars', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(car),
    })
      .then(response => {
        if (response.ok) {
          fetchCars();
        } else {
          alert('Something went wrong!');
        }
      })
      .catch(error => console.error(error));
  };

  const onDelClick = url => {
    if (window.confirm('Are you sure to delete this?')) {
      fetch(url, { method: 'DELETE' })
        .then(response => {
          if (response.ok) {
            fetchCars();
            setOpen(true);
          } else {
            alert('Something went wrong!');
          }
        })
        .catch(error => console.error(error));
    }
  };

  return (
    <React.Fragment>
      <AddCar addCar={addCar} />

      <div style={{ height: 500, width: '100%' }}>
        <DataGrid
          rows={cars}
          columns={datagrid_columns}
          getRowId={row => row._links.self.href}
          disableSelectionOnClick={true}
        />

        <Snackbar
          open={open}
          autoHideDuration={2000}
          onClose={() => setOpen(false)}
          message="Car deleted"
        />
      </div>
    </React.Fragment>
  );
};

export default Carlist;
