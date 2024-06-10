import React, { useState } from "react";
import axios from "axios";
import "react-datepicker/dist/react-datepicker.css";
import "react-time-picker/dist/TimePicker.css";
import "../styles/AddRepair.css";

const isValidHourFormat = (hour) => {
  // Expresión regular para validar el formato de hora (HH:MM)
  const regex = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
  return regex.test(hour);
};

const AddTypeRepair = () => {
  const [name, setName] = useState("");
  const [engine, setEngine] = useState("");
  const [ammount, setAmmount] = useState("");
  const [message, setMessage] = useState(""); // Estado para el mensaje
  const [error, setError] = useState(""); // Estado para el mensaje de error


  const handleAdmissionHourChange = (e) => {
    const { value } = e.target;
    // Verificar si el formato de hora es válido antes de actualizar el estado
    if (isValidHourFormat(value)) {
      setAdmissionHour(value);
    } else {
      // Mostrar un mensaje de error o realizar alguna otra acción en caso de formato inválido
      console.error("Formato de hora inválido");
    }
  };

  const handleExitHourChange = (e) => {
    const { value } = e.target;
    // Verificar si el formato de hora es válido antes de actualizar el estado
    if (isValidHourFormat(value)) {
      setExitHour(value);
    } else {
      // Mostrar un mensaje de error o realizar alguna otra acción en caso de formato inválido
      console.error("Formato de hora inválido");
    }
  };
  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:6081/api/v2/repairs/add",
        {
          name: name,
          engineId: engine,
          ammount: ammount,
        }
      );

      console.log("Respuesta del backend:", response.data);
      setMessage("Tipo de reparación añadida correctamente");
      setError(""); // Limpia el mensaje de error si lo hubiera

      // Aquí podrías mostrar un mensaje de éxito o redirigir a otra página
    } catch (error) {
      console.error("Error al enviar los datos:", error);
      // Aquí podrías mostrar un mensaje de error al usuario
      setError("Error al añadir tipo de reparación");
      setMessage(""); // Limpia el mensaje de éxito si lo hubiera
    }
  };

  return (
    <div className="add-car-container">
      <h1>Agregar un nuevo Tipo de Reparación</h1>
      <form onSubmit={handleSubmit} className="add-car-form">
        <div className="form">
          <label>
            Nombre de la reparación
            <input
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </label>
          <label>
            Tipo de motor
            <select value={engine} onChange={(e) => setEngine(e.target.value)}>
              <option value="">Seleccione el tipo de reparación</option>
              <option value="1">1. Gasolina</option>
              <option value="2">2. Diésel</option>
              <option value="3">3. Híbrido</option>
              <option value="4">4. Eléctrico</option>
            </select>
          </label>

          <label>
            Monto de la reparación
            <input
              type="int"
              value={ammount}
              onChange={(e) => setAmmount(e.target.value)}
            />
          </label>
        </div>
        <button type="submit">Agregar Reparación</button>
      </form>
      {message && <p className="success-message">{message}</p>}
        {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default AddTypeRepair;
