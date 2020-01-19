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
import SignIn from './SignIn';
import icon from '../../resources/icons/Tic-Tac-Toe-Game-256.png';
import {
    useLocation
} from "react-router-dom";

import UpdatePassword from './UpdatePassword';
import ServiceClient from '../../Services/ServiceClient';
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

export default function ForgotPassword() {
    const classes = useStyles();

    const [msg, setMsg] = React.useState('');
    const [email, setEmail] = React.useState('');


    const [emailV, setEmailV] = React.useState(false);

    function openLogin() {
        ReactDOM.render(<SignIn />, document.getElementById('root'));
    }

    function recoveryPassword() {

        var emailProblem = !Validate.validateEmail(email);
        setEmailV(emailProblem);


        if (!emailProblem) {
            const querystring = require('querystring');

            ServiceClient.getAxiosInstance(true)({
                method: 'post',
                url: '/recoveryPass',
                headers: { 'content-type': 'application/x-www-form-urlencoded' },
                data: querystring.stringify({
                    email: email,
                })

            }).then(function (response) {
                console.log(response.data);

                if (response.status === 200) {
                    ReactDOM.render(<UpdatePassword />, document.getElementById('root'));
                }


            }).catch(function (error) {
                console.log(error.status + ' - ' + error.statusText);
                if (error.response.status === 404) {
                    setMsg("The email don't exist or is incorrect");
                } else if (error.response.status === 500) {
                    setMsg("Exist some problem with the network");
                }

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
                    Forgot Password
        </Typography>
                <Typography component="h4" variant="h10" color='error'>
                    {msg}
                </Typography>
                <form /*action="http://localhost:8080/recoveryPass"*/ className={classes.form} noValidate>

                    <TextField
                    error={emailV}
                    helperText={emailV ? "The email is not correct written" : ""}
                    
                        variant="outlined"
                        required
                        fullWidth
                        id="email"
                        label="Email Address"
                        name="email"
                        autoComplete="email"
                        onChange={(e) => setEmail(e.target.value)}
                        inputProps={{ maxLength: 40 }}
                    />

                    <Button
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        onClick={recoveryPassword}
                    >
                        Send New Password
          </Button>

                    <Grid container justify="flex-end">
                        <Grid item>
                            <Link href="#" variant="body2" onClick={openLogin}>
                                Do you remember the credentials? Sign in
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