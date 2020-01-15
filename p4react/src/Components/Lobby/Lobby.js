import React from 'react';
import clsx from 'clsx';
import { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import Box from '@material-ui/core/Box';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import Badge from '@material-ui/core/Badge';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Link from '@material-ui/core/Link';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import Fab from '@material-ui/core/Fab';
import AppsIcon from '@material-ui/icons/Apps';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import GameBoardSP from '../TicTacToe/GameBoardSP';
import PanToolIcon from '@material-ui/icons/PanTool';
import ListSubheader from '@material-ui/core/ListSubheader';
import LooksOneIcon from '@material-ui/icons/LooksOne';
import LooksTwoIcon from '@material-ui/icons/LooksTwo';
import Looks3Icon from '@material-ui/icons/Looks3';
import HomeIcon from '@material-ui/icons/Home';
import HomeScreen from './HomeScreen';
import GameFields from '../TicTacToe/GameFields';
import TTTGameSelection from '../TicTacToe/TTTGameSelection';
import ServiceClient from '../../Services/ServiceClient';

import {
  useLocation
} from "react-router-dom";


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

    

    const handleDrawerOpen = () => {
      setOpen(true);

    };
    const handleDrawerClose = () => {
      setOpen(false);
    };

    function logout(){
      //onClick={logout}
      /*ServiceClient.get("/logout").then((res) => {
      })*/

      

    }

    var query = null;
      try {
      const location = useLocation();
      query = location!=null? new URLSearchParams(location.search):null; 
      
      } catch (error) {
          
      }

    useEffect(() => {
     
     setCurrentUser((query!=null ) ?query.get('user'):"");


    }, []);
    

    return (
      <div className={classes.root}>
        <CssBaseline />
        <AppBar position="absolute" className={clsx(classes.appBar, open && classes.appBarShift)}>
          <Toolbar className={classes.toolbar}>
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
            <Fab variant="extended" color="primary"  href="http://localhost:8080/logout" >
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