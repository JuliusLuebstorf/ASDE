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
import SignUp from './SignUp';
import ForgotPassword from './ForgotPassword';
import icon from '../../resources/icons/Tic-Tac-Toe-Game-256.png';

import {
    useLocation
  } from "react-router-dom";
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

export default function SignIn() {
    const classes = useStyles();

    const [username, setUsername] = React.useState(true);
    const [pass, setPass] = React.useState('');

    var query = null;
    try {
    const location = useLocation();
    query = location!=null? new URLSearchParams(location.search):null; 
    
    } catch (error) {
        
    }
    const msg = (query!=null && query.get('msg') === 'login_incorrect') ?"User or Password incorrect":"";
    
    //console.log(query.get('msg'))  {this.props.location.search}

    function updateUsername(e){

        setUsername(e.target.value);
    }

    function updatePass(e){
        setPass(e.target.value);

    }

    function submit(){
        ServiceClient.post("/perform_login", {
            params: { username: username, password: pass}
          }).then((res) => {
      
            alert(res.data);
            //console.log(res.data);
      
            // this.props.updateUsers();
          })

       
    }
  
    function openCreateUser() {
        ReactDOM.render(<SignUp />, document.getElementById('root'));
    }

    function openForgotPassword() {
        ReactDOM.render(<ForgotPassword />, document.getElementById('root'));
    }

    function login(e) {
        alert(e);
    }
    
    

    return (
        


        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                    <img src={icon} />
                </Avatar>
                <Typography component="h1" variant="h5">
                 Sign in
        </Typography>
        <Typography component="h4" variant="h10" color='error'>
                {msg}
        </Typography>
               <form action="http://localhost:8080/perform_login" noValidate autoComplete="off" method = "POST" /*onSubmit={submit}*/>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id="username"
                        label="User Name"
                        name="username"
                        autoComplete="username"
                        autoFocus
                        onChange={updateUsername}
                    />

                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        onChange={updatePass}
                    />

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        
                    >
                        Sign In
          </Button>
                    <Grid container>
                        <Grid item xs>
                            <Link href="#" variant="body2" onClick={openForgotPassword}>
                                Forgot password?
              </Link>
                        </Grid>
                        <Grid item>
                            <Link href="#" variant="body2" onClick={openCreateUser}>
                                {"Don't have an account? Sign Up"}
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