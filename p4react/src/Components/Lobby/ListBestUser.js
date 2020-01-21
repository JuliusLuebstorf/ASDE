import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import IconButton from '@material-ui/core/IconButton';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import FolderIcon from '@material-ui/icons/Folder';
import DeleteIcon from '@material-ui/icons/Delete';
import ServiceClient from '../../Services/ServiceClient';

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
        maxWidth: 752,
    },
    demo: {
        backgroundColor: theme.palette.background.paper,
    },
    title: {
        margin: theme.spacing(4, 0, 2),
    },
}));


export default function ListBestUser() {
    const classes = useStyles();
    const [dense, setDense] = React.useState(false);
    const [secondary, setSecondary] = React.useState(false);

    const [listOfElements, setListOfElements] = React.useState([]);

    //const listOfElements = ['a', 'b', 'c'];


    useEffect(() => {
        alert("ejecuto use effect");
        //setCurrentUser((query != null) ? query.get('user') : "");

        ServiceClient.getAxiosInstance().get("/multiplayer/leaderboardTopList", {
            params: { gameType: "TicTacToe" }
        })
            .then(function (response) {
                console.log(response.data);
                console.log(response.status);

                setListOfElements(response.data);

                /*
                
                if (response.status === 200) {
                  setCurrentUser(response.data);
                }
                */

            }).catch(function (error) {

                /*console.log(error);
                  //console.log(error.response.status);
                  if (error.response.status === 401 || error.response.status === 404) {
                    ReactDOM.render(<SignIn />, document.getElementById('root'));
                  }
                  */
            })

    }, []);

    return (
        <div className={classes.root}>

            <Grid container spacing={2}>
                <Grid item xs={12} md={6}>
                    <Typography variant="h6" className={classes.title}>
                        Text only
          </Typography>
                    <div className={classes.demo}>
                        <List dense={dense}>

                            {listOfElements.map(function (item) {
                                return <ListItem><ListItemText key={item.username}>{item.ticTacToeScore + " " + item.username}</ListItemText> </ListItem>;
                            })}

                        </List>
                    </div>
                </Grid>

            </Grid>

        </div>
    );
}