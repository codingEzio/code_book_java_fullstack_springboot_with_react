import React, { useState } from 'react';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import Snackbar from '@mui/material/Snackbar';

import { SERVER_URL } from '../constants';
import Carlist from './Carlist';

const Login = () => {
  const [user, setUser] = useState({
    username: '',
    password: '',
  });
  const [isAuthenticated, setAuthentication] = useState(false);
  const [open, setOpen] = useState(false);

  const handleChange = event => {
    setUser({
      ...user,
      [event.target.name]: event.target.value,
    });
  };

  const login = () => {
    fetch(SERVER_URL + 'login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user),
    })
      .then(response => {
        // If we are successfully authenticated, the backend server would
        // return the generated JWT token in the form of a HTTP header
        const jwtToken = response.headers.get('Authorization');

        // If we got the token, add it to the session storage
        if (jwtToken !== null) {
          sessionStorage.setItem('jwt', jwtToken);

          setAuthentication(true);
        } else {
          setOpen(true);
        }
      })
      .catch(error => console.error(error));
  };

  if (isAuthenticated) {
    return <Carlist />;
  } else {
    return (
      <div>
        <Stack spacing={2} alignItems="center" mt={2}>
          <TextField name="username" label="Username" onChange={handleChange} />
          <TextField
            type="password"
            name="password"
            label="Password"
            onChange={handleChange}
          />
          <Button variant="outlined" color="primary" onClick={login}>
            Login
          </Button>
        </Stack>

        <Snackbar
          open={open}
          autoHideDuration={3000}
          onClose={() => setOpen(false)}
          message="Login failed: Check your username and password again!"
        />
      </div>
    );
  }
};

export default Login;
