import React from 'react';
import ClearIcon from '@material-ui/icons/Clear';
import RadioButtonUncheckedIcon from '@material-ui/icons/RadioButtonUnchecked';
import FingerprintRoundedIcon from '@material-ui/icons/FingerprintRounded';
import IconButton from '@material-ui/core/IconButton';

class GameFields extends React.Component {

    render() {
        if (this.props.value == "X") {
        return (
            <IconButton disabled>
                <ClearIcon/>
            </IconButton>);
                
        } else if (this.props.value == "O") {
            return (
            <IconButton disabled>
                <RadioButtonUncheckedIcon/>
            </IconButton>);
        }
        else {
            return (
            <div onClick={() => this.props.onClick()}>
            <IconButton>
                <FingerprintRoundedIcon/>
            </IconButton>
            </div>
            );
        }
    }
}

export default GameFields;

