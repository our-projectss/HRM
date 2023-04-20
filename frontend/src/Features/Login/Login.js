import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { Avatar, Stack, Button, Checkbox, FormControlLabel, Grid, Link, Paper, TextField, Typography } from '@mui/material';
import React from 'react';

export default function Login() {
  const containerStyle = { display: "flex", margin: "auto auto", height: "100vh" };
  const paperStyle = { padding: '40px 60px', width: 280, margin: "auto auto" };
  const avatarStyle = { backgroundColor: '#1bbd7e' };
  const btnstyle = { margin: '8px 0' };

  return (
    <Grid style={containerStyle}>
      <Paper elevation={10} style={paperStyle}>
        <Stack spacing={2}>
          <Grid align='center'>
            <Avatar align='center' style={avatarStyle}><LockOutlinedIcon /></Avatar>
            <Typography mt={2} variant='h5'>Sign In</Typography>
          </Grid>
          <TextField label='Username' placeholder='Enter username' fullWidth required />
          <TextField label='Password' placeholder='Enter password' type='password' fullWidth required />
          <FormControlLabel
            control={
              <Checkbox
                name="checkedB"
                color="primary"
              />
            }
            label="Remember me"
          />
          <Button type='submit' color='primary' variant="contained" style={btnstyle} fullWidth>Sign in</Button>
          <div>
            <Typography >
              <Link href="#" >
                Forgot password ?
              </Link>
            </Typography>
            <Typography > Do you have an account ?
              <Link href="#" >
                Sign Up
              </Link>
            </Typography>
          </div>
        </Stack>
      </Paper>
    </Grid>
  )
}
