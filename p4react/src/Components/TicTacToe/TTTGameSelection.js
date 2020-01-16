import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardContent from '@material-ui/core/CardContent';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import React from 'react';
import GameBoardSP from './GameBoardSP';
import MatchList from './Multiplayer/MatchList';
import TTTLeaderboard from './TTTLeaderboard';
import ServiceClient from '../../Services/ServiceClient';

class TTTGameSelection extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            game: 0,
            leaderList: [],
            first: "-",
            second: "-",
            third: "-"
        }

        ServiceClient.getAxiosInstance().get("/multiplayer/leaderboard", {params: {gameType: "TicTacToe"}
        })
        .then((res)=> {
                let newList = res.data;
                newList.slice();
                if (res.data.length === 0) {
                    return
                } else if (res.data.length === 1) {
                    this.setState({first: newList[0]});
                } else if (res.data.length === 2) {
                    this.setState({first: newList[0], second: newList[1]});
                } else {
                    this.setState({first: newList[0], second: newList[1], third: newList[2]});
                }
            } 
            );

    }


    render() { 
     if (this.state.game === 1) {
        return (
            <div>
                <IconButton onClick={()=> this.setState({game:0})}>
                    <ArrowBackIcon/>
                </IconButton>
                <GameBoardSP/>
            </div>
        );
      } 
      else if (this.state.game === 2) {
        return (
            <div>
                <MatchList user={this.props.user}/>
            </div>
        );  
    }

    else {
    return (
    
        <div>
        <Container maxWidth="lg">
                        
        <Grid container spacing={3} >
            <Grid item xs={12} md={8} lg={6} >

            <div>
            <Card >
            <CardActionArea onClick={()=> this.setState({game:1})}>
                <CardContent>
                <Typography gutterBottom variant="h5" component="h2">
                    Singleplayer
                </Typography>
                <Typography variant="body2" color="textSecondary" component="p">
                    Play against AI.
                </Typography>
                </CardContent>
            </CardActionArea>
                    
            </Card>
            </div> 
            </Grid>


            <Grid item xs={12} md={8} lg={6}>

            <div>
            <Card>
            <CardActionArea onClick={()=> this.setState({game:2})}>
                <CardContent>
                <Typography gutterBottom variant="h5" component="h2">
                    Multiplayer
                </Typography>
                <Typography variant="body2" color="textSecondary" component="p">
                    Play online vs your friends.
                </Typography>
                </CardContent>
            </CardActionArea>
            </Card>
            </div>

                
            </Grid>

            <Grid item xs={12} md={8} lg={6}>

            <Card>
                <TTTLeaderboard first={this.state.first} second={this.state.second} third={this.state.third}/>
            </Card>

                
            </Grid>
        </Grid>
        </Container>
        </div>
  );
  
  }
}
}



export default TTTGameSelection;