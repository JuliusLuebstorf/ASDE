import AppBar from '@material-ui/core/AppBar';
import Avatar from '@material-ui/core/Avatar';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import CssBaseline from '@material-ui/core/CssBaseline';
import Divider from '@material-ui/core/Divider';
import Drawer from '@material-ui/core/Drawer';
import Fab from '@material-ui/core/Fab';
import Grid from '@material-ui/core/Grid';
import IconButton from '@material-ui/core/IconButton';
import Link from '@material-ui/core/Link';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemText from '@material-ui/core/ListItemText';
import Paper from '@material-ui/core/Paper';
import { makeStyles } from '@material-ui/core/styles';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import AppsIcon from '@material-ui/icons/Apps';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import HomeIcon from '@material-ui/icons/Home';
import MenuIcon from '@material-ui/icons/Menu';
import clsx from 'clsx';
import React, { useEffect } from 'react';
import { useLocation } from "react-router-dom";
import TTTGameSelection from '../TicTacToe/TTTGameSelection';
import HomeScreen from './HomeScreen';
import LocalStorageService from '../../Services/LocalStorageService';
import ReactDOM from 'react-dom';
import SignIn from '../Login/SignIn';
import ServiceClient from '../../Services/ServiceClient';
import { Redirect } from 'react-router-dom'

function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {'Copyright © '}
        <Link color="inherit" href="http://eracle.mat.unical.it/projects/P4/issues/P4-8?filter=allopenissues">
          Project 4
        </Link>{' '}
        {new Date().getFullYear()}
        {'.'}
      </Typography>
    );
  }
   
  const drawerWidth = 240;
  
  const useStyles = makeStyles(theme => ({
    root: {
      display: 'flex',
    },
    toolbar: {
      paddingRight: 24, // keep right padding when drawer closed
    },
    toolbarIcon: {
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'flex-end',
      padding: '0 8px',
      ...theme.mixins.toolbar,
    },
    appBar: {
      zIndex: theme.zIndex.drawer + 1,
      transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
      }),
    },
    appBarShift: {
      marginLeft: drawerWidth,
      width: `calc(100% - ${drawerWidth}px)`,
      transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
    },
    menuButton: {
      marginRight: 36,
    },
    menuButtonHidden: {
      display: 'none',
    },
    title: {
      flexGrow: 1,
    },
    drawerPaper: {
      position: 'relative',
      whiteSpace: 'nowrap',
      width: drawerWidth,
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
    },
    drawerPaperClose: {
      overflowX: 'hidden',
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
      }),
      width: theme.spacing(7),
      [theme.breakpoints.up('sm')]: {
        width: theme.spacing(9),
      },
    },
    appBarSpacer: theme.mixins.toolbar,
    content: {
      flexGrow: 1,
      height: '100vh',
      overflow: 'auto',
    },
    container: {
      paddingTop: theme.spacing(4),
      paddingBottom: theme.spacing(4),
    },
    paper: {
      padding: theme.spacing(2),
      display: 'flex',
      overflow: 'auto',
      flexDirection: 'column',
    },
    fixedHeight: {
      height: 240,
    },
    gameList: {
      width: '100%',
      maxWidth: 360,
      backgroundColor: theme.palette.background.paper,
    },
  }));

 

export default function Lobby() {
    const classes = useStyles();
    const [open, setOpen] = React.useState(true);
    const [component, setComponent] = React.useState('lobby');
    const [currentUser, setCurrentUser] = React.useState('currentUser');

    const [redirect, setRedirect] = React.useState(false);

    const handleDrawerOpen = () => {
      setOpen(true);

    };
    const handleDrawerClose = () => {
      setOpen(false);
    };

    function renderRedirect() {
      if (redirect) {
        window.location.assign('http://localhost:3000/login');
        //return <Redirect to='/login' />
      }
    }

    function logout() {
      const localStorageService = LocalStorageService.getService();
      localStorageService.setToken("");
  
      setRedirect(true);
      //ReactDOM.render(<SignIn />, document.getElementById('root'));
  
      /*ServiceClient.getAxiosInstance(true).get("/logout")
        .then((res) => {
          console.log(res.data);
  
          alert("logout");
          localStorageService.setToken("");
  
          // this.props.updateUsers();
        })*/
  
      //href = "http://localhost:8080/logout"
  
    }

    

    useEffect(() => {

      //setCurrentUser((query != null) ? query.get('user') : "");
  
      ServiceClient.getAxiosInstance().get("/currentUserName")
      .then(function (response) {
        console.log(response.data);
        console.log(response.status);
  
        
        if (response.status === 200) {
          setCurrentUser(response.data);
        }
  
    }).catch(function (error) {
        console.log(error);
        //console.log(error.response.status);
        if (error.response.status === 401 || error.response.status === 404) {
          ReactDOM.render(<SignIn />, document.getElementById('root'));
        }
    })
  
    }, []);
    

    return (
      <div className={classes.root}>
        <CssBaseline />
        <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
          <Toolbar className={classes.toolbar}>
          {renderRedirect()}
            {<IconButton
              edge="start"
              color="inherit"
              aria-label="open drawer"
              onClick={handleDrawerOpen}
              className={clsx(classes.menuButton, open && classes.menuButtonHidden)}
            >
              <MenuIcon />
            </IconButton>}
            <Typography component="h1" variant="h6" color="inherit" noWrap className={classes.title}>
            {currentUser} Welcome to Game Lobby
            </Typography>
            <Fab variant="extended" color="primary"  /*href="http://localhost:8080/logout"*/ onClick={logout}>
              <ExitToAppIcon />
                Logout
            </Fab>
          </Toolbar>
        </AppBar>
        <Drawer
          variant="permanent"
          classes={{
            paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
          }}
          open={open}
        >
         <div className={classes.toolbarIcon}>
            <IconButton onClick={handleDrawerClose}>
              <ChevronLeftIcon />
            </IconButton>
          </div>
          <Divider />
          <List className={classes.gameList}>
                <div onClick={() => setComponent('')}> {/* Give the component a name and call it in the if / else below */}
                    <ListItem button>
                      <ListItemAvatar>
                        <Avatar>
                          <HomeIcon />
                        </Avatar>
                      </ListItemAvatar>
                      <ListItemText primary="Home Screen"  />
                      </ListItem>
                  </div>
                <div onClick={() => setComponent('ttt')}>
                <ListItem button color="primary">
                  <ListItemAvatar>
                    <Avatar>
                      <AppsIcon />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText primary="Tic-Tac-Toe" />
                  </ListItem>
                  </div>
                  {/* More games can be added here*/}
                  
                </List>
        </Drawer>
        }
        <main className={classes.content}>
          <div className={classes.appBarSpacer} />
          <Container maxWidth="lg" className={classes.container}>
            
            <Grid container spacing={3}>
              <Grid item xs={12} md={4} lg={3}>
                
              </Grid>
              {/* GameDisplay */}
              <Grid item xs={12}>
                <Paper className={classes.paper}>
                  {/* Here the if / else have to be called to change the games */}
                {component === 'ttt' ? <TTTGameSelection user={currentUser}/> : 
                component === 'changeThisForFutureGame' ? "futureGame" : 
                <HomeScreen/>
                }
                </Paper>
              </Grid>
            </Grid>
            <Box pt={4}>
              <Copyright />
            </Box>
          </Container>
        </main>
      </div>
    );
      
  }
