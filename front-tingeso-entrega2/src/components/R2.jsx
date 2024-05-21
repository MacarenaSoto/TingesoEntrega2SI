import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/R1.css'; // Importa el archivo de estilos CSS

const R2 = () => {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8090/api/v1/details/report2Prueba');
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="tabla-container"> {/* Agrega una clase contenedora para aplicar estilos */}
      <h2>R2: Reporte de cantidad de tipos de vehículos reparados por reparación</h2>
      <table className="tabla-r1"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>Tipo de Reparación</th>
            <th>Tipo de Vehículo</th>
            <th>Número de Reparaciones</th>
            <th>Precio por Tipo de Reparación</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.repairTypeNameString}</td>
              <td>{item.typeName}</td>
              <td>{item.repairsN}</td>
              <td>{item.repairsTypePrice}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default R2;
