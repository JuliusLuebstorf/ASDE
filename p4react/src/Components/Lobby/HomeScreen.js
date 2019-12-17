import React from 'react';
import Leaderboard from './Leaderboard';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

class HomeScreen extends React.Component {

    render() {
        return (
            <div>
                <Container maxWidth="lg">
                
                    <Grid container spacing={3}>
            
                    {/* Chart */}
                        <Grid item xs={12} md={8} lg={9}>
                        <Paper>
                            <Typography variant="h5" component="h3">
                               Please select a game.
                            </Typography>
                            <Typography component="p">

                            </Typography>
                            </Paper>
                        </Grid>
            
                    {/* Leaderboard */}
                        <Grid item xs={12} md={4} lg={3}>
                            <Leaderboard/>
                        </Grid>
                    </Grid>
                </Container>
            </div>
        );
    }
}

export default HomeScreen;