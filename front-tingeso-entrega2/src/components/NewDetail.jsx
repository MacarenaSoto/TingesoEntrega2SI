import React, { useState, useEffect } from "react";
import axios from "axios";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import TimePicker from "react-time-picker";
import "react-time-picker/dist/TimePicker.css";
import "../styles/AddRepair.css";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Detail from "./Detail";
import "../styles/NewDetail.css";

//import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
//import { useHistory } from 'react-router-dom';
//import Detail from './Detail';

const isValidHourFormat = (hour) => {
  // Expresión regular para validar el formato de hora (HH:MM)
  const regex = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
  return regex.test(hour);
};

const NewDetail = () => {
  const [patent, setPatent] = useState("");
  const [realExitDate, setRealExitDate] = useState(null);
  const [realExitHour, setRealExitHour] = useState("");
  const [km, setKm] = useState("");
  const [bonusOptions, setBonusOptions] = useState([]);
  const [selectedBonus, setSelectedBonus] = useState("");
  const [id, setCarId] = useState(null);
  const [numberRepairs, setNumberRepairs] = useState(1);
  const [admissionDate, setAdmissionDate] = useState(null);
  const [admissionHour, setAdmissionHour] = useState("");
  const [message, setMessage] = useState(""); // Estado para el mensaje
  const [error, setError] = useState(""); // Estado para el mensaje de error
  const navigate = useNavigate();

  const handleNavigate = () => {
    navigate("/home");
  };

  const fetchCarId = async (patent) => {
    try {
      console.log("Fetching carId for patent:", patent);
      // Realizar una solicitud para obtener el objeto completo del automóvil basado en la patente
      const carResponse = await axios.get(
        `http://localhost:6081/api/v2/cars/patent/${patent}`
      );
      console.log("Car response data:", carResponse.data);

      const carId = carResponse.data.id;
      console.log("Este es el CarId:", carId);

      // Actualizar el estado con el carId obtenido
      setCarId(carId);

      console.log("CarId set successfully:", carId);
    } catch (error) {
      console.error("Error fetching carId:", error);
    }
  };

  const fetchNumberRepairs = async (patent) => {
    try {
      console.log("Fetching numberRepairs for patent:", patent);
      // Realizar una solicitud para obtener el objeto completo del automóvil basado en la patente
      const carResponse = await axios.get(
        `http://localhost:6081/api/v2/carrepairs/car/${id}`
      );
      const numberRepairs = carResponse.data.numberRepairs;
      const admissionDate = carResponse.data.admissionDate;
      const admissionHour = carResponse.data.admissionHour;

      console.log("Este es el numberRepairs:", numberRepairs);

      // Actualizar el estado con el carId obtenido
      setNumberRepairs(numberRepairs);

      console.log("numberRepairs set successfully:", numberRepairs);
    } catch (error) {
      console.error("Error fetching numberRepairs:", error);
    }
  };

  const handlePatentChange = (e) => {
    const { value } = e.target;
    setPatent(value);
    fetchCarId(value);
    fetchNumberRepairs(value);
  };

  useEffect(() => {
    const fetchBonusOptions = async () => {
      try {
        const response = await axios.get(
          "http://localhost:6081/api/v2/bonus/all"
        );
        setBonusOptions(response.data);
      } catch (error) {
        console.error("Error fetching bonus options:", error);
      }
    };

    fetchBonusOptions();
  }, []);

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

  useEffect(() => {
    console.log("El bono seleccionado ha cambiado: ", selectedBonus);
  }, [selectedBonus]);

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      // Formatear la fecha real  de salida
      const formattedRealExitDate = realExitDate
        ? realExitDate.toISOString().split("T")[0]
        : null;

      // Formatear la hora real de salida
      const formattedRealExitHour = realExitHour ? realExitHour : null;

      // Log del valor que se está enviando al backend
      console.log("Valor enviado al backend:", {
        carId: id,
        realExitDate: formattedRealExitDate,
        realExitHour: formattedRealExitHour,
        kilometrajem: km,
        selectedBonus: selectedBonus,
      });

      console.log("2daaaaaaaaaaaaaaaaaa");

      


      console.log("Valores a enviar en la solicitud:");
      console.log("km:", km, typeof km);
      console.log("selectedBonus:", selectedBonus, typeof selectedBonus);
      console.log(
        "formattedRealExitDate:",
        formattedRealExitDate,
        typeof formattedRealExitDate
      );

      const response3 = await axios.put(
        `http://localhost:6081/api/v2/details/update/${id}/${km}/${formattedRealExitDate}/${selectedBonus}`,
        {
          
        }
      );

      const response4 = await axios.put(
        `http://localhost:6081/api/v2/carrepairs/update/${id}?selectedBonus=${selectedBonus}&km=${km}&realExitDate=${formattedRealExitDate}`,
        {
          //realExitDate: formattedRealExitDate,
          realExitHour: formattedRealExitHour,
          //selectedBonus: selectedBonus,
          //km: km,
        }
      );

      setMessage("¡Boleta generada exitosamente!");
      setError(""); // Limpia el mensaje de error si lo hubiera


      // Aquí podrías mostrar un mensaje de éxito o redirigir a otra página
      //history.push('/detail');

      let bonusToSend = selectedBonus; // Almacena el valor seleccionado del bono

      // Verificar si se ha seleccionado un bono
      if (selectedBonus === "") {
        console.log("No se ha seleccionado un bono");
        // Define el valor que se enviará al backend si no se selecciona un bono
        bonusToSend = "0"; // O cualquier otro valor que desees enviar
        // Aquí podrías mostrar un mensaje de error al usuario o realizar alguna otra acción
        return;
      }
      console.log("1eraaaaaaaaaaaaaaaaaa");
    } catch (error) {
      console.error("Error al enviar los datos:", error);
      // Aquí podrías mostrar un mensaje de error al usuario
      setError("Error al generar la nueva boleta ");
      setMessage(""); // Limpia el mensaje de éxito si lo hubiera
    }
  };

  return (
    <div>
      <div className="add-car-container">
        <h1 className="add-car-title">Crear una nueva Boleta</h1>
        <div className="add-car-card">
          <form onSubmit={handleSubmit} className="add-car-form">
            <div className="form">
              <label>
                Patente del vehículo:
                <input
                  type="text"
                  value={patent}
                  onChange={handlePatentChange}
                />
              </label>
              <label>
                Kilometraje del vehículo:
                <input
                  type="number"
                  value={km}
                  onChange={(e) => setKm(parseInt(e.target.value))}
                />
              </label>
              <label>
                Fecha real de Salida:
                <DatePicker // Usa el componente DatePicker
                  selected={realExitDate}
                  onChange={(date) => setRealExitDate(date)}
                />
              </label>
              <label>
                Hora real de Salida:
                <TimePicker value={realExitHour} onChange={setRealExitHour} />
              </label>

              <label>
                Bono:
                <select
                  value={selectedBonus}
                  onChange={(e) => {
                    const value = e.target.value;
                    const selectedValue = value !== "" ? parseInt(value) : "0"; // Si es una cadena vacía, asigna "0"

                    setSelectedBonus((prevBonus) =>{
                      console.log("Se seleccionó el bono : ", selectedValue);
                      return selectedValue;

                    } );
                    /* console.log("Se seleccionó el bono : ", selectedValue);
                    console.log(
                      "Se seleccionó el bonoooooooooooooooo final  : ",
                      selectedBonus
                    ); */
                  }}
                >
                  <option value="">Seleccionar Bono</option>
                  {bonusOptions.map((option) => (
                    <option key={option.id} value={option.id}>
                      {option.number} {option.brand}
                    </option>
                  ))}
                </select>
              </label>
            </div>
            <button type="submit">Generar Boleta</button>
          </form>
          <button onClick={handleNavigate} className="boton11">
            Inicio
          </button>
          {message && <p className="success-message">{message}</p>}
        {error && <p className="error-message">{error}</p>}
        </div>
      </div>
    </div>
  );
};

const styles = {
  column: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
  },
  row: {
    margin: "10px",
  },
  button: {
    backgroundColor: "#2ea6b8",
    border: "none",
    color: "white",
    padding: "15px 32px",
    textAlign: "center",
    textDecoration: "none",
    display: "inline-block",
    fontSize: "16px",
    cursor: "pointer",
    borderRadius: "50%",
    boxShadow: "0px 4px 8px rgba(0, 0, 0, 0.1)",
    transition: "background-color 0.3s",
  },
};

export default NewDetail;
