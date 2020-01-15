import React from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import List from '@material-ui/core/List';
import ListSubheader from '@material-ui/core/ListSubheader';
import LooksOneIcon from '@material-ui/icons/LooksOne';
import LooksTwoIcon from '@material-ui/icons/LooksTwo';
import Looks3Icon from '@material-ui/icons/Looks3';

class TTTLeaderboard extends React.Component {

    render() {
        return (
            <List  subheader={<ListSubheader>Leaderboard</ListSubheader>}>
                
                <ListItem>
                  <ListItemAvatar>
                    <Avatar>
                      <LooksOneIcon />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText primary="Rank 1" secondary={this.props.first}/>
                  </ListItem>
                  
                    <ListItem>
                      <ListItemAvatar>
                        <Avatar>
                          <LooksTwoIcon />
                        </Avatar>
                      </ListItemAvatar>
                      <ListItemText primary="Rank 2" secondary={this.props.second} />
                      </ListItem>

                      <ListItem>
                      <ListItemAvatar>
                        <Avatar>
                          <Looks3Icon />
                        </Avatar>
                      </ListItemAvatar>
                      <ListItemText primary="Rank 3" secondary={this.props.third} />
                      </ListItem>

                </List>
        );
    }
}

export default TTTLeaderboard;