import React, { useState } from 'react';

import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';

import { SERVER_URL } from '../constants';
import Carlist from './Carlist';

const Login = () => {
  const [user, setUser] = useState({
    username: '',
    password: '',
  });
  const [isAuthenticated, setAuthentication] = useState(false);

  const handleChange = event => {
    setUser({
      ...user,
      [event.target.name]: event.target.value,
    });
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
          <Button variant="outlined" color="primary" onClick={}>
            Login
          </Button>
        </Stack>
      </div>
    );
  }
};

export default Login;
