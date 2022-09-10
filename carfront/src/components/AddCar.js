import React, { useState } from 'react';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';

import Button from '@mui/material/Button';

import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';

const AddCar = props => {
  const [open, setOpen] = useState(false);
  const [car, setCar] = useState({
    brand: '',
    model: '',
    color: '',
    releasedAt: '',
    fuel: '',
    price: '',
  });

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = event => {
    setCar({
      ...car,
      [event.target.name]: event.target.value,
    });
  };

  const handleSave = () => {
    props.addCar(car);

    handleClose();
  };

  return (
    <div>
      <Button variant="contained" onClick={handleClickOpen}>
        New Car
      </Button>

      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>New car</DialogTitle>

        <DialogContent>
          <Stack spacing={2} mt={1}>
            <TextField
              autoFocus
              variant="standard"
              label="Brand"
              name="brand"
              value={car.brand}
              onChange={handleChange}
            />
            <TextField
              variant="standard"
              label="Model"
              name="model"
              value={car.model}
              onChange={handleChange}
            />
            <TextField
              variant="standard"
              label="Color"
              name="color"
              value={car.color}
              onChange={handleChange}
            />
            <TextField
              variant="standard"
              label="Released At"
              name="releasedAt"
              value={car.releasedAt}
              onChange={handleChange}
            />
            <TextField
              variant="standard"
              label="Price"
              name="price"
              value={car.price}
              onChange={handleChange}
            />
          </Stack>
        </DialogContent>

        <DialogActions>
          <Button variant="contained" onClick={handleClose}>
            Cancel
          </Button>
          <Button variant="contained" onClick={handleSave}>
            Save
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default AddCar;
