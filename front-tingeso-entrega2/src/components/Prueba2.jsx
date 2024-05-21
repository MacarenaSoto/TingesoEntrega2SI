import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/Prueba.css'; // Importa el archivo de estilos CSS

const Prueba2 = () => {
  const [listaReparaciones, setListaReparaciones] = useState([]);

  const obtenerListaReparaciones = async () => {
    try {
      const response = await axios.get('http://localhost:8090/api/v1/repairs/all');
      setListaReparaciones(response.data);
    } catch (error) {
      console.error('Error al obtener la lista de reparaciones:', error);
    }
  };

  useEffect(() => {
    obtenerListaReparaciones();
  }, []);

  return (
    <div className="tabla-container"> {/* Agrega una clase contenedora para aplicar estilos */}
      <h2>Lista de Reparaciones</h2>
      <table className="tabla-reparaciones"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>ID</th>
            <th>Fecha de Admisión</th>
            <th>Hora de Admisión</th>
            <th>Tipo de Reparación</th>
            <th>Monto</th>
            <th>Fecha de Salida</th>
            <th>Hora de Salida</th>
            <th>Fecha Real de Salida</th>
            <th>Hora Real de Salida</th>
            <th>ID de Coche</th>
            <th>ID de Detalle</th>
          </tr>
        </thead>
        <tbody>
          {listaReparaciones.map(reparacion => (
            <tr key={reparacion.id}>
              <td>{reparacion.id}</td>
              <td>{reparacion.admissionDate}</td>
              <td>{reparacion.admissionHour}</td>
              <td>{reparacion.repairType}</td>
              <td>{reparacion.amount}</td>
              <td>{reparacion.exitDate}</td>
              <td>{reparacion.exitHour}</td>
              <td>{reparacion.realExitDate}</td>
              <td>{reparacion.realExitHour}</td>
              <td>{reparacion.carId}</td>
              <td>{reparacion.detailId}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Prueba2;
