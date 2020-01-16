import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Link from '@material-ui/core/Link';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import ReactDOM from 'react-dom';
import CreateUser from './CreateUser';
import SignIn from './SignIn';

import icon from '../../resources/icons/Tic-Tac-Toe-Game-256.png';

import {
  useLocation
} from "react-router-dom";
import ServiceClient from '../../Services/ServiceClient';
import LocalStorageService from '../../Services/LocalStorageService';
import Validate from '../../util/Validate';

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © Multiplayer Game'}
      {' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const useStyles = makeStyles(theme => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(1),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
}));

export default function SignUp() {
  const classes = useStyles();

  const [username, setUsername] = React.useState('');
  const [pass, setPass] = React.useState('');
  const [email, setEmail] = React.useState('');
  const [msg, setMsg] = React.useState('');

  //For validators
  const [usernameV, setUsernameV] = React.useState(false);
  const [passV, setPassV] = React.useState(false);
  const [emailV, setEmailV] = React.useState(false);


  function openLogin() {
    ReactDOM.render(<SignIn />, document.getElementById('root'));
  }

  function submit() {

    var userProblem = !Validate.validatePassword_User(username);
    setUsernameV(userProblem);

    var passProblem = !Validate.validatePassword_Pass(pass);
    setPassV(passProblem);

    var emailProblem = !Validate.validateEmail(email);
    setEmailV(emailProblem);

    if (!userProblem && !passProblem && !emailProblem) {
      const querystring = require('querystring');

      ServiceClient.getAxiosInstance(true)({
        method: 'post',
        url: '/addUser',
        headers: { 'content-type': 'application/x-www-form-urlencoded' },
        data: querystring.stringify({
          username: username,
          pass: pass,
          email: email
        })
      }).then(function (response) {
        console.log(response.status);

        if (response.status === 200) {
          ReactDOM.render(<SignIn />, document.getElementById('root'));
        }


      }).catch(function (error) {
        console.log(error);

        if (error.response.status === 500) {
          setMsg("Problem in saving the user");
        } else if (error.response.status === 409) {
          setMsg("This User or Email already exist and needs to be unique");
        }

        //console.log(error.response.status);

      })

    }
  }

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>

        <Avatar className={classes.avatar}>
          <img src={icon} />
        </Avatar>


        <Typography component="h1" variant="h5">
          Sign up
        </Typography>
        <Typography component="h4" variant="h10" color='error'>
          {msg}
        </Typography>
        <form /*action="http://localhost:8080/addUser"*/ className={classes.form} noValidate>
          <TextField
            error={usernameV}
            helperText={usernameV ? "The user name is not correct written. It must has 4 character as minimun and 10 as maximun." : ""}

            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="username"
            label="User Name"
            name="username"
            autoComplete="username"
            autoFocus
            onChange={(e) => setUsername(e.target.value)}
          />

          <TextField
            error={passV}
            helperText={passV ? 'The password must be composed at least by: one upper character, one lower character, one digit, one special character. It does not has blank space; besides, six characters as minimun and 10 as maximun.' : ""}

            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            onChange={(e) => setPass(e.target.value)}
          />

          <TextField
          error={emailV}
          helperText={emailV ? "The email is not correct written" : ""}

            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="email"
            label="Email"
            id="email"
            autoComplete="current-password"
            onChange={(e) => setEmail(e.target.value)}
          />

          <Button
            type="Button"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={submit}

          >
            Sign Up
          </Button>
          <Grid container justify="flex-end">
            <Grid item>
              <Link href="#" variant="body2" onClick={openLogin}>
                Already have an account? Sign in
              </Link>
            </Grid>
          </Grid>
        </form>
      </div>
      <Box mt={8}>
        <Copyright />
      </Box>
    </Container>
  );
}