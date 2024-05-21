import React, { useState } from "react";
import axios from "axios";

import MoreTimeIcon from "@mui/icons-material/MoreTime";
import "../styles/AddCar.css";

const AddCar = () => {
  const [brandId, setBrandId] = useState("");
  const [number, setNumber] = useState("");
  const [amount, setAmount] = useState("");
  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8090/api/v1/bonuss/add",
        {
            brandId: brandId,
            number: number,
            amount: amount,
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
      <h1 className="add-car-title">Agregar un Nuevo Bonus</h1>
      <div className="add-car-card">
        <form onSubmit={handleSubmit} className="add-car-form">
          <div className="form">
          <label>
              Marca:
              <select
                value={brandId}
                onChange={(e) => setBrandId(e.target.value)}
              >
                <option value="">Selecciona una marca</option>
                <option value="1">Toyota</option>
                <option value="4">Ford</option>
                <option value="6">Hyundai</option>
                <option value="3">Honda</option>
                <option value="0">No aplica</option>
                
                
              </select>
            </label>
            <label>
              Cantidad de bonos:
              <input
                type="text"
                value={number}
                onChange={(e) => setNumber(e.target.value)}
              />
            </label>
            <label>
              Monto del bono:
              <input
                type="text"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
              />
            </label>
           
            {/* <FormControl fullWidth>
              <TextField
                id="month"
                label="PRUEBA"
                value={{ engineId }}
                select
                variant="standard"
                defaultValue="1"
                onChange={(e) => setMonth(e.target.value)}
                style={{ width: "25%" }}
              >
                <MenuItem value={1}>Enero</MenuItem>
                <MenuItem value={2}>Febrero</MenuItem>
                <MenuItem value={3}>Marzo</MenuItem>
                <MenuItem value={4}>Abril</MenuItem>
                <MenuItem value={5}>Mayo</MenuItem>
                <MenuItem value={6}>Junio</MenuItem>
                <MenuItem value={7}>Julio</MenuItem>
                <MenuItem value={8}>Agosto</MenuItem>
                <MenuItem value={9}>Septiembre</MenuItem>
                <MenuItem value={10}>Octubre</MenuItem>
                <MenuItem value={11}>Noviembre</MenuItem>
                <MenuItem value={12}>Diciembre</MenuItem>
              </TextField>
            </FormControl> */}
          </div>
          <button startIcon={<MoreTimeIcon />} type="submit">
            Agregar Bono
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddCar;
