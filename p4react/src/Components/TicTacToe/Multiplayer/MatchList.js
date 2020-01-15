import React from 'react';
import { Button } from '@material-ui/core';
import ArrowBackIcon from '@material-ui/icons/ArrowBack';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import GameBoardMP from './GameBoardMP';
import IconButton from '@material-ui/core/IconButton';
import ServiceClient from '../../../Services/ServiceClient';
import TTTGameSelection from '../TTTGameSelection';
import Typography from '@material-ui/core/Typography';

class MatchList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            currentMatch: null,
            matches: [],
            observer: false
        }
        this.addGame = this.addGame.bind(this);
        this.countDown = this.countDown.bind(this);
        this.getGames = this.getGames.bind(this);
        this.endGame = this.endGame.bind(this);
        this.startGame = this.startGame.bind(this);
        this.watch = this.watch.bind(this);
    }

    componentDidMount() {
        this.getGames();

        this.timer = setInterval(this.countDown, 1500);
    }

    addGame() {
        ServiceClient.get("/multiplayer/newGame?player=" + this.props.user);
    }

    countDown() {
        this.getGames();
        
        if(this.state.currentMatch != null)
          clearInterval(this.timer);
    }

    getGames() {
        ServiceClient.get("/multiplayer/getGames").then((res)=> {
            this.setState({matches: res.data});
        });
    }

    
    endGame(match) {
        ServiceClient.get("/multiplayer/isRunning?gameID=" + match.gameID).then((res)=> {
            if(match.players[0] === this.props.user && !res.data) {
                ServiceClient.get("/multiplayer/endGame?gameID=" + match.gameID + "&user=" + this.props.user);
                this.setState({matches: []});
            } else
                alert("Cannot end match...");
        });
    }

    startGame(match) {
        if(match.gameStatus === "Waiting") {
            ServiceClient.post("/multiplayer/joinGame", {gameID: match.gameID, currentPlayer: this.props.user}).then((res) => {
                if(res.data === true)
                    this.setState({currentMatch: match.gameID, matches: []});
                else
                    alert("Nobody joined yet...");
            });
        } else if(match.gameStatus === "Running" && match.players.includes(this.props.user))
            this.setState({currentMatch: match.gameID, matches: []});
        else if(match.gameStatus === "Running" && !match.players.includes(this.props.user))
            alert("Cannot start match...");
    }

    render() {
        if(this.state.currentMatch != null && this.state.currentMatch > 0)
            return (<GameBoardMP gameID={this.state.currentMatch} user={this.props.user} observer={this.state.observer}/>);
        else if(this.state.currentMatch == null) {
            return (
                <div>
                    <IconButton onClick={()=> this.setState({currentMatch: -1})}><ArrowBackIcon/></IconButton>
                    <div style={{textAlign: "center"}}>
                        <Button style={{color: "blue"}} onClick={this.addGame} ><h2>Create match</h2></Button>
                        
                        {this.state.matches.map(match => 
                            <Card>
                                <CardContent>
                                    <Typography>
                                        <Button onClick={() => this.watch(match)}>Watch</Button>
                                        <Button onClick={() => this.startGame(match)}>Start</Button>{match.gameID}) {match.players[0]}: {match.gameStatus}
                                        <Button onClick={() => this.endGame(match)}>End</Button>
                                    </Typography>
                                </CardContent>    
                            </Card>)
                        }
                    </div>
                </div>
            );
        } else if(this.state.currentMatch != null && this.state.currentMatch < 0)
            return(<TTTGameSelection user={this.props.user}/>);
    }

    watch(match) {
        if(match.gameStatus != "Running")
            alert("Match not started");
        else 
            ServiceClient.get("/multiplayer/isRunning?gameID=" + match.gameID).then((res)=> {
                if(res.data === true)
                    this.setState({currentMatch: match.gameID, matches: [], observer: true});
            });
    }
} export default MatchList;