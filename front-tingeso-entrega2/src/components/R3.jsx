import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/R1.css'; // Importa el archivo de estilos CSS

const R3 = () => {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8090/api/v1/details/report3');
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
      <h2>R3: Reporte de Tiempo Promedio de Reparación por Marca en días</h2>
      <table className="tabla-r1"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>Marca</th>
            <th>Tiempo Promedio de Reparación (Días)</th>
            <th>Tiempo Promedio de Reparación (Horas)</th>
            <th>Tiempo de reparación más rápida (Días)</th>
            <th>Tiempo de reparación más lenta (Días)</th>
            <th>Tiempo de reparación más rápida (Horas)</th>
            <th>Tiempo de reparación más lenta (Horas)</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.brandName}</td>
              <td>{item.timePromRepair}</td>
              <td>{item.timeHPromRepair}</td>
              <td>{item.fasterRepairTime}</td>
              <td>{item.slowerRepairTime}</td>
              <td>{item.fasterRepairTimeH}</td>
              <td>{item.slowerRepairTimeH}</td>
              

            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default R3;
