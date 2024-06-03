import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/R1.css'; // Importa el archivo de estilos CSS
import carListDetail from './CarListDetail';
import { Link } from "react-router-dom";
import { useNavigate } from 'react-router-dom';

const CarList = () => {
  const [data, setData] = useState([]);
  const navigate = useNavigate();

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:6081/api/v2/carrepairs/carsList');
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const getStatus = (detailId, exitDate) => {
    const today = new Date();
    const exit = new Date(exitDate);
    if (detailId) {
      return "Entregado";
    } else if (exit > today) {
      return "Reparando";
    } else {
      return "Listo para recoger";
    }
  };

  const handleDetail = (id) => {
    navigate(`/carListDetail/${id}`);
    };



  return (
    <div className="tabla-container"> {/* Agrega una clase contenedora para aplicar estilos */}
      <h2>Reparaciones de Autos</h2>
      <table className="tabla-r1"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>ID Auto</th>
            <th>Patente</th>
            <th>Fecha Ingreso </th>
            <th>Hora Ingreso </th>
            <th>Fecha Salida </th>
            <th>Hora Salida </th>
            <th>Fecha Real de Salida </th>
            <th>Hora Real de Salida </th>
            <th>Número de boleta </th>
            <th>Monto de descuentos </th>
            <th>Monto de recargos </th>
            <th>Monto Final </th>
            <th>Estado </th> {/* Dependiendo de la fecha real de salida(si tiene o no) y dependiendo de la fecha de sañida inicial--> Entregado, Reparando o Listo para retirar */}
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.id}</td>
              <td>{item.patent}</td>
              <td>{item.admissionDate}</td>
              <td>{item.admissionHour}</td>
              <td>{item.exitDate}</td>
              <td>{item.exitHour}</td>
              <td>{item.realExitDate}</td>
              <td>{item.realExitHour}</td>
              <td>{item.detailId}</td>
              <td>{item.discountAmounts}</td>
              <td>{item.surchargeAmounts}</td>
              <td>{item.totalAmounts}</td>
              <td>{getStatus(item.detailId, item.exitDate)}</td>
              <td>
                    <button onClick = {() => handleDetail(item.id)}>Ver Detalle</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CarList;
