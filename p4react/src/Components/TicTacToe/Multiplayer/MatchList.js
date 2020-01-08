import React from 'react';
import GameBoardMP from './GameBoardMP';
import { Button } from '@material-ui/core';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import ServiceClient from '../../../Services/ServiceClient';

class MatchList extends React.Component {
    static ids = 0;

    constructor(props) {
        super(props);
        this.state = {
            game: false,
            matches: []
        }
        this.addGame = this.addGame.bind(this);
        this.getWaitingGames = this.getWaitingGames.bind(this);
        this.startGame = this.startGame.bind(this);
    }

    componentDidMount() {
        this.getWaitingGames();
    }

    getWaitingGames() {
        ServiceClient.get("/multiplayer/getWaitingGames").then((res)=> {
            this.setState({matches: res.data});
        })
    }

    addGame() {
        ServiceClient.get("/multiplayer/newGame");
    }

    startGame() {
        this.setState({game: true});
    }

    render() {
        if(this.state.game)
            return (<GameBoardMP/>);
        else {
            return (
                <div style={{textAlign: "center"}}>
                    <Button style={{color: "blue"}} onClick={this.getWaitingGames} ><h2>Get games</h2></Button>
                    <Button style={{color: "red"}} onClick={this.addGame} ><h2>Create game</h2></Button>
                    
                    {this.state.matches.map(i => 
                        <Card><CardActionArea onClick={this.startGame}>
                            <CardContent><Typography>{i.gameID}: {i.status}</Typography></CardContent>
                        </CardActionArea></Card>)
                    }
                </div>
            );
        }
    }
} export default MatchList;