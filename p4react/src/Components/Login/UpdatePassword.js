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
import LocalStorageService from '../../Services/LocalStorageService';
import Validate from '../../util/Validate';
import ServiceClient from '../../Services/ServiceClient';

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

export default function UpdatePassword() {
  const classes = useStyles();

  const [username, setUsername] = React.useState('');
  const [passTemp, setPassTemp] = React.useState('');
  const [passNew, setPassNew] = React.useState('');

  const [usernameV, setUsernameV] = React.useState(false);
  const [passV, setPassV] = React.useState(false);
  const [passTempV, setPassTempV] = React.useState(false);

  const [msg, setMsg] = React.useState('');


  function openLogin() {
    ReactDOM.render(<SignIn />, document.getElementById('root'));
  }


  function submit() {

    setMsg("");

    var userT = !Validate.validateUser(username);
    setUsernameV(userT);

    var passT = !Validate.validatePassword(passNew);
    setPassV(passT);
    
    var passTempT = !Validate.validatePasswordTemp(passTemp);
    setPassTempV(passTempT);

    if (!userT && !passT && !passTempT) {
      const localStorageService = LocalStorageService.getService();
      const querystring = require('querystring');

      ServiceClient.getAxiosInstance(true)({
        method: 'post',
        url: '/updatePass',
        headers: { 'content-type': 'application/x-www-form-urlencoded' },
        data: querystring.stringify({
          username: username,
          oldPass: passTemp,
          newPass: passNew
        })
      }).then(function (response) {
        console.log(response.data);
        console.log(response.status);

        localStorageService.setToken(response.data);

        if (response.status === 200) {
          //setPassTempV(false);
          ReactDOM.render(<SignIn />, document.getElementById('root'));
        }

      }).catch(function (error) {
        //console.log(error);
        //console.log(error.response.status);
        if (error.response.status === 401) {
          setMsg("The old password is not correct.");
          //alert("The old password is not correct.");
          setPassTempV(true);
        }
        else if (error.response.status === 500){           
            setMsg("The process could not complete. Please, try again.");          
        }
        else if (error.response.status === 406){
          setMsg("The user does not exist.");
          setUsernameV(true);
        }
        localStorageService.setToken("");
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
          Update Password
        </Typography>
        <Typography component="h4" variant="h10" color='error'>
          {msg}
        </Typography>
        <form /*action="http://localhost:8080/updatePass"*/ className={classes.form} noValidate>
          <TextField

            error={usernameV}
            helperText={usernameV ? "The user name is not correct written. It must has 4 character as minimun and 10 as maximun. No special characters, just letters and numbers." : ""}

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
            inputProps={{ maxLength: 10 }}
          />

          <TextField

            error={passTempV}
            helperText={passTempV ? "The temporal password is not correct written." : ""}

            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="oldPass"
            label="Old Password"
            type="password"
            id="oldpassword"
            autoComplete="current-password"
            onChange={(e) => setPassTemp(e.target.value)}
            inputProps={{ maxLength: 5 }}
          />

          <TextField

            error={passV}
            helperText={passV ? 'The password must be composed at least by: one upper character, one lower character, one digit, one special character. It does not has blank space; besides, six characters as minimun and 10 as maximun.' : ""}

            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="newPass"
            label="New Password"
            type="password"
            id="newpassword"
            autoComplete="current-password"
            onChange={(e) => setPassNew(e.target.value)}
            inputProps={{ maxLength: 10 }}
          />

          <Button
            type="Button"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={submit}
          >
            Update Password
          </Button>
          <Grid container justify="flex-end">
            <Grid item>
              <Link href="#" variant="body2" onClick={openLogin}>
                Sign in
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