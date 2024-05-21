import React, { useState } from "react";
import axios from "axios";
import "../styles/AddCar.css";


const AddCar = () => {
  const [patent, setPatent] = useState("");
  const [model, setModel] = useState("");
  const [year, setYear] = useState("");
  const [seats, setSeats] = useState("");
  const [brandId, setBrandId] = useState("");
  const [typeId, setTypeId] = useState("");
  const [engineId, setEngineId] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8090/api/v1/cars/add",
        {
          patent: patent,
          model: model,
          year: year,
          seats: seats,
          brandId: brandId,
          typeId: typeId,
          engineId: engineId,
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
      <h1 className="add-car-title">Agregar un Nuevo Auto</h1>
      <div className="add-car-card">
        <form onSubmit={handleSubmit} className="add-car-form">
          <div className="form">
            <label>
              Patente del vehículo:
              <input
                type="text"
                value={patent}
                onChange={(e) => setPatent(e.target.value)}
              />
            </label>
            <label>
              Modelo:
              <input
                type="text"
                value={model}
                onChange={(e) => setModel(e.target.value)}
              />
            </label>
            <label>
              Año:
              <input
                type="text"
                value={year}
                onChange={(e) => setYear(e.target.value)}
              />
            </label>
            <label>
              Cantidad de asientos:
              <input
                type="text"
                value={seats}
                onChange={(e) => setSeats(e.target.value)}
              />
            </label>
            <label>
              Marca:
              <select
                value={brandId}
                onChange={(e) => setBrandId(e.target.value)}
              >
                <option value="">Selecciona una marca</option>
                <option value="1">Toyota</option>
                <option value="2">Kia</option>
                <option value="3">Honda</option>
                <option value="4">Ford</option>
                <option value="5">Chevrolet</option>
                <option value="6">Hyundai</option>
              </select>
            </label>
            <label>
              Tipo:
              <select
                value={typeId}
                onChange={(e) => setTypeId(e.target.value)}
              >
                <option value="">Selecciona un tipo</option>
                <option value="1">Sedan</option>
                <option value="2">Hatchback</option>
                <option value="3">SUV</option>
                <option value="4">Pickup</option>
                <option value="5">Furgoneta</option>
              </select>
            </label>
            <label>
              Motor:
              <select
                value={engineId}
                onChange={(e) => setEngineId(e.target.value)}
              >
                <option value="">Selecciona un motor</option>
                <option value="1">Gasolina</option>
                <option value="2">Diesel</option>
                <option value="3">Híbrido</option>
                <option value="4">Eléctrico</option>
              </select>
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
          <button  type="submit" class= "boton11">
          {/* <IconButton>
                <AddIcon style={{ color: 'white' }} />
              </IconButton>
              <IconButton>
                <DirectionsCarIcon style={{ color: 'white' }} />
              </IconButton> */}
            Agregar Auto
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddCar;
