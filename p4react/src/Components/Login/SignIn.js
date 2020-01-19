import Avatar from '@material-ui/core/Avatar';
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import Container from '@material-ui/core/Container';
import CssBaseline from '@material-ui/core/CssBaseline';
import Grid from '@material-ui/core/Grid';
import Link from '@material-ui/core/Link';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Typography from '@material-ui/core/Typography';
import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import { useLocation } from "react-router-dom";
import icon from '../../resources/icons/Tic-Tac-Toe-Game-256.png';
import LocalStorageService from '../../Services/LocalStorageService';
import SignUp from './SignUp';
import ServiceClient from '../../Services/ServiceClient';
import ForgotPassword from './ForgotPassword';
import Lobby from '../Lobby/Lobby';
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

export default function SignIn() {
    const classes = useStyles();

    const [username, setUsername] = React.useState("");
    const [pass, setPass] = React.useState('');
    const [msg, setMsg] = React.useState('');

    const [usernameV, setUsernameV] = React.useState(false);
    const [passV, setPassV] = React.useState(false);

    /*var query = null;
    try {
        const location = useLocation();
        query = location != null ? new URLSearchParams(location.search) : null;

    } catch (error) {

    }
    const msg = (query != null && query.get('msg') === 'login_incorrect') ? "User or Password incorrect" : "";
*/
    //console.log(query.get('msg'))  {this.props.location.search}

    /*function updateUsername(e) {

        setUsername(e.target.value);
    }

    function updatePass(e) {
        setPass(e.target.value);

    }*/

    function submit() {


        var userT = !Validate.validateUser(username);
        setUsernameV(userT);

        var passT = !Validate.validatePassword(pass);
        setPassV(passT);

        
        if (!userT && !passT) {
            const localStorageService = LocalStorageService.getService();
            const querystring = require('querystring');

            ServiceClient.getAxiosInstance(true)({
                method: 'post',
                url: '/perform_login',
                headers: { 'content-type': 'application/x-www-form-urlencoded' },
                data: querystring.stringify({
                    username: username,
                    password: pass
                })
            }).then(function (response) {
                console.log(response.data);
                console.log(response.status);

                localStorageService.setToken(response.data);

                if (response.status === 200) {
                    ReactDOM.render(<Lobby />, document.getElementById('root'));
                }

            }).catch(function (error) {
                //console.log(error);
                //console.log(error.response.status);
                if (error.response.status === 401) {
                    setMsg("User or Password incorrect");

                    localStorageService.setToken("");
                }
            })
        }

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
                <form /*action="http://localhost:8080/perform_login"*/ /*noValidate autoComplete="off" method="get" onSubmit={submit}*/>
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