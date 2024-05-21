import React, { useEffect, useState } from "react";
import axios from "axios";
import "../styles/AddCar.css";
import Long from "long";

const AddCar = () => {
  const [patent, setPatent] = useState("");
  const [model, setModel] = useState("");
  const [year, setYear] = useState("");
  const [seats, setSeats] = useState("");
  const [brandOptions, setBrandOptions] = useState([]);
  const [selectedBrand, setBrandId] = useState("");
  const [typeOptions, setTypeOptions] = useState([]);
  const [selectedType, setTypeId] = useState("");
  const [engineOptions, setEngineOptions] = useState([]);
  const [selectedEngine, setEngineId] = useState("");


  useEffect(() => {
    const fetchBrandOptions = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8081/api/v2/brands/all"
        );
        setBrandOptions(response.data.map(brand => ({ id: brand.id, name: brand.name })));

      } catch (error) {
        console.error("Error fetching brand options:", error);
      }
    };

    fetchBrandOptions();

    const fetchTypeOptions = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8081/api/v2/types/all"
        );
        setTypeOptions(response.data.map(type => ({ id: type.id, name: type.name })));
      } catch (error) {
        console.error("Error fetching type options:", error);
      }
    }

    fetchTypeOptions();

    const fetchEngineOptions = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8081/api/v2/engines/all"
        );
        setEngineOptions(response.data.map(engine => ({ id: engine.id, name: engine.name })));
      } catch (error) {
        console.error("Error fetching engine options:", error);
      }
    }

    fetchEngineOptions();
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8081/api/v2/cars/add",
        {
          patent: patent,
          model: model,
          year: year,
          seats: seats,
          brandId: selectedBrand,
          typeId: selectedType,
          engineId: selectedEngine,
        }
      );

    
      console.log("Respuesta del backend:", response.data);
      console.log("ID de la marca seleccionada:", selectedBrand);

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
              Seleccionar Marca:
              <select
                value={selectedBrand}
                onChange={(e) => {
                  setBrandId(e.target.value);
                  console.log("Tipo de dato de selectedBrand:", typeof e.target.value);
                }}
              >
                <option value="">Selecciona una marca</option>
                {brandOptions.map((brand) => (
                  <option key={brand.id} value={brand.id}>
                    {brand.name}
                  </option>
                ))}
              </select>
            </label>


            <label>
              Tipo:
              <select
                value={selectedType}
                onChange= {(e) => setTypeId(e.target.value)}
              >
                <option value="">Selecciona un tipo</option>
                {typeOptions.map((typeId) => (
                  <option key={typeId.id} value={typeId.id}>
                    {typeId.name}
                  </option>
                ))}
              </select>
            </label>

            <label>
              Motor:
              <select
                value={selectedEngine}
                onChange= {(e) => setEngineId(e.target.value)}
              >
                <option value="">Selecciona un motor</option>
                {engineOptions.map((engineId) => (
                  <option key={engineId.id} value={engineId.id}>
                    {engineId.name}
                  </option>
                ))}
              </select>
            </label>

          </div>
          <button type="submit" class="boton11">
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
