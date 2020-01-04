import React from 'react';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import Container from '@material-ui/core/Container';
import GameBoardSP from './GameBoardSP';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import IconButton from '@material-ui/core/IconButton';
import GameBoardMP from './GameBoardMP';

class TTTGameSelection extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            game: 0,
        }
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
                <IconButton onClick={()=> this.setState({game:0})}>
                    <ArrowBackIcon/>
                </IconButton>
                <GameBoardMP/>
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
        </Grid>
        </Container>
        </div>
  );
  
  }
}
}



export default TTTGameSelection;