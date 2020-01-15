import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

import ServiceClient from '../../Services/ServiceClient';
import {
  useLocation
} from "react-router-dom";

const useStyles = makeStyles(theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200,
  },

  button: {
    margin: theme.spacing(1),
  },
}));


export default class CreateUser extends React.Component {

  constructor() {
    super();
    this.state = {
      user_name: '',
      password: '',
      email: ''
    }
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }




  handleSubmit(e) {
    e.preventDefault();

    ServiceClient.get("/addUser", {
      params: { username: this.state.user_name, pass: this.state.password, email: this.state.email }
    }).then((res) => {

      //console.log(res.data);

      // this.props.updateUsers();
    })

    this.setState({
      user_name: '',
      password: '',
      email: ''
    });
  }

  handleInputChange(e) {
    const { value, name } = e.target;
    console.log(value, name);
    this.setState({
      [name]: value
    });
  }


  render() {
    // RETURN THE COMPONENT

    var query = null;
    try {
      const location = useLocation();
      query = location != null ? new URLSearchParams(location.search) : null;

    } catch (error) {
    }
    const msg = (query != null && query.get('msg') === 'addUser_problem') ? "User or Email already exist" : "";


    return (

      <form onSubmit={this.handleSubmit} className={useStyles.container} noValidate autoComplete="off">
        <ul >
          <li style={{ listStyleType: "none" }}>
          <Typography component="h4" variant="h10" color='error'>
            {msg}
          </Typography>
          </li>
        
          <li style={{ listStyleType: "none" }}>
            <div>
              <TextField
                id="outlined-basic"
                name="user_name"
                className={useStyles.textField}
                label="User Name"
                margin="normal"
                variant="outlined"
                value={this.state.user_name}
                onChange={this.handleInputChange}
              />
            </div>
          </li>
          <li style={{ listStyleType: "none" }}>

            <div>
              <TextField
                id="outlined-basic"
                name="email"
                className={useStyles.textField}
                label="Email"
                margin="normal"
                variant="outlined"
                value={this.state.email}
                onChange={this.handleInputChange}
              />
            </div>
          </li>
          <li style={{ listStyleType: "none" }}>
            <div>
              <TextField
                id="outlined-password-input"
                name="password"
                label="Password"
                className={useStyles.textField}
                type="password"
                autoComplete="current-password"
                margin="normal"
                variant="outlined"
                value={this.state.password}
                onChange={this.handleInputChange}
              />

            </div>
          </li>


          <li style={{ listStyleType: "none" }}>

            <div>

              <Button variant="contained" color="primary" className={useStyles.button} type="submit">
                Create User
                            </Button>

            </div>
          </li>
        </ul>
      </form>
    );
  }
}
