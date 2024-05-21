import React from "react";
import { Link } from "react-router-dom";
import AddIcon from "@mui/icons-material/Add";
import IconButton from "@mui/material/IconButton";
import DirectionsCarIcon from "@mui/icons-material/DirectionsCar";
import BuildIcon from "@mui/icons-material/Build";
import ConfirmationNumberIcon from "@mui/icons-material/ConfirmationNumber";

import carImage from "../assets/muscular-car-service-worker-repairing-vehicle.jpg";
import "../styles/Home.css";

const Home = () => {
  return (
    <div className="main-container">
      <div style={{ backgroundColor: "white", padding: "20px"  ,width: '1000px', height: '180px' }}>
        <div className="title">
          <p>Autofix</p>
        </div>
      </div>
      <div className="card">
        <div className="card-image">
          <img src={carImage} alt="Example" className="example-image" />
        </div>
        <div className="card-buttons">
          <div className="button">
            <Link to="/AddCar" className="button-link">
              <IconButton>
                <AddIcon style={{ color: "white" }} />
              </IconButton>
              <IconButton>
                <DirectionsCarIcon style={{ color: "white" }} />
              </IconButton>
              Agregar Auto Nuevo
            </Link>
          </div>
          <div className="button">
            <Link to="/AddTypeRepair" className="button-link">
              <IconButton>
                <AddIcon style={{ color: "white" }} />
              </IconButton>
              <IconButton>
                <BuildIcon style={{ color: "white" }} />
              </IconButton>
              Agregar Tipo de Reparación
            </Link>
          </div>
          <div className="button">
            <Link to="/AddRepair" className="button-link">
              <IconButton>
                <AddIcon style={{ color: "white" }} />
              </IconButton>
              <IconButton>
                <BuildIcon style={{ color: "white" }} />
              </IconButton>
              Agregar Reparación a Auto
            </Link>
          </div>
          <div className="button">
            <Link to="/NewDetail" className="button-link">
              <IconButton>
                <AddIcon style={{ color: "white" }} />
              </IconButton>
              <IconButton>
                <ConfirmationNumberIcon style={{ color: "white" }} />
              </IconButton>
              Generar Boleta
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

/* const styles = {
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    minHeight: '100vh',
  },
  column: {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  row: {
    margin: '10px',
  },
  button: {
    backgroundColor: '#4CAF50',
    border: 'none',
    color: 'white',
    padding: '15px 32px',
    textAlign: 'center',
    textDecoration: 'none',
    display: 'inline-block',
    fontSize: '16px',
    cursor: 'pointer',
    borderRadius: '10%',
    boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.1)',
    transition: 'background-color 0.3s',
  },
}; */

export default Home;
