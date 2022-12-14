import React, { useState, useEffect } from 'react';
import {
  DataGrid,
  GridToolbarContainer,
  GridToolbarExport,
  gridClasses,
} from '@mui/x-data-grid';
import Snackbar from '@mui/material/Snackbar';

import Stack from '@mui/material/Stack';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';

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
      field: '_links.car.href',
      headerName: 'Edit',
      sortable: false,
      filterable: false,
      disableExport: true,
      renderCell: row => {
        return <EditCar data={row} updateCar={updateCar} />;
      },
    },
    {
      field: '_links.self.href',
      headerName: '',
      sortable: false,
      filterable: false,
      disableExport: true,
      renderCell: row => {
        return (
          <IconButton onClick={() => onDelClick(row.id)}>
            <DeleteIcon color="error" />
          </IconButton>
        );
      },
    },
  ];

  useEffect(() => {
    fetchCars();
  }, []);

  const fetchCars = () => {
    const jwtToken = sessionStorage.getItem('jwt');

    fetch(SERVER_URL + 'api/cars', {
      headers: { Authorization: jwtToken },
    })
      .then(response => response.json())
      .then(data => setCars(data._embedded.cars))
      .then(error => console.error(error));
  };

  const addCar = car => {
    const jwtToken = sessionStorage.getItem('jwt');

    fetch(SERVER_URL + 'api/cars', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json', Authorization: jwtToken },
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

  const updateCar = (car, link) => {
    const jwtToken = sessionStorage.getItem('jwt');

    fetch(link, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json', Authorization: jwtToken },
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
      const jwtToken = sessionStorage.getItem('jwt');

      fetch(url, { method: 'DELETE', headers: { Authorization: jwtToken } })
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

  const CustomToolbar = () => {
    return (
      <GridToolbarContainer className="{gridClasses.toolbarContainer}">
        <GridToolbarExport />
      </GridToolbarContainer>
    );
  };

  return (
    <React.Fragment>
      <Stack mt={2} mb={2}>
        <AddCar addCar={addCar} />
      </Stack>

      <div style={{ height: 500, width: '100%' }}>
        <DataGrid
          rows={cars}
          columns={datagrid_columns}
          getRowId={row => row._links.self.href}
          disableSelectionOnClick={true}
          components={{ Toolbar: CustomToolbar }}
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
