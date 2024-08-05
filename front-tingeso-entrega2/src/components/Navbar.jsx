import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import Sidemenu from "./Sidemenu";
import { useState } from "react";
import '../styles/Navbar.css'; // Importa el archivo CSS para los estilos

export default function Navbar() {
  const [open, setOpen] = useState(false);

  const toggleDrawer = (open) => (event) => {
    setOpen(open);
  };

  return (
    <Box  style={{height: "30px"}}>
      <AppBar position="static" className="navbar"> {/* Agrega la clase "navbar" */}
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            style={{ zIndex: 1000 }}
            onClick={toggleDrawer(true)}
          >
            <MenuIcon />
          </IconButton>

          <Typography variant="h6" component="div" className="navbar-title"> {/* Agrega la clase "navbar-title" */}
            Autofix
          </Typography>
          {/* <Button color="inherit" className="navbar-button">Login</Button> */}
        </Toolbar>
        
        <Sidemenu open={open} toggleDrawer={toggleDrawer} ></Sidemenu>
      </AppBar>
      
    </Box>
  );
}
