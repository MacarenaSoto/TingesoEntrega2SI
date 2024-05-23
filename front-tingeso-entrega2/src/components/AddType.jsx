import React, { useState } from "react";
import axios from "axios";

import MoreTimeIcon from "@mui/icons-material/MoreTime";
import "../styles/AddCar.css";

const AddType = () => {
  const [name, setName] = useState("");
  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:6081/api/v2/types/add",
        {
            name: name,
        }
      );

      console.log("Respuesta del backend:", response.data);

      // Aquí podrías mostrar un mensaje de éxito o redirigir a otra página
    } catch (error) {
      console.error("Error al enviar los datos:", error);
      // Aquí podrías mostrar un mensaje de error al usuario
    }
  };

  return (
    <div className="add-car-container">
      <h1 className="add-car-title">Agregar un Nuevo Tipo</h1>
      <div className="add-car-card">
        <form onSubmit={handleSubmit} className="add-car-form">
          <div className="form">

        
            <label>
              Nombre del tipo de vehículo:
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </label>
          </div>
          <button startIcon={<MoreTimeIcon />} type="submit">
            Agregar Tipo 
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddType;
