import React, { useState } from "react";
import axios from "axios";

import MoreTimeIcon from "@mui/icons-material/MoreTime";
import "../styles/AddCar.css";

const AddBrand = () => {
  const [name, setName] = useState("");
  const [message, setMessage] = useState(""); // Estado para el mensaje
  const [error, setError] = useState(""); // Estado para el mensaje de error


  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:6081/api/v2/brands/add",
        {
            name: name,
        }
      );

      console.log("Respuesta del backend:", response.data);
      setMessage("Marca añadida correctamente");
      setError(""); // Limpia el mensaje de error si lo hubiera

      // Aquí podrías mostrar un mensaje de éxito o redirigir a otra página
    } catch (error) {
      console.error("Error al enviar los datos:", error);
      // Aquí podrías mostrar un mensaje de error al usuario
      setError("Error al añadir la marca");
      setMessage(""); // Limpia el mensaje de éxito si lo hubiera
    }
  };

  return (
    <div className="add-car-container">
      <h1 className="add-car-title">Agregar una Nueva Marca </h1>
      <div className="add-car-card">
        <form onSubmit={handleSubmit} className="add-car-form">
          <div className="form">

        
            <label>
              Nombre de la Marca de Vehículo:
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
            </label>
          </div>
          <button startIcon={<MoreTimeIcon />} type="submit">
            Agregar Marca 
          </button>
        </form>
        {message && <p className="success-message">{message}</p>}
        {error && <p className="error-message">{error}</p>}
      </div>
    </div>
  );
};

export default AddBrand;
