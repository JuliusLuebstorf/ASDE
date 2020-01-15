import React from 'react';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import ButtonGroup from '@material-ui/core/ButtonGroup';
import GameFields from '../GameFields';
import Grid from '@material-ui/core/Grid';
import IconButton from '@material-ui/core/IconButton';
import MatchList from './MatchList';
import Paper from '@material-ui/core/Paper';
import ServiceClient from '../../../Services/ServiceClient';
import Typography from '@material-ui/core/Typography';

class GameBoardMP extends React.Component {
    static emptyChar = "-";

    constructor(props) {
        super(props);
        
        this.state = {fields: Array(9).fill(GameBoardMP.emptyChar), symbol: null, currentPlayer: null, winner: null, game: true};
        this.clickFunction = this.clickFunction.bind(this);
        this.startTimer = this.startTimer.bind(this);
        this.update = this.update.bind(this);
    }

    componentDidMount() {
        if(this.props.observer)
            this.startTimer();
        else
            ServiceClient.post("/multiplayer/get", {gameID: this.props.gameID, currentPlayer: this.props.user, gridArray: this.state.fields}).then((res) => {
                this.setState({symbol: res.data.symbol, currentPlayer: res.data.player, fields: res.data.gridArray, winner: calculateWinner(res.data.gridArray)});
                
                if(this.state.winner === null)
                    this.startTimer();
                else
                    ServiceClient.get("/multiplayer/endGame?gameID=" + this.props.gameID + "&user=" + this.props.user);
            });
    }

    clickFunction(index) {
        if(this.props.observer || this.state.currentPlayer != this.props.user || this.state.fields[index] != GameBoardMP.emptyChar || this.state.winner != null)
            return;
    
        let array = this.state.fields;
        array[index] = this.state.symbol;

        this.setState({fields: array, winner: calculateWinner(array)});

        if(this.state.winner === null) {
            ServiceClient.post("/multiplayer/move", {gameID: this.props.gameID, currentPlayer: this.props.user, gridArray: array}).then((res)=> {
                this.setState({currentPlayer: res.data}); 
                this.startTimer();
            });
        } else
            ServiceClient.get("/multiplayer/endGame?gameID=" + this.props.gameID + "&user=" + this.props.user);
    }

    render() {
        if(this.state.game)
            return (
                <div>
                    <IconButton onClick={()=> this.setState({game:false})}><ArrowBackIcon/></IconButton>
                    <Grid container direction="row" justify="center" alignItems="center">
                        <Paper><Typography variant="h3" component="h3">Tic-Tac-Toe</Typography></Paper>
                    </Grid>
                    <Grid container direction="row" justify="center" alignItems="center">
                        <div><h2>{this.state.currentPlayer}</h2></div>
                    </Grid>
                    <Grid container direction="row" justify="center" alignItems="center">
                        <ButtonGroup variant="contained" color="primary" aria-label="contained primary button group">
                            <Grid container item xs={200} spacing={200}><GameFields value={this.state.fields[0]} onClick={() => this.clickFunction(0)}/></Grid>
                            <Grid container item xs={12} spacing={200}><GameFields value={this.state.fields[1]} onClick={() => this.clickFunction(1)}/></Grid>
                            <Grid container item xs={12} spacing={200}><GameFields value={this.state.fields[2]} onClick={() => this.clickFunction(2)}/></Grid>
                        </ButtonGroup>
                    </Grid>
                    <Grid container direction="row" justify="center" alignItems="center">
                        <ButtonGroup variant="contained" color="primary" aria-label="contained primary button group">
                            <Grid container item xs={12} spacing={200}><GameFields value={this.state.fields[3]} onClick={() => this.clickFunction(3)}/></Grid>
                            <Grid container item xs={12} spacing={200}><GameFields value={this.state.fields[4]} onClick={() => this.clickFunction(4)}/></Grid>
                            <Grid container item xs={12} spacing={200}><GameFields value={this.state.fields[5]} onClick={() => this.clickFunction(5)}/></Grid>
                        </ButtonGroup>
                    </Grid>
                    <Grid container direction="row" justify="center" alignItems="center">
                        <ButtonGroup variant="contained" color="primary" aria-label="contained primary button group">
                            <Grid container item xs={12} spacing={200}><GameFields value={this.state.fields[6]} onClick={() => this.clickFunction(6)}/></Grid>
                            <Grid container item xs={12} spacing={200}><GameFields value={this.state.fields[7]} onClick={() => this.clickFunction(7)}/></Grid>
                            <Grid container item xs={12} spacing={200}><GameFields value={this.state.fields[8]} onClick={() => this.clickFunction(8)}/></Grid>
                        </ButtonGroup>
                    </Grid>
                    <Grid container direction="row" justify="center" alignItems="center">
                        <h3>{this.state.winner === null ? "" : (this.state.winner === "" ? "Draw..." : this.state.winner + " wins!")}</h3>
                    </Grid>
                </div>
            );
        else
            return(<MatchList user={this.props.user}/>);
    }

    startTimer() {
        if(this.props.observer || (this.state.currentPlayer !== null && this.state.currentPlayer !== this.props.user))
            this.timer = setInterval(this.update, 1500);
    }

    update() {
        if(this.state.winner !== null) {
            clearInterval(this.timer);
            
            if(!this.props.observer)
                ServiceClient.get("/multiplayer/endGame?gameID=" + this.props.gameID + "&user=" + this.props.user);
        } else
            ServiceClient.get("multiplayer/update?gameID=" + this.props.gameID).then((res) => {
                this.setState({currentPlayer: res.data.currentPlayer, fields: res.data.gridArray, winner: calculateWinner(res.data.gridArray)});

                if(this.state.currentPlayer === this.props.user)
                    clearInterval(this.timer);
            });
    }
}

function calculateWinner(array) {
    for(let start = 0; start < array.length; start++)
        if(array[start] !== GameBoardMP.emptyChar && ((start % 3 === 0 && array[start + 1] === array[start] && array[start + 2] === array[start]) || (start < 3 && array[start + 3] === array[start] && array[start + 6] === array[start]) || (start === 0 && array[4] === array[start] && array[8] === array[start]) || (start === 2 && array[4] === array[start] && array[6] === array[start])))
            return array[start];

    for(let index = 0; index < array.length; index++)
        if(array[index] === GameBoardMP.emptyChar)
            return null;

    return "";
}

export default GameBoardMP;