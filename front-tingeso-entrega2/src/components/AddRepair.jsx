import React, { useEffect, useState } from "react";
import axios from "axios";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import TimePicker from "react-time-picker";
import "react-time-picker/dist/TimePicker.css";
import "../styles/AddRepair.css";

const isValidHourFormat = (hour) => {
  // Expresión regular para validar el formato de hora (HH:MM)
  const regex = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
  return regex.test(hour);
};

const AddRepair = () => {
  const [patent, setPatent] = useState("");
  const [admissionDate, setAdmissionDate] = useState(null);
  const [admissionHour, setAdmissionHour] = useState("");
  const [repairOptions, setRepairOptions] = useState([]);
  const [selectedRepair, setRepairType] = useState("");
  const [exitDate, setExitDate] = useState(null);
  const [exitHour, setExitHour] = useState("");
  const [engineId, setEngineId] = useState(null);

  // Función para obtener el engineId basado en la patente
  const fetchEngineId = async (patent) => {
    try {
      console.log("Fetching engineId for patent:", patent);
      // Realizar una solicitud para obtener el objeto completo del automóvil basado en la patente
      const carResponse = await axios.get(
        `http://localhost:8081/api/v2/cars/patent/${patent}`
      );
      console.log("Car response data:", carResponse.data);
      // Obtener el engineId del objeto del automóvil
      const engineId = carResponse.data.engineId;
      console.log("Este es el EngineId:", engineId);
      // Actualizar el estado con el engineId obtenido
      setEngineId(engineId);
      console.log("EngineId set successfully:", engineId);
    } catch (error) {
      console.error("Error fetching engineId:", error);
    }
  };

  // Función para filtrar las opciones de reparación basadas en el engineId
  const filterRepairOptions = () => {
    return repairOptions.filter((repair) => repair.engineId === engineId);
  };

  // Llamada a fetchEngineId cuando cambie la patente del vehículo
  const handlePatentChange = (e) => {
    const { value } = e.target;
    setPatent(value);
    fetchEngineId(value);
  };

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

  useEffect(() => {
    const fetchRepairOptions = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v2/repairs/all"
        );
        setRepairOptions(response.data);
      } catch (error) {
        console.error("Error fetching repair options:", error);
      }
    };

    const filteredOptions = filterRepairOptions();
    setFilteredRepairOptions(filteredOptions);

    // Llamada inicial para obtener todas las opciones de reparación
    fetchRepairOptions();
  }, [patent, engineId, repairOptions]); // Dependencia de patent para volver a obtener opciones de reparación cuando cambie la patente

  // Estado para almacenar las opciones de reparación filtradas
  const [filteredRepairOptions, setFilteredRepairOptions] = useState([]);

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
      // Formatear la fecha de ingreso
      const formattedAdmissionDate = admissionDate
        ? admissionDate.toISOString().split("T")[0]
        : null;

      // Formatear la fecha de salida
      const formattedExitDate = exitDate
        ? exitDate.toISOString().split("T")[0]
        : null;

      // Formatear la hora de ingreso
      const formattedAdmissionHour = admissionHour ? admissionHour : null;

      // Formatear la hora de salida
      const formattedExitHour = exitHour ? exitHour : null;

      const response = await axios.post(
        "http://localhost:8090/api/v1/repairs/add",
        {
          patent: patent,
          admissionDate: formattedAdmissionDate,
          admissionHour: formattedAdmissionHour,
          repairType: repairOptions,
          exitDate: formattedExitDate,
          exitHour: formattedExitHour,
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
      <h1>Agregar una nueva Reparación</h1>
      <form onSubmit={handleSubmit} className="add-car-form">
        <div className="form">
          <label>
            Patente del vehículo:
            <input type="text" value={patent} onChange={handlePatentChange} />
          </label>
          <label>
            Fecha de ingreso:
            <DatePicker // Usa el componente DatePicker
              selected={admissionDate}
              onChange={(date) => setAdmissionDate(date)}
            />
          </label>
          <label>
            Hora de ingreso:
            <TimePicker
              value={admissionHour}
              onChange={setAdmissionHour}
              style={{
                color: "red",
                fontSize: "24px",
                marginRight: "8px",
              }}
            />
          </label>
          <label>
            Tipo de reparación
            <select
              value={selectedRepair}
              onChange={(e) => setRepairType(e.target.value)}
            >
              <option value="">Seleccione el tipo de reparación</option>
              {filteredRepairOptions.map((repair) => (
                <option key={repair.id} value={repair.id}>
                  {repair.name}
                </option>
              ))}
            </select>
          </label>
          <label>
            Fecha de Salida:
            <DatePicker // Usa el componente DatePicker
              selected={exitDate}
              onChange={(date) => setExitDate(date)}
            />
          </label>
          <label>
            Hora de Salida:
            <TimePicker
              value={exitHour}
              onChange={setExitHour}
              style={{ color: "blue !important" }}
            />
          </label>
        </div>
        <button type="submit">Agregar Reparación</button>
      </form>
    </div>
  );
};

export default AddRepair;
