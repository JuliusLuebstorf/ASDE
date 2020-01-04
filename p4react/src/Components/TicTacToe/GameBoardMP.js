import React from 'react';
import GameFields from './GameFields';
import Grid from '@material-ui/core/Grid';
import ButtonGroup from '@material-ui/core/ButtonGroup';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import ServiceClient from '../../Services/ServiceClient';

class GameBoardMP extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            fields: Array(9).fill(""),
            symbol: "X",
            player: 1,
            winner: null,
            clickCount: 0
        };
        this.basicState = this.state;

        this.getGameFields = this.getGameFields.bind(this);
    }

    resetGame = () => {
        this.setState(this.basicState);
        alert("Game has been reset!");
    }


    getGameFields(displayValue) {
        return <GameFields value={this.state.fields[displayValue]}
        onClick={() => this.clickFunction(displayValue)}/>
    }

    clickFunction(index) {
        let targetField = this.state.fields.slice();
        
        if (calculateWinner(targetField)) {
            let winner = calculateWinner(targetField);
            this.setState({winner: targetField})
            return;
          } 

        if (this.state.player===1) {
            targetField[index] = "X";
            this.setState({fields: targetField, player:2});
        } else {
            targetField[index] = "O";
            this.setState({fields:targetField, player:1})
        }
      }




    render() {

        return (
        <div>
            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
                >
            <Paper>
                <Typography variant="h3" component="h3">
                    Tic-Tac-Toe
                </Typography>
            </Paper>
            </Grid>
            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
                >
                <ButtonGroup variant="contained" color="primary" aria-label="contained primary button group">
                    <Grid container item xs={200} spacing={200}>
                        {this.getGameFields(0)}
                    </Grid>
                    <Grid container item xs={12} spacing={200}>
                        {this.getGameFields(1)}
                    </Grid>
                    <Grid container item xs={12} spacing={200}>
                        {this.getGameFields(2)}
                    </Grid>
                </ButtonGroup>
            </Grid>
            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
                >
                <ButtonGroup variant="contained" color="primary" aria-label="contained primary button group">
                    <Grid container item xs={12} spacing={200}>
                        {this.getGameFields(3)}
                    </Grid>
                    <Grid container item xs={12} spacing={200}>
                        {this.getGameFields(4)}
                    </Grid>
                    <Grid container item xs={12} spacing={200}>
                        {this.getGameFields(5)}
                    </Grid>
                </ButtonGroup>
            </Grid>
            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
                >
                <ButtonGroup variant="contained" color="primary" aria-label="contained primary button group">
                    <Grid container item xs={12} spacing={200}>
                        {this.getGameFields(6)}
                    </Grid>
                    <Grid container item xs={12} spacing={200}>
                        {this.getGameFields(7)}
                    </Grid>
                    <Grid container item xs={12} spacing={200}>
                        {this.getGameFields(8)}
                    </Grid>
                </ButtonGroup>
            </Grid>
            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
                >
                    <h3>{(calculateWinner(this.state.fields) === "X") ? "Player 1 wins!" : (calculateWinner(this.state.fields) === "O" ? "Player 2 wins!": this.state.clickCount >= 9 ? "Game ends in a draw!": "")} </h3>
            </Grid>

            <Grid
                container
                direction="row"
                justify="center"
                alignItems="center"
                >
                    
                <ButtonGroup variant="text" color="primary" aria-label="text primary button group">
                <div onClick={this.resetGame}>
                    <Button variant="contained" color="default">
                        Reset Game
                    </Button>
                </div>
                </ButtonGroup>
            </Grid>
        </div>
        );
    }

}


function calculateWinner(squares) {
    const lines = [
      [0, 1, 2],
      [3, 4, 5],
      [6, 7, 8],
      [0, 3, 6],
      [1, 4, 7],
      [2, 5, 8],
      [0, 4, 8],
      [2, 4, 6],
    ];
    for (let i = 0; i < lines.length; i++) {
      const [a, b, c] = lines[i];
      if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
        return squares[a];
      }
    }
    return null;
  }
  

export default GameBoardMP;